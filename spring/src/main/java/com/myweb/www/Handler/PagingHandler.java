package com.myweb.www.Handler;

import com.myweb.www.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Getter
@Setter
public class PagingHandler {
	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int totalCount;
	private PagingVO pgvo;
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		this.endPage = 
				(int)(Math.ceil(pgvo.getPageNO() / (pgvo.getQty()*1.0))*pgvo.getQty());
		this.startPage = this.endPage - 9;
		int realEndPage = (int)(Math.ceil((totalCount*1.0) / pgvo.getQty()));
		
		if(realEndPage < this.endPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = this.startPage > 1; //true / false
		this.next = this.endPage < realEndPage; //true / false
		log.info(">>>endPage : "+endPage);
		log.info(">>>startPage : "+startPage);
	}
}
