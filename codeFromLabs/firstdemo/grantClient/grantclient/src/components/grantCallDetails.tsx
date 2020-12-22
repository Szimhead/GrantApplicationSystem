import React from 'react';
import {GrantCallDTO} from "../clientAPI";

export const GrantCallDetails = ({grantCall}:{grantCall:GrantCallDTO}) => {
    return (
        <div className="d-flex flex-column">
            <h4>Grant Call name:</h4>
            <p>{grantCall.title}</p>
            <h4>Closing date:</h4>
            <p>{grantCall.closeDate.toLocaleDateString()}</p>
        </div>
    )
}