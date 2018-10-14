package Command;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        try {
            if(request.getAttribute("cadastroSucesso")!=null && request.getAttribute("cadastroSucesso").equals(true)){
                request.setAttribute("cadastroSucesso", true);
            }else{
                request.setAttribute("cadastroSucesso", false);
            }
            request.setAttribute("erroLogin", false);
            RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/login.jsp");
            despachante.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
