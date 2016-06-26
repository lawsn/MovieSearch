package kk.lawsn.ssm.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import kk.lawsn.ssm.domain.Bookmark;
import kk.lawsn.ssm.repository.BookmarkRepository;

@Service
@Transactional
public class BookmarkService {
	
	private String[] sortFields = {
			
	};

	@Resource
	private BookmarkRepository bookmarkRepository;
	
	public Page<Bookmark> findAll(int result, int pageno, Direction direction, String sortField) {
		Pageable pageable = new PageRequest(result, pageno, direction, sortField);
		return this.bookmarkRepository.findAll(pageable);
	}
	
	public Bookmark findOne(int seq) {
		return this.bookmarkRepository.findOne(seq);
	}
	
	public Bookmark crate(Bookmark bookmark) {
		return this.bookmarkRepository.save(bookmark);
	}
	
	public void delete(int seq) {
		this.bookmarkRepository.delete(seq);
	}
}
