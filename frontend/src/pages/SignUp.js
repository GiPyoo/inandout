import React, {Component} from 'react';
import axios from 'axios';

class SignUp extends Component {

    doSignUp = async (event) => {
        event.preventDefault();
        
        const requestData = {
            'email': document.getElementById('signUpEmail').value, 
            'name': document.getElementById('signUpName').value, 
            'password': document.getElementById('signUpPassword').value, 
            'passwordCheck': document.getElementById('signUpPasswordCheck').value
        };

        const signUpResponse = await axios.post(
                                        'http://106.10.56.117:8080/picture-on-map/v1/accounts', 
                                        requestData
                                        );

        if(signUpResponse.status === 201){
            alert('회원가입 성공');
            this.props.history.push("/signIn");
        }else{
            alert('회원가입 실패');
        }

    };
    
    render(){
        return (
            <div className="container mt-5">
                <h1>회원가입</h1>
                <form className="mt-5">
                    <div className="form-group">
                        <label htmlFor="signUpEmail">이메일</label>
                        <input type="email" className="form-control" id="signUpEmail" aria-describedby="signUpEmailHelp" placeholder="이메일" autoComplete="on" required autoFocus />
                        <small id="signUpEmailHelp" className="form-text text-muted">이메일은 필수 입력사항 입니다.</small>
                    </div>
                    <div className="form-group">
                        <label htmlFor="signUpName">성명</label>
                        <input type="text" className="form-control" id="signUpName" aria-describedby="signUpNameHelp" placeholder="성명" autoComplete="on" required autoFocus />
                        <small id="signUpNameHelp" className="form-text text-muted">성명은 필수 입력사항 입니다.</small>
                    </div>
                    <div className="form-group">
                        <label htmlFor="signUpPassword">비밀번호</label>
                        <input type="password" className="form-control" id="signUpPassword" aria-describedby="signUpPasswordHelp" placeholder="비밀번호" autoComplete="off" required autoFocus />
                        <small id="signUpPasswordHelp" className="form-text text-muted">비밀번호는 필수 입력사항 입니다.</small>
                    </div>
                    <div className="form-group">
                        <label htmlFor="signUpPasswordCheck">비밀번호 확인</label>
                        <input type="password" className="form-control" id="signUpPasswordCheck" aria-describedby="signUpPasswordCheckHelp" placeholder="비밀번호 확인" autoComplete="off" required autoFocus />
                        <small id="signUpPasswordCheckHelp" className="form-text text-muted">입력한 비밀번호를 다시 한번 입력해주세요</small>
                    </div>
                    <button type="submit" className="btn btn-dark" id="btn-signup" onClick={this.doSignUp}>가입</button>
                </form> 
                </div>
        );
    }
}

export default SignUp;