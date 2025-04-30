package unoeste.fipp.ativooperantebackend.restcontrollers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unoeste.fipp.ativooperantebackend.entities.Denuncia;
import unoeste.fipp.ativooperantebackend.entities.Erro;
import unoeste.fipp.ativooperantebackend.services.DenunciaService;

import java.util.List;

@RestController
@RequestMapping("apis/denuncia")
public class DenunciaController {
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
}
