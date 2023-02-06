package com.keduit.bpro54.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/sample/")
public class SempleController {

	@GetMapping("/all")
	public void exAll() {
		log.info("위치 : SempleController exAll()");
	}
	
	@GetMapping("/member")
	public void exMember() {
		log.info("위치 : SempleController exMember()");
	}
	
	@GetMapping("/admin")
	public void exAdmin() {
		log.info("위치 : SempleController exAdmin()");
	}
	
	
	
}
