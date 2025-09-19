package com.coffee.controller;

import com.coffee.entity.Member;
import com.coffee.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController // 해당 클래스는 웹 요청(form react)을 접수하여 처리해주는 컨트로럴 클래스입니다.
@RequiredArgsConstructor // final 키워드 또는 @NotNull 필드가 들어있는 식별자에 생성자를 통하여 값을 외부에서 주입해줍니다
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/member/signup")
    public ResponseEntity<?> signup(@RequestBody Member bean){ // 회원가입을 위한 컨트롤러 메소드 -- bean은 유의미한 데이터를 뜻함
        // ResponseEntity : Http 응답 코드(숫자 형식)나, 적절한 메시지 등을 표현하기 위한 클래스
        // JSON : JavaScript Object Notation
        // @RequestBody : JSON 형태의 문자열을 자바의 객체 타입으로 변환해 줍니다.
        System.out.println(bean);

        // 입력된 이메일을 이용하여 중복 여부 체크를 실행합니다.
        Member member = memberService.findByEmail(bean.getEmail());

        if(member != null){// 이미 존재하는 email
            return new ResponseEntity<>(Map.of("email","이미 존재하는 이메일 주소입니다"), HttpStatus.BAD_REQUEST);

        }else{ // 회원 가입 처리
            memberService.insert(bean);
            return new ResponseEntity<>("회원 가입 성공", HttpStatus.OK);
        }
    }
}
