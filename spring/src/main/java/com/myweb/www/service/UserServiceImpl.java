package com.myweb.www.service;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myweb.www.domain.UserVO;
import com.myweb.www.repository.UserDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
   @Inject
   private UserDAO udao;
   @Inject //선언만해도 사용할 수 있게 해주는 어노테이션(@)
   BCryptPasswordEncoder passwordencoder;

   @Override
   public int signUp(UserVO uvo) {
      log.info(">>> signUp service in");
      //아이디가 중복되면 회원가입 실패
      //아이디를 주고 DB에서 일치하는 유저를 달라고 요청
      //일치하는 유저가 없으면 가입(1리턴), 있으면 (0리턴)
      UserVO tempUser = udao.getUser(uvo.getId());
      //tempUser가 null이 아니라면 이미 가입된 회원 => 아이디중복 => 회원가입 실패
      if(tempUser != null) {
         return 0;
      }
      //id가 중복되지 않았다면 회원가입 진행~!!
      //password가 null이거나 값이 없으면 가입불가
      if(uvo.getId() == null || uvo.getId().length()==0) {
         return 0;
      }
      if(uvo.getPw() == null || uvo.getPw().length()==0) {
         return 0;
      }
      //null이 아니면 회원가입 진행
      String pw = uvo.getPw();
      //encode(암호화) / matches(원래 비번, 암호화된 비번
      String encodePw = passwordencoder.encode(pw); //기존 패스워드 암호화
      //uvo의 패스워드를 암호화된 패스워드로 수정
      uvo.setPw(encodePw);
      //회원가입 => insert
      int isOk = udao.insertUser(uvo); //실제 리턴
      return isOk;
      
   }

   @Override
   public UserVO isUser(String id, String pw) {
      log.info(">>> login Service in");
      //id를 주고 해당 id에 일치하는 객체를 DB에서 호출
      UserVO user = udao.getUser(id); //id 주고 user 달라고 하면 mapper까지 가지 않아도 전에 쓴 걸로 활용 가능 - 앞에서 했던 메서드 호출
      //가져온 user의 비번과 입력받은 비번이 같은지 확인
      //passwordencoder.mathces(원래비번, 암호화된 비번) : 매치가 된다면 true
      if(user == null) { return null; } //user가 없을 경우(id도 일치하는 게 없을 경우)
      
      if(passwordencoder.matches(pw, user.getPw())) {
         return user;
      }else {
         return null;
      }
      
   }


}