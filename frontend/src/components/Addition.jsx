import React, { Component } from "react";
import axios from "axios";

class Addition extends Component {
  constructor(props) {
    super(props);
    this.state = { name: "", amount: 0, categoryId: 0 };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }
  handleChange = event => {
    var targetInput = {};
    targetInput[event.target.name] = event.target.value;
    this.setState(targetInput);
  };
  handleSubmit = async event => {
    const data = {
      name: this.state.name,
      amount: this.state.amount,
      categoryId: this.state.categoryId
    };
    console.log(data);
    const response = await axios
      .post("/picture-on-map/v1/accounts/virtualAccounts/", data)
      .catch(e => {
        console.log(e);
      });

    if (response === 200) {
      this.props.parent(false);
    }
  };

  render() {
    return (
      <article className={"slider"}>
        <div className={"top"}>계좌추가</div>
        <form>
          <div className={"middle"}>
            <label> 통장이름 </label>
            <br />
            <input
              type={"text"}
              name={"name"}
              value={this.state.name}
              onChange={this.handleChange}
            />
            <br />
            <label> 설정금액 </label>
            <br />
            <input
              type={"text"}
              name={"amount"}
              value={this.state.amount}
              onChange={this.handleChange}
            />

            <br />
            <label>카테고리</label>
            <br />
            <select
              name={"categoryId"}
              value={this.state.categoryId}
              onChange={this.handleChange}
            >
              <option value="1">식비</option>
              <option value="2">취미/여가</option>
              <option value="3">편의점/마트/잡화</option>
              <option value="4">주거/통신</option>
              <option value="5">쇼핑</option>
              <option value="6">교육</option>
            </select>
          </div>
          <div className={"bottom"}>
            <button type={"submit"} onClick={this.handleSubmit}>
              확인!
            </button>
          </div>
        </form>
      </article>
    );
  }
}

export default Addition;
