import React from "react";
import {Form} from "react-bootstrap";
import {ButtonBlue} from "../components/button-blue";
import {Header} from "../components/header";
import {PageTitle} from "../components/page-title";
import {Borders} from "../components/borders";
import {Footer} from "../components/footer";

export const Login = () => {
    const content = <div className="container">
        <Form>
            <Form.Group controlId="userEmail">
                <Form.Label>Email address</Form.Label>
                <Form.Control type="email" placeholder="Enter email"/>
            </Form.Group>

            <Form.Group controlId="userPassword">
                <Form.Label>Password</Form.Label>
                <Form.Control type="password" placeholder="Password"/>
            </Form.Group>
            <div className="row justify-content-center">
                <ButtonBlue link={"/"} text={"login"}/>
            </div>
        </Form>
    </div>
    return (
        <>
            <Header/>
            <PageTitle title={"Login"} extraText={""}/>
            <div className="container w-50">
                <Borders title={""} content={content}/>
            </div>
            <Footer/>

        </>
    );
};

export default Login;