import React, { Component } from "react";
import { withRouter } from "react-router";
import axios from "axios";
import Elements from "../components/Elements";
import NavBar from "../components/NavBar";
import "../styles/pages/accountDetail.css";

class AccountDetail extends Component {
  id = 0;
  constructor(props) {
    super(props);
    this.state = {
      histories: []
    };
  }

  componentDidMount = async event => {
    console.log(this.props.match.params.id);
    event.preventDefault();
    const response = await axios.get(
      "/picture-on-map/v1/accounts/virtualAccounts/" +
        this.props.match.params.id
    );
    this.createHistories(response.data.data);
  };

  createHistories = data => {
    if (data) {
      for (var i = 0; i < data.length; i++) {
        //createdAt : 시간 , amount : 남은금액 , withdraw : 지출 , deposit : 수입
        var { histories } = this.state;
        var temp = {
          contents: data[i].name,
          createdAt: data[i].createdAt,
          amount: data[i].amount,
          withdraw: data[i].withdraw,
          deposit: data[i].deposit,
          category: data[i].virtualAccount.category.name,
          accountname: data[i].virtualAccount.name
        };
        this.setState({
          histories: histories.concat({ id: this.id++, ...temp })
        });
        console.log(this.state.histories);
      }
    }
  };
  render() {
    return (
      <div>
        <NavBar />
        <section>
          <Elements data={this.state.histories} />
        </section>
      </div>
    );
  }
}

export default withRouter(AccountDetail);
