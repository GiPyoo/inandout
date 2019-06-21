import React from 'react';
import {Link} from 'react-router-dom';

function NavBar({userId}){

    // 로그아웃
    const doSignOut = () => {
        alert('로그아웃 되셨습니다.');

        // sessionStorage에서 userId 정보 삭제
        sessionStorage.removeItem('userId');
        window.location.href = '/';
    };
    
    return (
        <nav className={"navbar navbar-expand-lg navbar-dark bg-dark"}>
            <a className={"navbar-brand"} href={"/"}>NaverMap</a>
            <div className={"collapse navbar-collapse"}>
            <form className={"form-inline my-2 my-lg-0 mr-auto"}>
                    <input className={"form-control mr-sm-2"} type={"search"} placeholder={"Search"} />
                    <button className={"btn btn-outline-success my-2 my-sm-0"} type={"submit"}>Search</button>
            </form>
            <form className={"form-inline my-2 my-lg-0"}>
                <span className={"text-white mr-2"}><span>{userId}</span>님 환영합니다</span>
                <Link className={"btn btn-outline-warning my-2 my-sm-0"} to="/imageStore" id={"btn-imageStore"}>이미지 등록</Link>

                {
                    // 로그인 버튼의 경우, 조건부 렌더링을 통해 사용자가 로그인 하지 않았을때만 출력되도록
                    userId === '' && (<Link className={"btn btn-outline-success my-2 my-sm-0"} to="/signIn" id={"btn-signIn"}>로그인</Link>)
                }
                

                {
                    // 로그아웃 버튼의 경우, 조건부 랜더링을 통해 사용자가 로그인 했을때만 출력되도록
                    userId !== '' ? (<button className={"btn btn-outline-success my-2 my-sm-0"} id={"btn-signOut"} type={"button"} onClick={doSignOut}>로그아웃</button>) : <span></span>
                }
                
            </form>
        </div>
        </nav> 
    );
}

export default NavBar;