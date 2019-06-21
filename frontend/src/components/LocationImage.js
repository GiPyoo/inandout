import React, {Component} from 'react';

class LocationImage extends Component{

    render(){
        const {locationImage} = this.props;

        
        return(
            <div className={"locationImageContainer"} onClick={this.handleClick} style={{'textAlign': 'center'}}>
                <img src={locationImage} width={"100%"} />
            </div>
        );
    }
}

export default LocationImage;