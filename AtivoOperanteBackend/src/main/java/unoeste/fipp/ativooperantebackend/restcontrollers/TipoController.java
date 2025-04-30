package unoeste.fipp.ativooperantebackend.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unoeste.fipp.ativooperantebackend.entities.Erro;
import unoeste.fipp.ativooperantebackend.entities.Tipo;
import unoeste.fipp.ativooperantebackend.services.TipoService;

import java.util.List;

@RestController
@RequestMapping("apis/tipo")
public class TipoController {
    @Autowired
    TipoService tipoService;

    @GetMapping()
    public ResponseEntity<Object> getAll(){

        try{
            List<Tipo> tipos= tipoService.getAll();

            if(!tipos.isEmpty()){

                return ResponseEntity.ok(tipos);
            }
            else{
                return ResponseEntity.badRequest().body(
                        new Erro("Nnehum"));

            }
        }
        catch(Exception ex){
            return ResponseEntity.badRequest().body(new Erro("Nnehum"));
        }


    }
}
