import React from 'react';
import styled from "styled-components";
import {Link} from "react-router-dom";
import Navbar from "react-bootstrap/Navbar";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import NavDropdown from "react-bootstrap/NavDropdown";
import Form from "react-bootstrap/Form";
import {Button} from "react-bootstrap";
// 하나의 컴포넌트를 생성 (재사용)

// styled-components=> js파일과 css파일이 합쳐져서 관리!
const StyledHeaderDiv = styled.div`
  border: 1px solid red;
  height: 100px;
  background-color: ${(props)=>props.backgroundColor};
`

const StyledHeadLink = styled(Link)`
  color: mediumvioletred;
`;

const Header = () => {
    return (
        <div>
            {/*<StyledHeaderDiv backgroundColor="white">*/}
            {/*    <h1>엔지니어링 디지털 변환 클라우드 플랫폼</h1>*/}
            {/*    <ul>*/}
            {/*        <StyledHeadLink to="/">홈</StyledHeadLink>*/}
            {/*        <StyledHeadLink to="/login/10">로그인</StyledHeadLink>*/}
            {/*        /!*<li><a href="/">홈</a></li>*!/*/}
            {/*        /!*<li><a href="/login">로그인</a></li>*!/*/}
            {/*    </ul>*/}
            {/*</StyledHeaderDiv>*/}
            <Navbar bg="light" expand="lg">
                <Container fluid>
                    <Navbar.Brand href="#">엔지니어링 과제</Navbar.Brand>
                    <Navbar.Toggle aria-controls="navbarScroll" />
                    <Navbar.Collapse id="navbarScroll">
                        <Nav
                            className="me-auto my-2 my-lg-0"
                            style={{ maxHeight: '100px' }}
                            navbarScroll
                        >
                            <Link to={"/"} className="nav-link">Home</Link>
                            <Link to={"/login/10"} className="nav-link">Login</Link>
                            <NavDropdown title="Link" id="navbarScrollingDropdown">
                                <NavDropdown.Item href="#action3">Action</NavDropdown.Item>
                                <NavDropdown.Item href="#action4">
                                    Another action
                                </NavDropdown.Item>
                                <NavDropdown.Divider />
                                <NavDropdown.Item href="#action5">
                                    Something else here
                                </NavDropdown.Item>
                            </NavDropdown>
                            <Nav.Link href="#" disabled>
                                Link
                            </Nav.Link>
                        </Nav>
                        <Form className="d-flex">
                            <Form.Control
                                type="search"
                                placeholder="Search"
                                className="me-2"
                                aria-label="Search"
                            />
                            <Button variant="outline-success">Search</Button>
                        </Form>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </div>
    );
};

export default Header;