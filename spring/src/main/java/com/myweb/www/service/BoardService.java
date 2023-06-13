package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.UserVO;

public interface BoardService {

	int register(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO getDetail(int bno);

//   int modify(BoardVO bvo);

	int readCount(int bno);

	int modify(BoardVO bvo, UserVO user);

	int remove(int bno, UserVO user);

	List<BoardVO> getList(PagingVO pvo);

	int getTotalCount();

}