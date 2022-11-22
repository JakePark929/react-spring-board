import "../../App.css";
import React from 'react';
import {useDispatch} from "react-redux";
import {decrease, increase} from "../../store";

const Bottom = () => {
    // const {addNumber} = props;
    const dispatcher = useDispatch();
    return (
        <div className='sub_container'>
            <h1>Bottom</h1>
            <button onClick={()=>dispatcher(increase("jake"))}>증가</button>
            <button onClick={()=>dispatcher(decrease("park"))}>감소</button>
        </div>
    );
};

export default Bottom;