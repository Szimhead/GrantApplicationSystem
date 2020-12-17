import React from 'react';
import {GrantCall, GrantCalls} from "../types";
import {GrantCallComponent} from "./grantCallComponent";

export const grantCallList = ({grantCalls}:GrantCalls) =>
    <ul>
        {grantCalls.map(
            (grantCall:GrantCall) => <GrantCallComponent grantCall={grantCall} />
        )}
    </ul>
