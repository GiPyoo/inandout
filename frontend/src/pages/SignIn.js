import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import '../styles/pages/Login.css';
import saladImg from '../styles/img/salad.svg';
import axios from 'axios';

class SignIn extends Component{
    doSignIn = async (event) => {
        event.preventDefault();
        
        const requestData = {
            email: document.getElementById('signInEmail').value, 
            password: document.getElementById('signInPassword').value
        };
        
        try{
            const signInResponse = await axios.post(
                'http://106.10.56.117:8080/picture-on-map/v1/accounts/login', 
                requestData);

            // 브라우저의 세션 스토리지에 로그인 정보 저장
            sessionStorage.setItem('userId', signInResponse.data.data.id);
            this.props.history.push("/");
            
        }catch(error){
            alert('로그인에 실패하셨습니다.');
        }

        
        
        
    };
    
    render(){
        const signUpButtonStyle = {
            'color': 'white', 
            'textDecoration': 'none'
        };
        
        return(
            <div className={"text-center"} id={"loginContainer"}>
                <form className={"form-signin"}>
                    <img src={saladImg} alt={""} width={"70"} height={"70"} />
                    <h1 className={"h3 mb-3 font-weight-normal"}>Login</h1>
                    <input type={"email"} id={"signInEmail"} className={"form-control"} name={"id"} placeholder={"이메일"} autoFocus autoComplete={"on"} />
                    <input type={"password"} id={"signInPassword"} className={"form-control"} name={"password"} placeholder={"비밀번호"} autoFocus autoComplete={"off"} />
                    <button className={"btn btn-lg btn-dark btn-block"} type={"submit"} id={"btn-login"} onClick={this.doSignIn}>로그인</button>
                    <button className={"btn btn-lg btn-dark btn-block"} type={"button"} id={"btn-signup"}><Link to="/signUp" style={signUpButtonStyle}>회원가입</Link></button>
                    <p className={"mt-5 mb-3 text-muted"}>picture on map</p>
                </form>
            </div>
        );
    }
}

export default SignIn;