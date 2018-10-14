package Command;

import DAO.EventoDAO;
import Model.Evento;
import Model.Participante;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        EventoDAO eventodao = EventoDAO.getInstance();
        List<Evento> eventos;
        try {
            Integer idParticipante;
            if(request.getAttribute("usuario")!=null){
                idParticipante = (Integer)request.getAttribute("usuario");
            } else{
                idParticipante = Integer.parseInt(request.getParameter("usuario"));
            }
            eventos = eventodao.listByIDParticipante(idParticipante);
            Gson gson = new Gson();
            String eventosJson = gson.toJson(eventos);
            request.setAttribute("eventos", eventosJson);
            request.setAttribute("usuario", idParticipante);
            RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/index.jsp");
            despachante.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(IndexCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
