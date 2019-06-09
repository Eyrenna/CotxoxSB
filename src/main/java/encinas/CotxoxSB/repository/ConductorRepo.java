package encinas.CotxoxSB.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import encinas.CotxoxSB.domain.Conductor;

public interface ConductorRepo extends CrudRepository<Conductor, String> {

	@Query("select conductor from Conductor conductor, Carrera carrera where carrera.tarjetaCredito=?1 and carrera.conductor = conductor.nombre")
	public Conductor recuperaConductor(String tarjetaCredito);

}
