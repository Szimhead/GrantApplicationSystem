import React from 'react';

import { Header } from '../components/header';
import { Footer } from '../components/footer';
import {PageTitle} from "../components/page-title";

export const StudentHomepage = () => {
    return (
        <>
            <Header />
            <PageTitle title={"Homepage"} extraText={""}/>
            <Footer />
        </>
    );
};

export default StudentHomepage;