package dao;
import controller.ConnectionMySQL;
import model.JogoModel;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class JogoDAO {
    
    private Connection connection;
    
    public JogoDAO(Connection connection){
        this.connection = new ConnectionMySQL().getConnection();
    }
    
    public void adicionar(JogoModel jogo) {
        String sql = "INSERT INTO jogo(nome,plataforma,preco)VALUES(?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, jogo.getNome());
            ps.setString(2, jogo.getPlataforma());
            ps.setDouble(3, jogo.getPreco());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar! Classe DAO");
            throw new RuntimeException(e);
        }

    }
    
    public List<JogoModel> leitura() {
        connection = new ConnectionMySQL().getConnection();

        PreparedStatement ps = null;

        ResultSet rs = null;

        List<JogoModel> jogoArray = new ArrayList<>();

        try {
            ps = connection.prepareStatement(
                    "SELECT * FROM jogo");
            rs = ps.executeQuery();

            while (rs.next()) {
                JogoModel u = new JogoModel();

                u.setIdJogo(rs.getInt("idJogo"));
                u.setNome(rs.getString("nome"));
                u.setPlataforma(rs.getString("plataforma"));
                u.setPreco(rs.getDouble("preco"));

                jogoArray.add(u);

            }
//            JOptionPane.showMessageDialog(null, "Lista DAO Funcionou");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lista DAO NÂO Funcionou");

            throw new RuntimeException(e);
        }

        return jogoArray;

    }
    
    public void deletar(JogoModel jogo) {
        String sql = "DELETE FROM jogo WHERE idJogo = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, jogo.getIdJogo());

            ps.execute();

            JOptionPane.showMessageDialog(null, "Exclusão DAO concluída!");

            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Exclusão DAO não concluida!");

            throw new RuntimeException(e);
        }
    }
}
