import React, {useEffect, useState} from 'react';

import {Header} from '../components/header';
import {Footer} from '../components/footer';
import {PageTitle} from "../components/page-title";
import {Review} from "../types";
import ReviewsList from "../components/reviewsList";
import {useParams} from "react-router-dom";
import {ApplicationControllerApi, PanelDTO, ReviewDTO} from "../clientAPI";

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
    let {id} = useParams<Record<string, string | undefined>>();
    const appId = Number(id);
    const [reviews, setReviews] = useState([] as ReviewDTO[])
    const applicationAPI = new ApplicationControllerApi()

    useEffect(() => {
        if (reviews.length == 0) {
            applicationAPI.getAllReviewsFromApplicationUsingGET(appId).then((value) => {
                setReviews(value as ReviewDTO[])
            })
        }
    })

    return (
        <>
            <Header/>
            <PageTitle title={"Application "+appId+" - Reviews"} extraText={""}/>
            <ReviewsList reviews={reviews} final={exampleFinal} extra={"yes"}/>
            <Footer/>
        </>
    );
};



export default ReviewsListPage;