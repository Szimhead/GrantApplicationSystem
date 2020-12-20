import React from 'react';
import {GrantCall} from "../types";
import {ListGroup} from "react-bootstrap";

export const GrantCallComponent = ({grantCall}:{grantCall:GrantCall}) => {
    return (
        <ListGroup.Item className="border-0" as="li" action>{grantCall.title}</ListGroup.Item>
    )
}