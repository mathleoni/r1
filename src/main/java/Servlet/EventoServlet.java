/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Command.Comando;
import DAO.EventoDAO;
import Model.Evento;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EventoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.verificaSorteio();
        Map<String, String> rotas = new HashMap<>();
        rotas.put("/eventos.html", "Command.EventoCommand");
        rotas.put("/index.html", "Command.LoginCommand");
        rotas.put("/inicial.html", "Command.IndexCommand");
        rotas.put("/registro.html", "Command.RegistroCommand");
        rotas.put("/inscritos.html", "Command.InscritosCommand");
        rotas.put("/inscricao.html", "Command.InscricaoCommand");
        rotas.put("/novo-evento.html", "Command.NovoEventoCommand");
        rotas.put("/amigo.html", "Command.AmigoCommand");
        rotas.put("/sorteio.html", "Command.SorteioCommand");
        
        String clazzName = rotas.get(request.getServletPath());
        try{
            Comando comando = (Comando)Class.forName(clazzName).newInstance();
            comando.exec(request, response);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(EventoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.verificaSorteio();
        Map<String, String> rotas = new HashMap<>();        
        rotas.put("/index.html", "Command.LoginPostCommand");
        rotas.put("/inscricao.html", "Command.InscricaoPostCommand");
        rotas.put("/registro.html", "Command.RegistroPostCommand");
        rotas.put("/novo-evento.html", "Command.NovoEventoPostCommand");
        
        String clazzName = rotas.get(request.getServletPath());
        try{
            Comando comando = (Comando)Class.forName(clazzName).newInstance();
            comando.exec(request, response);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(EventoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void verificaSorteio(){
        
        try {
            List<Evento> eventos = EventoDAO.getInstance().listByDataSorteio();
            if(!eventos.isEmpty()){
                for(Evento evento: eventos){
                    evento.sorteia();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
