import React from 'react';

import {Header} from '../components/header';
import {Footer} from '../components/footer';
import {PageTitle} from "../components/page-title";
import {Requirement} from "../types";
import {FillRequirementBox} from "../components/fillRequirements";

const exampleRequirement1: Requirement = {name: "Introduction", contentType: "text"}
const exampleRequirement2: Requirement = {name: "Related Work", contentType: "text"}
const exampleRequirement3: Requirement = {name: "Work Plan", contentType: "text"}
const exampleRequirement4: Requirement = {name: "Publications", contentType: "file"}
const requirements: Requirement[] = [exampleRequirement1, exampleRequirement2, exampleRequirement3, exampleRequirement4]

export const FillOutAppPage = () => {
    return (
        <>
            <Header/>
            <PageTitle title={"Application 1"} extraText={""}/>
            <FillRequirementBox requirements={requirements}/>
            <Footer/>
        </>
    );
};

export default FillOutAppPage;
