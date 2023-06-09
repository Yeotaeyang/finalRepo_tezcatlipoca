package com.trillon.camp.members.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trillon.camp.members.dto.Member;
import com.trillon.camp.members.repository.MemberRepository;
import com.trillon.camp.members.validator.form.SignUpForm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public boolean existUser(String userId) {
		Member member = memberRepository.selectMemberByUserId(userId);
		return member != null;
	}

	@Override
	public void insertNewMember(SignUpForm form) {
		form.setPassword(passwordEncoder.encode(form.getPassword()));
		memberRepository.insertMember(form);
	}

	

	@Override
	public Member authenticateUser(Member rowMember) {
		
		Member member = memberRepository.selectMemberByUserId(rowMember.getUserId());
		
		if(member == null) return null;
		
		return member;
	}

}