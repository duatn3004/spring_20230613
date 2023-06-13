package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.repository.CommentDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
	@Inject
	private CommentDAO cdao;

	@Override
	public int register(CommentVO cvo) {
		log.info("insert service 진입~!!!");
		return cdao.insert(cvo);
	}

	@Override
	public List<CommentVO> getList(int bno) {
		log.info("getList service 진입~!!!");
		return cdao.getList(bno);
	}


	@Override
	public int edit(CommentVO cvo) {
		log.info("edit service 진입~!!!");
		return cdao.edit(cvo);
	}


	@Override
	public int remove(int cno) {
		log.info("remove service 진입~!!!");
		return cdao.remove(cno);
	}
}
