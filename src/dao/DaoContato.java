package dao;

import entidades.Contato;
import utils.Conexao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoContato {
    
    public static boolean alterar(Contato ct) {
        try (PreparedStatement stm = Conexao.conectar().prepareStatement("update contatos set nome = ?, email = ?, fone = ? where id = ?")) {
            stm.setString(1, ct.getNome());
            stm.setString(2, ct.getEmail());
            stm.setString(3, ct.getFone());
            stm.setInt(4, ct.getId());
            
            stm.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DaoContato.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean salvar(Contato ct) {
        try (PreparedStatement stm = Conexao.conectar().prepareStatement("insert into contatos(nome, email, fone) values(?,?,?)")) {
            stm.setString(1, ct.getNome());
            stm.setString(2, ct.getEmail());
            stm.setString(3, ct.getFone());

            stm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DaoContato.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static List<Contato> consultar() {
        List<Contato> lista = new ArrayList<>();
        try (PreparedStatement stm = Conexao.conectar().prepareStatement("select * from contatos")) {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Contato ct = new Contato();
                    ct.setId(rs.getInt("id"));
                    ct.setNome(rs.getString("nome"));
                    ct.setEmail(rs.getString("email"));
                    ct.setFone(rs.getString("fone"));
                    lista.add(ct);
                }
            }

            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DaoContato.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public static Contato consultarPeloId(int id) {
        Contato ct = new Contato();
        try (PreparedStatement stm = Conexao.conectar().prepareStatement("select * from contatos where id = ?")) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    ct.setId(rs.getInt("id"));
                    ct.setNome(rs.getString("nome"));
                    ct.setEmail(rs.getString("email"));
                    ct.setFone(rs.getString("fone"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoContato.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ct;
    }

    public static void excluir(int id) {
        try (PreparedStatement stm = Conexao.conectar().prepareStatement("delete from contatos where id = ?")) {
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoContato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
