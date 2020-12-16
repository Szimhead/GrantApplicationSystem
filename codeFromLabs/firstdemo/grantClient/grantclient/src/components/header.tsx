// src/components/header.tsx

/* eslint-disable jsx-a11y/anchor-is-valid */
import React from 'react';
import Navbar from 'react-bootstrap/Navbar'
import {Button, Form, FormControl, Nav, NavDropdown} from "react-bootstrap";

export const Header = () => {
    return (
        <header>
            <Navbar bg="dark" variant="dark" className="p-3">
                <Nav.Item><img
                    style={{ width: 50 }}
                    className="mr-3"
                    src="/images/logo.png"
                    alt="Logo"
                /></Nav.Item>
                <Navbar.Brand href="#home">Grant Application System</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav"/>
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="mr-auto">
                        <Nav.Link href="#home">Home</Nav.Link>
                    </Nav>
                    <Nav className="mr-4">
                        <NavDropdown title="Profile" id="basic-nav-dropdown" className="mr-5">
                            <NavDropdown.Item href="#action/3.1">My profile</NavDropdown.Item>
                            <NavDropdown.Divider/>
                            <NavDropdown.Item href="#action/3.4">Logout</NavDropdown.Item>
                        </NavDropdown>
                    </Nav>
                </Navbar.Collapse>
            </Navbar>
        </header>
    );
};