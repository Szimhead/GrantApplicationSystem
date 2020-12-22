import React, {useEffect, useState} from 'react';

import { Header } from '../components/header';
import { Footer } from '../components/footer';
import {PageTitle} from "../components/page-title";
import {Borders} from "../components/borders";
import {SelectOneButton} from "../components/selectOneButton";
import {GrantCallControllerApi, GrantCallDTO} from "../clientAPI";

export const AnonymousHomepage = () => {
    const [allCalls, setAllCalls] = useState([] as string[][])
    const [openCalls, setOpenCalls] = useState([] as string[][])
    const grantCallAPI = new GrantCallControllerApi();

    useEffect(() => {
        if (allCalls.length == 0) {
            grantCallAPI.getAllOpenUsingGET().then((value) => {
                let calls = value as GrantCallDTO[]
                let res = [] as string[][]
                for(let gc of calls) {
                    res.push([gc.title, "" + gc.sponsorId])
                }
                console.log(res)
                setAllCalls(res);
            })

        }
        if (openCalls.length == 0) {
            grantCallAPI.getAllUsingGET2().then((value) => {
                let calls = value as GrantCallDTO[]
                let res = [] as string[][]
                for(let gc of calls) {
                    res.push([gc.title, "status", gc.openDate.toString(), gc.closeDate.toString()])
                }
                console.log(res)
                setOpenCalls(res);
            })
        }
    })

    const content = <div className="container">
        <SelectOneButton  recordsFirst={openCalls} recordsSecond={allCalls}/>
    </div>;

    return (
        <>
            <Header />
            <PageTitle title={"Homepage"} extraText={""}/>
            <div className="container">
                <Borders title={"GrantCalls"} content={content} />
            </div>

            <Footer />
        </>
    );
};

export default AnonymousHomepage;