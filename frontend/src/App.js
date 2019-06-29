import React, { Component } from "react";
import { Route, BrowserRouter } from "react-router-dom";
import {
  Payment,
  Home,
  CheckPayment,
  SignIn,
  SignUp,
  ImageStore,
  AccountDetail
} from "./pages";

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      id: 0
    };
    this.VAIDCallBack = this.VAIDCallBack.bind(this);
  }

  VAIDCallBack = dataFromChild => {
    this.setState({
      id: dataFromChild
    });
  };

  render() {
    return (
      <BrowserRouter>
        <Route exact path="/payment" render={() => <Payment />} />
        <Route
          exact
          path="/payment/result"
          render={() => <CheckPayment data={this.state.PaymentData} />}
        />
        <Route
          exact
          path="/home"
          render={() => <Home VAIDCallBack={this.VAIDCallBack} />}
        />
        <Route exact path="/login" component={SignIn} />
        <Route exact path="/signUp" component={SignUp} />
        <Route
          exact
          path="/AccountDetail/:id"
          render={() => <AccountDetail acountsId={this.state.id} />}
        />
        <Route exact path="/imageStore" component={ImageStore} />
      </BrowserRouter>
    );
  }
}

export default App;
