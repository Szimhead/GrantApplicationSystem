import React from 'react';

import {Header} from '../components/header';
import {Footer} from '../components/footer';
import {PageTitle} from "../components/page-title";
import {Review} from "../types";
import ReviewsList from "../components/reviewsList";

const exampleReview1: Review = {
    id: 0,
    isAccepted: true,
    comment: "I think this was a very good application, well done :)",
    reviewerId: 666
}

const exampleReview2: Review = {
    id: 1,
    isAccepted: false,
    comment: "I hate it",
    reviewerId: 12
}
const exampleReview3: Review = {
    id: 2,
    isAccepted: true,
    comment: "ok",
    reviewerId: 623
}
const exampleReview4: Review = {
    id: 3,
    isAccepted: true,
    comment: "ran out of ideas",
    reviewerId: 35
}


const exampleFinal: Review | null = null
//     {
//     id: 3,
//     isAccepted: false,
//     comment: "not good",
//     reviewerId: 664
// }

export const ReviewsListPage = () => {
    return (
        <>
            <Header/>
            <PageTitle title={"Application 1 - Reviews"} extraText={""}/>
            <ReviewsList reviews={[exampleReview1, exampleReview2, exampleReview3, exampleReview4]} final={exampleFinal} extra={"yes"}/>
            <Footer/>
        </>
    );
};



export default ReviewsListPage;