package unoeste.fipp.ativooperante_be.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Column(name = "usu_id")
    private int id;
    @Column(name = "usu_cpf")
    private String cpf;
    @Column(name = "usu_email")
    private String email;
    @Column(name = "usu_senha")
    private String senha;
    @Column(name = "usu_nivel")
    private int nivel;

    
}
