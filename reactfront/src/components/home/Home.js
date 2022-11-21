import React from 'react';
import styled from "styled-components";
import {Button} from "react-bootstrap";
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';

const StyledHomeDiv = styled.div`
  padding: 30px 0 30px 0px;
`;

const StyledDeleteButton = styled.button`
  color: ${(props) => props.user.username === 'ssar' ? 'blue' : 'red'};
`;

// 스타일 상속!!
const StyledAddButton = styled(StyledDeleteButton)`
  background-color: green;
`;

// Function 방식..
// class 방식으로 받으려면.. this.props.

// 부모로 부터 받아온 어떤 데이터를 가지고 스타일링을 동적으로 할 것 이라면?
const Home = (props) => {
    // const boards=props.boards;
    // console.log(boards);
    // console.log(props.id);

    // 구조분할 할당
    const {boards, setBoards, number, setNumber, user} = props;

    return (
        <StyledHomeDiv>
            <h1>홈페이지</h1>
            <h1>홈: {number}</h1>
            <button onClick={() => (setNumber(number + 1))}>증가</button>
            <br/>
            <Button variant="primary">Primary</Button>{' '}
            <StyledAddButton user={user}>더하기</StyledAddButton>
            <StyledDeleteButton user={user} onClick={() => setBoards([])}>전체삭제</StyledDeleteButton>
            {boards.map((board) => <h3 key={board.id}>제목: {board.title} 내용: {board.content}</h3>)}
        </StyledHomeDiv>
    );
};

export default Home;