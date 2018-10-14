
package Command;

import DAO.EventoDAO;
import Model.Evento;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pedrofreitas
 */
public class SorteioCommand implements Comando{

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("codEvento"));
        int idU = Integer.parseInt(request.getParameter("usuario"));
        request.setAttribute("usuario", idU);
        EventoDAO dao = EventoDAO.getInstance();
        Evento evento;
        
        try {
            evento = dao.listByID(id);
            evento.sorteia();
            request.setAttribute("evento", evento);
            IndexCommand index = new IndexCommand();
            index.exec(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SorteioCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
}
