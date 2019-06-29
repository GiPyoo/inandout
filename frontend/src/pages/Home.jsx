import React, { Component, Fragment } from "react";
import axios from "axios";

import NavBar from "../components/NavBar";
import Addition from "../components/Addition";
import Card from "../components/Card";

import "../styles/pages/home.css";
import "bootstrap/dist/css/bootstrap.css";

// 각종 상태들을 관리하는 App 컴포넌트
class Home extends Component {
  id = 0;
  constructor(props) {
    super(props);

    this.state = {
      accounts: [],
      categoryId: 0,
      category: "",
      name: "",
      amount: 0,
      totalindex: 0,
      curruntindex: 0,
      add: false
    };

    this.handleNextAccount = this.handleNextAccount.bind(this);
    this.handlePreAccount = this.handlePreAccount.bind(this);
    this.handleAddAccount = this.handleAddAccount.bind(this);
  }

  handleNextAccount() {
    console.log(this.state.totalindex);
    var index = this.state.curruntindex;
    console.log(index);
    console.log("다음");
    if (index < this.state.totalindex - 1) {
      index++;
      console.log(index);
      this.setState({ curruntindex: index });
    }
    this.componentDidMount();
  }

  handlePreAccount() {
    console.log(this.state.totalindex);
    var index = this.state.curruntindex;
    console.log("이전");
    if (index > 0) {
      index--;
      console.log(index);
      this.setState({ curruntindex: index });
    }
    this.componentDidMount();
  }

  handleAddAccount = CallFromChild => {
    this.setState({ add: CallFromChild });
  };

  addCallback = CallFromChild => {
    this.setState({
      add: CallFromChild
    });
  };

  createAccountObject(obj) {
    this.setState({
      amount: obj[this.state.curruntindex].amount,
      categoryId: obj[this.state.curruntindex].category.id,
      category: obj[this.state.curruntindex].category.name,
      name: obj[this.state.curruntindex].name,
      totalindex: obj.length
    });
  }

  componentDidMount = async event => {
    const response = await axios.get(
      "/picture-on-map/v1/accounts/virtualAccounts/"
    );
    console.log(response.data.data);
    console.log(response.data.data[0].amount);
    console.log(response.data.data[0].category.name);
    if (response.status == 200) {
      this.createAccountObject(response.data.data);
    }
  };

  render() {
    var template;
    if (this.state.add) {
      template = <Addition parent={this.addCallback} />;
    } else {
      template = (
        <Card
          amount={this.state.amount}
          categoryId={this.state.categoryId}
          category={this.state.category}
          name={this.state.name}
          handlePreAccount={this.handlePreAccount}
          handleNextAccount={this.handleNextAccount}
          handleAddAccount={this.handleAddAccount}
        />
      );
    }
    return (
      <div>
        <NavBar />
        <section>{template}</section>
      </div>
    );
  }
}

export default Home;
