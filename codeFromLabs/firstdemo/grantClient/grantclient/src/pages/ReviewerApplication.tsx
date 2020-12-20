import React from 'react';

import {Header} from '../components/header';
import {Footer} from '../components/footer';
import {PageTitle} from "../components/page-title";
import {Borders} from "../components/borders";

export const ReviewerApplication = () => {
    return (
        <>
            <Header/>
            <PageTitle title={"Application"} extraText={"Submission date: (some date)"}/>
            <div className="container">
                <div className="row mt-4 justify-content-center">
                   {/* <Borders title={"title"}/>
                    <Borders title={"title2"}/>*/}
                </div>
            </div>
            <Footer/>
        </>
    );
};

export default ReviewerApplication;