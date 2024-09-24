package com.adriYalan.gestionDeReclamos;

import com.adriYalan.gestionDeReclamos.entity.Unidad;
import com.adriYalan.gestionDeReclamos.repository.UnidadDAO;
import com.adriYalan.gestionDeReclamos.repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class GestionDeReclamosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GestionDeReclamosApplication.class, args);
	}

	@Autowired
	UnidadRepository unidadRepository;

	@Override
	public void run(String... args) throws Exception {

		UnidadDAO.getInstancia().setRepository(unidadRepository);

		/*List<Unidad> unidades = UnidadDAO.getInstancia().getAllUnidades();
		for (Unidad unidad : unidades) {
			System.out.println(unidad);
		}*/
		Optional<Unidad> unidad = UnidadDAO.getInstancia().getUnidadById(1);
		if (unidad.isPresent()) {
			System.out.println(unidad);
		}
	}
}
