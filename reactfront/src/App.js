import logo from './logo.svg';
import './App.css';

// 0. React는 엔진 - 데이터 변경 감지에서 UI그려주는!
// 1. 실행과정 : index.js 에서 App을 렌더링 함 >> (index.html) 의 root 에 App을 넣음
// SPA(Single Page Application) <a>태그 못씀...
// 2. JSX 문법
// (1) return시에 하나의 Dom만 리턴할 수 있다.
// (2) 변수 선언은 let(변수) 혹은 const(상수)로만 해야함
// (3) if 사용 불가능 -> 3항연산자 사용가능(조건?값(true):값(false)), ==은 값 === 은 값과 타입
// (4) 조건부 렌더링(조건&&값(true))
// (5) css디자인
// - 내부에 적는 방법
// - 외부 파일에 적는 방법
// - 라이브러리 사용(부트스트랩, component-styled, mui)
// next.js 프레임워크 hooks
// 3. 웹핵, 바벨 : (JS ES5) -> ES6 var App=()=> {}; 6에서 5로 바꾸는 라이브러리

let a = 10; // 변수
const b = 20; // 상수

function App() {
    let c;
    let d=undefined; // 값이 정의 안됨
    console.log(1,c);
    const mystyle = {
        color: 'red',
    };

    let list = [1,2,3];

    let number = 1;
    const add = () => {
        number++;
        console.log('add',add);
    }

    return (
        <div>
            <div style={mystyle}>안녕 {a === 10 ? '10입니다.' : '10이 아닙니다.'}</div>
            <h1 className="box-style">헤딩태그 {b === 10 && '20입니다.'}</h1>
            <hr/>
            <div>{list[0]}</div>
            {/*foreach로 못뿌림*/}
            <div>{list.forEach(n=><h1>{n+10}</h1>)}</div>
            {/*map으로 뿌려야됨*/}
            <div>{list.map(n=><h1>{n+10}</h1>)}</div>
            <div>{list.map(n=>n)}</div>
        </div>
    );
}

export default App;
