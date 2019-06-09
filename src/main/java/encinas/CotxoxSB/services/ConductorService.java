package encinas.CotxoxSB.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import encinas.CotxoxSB.domain.Conductor;
import encinas.CotxoxSB.repository.ConductorRepo;

@Service
public class ConductorService {
	
	@Autowired
	private ConductorRepo repo;

	public Conductor recuperarConductor(String tarjetaCredito) {
		return repo.recuperaConductor(tarjetaCredito);
	}

}
