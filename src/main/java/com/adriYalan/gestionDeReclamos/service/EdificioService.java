package com.adriYalan.gestionDeReclamos.service;

import com.adriYalan.gestionDeReclamos.entity.Edificio;
import com.adriYalan.gestionDeReclamos.entity.Persona;
import com.adriYalan.gestionDeReclamos.entity.Reclamo;
import com.adriYalan.gestionDeReclamos.entity.Unidad;
import com.adriYalan.gestionDeReclamos.exception.EdificioException;
import com.adriYalan.gestionDeReclamos.repository.EdificioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EdificioService {

    @Autowired
    private EdificioDAO edificioDAO;

    public List<Edificio> getEdificios() {
        return edificioDAO.getAllEdificios();
    }

    public Edificio getEdificiosByCodigo(int codigo) throws EdificioException {
        Optional<Edificio> edificioOpt = edificioDAO.getEdificioByCodigo((long) codigo);
        if (edificioOpt.isEmpty()) {
            throw new EdificioException("El edificio con código " + codigo + " no existe.");
        }
        return edificioOpt.get();
    }

    public List<Persona> habilitadosPorEdificio(int codigo) throws EdificioException {
        Edificio edificio = this.getEdificiosByCodigo(codigo);
        return edificio.getHabitantes();
    }

    public List<Persona> dueniosPorEdificio(int codigo) throws EdificioException {
        Edificio edificio = this.getEdificiosByCodigo(codigo);
        return edificio.getDuenios();
    }

    public List<Persona> inquilinosPorEdificio(int codigo) throws EdificioException {
        Edificio edificio = this.getEdificiosByCodigo(codigo);
        return edificio.getInquilinos();
    }

    public List<Unidad> getUnidadesPorEdificio(int codigo) throws EdificioException {
        Edificio edificio = this.getEdificiosByCodigo(codigo);
        return edificio.getUnidades();
    }

    public Edificio agregarEdificio(String direccion, String nombre) throws EdificioException {
        // Verificar si ya existe un edificio con el mismo código
        if (edificioDAO.getEdificioByNombreAndDireccion(direccion,nombre).isPresent()) {
            throw new EdificioException("El edificio con esa direccion y nombre ya existe.");
        }

        // Crear y guardar el nuevo edificio
        Edificio nuevoEdificio = new Edificio();
        nuevoEdificio.setDireccion(direccion);
        nuevoEdificio.setNombre(nombre);

        return edificioDAO.guardarEdificio(nuevoEdificio);
    }

    public List<Reclamo> getReclamoZonaComunPorEdificio(int codigoEdificio) throws EdificioException {
        // Obtener el edificio
        Edificio edificio = getEdificiosByCodigo(codigoEdificio);

        // Filtrar reclamos de zonas comunes (identificador de unidad es null)
        List<Reclamo> reclamosZonaComun = new ArrayList<>();
        for (Reclamo reclamo : edificio.getReclamos()) {
            if (reclamo.getUnidad() == null) {
                reclamosZonaComun.add(reclamo);
            }
        }
        return reclamosZonaComun;
    }

    public List<Reclamo> getReclamoPorEdificio(int codigoEdificio) throws EdificioException {
        Edificio edificio = getEdificiosByCodigo(codigoEdificio);
        return edificio.getReclamos();
    }
}
