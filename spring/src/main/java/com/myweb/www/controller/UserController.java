package com.myweb.www.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpServerErrorException;

import com.myweb.www.domain.UserVO;
import com.myweb.www.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 이거 넣으면 밑에 private static final Logger~ 안 적어도 됨
@RequestMapping("/member/*")
@Controller //컨트롤러의 기능을 할 class
public class UserController {
   
//   private static final Logger logger = LoggerFactory.getLogger(UserController.class);
   @Inject
   private UserService usv;
   
   @GetMapping("/signup")
   public String index(Model m) {//Model 객체가 setAttribute 역할 대신함
      log.info("home 접근 완료");
//      m.addAttribute("msg_home",1);
      return "/user/signup"; 
   }
   
   @PostMapping("/signup") //postMapping으로 온 signup  -----아직 DB에 추가한 건 아님=> mapper에서 
   public String signupPost(Model m, UserVO uvo) { //UserVO 추가해주기
      log.info("회원가입 접근 완료");
      log.info(uvo.toString());
      int isOk = usv.signUp(uvo);
      if(isOk>0) {
         m.addAttribute("msg_signup", 1);
      }else {
         m.addAttribute("msg_signup", 0);
      }
      return "home"; //결과 페이지
   }
   
   @GetMapping("/login")
   public  String loginGet() { //Model m을 파라미터 값으로 안 넣어도 됨 (안 쓸거면)
      return "/user/login";
   }
   
   @PostMapping("/login")
   public String loginPost(Model m, String id, String pw, HttpServletRequest request) {
      log.info(">>> id:"+id+", pw:"+pw);
      //파라미터로 받은 id, pw를 DB에 넘겨 일치하는 객체를 받음
      
      //DB에서 얻은 객체가 null이 아니라면 세션 연결 저장
      
      
      UserVO isUser = usv.isUser(id, pw);
//      log.info(">>> isUser " + isUser.toString()); //주석 풀면 에러남

      //DB에서 얻은 객체가 null이 아니라면 세션 연결 저장
      if(isUser != null) {
         HttpSession ses = request.getSession();
         ses.setAttribute("ses", isUser); //세션에 객체 담기
         ses.setMaxInactiveInterval(60*10); //로그인 유지 시간
         m.addAttribute("user",isUser); //모델이 request 객체 역할 해줌
         m.addAttribute("msg_login",1);
      }else {
         m.addAttribute("msg_login",0);
      }
      return "home";
   }
   
   @GetMapping("logout")
   public String logout(Model m, HttpServletRequest request) {
      request.getSession().removeAttribute("ses");
      request.getSession().invalidate(); //세션 연결 끊기 
      m.addAttribute("msg_logout", 1);
      return "home";
   }
   
   
   
   
   
}