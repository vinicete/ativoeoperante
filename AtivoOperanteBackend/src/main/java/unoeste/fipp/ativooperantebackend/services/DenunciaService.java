package unoeste.fipp.ativooperantebackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.ativooperantebackend.entities.Denuncia;
import unoeste.fipp.ativooperantebackend.repositories.IDenunciaRepository;

import java.util.List;

@Service
public class DenunciaService {
    @Autowired
    private IDenunciaRepository denunciaRepository;
    public List<Denuncia> getAll(){return denunciaRepository.findAll();}
}
