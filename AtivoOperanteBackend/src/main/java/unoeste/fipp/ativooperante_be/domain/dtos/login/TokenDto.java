package unoeste.fipp.ativooperante_be.domain.dtos.login;

public class TokenDto {

    private String token;

    public TokenDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
