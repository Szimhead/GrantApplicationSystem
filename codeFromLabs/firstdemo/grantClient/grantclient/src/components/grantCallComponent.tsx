import React from 'react';
import {GrantCall} from "../types";

export const GrantCallComponent = ({grantCall}:{grantCall:GrantCall}) => {
    return (
        <li>{grantCall.title}</li>
    )
}