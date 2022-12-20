import React from 'react';

const Temp = () => {
    /**
     * [ computed property ]
     * 식 자체를 넣는것도 가능
     * Object.assign(): 객체복제(깊은 복사) - const newUser = Object.assign({}, user);
     * Object.keys(): 키 배열 반환
     * Object.values(): 값 배열 반환
     * Object.entries(): 키/값 배열 반환
     * Object.fromEntries(): 키값 배열을 객체로
     */
    let n = 'name';
    let b = 'age';
    const user = {
        [n]: "Mike",
        [b]: 30,
    }
    const makeObj = (key, val) => {
        return {
            [key]: val,
        }
    }

    const obj = makeObj("나이", 33);
    console.log(obj);
    console.log(user);

    const user3 = Object.assign({}, user);
    user3.name = 'Jake';
    console.log(user3);
    console.log(user);
    let result = Object.entries(user);
    console.log(result);

    let arr = [
        ["mon", "월"],
        ["tue", "화"]
    ]
    result = Object.fromEntries(arr);
    console.log(result);

    /**
     * 객체 property key 문자열 형..
     * Symbol - new 붙이지 않은 유일한 식별자!
     * -> 유일성 보장..
     * Symbol.for() : 전역 심볼
     * 하나의 심볼만 보장받을 수 있음
     * Symbol.keyFor()
     * Object.getOwnPropertySymbols(user) 심볼만 볼 수 있음
     * Reflect.ownKeys().. symbol 까지 모두 출력
     */
        // 내가 작업
        // user.showName = function() {};
    const showName = Symbol("show name");
    user[showName] = function () {
        console.log(this.name);
    }

    user[showName]();

    // 사용자가 접속하면 보는 메세지
    for (let key in user) {
        console.log(`His ${key} is ${user[key]}.`);
    }

    /**
     * toString() : 10진수 -> 2진수, 16진수
     * Math.PI // 3.141592
     * Math.ceil(num): 올림
     * Math.floor(num): 내림
     * Math.round(num): 반올림
     * Math.toFixed(): 0, 6 자릿수 반올림 - string 반환
     * isNaN - 자기자신도 false 를 반환하기때문에 무조건
     * parseInt - string 을 숫자로 반환 10진수를 2진수로도 바꿔줌
     * parseFloat
     * Math.random: 랜덤 숫자 생성
     * Math.max 최댓값 Math.min 최솟값
     * Math.abs: 절대값
     * Math.pow(n, m): n 의 m 승
     * Math.sqrt : 제곱근
     */

    /**
     * [ String ]
     * toUpperCase() / toLowerCase()
     * indexOf() - 없으면 -1 -1보다 큰가로 구분
     * str.slice(n, m) m 없으면 끝까지, 양수면 숫자까지, 불포함, 음수면 끝에서 몇번째까지
     * str.substring(n,m) n과 m 사이 음수를 허용하지 않음(0으로 인식)
     * str.substr(n,m) n부터 시작해서 m개를 가져옴
     * str.trim() 앞 뒤 공백 제거
     * str.repeat() 문자열 n번 반복
     *
     * [ 문자열 비교 ]
     * "a".codePointAt(0); // 97
     * Stromg.fromCodePoint(97); // a
     */

    let list = [
        "01. 들어가며",
        "02. JS의 역사",
        "03. 자료형",
        "04. 함수",
        "05. 배열"
    ]

    let newList = [];
    for (let i = 0; i < list.length; i++) {
        newList.push(
            list[i].slice(4)
        );
    }

    console.log(newList);

    // 금칙어 : 콜라
    // includes() 있으면 true 없으면 false
    const hasCola = (str) => {
        // if(str.indexOf('콜라') > -1){ // -1이 없으면 -1도 true 0에 있어도 false가 뜸
        if (str.includes('콜라')) {
            console.log("금칙어가 있습니다.");
        } else {
            console.log("통과");
        }
    }
    hasCola('와 사이다가 짱이야!');
    hasCola('와 콜라가 짱이야!');
    hasCola('콜라가 짱');

    /**
     * [ Array Method ]
     * arr.splice(n, m, x) : 특정 요소 지우고 추가 n 부터 m 개 지우고 x 추가
     * + 삭제된 요소가 반환
     * arr.slice(n, m) : n부터 m까지 반환 괄호에 아무것도 없으면 복사
     * arr.concat(arr2, arr3 ..) : 합쳐서 새 배열 반환
     * arr.forEach(item: 해당배열, index: 인덱스, arr: 배열 그자체) : 배열반복
     */

        // forEach
    let array = ["Mike", "Tom", "Jane"];
    array.forEach((name, index) => {
        console.log(`0${index + 1}, ${name}`);
    })

    /**
     * arr.indexOf() / LastindexOf()
     * arr.includes() : 인덱스 확인 없이 포함하는지
     * arr.find(fn) / arr.findIndex(fn) : 첫번째 true 만 반환, 없으면 undefined
     */

        // find / findIndex
    let array2 = [1, 2, 3, 4, 5, 6];

    const results = array2.find((item) => {
        return item % 2 === 0; // 짝수를 찾고 true일때 멈춤
    });
    console.log(results);

    let userList = [
        {name: "Mike", age: 30},
        {name: "Jane", age: 27},
        {name: "Tom", age: 10},
    ];

    // const results2 = userList.find((user) => {
    const results2 = userList.findIndex((user) => {
        if (user.age < 19) {
            return true;
        }
        return false;
    })
    console.log(results2); // 첫번째 true 값을 반환, 없으면 undefined

    const results3 = array2.filter((item => {
        return item % 2 === 0;
    }))

    console.log(results3);

    /**
     * arr.reverse() 배열 역순
     * arr.map() : 함수를 받아 특정 기능을 시행하고 새로운 배열을 반환
     */

        // arr.map(fn)
    let newUserList = userList.map((user, index) => {
            return Object.assign({}, user, {
                id: index + 1,
                isAdult: user.age > 19,
            })
        });
    console.log("map 예제 ", newUserList);
    console.log(userList);

    // join, split
    let array3 = ["안녕", "나는", "철수야"];
    let results4 = array3.join(); // 아무것도 없으면 쉼표 - Json, csv에 응용가능!
    console.log(results4);
    const users = "Mike,Jane,Tome,Tony";
    const results5 = users.split(",");
    console.log(results5);
    let str = "Hello, My name is Jake. This is my simple stories.";
    const results6 = str.split(".");
    console.log(results6);

    // Array.isArray()

    /**
     * arr.sort() : 배열 재정렬 - 배열 자체가 변경됨
     * 인수로 정렬 로직을 담은 함수를 받음
     */
    let arr2 = [1, 5, 4, 2, 3];
    // arr2 = ["a", "e", "c", "b", "d"];
    // arr2 = [27, 8, 5, 13]; // 정렬할때 숫자를 문자로 취급함
    const fn = (a, b) => {
        return a - b; // Lodash 같은 라이브러리 사용
    }
    arr2.sort(fn);
    console.log(arr2);

    /**
     * arr.reduce() : 인수로 함수를 받음, 누적 계산
     * arr.reduceRight() : 누적계산 오른쪽 부터
     */
        // 배열의 모든 수 합치기
        // for, for of, forEach
        // let result2 = 0;
        // arr2.forEach(num => {
        //     // result = result + unm;
        //     result2 += num;
        // })
    let result2 = arr2.reduce((prev, cur) => {
            return prev + cur;
        }, 100) // 초기값 optional
    console.log(result2);

    let userList2 = [
        {name: "Jake", age: 29},
        {name: "Tom", age: 10},
        {name: "Jane", age: 19},
        {name: "Sue", age: 26},
        {name: "Harry", age: 30},
        {name: "Steve", age: 20},
    ];

    let result3 = userList2.reduce((prev, cur) => {
        // if(cur.age > 19) {
        //     prev.push(cur.name);
        // }
        // return prev; // 성인인 사람 이름만 출력
        // return (prev += cur.age); // 나이의 합
        if (cur.name.length === 3) {
            prev.push(cur.name);
        }
        return prev;
    }, []);

    console.log(result3);

    /**
     * 구조분해 할당(Destructing Assignment) : 배열이나 객체의 속성을 분해해서 그 값을 변수에 담을 수 있게 하는 표현식
     * let [x, y] = [1, 2]
     * let [user1, , user2] = [...] : 일부 반환값 무시
     * - 배열 구조 분해 : 바꿔치기 temp를 주고 b를 a로 옮기고 c를 b로 이동
     * let [a, b] = [b, a];
     * - 객체 구조 분해 : 키 값등으로 저장, 변수 바꾸기 가능
     */

    /**
     * 나머지 매개변수, 전개구문(Rest Parameters, Spread Syntax)
     * [ ... ]
     * 메소드 인수 전달? - 개수 마음대로, 아무것도 안해도 됨
     * arguments - 함수로 넘어온 모든 인수에 접근! 함수 내에서 이용가능한 지역변수, 배열 내장메소드는 없음 -> ES6에서는 ...로 변경
     */

        // 나머지 매개변수 - 전달받은 모든 수를 더해야함
    const add = (...numbers) => {
            // let result = 0;
            // numbers.forEach(num => result += num);
            let result = numbers.reduce((prev, cur) => prev + cur);
            console.log(result);
        }
    add(1, 2, 3);
    add(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    /**
     * 나머지 매개변수(...)
     * user 객체를 만들어 주는 생성자 함수 만듦
     */
    function Users(name, age, ...skill) {
        this.name = name;
        this.age = age;
        this.skill = skill;
    }

    const newUser1 = new Users('Mike', 30, 'html', 'css');
    const newUser2 = new Users('Tom', 20, 'JS', 'React', 'java');
    const newUser3 = new Users('Jane', 10, 'English');

    console.log(newUser1);
    console.log(newUser2);
    console.log(newUser3);

    /**
     * 전개 구문(Spread Syntax) : 복제
     * arr1 을 [4,5,6,1,2,3] 으로
     */
    let arr3 = [1, 2, 3];
    let arr4 = [4, 5, 6];

    // arr4.reverse().forEach((num) => {
    //     arr3.unshift(num);
    // });
    // console.log(arr3);

    arr3 = [...arr4, ...arr3];
    console.log(arr3);

    let dev = {name: "Jake"};
    let info = {age: 29};
    let fe = ["Java", "React"];
    let lang = ["Korean", "Japanese"];

    // dev = Object.assign({},
    //     dev,
    //     info, {
    //         skills: [],
    //     });
    // fe.forEach((item) => {
    //     dev.skills.push(item);
    // });
    // lang.forEach((item) => {
    //     dev.skills.push(item);
    // });
    // dev.skills = [fe, lang];
    dev = {
        ...user,
        ...info,
        skills: [
            ...fe,
            ...lang,
        ]
    }
    console.log(dev);

    /**
     * 클로저(Closure)
     * 자바 스크립트는 어휘적 환경(Lexical Environment)를 가짐
     * let 으로 선언된 변수(초기화 x, undefined), 함수(사용가능)가 먼저 선언됨
     * 넘겨받은 새로운 내부 Lexical 환경 -> 전역 Lexical 환경 참조
     *
     * 클로저 - 함수와 렉시컬 환경의 조합
     * 함수가 생성될 당시의 외부 변수를 기억 생성 이후에도 계속 접근 가능
     */
    function makeCounter() {
        let num = 0; // 외부함수의 num에 접근 (은닉화!)

        return function () {
            return num++; // 내부함수에서
        }
    }

    let counter = makeCounter(); // 이 함수로만 컨트롤 할 수 있음

    console.log(counter());
    console.log(counter());
    console.log(counter());

    /**
     * setTimeout(함수, 시간, 인수) : 일정시간이 지난 후 함수를 실행
     * setInterval() : 일정 시간 간격으로 함수를 반복
     */

    // function time() {
    //     console.log(3)
    // }
    // setTimeout(time, 3000);
    // setTimeout((name) => {
    //     console.log(name)
    // }, 3000, 'Mike');
    // clearTimeout : setTimeout 이 반환하는 tId를 초기화 시켜 스케쥴링을 중지함
    // setInterval((name) => {
    //     console.log(name)
    // }, 3000, 'Mike');
    // setInterval 은 delay = 0 브라우저 기본지연시간 4ms 이상으로 실행 (실행중인 스크립트가 종료된 이후에 실행되기 때문)
    // let num = 0;

    // function showTime() {
    //     console.log(`안녕하세요, 접속하신지 ${num++}초가 지났습니다.`);
    //     if (num > 5) {
    //         clearInterval(tId);
    //     }
    // }
    //
    // const tId = setInterval(showTime, 1000);

    /**
     * call, apply, bind : 함수 호출 방식과 관계없이 this 를 지정할 수 있음.
     * call : 모든 함수에서 사용할 수 있으며, this 를 특정값으로 지정할 수 있음.
     * apply : 함수 매개변수를 처리하는 방법만 다름, call 은 일반적인 함수와 마찬가지로 매개변수를 직접 받지만, apply 는 매개변수를 배열로 받음.
     * bind : 함수의 this 값을 영구히 바꿀 수 있음.
     */
    const mike = {
        name: "Mike",
    }
    const tom = {
        name: "Tom",
    }

    function showThisName() {
        // console.log(this.name);
    }

    showThisName();
    showThisName.call(mike);

    function update(birthYear, occupation) {
        this.brithYear = birthYear;
        this.occupation = occupation;
    }

    update.call(mike, 1999, "singer");
    update.apply(mike, [1999, "singer"]);
    console.log(mike);

    update.call(tom, 2002, "teacher");
    update.apply(tom, [2002, "teacher"]);
    console.log(tom);

    // apply 는 매개변수를 배열로 받을 때 유용
    const nums = [3, 10, 1, 6, 4];
    // const minNum = Math.min(...nums);
    const minNum = Math.min.apply(null, nums);
    // const maxNum = Math.max(...nums);
    // const maxNum = Math.max.apply(null, nums);
    const maxNum = Math.max.call(null, ...nums);
    console.log(minNum);
    console.log(maxNum);

    const updateMike = update.bind(mike);

    updateMike(1980, "police");
    console.log(mike);

    const user4 = {
        name: "Mike",
        showName: function () {
            console.log(`hello, ${this.name}.`);
        },
    }
    user4.showName();
    let fns = user4.showName;
    fns.call(user4);
    fns.apply(user4);
    let boundFn = fns.bind(user4);
    boundFn();

    /**
     * [ 상속, prototype ]
     * Prototype Chaining
     */
        // const car = {
        //     wheels: 4,
        //     drive() {
        //         console.log("drive...");
        //     }
        // }
        // const bmw = {
        //     color: "red",
        //     navigation: 1,
        // }
        // const benz = {
        //     color: "white",
        // }
        // const audi = {
        //     color: "black",
        // }
        // bmw.__proto__= car;
        // benz.__proto__= car;
        // audi.__proto__= car;
        // console.log(bmw.wheels);

    const Bmw = function (color) {
            const c = color;
            this.getColor = function () {
                console.log(c);
            }
        };

    const x5 = new Bmw("red");
    console.log(x5.getColor());

    /**
     * Class : ES6에 추가된 스펙
     */
    class User2 {
        constructor(name, age) {
            this.name = name;
            this.age = age;
        }

        showName() {
            console.log(this.name);
        }
    }

    const tom2 = new User2('Tom', 30);

    /**
     * Class 오버라이딩
     */
        // extends 로 상송
    class Car {
        constructor(color) {
            this.color = color;
            this.wheels = 4;
        }

        drive() {
            console.log("drive..");
        }

        stop() {
            console.log("STOP!!");
        }
    }

    class Bmws extends Car {
        constructor(color) {
            super(color);
            this.navigation = 1;
        }

        park() {
            console.log("PARK");
        }

        stop() {
            super.stop(); // 부모의 메소드를 상속
            // console.log("OFF");
        }
    }

    const z4 = new Bmws("blue");
    console.log(z4);

    /**
     * [ Promise ] : 상품이 준비되는동안 전화번호 줌
     * Callback 함수 : 어떤 일이 완료된 이후(성공(resolve), 실패(reject)) 실행되는 함수
     * new Promise
     * state 는 pending(대기) 였다가 resolve(value) 가 되면 fulfilled(이행) 됨
     * result 는 undefined 였다가 resolve(value)의 value 가 됨
     * state 는 pending(대기) 였다가 reject(error) 가 되면 rejected(거부) 됨
     * result 는 undefined 였다가 reject(error)의 error 가 됨
     *
     * then, catch, finally
     */
    const pr = new Promise((resolve, reject) => {
        setTimeout(() => {
            // resolve('OK')
            // reject(new Error('err.....'));
        }, 1000)
    })
    //
    // console.log("시작");
    // pr.then(result => {
    //     console.log(result);
    // }).catch((err)=>{
    //   console.log(err);
    // }).finally(()=>{
    //     console.log("끝");
    // })

    // callback hell 콜백 지옥..
    const f1 = (callback) => {
        setTimeout(function () {
            console.log("1번 주문 완료");
            callback();
        }, 1000);
    };

    const f2 = (callback) => {
        setTimeout(function () {
            console.log("2번 주문 완료");
            callback();
        }, 3000);
    };

    const f3 = (callback) => {
        setTimeout(function () {
            console.log("3번 주문 완료");
            callback();
        }, 2000);
    };

    // console.log('시작')
    // f1(function () {
    //     f2(function() {
    //         f3(function () {
    //             console.log('끝')
    //         });
    //     });
    // });

    // Promise Chaining
    const f4 = () => {
        return new Promise((res, rej) => {
            setTimeout(() => {
                res("1번 주문 완료");
            }, 1000)
        })
    }

    const f5 = (message) => {
        console.log(message);
        return new Promise((res, rej) => {
            setTimeout(() => {
                res("2번 주문 완료");
                // rej("xxx");
            }, 3000)
        })
    }

    const f6 = (message) => {
        console.log(message);
        return new Promise((res, rej) => {
            setTimeout(() => {
                res("3번 주문 완료");
            }, 2000)
        })
    }

    async function order() {
        console.log("시작")
        try {
            // const result1 = await f4();
            // const result2 = await f5(result1);
            // const result3 = await f6(result2);
            // console.log(result3);
            const result = await Promise.all([f4(), f5(), f6()]);
            console.log(result);
        } catch (e) {
            console.log(e);
        }
        console.log("종료")
    }

    // order();

    // console.log('시작');
    // f4()
    //     .then(res => f5 (res))
    //     .then(res => f6 (res))
    //     .then(res => console.log(res))
    //     .catch(console.log)
    //     .finally(()=>console.log('끝'));

    // Promise.all : 한꺼번에 시작하고 모두 사용할 수 있음, 한개라도 error 가 있으면 바로 실패
    // console.time('x');
    // Promise.all([f4(), f5(), f6()])
    //     .then(res => {
    //         console.log(res);
    //         console.timeEnd("x");
    //     })

    // Promise.race : 하나라도 완료되면 먼저 끝냄!
    // console.time('x');
    // Promise.race([f4(), f5(), f6()])
    //     .then(res => {
    //         console.log(res);
    //         console.timeEnd("x");
    //     })

    /**
     * async, await
     */
    // async function getName() {
    //     // return "Mike";
    //     // return Promise.resolve("Tom");
    //     throw new Error("error..");
    // }
    // console.log(getName());
    // getName().then(name => {
    //     console.log(name);
    // })
    // getName().catch(err => {
    //     console.log(err);
    // })

    // await
    // function getName(name) {
    //     return new Promise((resolve, reject) => {
    //         setTimeout(()=>{
    //             resolve(name);
    //         }, 1000);
    //     });
    // }
    //
    // async function shoName() {
    //     const result = await getName('Mike');
    //     console.log(result);
    // }
    // console.log("시작");
    // shoName();

    /**
     * Generator : 함수의 실행을 중간에 멈췄다가 재개할 수 있는 기능
     * next..하면 다음 yield 까지 실행 value 는 yield 옆의 값, done 은 실행완료시 true, 한번 더 next 하면 undefined 에 done 은 true
     * next()
     * return() : 즉시 그 value 를 반환 done 은 true
     * throw() : error 시 value undefined, err 반환 done 은 true
     * iterable
     * Symbol.iterator 가 있다.
     * 배열도 iterable 한 객체
     * 문자열도 iterable
     */
    function* fn7() {
        try {
            console.log(1);
            yield 1;
            console.log(2);
            yield 2;
            console.log(3);
            console.log(4);
            yield 3;
            return 'finish';
        } catch (e) {
            console.log(e);
        }
    }
    const a = fn7();
    console.log(a);
    a.next();

    const arr10 = [1,2,3,4,5];
    const it = arr10[Symbol.iterator]();
    console.log(it.next())

    // Generator yield* dldyd
    function* gen1() {
        yield "W";
        yield "o";
        yield "r";
        yield "l";
        yield "d";
    }

    function* gen2() {
        yield "Hello,";
        yield* gen1();
        yield "!";
    }

    console.log(...gen2());

    return (
        <div>
            <h1>하이</h1>
        </div>
    );


};

export default Temp;
