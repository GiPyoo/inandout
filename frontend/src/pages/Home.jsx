import React, { Component, Fragment } from "react";
import axios from "axios";

import NavBar from "../components/NavBar";
import Slide from "../components/Slide";
import { LeftArrow, RightArrow } from "../components/Arrow";
import Addition from "../components/Addition";

import * as category from "../module/ImageList";
import "../styles/pages/home.css";

// 각종 상태들을 관리하는 App 컴포넌트
class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      account: [],
      curruntindex: 0,
      add: false
    };
  }

  handleNextAccount() {
    var index = this.state.curruntindex;
    if (index != 6) {
      return this.setState({ curruntindex: index + 1 });
    }
  }

  handlePreAccount() {
    var index = this.state.curruntindex;
    console.log;
    if (index != 0) {
      return this.setState({ curruntindex: index - 1 });
    }
  }

  handleAddAccount() {
    this.setState({ add: true });
  }

  addCallback = CallFromChild => {
    this.setState({
      add: CallFromChild
    });
  };

  componentDidMount() {
    axios
      .get("/picture-on-map/v1/accounts/virtualAccounts/")
      .then(res => {
        console.log("성공");
        console.log(res);
        console.log(JSON.parse(res.data));
        const account = res.data.body;
        this.setState({ account: account.virtualAccount });
      })
      .catch(error => {
        console.log(error);
      });
  }

  render() {
    const item = category.lists();
    const real_item = this.state.account;
    var template;
    if (this.state.add) {
      template = <Addition parent={this.addCallback} />;
    } else {
      template = (
        <article className={"slider"}>
          <div className={"top"}>{real_item[this.state.curruntindex]} 계좌</div>
          <div className={"middle"}>
            <LeftArrow handlePreAccount={this.handlePreAccount.bind(this)} />
            <Slide index={item[this.state.curruntindex]} />
            <RightArrow handleNextAccount={this.handleNextAccount.bind(this)} />
          </div>
          <div className={"bottom"}>
            <p>{real_item[this.state.curruntindex]}</p>
            <button>조회</button>
            <button onClick={this.handleAddAccount.bind(this)}>추가</button>
          </div>
        </article>
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
