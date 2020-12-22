import React from "react";

import {Header} from "../components/header";
import {PageTitle} from "../components/page-title";
import {Borders} from "../components/borders";
import {Footer} from "../components/footer";
import {Col, Row} from "react-bootstrap";
import {GrantCallDTO} from "../clientAPI";

const grantCall0: GrantCallDTO = {id: 0, title: "title0", description: "description", funding: 0, openDate: new Date(), closeDate: new Date(), sponsorId: 0}

const content= <div>
    <Row>
        <Col className="m-4">
            <h5>Grant Name:</h5>
            <p>{grantCall0.title}</p>
            <h5>Opening Date:</h5>
            <p>{grantCall0.openDate.toLocaleDateString()}</p>
        </Col>
        <Col className="m-4">
            <h5>Funding:</h5>
            <p>{grantCall0.funding}</p> {/*changed from number of submitted apps to funding*/}
            <h5>Closing Date:</h5>
            <p>{grantCall0.closeDate.toLocaleDateString()}</p>
        </Col>
    </Row>
    <h5>Description:</h5>
    <p>{grantCall0.description}</p>
</div>

export const GrantCallPage = () => {
    return(
        <>
            <Header />
            <PageTitle title={grantCall0.title} extraText={""}/>
            <div className="container">
                <Borders title={"Details"} content={content}/>
            </div>
            <Footer />
        </>
    );
};


export default GrantCallPage;