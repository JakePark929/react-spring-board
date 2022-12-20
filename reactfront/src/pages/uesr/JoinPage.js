import React from 'react';
import {Link, useNavigate} from "react-router-dom";
import {Button} from "react-bootstrap";

const JoinPage = () => {
    const navigate = useNavigate();
    return (
        <div>
            <h1>가입완료</h1>
            <Button variant="primary"
                    type="submit"
                    onClick={() => navigate("/login-form")}>
            </Button>
        </div>
    );
};

export default JoinPage;