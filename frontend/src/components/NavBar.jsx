import React, { Component } from "react";
import NavBtn from './NavBtn'
import "../styles/components/NavBar.css";

class NavBar extends Component {
  render() {
    return (
      <nav className={"navigation"}>
        <ul>
          <li>
          <NavBtn url="/home" name="Home"/>
          </li>
          <li>
            <NavBtn url="/login" name="Login"/>
          </li>
          <li>
          <NavBtn url="/signUp" name="SignUp"/>
          </li>
          <li>
          <NavBtn url="/imageStore" name="ImageStore"/>
          </li>
          <li>
          <NavBtn url="/payment" name="Payment"/>
          </li>
          <li>
          <NavBtn url="/payment/result" name="Payment-Result"/>
          </li>
        </ul>
      </nav>
    );
  }
}

export default NavBar;
