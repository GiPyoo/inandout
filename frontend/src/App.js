import React, {Component} from 'react';
import {Route, BrowserRouter} from 'react-router-dom';
import {Payment, SignIn, SignUp, ImageStore} from './pages';

class App extends Component {
  myCallback = (dataFromChild) => {
  }
  render(){
    
    return (
      <BrowserRouter>
        <Route exact path ="/" component={Payment} />
        <Route exact path="/signIn" component={SignIn} />
        <Route exact path="/signUp" component={SignUp} />
        <Route exact path="/imageStore" component={ImageStore} />
      </BrowserRouter>
    );
  }
}

export default App;