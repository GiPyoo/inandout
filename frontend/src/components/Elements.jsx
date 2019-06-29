import React, { Component } from "react";
import HistoryDetail from "./HistoryDetail";

class Elements extends Component {
  static defaultProps = {
    data: []
  };

  render() {
    const { data } = this.props;
    console.log(data);
    const list = data.map(info => <HistoryDetail key={info.id} info={info} />);

    return <article>{list}</article>;
  }
}

export default Elements;
