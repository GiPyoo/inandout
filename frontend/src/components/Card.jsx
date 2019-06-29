import React, { Component, Fragment } from "react";
import Slide from "./Slide";
import { LeftArrow, RightArrow } from "./Arrow";

class Card extends Component {
  constructor(props) {
    super(props);
    this.handleCall = this.handleCall.bind(this);
  }

  handleonClick = event => {
    this.props.handleAddAccount(true);
  };

  handleCall = event => {
    location.href = "/AccountDetail/" + this.props.accountID;
  };
  render() {
    console.log(this.props.info);
    return (
      <article className={"slider"}>
        <div className={"top"}>
          {this.props.category} {this.props.name} 계좌
        </div>
        <div className={"middle"}>
          <LeftArrow handlePreAccount={this.props.handlePreAccount} />
          <Slide categoryId={this.props.categoryId} />
          <RightArrow handleNextAccount={this.props.handleNextAccount} />
        </div>
        <div className={"bottom"}>
          <p>{this.props.amount}</p>
          <button
            className="btn btn-outline-secondary"
            onClick={this.handleCall}
          >
            조회
          </button>
          <button
            className="btn btn-outline-dark"
            onClick={this.handleonClick.bind(this)}
          >
            추가
          </button>
        </div>
      </article>
    );
  }
}

export default Card;
