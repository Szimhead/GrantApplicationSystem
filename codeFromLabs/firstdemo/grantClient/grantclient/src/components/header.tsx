import React from 'react';
import {Navbar, Nav, NavDropdown} from "react-bootstrap";

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
                <Navbar.Brand href="/">Grant Application System</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav"/>
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="mr-auto">
                        <Nav.Link href="/">Home</Nav.Link>
                    </Nav>
                    <Nav className="mr-auto">
                        <Nav.Link href="/pages/anonymousHomepage">Anonymous</Nav.Link>
                    </Nav>
                    <Nav className="mr-auto">
                        <Nav.Link href="/pages/fundedApplications">Funded</Nav.Link>
                    </Nav>
                    <Nav className="mr-auto">
                        <Nav.Link href="/pages/signup">Sign Up</Nav.Link>
                    </Nav>
                    <Nav className="mr-auto">
                        <Nav.Link href="/pages/studentProfile">Student Profile</Nav.Link>
                    </Nav>
                    <Nav className="mr-auto">
                        <Nav.Link href="/pages/grantCallPage">Grant Call Page</Nav.Link>
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