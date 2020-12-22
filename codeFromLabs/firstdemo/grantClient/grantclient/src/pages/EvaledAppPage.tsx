import React, {useEffect, useState} from 'react';

import {Header} from '../components/header';
import {Footer} from '../components/footer';
import {PageTitle} from "../components/page-title";
import {Answer, Requirement, Review} from "../types";
import {RequirementBox} from "../components/requirement";
import {text1, text2, text3, text6} from "../components/randomTestTexts";
import ReviewsList from "../components/reviewsList";
import {useParams} from "react-router-dom";
import {ApplicationControllerApi, ReviewDTO} from "../clientAPI";

const exampleRequirement1: Requirement = {name: "Introduction", contentType: "text"}
const exampleRequirement2: Requirement = {name: "Related Work", contentType: "text"}
const exampleRequirement3: Requirement = {name: "Work Plan", contentType: "text"}
const exampleRequirement4: Requirement = {name: "Publications", contentType: "file"}

const exampleAnswer1: Answer = {requirement: exampleRequirement1, content: "this is my introduction: " + text1}
const exampleAnswer2: Answer = {requirement: exampleRequirement2, content: "this is my related work: " + text2}
const exampleAnswer3: Answer = {requirement: exampleRequirement3, content: "this is my work plan: " + text3}
const exampleAnswer4: Answer = {requirement: exampleRequirement4, content: ""}
const answers: Answer[] = [exampleAnswer1, exampleAnswer2, exampleAnswer3, exampleAnswer4]


const exampleFinal: Review = {
    id: 3,
    isAccepted: false,
    comment: "not good",
    reviewerId: 664
}

export const EvaledAppPage = () => {
    let {id} = useParams<Record<string, string | undefined>>();
    const appId = Number(id);
    const [reviews, setReviews] = useState([] as ReviewDTO[])
    const applicationAPI = new ApplicationControllerApi()

    useEffect(() => {
        if (reviews.length == 0) {
            applicationAPI.getAllReviewsFromApplicationUsingGET(appId).then((value) => {
                setReviews(value as ReviewDTO[])
            })
        }
    })

    return (
        <>
            <Header/>
            <PageTitle title={"Application "+appId} extraText={"Result: "+exampleFinal.isAccepted}/>
            <RequirementBox answers={answers}/>
            <div className="container">
                <div className="row">
                    <ReviewsList reviews={reviews} final={exampleFinal} extra={""}/>
                </div>
            </div>
            <Footer/>
        </>
    );
};


export default EvaledAppPage;