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

export const PanelListWithDetails = ({panels, grantCalls}: PanelsI) => {
    const [currPanelIndex, setPanelIndex] = useState(0)
    const [currGrantIndex, setGrantIndex] = useState(0)

    let handleClick = (e: React.MouseEvent<HTMLButtonElement>) => {
        let indexP: string | null = e.currentTarget.getAttribute("data-rb-event-key")

        if (indexP == null)
            setPanelIndex(0)
        else
            setPanelIndex(panels[Number(indexP)].id)
            setGrantIndex(Number(indexP))
    }

    const handleNull = () => {
        if (grantCalls.length != 0 && panels.length != 0) {
            return <>
                <div className="d-flex w-100 mb-1">
                    <div className="w-50 align-self-center">
                        <ListGroup as="ul" defaultActiveKey={"" + panels[0].id}>
                            {panels.map(
                                (panel: Panel, index) => <ListGroup.Item as="li" eventKey={"" + index}
                                                                  onClick={handleClick}
                                                                  action>Panel {panel.id}</ListGroup.Item>
                            )}
                        </ListGroup>
                    </div>
                    <div className="w-50">
                        <Borders title={"Details"} content={<GrantCallDetails grantCall={grantCalls[currGrantIndex]}/>}/>
                    </div>
                </div>
                <div className="row m-auto justify-content-center">
                    <ButtonBlue text={"Go to Panel"} link={"/pages/panelPage/"+currPanelIndex}/>
                </div>
            </>
        } else
            return <></>
    }

    return (
        handleNull()
    )
}