package projetoCRUDbasico.app;

import projetoCRUDbasico.service.FuncionarioService;

import javax.swing.*;

public class AppDeleteAll {
    public static void main(String[] args) {
        var service = new FuncionarioService();
        var funcionarios = service.findByName("Ighor");

        var status = service.deleteAll(funcionarios);

        var msg = "Status da exclusão: " + (status ? "Sucesso" : "Falha");
        JOptionPane.showMessageDialog(null, msg, "Resposta", JOptionPane.INFORMATION_MESSAGE);
    }
}