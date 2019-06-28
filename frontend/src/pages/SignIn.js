import React, { Component } from 'react';
import '../styles/pages/Login.css';
import axios from 'axios';

class SignIn extends Component {
    constructor(props) {
        super(props);
        this.handleNameChange = this.handleNameChange.bind(this);
        this.handlePasswordChange = this.handlePasswordChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this)
        this.state = {
            name: '',
            password: '',
            isSignedUp: false,
        };

    }

    handleNameChange(e) {
        this.setState({ name: e.target.value })
    }
    handlePasswordChange(e) {
        this.setState({ password: e.target.value })
    }

    handleSubmit = async (event) => {
        event.preventDefault();
        console.log(this.state);
        const logisState = await axios.post('/picture-on-map/v1/accounts/login/process/', {
            name: this.state.name,
            password: this.state.password
        })

        if( logisState.status == 200 ){
            console.log("성공");
            this.props.history.push("/home")
        }else{
            console.log(logisState);
        }

    }

    render() {
        return (
            <form className={"login-box"}>
                <h1>Login</h1>
                <input type={"text"} onChange={this.handleNameChange} id={'username'} placeholder={"Username"} autoFocus autoComplete={"on"} />
                <input type={"password"} onChange={this.handlePasswordChange} id={'password'} placeholder={"password"} autoFocus autoComplete={"off"} />
                <button onClick={this.handleSubmit}>로그인</button>
            </form>
        );
    }
}

export default SignIn;