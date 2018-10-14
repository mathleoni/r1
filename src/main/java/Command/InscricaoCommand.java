package Command;

import DAO.ParticipanteDAO;
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

public class InscricaoCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer codigo = Integer.parseInt(request.getParameter("codEvento"));
            ParticipanteDAO dao = ParticipanteDAO.getInstance();
            List<Participante> participantes = dao.listNaoParticipantesEvento(codigo);
            RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/gerenciarEvento.jsp");
            request.setAttribute("participantes", participantes);
            request.setAttribute("codEvento", codigo);
            Integer id = Integer.parseInt(request.getParameter("usuario"));
            request.setAttribute("usuario", id);
            dispacher.forward(request, response);

        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(EventoCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
