package com.fsj.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * <p>
 * 		加载jsp
 * </p>
 * @author Jerry
 * 微博/微信：九唐时光
 * Email:jerrtop@163.com
 */
@Controller
public class CommonController {
	@RequestMapping(method=RequestMethod.GET,value="{name}")
	public String execute(@PathVariable("name") String name,Model model) throws Exception{
		if(name.equals("top")){
			model.addAttribute("systemTime",System.currentTimeMillis());
		}
		return name;
	}
}
