import React from 'react';

import {Header} from '../components/header';
import {Footer} from '../components/footer';
import {PageTitle} from "../components/page-title";
import {Answer, Requirement, Review} from "../types";
import {RequirementBox} from "../components/requirement";
import {text1, text2, text3, text6} from "../components/randomTestTexts";
import {Borders} from "../components/borders";
import ReviewsList from "../components/reviewsList";

const exampleRequirement1: Requirement = {name: "Introduction", contentType: "text"}
const exampleRequirement2: Requirement = {name: "Related Work", contentType: "text"}
const exampleRequirement3: Requirement = {name: "Work Plan", contentType: "text"}
const exampleRequirement4: Requirement = {name: "Publications", contentType: "file"}

const exampleAnswer1: Answer = {requirement: exampleRequirement1, content: "this is my introduction: " + text1}
const exampleAnswer2: Answer = {requirement: exampleRequirement2, content: "this is my related work: " + text2}
const exampleAnswer3: Answer = {requirement: exampleRequirement3, content: "this is my work plan: " + text3}
const exampleAnswer4: Answer = {requirement: exampleRequirement4, content: ""}
const answers: Answer[] = [exampleAnswer1, exampleAnswer2, exampleAnswer3, exampleAnswer4]

const exampleReview1: Review = {
    id: 0,
    isAccepted: true,
    comment: text1,
    reviewerId: 666
}

const exampleReview2: Review = {
    id: 1,
    isAccepted: false,
    comment: "I hate it",
    reviewerId: 12
}
const exampleReview3: Review = {
    id: 2,
    isAccepted: true,
    comment: "ok",
    reviewerId: 623
}
const exampleReview4: Review = {
    id: 3,
    isAccepted: true,
    comment: text6,
    reviewerId: 35
}


const exampleFinal: Review = {
    id: 3,
    isAccepted: false,
    comment: "not good",
    reviewerId: 664
}

export const EvaledAppPage = () => {
    return (
        <>
            <Header/>
            <PageTitle title={"Application 1"} extraText={"Result: (some result)"}/>
            <RequirementBox answers={answers}/>
            <div className="container">
                <div className="row">
                    <ReviewsList reviews={[exampleReview1, exampleReview2, exampleReview3, exampleReview4]} final={exampleFinal} extra={""}/>
                </div>
            </div>
            <Footer/>
        </>
    );
};


export default EvaledAppPage;