package org.restWebService.RLibrary.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.restWebService.RLibrary.domain.BookUserReaded;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface BookUserReadedRepository extends JpaRepository<BookUserReaded, Long> {

	@Query("SELECT bur FROM BookUserReaded bur WHERE bur.book.id = ?1")
	public List<BookUserReaded> findByIdBook(Long idBook);

	@Query("SELECT bur FROM BookUserReaded bur WHERE bur.rUser.id = ?1")
	public List<BookUserReaded> findByIdUser(Long idRUser);

}
