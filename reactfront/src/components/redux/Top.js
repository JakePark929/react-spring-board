import "../../App.css";
import React from 'react';
import {useSelector} from "react-redux";

const Top = () => {
    // const {number} = props;
    const {number, username} = useSelector((store)=>store);
    return (
        <div className='sub_container'>
            <h1>Top</h1>
            번호: {number} <br/>
            이름: {username}
        </div>
    );
};

export default Top;