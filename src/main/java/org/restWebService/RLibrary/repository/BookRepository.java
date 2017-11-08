package org.restWebService.RLibrary.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.restWebService.RLibrary.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {

	
	@Query("SELECT b FROM Book b ORDER BY b.title ASC")
	public List<Book> findAllByTitleAsc();

	@Query("SELECT b FROM Book b WHERE b.bookType.id = ?1")
	public List<Book> findByIdBookType(Long idBookType);

}
