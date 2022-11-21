import React from 'react';
import Login from "../components/login/Login";
import {useNavigate, useParams} from "react-router-dom";

const LoginPage = () => {
    console.log(useParams().id);
    // console.log(useLocation());
    const navigate=useNavigate();
    return (
        <div>
            <button onClick={()=>navigate(-1)}>뒤로가기</button>
            <button onClick={()=>navigate("/")}>홈</button>
            <Login/>
        </div>
    );
};

export default LoginPage;