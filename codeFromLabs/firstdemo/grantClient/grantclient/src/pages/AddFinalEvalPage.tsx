import React from 'react';
import AddReview from "../components/addReview";
import {useParams} from "react-router-dom";

export const AddFinalEvalPage = () => {
    let {id} = useParams<Record<string, string | undefined>>();
    const appId:number = Number(id);
    return (
        <>
            <AddReview title={"Application 1 - add final evaluation"} label={"Accepted? (final)"} appId={appId}/>
        </>
    );
};

export default AddFinalEvalPage;