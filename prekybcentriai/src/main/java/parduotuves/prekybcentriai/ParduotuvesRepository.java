package parduotuves.prekybcentriai;

import org.springframework.data.repository.CrudRepository;

public interface ParduotuvesRepository extends CrudRepository<Parduotuves, Integer> {	
	
	Iterable<Parduotuves> findByIdRajono(Integer id_rajono);

}