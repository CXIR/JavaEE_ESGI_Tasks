package Servlets;

import DAO.DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "IndexServlet")
public class IndexServlet extends HttpServlet {

    private static final String CONF_DAO = "dao";

    private DAO dao;

    public void init() throws ServletException {
        this.dao = (DAO) getServletContext().getAttribute( CONF_DAO );
    }

    public IndexServlet(){
        super();
    }

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        if( dao.getAllPriority().isEmpty()){

            dao.createPriority("Basse");
            dao.createPriority("Moyenne");
            dao.createPriority("Élevée");
            dao.createPriority("Urgente");
        }

        this.getServletContext().getRequestDispatcher( "/WEB-INF/index.jsp" ).forward( request, response );
    }
}
