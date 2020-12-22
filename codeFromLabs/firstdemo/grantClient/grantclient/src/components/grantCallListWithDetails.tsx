import React, {useState} from 'react';
import {Borders} from "./borders";
import {GrantCallDetails} from "./grantCallDetails";
import {GrantCalls} from "../types";
import {ListGroup} from "react-bootstrap";
import {ButtonBlue} from "./button-blue";
import {GrantCallDTO} from "../clientAPI";

export const GrantCallListWithDetails = ({grantCalls}:GrantCalls) => {
    const [currIndex, setIndex] = useState(0)
//    console.log(grantCalls[0])

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
                            (grantCall: GrantCallDTO, index) => <ListGroup.Item as="li" eventKey={"" + grantCall.id}
                                                                             onClick={handleClick}
                                                                             action>{grantCall.title}</ListGroup.Item>
                        )}
                    </ListGroup>
                </div>
                <div className="w-50">
                    <Borders title={"Details"} content={<GrantCallDetails grantCall={grantCalls[currIndex]}/>}/>
                </div>
            </div>
            <div className="row m-auto justify-content-center">
                <ButtonBlue text={"Go to Grant Call"} link={"/"}/>
            </div>
        </>
    )
}