import React, {useEffect, useState} from "react";
import {Header} from "../components/header";
import {PageTitle} from "../components/page-title";
import {Borders} from "../components/borders";
import {Footer} from "../components/footer";
import {Student} from "../types";
import {Col, Row} from "react-bootstrap";
import {DropList} from "../components/dropList";
import {ApplicationControllerApi, ApplicationDTO, StudentControllerApi, UserDTO, GrantCallControllerApi, GrantCallDTO} from "../clientAPI";
import {useParams} from "react-router-dom";

export const StudentProfile=()=>{
    let {id} = useParams<Record<string, string | undefined>>();
    const studId = Number(id);
    const [student, setStudent] = useState(null as any)
    const [toBeSubmitted, setToBeSubmitted] = useState([] as ApplicationDTO[])
    const studentAPI = new StudentControllerApi();
    const applicationAPI = new ApplicationControllerApi();


    useEffect(() => {
        //TODO: get user id
        studentAPI.getOneUsingGET6(studId).then((value) => setStudent(value as UserDTO))
        applicationAPI.getAllAnswersUsingGET(studId).then((value) => setToBeSubmitted(value as ApplicationDTO[]))
    })

    function mapAllApplications(apps:ApplicationDTO[]){
        return apps.map(app=>[app.callTitle, app.status.toString()])
    }


    const content= <div>
        <Row>
            <Col className="m-4">
                <h5>Name:</h5>
                <p>{student==null?"":(student.name)}</p>
                <h5>Email:</h5>
                <p>{student==null?"":(student.email)}</p>
            </Col>
            <Col className="m-4">
                <h5>Address:</h5>
                <p>{student==null?"":(student.address)}</p>
            </Col>
        </Row>

    </div>
    return(
        <>
            <Header />
            <PageTitle title={"My profile"} extraText={""}/>
            <div className="container">
                <Borders title={"Details"} content={content}/>
                <DropList title="Applications to be submitted" headers={["Grant Call"]} records={[["Grant Call 1"],["Grant Call 2"],["Grant Call 3"]]} show={false}/>

                <DropList title="Evaluated applications" headers={["Grant Call Name","Result"]}
                          records={[["Grant Call 1", "Accepted"],["Grant Call 2", "Accepted"],["Grant Call 3", "Denied"]]} show={false}/>

                <DropList title="All applications" headers={["Grant Call Name","Status"]} records={mapAllApplications(toBeSubmitted)} show={false}/>
            </div>
            <Footer />
        </>

    )
};

export default StudentProfile;