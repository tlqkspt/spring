package com.company.app.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.company.app.board.service.BoardService;

@Component
public class Scheduler {

	@Autowired 
	BoardService boardService;
	
	@Scheduled(cron = "0 30-39 9 * * *")
	public void jobMethod3() {
		System.out.println(boardService.getBoardMap(null));
	}
	
	
	//@Scheduled(cron = "0/10 22-30 9 * * *")	//20,21,22	//   / 는 간격
	public void jobMethod1() {
		System.out.println("스케줄 실행");
	}
	
	//@Scheduled(fixedRate = 5000)	//20,21,22	//   / 는 간격
	public void jobMethod2() {
		System.out.println("fixedrate 5초마다 실행");
	}
	
	
}
