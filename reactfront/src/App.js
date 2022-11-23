import './App.css';
import Footer from "./components/Footer";
import React from 'react';
import {Container} from "react-bootstrap";
import {Route, Routes} from "react-router-dom";
import BookHeader from "./components/BookHeader";
import MainPage from "./pages/book/MainPage";
import SaveForm from "./pages/book/SaveForm";
import LoginForm from "./pages/uesr/LoginForm";
import JoinForm from "./pages/uesr/JoinForm";
import DetailPage from "./pages/book/DetailPage";
import UpdateForm from "./pages/book/UpdateForm";

function App() {
    // App이 상태를 들고 있으면 됨?
    // const [number, setNumber] = useState(1);
    //
    // const addNumber = () => {
    //     setNumber(number+1);
    // }

    return (
        <div>
            {/*<Header/>*/}
            {/*<Navigation/>*/}
            {/*/!*Route 5.3 부터 사용법 변경*!/*/}
            {/*<Routes>*/}
            {/*<Route path="/" exact={true} element={<HomePage/>}/>*/}
            {/*<Route path="/login/:id" exact={true} element={<LoginPage/>}/>*/}
            {/*<Route path="/" exact={true} element={<ListPage/>}/>*/}
            {/*<Route path="/write" exact={true} element={<WritePage/>}/>*/}
            {/*</Routes>*/}

            {/*<ListPage/>*/}

            {/*<div className='container'>*/}
            {/*    <h1>최상단화면</h1>*/}
            {/*    <Top/>*/}
            {/*    <Bottom/>*/}
            {/*</div>*/}
            <BookHeader/>
            <Container>
                <Routes>
                    <Route path="/" exact={true} element={<MainPage/>}/>
                    <Route path="/saveform" exact={true} element={<SaveForm/>}/>
                    <Route path="/book/:id" exact={true} element={<DetailPage/>}/>
                    <Route path="/loginform" exact={true} element={<LoginForm/>}/>
                    <Route path="/joinform" exact={true} element={<JoinForm/>}/>
                    <Route path="/updateform/:id" exact={true} element={<UpdateForm/>}/>
                </Routes>
            </Container>
            <Footer/>
        </div>
    );
}

export default App;
