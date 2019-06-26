import React, { Component, Fragment } from 'react';
import leftarrow from '../../public/leftarrow.png'
import rightarrow from '../../public/rightarrow.png'

class LeftArrow extends Component {
    render() {
        return (
            <img src={leftarrow}></img>
        )
    }
}

class RightArrow extends Component {
    render() {
        return (
            <img src={rightarrow}></img>
        )
    }
}

export { LeftArrow, RightArrow };