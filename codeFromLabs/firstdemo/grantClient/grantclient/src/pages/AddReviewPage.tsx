import React from 'react';
import AddReview from "../components/addReview";
import {useParams} from "react-router-dom";

export const AddReviewPage = () => {
    let {id} = useParams<Record<string, string | undefined>>();
    const appId:number = Number(id);
    return (
        <>
            <AddReview title={"Application 1 - add review and classification"} label={"Accepted?"} appId={appId}/>
        </>
    );
};

export default AddReviewPage;