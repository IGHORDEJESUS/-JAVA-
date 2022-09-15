package projetoCRUDbasico.app;

import projetoCRUDbasico.service.FuncionarioService;

import javax.swing.*;

public class AppFindById {
    public static void main(String[] args) {
        var service = new FuncionarioService();

        var fun = service.findById(1L);

        var msg = "Funcionario.nome: " + fun.getNome() + "\n" + "Funcionario.email: " + fun.getEmail() + "\n" + "Funcionario.matricula: " + fun.getMatricula() + "\n" + "Funcionario.sexo: " + fun.getSexo();
        JOptionPane.showMessageDialog(null, msg, "Resposta", JOptionPane.INFORMATION_MESSAGE);
    }
}