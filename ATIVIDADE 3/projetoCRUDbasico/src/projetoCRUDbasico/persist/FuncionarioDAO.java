package projetoCRUDbasico.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projetoCRUDbasico.model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FuncionarioDAO extends DAO {

    private static Logger logger = LoggerFactory.getLogger(FuncionarioDAO.class);

    public boolean save(Funcionario funcionario) {
        var sql = "insert into funcionario(nome, email, matricula, sexo) values (?, ?, ?, ?)";
        // https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, funcionario.getNome());
            pstmt.setString(2, funcionario.getEmail());
            pstmt.setInt(3, funcionario.getMatricula());
            pstmt.setString(4, funcionario.getSexo());

            logger.debug("Query executada: {}", sql);
            return (pstmt.executeUpdate() != 0) ? Boolean.TRUE : Boolean.FALSE; // retorna error => 0 | success => 1

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error on save funcionario. Error: {}", e.getMessage());
            return Boolean.FALSE;
        }
    }

    public List<Funcionario> findAll() {
        var funcionarios = new ArrayList<Funcionario>();
        var sql = "select * from funcionario";
        // Java versão 7+
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            logger.info("Query executada: {}", sql);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    funcionarios.add(setFuncionario(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error on list funcionarios. Error: {}", e.getMessage());
            return new ArrayList<>();
        }
        return funcionarios;
    }

    public Funcionario findById(long id) {
        var sql = "select * from funcionario where id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);

            logger.info("Query executada: {}", sql);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? setFuncionario(rs) : new Funcionario();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error on find id funcionario. Error: {}", e.getMessage());
            return new Funcionario();
        }
    }

    public List<Funcionario> findByName(String nome) {
        var funcionario = new ArrayList<Funcionario>();
        var sql = "select * from funcionario where nome like ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, '%' + nome + '%');

            logger.info("Query executada: {}", sql);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    funcionario.add(setFuncionario(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error on list funcionarios. Error: {}", e.getMessage());
            return new ArrayList<>();
        }
        return funcionario;
    }

    public Funcionario findByMatricula(int matricula) {
        var sql = "select * from funcionario where matricula = ?";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, matricula);

            logger.info("Query executada: {}", sql);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? setFuncionario(rs) : new Funcionario();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error on find id funcionario. Error: {}", e.getMessage());
            return new Funcionario();
        }
    }

    public boolean update(Funcionario funcionario) {
        var sql = "update funcionario set nome = ?, email = ?, matricula = ?, sexo = ? where id  = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, funcionario.getNome());
            pstmt.setString(2, funcionario.getEmail());
            pstmt.setInt(3, funcionario.getMatricula());
            pstmt.setString(4, funcionario.getSexo());
            pstmt.setInt(5, funcionario.getId());

            logger.info("Query executada: {}", sql);
            return (pstmt.executeUpdate() != 0) ? Boolean.TRUE : Boolean.FALSE;

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error on save funcionario. Error: {}", e.getMessage());
            return Boolean.FALSE;
        }
    }

    public Funcionario findByEmail(String email) {
        var sql = "select * from funcionario where email = ?";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);

            logger.info("Query executada: {}", sql);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? setFuncionario(rs) : new Funcionario();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error on find id funcionario. Error: {}", e.getMessage());
            return new Funcionario();
        }
    }

    public boolean deleteById(long id) {
        var sql = "delete from funcionario where id = ?";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);

            logger.info("Query executada: {}", sql);
            return (pstmt.executeUpdate() != 0) ? Boolean.TRUE : Boolean.FALSE;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error on delete funcionario. Error: {}", e.getMessage());
            return Boolean.FALSE;
        }
    }

    public boolean deleteAll(List<Funcionario> funcionarios) {
        var sql = "delete from funcionario where id in (?)";

        //# workaround(gambiarra) para o MySQL
        var sqlIN = funcionarios
                .stream()// # 1. abre a transmissao
                .map(funcionario -> String.valueOf(funcionario.getId())) // 2. transforma o dado
                .collect(Collectors.joining(",", "(", ")")); // 3. transformando concatenando

        sql = sql.replace("(?)", sqlIN);
        //## workaround(gambiarra) para o MySQL

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

// O método createArrayOf não é suportado pela lib do MySQL
//            var idsInList = funcionarios.stream().map(Funcionario::getId).toList();
//            var idsInArray = conn.createArrayOf("integer", idsInList.toArray());
//            pstmt.setArray(1, idsInArray);
            logger.info("Query executada: {}", sql);
            return (pstmt.executeUpdate() != 0) ? Boolean.TRUE : Boolean.FALSE;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error on delete table funcionario. Error: {}", e.getMessage());
            return Boolean.FALSE;
        }
    }

    private Funcionario setFuncionario(ResultSet rs) throws SQLException {
        return new Funcionario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getInt("matricula"), rs.getString("sexo"));
    }
}