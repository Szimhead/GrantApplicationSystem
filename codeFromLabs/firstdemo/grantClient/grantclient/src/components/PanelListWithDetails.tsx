import React, {useState} from 'react';
import {Borders} from "./borders";
import {GrantCallDetails} from "./grantCallDetails";
import {Panel} from "../types";
import {ListGroup} from "react-bootstrap";
import {ButtonBlue} from "./button-blue";
import {GrantCallDTO, PanelDTO} from "../clientAPI";

type PanelsI = {
    panels: PanelDTO[]
    grantCalls: GrantCallDTO[]
}

export const PanelListWithDetails = ({panels, grantCalls}: PanelsI) => {
    const [currGrantIndex, setGrantIndex] = useState(0)
    const [currGrantId, setGrantId] = useState(0)
    let i = 0;

    let handleClick = (e: React.MouseEvent<HTMLButtonElement>) => {
        let indexP: string | null = e.currentTarget.getAttribute("data-rb-event-key")

        for (let g = 0; g < grantCalls.length; g++) {
            if (grantCalls[g].id == panels[Number(indexP)].grantId) {
                setGrantIndex(g)
                setGrantId(grantCalls[g].id)
            }
        }

    }

    const rightIndex = () => {
        if (currGrantId == 0)
            return i
        else
            return currGrantId
    }

    const handleNull = () => {
        if (grantCalls.length != 0 && panels.length != 0) {
            i = grantCalls[0].id;
            return <>
                <div className="d-flex w-100 mb-1">
                    <div className="w-50 align-self-center">
                        <ListGroup as="ul" defaultActiveKey={"0"}>
                            {panels.map(
                                (panel: Panel, index) => <ListGroup.Item as="li" eventKey={"" + index}
                                                                         onClick={handleClick}
                                                                         action>Panel {panel.id}</ListGroup.Item>
                            )}
                        </ListGroup>
                    </div>
                    <div className="w-50">
                        <Borders title={"Details"}
                                 content={<GrantCallDetails grantCall={grantCalls[currGrantIndex]}/>}/>
                    </div>
                </div>
                <div className="row m-auto justify-content-center">
                    <ButtonBlue text={"Go to Panel"} link={"/pages/panelPage/" + rightIndex()}/>
                </div>
            </>
        } else
            return <></>
    }

    return (
        handleNull()
    )
}