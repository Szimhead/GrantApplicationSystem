import React from "react";
import {Form, Row} from "react-bootstrap";
import {ButtonBlue} from "../components/button-blue";
import {Header} from "../components/header";
import {PageTitle} from "../components/page-title";
import {Borders} from "../components/borders";
import {Footer} from "../components/footer";


export const Signup= ()=>{

    const content=<div className="container">
        <Form>
            <Form.Group controlId="userName">
                <Form.Label>Name</Form.Label>
                <Form.Control type="name" placeholder="Name" />
            </Form.Group>

            <Form.Group controlId="userEmail">
                <Form.Label>Email address</Form.Label>
                <Form.Control type="email" placeholder="Enter email" />
            </Form.Group>

            <Form.Group controlId="userPassword">
                <Form.Label>Password</Form.Label>
                <Form.Control type="password" placeholder="Password" />
            </Form.Group>

            <Form.Group controlId="userControlPassword">
                <Form.Label>Confirm Password</Form.Label>
                <Form.Control type="password" placeholder="Confirm Password" />
            </Form.Group>

            <Form.Group controlId="userAddress">
                <Form.Label>Name</Form.Label>
                <Form.Control type="address" placeholder="Address" />
            </Form.Group>

            <Form.Group as={Row}>
                <div className="col-lg-2 w-100">
                    <Form.Label>Choose position:</Form.Label>
                </div>
                <div className="col-lg-1 w-100">
                    <Form.Check type="radio" name="role" id="student" label="Student"/>
                </div>
                <div className="col w-auto">
                    <Form.Check type="radio"name="role" id="reviewer" label="Reviewer"/>
                </div>
            </Form.Group>
            <Form.Group controlId="institution">
                <Form.Label>Institution</Form.Label>
                <Form.Control as="select">
                    <option>Institution 1</option>
                    <option>Institution 2</option>
                    <option>Institution 3</option>
                    <option>Institution 4</option>
                </Form.Control>
            </Form.Group>


            <ButtonBlue link={"/"} text={"Sign up!"}/>
        </Form>
    </div>
       return(
           <>
               <Header />
               <PageTitle title={"Sign Up!"} extraText={""}/>
               <div className="container">
                   <Borders title={""} content={content} />
               </div>
               <Footer />

           </>
       );
};

export default Signup;