import React, {Component} from 'react';
import food from '../../public/식비.png'
class Slide extends Component{
    render(){
        return(
            <div>
                <img src={this.props.index} />
            </div>
        );
    }
}

export default Slide;