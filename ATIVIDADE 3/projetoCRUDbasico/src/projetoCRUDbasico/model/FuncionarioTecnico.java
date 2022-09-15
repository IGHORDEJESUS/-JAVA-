package projetoCRUDbasico.model;

public class FuncionarioTecnico extends Funcionario {

    private String especializacao;

    /*
    * this -> representa a classe corrente "aberta"
    * super -> representa membro da classe pai (só funciona com herança)
    * */
    @Override
    public String getNome() {
        return "Funcionario trabalho técnico: " + getNome();
    }


    public String getEspecializacao() {
        return especializacao;
    }

    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }
}
