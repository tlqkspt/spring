package com.company.app.board;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

//@Data
//@JsonIgnoreProperties(value={"uploadFilename","orderby","seqList","msg"})
//public class BoardVO {
//	private int seq;
//	private String title;
//	private String writer;
//	private String content;
//	private Date regDate;
//	private int cnt;
//	
//	private String uploadFilename;
//	private String orderby;
//	private int[] seqList;
//	private String msg;
////	private MultipartFile uploadFile;
//	private String boardType;
//}

@Data
@JsonIgnoreProperties(value={"uploadFilename","orderby","seqList","msg"})
@Entity		//영속 객체(테이블에 매핑)
@Table(name="BOARD")	// p.533	생략하면 클래스명과 동일하게 생성됨
public class BoardVO {
	@Id	//프라이머리키
	@GeneratedValue	//시퀀스사용
	private int seq;
	
	@Column(nullable = false)
	private String title;
	
	@Column(updatable = false, length = 30)
	private String writer;
	
	@Column(name="conntents", length = 2000)
	private String content;
	
	@Temporal(TemporalType.DATE)
	private Date regDate = new Date();	//유틸 데이트사용
	private int cnt;
	
	// @Transient 안만들어지게함 
	private String uploadFilename;
	@Transient private String orderby;
	@Transient private int[] seqList;
	@Transient private String msg;
//	private MultipartFile uploadFile;
	@Transient private String boardType;
}

