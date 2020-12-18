import React from 'react';

import { Header } from '../components/header';
import { Footer } from '../components/footer';
import {PageTitle} from "../components/page-title";

export const ReviewerApplication = () => {
    return (
        <>
            <Header />
            <PageTitle title={"Application"} extraText={"this is extra text"}/>
            <Footer />
        </>
    );
};

export default ReviewerApplication;