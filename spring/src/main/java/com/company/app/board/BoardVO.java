package com.company.app.board;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardVO {
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private int cnt;
	private String uploadFilename;
	private String orderby;
	private int[] seqList;
}
