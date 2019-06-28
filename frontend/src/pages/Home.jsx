import React, { Component, Fragment } from "react";
import axios from "axios";

import NavBar from "../components/NavBar";
import Slide from "../components/Slide";
import { LeftArrow, RightArrow } from "../components/Arrow";

import * as category from "../module/ImageList";
import "../styles/pages/home.css";

// 각종 상태들을 관리하는 App 컴포넌트
class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      account: [],
      curruntindex: 0
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

  componentDidMount() {
    axios
      .get("/picture-on-map/v1/accounts/virtualAccounts/")
      .then(function(res) {
        console.log("성공"); 
        const account = res.data.body;
        this.setState({ account: account.virtualAccount });
      })
      .catch(function(error) {
        console.log(error.res);
      });
  }

  render() {
    const item = category.lists();
    const real_item = this.state.account
    return (
      <div>
        <NavBar />
        <section>
          <article className={"slider"}>
            <div className = {'top'}>
              {real_item[this.state.curruntindex].name} 계좌
            </div>
            <div className = {'middle'}>
              <LeftArrow handlePreAccount={this.handlePreAccount.bind(this)} />
              <Slide index={item[this.state.curruntindex]} />
              <RightArrow
                handleNextAccount={this.handleNextAccount.bind(this)}
              />
            </div>
            <div className={"bottom"}>
              <p>{real_item[this.state.curruntindex].amount}</p>
              <button>조회</button>
            </div>
          </article>
        </section>
      </div>
    );
  }
}

export default Home;
