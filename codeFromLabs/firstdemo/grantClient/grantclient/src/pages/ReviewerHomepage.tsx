import React from 'react';

import {Header} from '../components/header';
import {Footer} from '../components/footer';
import {PageTitle} from "../components/page-title";
import {Borders} from "../components/borders";
import {PanelListWithDetails} from "../components/PanelListWithDetails";
import {GrantCallDTO} from "../clientAPI";
import {Panel} from "../types";

const grantCall0: GrantCallDTO = {
    id: 5,
    title: "title0",
    description: "description",
    funding: 0,
    openDate: new Date(),
    closeDate: new Date(),
    sponsorId: 0
}
const grantCall1: GrantCallDTO = {
    id: 7,
    title: "title1",
    description: "description",
    funding: 11,
    openDate: new Date(),
    closeDate: new Date(),
    sponsorId: 111
}
const grantCall2: GrantCallDTO = {
    id: 9,
    title: "title3",
    description: "description",
    funding: 22,
    openDate: new Date(),
    closeDate: new Date(),
    sponsorId: 222
}

const panel0:Panel = {id:5}
const panel1:Panel = {id:7}
const panel2:Panel = {id:9}
const grantCalls: GrantCallDTO[] = [grantCall0, grantCall1, grantCall2]
const panels: Panel[] = [panel0, panel1, panel2]
const content = <PanelListWithDetails panels={panels} grantCalls={grantCalls}/>


export const ReviewerHomepage = () => {

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