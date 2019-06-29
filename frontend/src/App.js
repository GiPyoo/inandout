import React, { Component } from "react";
import { Route, BrowserRouter } from "react-router-dom";
import {
  Payment,
  Home,
  CheckPayment,
  SignIn,
  SignUp,
  ImageStore
} from "./pages";

class App extends Component {
  constructor(props) {
    super(props);
    var data = {
      name: "",
      price: ""
    };
    this.state = { PaymentData: data };
    console.log(data);
  }

  PaymentCallBack = dataFromChild => {
    this.setState({
      PaymentData: dataFromChild
    });
  };

  render() {
    return (
      <BrowserRouter>
        <Route
          exact
          path="/payment"
          render={() => <Payment callbackFromParent={this.PaymentCallBack} />}
        />
        <Route
          exact
          path="/payment/result"
          render={() => <CheckPayment data={this.state.PaymentData} />}
        />
        <Route exact path="/home" component={Home} />
        <Route exact path="/login" component={SignIn} />
        <Route exact path="/signUp" component={SignUp} />
        <Route exact path="/AccountDetail" component={SignUp} />
        <Route exact path="/imageStore" component={ImageStore} />
      </BrowserRouter>
    );
  }
}

export default App;
