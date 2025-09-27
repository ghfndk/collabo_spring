package com.coffee.service;

import com.coffee.constant.Role;
import com.coffee.entity.Member;
import com.coffee.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service // 서비스 역할을 하며, 주로 로직 처리에 활용되는 자바 클래스입니다.
@RequiredArgsConstructor // final 키워드 또는 @NotNull 필드가 들어있는 식별자에 생성자를 통하여 값을 외부에서 주입해줍니다.
public class MemberService {
    private  final MemberRepository memberRepository;

    public Member findByEmail(String email){
        return memberRepository.findByEmail(email);
    }

    public void insert(Member bean){
        // '사용자 역할'과 '등록일자'는 여기서 넣어 줍니다.
        bean.setRole(Role.USER);
        bean.setRegdate(LocalDate.now());

        // 주의) Repository에서 insert 작업은 save() 메소드를 사용합니다.
        memberRepository.save(bean);
    }

    public Optional<Member> findMemberById(long memberId) {
        return this.memberRepository.findById(memberId);
    }
}
