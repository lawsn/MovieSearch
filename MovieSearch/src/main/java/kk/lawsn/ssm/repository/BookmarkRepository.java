package kk.lawsn.ssm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kk.lawsn.ssm.domain.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {

}