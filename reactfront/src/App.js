import './App.css';
import Header from "./components/Header";
import Footer from "./components/Footer";
import React, {useState} from 'react';
import Bottom from "./components/redux/Bottom";
import Top from "./components/redux/Top";

function App() {
    // App이 상태를 들고 있으면 됨?
    // const [number, setNumber] = useState(1);
    //
    // const addNumber = () => {
    //     setNumber(number+1);
    // }

    return (
        <div>
            <Header/>
            {/*<Navigation/>*/}
            {/*/!*Route 5.3 부터 사용법 변경*!/*/}
            {/*<Routes>*/}
                {/*<Route path="/" exact={true} element={<HomePage/>}/>*/}
                {/*<Route path="/login/:id" exact={true} element={<LoginPage/>}/>*/}
                {/*<Route path="/" exact={true} element={<ListPage/>}/>*/}
                {/*<Route path="/write" exact={true} element={<WritePage/>}/>*/}
            {/*</Routes>*/}
            {/*<ListPage/>*/}
            <div className='container'>
                <h1>최상단화면</h1>
                <Top/>
                <Bottom/>
            </div>
            <Footer/>
        </div>
    );
}

export default App;
