import React, {useState} from 'react';
import styled from "styled-components";

const StyledItemBoxDiv = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  border: 1px solid black;
  padding: 10px;
  height: 100px;
  margin: 20px;
`;

// 글쓰기, 글삭제, 글목록보기
const ListPage = () => {
    const [no, setNo] = useState(6);
    const [post, setPost] = useState({
        id:"",
        title:"",
        content:""
    });
    const [posts, setPosts] = useState([
        {id: 1, title: "제목1", content: "내용1"},
        {id: 2, title: "제목2", content: "내용2"},
        {id: 3, title: "제목3", content: "내용3"},
        {id: 4, title: "제목4", content: "내용4"},
        {id: 5, title: "제목5", content: "내용5"}
    ]);
    const handleWrite=()=>{
        // ListPage의 setPosts에 무엇을 담아야 함?
        // e.preventDefault(); // form태그가 하려는 액션을 중지 시켜야 함.
        let wrPost={id:no, title:post.title, content: post.content};
        setNo(no+1);
        console.log(post.id);
        console.log(post.title);
        console.log(post.content);
        setPosts([...posts,wrPost]);
        setPost({
            id:"",
            title:"",
            content:""
        });
    };
    // const handleChangeTitle=(e)=>{
    //     console.log(e.target.value);
    //     setPost({title: e.target.value});
    // }
    // const handleChangeContent=(e)=>{
    //     console.log(e.target.value);
    //     setPost({content: e.target.value});
    // }
    const handleForm=(e)=>{
        console.log(e.target.name);
        // console.log(e.target.value);
        // computed property names 문법(키값 동적할당)
        // let a="name";
        setPost({
            ...post,
            [e.target.name]:e.target.value});
        console.log(post.id);
        console.log(post.title);
        console.log(post.content);
    };
    const handleDelete=(e)=>{
        console.log(e.target.name);
        const dp=posts.filter((n)=>{
            return n.id!=e.target.name;
        })
        setPosts(dp);
    }
    return (
        <div>
            <h3>리스트 페이지</h3>
            <hr/>
            <form>
                <input type="text"
                       placeholder="제목을 입력하세요"
                       value={post.title}
                       // onChange={handleChangeTitle}
                       onChange={handleForm}
                       name="title"
                />
                <input type="text"
                       placeholder="내용을 입력하세요"
                       value={post.content}
                       // onChange={handleChangeContent}
                       onChange={handleForm}
                       name="content"
                />
                <button type="button" onClick={handleWrite}>글쓰기</button>
            </form>
            <hr/>
            {posts.map((post) => <StyledItemBoxDiv>
                <div>
                    번호: {post.id} / 제목: {post.title} / 내용: {post.content}
                </div>
                <button type="button" onClick={handleDelete} name={post.id}>삭제</button>
            </StyledItemBoxDiv>)}
        </div>
    );
};

export default ListPage;