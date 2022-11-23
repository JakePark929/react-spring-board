import React from 'react';
import styled from "styled-components";
// 하나의 컴포넌트를 생성 (재사용)

// styled-components=> js파일과 css파일이 합쳐져서 관리!
const StyledFooterDiv = styled.div`
  border: 1px solid red;
  height: 200px;
`

const Footer = () => {
    return (
        <div>
            <br/>
            <StyledFooterDiv>
                Contact Us
                <ul>
                    <li>이메일 : dxeng@wise.co.kr</li>
                    <li>전화번호 : 01000000000</li>
                </ul>
            </StyledFooterDiv>
        </div>
    );
};

export default Footer;