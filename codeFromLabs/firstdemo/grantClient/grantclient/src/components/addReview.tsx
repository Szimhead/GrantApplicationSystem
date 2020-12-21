import React from "react";

import {Header} from "./header";
import {PageTitle} from "./page-title";
import {Borders} from "./borders";
import {Footer} from "./footer";
import {ReviewForm} from "./form";

type AddReviewI = {
    title: string,
    label: string
}

export const AddReview = ({title,label}:AddReviewI) => {
    return (
        <>
            <Header/>
            <PageTitle title={title} extraText={""}/>
            <div className="container">
                <Borders title={"Form"} content={<ReviewForm label={label}/>}/>
            </div>
            <Footer/>
        </>
    );
};

export default AddReview;
