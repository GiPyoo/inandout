import React, {Component} from 'react';
import LocationImageList from './LocationImageList';


class SideBar extends Component {
    render(){
        const style = {
            'height': '100%', 
            'backgroundColor': '#f5f5f5', 
            'overflow': 'auto'
        };


        return (
            <div className={"col-2"} style={style}>
                <h2 style={{'textAlign': 'center'}}>Selected</h2>
                <LocationImageList data={this.props.data} 
                                    imageList={this.props.imageList}/>
            </div>
        );
    }
    
};

export default SideBar;