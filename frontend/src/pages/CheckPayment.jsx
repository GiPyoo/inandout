import React, { Component, Fragment } from 'react';
import PaymentInfo from '../components/PaymentInfo'

class CheckPayment extends Component {
  render() {
    console.log(this.props)
    const InfoList = Object.keys(this.props.data).map((key,value)=>{
        return (
          
            <PaymentInfo label = {key} info = {value.toString()}/>
        );
      })
    return (
      <h1>결제 완료</h1>,
      <ul>{InfoList}</ul>
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