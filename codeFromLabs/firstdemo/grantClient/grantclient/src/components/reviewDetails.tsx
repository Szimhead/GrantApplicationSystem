import React from 'react';
import {Review} from "../types";

export const ReviewDetails = ({review}:{review:Review}) => {
    return (
        <div>
            <h4 className="d-inline-flex pr-4">Accepted? (true/false):</h4>
            <p>{String(review.isAccepted)}</p>
            <h4>Comment:</h4>
            <p>{review.comment}</p>
        </div>
    )
}