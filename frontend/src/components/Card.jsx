import React, { Component, Fragment } from "react";
import Slide from "./Slide";
import { LeftArrow, RightArrow } from "./Arrow";

class Card extends Component {
  constructor(props) {
    super(props);
  }

  handleonClick = event => {
    this.props.handleAddAccount(true);
  };
  render() {
    console.log(this.props.info);
    return (
      <article className={"slider"}>
        <div className={"top"}>{this.props.category} 계좌</div>
        <div className={"middle"}>
          <LeftArrow handlePreAccount={this.props.handlePreAccount} />
          <Slide categoryId={this.props.categoryId} />
          <RightArrow handleNextAccount={this.props.handleNextAccount} />
        </div>
        <div className={"bottom"}>
          <p>{this.props.amount}</p>
          <button className="btn btn-outline-secondary">조회</button>
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
