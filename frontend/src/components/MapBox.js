import React, {Component, Fragment} from 'react';
import {NaverMap} from 'react-naver-maps';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import SideBar from './SideBar';


// NaverMap 컴포넌트를 렌더링하는 컴포넌트
class MapBox extends Component{
    markerAndImageUriList = [];

    state = {
        modal: false, 
        modalContent: {
            nowIndex: 0, 
            nowImageList: null, 
            nowSideImageList: null
        }
    };

    modalToggle = () => {
        if(this.state.modal === true){
            this.setState({
                modal: !this.state.modal,
                modalContent: {
                    nowIndex: 0, 
                    nowImageList: null, 
                    nowSideImageList: this.state.modalContent.nowSideImageList
                }
            });
        }
    };
    
    handleMarkerClick = (event, imageList) => {
        console.log(event);
        console.log(imageList);
        
        this.setState({
            modal: !this.state.modal, 
            modalContent: {
                nowIndex: 0, 
                nowImageList: imageList, 
                nowSideImageList: [...imageList]
            }
        });
    };

    showPrevImage = () => {
        let nowIndex = this.state.modalContent.nowIndex - 1;
        if(nowIndex < 0){
            nowIndex = nowIndex + this.state.modalContent.nowImageList.length;
        }

        this.setState({
            modalContent: {
                nowIndex: nowIndex, 
                nowImageList: this.state.modalContent.nowImageList, 
                nowSideImageList: this.state.modalContent.nowSideImageList
            }
        });
    };

    showNextImage = () => {
        const nowIndex = (this.state.modalContent.nowIndex + 1) % (this.state.modalContent.nowImageList.length);
        this.setState({
            modalContent: {
                nowIndex: nowIndex, 
                nowImageList: this.state.modalContent.nowImageList, 
                nowSideImageList: this.state.modalContent.nowSideImageList
            }
        });
    };

    // Infowindow 객체의 내용(html element)를 생성한 뒤 반환해주는 함수
    getInfowindowContentElement = (uri, tags) => {
        
            return [
                '<div>', 
                `<div>${uri}</div>`, 
                `<div>${tags}</div>`, 
                '</div>'
            ].join('');            
    };
    
    // 이미 해당 위치에 마커가 존재하는 경우 true리턴
    checkDuplicationLocationMarker = (lat, lng) => {
        
        if(this.markerAndImageUriList.length === 0){
            return false;
        }
        
        for(let index = 0; index < this.markerAndImageUriList.length; index++){
            const markerPosition = this.markerAndImageUriList[index].marker.getPosition();

            if(markerPosition.lat() === lat && markerPosition.lng() === lng){
                return true;
            }
        }
        
        return false;
    };

    addOnClickListenerToMarkers = () => {
        for(let index = 0; index < this.markerAndImageUriList.length; index++){
            const marker = this.markerAndImageUriList[index].marker;
            const imageList = this.markerAndImageUriList[index].imageUriList;


            // Marker 객체 click에 대한 이벤트 리스너
            window.naver.maps.Event.addListener(marker, 'click', (event) => {this.handleMarkerClick(event, imageList)});
        
        }

    };


    // userId Props 사용
    render(){
        const closeBtn = <button className="close" onClick={this.modalToggle}>&times;</button>;
        
        console.log('render() - MapBox.js');
        console.log(this.state);
        return (
            <Fragment>
            <SideBar data={this.props.data} imageList={this.state.modalContent.nowSideImageList}/>
            <div className={"col-10"} style={{'padding': '0px', 'height': '100%'}}>
            <NaverMap 
                id={'map'} 
                style={{
                width: '100%',
                height: '100%'
                }} 
                naverRef={ref => {this.mapRef = ref}}
                defaultCenter={{lat: 37.3595704, lng: 127.105399}} 
                defaultZoom={10} 
            >
            </NaverMap>
            <Modal isOpen={this.state.modal} toggle={this.modalToggle} size={"lg"}>
                <ModalHeader toggle={this.modalToggle} close={closeBtn}>
                    Image Viewer
                </ModalHeader>
                <ModalBody style={{'textAlign': 'center'}}>
                    {this.state.modalContent.nowImageList ? 
                        (
                        <Fragment>
                        <img src={this.state.modalContent.nowImageList[this.state.modalContent.nowIndex].uri} height="500" width="100%" />
                        <div>{this.state.modalContent.nowIndex + 1}/{this.state.modalContent.nowImageList.length}</div>
                        </Fragment>
                        ) 
                        : <span></span>}
                </ModalBody>
                <ModalFooter>
                    <Button color="primary" onClick={this.showPrevImage}>이전</Button>
                    <Button color="primary" onClick={this.showNextImage}>다음</Button>
                </ModalFooter>
            </Modal>
          </div>
          </Fragment>
        );
    }

    componentDidMount(){
        console.log('componentDidMount() - MapBox.js');

        const {data} = this.props;
        // App.js의 모든 위치 정보(state)에 대한 Marker와 Infowindow 객체를 저장하는 Array 생성
            data.map(locationInfo => {
                if(!this.checkDuplicationLocationMarker(locationInfo.location.lat, locationInfo.location.lng)){
                    
                    // Marker 객체  생성
                    const marker = new window.naver.maps.Marker({
                        position: new window.naver.maps.LatLng(locationInfo.location.lat, locationInfo.location.lng), 
                        map: this.mapRef.instance, 
                        animation: window.naver.maps.Animation.BOUNCE, 
                        icon: {
                            url: locationInfo.uri, 
                            scaledSize: new window.naver.maps.Size(60, 60)
                        }
                    });

                    /*
                    // Infoview 객체 생성
                    const infowindow = new window.naver.maps.InfoWindow({
                        content: this.getInfowindowContentElement(locationInfo.uri, locationInfo.tags)
                    });
                

        */

                    // class의 markerAndInfowindowList 객체에 마커, 이미지 링크 정보 삽입
                     this.markerAndImageUriList.push({
                        marker: marker, 
                        imageUriList: [{id: locationInfo.id, uri: locationInfo.uri}]
                    });

                }else{
                    for(let index = 0; index < this.markerAndImageUriList.length; index++){
                        const markerPosition = this.markerAndImageUriList[index].marker.getPosition();
                    
                        if(markerPosition.lat() === locationInfo.location.lat && markerPosition.lng() === locationInfo.location.lng){
                            this.markerAndImageUriList[index].imageUriList.push({id: locationInfo.id, uri: locationInfo.uri});
                            break;
                        }
                    }
                }    
                            
            });

            console.log(this.markerAndImageUriList);
            this.addOnClickListenerToMarkers();
        
        }


}




export default MapBox;