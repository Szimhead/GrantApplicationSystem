import React from 'react';
import {GrantCallDTO} from "../clientAPI";

export const GrantCallDetails = ({grantCall}:{grantCall:GrantCallDTO}) => {

    const handleNull = () => {
        if (grantCall != undefined)
            return <div className="d-flex flex-column">
                <h4>Grant Call name:</h4>
                <p>{grantCall.title}</p>
                <h4>Closing date:</h4>
                <p>{grantCall.closeDate}</p>
            </div>
        else
            return <></>
    }

    return (
        handleNull()
    )
}