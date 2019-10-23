package com.company.app.board;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(value={"uploadFilename","orderby","seqList","msg"})
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
	private String msg;
}
