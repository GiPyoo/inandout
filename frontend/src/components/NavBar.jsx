import React, { Component } from "react";
import 'bootstrap/dist/css/bootstrap.css';

class NavBar extends Component {
  render() {
    return (
      <nav class="navbar navbar-expand-sm bg-dark navbar-dark">

      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="/home">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/login">Login</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="=/signUp">SignUp</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="=/payment">Payment</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="=/payment/result">Payment-Result</a>
        </li>
    
      </ul>
    
    </nav>
    );
  }
}

export default NavBar;
