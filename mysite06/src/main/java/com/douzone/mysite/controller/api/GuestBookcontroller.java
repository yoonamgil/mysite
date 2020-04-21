package com.douzone.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestBookService;
import com.douzone.mysite.vo.GuestBookVo;

@RestController("GuestBookApiController")
@RequestMapping("/api/guestbook")
public class GuestBookcontroller {
	
	@Autowired
	private GuestBookService guestbookService;
	
	
	@GetMapping("/list/{no}")
	public JsonResult list(@PathVariable("no") Long startNo) {
		List<GuestBookVo> list =guestbookService.getMessageList(startNo);
		
		return JsonResult.success(list);
	}
	
	

	@PostMapping("/add")
	public JsonResult add(@RequestBody GuestBookVo vo) {
		System.out.println(vo);
		
		guestbookService.add(vo);
		vo.setPassword("");
		return JsonResult.success(vo);
	}
	
	@DeleteMapping("/delete/{no}")
	public JsonResult delete(@PathVariable("no") Long no,
							@RequestParam(value="password",required=true, defaultValue="") String password ) {
		System.out.println(no);
		System.out.println(password);
		GuestBookVo vo = new GuestBookVo();
		vo.setNo(no);
		vo.setPassword(password);
		
		boolean result = guestbookService.write(vo);
		
		System.out.println(result);
		
		
		return JsonResult.success(result ? no : -1);
	}
	
	
}
