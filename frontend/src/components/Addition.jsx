import React, { Component } from "react";
import Axios from "axios";

class Addition extends Component {
  constructor(props) {
    super(props);
    this.state = { name: "", amount: "" };
    this.handleChange = this.handleChange.bind(this);
  }
  handleChange = contents => {
    event => {
      var targetInput = {};
      targetInput[contents] = event.target.value;
      this.setState({});
    };
  };
  handleSubmit = async event => {
    const data = {
      name: this.state.name,
      amount: this.state.amount
    };
    console.log(data);
    const response = await Axios.post(
      "/picture-on-map/v1/accounts/virtualAccounts/add",
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
          </div>
          <br />
          <div className={"bottom"}>
            <button type={"submit"}>확인!</button>
          </div>
        </form>
      </article>
    );
  }
}

export default Addition;
