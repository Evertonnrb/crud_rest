package br.com.dao;

import br.com.bean.Chamado;
import br.com.bean.Status;
import br.com.infra.ConexaoJDBC;
import br.com.infra.ConexaoPostGresJDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChamadoDaoJDBC {

    private final ConexaoJDBC conexao;

    public ChamadoDaoJDBC() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoPostGresJDBC();
    }

    public Long inserir(Chamado chamado) throws SQLException, ClassNotFoundException {
        Long id = null;
        String sql = "INSERT INTO chamado (assunto,status,mensagem)VALUES(?,?,?)RETURNING id";
        try {
            PreparedStatement pst = this.conexao.getConnection().prepareStatement(sql);
            pst.setString(1, chamado.getAssunto());
            pst.setString(2, chamado.getStatus().toString());
            pst.setString(3, chamado.getMensagem());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getLong("id");
            }
            this.conexao.commit();
        } catch (SQLException e) {
            this.conexao.roolBack();
        }
        return id;
    }

    public int alterar(Chamado c) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE chamado SET assunto = ?, status = ?, mensagem = ? WHERE id = ?";
        int linasAlteradas = 0;
        try {
            PreparedStatement pst = this.conexao.getConnection().prepareStatement(sql);
            pst.setString(1, c.getAssunto());
            pst.setString(2, c.getStatus().toString());
            pst.setString(3, c.getMensagem());
            pst.setLong(4, c.getId());
            linasAlteradas = pst.executeUpdate();
            this.conexao.commit();
        } catch (SQLException e) {
            this.conexao.roolBack();
        }
        return linasAlteradas;
    }

    public int excluir(Chamado chamado) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM chamado WHERE id = ?";
        int linhasAfetadas = 0;
        try {
            PreparedStatement pst = this.conexao.getConnection().prepareStatement(sql);
            pst.setLong(1, chamado.getId());
            linhasAfetadas = pst.executeUpdate();
            this.conexao.commit();
        } catch (SQLException e) {
            this.conexao.close();
        }
        return linhasAfetadas;
    }

    public Chamado selecionar(Long id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM chamado WHERE id = ?";
        try {
            PreparedStatement pst = this.conexao.getConnection().prepareStatement(sql);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return parser(rs);
            }
        } catch (SQLException e) {
            throw e;
        }
        return null;
    }

    public List<Chamado> listar() {
        String sql = "SELECT*FROM chamado ORDER BY id";
        try {
            PreparedStatement pst = this.conexao.getConnection().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            List<Chamado>chamados = new ArrayList<>();
            while(rs.next()){
                chamados.add(parser(rs));
            }
            return chamados;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Chamado parser(ResultSet rs) throws SQLException {
        Chamado c = new Chamado();
        c.setId(rs.getLong("id"));
        c.setAssunto(rs.getString("assunto"));
        c.setMensagem(rs.getString("mensagem"));
        c.setStatus(Status.valueOf(rs.getString("status")));
        return c;
    }
}
