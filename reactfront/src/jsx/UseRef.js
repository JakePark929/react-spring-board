import React, {createRef, useRef, useState} from 'react';

// useRef (디자인)
// dom을 변경할 때 사용

const a= { // 디자인을 만들때 함수안에 만드는 것은 좋지 않다. 정적이기 때문
    backgroundColor :'red', 
}

const UseRef = () => {
    const myRef = useRef(null);

    const [list,setList]=useState([
        {id:1,name:'길동'},
        {id:2,name:'꺽정'}
    ]);

    const myRefs = Array.from({length:list.length}).map(()=>createRef());

    return (
        <div>
            <button onClick={() => {
                console.log(myRef);
                console.log(myRef.current);
                // myRef.current.style.backgroundColor = 'red';
                myRefs[1].current.style.backgroundColor = 'red';
            }}>
                색변경
            </button>
            <div ref={myRef}>박스</div>
            {list.map((user,index)=>(
                <p ref={myRefs[index]}>{user.name}</p>
            ))}
            <div style={a}>확인</div>
            <div className="box-style">헬로</div>
        </div>
    );
};

export default UseRef;