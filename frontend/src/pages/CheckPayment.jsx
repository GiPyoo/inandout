import React, { Component, Fragment } from 'react';
import axios from 'axios';

// 각종 상태들을 관리하는 App 컴포넌트
class CheckPayment extends Component {

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleSubmit(event) {
  }

  render() {
    return (
      <h1>결제 완료</h1>
    );
  };

  componentDidMount() {
    console.log('Payment.js - componentDidMount()');

    // 로그인 되어 있는지 안되어 있는지 확인
    const loginUserId = sessionStorage.getItem('userId');

    if (loginUserId !== null) {
      this.setState({
        userId: loginUserId
      });
    } else {
      this.setState({
        userId: ''
      })
    }

  }
}

export default CheckPayment;