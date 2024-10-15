package com.adriYalan.gestionDeReclamos;

import com.adriYalan.gestionDeReclamos.service.EdificioService;
import com.adriYalan.gestionDeReclamos.service.ReclamoService;
import com.adriYalan.gestionDeReclamos.service.UnidadService;
import com.adriYalan.gestionDeReclamos.entity.Unidad;
import com.adriYalan.gestionDeReclamos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionDeReclamosApplication implements CommandLineRunner {

	@Autowired
	private PersonaDAO personaDAO;

	@Autowired
	private InquilinoDAO inquilinoDAO;

	@Autowired
	private HabitanteDAO habitanteDAO;

	@Autowired
	private DuenioDAO duenioDAO;

	@Autowired
	private ReclamoDAO reclamoDAO;

	@Autowired
	private UnidadDAO unidadDAO;

	@Autowired
	private EdificioDAO edificioDAO;

	@Autowired
	private UbicacionDAO ubicacionDAO;

	@Autowired
	private TipoReclamoDAO tipoReclamoDAO;

	@Autowired
	UnidadService unidadController;

	@Autowired
	ReclamoService reclamoController;

	@Autowired
	EdificioService edificioController;
	@Autowired
	EdificioRepository edificioRepository;
	@Autowired
	UnidadRepository unidadRepository;

	public static void main(String[] args) {
		SpringApplication.run(GestionDeReclamosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*List<Unidad> unidades = unidadRepository.findAll();
		for (Unidad unidad : unidades) {
			System.out.println(unidad);
		}*/
		/*Optional<Unidad> unidad = UnidadDAO.getInstancia().getUnidadById(8);
		if (unidad.isPresent()) {
			System.out.println(unidad);
		}*/

		/*Optional<Persona> persona = personaRepository.findById("CI 13230978");
		if (persona.isPresent()) {
			System.out.println(persona);
		}*/


		/*Optional<Edificio> edificio = edificioRepository.findById((long)1);

		if (edificio.isPresent()) {
			for (Persona person : edificio.get().getDuenios()) {
				System.out.println(person);
			}
		}*/



		/*Optional<Reclamo> reclamo = reclamoRepository.findById(11);
		if (reclamo.isPresent()) {
			System.out.println(reclamo);
		}*/

		//System.out.println(unidadController.inquilinosPorUnidad(1, "9", "5"));
		//System.out.println(unidadController.inquilinosPorUnidad(1, "9", "2"));

		//System.out.println(unidadController.dueniosPorUnidad(1, "9", "5"));
		//unidadController.agregarHabitanteUnidad(1, "1", "1", "DNI29988738");
		//unidadController.agregarDuenioUnidad(1, "9", "5", "CI 13230978");
		//unidadController.liberarUnidad(1, "9", "5");
		//System.out.println(unidadController.dueniosPorUnidad(1, "9", "5"));

		//reclamoController.agregarReclamo(1, "9","5", "DNI29988738", 1, "Prueba tenes que andar", 1, null);
		//System.out.println(edificioController.getEdificios());
		for (Unidad unidad : edificioController.getUnidadesPorEdificio(1)) {
				System.out.println(unidad.estaHabitado());
			}
		}

	}

