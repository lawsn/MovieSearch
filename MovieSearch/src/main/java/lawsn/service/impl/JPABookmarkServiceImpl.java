package lawsn.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lawsn.domain.Bookmark;
import lawsn.repository.BookmarkRepository;
import lawsn.service.BookmarkService;

/**
 * 북마크를 관리하는 서비스 구현 클래스
 *
 * @version 1.0
 * @author 오범석
 */
@Service("bookmarkService")
@Transactional
public class JPABookmarkServiceImpl implements BookmarkService {

	@Resource
	private BookmarkRepository bookmarkRepository;
	
	@Override
	public Page<Bookmark> findAll(int result, int pageno, Direction direction, String sortField) {
		Pageable pageable = new PageRequest(result, pageno, direction, sortField);
		return this.bookmarkRepository.findAll(pageable);
	}
	
	@Override
	public Bookmark findOne(int seq) {
		return this.bookmarkRepository.findOne(seq);
	}
	
	@Override
	public Bookmark crate(Bookmark bookmark) {
		return this.bookmarkRepository.save(bookmark);
	}
	
	@Override
	public void delete(int seq) {
		this.bookmarkRepository.delete(seq);
	}
}
