package unoeste.fipp.ativooperante_be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.ativooperante_be.domain.entities.Denuncia;
import unoeste.fipp.ativooperante_be.domain.entities.FeedBack;
import unoeste.fipp.ativooperante_be.repositories.DenunciaRepository;

import java.util.List;

@Service
public class DenunciaService {
    @Autowired
    private DenunciaRepository denunciaRepository;
    public List<Denuncia> getAll()
    {
        return denunciaRepository.findAll();
    }

    public Denuncia save(Denuncia denuncia){
        return denunciaRepository.save(denuncia);
    }

    public boolean addFeedBack(FeedBack feedBack){
        try {
            denunciaRepository.addFeedBack(feedBack.getId(), feedBack.getTexto());
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean delete(Denuncia denuncia){
        try{
            denunciaRepository.deleteById(denuncia.getId());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<Denuncia> getDenunciasByUser(Long id){
        return denunciaRepository.getDenunciasByUsuarioId(id);
    }
}
