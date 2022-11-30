import React, {useState} from 'react';
import {Button, Form} from "react-bootstrap";
import {useSelector} from "react-redux";
import {useNavigate} from "react-router-dom";

const JoinForm = () => {
    const {url} = useSelector((store) => store);
    const navigate = useNavigate();
    const [user, setUser] = useState({
        userId: "",
        password: "",
        email: ""
    });

    const changeValue = (e) => {
        setUser({
            ...user,
            [e.target.name]: e.target.value
        })
    }

    const submitUser = (e) => {
        e.preventDefault(); // submit이 action을 안타고 자기 할일을 그만함.
        fetch(url + "/join-user", {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            },
            body: JSON.stringify(user)
        })
            .then(res => {
                console.log(1, res)
                if (res.status === 201) {
                    return res.json()
                } else {
                    return null;
                }
            })
            .then(res => { // Catch는 여기서 오류가 나야 실행됨
                console.log("정상", res);
                if (res !== null) {
                    navigate("/join");
                } else {
                    alert("회원가입에 실패하였습니다.");
                }
            })
    }

    return (
        <div>
            <h1>회원가입 페이지</h1>
            <Form onSubmit={submitUser}>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>회원 ID</Form.Label>
                    <Form.Control type="text" placeholder="Enter ID" onChange={changeValue} name="userId"/>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>비밀번호</Form.Label>
                    <Form.Control type="password" placeholder="Enter Password" onChange={changeValue} name="password"/>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>이메일</Form.Label>
                    <Form.Control type="text" placeholder="Enter Email" onChange={changeValue} name="email"/>
                </Form.Group>
                <Button variant="primary" type="submit">
                    회원가입
                </Button>
            </Form>
        </div>
    );
};

export default JoinForm;