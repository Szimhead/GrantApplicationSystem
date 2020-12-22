import React from 'react';
import {GrantCalls} from "../types";
import {GrantCallComponent} from "./grantCallComponent";
import {ListGroup} from "react-bootstrap";
import {GrantCallDTO} from "../clientAPI";

export const GrantCallList = ({grantCalls}:GrantCalls) =>
    <ListGroup as="ul">
        {grantCalls.map(
            (grantCall:GrantCallDTO) => <GrantCallComponent grantCall={grantCall} />
        )}
    </ListGroup>
