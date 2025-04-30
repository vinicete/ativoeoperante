package unoeste.fipp.ativooperantebackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unoeste.fipp.ativooperantebackend.entities.Tipo;

@Repository
public interface ITipoRepository extends JpaRepository<Tipo,Long> {


}
