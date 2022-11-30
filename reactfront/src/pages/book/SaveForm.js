import React, {useState} from 'react';
import {Button, Form} from "react-bootstrap";
import {useNavigate} from "react-router-dom";
import {useSelector} from "react-redux";

const SaveForm = () => {
    const {url} = useSelector((store) => store);
    const navigate = useNavigate();
    const [book, setBook] = useState({
        title: "",
        author: ""
    });

    const changeValue = (e) => {
        setBook({
            ...book,
            [e.target.name]: e.target.value
        })
    }

    const submitBook = (e) => {
        e.preventDefault(); // submit이 action을 안타고 자기 할일을 그만함.
        fetch(url+"/book", {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            },
            body: JSON.stringify(book)
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
                    navigate("/");
                } else {
                    alert("파일 등록에 실패하였습니다.");
                }
            })
        // catch 의 동작 then에서 뭔가가 실패하면?
        // .catch(error=>{ 
        //     console.log("실패",error);
        // })
    }

    return (
        <div>
            <h1>파일 등록하기</h1>
            <Form onSubmit={submitBook}>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Title</Form.Label>
                    <Form.Control type="text" placeholder="Enter title" onChange={changeValue} name="title"/>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>author</Form.Label>
                    <Form.Control type="text" placeholder="Enter author" onChange={changeValue} name="author"/>
                </Form.Group>
                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
        </div>
    );
};

export default SaveForm;