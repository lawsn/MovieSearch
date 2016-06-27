package lawsn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lawsn.domain.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {

}