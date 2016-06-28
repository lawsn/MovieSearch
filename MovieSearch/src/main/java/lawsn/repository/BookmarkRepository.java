package lawsn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lawsn.domain.Bookmark;

/**
 * JpaRepository를 상속받아 북마크 DB를 관리하는 인터페이스
 *
 * @version 1.0
 * @author 오범석
 */
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {

}