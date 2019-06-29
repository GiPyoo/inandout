import React, { Component } from "react";
import Axios from "axios";

class Addition extends Component {
  constructor(props) {
    super(props);
    this.state = { name: "", amount: 0, categoryId: 0 };
    this.handleChange = this.handleChange.bind(this);
  }
  handleChange = contents => {
    event => {
      var targetInput = {};
      targetInput[contents] = event.target.value;
      this.setState({ targetInput });
    };
  };
  handleSubmit = async event => {
    const data = {
      name: this.state.name,
      amount: this.state.amount,
      categoryId: this.state.categoryId
    };
    console.log(data);
    const response = await Axios.post(
      "/picture-on-map/v1/accounts/virtualAccounts/",
      data
    );
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
              value={this.state.name}
              onChange={this.handleChange("name")}
            />
            <br />
            <label> 설정금액 </label>
            <br />
            <input
              type={"text"}
              value={this.state.amount}
              onChange={this.handleChange("amount")}
            />

            <br />
            <label>카테고리</label>
            <br />
            <select value={this.state.categoryId} onChange={this.handleChange}>
              <option value="1">식비</option>
              <option value="2">취미/여가</option>
              <option value="3">편의점/마트/잡화</option>
              <option value="4">주거/통신</option>
              <option value="5">쇼핑</option>
              <option value="6">교육</option>
            </select>
          </div>
          <div className={"bottom"}>
            <button type={"submit"}>확인!</button>
          </div>
        </form>
      </article>
    );
  }
}

export default Addition;
