import React from 'react';
import {GrantCall} from "../types";
import {ListGroup} from "react-bootstrap";

export const GrantCallComponent = ({grantCall}:{grantCall:GrantCall}) => {
    return (
        <ListGroup.Item as="li" action>{grantCall.title}</ListGroup.Item>
    )
}