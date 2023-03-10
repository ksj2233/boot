package com.keduit.bpro02.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.keduit.bpro02.dto.SampleDTO;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping({"/sample","/layout"})
@Log4j2
public class SampleController {

	@GetMapping("ex1")
	public void ex1() {
		log.info("ex1..............");
	}

	@GetMapping({ "/ex2" })
	public void exModel(Model model) {

		List<SampleDTO> list = IntStream.range(1, 20)
						.asLongStream().mapToObj(i -> {
							SampleDTO dto = SampleDTO
						.builder()
						.sno(i)
						.first("First...." + i)
						.last("Last....." + i)
						.regTime(LocalDateTime.now()).build();
			return dto;
		}).collect(Collectors.toList());
		model.addAttribute("list", list);
	}

	@GetMapping("/exinline")
	public String exInline(RedirectAttributes redirectAttributes) {
		
		log.info("exInline..............................................");
		SampleDTO dto = SampleDTO.builder()
				.sno(10L)
				.first("First ............. 10")
				.last("Last ............. 10")
				.regTime(LocalDateTime.now())
				.build();
		
		redirectAttributes.addFlashAttribute("result","succes");
		redirectAttributes.addFlashAttribute("dto", dto);
		
		return "redirect:/sample/ex3";
	}
	
	@GetMapping("/ex3")
	public void ex3() {
		log.info("ex3...............................");
	}
	
	@GetMapping("/exlink")
	public void exlink() {
		log.info("exlink........................");
	}
	
	@GetMapping({"/exlayout1","/exLayout2","/layout1","/exsidebar"})
	public void exlayout1() {
		log.info("layout");
	}
	
}
