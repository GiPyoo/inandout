import React, { Component } from "react";

class HistoryDetail extends Component {
  render() {
    const {
      createdAt,
      amount,
      withdraw,
      deposit,
      category,
      accountname
    } = this.props.info;

    if (deposit == 0) {
      return (
        <div className={"card"}>
          <div className={"big top"}>
            <div className={"good side"}>
              <div className={"accountname"}>{accountname}</div>
              <div className={"category"}>{category}</div>
            </div>
            <div className={"good withdraw"}>- {withdraw} 원 </div>
          </div>
          <div className={"big bottom"}>{createdAt}</div>
        </div>
      );
    } else {
      return (
        <div className={"card"}>
          <div className={"big top"}>
            <div className={"good side"}>
              <div className={"accountname"}>{accountname}</div>
              <div className={"category"}>{category}</div>
            </div>
            <div className={"good deposit"}>{deposit} 원 </div>
          </div>
          <div className={"big bottom"}>{createdAt}</div>
        </div>
      );
    }
  }
}

export default HistoryDetail;
