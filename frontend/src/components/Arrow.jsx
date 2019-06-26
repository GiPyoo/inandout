import React, { Component, Fragment } from 'react';
import leftarrow from '../../public/leftarrow.png'
import rightarrow from '../../public/rightarrow.png'

class LeftArrow extends Component {
    render() {
        return (
            <div onClick ={this.props.handlePreAccount}>
            <img src={leftarrow} ></img>
            </div>
        )
    }
}

class RightArrow extends Component {
    render() {
        return (
            <div onClick ={this.props.handleNextAccount}>
            <img src={rightarrow} ></img>
            </div>
        )
    }
}

export { LeftArrow, RightArrow };