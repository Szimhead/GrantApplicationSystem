import React, {useEffect, useState} from 'react';

import {Header} from '../components/header';
import {Footer} from '../components/footer';
import {PageTitle} from "../components/page-title";
import {Borders} from "../components/borders";
import {ApplicationTabs} from "../components/tabs";
import {Student, Answer, Requirement} from "../types";
import {StudentDetails} from "../components/studentDetails";
import {useParams} from "react-router-dom";
import {ApplicationControllerApi, ApplicationDTO, GrantCallDTO, ReviewDTO, StudentControllerApi} from "../clientAPI";

const exampleRequirement1: Requirement = {name: "Introduction", contentType: "text"}
const exampleRequirement2: Requirement = {name: "Related Work", contentType: "text"}
const exampleRequirement3: Requirement = {name: "Work Plan", contentType: "text"}
const exampleRequirement4: Requirement = {name: "Publications", contentType: "file"}
const exampleAnswer1: Answer = {requirement: exampleRequirement1, content: "this is my introduction"}
const exampleAnswer2: Answer = {requirement: exampleRequirement2, content: "this is my related work"}
const exampleAnswer3: Answer = {requirement: exampleRequirement3, content: "this is my work plan"}
const exampleAnswer4: Answer = {requirement: exampleRequirement4, content: "my publications"}

export const ReviewerApplication = () => {
    let {id} = useParams<Record<string, string | undefined>>();
    const appId = Number(id);
    const studentAPI = new StudentControllerApi();
    const applicationAPI = new ApplicationControllerApi();
    const [student, setStudent] = useState(null as any)

    useEffect(() => {
        applicationAPI.getOneUsingGET(appId).then((value) => {
            if (student == null)
                studentAPI.getOneUsingGET6(value.studentId).then((value) => setStudent(value))
            console.log(student)
        })
    })

    const handleNull = () => {
        if (student != null) {
            return <>
                <Header/>
                <PageTitle title={"Application " + appId} extraText={"Submission date: (some date)"}/>
                <div className="container">
                    <div className="row mt-4 justify-content-center">
                        <ApplicationTabs answers={[exampleAnswer1, exampleAnswer2, exampleAnswer3, exampleAnswer4]}/>
                        <div className="col-4">
                            <Borders title={"Student details"} content={<StudentDetails student={student}/>}/>
                        </div>
                    </div>
                </div>
                <Footer/>
            </>
        } else
            return <>empty</>
    }

    return (
        handleNull()
    );
};

export default ReviewerApplication;