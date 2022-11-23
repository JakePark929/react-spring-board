import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from "react-router-dom";
import {Button} from "react-bootstrap";

const DetailPage = () => {
    const id = useParams().id;
    const navigate = useNavigate();
    const [book, setBook] = useState({
        id: "",
        title: "",
        author: "",
    });

    useEffect(() => {
        fetch("http://localhost:8080/book/" + id)
            .then(res => res.json())
            .then(res => {
                // setBook({...book});
                setBook(res);
            });
    }, []);

    const deleteBook = () => {
        fetch("http://localhost:8080/book/" + id, {
            method: "DELETE",
        })
            .then(res => res.text())
            .then(res => {
                if (res === "ok") {
                    navigate("/")
                } else {
                    alert("삭제실패");
                }
            })
    }

    const updateBook = () => {
        navigate("/updateform/" + id);
    }

    return (
        <div>
            <h1>파일 상세보기</h1>
            <Button variant="warning" onClick={updateBook}>수정</Button>
            {' '}
            {/*<Button variant="danger" onClick={()=>deleteBook(book.id)}>삭제</Button>*/}
            <Button variant="danger" onClick={deleteBook}>삭제</Button>
            <hr/>
            <h3>{book.author}</h3>
            <h1>{book.title}</h1>
        </div>
    );
};

export default DetailPage;