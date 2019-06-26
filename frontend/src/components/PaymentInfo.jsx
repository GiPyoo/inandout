import React, {Component} from 'react';

class PaymentInfo extends Component{

    render(){
        return(
            <li>{this.props.label}:{this.props.info}
            </li>
        );
    }
}

export default PaymentInfo;