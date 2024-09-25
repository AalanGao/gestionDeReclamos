package com.adriYalan.gestionDeReclamos.controller;

import com.adriYalan.gestionDeReclamos.entity.Edificio;
import com.adriYalan.gestionDeReclamos.entity.Unidad;
import com.adriYalan.gestionDeReclamos.exception.EdificioException;
import com.adriYalan.gestionDeReclamos.repository.EdificioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class EdificioController {

    @Autowired
    private EdificioDAO edificioDAO;

    public List<Unidad> getUnidadesPorEdificio(int codigo) throws EdificioException {
        // 1. Buscar el edificio por su código
        Optional<Edificio> edificioOpt = edificioDAO.getEdificioByCodigo((long) codigo);

        // 2. Si no se encuentra, lanzamos la excepción
        if (!edificioOpt.isPresent()) {
            throw new EdificioException("El edificio con código " + codigo + " no existe.");
        }

        // 3. Obtener las unidades del edificio
        Edificio edificio = edificioOpt.get();
        return edificio.getUnidades(); // Devolver la lista de unidades directamente
    }
}
