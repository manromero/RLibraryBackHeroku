package org.restWebService.RLibrary.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.restWebService.RLibrary.domain.BookType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface BookTypeRepository extends JpaRepository<BookType, Long> {

	@Query("SELECT bt FROM BookType bt ORDER BY bt.description ASC")
	public List<BookType> findAllByDescriptionAsc();

	@Query("SELECT bt FROM BookType bt WHERE bt.description = ?1 ")
	public BookType findByDescription(String description);

}
