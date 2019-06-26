import React, { Component, Fragment } from 'react';
import axios from 'axios';

// 각종 상태들을 관리하는 App 컴포넌트
class Home extends Component {

  constructor(props) {
    super(props);
    this.state = { account: []};
  }

  componentDidMount() {
    axios.get('/picture-on-map/v1/accounts/home/getlist/')
      .then(res => {
        const account = res.data;
        this.setState({ account });
        console.log(account);
      }).catch(error => {
        console.log(실패);
      })
  }

  render() {
    return (
        <div>홈입니다. 
            {this.state.account[0]}
        </div>
    );
  };
}

export default Home;