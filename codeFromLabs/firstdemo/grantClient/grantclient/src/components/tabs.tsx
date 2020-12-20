import React from 'react';
import {Tabs} from "react-bootstrap";
import Tab from 'react-bootstrap/Tab'
import {Answer} from "../types";

export const ApplicationTabs = ({answers}:{answers:Answer[]}) => {
    return (
        <div className="col-8 px-1">
            <Tabs defaultActiveKey={answers[0].title} id="controlled-tab-example">
                {answers.map((answer: Answer) => <Tab eventKey={answer.title} title={answer.title}>
                    <p className="p-3">{answer.content}</p>
                </Tab>)}
            </Tabs>
        </div>
    );
};