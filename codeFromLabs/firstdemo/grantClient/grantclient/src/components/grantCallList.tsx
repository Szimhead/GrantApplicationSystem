import React from 'react';
import {GrantCall, GrantCalls} from "../types";
import {GrantCallComponent} from "./grantCallComponent";
import {ListGroup} from "react-bootstrap";

export const GrantCallList = ({grantCalls}:GrantCalls) =>
    <ListGroup as="ul">
        {grantCalls.map(
            (grantCall:GrantCall) => <GrantCallComponent grantCall={grantCall} />
        )}
    </ListGroup>
