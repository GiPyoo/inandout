import React, {Component, Fragment} from 'react';
import {RenderAfterNavermapsLoaded} from 'react-naver-maps';
import axios from 'axios';
import MapBox from '../components/MapBox';
import NavBar from '../components/NavBar';
import '../styles/pages/Map.css';




// 각종 상태들을 관리하는 App 컴포넌트
class Map extends Component{

  constructor(props) {
    super(props);
    this.state = {name: '',price:''};

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({name: event.target.value, price: event.target.value});
  }

  handleSubmit(event) {
    axios.post('/process/payment', this.state)
  .then( response => { console.log(response) } )
  .catch( response => { console.log(response) } );
  }

  render(){
    return (
      <form onSubmit={this.handleSubmit}>
        <label>
          Name:
          <input type="text" value={this.state.name} onChange={this.handleChange} />
        </label>
        <label>
          Price:
          <input type="text" value={this.state.price} onChange={this.handleChange} />
        </label>
        <input type="submit" value="Submit" />
      </form>
    );
  }
  };

  componentDidMount(){
    console.log('Payment.js - componentDidMount()');

    // 로그인 되어 있는지 안되어 있는지 확인
    const loginUserId = sessionStorage.getItem('userId');

    if(loginUserId !== null){
      this.setState({
        userId: loginUserId
      });
    }else{
      this.setState({
        userId: ''
      })
    }

    this.getServerImageData();
    
  }
}



export default Map;
