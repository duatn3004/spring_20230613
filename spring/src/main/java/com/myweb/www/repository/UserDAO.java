package com.myweb.www.repository;

import com.myweb.www.domain.UserVO;

public interface UserDAO {

   UserVO getUser(String id); //getUser가 Mapper에서 id임 => id를 주고 객체를 받아옴

   int insertUser(UserVO uvo);

}