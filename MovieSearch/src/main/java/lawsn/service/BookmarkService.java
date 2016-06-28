package lawsn.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;

import lawsn.domain.Bookmark;

/**
 * 북마크를 관리하는 서비스 인터페이스
 *
 * @version 1.0
 * @author 오범석
 */
public interface BookmarkService {

	/**
	 * 북마크 목록 검색
	 * 
	 * @param result 페이지당 검색건수
	 * @param pageno 검색할 페이지번호
	 * @param direction 정렬순서
	 * @param sortField 정렬필드
	 * @return 페이징처리된 북마크 정보
	 */
	public Page<Bookmark> findAll(int result, int pageno, Direction direction, String sortField);
	
	/**
	 * 북마크 조회 by PK
	 * 
	 * @param seq 북마크 PK
	 * @return 북마크 정보
	 */
	public Bookmark findOne(int seq);
	
	/**
	 * 북마크 등록
	 * 
	 * @param bookmark 북마크 정보
	 * @return 등록된 북마크 정보
	 */
	public Bookmark crate(Bookmark bookmark);
	
	/**
	 * 북마크 삭제 by PK
	 * 
	 * @param seq 북마크 PK
	 */
	public void delete(int seq);

}
