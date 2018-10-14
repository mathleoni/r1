package Command;

import DAO.EventoDAO;
import DAO.ParticipanteDAO;
import Model.Evento;
import Model.Participante;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InscritosCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {

        Integer idEvento = Integer.parseInt(request.getParameter("codEvento"));
        Integer idUsuario = Integer.parseInt(request.getParameter("usuario"));
        request.setAttribute("usuario", idUsuario);
        EventoDAO eventoDAO = EventoDAO.getInstance();
        Evento evento = null;
        try {
            evento = eventoDAO.listByID(idEvento);
            request.setAttribute("evento", evento);
            ParticipanteDAO participantaDAO = ParticipanteDAO.getInstance();
            List<Participante> participantes = participantaDAO.listByIDEvento(idEvento);
            request.setAttribute("participantes", participantes);
            RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/inscritos.jsp");
            despachante.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(InscritosCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
