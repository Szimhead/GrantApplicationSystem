import React, {useEffect, useState} from "react";
import {Header} from "../components/header";
import {PageTitle} from "../components/page-title";
import {Borders} from "../components/borders";
import {Footer} from "../components/footer";
import {Student} from "../types";
import {Col, Row} from "react-bootstrap";
import {DropList} from "../components/dropList";
import {ApplicationControllerApi, ApplicationDTO, StudentControllerApi, UserDTO, GrantCallControllerApi, GrantCallDTO} from "../clientAPI";

export const StudentProfile=()=>{
    const [student, setStudent] = useState(null as any)
    const [toBeSubmitted, setToBeSubmitted] = useState([] as ApplicationDTO[])
    const studentinho:Student={id:0,name:"Jacek",email:"Jacek@gmail.com",address:"Tiago's house"}
    const studentAPI = new StudentControllerApi();
    const applicationAPI = new ApplicationControllerApi();
    const callAPI = new GrantCallControllerApi();


    useEffect(() => {
        //TODO: get user id
        studentAPI.getOneUsingGET6(2).then((value) => setStudent(value as UserDTO))
        applicationAPI.getAllAnswersUsingGET(2).then((value) => setToBeSubmitted(value as ApplicationDTO[]))
    })

    function mapApplications(apps:ApplicationDTO[]){
        return [apps.map(app=>[app.callTitle])]
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

                <DropList title="All applications" headers={["Grant Call Name","Status"]} records={[["Grant 1", "Submitted"],
                    ["Grant 2","Evaluated"],["Grant Call 3","To be submitted"]]} show={false}/>
            </div>
            <Footer />
        </>

    )
};

export default StudentProfile;