package Model;

public class Participante {
    String nome, email, senha;
    Participante amigo;
    Integer codigo;

    public Participante(Integer codigo, String nome, String email, String senha) {
        this.nome = nome;
        this.codigo = codigo;
        this.email = email;
        this.senha = senha;
    }
    
    public Participante(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Participante getAmigo() {
        return amigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setAmigo(Participante amigo) {
        this.amigo = amigo;
    }
}
