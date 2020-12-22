import React, {useEffect, useState} from 'react';

import {Header} from '../components/header';
import {Footer} from '../components/footer';
import {PageTitle} from "../components/page-title";
import {Borders} from "../components/borders";
import {TextAndButton} from "../components/textAndButton";
import {Application} from "../types";
import ApplicationList from "../components/applicationList";
import {ApplicationControllerApi, ApplicationDTO, GrantCallControllerApi, GrantCallDTO} from "../clientAPI";
import {useParams} from "react-router-dom"

// const exampleApp1: Application = {id: 0}
// const exampleApp2: Application = {id: 1}
// const exampleApp3: Application = {id: 2}
// const exampleApp4: Application = {id: 3}
// const apps1: Application[] = [exampleApp1, exampleApp2, exampleApp3, exampleApp4]


export const PanelPage = () => {
    let {id} = useParams<Record<string, string | undefined>>();
    const grantCallId = Number(id);
    const grantCallAPI = new GrantCallControllerApi();
    const [apps, setApps] = useState([] as ApplicationDTO[])
    const [grantCall, setGrantCall] = useState(null as any)

    useEffect(() => {
        if (apps.length == 0) {
            grantCallAPI.getAllApplicationsFromGrantCallUsingGET(grantCallId).then((value) => setApps(value as ApplicationDTO[]))
        }
        if (grantCall == null) {
            grantCallAPI.getOneUsingGET2(grantCallId).then((value) => setGrantCall(value as GrantCallDTO))
        }
    })

    let handleNull = () => {
        if (grantCall != null)
            return grantCall.title
        else
            return "empty"
    }

    return (
        <>
            <Header/>
            <PageTitle title={"Panel Page"} extraText={""}/>
            <div className="container">
                <div className="row mt-4 justify-content-center">
                    <h4>Grant Call Name: {handleNull()}</h4>
                </div>
                <ApplicationList applications={apps}/>
            </div>
            <Footer/>
        </>
    );
};

export default PanelPage;