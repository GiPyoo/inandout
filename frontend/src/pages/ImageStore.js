import React, {Component} from 'react';
import axios from 'axios';

class ImageStore extends Component {

    doImageStore = async (event) => {
        event.preventDefault();
    
        const requestFormData = new FormData();
        requestFormData.append('fileName', document.getElementById('storeFileName').value);
        requestFormData.append('file', document.getElementById('storeFile').files[0]);
        requestFormData.append('xIndex', document.getElementById('storeLongitude').value);
        requestFormData.append('yIndex', document.getElementById('storeLatitude').value);

        const imageStoreResponse = await axios({
                                        method: 'post', 
                                        url: 'http://106.10.56.117:8080/picture-on-map/v1/images', 
                                        data: requestFormData, 
                                        headers: {'Content-Type': 'multipart/form-data'}
                                        });
        
        if(imageStoreResponse.status === 201){
            alert('이미지 등록 성공');
            this.props.history.push("/");
        }else{
            alert('이미지 등록 실패');
        }
        
    };

    
    render(){
        return (
            <div className="container mt-5">
                <h1>이미지 등록</h1>
                <form className="mt-5">
                    <div className="form-group">
                        <label htmlFor="storeFileName">파일명</label>
                        <input type="email" className="form-control" id="storeFileName" aria-describedby="storeFileNameHelp" placeholder="파일명" autoComplete="on" required autoFocus />
                        <small id="storeFileNameHelp" className="form-text text-muted">파일명은 필수 입력사항 입니다.</small>
                    </div>
                    <div className="form-group">
                        <label htmlFor="storeFile">파일</label>
                        <input type="file" className="form-control" id="storeFile" aria-describedby="storeFileHelp" placeholder="파일" autoComplete="on" required autoFocus />
                        <small id="storeFileHelp" className="form-text text-muted">파일은 필수 입력사항 입니다.</small>
                    </div>
                    <div className="form-group">
                        <label htmlFor="storeLatitude">위도(latitude)</label>
                        <input type="text" className="form-control" id="storeLatitude" aria-describedby="storeLatitudeHelp" placeholder="위도(latitude)" autoComplete="on" required autoFocus />
                        <small id="storeLatitudeHelp" className="form-text text-muted">위도는 필수 입력사항 입니다.</small>
                    </div>
                    <div className="form-group">
                        <label htmlFor="storeLongitude">경도(longitude)</label>
                        <input type="text" className="form-control" id="storeLongitude" aria-describedby="storeLongitudeHelp" placeholder="경도(longitude)" autoComplete="on" required autoFocus />
                        <small id="storeLongitudeHelp" className="form-text text-muted">경도는 필수 입력사항 입니다.</small>
                    </div>
                    <button type="submit" className="btn btn-dark" id="btn-imageStore" onClick={this.doImageStore}>저장</button>
                </form> 
                </div>
        );
    }
}

export default ImageStore;