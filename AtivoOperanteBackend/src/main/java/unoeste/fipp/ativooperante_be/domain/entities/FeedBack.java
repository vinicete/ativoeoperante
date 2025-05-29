package unoeste.fipp.ativooperante_be.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "feedback")
public class FeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fee_id")
    private Long Id;
    @Column(name = "fee_texto")
    private String texto;
    @OneToOne
    @JoinColumn(name = "den_id", unique = true)
    private Denuncia denuncia;

    public FeedBack( String texto, Denuncia denuncia) {
        this.texto = texto;
        this.denuncia = denuncia;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Denuncia getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(Denuncia denuncia) {
        this.denuncia = denuncia;
    }
}
