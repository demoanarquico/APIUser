package cl.bci.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cl.bci.user.entity.PhoneEntity;

@Repository
@Transactional
public interface PhoneRepository extends JpaRepository<PhoneEntity, Integer>  {
	
	List<PhoneEntity> findByiduser( Integer id );

}
