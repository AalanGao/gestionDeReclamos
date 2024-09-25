package com.adriYalan.gestionDeReclamos;

import com.adriYalan.gestionDeReclamos.entity.Edificio;
import com.adriYalan.gestionDeReclamos.entity.Persona;
import com.adriYalan.gestionDeReclamos.entity.Reclamo;
import com.adriYalan.gestionDeReclamos.entity.Unidad;
import com.adriYalan.gestionDeReclamos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class GestionDeReclamosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GestionDeReclamosApplication.class, args);
	}

	@Autowired
	UnidadRepository unidadRepository;
	@Autowired
	PersonaRepository personaRepository;
	@Autowired
	EdificioRepository edificioRepository;
	@Autowired
	ReclamoRepository reclamoRepository;


	@Override
	public void run(String... args) throws Exception {

		UnidadDAO.getInstancia().setRepository(unidadRepository);

		/*List<Unidad> unidades = UnidadDAO.getInstancia().getAllUnidades();
		for (Unidad unidad : unidades) {
			System.out.println(unidad);
		}*/
		Optional<Unidad> unidad = UnidadDAO.getInstancia().getUnidadById(8);
		if (unidad.isPresent()) {
			System.out.println(unidad);
		}

		Optional<Persona> persona = personaRepository.findById("CI 13230978");
		if (persona.isPresent()) {
			System.out.println(persona);
		}

		Optional<Edificio> edificio = edificioRepository.findById((long)1);
		if (edificio.isPresent()) {
			System.out.println(edificio);
		}

		Optional<Reclamo> reclamo = reclamoRepository.findById(11);
		if (reclamo.isPresent()) {
			System.out.println(reclamo);
		}

	}
}
