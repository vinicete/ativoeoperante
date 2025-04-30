package unoeste.fipp.ativooperantebackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.ativooperantebackend.entities.Tipo;
import unoeste.fipp.ativooperantebackend.repositories.ITipoRepository;

import java.util.List;

@Service
public class TipoService {
    @Autowired
    private ITipoRepository tipoRepository;

    public List<Tipo> getAll(){
        return tipoRepository.findAll();
    }

}
