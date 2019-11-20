package com.safefood.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.safefood.dto.NoticeDTO;
import com.safefood.service.INoticeService;
import com.safefood.service.NoticeServiceImpl;

@Controller
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

	@ExceptionHandler(Exception.class)
	public String allException(Exception e, HttpServletRequest req) {
		req.setAttribute("message", e.getStackTrace());
		return "allErrorPage";
	}

	@Autowired
	@Qualifier("NoticeServiceImpl")
	INoticeService nSer;

	@RequestMapping("/noticeinsertpage")
	public String noticeInsertPage() {
		return "/notice/noticeinsert";
	}

	@RequestMapping("/sortrecent")
	private ModelAndView sortRecent(ModelAndView mv) {
		// TODO Auto-generated method stub
		ArrayList<NoticeDTO> list = nSer.listNotice();
		list.sort(new Comparator<NoticeDTO>() {

			@Override
			public int compare(NoticeDTO o1, NoticeDTO o2) {
				// TODO Auto-generated method stub
				int id1 = o1.getNid();
				int id2 = o2.getNid();

				return id2 - id1;
			}
		});
		mv.addObject("list", list);
		mv.addObject("sel", "recent");
		mv.setViewName("/notice/noticelist");
		return mv;
	}

	@RequestMapping("/sortview")
	private ModelAndView sortView(ModelAndView mv) {
		// TODO Auto-generated method stub
		ArrayList<NoticeDTO> list = nSer.listNotice();
		list.sort(new Comparator<NoticeDTO>() {

			@Override
			public int compare(NoticeDTO o1, NoticeDTO o2) {
				// TODO Auto-generated method stub
				int cnt1 = o1.getNcount();
				int cnt2 = o2.getNcount();

				return cnt2 - cnt1;
			}
		});
		System.out.println(list.toString());
		mv.addObject("list", list);
		mv.addObject("sel", "view");
		mv.setViewName("/notice/noticelist");
		return mv;
	}

	@RequestMapping("/sortregist")
	private ModelAndView sortRegist(ModelAndView mv) {
		// TODO Auto-generated method stub
		ArrayList<NoticeDTO> list = nSer.listNotice();
		list.sort(new Comparator<NoticeDTO>() {

			@Override
			public int compare(NoticeDTO o1, NoticeDTO o2) {
				// TODO Auto-generated method stub
				int id1 = o1.getNid();
				int id2 = o2.getNid();

				return id1 - id2;
			}
		});
		mv.addObject("sel", "reg");
		mv.addObject("list", list);
		mv.setViewName("/notice/noticelist");
		return mv;
	}

	@RequestMapping("/registernotice")
	private ModelAndView registerNotice(HttpServletRequest request, ModelAndView mv) {
		String ntitle = request.getParameter("ntitle");
		String ncontent = request.getParameter("ncontent");
		boolean res = nSer.registerNotice(ntitle, ncontent);
		if (res) {
			mv.setViewName("redirect:listnotice");
		}
		return mv;
	}

	@RequestMapping("/listnotice")
	private ModelAndView listNotice(ModelAndView mv) {
		ArrayList<NoticeDTO> list = nSer.listNotice();
		mv.addObject("list", list);
		mv.setViewName("/notice/noticelist");
		// System.out.println("공지사항 리스트");
		return mv;
	}

	@RequestMapping("/infonotice")
	private ModelAndView infoNotice(@RequestParam("nid") int nid, ModelAndView mv) {
		// System.out.println(nid+"ㅡㅡㅡㅡㅡㅡㅡ");
		boolean res = nSer.countUp(nid);
		NoticeDTO notice = nSer.infoNotice(nid);
		mv.addObject("notice", notice);
		mv.setViewName("/notice/noticeview");
		return mv;
	}

	@RequestMapping("/updateviewnotice")
	private ModelAndView updateView(@RequestParam("nid") int nid, @RequestParam("ntitle") String ntitle,
			@RequestParam("ncontent") String ncontent, ModelAndView mv) {
		NoticeDTO m = new NoticeDTO(nid, ntitle, ncontent);
		mv.addObject("notice", m);
		
		System.out.println("update하러간다.");
		mv.setViewName("/notice/noticeupdate");

		return mv;
	}

	@RequestMapping("/updatenotice")
	private ModelAndView updateNotice(@RequestParam("nid") int nid, @RequestParam("ntitle") String ntitle,
			@RequestParam("ncontent") String ncontent, ModelAndView mv) {
		boolean res = nSer.updateNotice(nid, ntitle, ncontent);
		if (res) {
			mv.setViewName("redirect:listnotice");
		} else {
			System.out.println("updatenotice 에러");
		}

		return mv;

	}

	@RequestMapping("/deletenotice")
	private ModelAndView deleteNotice(@RequestParam("nid") int nid, ModelAndView mv) {
		System.out.println("deltenotice  " + nid);
		boolean res = nSer.deleteNotice(nid);
		if (res) {
			mv.setViewName("redirect:listnotice");
			System.out.println("제거완료");
		}

		return mv;
	}

	@RequestMapping("/searchnotice")
	private void searchNotice(HttpServletRequest request, ModelAndView mv) {
		String keyword = request.getParameter("keyword");
		ArrayList<NoticeDTO> list = nSer.searchNotice(keyword);
		request.setAttribute("list", list);
		mv.setViewName("redirect:listnotice");
	}

}
