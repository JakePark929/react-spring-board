import React from 'react';

const WritePage = () => {
    const handleWrite=()=>{
        // ListPage의 setPosts에 무엇을 담아야 함?
        // let post = {id:6, title: "인풋값"};
    };
    return (
        <div>
            <h3>글쓰기 페이지</h3>
            <hr/>
            <form>
                <input type="text" placeholder="제목을 입력하세요"/>
                <button type="button" onClick={handleWrite}>글쓰기</button>
            </form>
        </div>
    );
};

export default WritePage;