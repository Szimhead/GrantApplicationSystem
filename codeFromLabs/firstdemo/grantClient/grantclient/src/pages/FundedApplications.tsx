import React from 'react';

import { Header } from '../components/header';
import { Footer } from '../components/footer';
import {PageTitle} from "../components/page-title";
import {Borders} from "../components/borders";
import {HidingList} from "../components/hidingList";
import {GrantCallComponent} from "../components/grantCallComponent";
import {GrantCall} from "../types";

const call:GrantCall={id:0,title:"grandzik",description:"",funding:400,openDate:new Date(),closeDate:new Date(),sponsorId:2}


export const FundedApplications = () => {
    return (
        <>
            <Header />
            <PageTitle title={"Funded Applications"} extraText={""}/>
            <GrantCallComponent grantCall={call}/>
            <Borders title={"Funded Applications"} />
            <div className="container">
                <HidingList  headers={[]}
                                  records={[["Student 1 Name"],["Student 2 Name"],
                                      ["Student 3 Name"],["Student 4 Name"]]}
                                  show={true}/>
            </div>
            <Footer />
        </>
    );
};

export default FundedApplications;