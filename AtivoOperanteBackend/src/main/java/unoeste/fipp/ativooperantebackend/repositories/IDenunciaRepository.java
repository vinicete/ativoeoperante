package unoeste.fipp.ativooperantebackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unoeste.fipp.ativooperantebackend.entities.Denuncia;

public interface IDenunciaRepository extends JpaRepository<Denuncia,Long> {
}
