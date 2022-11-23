import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from "react-router-dom";
import {Button, Form} from "react-bootstrap";

const UpdateForm = () => {
    const id = useParams().id;
    const navigate = useNavigate();
    const [book, setBook] = useState({
        title: "",
        author: ""
    });

    useEffect(() => {
        fetch("http://localhost:8080/book/" + id)
            .then(res => res.json())
            .then(res => {
                // setBook({...book});
                setBook(res);
            });
    }, []);

    const changeValue = (e) => {
        setBook({
            ...book,
            [e.target.name]: e.target.value
        })
    }

    const submitBook = (e) => {
        e.preventDefault(); // submit이 action을 안타고 자기 할일을 그만함.
        fetch("http://localhost:8080/book/"+id, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            },
            body: JSON.stringify(book)
        })
            .then(res => {
                console.log(1, res)
                if (res.status === 200) {
                    return res.json()
                } else {
                    return null;
                }
            })
            .then(res => { // Catch는 여기서 오류가 나야 실행됨
                console.log("정상", res);
                if (res !== null) {
                    navigate("/book/"+id);
                } else {
                    alert("파일 수정에 실패하였습니다.");
                }
            })
    }

    return (
        <div>
            <h1>파일 수정하기</h1>
            <Form onSubmit={submitBook}>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Title</Form.Label>
                    <Form.Control type="text"
                                  placeholder="Enter title"
                                  onChange={changeValue}
                                  name="title"
                                  value={book.title}
                    />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>author</Form.Label>
                    <Form.Control type="text"
                                  placeholder="Enter author"
                                  onChange={changeValue}
                                  name="author"
                                  value={book.author}
                    />
                </Form.Group>
                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
        </div>
    );
};

export default UpdateForm;