import React, {useEffect, useState} from 'react';
import Home from "../components/home/Home";

const HomePage = () => {
    // http 요청 (jquery ajax(못씀), fetch(순수), axios(다운))
    const [boards, setBoards] = useState([]); // 왠만하면 페이지에서 사용하는 것이 좋음, 상태데이터로만..
    const [number, setNumber] = useState(0);
    const [user, setUser] = useState({});

    useEffect(() => {
        // 다운로드 가정 = fetch(), axios(), ajax()
        let data = [
            {id:1,title:"제목1",content:"내용1"},
            {id:2,title:"제목2",content:"내용2"},
            {id:3,title:"제목3",content:"내용3"},
        ];

        // 빈데이터가 들어감
        setBoards([...data]);
        setUser({id:1,username:'ssar'});
    }, []); // 빈 배열은 한번만 실행

    return (
        <div>
            <Home boards={boards} setBoards={setBoards} number={number} setNumber={setNumber} user={user}/>
        </div>
    );
};

export default HomePage;