import React from 'react';

import { Header } from '../components/header';
import { Footer } from '../components/footer';
import {PageTitle} from "../components/page-title";
import {Borders} from "../components/borders";
import {SelectOneButton} from "../components/selectOneButton";
import {HidingList} from "../components/hidingList";

export const AnonymousHomepage = () => {
    const content = <div className="container">
        <SelectOneButton  title={["Show open Grant Calls","Show all Grant Calls"]}
                          headersFirst={["Grant Call name","Number of submitted applications"]}
                          recordsFirst={[["Grant call 1","50"],["Grant call 2","12"],
                              ["Grant call 3","3"],["Grant call 4","43"]]}
                          headersSecond={["Grant Call name","Status","Opening date","Closing date"]}
                          recordsSecond={[["Grant call 1","open","12.10.2020","30.12.2020"],
                              ["Grant call 2","closed","21.08.2020","30.10.2020"],
                              ["Grant call 3","open","01.12.2020","30.01.2021"],
                              ["Grant call 4","upcoming","15.01.2021","20.01.2021"]]}/>
    </div>;

    return (
        <>
            <Header />
            <PageTitle title={"Homepage"} extraText={""}/>
            <Borders title={"GrantCalls"} content={content}/>
            <Footer />
        </>
    );
};

export default AnonymousHomepage;