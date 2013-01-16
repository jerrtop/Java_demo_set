package com.fsj.spring.web;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsj.spring.model.TDept;
import com.fsj.spring.service.IDeptService;

@Controller
@RequestMapping("/dept")
public class DeptController {


	@RequestMapping(value="/queryAll")
	@ResponseBody         
	public Map<String, Object> queryAll() throws Exception{
	    return deptService.getAllJson(); //可以返回map
	}
	
	@RequestMapping(value="/allList")
	@ResponseBody         
	public List<TDept> allList() throws Exception{
	    return deptService.getDeptList(); //也可以返回list
	}
	
	
	private IDeptService deptService;
	
	public IDeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(IDeptService deptService) {
		this.deptService = deptService;
	}

}
