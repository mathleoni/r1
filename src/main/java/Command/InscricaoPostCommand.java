package Command;

import DAO.EventoDAO;
import DAO.ParticipanteDAO;
import Model.Participante;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InscricaoPostCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        String[] values = request.getParameterValues("selecionados");
        Integer codEvento = Integer.parseInt(request.getParameter("evento"));
        Integer codUsuario = Integer.parseInt(request.getParameter("usuario"));
        EventoDAO dao = EventoDAO.getInstance();
        try {
            for (String value : values) {
                dao.adicionarParticipacao(codEvento, Integer.parseInt(value));
            }
            response.sendRedirect("inscritos.html?codEvento=" + codEvento + "&usuario=" + codUsuario);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(InscricaoPostCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
