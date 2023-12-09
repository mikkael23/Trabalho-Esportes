package org.libertas.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class AtletaDao {
    private Connection connection;

    public AtletaDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/atletas";
            String user = "root";
            String pwd = "1234";
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void desconectar() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inserir(Atleta a) {
        try {
            String sql = "INSERT INTO atletas (nome, modalidade, idade, clube) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, a.getNome());
            pstmt.setString(2, a.getModalidade());
            pstmt.setInt(3, a.getIdade());
            pstmt.setString(4, a.getClube());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
    }

    public void alterar(Atleta a) {
        try {
            String sql = "UPDATE atletas SET nome = ?, modalidade = ?, idade = ?, clube = ? WHERE idAtleta = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, a.getNome());
            pstmt.setString(2, a.getModalidade());
            pstmt.setInt(3, a.getIdade());
            pstmt.setString(4, a.getClube());
            pstmt.setInt(5, a.getIdAtleta());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
    }

    public void excluir(int id) {
        try {
            String sql = "DELETE FROM atletas WHERE idAtleta = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
    }

    public Atleta consultar(int id) {
        try {
            String sql = "SELECT * FROM atletas WHERE idAtleta = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                Atleta a = new Atleta();
                a.setIdAtleta(res.getInt("idAtleta"));
                a.setNome(res.getString("nome"));
                a.setModalidade(res.getString("modalidade"));
                a.setIdade(res.getInt("idade"));
                a.setClube(res.getString("clube"));
                return a;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
        return null;
    }

    public List<Atleta> listar() {
        List<Atleta> lista = new LinkedList<>();
        try {
            String sql = "SELECT * FROM atletas ORDER BY nome";
            Statement sta = connection.createStatement();
            ResultSet res = sta.executeQuery(sql);
            while (res.next()) {
                Atleta a = new Atleta();
                a.setIdAtleta(res.getInt("idAtleta"));
                a.setNome(res.getString("nome"));
                a.setModalidade(res.getString("modalidade"));
                a.setIdade(res.getInt("idade"));
                a.setClube(res.getString("clube"));
                lista.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
        return lista;
    }
}
