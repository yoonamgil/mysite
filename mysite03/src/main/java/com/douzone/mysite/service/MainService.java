package com.douzone.mysite.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.douzone.mysite.repository.MainRepository;
import com.douzone.mysite.vo.SiteVo;

@Service
public class MainService {

	@Autowired
	private MainRepository mainRepository;
	
	public SiteVo findByAll() {
		
		return mainRepository.findByAll();
	}
	
	
	
	
	
}
