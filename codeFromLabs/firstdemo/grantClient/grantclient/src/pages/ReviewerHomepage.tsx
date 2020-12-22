import React, {useEffect, useState} from 'react';

import {Header} from '../components/header';
import {Footer} from '../components/footer';
import {PageTitle} from "../components/page-title";
import {Borders} from "../components/borders";
import {PanelListWithDetails} from "../components/PanelListWithDetails";
import {GrantCallControllerApi, GrantCallDTO, PanelDTO, ReviewerControllerApi} from "../clientAPI";
import {useParams} from "react-router-dom";


export const ReviewerHomepage = () => {
    const [grantCalls, setGrantCalls] = useState([] as GrantCallDTO[])
    const [panels, setPanels] = useState([] as PanelDTO[])
    const grantCallAPI = new GrantCallControllerApi();
    const reviewerAPI = new ReviewerControllerApi();
    let {revId} = useParams<Record<string, string | undefined>>();
    const id = Number(revId);
    //console.log(id)

    useEffect(() => {
        //console.log(panels.length)
        if (panels.length == 0) {
            reviewerAPI.getPanelsUsingGET(id).then((value) => {
                setPanels(value as PanelDTO[])
            })
        }
        if (grantCalls.length == 0) {
            panels.map((panel: PanelDTO, index) =>
                grantCallAPI.getOneUsingGET2(panel.grantId).then((value) => setGrantCalls(grantCalls => grantCalls.concat(value))));
        }
    })

    const content = <PanelListWithDetails panels={panels} grantCalls={grantCalls}/>

    return (
        <>
            <Header/>
            <PageTitle title={"Homepage"} extraText={""}/>
            <div className="container">
                <Borders title={"Your panels"} content={content}/>
            </div>
            <Footer/>
        </>
    );
};

export default ReviewerHomepage;