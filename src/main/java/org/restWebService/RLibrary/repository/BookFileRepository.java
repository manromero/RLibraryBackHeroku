package org.restWebService.RLibrary.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.restWebService.RLibrary.domain.BookFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface BookFileRepository extends JpaRepository<BookFile, Long> {

	@Query("SELECT bf FROM BookFile bf WHERE bf.book.id = ?1")
	public List<BookFile> findByIdBook(Long idBook);

}
