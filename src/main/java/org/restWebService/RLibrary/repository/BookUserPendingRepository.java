package org.restWebService.RLibrary.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.restWebService.RLibrary.domain.BookUserPending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface BookUserPendingRepository extends JpaRepository<BookUserPending, Long> {

	@Query("SELECT bup FROM BookUserPending bup WHERE bup.book.id = ?1")
	public List<BookUserPending> findByIdBook(Long idBook);

	@Query("SELECT bup FROM BookUserPending bup WHERE bup.rUser.id = ?1")
	public List<BookUserPending> findByIdUser(Long idRUser);

}
