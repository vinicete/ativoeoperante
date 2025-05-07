package unoeste.fipp.ativooperante_be.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unoeste.fipp.ativooperante_be.entities.Denuncia;
import unoeste.fipp.ativooperante_be.entities.Erro;
import unoeste.fipp.ativooperante_be.entities.FeedBack;
import unoeste.fipp.ativooperante_be.entities.Tipo;
import unoeste.fipp.ativooperante_be.services.DenunciaService;

import java.util.List;

@RestController
@RequestMapping("apis/denuncia")
public class DenunciaRestController {

    @Autowired
    private DenunciaService denunciaService;

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<Denuncia> denunciaList;
        denunciaList=denunciaService.getAll();
        if (!denunciaList.isEmpty())
            return ResponseEntity.ok(denunciaList);
        else
            return ResponseEntity.badRequest().body(
                    new Erro("Nenhum tipo cadastrado"));
    }
    @GetMapping("add-feedback/{id}/{texto}")
    public ResponseEntity<Object> addFeedBack(@PathVariable Long id, @PathVariable String texto) {
        if(denunciaService.addFeedBack(new FeedBack(id,texto)))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body("Não foi possível adicionar o feebback");
    }

}
