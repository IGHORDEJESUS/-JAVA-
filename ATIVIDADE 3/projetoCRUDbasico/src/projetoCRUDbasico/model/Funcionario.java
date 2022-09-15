package projetoCRUDbasico.model;

public class Funcionario {

    private int id;
    private String nome;
    private String email;
    private int matricula;
    private String sexo;

    public Funcionario() {}

    public Funcionario(int id, String nome, String email, int matricula, String sexo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.sexo = sexo;
    }

    public Funcionario(String nome, String email, int matricula, String sexo) {
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.sexo = sexo;
    }

    // ecapsulamento
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void trabalhar() {
        System.out.println("O funcionario está trabalhando com Java");
    }

    public String efetuarPergunta() {
        return "O funcionario está fazendo uma pergunta sobre duvida dele com Java";
    }

    public Funcionario retornaTrabalho() {
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
