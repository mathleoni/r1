package Command;

import DAO.EventoDAO;
import DAO.ParticipanteDAO;
import Model.Evento;
import Model.Participante;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AmigoCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer id = Integer.parseInt(request.getParameter("usuario"));
            Integer idEvento = Integer.parseInt(request.getParameter("evento"));
            ParticipanteDAO dao = ParticipanteDAO.getInstance();
            Participante amigo = dao.getAmigoOculto(idEvento, id);
            EventoDAO eDao = EventoDAO.getInstance();
            Evento evento = eDao.listByID(idEvento);
                    
            request.setAttribute("usuario", id);
            request.setAttribute("amigo", amigo);
            request.setAttribute("evento", evento);
            RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/amigo.jsp");
            despachante.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(AmigoCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
