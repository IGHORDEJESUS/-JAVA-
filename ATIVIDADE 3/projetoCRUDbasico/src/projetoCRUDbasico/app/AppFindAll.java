package projetoCRUDbasico.app;

import projetoCRUDbasico.service.FuncionarioService;

import javax.swing.*;

public class AppFindAll {
    public static void main(String[] args) {
        var service = new FuncionarioService();

        var respostaList = service.findAll().stream().map(funcionario -> "Funcionario.nome: " + funcionario.getNome() + "\n" + "Funcionario.email: " + funcionario.getEmail() + "\n" + "Funcionario.matricula: " + funcionario.getMatricula() + "\n" + "Funcionario.sexo: " + funcionario.getSexo() + "\n\n").toList();
        JOptionPane.showMessageDialog(null, respostaList, "Resposta", JOptionPane.INFORMATION_MESSAGE);
    }
}