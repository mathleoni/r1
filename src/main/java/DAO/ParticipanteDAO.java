package DAO;

import Model.Evento;
import Model.Participante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParticipanteDAO {

    private static Connection conexao;
    private static ParticipanteDAO dao;

    public ParticipanteDAO() throws SQLException {
        ParticipanteDAO.conexao = Conexao.getInstance();
    }

    public static ParticipanteDAO getInstance() {
        if (dao == null) {
            try {
                dao = new ParticipanteDAO();
            } catch (SQLException ex) {
                Logger.getLogger(ParticipanteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dao;
    }

    public List<Participante> listAll() throws SQLException {
        List<Participante> participantes = new ArrayList<>();
        PreparedStatement consulta = conexao.prepareStatement("Select * from participante");
        ResultSet resultado = consulta.executeQuery();
        if (resultado.next()) {
            do {
                participantes.add(new Participante(resultado.getInt("codigo"), resultado.getString("nome"), resultado.getString("email"), resultado.getString("senha")));
            } while (resultado.next());
        }
        return participantes;
    }

    public void adicionar(Participante participante) throws SQLException, IllegalArgumentException {
        if (jaCadastrado(participante.getEmail()) == true) {
            throw new IllegalArgumentException();
        }
        String sql = "INSERT INTO PARTICIPANTE(nome, email,senha) VALUES(?,?,?)";
        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, participante.getNome());
            comando.setString(2, participante.getEmail());
            comando.setString(3, participante.getSenha());
            comando.execute();
            comando.close();
        }
    }

    public void alterar(Participante participante) throws SQLException {
        String sql = "UPDATE evento SET(nome = ?,"
                + " email = ?,"
                + "senha = ?"
                + " WHERE codigo = ? ";
        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, participante.getNome());
            comando.setString(2, participante.getEmail());
            comando.setString(3, participante.getSenha());
            comando.execute();
            comando.close();
        }
    }

    public void excluir(Participante participante) throws SQLException {
        String sql = "DELETE FROM PARTICIPANTE WHERE CODIGO = ?";
        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, participante.getCodigo());
            comando.execute();
            comando.close();
        }
    }

    public Participante getUsuario(String nome, String senha) throws SQLException {
        Participante participante = null;
        PreparedStatement consulta = conexao.prepareStatement("Select * from participante where email = ? AND senha = ?");
        consulta.setString(1, nome);
        consulta.setString(2, senha);
        consulta.setMaxRows(1);
        consulta.execute();
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            participante = new Participante(resultado.getInt("codigo"), resultado.getString("nome"), resultado.getString("email"), resultado.getString("senha"));
        }
        consulta.close();
        return participante;
    }

    public Participante listbyID(Integer id) throws SQLException {
        Participante participante;
        try (PreparedStatement consulta = conexao.prepareStatement("Select * from participante where codigo = ?")) {
            consulta.setInt(1, id);
            ResultSet resultado = consulta.executeQuery();
            resultado.next();
            participante = new Participante(resultado.getInt("codigo"), resultado.getString("nome"), resultado.getString("email"), resultado.getString("senha"));
        }
        return participante;
    }

    public List<Participante> listByIDEvento(Integer id) throws SQLException {
        List<Participante> participantes = new ArrayList<>();
        PreparedStatement consulta = conexao.prepareStatement("select * from participante inner join evento_participante on codigo = codparticipante where codevento = ? order by nome DESC");
        consulta.setInt(1, id);
        ResultSet resultado = consulta.executeQuery();
        if (resultado.next()) {

            do {
                participantes.add(new Participante(resultado.getInt("codigo"), resultado.getString("nome"), resultado.getString("email"), resultado.getString("senha")));
            } while (resultado.next());
        }
        return participantes;
    }

    public List<Participante> listNaoParticipantesEvento(Integer id) throws SQLException {
        List<Participante> participantes = new ArrayList<>();
        PreparedStatement consulta = conexao.prepareStatement("select * from participante where codigo not in (select codparticipante from evento_participante where codevento = ?)");
        consulta.setInt(1, id);
        ResultSet resultado = consulta.executeQuery();
        if (resultado.next()) {

            do {
                participantes.add(new Participante(resultado.getInt("codigo"), resultado.getString("nome"), resultado.getString("email"), resultado.getString("senha")));
            } while (resultado.next());
        }
        return participantes;
    }

    public Integer totalParticipantes() throws SQLException {
        String sql = "SELECT COUNT(*) AS TOTAL FROM PARTICIPANTE";
        Integer total = null;
        try (PreparedStatement consulta = conexao.prepareStatement(sql)) {
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                total = resultado.getInt("total");
            }
        }
        return total;
    }

    public Participante getAmigoOculto(Integer idEvento, Integer idParticipante) throws SQLException {
        Participante participante;
        String sql = "SELECT codamigo FROM PARTICIPANTE INNER JOIN EVENTO_PARTICIPANTE ON CODIGO = CODPARTICIPANTE WHERE CODEVENTO = ? AND CODPARTICIPANTE = ?";
        try (PreparedStatement consulta = conexao.prepareStatement(sql)) {
            consulta.setInt(1, idEvento);
            consulta.setInt(2, idParticipante);
            ResultSet resultado = consulta.executeQuery();
            resultado.next();
            participante = this.listbyID(resultado.getInt("codamigo"));
        }
        return participante;

    }

    private boolean jaCadastrado(String email) throws SQLException {
        String sql = "SELECT FROM PARTICIPANTE WHERE EMAIL = ?";
        try (PreparedStatement consulta = conexao.prepareStatement(sql)) {
            consulta.setString(1, email);
            ResultSet resultado = consulta.executeQuery();
            return resultado.next();
        }
    }
}
