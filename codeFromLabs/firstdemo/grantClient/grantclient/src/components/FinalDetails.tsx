import React from 'react';
import {Review} from "../types";

export const FinalDetails = ({review}:{review:Review}) => {
    return (
        <div>
            <h4 className="d-inline-flex pr-4">Final decision - accepted? (true/false):</h4>
            <p>{String(review.isAccepted)}</p>
            <h4>Final Evaluation:</h4>
            <p>{review.comment}</p>
        </div>
    )
}