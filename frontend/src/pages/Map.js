import React, {Component, Fragment} from 'react';
import {RenderAfterNavermapsLoaded} from 'react-naver-maps';
import axios from 'axios';
import MapBox from '../components/MapBox';
import NavBar from '../components/NavBar';
import '../styles/pages/Map.css';




// 각종 상태들을 관리하는 App 컴포넌트
class Map extends Component{

  // 테스트용 State
  state = {
    userId: '', 
    marker: [ 
    ]
  };

  // 서버 이미지 데이터 읽어오기
  getServerImageData = () => {
    axios.get('http://localhost:8080/picture-on-map/v1/images')
    .then(response => {
      const imageDataList = response.data.data.map(imageData => {
        return {
          id: imageData.id, 
          location: {
            lat: imageData.point.yindex, 
            lng: imageData.point.xindex
          }, 
          uri: imageData.uri, 
          tags: imageData.imageTags
        };
      });
    
    // 읽어온 data를 state에 등록!
    this.setState({
      marker: [...this.state.marker, ...imageDataList]
    });
      
    }).catch(error => {
      console.log(error);
      /*
      서버 끊길때 사용

      this.setState({
        marker: [...this.state.marker,         {
          id: 100, 
          location: {
            lat: 37.3595704, 
            lng: 127.105399
          }, 
          uri: 'https://images-na.ssl-images-amazon.com/images/I/71-%2B2B-BJWL._SY679_.jpg', 
          tags: []
        }]

      });
      */
    });

  };

  
  render(){
    const countOfImageData = this.state.marker.length;
    
    if(countOfImageData > 0){
      console.log(this.state.marker);
      return (
        <Fragment>
          <NavBar userId={this.state.userId}/>
  
          <div className={"container-fluid"}>
            <div className={"row"}>
              
  
              <RenderAfterNavermapsLoaded
                  ncpClientId={"83bfuniegk"}
              >
                <MapBox data={this.state.marker} userId={this.state.userId} />
              </RenderAfterNavermapsLoaded>
            </div>        
          </div>
        </Fragment>
      );
    }else{
      return(
        <div>
        </div>

      )
    }

  }

  componentDidMount(){
    console.log('Map.js - componentDidMount()');

    // 로그인 되어 있는지 안되어 있는지 확인
    const loginUserId = sessionStorage.getItem('userId');

  
    /*
     * sessionStorage에 로그인한 id가 저장되어 있음 (로그인 안했을 경우에는 ''으로 저장되어 있음)
     * sessionStorage를 확인하여 로그인 되어 있을 경우, state userId를 해당 id로,
     * sessionStorage를 확인하여 로그인 안되어 있을 경우, state userId를 ''으로 
     */
    if(loginUserId !== null){
      this.setState({
        userId: loginUserId
      });
    }else{
      this.setState({
        userId: ''
      })
    }

    this.getServerImageData();
    
  }
}



export default Map;
