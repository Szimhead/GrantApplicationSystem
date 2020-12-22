import React, {useState} from 'react';
import {Borders} from "./borders";
import {GrantCallDetails} from "./grantCallDetails";
import {Panel} from "../types";
import {ListGroup} from "react-bootstrap";
import {ButtonBlue} from "./button-blue";
import {GrantCallDTO} from "../clientAPI";

type PanelsI = {
    panels: Panel[]
    grantCalls: GrantCallDTO[]
}

export const PanelListWithDetails = ({panels, grantCalls}:PanelsI) => {
    const [currIndex, setIndex] = useState(0)
    const [grantCall, setGrantCall] = useState(panels[0])

    let handleClick = (e:React.MouseEvent<HTMLButtonElement>) => {
        let index: string | null = e.currentTarget.getAttribute("data-rb-event-key")
        if(index == null)
            setIndex(0)
        else
            setIndex(Number(index))
    }

    return (
        <>
            <div className="d-flex w-100 mb-1">
                <div className="w-50 align-self-center">
                    <ListGroup as="ul" defaultActiveKey={"" + panels[0].id}>
                        {panels.map(
                            (panel: Panel) => <ListGroup.Item as="li" eventKey={"" + panel.id}
                                                                             onClick={handleClick}
                                                                             action>Panel {panel.id}</ListGroup.Item>
                        )}
                    </ListGroup>
                </div>
                <div className="w-50">
                    <Borders title={"Details"} content={<GrantCallDetails grantCall={grantCalls[currIndex]}/>}/>
                </div>
            </div>
            <div className="row m-auto justify-content-center">
                <ButtonBlue text={"Go to Panel"} link={"/"}/>
            </div>
        </>
    )
}