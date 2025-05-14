package unoeste.fipp.ativooperante_be.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @Column(name = "usu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "usu_cpf")
    private String cpf;
    @Column(name = "usu_email")
    private String email;
    @Column(name = "usu_senha")
    private String senha;
    @Column(name = "usu_nivel")
    private int nivel;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
