import React, {useEffect, useState} from 'react';
import BookItem from "../../components/book/BookItem";

const MainPage = () => {
    const [books, setBooks] = useState([]);

    // 함수 실행 시 최초 한번 실행되는 것
    useEffect(() => {
        // 첫번째 then 에 promise 받음(ticket)
        // 두번째 then 에 데이터 넘겨줌
        fetch("http://localhost:8080/book").then(res=>res.json()).then(res=>{
            // console.log(1,res)
            setBooks(res);
        }); // 비동기 함수
    }, []);

    return (
        <div>
            <h1>파일 리스트 보기</h1>
            {books.map((book) =>(
                <BookItem key={book.id} book={book}/>
            ))}
        </div>
    );
};

export default MainPage;