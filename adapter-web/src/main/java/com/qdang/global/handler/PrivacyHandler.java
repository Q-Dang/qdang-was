package com.qdang.global.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PrivacyHandler {

	@RequestMapping("privacy")
	public String privacy() {
		return "privacy";
	}
}
