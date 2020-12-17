import React from 'react';
import {GrantCall} from "../types";

export const GrantCallDetails = ({grantCall}:{grantCall:GrantCall}) => {
    return (
        <>
            <h2>Grant Call name:</h2>
            <p>{grantCall.title}</p>
            <h2>Closing date:</h2>
            <p>{grantCall.closeDate}</p>
        </>
    )
}