package projetoCRUDbasico.app;

import projetoCRUDbasico.service.FuncionarioService;

import javax.swing.*;

public class AppFindByName {
    public static void main(String[] args) {
        var service = new FuncionarioService();

        var respostaList = service.findByName("Ighor").stream().map(funcionario -> "Funcionario.nome: " + funcionario.getNome() + "\n" + "Funcionario.email: " + funcionario.getEmail() + "\n" + "Aluno.matricula: " + funcionario.getMatricula() + "\n" + "Funcionario.sexo: " + funcionario.getSexo() + "\n\n").toList();
        JOptionPane.showMessageDialog(null, respostaList, "Resposta", JOptionPane.INFORMATION_MESSAGE);
    }
}