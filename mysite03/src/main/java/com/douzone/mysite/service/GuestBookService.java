package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVo;

@Service
public class GuestBookService {
	
	@Autowired
	private GuestBookRepository guestBookRepository;
	
	public List<GuestBookVo> guestBookList() {
		
		return guestBookRepository.findAll();
	}

	public void add(GuestBookVo vo) {
		
		guestBookRepository.insert(vo);
	}

	public void delete(GuestBookVo vo) {
		
		 guestBookRepository.delete(vo);
	}

	public List<GuestBookVo> getMessageList(Long startNo) {
	
		
		return guestBookRepository.findAll(startNo);
	}

	public Boolean write(GuestBookVo vo) {
		
		
		return 1 == guestBookRepository.delete(vo);
	}

}
