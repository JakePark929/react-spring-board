import React from 'react';
import {Card} from "react-bootstrap";
import {Link} from "react-router-dom";

const BookItem = (props) => {
    const {id, title, author} = props.book;
    return (
        <Card>
            <Card.Body>
                <Card.Title>{title}</Card.Title>
                <Card.Text>{author}</Card.Text>
                <Link to={"/book/"+id} className="btn btn-primary" variant="primary">상세보기</Link>
            </Card.Body>
        </Card>
    );
};

export default BookItem;