import React from 'react';

import {Header} from '../components/header';
import {Footer} from '../components/footer';
import {PageTitle} from "../components/page-title";
import {Borders} from "../components/borders";
import {ApplicationTabs} from "../components/tabs";
import {Student, Answer} from "../types";
import {StudentDetails} from "../components/studentDetails";

const exampleStudent: Student = {
    id: 0,
    name: "John Smith",
    email: "j.smith@gmail.com",
    address: "empty",
}

const exampleAnswer1: Answer = { title: "Introduction", content: "this is my introduction" }
const exampleAnswer2: Answer = { title: "Related Work", content: "this is my related work" }
const exampleAnswer3: Answer = { title: "Work plan", content: "this is my work plan" }
const exampleAnswer4: Answer = { title: "Publications", content: "my publications" }

export const ReviewerApplication = () => {
    return (
        <>
            <Header/>
            <PageTitle title={"Application 1"} extraText={"Submission date: (some date)"}/>
            <div className="container">
                <div className="row mt-4 justify-content-center">
                   <ApplicationTabs answers={[exampleAnswer1, exampleAnswer2, exampleAnswer3, exampleAnswer4]} />
                   <Borders title={"Student details"} content={<StudentDetails student={exampleStudent} />}/>
                </div>
            </div>
            <Footer/>
        </>
    );
};

export default ReviewerApplication;