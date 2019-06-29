import React, { Component } from "react";

import * as category from "../module/ImageList";

class Slide extends Component {
  render() {
    return (
      <div className={"cardbox virtual-acc"}>
        <img src={category.lists(this.props.categoryId)} />
      </div>
    );
  }
}

export default Slide;
