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
    const applicationAPI = new ApplicationControllerApi()
    const grantCallAPI = new GrantCallControllerApi();
    const [apps, setApps] = useState([] as ApplicationDTO[])
    const [grantCall, setGrantCall] = useState(null as any)

    useEffect(() => {
        if (apps.length == 0) {
            applicationAPI.getAllUsingGET().then((value) => setApps(value as ApplicationDTO[]))
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
                <div className="row justify-content-center">
                    <div className="w-50">
                        <Borders title={"Application List"} content={<ApplicationList applications={apps}/>}/>
                    </div>
                    <div className="col-5 mt-5">
                        <div className="row">
                            <TextAndButton text={"See application and student details"} buttonText={"Go"}
                                           buttonLink={"#"}/>
                        </div>
                        <div className="row">
                            <TextAndButton text={"See reviews"} buttonText={"Go"}
                                           buttonLink={"#"}/>
                        </div>
                        <div className="row mx-2 p-4 justify-content-center">
                            <h5 className="text-center">To post a review and
                                classification go to reviews.</h5>
                        </div>
                    </div>
                </div>
            </div>
            <Footer/>
        </>
    );
};

export default PanelPage;