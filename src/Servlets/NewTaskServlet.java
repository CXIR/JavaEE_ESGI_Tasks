package Servlets;

import DAO.DAO;
import Models.Priority;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@WebServlet( name        = "NewTaskServlet",
             urlPatterns = "/New" )
public class NewTaskServlet extends HttpServlet {

    private static final String CONF_DAO = "dao";
    private DAO dao;

    public void init() throws ServletException {
        this.dao = (DAO) getServletContext().getAttribute(CONF_DAO);
    }

    public NewTaskServlet(){
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String taskTitle = request.getParameter("title");
        String taskDescription = request.getParameter("description");
        String taskDeadline = request.getParameter("deadline");
        String taskPriority = request.getParameter("priority");
        String message;

        List<Priority> priorities = this.dao.getAllPriority();
        String title = "Nouvelle Täche";

        if(taskTitle == null || taskTitle.trim().length() == 0) message = "Veuillez renseigner un titre !";
        else if(taskDescription == null || taskDescription.trim().length() == 0) message = "Veuillez décrire la tâche";
        else if(taskDeadline == null || taskDeadline.trim().length() == 0) message = "Veuillez indiquer une échéance";

        else if(Integer.parseInt(taskPriority) == 0) message = "Vous n'avez pas sélectionner de Priorité !";
        else {

            try {
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                Date date = format.parse(taskDeadline);

                dao.createTask(taskTitle,taskDescription,date, dao.getPriorityByID(Integer.parseInt(taskPriority)));

                message = "Tâche créée !";
            }
            catch (ParseException e) {
                e.printStackTrace();

                message = "Le format de la date est incorrect, veuillez suivre les instructions";
            }

        }


        request.setAttribute("title", title);
        request.setAttribute("priorities",priorities);

        request.setAttribute("message",message);

        this.getServletContext().getRequestDispatcher("/new.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        List<Priority> priorities = this.dao.getAllPriority();
        String title = "Nouvelle Täche";

        request.setAttribute("title", title);
        request.setAttribute("priorities",priorities);

        this.getServletContext().getRequestDispatcher("/new.jsp").forward(request,response);
    }
}
