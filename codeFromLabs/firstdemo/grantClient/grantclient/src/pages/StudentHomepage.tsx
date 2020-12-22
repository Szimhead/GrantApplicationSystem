import React, {useEffect, useState} from 'react';

import {Header} from '../components/header';
import {Footer} from '../components/footer';
import {PageTitle} from "../components/page-title";
import {Borders} from "../components/borders";
import {GrantCallListWithDetails} from "../components/grantCallListWithDetails";
import {GrantCallControllerApi, GrantCallDTO} from "../clientAPI";

export const StudentHomepage = () => {
    const [grantCalls, setGrantCalls] = useState([] as GrantCallDTO[])
    const grantCallAPI = new GrantCallControllerApi();

    useEffect(() => {
        if (grantCalls.length == 0) {
            grantCallAPI.getAllOpenUsingGET().then((value) => setGrantCalls(value as GrantCallDTO[]))
        }
    })

    const content =
        <GrantCallListWithDetails grantCalls={grantCalls}/>

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