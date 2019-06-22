import React, {Component} from 'react';
import {Route, BrowserRouter} from 'react-router-dom';
import {Payment, SignIn, SignUp, ImageStore} from './pages';

class App extends Component {

  PaymentCallBack = (dataFromChild) => {
    this.setState({
      PaymentData : dataFromChild ? dataFromChild : ''
    })
  }

  render(){
    
    return (
      <BrowserRouter>
        <Route exact path ="/payment" component={Payment} callbackFromParent = {this.PaymentCallBack}/>
        <Route exact path="/signIn" component={SignIn} />
        <Route exact path="/signUp" component={SignUp} />
        <Route exact path="/imageStore" component={ImageStore} />
      </BrowserRouter>
    );
  }
}

export default App;