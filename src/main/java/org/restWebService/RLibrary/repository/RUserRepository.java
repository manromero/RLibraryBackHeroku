package org.restWebService.RLibrary.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.restWebService.RLibrary.domain.RUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface RUserRepository extends JpaRepository<RUser, Long> {

	@Query("SELECT u FROM RUser u ORDER BY u.alias ASC")
	public List<RUser> findAllOrderByAliasAsc();

}
