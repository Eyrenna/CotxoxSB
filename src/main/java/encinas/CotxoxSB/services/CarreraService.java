package encinas.CotxoxSB.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import encinas.CotxoxSB.domain.Carrera;
import encinas.CotxoxSB.repository.CarreraRepo;

@Service
public class CarreraService {
	
	@Autowired
	private CarreraRepo repo;

	public Long crearCarrera(String tarjetaCredito, String origen, String destino) {
		Carrera carrera = new Carrera(tarjetaCredito, origen, destino);
		repo.save(carrera);
		return carrera.getId();
	}

	public Carrera recuperaCarrera(Long idCarrera) {
		return repo.findById(idCarrera).get();
	}
	
	
	

}
