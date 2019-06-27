import React, { Component, Fragment } from 'react';
import Slide from '../components/Slide'
import axios from 'axios';
import * as category from '../module/ImageList'
import { LeftArrow, RightArrow } from '../components/Arrow'

// 각종 상태들을 관리하는 App 컴포넌트
class Home extends Component {

  constructor(props) {
    super(props);
    this.state = {
      account: [],
      curruntindex: 0
    };
  }

  handleNextAccount() {
    var index = this.state.curruntindex;
    if (index != 6) {
      return this.setState({ curruntindex: index + 1 })
    }
  }

  handlePreAccount() {
    var index = this.state.curruntindex;
    console.log
    if (index != 0) {
      return this.setState({ curruntindex: index - 1 })
    }
  }

  componentDidMount() {
    axios.get('/picture-on-map/v1/accounts/virtualAccount/')
      .then(res => {
        const account = res.data;
        this.setState({ account });
        console.log(account);
      }).catch(error => {
        console.log(실패);
      })
  }

  render() {
    const item = category.lists();

    return (
      <div>
        <section>
          <article className={'slider'}>
            <LeftArrow handlePreAccount = {this.handlePreAccount.bind(this)}></LeftArrow>
            <Slide index={item[this.state.curruntindex]} />
            <RightArrow handleNextAccount = {this.handleNextAccount.bind(this)}></RightArrow>
          </article>
        </section>
      </div>
    );
  };
}

export default Home;