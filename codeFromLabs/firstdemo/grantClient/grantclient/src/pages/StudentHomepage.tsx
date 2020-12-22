import React, {useEffect, useState} from 'react';

import {Header} from '../components/header';
import {Footer} from '../components/footer';
import {PageTitle} from "../components/page-title";
import {Borders} from "../components/borders";
import {GrantCallListWithDetails} from "../components/grantCallListWithDetails";
import {GrantCallControllerApi, GrantCallDTO} from "../clientAPI";

export const StudentHomepage = () => {
    const [grantCalls, setGrantCalls] = useState([] as GrantCallDTO[])
    const grantCall0: GrantCallDTO = {
        id: 0,
        title: "title0",
        description: "description",
        funding: 0,
        openDate: new Date(),
        closeDate: new Date(),
        sponsorId: 0
    }
    const grantCall1: GrantCallDTO = {
        id: 1,
        title: "title1",
        description: "description",
        funding: 11,
        openDate: new Date(),
        closeDate: new Date(),
        sponsorId: 111
    }
    const grantCall2: GrantCallDTO = {
        id: 2,
        title: "title3",
        description: "description",
        funding: 22,
        openDate: new Date(),
        closeDate: new Date(),
        sponsorId: 222
    }
    const grantCalls1: GrantCallDTO[] = [grantCall0, grantCall1, grantCall2]
    const grantCallAPI = new GrantCallControllerApi();

    useEffect(() => {
        if (grantCalls.length == 0) {
            grantCallAPI.getAllUsingGET2().then((value) => {
                setGrantCalls(value as GrantCallDTO[])
                console.log(value as GrantCallDTO[])
            })
        }
    })

    const content =
        <GrantCallListWithDetails grantCalls={grantCalls1}/>

    return (
        <>
            <Header/>
            <PageTitle title={"Homepage"} extraText={""}/>
            <div className="container">
                <Borders title={"Open Grant Calls"} content={content}/>
            </div>
            <Footer/>
        </>
    );
};

export default StudentHomepage;