package encinas.CotxoxSB;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.Repository;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import encinas.CotxoxSB.domain.Carrera;
import encinas.CotxoxSB.domain.Conductor;
import encinas.CotxoxSB.repository.CarreraRepo;
import encinas.CotxoxSB.repository.ConductorRepo;
import encinas.CotxoxSB.services.CarreraService;
import encinas.CotxoxSB.services.ConductorService;


@RunWith(SpringRunner.class)
@SpringBootTest

/**
 * Prepara la base de dades per a que:
 * 
 *  En arrancar l'aplicacio no realitzi cap acció 
 * 	sobre l'esquema de base de dades.
 */

@Sql(statements = {
		"delete from t_carreras",
		"delete from t_conductores",
		"insert into t_conductores (co_nombre, co_modelo, co_matricula, co_valoracion_media, co_ocupado) values ('Samantha', 'Chevy Malibu', '4ABC123', 0, 1)",
		"insert into t_carreras (c_id,c_tarjeta_credito, c_origen, c_destino, c_distancia, c_tiempo_esperado, c_tiempo_carrera, c_coste_total, c_propina, c_conductor) values (1,'4916119711304546', 'Aeroport Son Sant Joan', 'Magaluf', 7.75, 10, 0, 0, 0, 'Samantha')"
})

public class CotxoxAppTests {

	@Autowired(required=false)
	CarreraRepo carreraRepo;

	@Autowired(required=false)
	CarreraService carreraService;

	@Autowired(required=false)
	ConductorRepo conductorRepo;

	@Autowired(required=false)
	ConductorService conductorService;

	@PersistenceContext
	private EntityManager em;

	/** 
	 * TDD 
	 */

	/** 
	 * Mapea la classe conductor per a reflecteixi 
	 * l'esquema de la base de dades 
	 */

	@Test
	public void test_mapping_conductor() {
		Conductor conductor = em.find(Conductor.class, "Samantha");
		Assert.assertNotNull(conductor);
		Assert.assertEquals("4ABC123", conductor.getMatricula());
		Assert.assertEquals("Chevy Malibu", conductor.getModelo());
	}

	/** 
	 * Mapea la classe carrera per a reflecteixi l'esquema de la base de dades 
	 */

	@Test
	public void test_mapping_carrera() {
		Carrera carrera = em.find(Carrera.class, 1L);
		Assert.assertNotNull(carrera);
		Assert.assertEquals("4916119711304546", carrera.getTarjetaCredito());
		Assert.assertEquals("Samantha", carrera.getConductor().getNombre());
	}

	/**
	 * Crea una classe CarreraRepo que sigui un repositori Spring Data
	 * per l'entitat Carrera
	 */
	@Test
	public void test_RepoCarerra_es_repositori() {
		Assert.assertNotNull(carreraRepo);
		Assert.assertTrue(carreraRepo instanceof Repository);
	}

	/**
	 * Crea una classe CarreraService que sigui un component
	 * amb el rol de Service
	 */
	@Test
	public void test_carreraService_es_component() {
		Assert.assertNotNull(carreraService);
	}

	/**
	 * Utilitza els mètodes del repositori de carrera
	 * i del servei carrera per a fer persistent una carrera
	 */

	@Test
	public void test_save_carrera() {
		Long idCarrera = carreraService.crearCarrera("1234567890123456", "Parc de Ses Estacions", "Festival Park");
		// seria necessari afegir el conductor però anem a testear primer repo
		Assert.assertEquals("1234567890123456", carreraService.recuperaCarrera(idCarrera).getTarjetaCredito());
	}

	/**
	 * Crea una classe ConductorRepo que sigui un repositori Spring Data
	 * per l'entitat Conductor
	 */
	@Test
	public void test_ConductorRepo_es_repositori() {
		Assert.assertNotNull(conductorRepo);
		Assert.assertTrue(conductorRepo instanceof Repository);
	}

	/**
	 * Implementa el servei de l'entitat conductor i el seu repositori
	 * per a recuperar un conductor per la seva targeta de crèdit.
	 */

	@Test
	public void test_recuperar_conductor() {
		Conductor conductor = conductorService.recuperarConductor("4916119711304546");
		Assert.assertNotNull(conductor);
		Assert.assertEquals("Samantha", conductor.getNombre());
	}

//	/**
//	 * Completa el codi del cas test test_save_conductor()
//	 * per a afegir les conductores següents a la BBDD
//	 * mitjançant el servei de l'entitat conductor:
//	 *
//	 * String[] nombres = {"Sabrina", "Cici"};
//	 * String[] matricula = {"5DHJ444", "7JKK555"};
//	 * String[] modelos = {"Toyota Prius", "Mercedes A"};
//	 */
//
//	@Test
//	public void test_save_conductor() {
//
//		/**
//		 * Escriu aqui el codi per a crear les conductores
//		 * i escriure-les a la base de dades
//		 */
//        conductorService.crearConductor("2222222222222222","Sabrina", "5DHJ444","Toyota Prius");
//        conductorService.crearConductor("3333333333333333","Cici", "7JKK555","Mercedes A");
//
//        Assert.assertEquals("Sabrina", conductorService.recuperarConductor("2222222222222222").getNombre());
//		Assert.assertEquals("Cici", conductorService.recuperarConductor("3333333333333333").getNombre());
//
//	}
//
//	/**
//	 * Modifica l'atribut ocupat de l'entitat Conductor i la lògica
//	 * del mètodes setOcupado() i isOcupado()
//	 * per a adaptar-lo al TINYINT de MySQL
//	 */
//
//	@Test
//	public void test_BooleanOcupado_adaptado_a_SQL() {
//		Conductor conductora = conductorService.recuperarConductor("1111111111111111");
//		Assert.assertEquals("Samantha", conductora.getNombre());
//		Assert.assertEquals(false, conductora.isOcupado());
//		conductora.setOcupado(true);
//		Assert.assertEquals(true, conductora.isOcupado());
//	}
//
//	/**
//	 * Modifica el servei de l'entitat conductor amb un mètode init() per a inserir
//	 * a la base de dades les conductores següents, totes dues desocupades:
//	 * String[] nombres = {"Sabrina", "Cici"};
//	 * String[] matricula = {"5DHJ444", "7JKK555"};
//	 * String[] modelos = {"Toyota Prius", "Mercedes A"}
//	 */
//
//	 @Test
//	 public void test_post_construct_servei_conductor() {
//
//		conductorService.init();
//
//		Assert.assertEquals("Sabrina", conductorService.recuperarConductor("2222222222222222").getNombre());
//		Assert.assertEquals(false,conductorService.recuperarConductor("2222222222222222").isOcupado());
//		Assert.assertEquals("Cici", conductorService.recuperarConductor("3333333333333333").getNombre());
//		Assert.assertEquals(false,conductorService.recuperarConductor("3333333333333333").isOcupado());
//	 }
//
//	 /**
//	  * Implementa un métode en el repositori de l'entitat Conductor
//	  * que retorni una llista de conductores lliures
//	  */
//
//	 @Test
//	 public void test_recuperar_conductor_libre_repositori() {
//
//		// només n'hi ha una conductora, Samantha, a la BBDD
//
//		List<Conductor> conductoresLibres = conductorRepo.findByOcupado(0);
//		Assert.assertNotNull(conductoresLibres);
//		Assert.assertEquals("Samantha", conductoresLibres.get(0).getNombre());
//		Assert.assertEquals(false, conductoresLibres.get(0).isOcupado());
//
////		 introduïm més conductores a la BBDD
//		conductorService.init();
//		conductoresLibres = conductorRepo.findByOcupado(0);
//		Assert.assertEquals(3, conductoresLibres.size());
//		Assert.assertEquals(false, conductoresLibres.get(1).isOcupado());
//	}
//
//	/**
//	 * Implementa un mètode en el servei de l'entitat Conductor
//	 * que retorni una llista de conductores lliures
//	 */
//
//	@Test
//	public void tets_conductor_libre_service() {
//
//		conductorService.init();
//
//		Conductor conductora = conductorService.recuperarConductorLibre();
//		Assert.assertNotNull(conductora);
//		Assert.assertEquals(false, conductora.isOcupado());
//	}
//
//	/**
//	 * Assigna una conductora a una carrera que ja existeix a la BBDD
//	 * i comprova que s'ha actualitzat el registre
//	 */
//
//	@Test
//	public void test_asignar_conductor() {
//		Long idCarrera = carreraService.crearCarrera("1234567890123456", "Parc de Ses Estacions", "Festival Park", 15, 18);
//		// seria necesario añadir el conductor pero vamos a testear primero repo
//		Assert.assertEquals("1234567890123456", carreraService.recuperaCarrera(idCarrera).getTarjetaCredito());
//
//		Carrera carrera = carreraService.recuperaCarrera(idCarrera);
//		Assert.assertNotNull(carrera);
//
//		Conductor conductora = conductorService.recuperarConductorLibre();
//		Assert.assertNotNull(conductora);
//
//		carrera.setConductor(conductora);
//		Assert.assertEquals("Samantha", carrera.getConductor().getNombre());
//
//		carreraService.updateCarrera(carrera);
//		Assert.assertEquals("Samantha", carreraService.recuperaCarrera(idCarrera).getConductor().getNombre());
//	}
//
//	/**
//	 * A completar:
//	 *  - Introducció de la valoració que l'usuari/a fa del conductor/a al termini de la carrera.
//	 *  - Càlcul de la mitjana de valoracions d'un conductor/a mitjançant @Query
//	 *    al CrudRepositori de CarrerasRepo.
//	 */
}