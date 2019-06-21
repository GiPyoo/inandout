import React, {Component} from 'react';
import LocationImage from './LocationImage';
import '../styles/components/LocationInfoList.css';

class LocationImageList extends Component{
    render(){

        if(this.props.imageList !== null){
            
            const locationImages = this.props.imageList.map(
                locationImage => (<LocationImage 
                            key={locationImage.id}
                            locationImage={locationImage.uri}
                        />)
            );
            
            return (
                <div className="locationImagesWrapper">
                    {locationImages}
                </div>
            );
        }

        return (
            <div>

            </div>
        );
        
        

    }
}

export default LocationImageList;