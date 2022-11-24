// Action
export const increase = (username) => ({type: "INCREMENT", payload: username});
export const decrease = (username) => ({type: "DECREMENT", payload: username});

// State
const initState = {
    number: 1,
    url: "http://localhost:8080",
    username: "",
}

// 액션의 결과를 걸러냄
const reducer = (state = initState, action) => {
    switch (action.type) {
        case "INCREMENT":
            return {number: state.number + 1, username: action.payload}; // return 되면 호출한 쪽에서 받는게 아닌 return 되는 순간 ui 변경
        case "DECREMENT":
            return {number: state.number - 1, username: action.payload};
        default:
            return state;
    }
}

export default reducer;