import React, {useState} from 'react';
import {Button, Form} from "react-bootstrap";
import {Link, useNavigate} from "react-router-dom";

const LoginForm = () => {
    const navigate = useNavigate();
    const url = "http://localhost:8080/oauth2/authorization/google"
    // const url = "https://www.naver.com"
    const [user, setUser] = useState({
        userId: "",
        password: "",
    });

    const changeValue = (e) => {
        setUser({
            ...user,
            [e.target.name]: e.target.value
        })
    }

    return (
        <div>
            <h1>로그인 창</h1>
            <Form action="/login" method="POST">
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>회원 ID</Form.Label>
                    <Form.Control type="text" placeholder="Enter ID" onChange={changeValue} name="userId"/>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>비밀번호</Form.Label>
                    <Form.Control type="password" placeholder="Enter Password" onChange={changeValue} name="password"/>
                </Form.Group>
                <Button variant="primary" type="submit">
                    로그인
                </Button>
            </Form>
            <br/>
            <Button variant="primary"
                    // onClick={() => navigate("/oauth2/authorization/google")}
                    onClick={()=>{window.location.replace(url)}}
            >
                구글 로그인
            </Button>
            <Link to={"/oauth2/authorization/google"}
                  className="nav-link">
                구글 로그인
            </Link>
        </div>
    );
};

export default LoginForm;