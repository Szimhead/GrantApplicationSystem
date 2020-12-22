import React from 'react';

import { Header } from '../components/header';
import { Footer } from '../components/footer';
import {PageTitle} from "../components/page-title";
import {Borders} from "../components/borders";
import {HidingList} from "../components/hidingList";
import {GrantCallComponent} from "../components/grantCallComponent";
import {GrantCallDTO} from "../clientAPI";

const call:GrantCallDTO={id:0,title:"send nudes",description:"",funding:400,openDate:new Date(),closeDate:new Date(),sponsorId:2}

export const FundedApplications = () => {
    const content = <div className="container">
                        <HidingList  headers={[]}
                                     records={[["Student 1 Name"],["Student 2 Name"],
                                         ["Student 3 Name"],["Student 4 Name"]]}
                                     show={true}/>
                    </div>
    return (
        <>
            <Header />
            <PageTitle title={"Funded Applications"} extraText={""}/>
            <div className="container">
                <GrantCallComponent grantCall={call}/>
                <Borders title={"Funded Applications"} content={content} />
            </div>


            <Footer />
        </>
    );
};

export default FundedApplications;