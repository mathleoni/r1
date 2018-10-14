package DAO;

import Model.Evento;
import Model.Participante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventoDAO {

    private static Connection conexao;
    private static EventoDAO dao;
    private static ParticipanteDAO Participantedao;

    public EventoDAO() throws SQLException {
        EventoDAO.conexao = Conexao.getInstance();
        EventoDAO.Participantedao = ParticipanteDAO.getInstance();
    }

    public static EventoDAO getInstance() {
        if (dao == null) {
            try {
                dao = new EventoDAO();
            } catch (SQLException ex) {
                Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dao;
    }

    public List<Evento> listAll() throws SQLException {
        List<Evento> eventos = new ArrayList<>();
        PreparedStatement consulta = conexao.prepareStatement("Select * from evento order by datasorteio DESC, dataevento DESC");
        ResultSet resultado = consulta.executeQuery();
        if (resultado.next()) {

            do {
                Participante criador = Participantedao.listbyID(resultado.getInt("codcriador"));
                List<Participante> participantes = Participantedao.listByIDEvento(resultado.getInt("codigo"));
                DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                eventos.add(new Evento(resultado.getInt("codigo"), resultado.getString("titulo"), resultado.getDouble("minimo"), resultado.getString("dataevento"), resultado.getString("datasorteio"), criador, dt, participantes,resultado.getBoolean("sorteado")));
            } while (resultado.next());
        }
        return eventos;
    }

    public void adicionar(Evento evento) throws SQLException {
        String sql = "INSERT INTO EVENTO(titulo, minimo,dataevento,datasorteio,codcriador,sorteado) VALUES(?,?,?,?,?,?)";
        Integer idEvento = null;
        try (PreparedStatement comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            comando.setString(1, evento.getTitulo());
            comando.setDouble(2, evento.getValorMinimo());
            comando.setString(3, evento.getDataEvento());
            comando.setString(4, evento.getDataSorteio());
            comando.setInt(5, evento.getCriador().getCodigo());
            comando.setBoolean(6, false);
            comando.execute();
            ResultSet rs = comando.getGeneratedKeys();
            if (rs.next()) {
                idEvento = rs.getInt(1);
            }
            comando.close();
            this.adicionarParticipacao(idEvento, evento.getCriador().getCodigo());
        }

    }

    public void adicionarParticipacao(Integer idEvento, Integer idParticipante) throws SQLException {
        String sql = "INSERT INTO EVENTO_PARTICIPANTE(CODEVENTO, CODPARTICIPANTE) VALUES (?,?)";
        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, idEvento);
            comando.setInt(2, idParticipante);
            comando.execute();
            comando.close();
        }
    }

    public void adicionarAmigo(Integer idEvento, Integer idParticipante, Integer idAmigo) throws SQLException {
        String sql = "UPDATE EVENTO_PARTICIPANTE SET CODAMIGO = ? WHERE CODEVENTO = ? AND CODPARTICIPANTE = ?";
        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, idAmigo);
            comando.setInt(2, idEvento);
            comando.setInt(3, idParticipante);
            comando.execute();
            comando.close();
        }
    }
    
    public void setSorteado(Integer idEvento) throws SQLException{
        String sql = "UPDATE EVENTO SET SORTEADO = ? WHERE CODIGO = ?";
         try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setBoolean(1, true);
            comando.setInt(2, idEvento);
            comando.execute();
            comando.close();
        }
    }

    public void alterar(Evento evento) throws SQLException {
        String sql = "UPDATE evento SET(titulo = ?,"
                + " minimo = ?,"
                + "dataevento = ?,"
                + "datasorteio = ?"
                + " WHERE codigo = ? ";
        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, evento.getTitulo());
            comando.setDouble(2, evento.getValorMinimo());
            comando.setString(3, evento.getDataEvento());
            comando.setString(4, evento.getDataSorteio());
            comando.setInt(5, evento.getCodigo());
            comando.execute();
            comando.close();
        }
    }

    public void excluir(Evento evento) throws SQLException {
        String sql = "DELETE FROM EVENTO WHERE CODIGO = ?";
        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, evento.getCodigo());
            comando.execute();
            comando.close();
        }
    }

    public List<Evento> listByIDCriador(Integer id) throws SQLException {
        List<Evento> eventos = new ArrayList<>();
        PreparedStatement consulta = conexao.prepareStatement("Select * from evento where CODCRIADOR = ? order by datasorteio DESC, dataevento DESC");
        consulta.setInt(1, id);
        ResultSet resultado = consulta.executeQuery();
        if (resultado.next()) {
            Participante criador = Participantedao.listbyID(resultado.getInt("codcriador"));

            do {
                List<Participante> participantes = Participantedao.listByIDEvento(resultado.getInt("codigo"));
                DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                eventos.add(new Evento(resultado.getInt("codigo"), resultado.getString("titulo"), resultado.getDouble("minimo"), resultado.getString("dataevento"), resultado.getString("datasorteio"), criador, dt, participantes,resultado.getBoolean("sorteado")));
            } while (resultado.next());
        }
        return eventos;

    }

    public List<Evento> listByIDParticipante(Integer id) throws SQLException {
        List<Evento> eventos = new ArrayList<>();
        PreparedStatement consulta = conexao.prepareStatement("select * from evento inner join evento_participante on codigo = codevento where codparticipante = ? order by datasorteio DESC, dataevento DESC,titulo ASC");
        consulta.setInt(1, id);
        ResultSet resultado = consulta.executeQuery();
        if (resultado.next()) {

            do {
                Participante criador = Participantedao.listbyID(resultado.getInt("codcriador"));
                List<Participante> participantes = Participantedao.listByIDEvento(resultado.getInt("codigo"));
                DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                eventos.add(new Evento(resultado.getInt("codigo"), resultado.getString("titulo"), resultado.getDouble("minimo"), resultado.getString("dataevento"), resultado.getString("datasorteio"), criador, dt, participantes,resultado.getBoolean("sorteado")));
            } while (resultado.next());
        }
        return eventos;
    }

    public List<Evento> listByDataSorteio() throws SQLException {
        List<Evento> eventos = new ArrayList<>();
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        PreparedStatement consulta = conexao.prepareStatement("select * from evento where datasorteio = ? and sorteado = ?");
        consulta.setString(1, LocalDate.now().format(dt));
        consulta.setBoolean(2, false);
        ResultSet resultado = consulta.executeQuery();
        if (resultado.next()) {

            do {
                Participante criador = Participantedao.listbyID(resultado.getInt("codcriador"));
                List<Participante> participantes = Participantedao.listByIDEvento(resultado.getInt("codigo"));;
                eventos.add(new Evento(resultado.getInt("codigo"), resultado.getString("titulo"), resultado.getDouble("minimo"), resultado.getString("dataevento"), resultado.getString("datasorteio"), criador, dt, participantes,resultado.getBoolean("sorteado")));
            } while (resultado.next());
        }
        return eventos;
    }

    public Evento listByID(Integer id) throws SQLException {
        Evento evento = null;
        PreparedStatement consulta = conexao.prepareStatement("Select * from evento where CODIGO = ?");
        consulta.setInt(1, id);
        ResultSet resultado = consulta.executeQuery();
        if (resultado.next()) {
            Participante criador = Participantedao.listbyID(resultado.getInt("codcriador"));

            do {
                List<Participante> participantes = Participantedao.listByIDEvento(resultado.getInt("codigo"));
                DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                evento=new Evento(resultado.getInt("codigo"), resultado.getString("titulo"), resultado.getDouble("minimo"), resultado.getString("dataevento"), resultado.getString("datasorteio"), criador, dt, participantes,resultado.getBoolean("sorteado"));
            } while (resultado.next());
        }
        return evento;

    }
}
