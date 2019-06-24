import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import '../styles/pages/Login.css';
import axios from 'axios';

class SignIn extends Component {
    doSignIn = async (event) => {
        event.preventDefault();

        const requestData = {
            email: document.getElementById('signInEmail').value,
            password: document.getElementById('signInPassword').value
        };

        try {
            const signInResponse = await axios.post(
                'http://106.10.56.117:8080/picture-on-map/v1/accounts/login',
                requestData);

            // 브라우저의 세션 스토리지에 로그인 정보 저장
            sessionStorage.setItem('userId', signInResponse.data.data.id);
            this.props.history.push("/");

        } catch (error) {
            alert('로그인에 실패하셨습니다.');
        }
    };

    render() {
        const signUpButtonStyle = {
            'color': 'white',
            'textDecoration': 'none'
        };

        return (
            <form className={"login-box"}>
                <h1>Login</h1>
                <input type={"text"} name={"id"} placeholder={"Username"} autoFocus autoComplete={"on"} />
                <input type={"password"} name={"password"} placeholder={"password"} autoFocus autoComplete={"off"} />
                <button onClick={this.doSignIn}>로그인</button>
            </form>
        );
    }
}

export default SignIn;