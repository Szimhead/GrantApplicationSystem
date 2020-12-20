import React, {useState} from 'react';
import {GrantCallList} from "./grantCallList";
import {Borders} from "./borders";
import {GrantCallDetails} from "./grantCallDetails";
import {GrantCall, GrantCalls} from "../types";
import {ListGroup, Tab} from "react-bootstrap";
import {GrantCallComponent} from "./grantCallComponent";
import {ButtonBlue} from "./button-blue";

export const GrantCallListWithDetails = ({grantCalls}:GrantCalls) => {
    const [currIndex, setIndex] = useState(0)
    const [grantCall, setGrantCall] = useState(grantCalls[0])

    let handleClick = (e:React.MouseEvent<HTMLButtonElement>) => {
        let index: string | null = e.currentTarget.getAttribute("data-rb-event-key")
        if(index == null)
            setIndex(0)
        else
            setIndex(Number(index))
    }

    return (
        <>
            <div className="d-flex p-2 w-100 mb-1">
                <div className="w-50 align-self-center">
                    <ListGroup as="ul" defaultActiveKey={"" + grantCalls[0].id}>
                        {grantCalls.map(
                            (grantCall: GrantCall, index) => <ListGroup.Item as="li" eventKey={"" + grantCall.id}
                                                                             onClick={handleClick}
                                                                             action>{grantCall.title}</ListGroup.Item>
                        )}
                    </ListGroup>
                </div>
                <div className="w-50">
                    <Borders title={"Details"} content={<GrantCallDetails grantCall={grantCalls[currIndex]}/>}/>
                </div>
            </div>
            <div className="m-auto">
                <ButtonBlue text={"Go to Grant Call"} link={"/"}/>
            </div>
        </>
    )
}