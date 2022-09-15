package projetoCRUDbasico.app;

import projetoCRUDbasico.model.Funcionario;
import projetoCRUDbasico.service.FuncionarioService;

import javax.swing.*;

// FQN "Full Qualified Name: projetoCRUDbasico.app.App
public class AppSave {
    public static void main(String[] args) {
        var service = new FuncionarioService();
        Funcionario fun; // referencia a classe Funcionario
        fun = new Funcionario(); // criando uma nova instancia e atribuindo o endereço de memória dessa instancia para a referencia

        /* Sistema
         * Entrada padrão: teclado
         * Saída padrão: monitor
         * */
        var nome = JOptionPane.showInputDialog(null, "Informe o nome: ", "Input Nome", JOptionPane.QUESTION_MESSAGE);
        fun.setNome(nome);

        var email = JOptionPane.showInputDialog(null, "Informe o email: ", "Input Nome", JOptionPane.QUESTION_MESSAGE);
        fun.setEmail(email);

        var matricula = JOptionPane.showInputDialog(null, "Informe a matricula: ", "Input Nome", JOptionPane.QUESTION_MESSAGE);
        fun.setMatricula(Integer.parseInt(matricula));

        var sexo = JOptionPane.showInputDialog(null, "Informe o sexo: ", "Input Nome", JOptionPane.QUESTION_MESSAGE);
        fun.setSexo(sexo);

        var situacao = "Ocorreu uma falha na gravação. Verifique o log";
        var iconeStatus = JOptionPane.ERROR_MESSAGE;

        var response = service.save(fun);
        if(response) {
            situacao = "Gravado com sucesso";
            iconeStatus = JOptionPane.INFORMATION_MESSAGE;
        }

        var msg = "Situação da gravação no banco: " + situacao + "\n\n" + "Funcionario.nome: " + fun.getNome() + "\n" + "Funcionario.email: " + fun.getEmail() + "\n" + "Funcionario.matricula: " + fun.getMatricula() + "\n" + "Funcionario.sexo: " + fun.getSexo();
        JOptionPane.showMessageDialog(null, msg, "Resposta", iconeStatus);
    }
}