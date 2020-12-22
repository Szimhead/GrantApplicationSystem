import React from 'react';

import {Header} from '../components/header';
import {Footer} from '../components/footer';
import {PageTitle} from "../components/page-title";
import {Answer, Requirement} from "../types";
import {RequirementBox} from "../components/requirement";
import {text1, text2, text3} from "../components/randomTestTexts";

const exampleRequirement1: Requirement = {name: "Introduction", contentType: "text"}
const exampleRequirement2: Requirement = {name: "Related Work", contentType: "text"}
const exampleRequirement3: Requirement = {name: "Work Plan", contentType: "text"}
const exampleRequirement4: Requirement = {name: "Publications", contentType: "file"}

const exampleAnswer1: Answer = {requirement: exampleRequirement1, content: "this is my introduction: "+text1}
const exampleAnswer2: Answer = {requirement: exampleRequirement2, content: "this is my related work: "+text2}
const exampleAnswer3: Answer = {requirement: exampleRequirement3, content: "this is my work plan: "+text3}
const exampleAnswer4: Answer = {requirement: exampleRequirement4, content: ""}
const answers: Answer[] = [exampleAnswer1, exampleAnswer2, exampleAnswer3, exampleAnswer4]

export const SubmittedAppPage = () => {
    return (
        <>
            <Header/>
            <PageTitle title={"Application 1 (submitted)"} extraText={"Result: Pending"}/>
            <RequirementBox answers={answers}/>
            <Footer/>
        </>
    );
};


export default SubmittedAppPage;