package com.fsj.spring.web;

import org.springframework.stereotype.Controller;
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
	@RequestMapping(value="{name}",method={RequestMethod.POST, RequestMethod.GET})
	public String execute(@PathVariable("name") String name) throws Exception{
		return name;
	}
}
