import React from 'react';

import { Header } from '../components/header';
import { Footer } from '../components/footer';
import {PageTitle} from "../components/page-title";
import {Borders} from "../components/borders";
import {GrantCallList} from "../components/grantCallList";
import {GrantCall} from "../types";
import {GrantCallDetails} from "../components/grantCallDetails";
import {GrantCallListWithDetails} from "../components/grantCallListWithDetails";

export const StudentHomepage = () => {
    const grantCall0: GrantCall = {
        id: 0,
        title: "title0",
        description: "description",
        funding: 0,
        openDate: new Date(),
        closeDate: new Date(),
        sponsorId: 0
    }
    const grantCall1: GrantCall = {
        id: 1,
        title: "title1",
        description: "description",
        funding: 11,
        openDate: new Date(),
        closeDate: new Date(),
        sponsorId: 111
    }
    const grantCall2: GrantCall = {
        id: 2,
        title: "title3",
        description: "description",
        funding: 22,
        openDate: new Date(),
        closeDate: new Date(),
        sponsorId: 222
    }
    const grantCalls:GrantCall[] = [grantCall0, grantCall1, grantCall2]
    const content = <div className="container">
                        <Borders title={"Open Grant Calls"} content={<GrantCallListWithDetails grantCalls={grantCalls}/>}/>
                    </div>;
    return (
        <>
            <Header />
            <PageTitle title={"Homepage"} extraText={""}/>
            <Borders title={"Open Grant Calls"} content={content}/>
            <Footer />
        </>
    );
};

export default StudentHomepage;