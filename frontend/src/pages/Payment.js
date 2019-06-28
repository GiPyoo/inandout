import React, { Component, Fragment } from 'react';
import axios from 'axios';
import '../styles/pages/Map.css';
import 'bootstrap/dist/css/bootstrap.css';
import NavBar from "../components/NavBar";

// 각종 상태들을 관리하는 App 컴포넌트
class Payment extends Component {

  constructor(props) {
    super(props);
    this.state = {
        client:"",
        transactionDate:"" ,
        place:"",
        cash:"",
        inputCash:"",
        transactionType:"",
        outputCash:"",
        originalCash:""
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
      const tempObject = {}
      tempObject[event.target.name] = event.target.value;
    this.setState(tempObject);
  }

  async handleSubmit(event) {
      const saveTransactionResponse = await axios({
        method: 'post',
        url: '/hackathonApi/saveAccountTransactionHistory',
        data: this.state
      })
      .then(response => {
        console.log(response);
        // this.props.callbackFromParent(response.data);
      })
      .catch(response => { console.log(response);
      });

      console.log(saveTransactionResponse);
  }

  render() {
      console.log("난 바뀐 API야");
      console.log(this.state);
      return (
      <div>
      <NavBar/>
      <form onSubmit={this.handleSubmit} className="form-group">
        <label>
          client(의뢰인):
          <input type="text" name="client" value={this.state.client} onChange={this.handleChange} className="form-control"/>
        </label>
        <label>
          transactionDate(거래일):
          <input type="text" name="transactionDate" value={this.state.transactionDate} onChange={this.handleChange} className="form-control"/>
        </label>
        <label>
          place(취급점):
          <input type="text" name="place" value={this.state.place} onChange={this.handleChange} className="form-control"/>
        </label>
        <label>
          cash(거래금액):
          <input type="text" name="cash" value={this.state.cash} onChange={this.handleChange} className="form-control"/>
        </label>
        <label>
          inputCash(입금금액):
          <input type="text" name="inputCash" value={this.state.inputCash} onChange={this.handleChange} className="form-control"/>
        </label>
        <label>
          transactionType(통장적요):
          <input type="text" name="transactionType" value={this.state.transactionType} onChange={this.handleChange} className="form-control"/>
        </label>
        <label>
          outputCash(지급금액):
          <input type="text" name="outputCash" value={this.state.outputCash} onChange={this.handleChange} className="form-control"/>
        </label>
        <label>
          originalCash(거래원금, 안써도됨):
          <input type="text" name="originalCash" value={this.state.originalCash} onChange={this.handleChange} className="form-control"/>
        </label>
        <input type="submit" value="Submit" className="btn btn-primary" />
      </form>
      </div>
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

export default Payment;