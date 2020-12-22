import React from 'react';
import {ListGroup} from "react-bootstrap";
import {GrantCallDTO} from "../clientAPI";

export const GrantCallComponent = ({grantCall}:{grantCall:GrantCallDTO}) => {
    return (
        <ListGroup.Item className="border-0" as="li" action>{grantCall.title}</ListGroup.Item>
    )
}