import React, {Component} from 'react';
import {Route, BrowserRouter} from 'react-router-dom';
import {Payment, CheckPayment , SignIn, SignUp, ImageStore} from './pages';

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {PaymentData: ''};
  }

  PaymentCallBack = (dataFromChild) => {
    this.setState({
      PaymentData : dataFromChild
    })
  }

  render(){
    
    return (
      <BrowserRouter>
        <Route exact path ="/payment" component={Payment} callbackFromParent = {this.PaymentCallBack}/>
        <Route exact path ="/payment/result" component = {CheckPayment} data = {this.state.PaymentData}/>
        <Route exact path="/signIn" component={SignIn} />
        <Route exact path="/signUp" component={SignUp} />
        <Route exact path="/imageStore" component={ImageStore} />
      </BrowserRouter>
    );
  }
}

export default App;