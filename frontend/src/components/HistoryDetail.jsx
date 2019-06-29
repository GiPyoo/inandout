import React, { Component } from "react";

class HistoryDetail extends Component {
  render() {
    const {
      createdAt,
      contents,
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
              <div className={"accountname"}>{contents}</div>
              <div className={"category"}>
                {accountname} {category}
              </div>
            </div>
            <div className={"good day"}>
              <div className={"withdraw"}>- {withdraw} 원 </div>
              <div className={"sorrow"}> 잔액 : {amount} </div>
            </div>
          </div>
          <div className={"big bottom"}>{createdAt}</div>
        </div>
      );
    } else {
      return (
        <div className={"card"}>
          <div className={"big top"}>
            <div className={"good side"}>
              <div className={"accountname"}>{contents}</div>
              <div className={"category"}>
                {accountname} {category}
              </div>
            </div>
            <div className={"good day"}>
              <div className={"deposit"}> {deposit} 원 </div>
              <div className={"sorrow"}> 잔액 : {amount} </div>
            </div>
          </div>
          <div className={"big bottom"}>{createdAt}</div>
        </div>
      );
    }
  }
}

export default HistoryDetail;
