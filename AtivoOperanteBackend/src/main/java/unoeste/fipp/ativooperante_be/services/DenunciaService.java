package unoeste.fipp.ativooperante_be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.ativooperante_be.entities.Denuncia;
import unoeste.fipp.ativooperante_be.entities.FeedBack;
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

    public boolean addFeedBack(FeedBack feedBack){
        try {
            denunciaRepository.addFeedBack(feedBack.getId(), feedBack.getTexto());
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
