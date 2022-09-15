package projetoCRUDbasico.service;

import projetoCRUDbasico.model.Funcionario;
import projetoCRUDbasico.persist.FuncionarioDAO;

import java.util.List;

public class FuncionarioService {
    private FuncionarioDAO dao;

    public FuncionarioService() {
        dao = new FuncionarioDAO();
    }

    public boolean save(Funcionario funcionario) {
        return dao.save(funcionario);
    }

    public List<Funcionario> findAll() {
        return dao.findAll();
    }

    public Funcionario findById(long id) {
        return dao.findById(id);
    }

    public List<Funcionario> findByName(String nome) {
        return dao.findByName(nome);
    }

    public Funcionario findByMatricula(int matricula) {
        return dao.findByMatricula(matricula);
    }

    public Funcionario findByEmail(String email) {
        return dao.findByEmail(email);
    }

    public boolean deleteById(long id) {
        return dao.deleteById(id);
    }

    public boolean deleteAll(List<Funcionario> funcionarios) {
        return dao.deleteAll(funcionarios);
    }
}
