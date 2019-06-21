import React, {Component} from 'react';
import {Route, BrowserRouter} from 'react-router-dom';
import {Map, SignIn, SignUp, ImageStore} from './pages';

class App extends Component {
  render(){
    return (
      <BrowserRouter>
        <Route exact path ="/" component={Map} />
        <Route exact path="/signIn" component={SignIn} />
        <Route exact path="/signUp" component={SignUp} />
        <Route exact path="/imageStore" component={ImageStore} />
      </BrowserRouter>
    );
  }
}

export default App;