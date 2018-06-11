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
    private String pageTitle;
    private List<Priority> priorities;

    public void init() throws ServletException {
        this.dao = (DAO) getServletContext().getAttribute(CONF_DAO);
        this.pageTitle = "Nouvelle Tâche";
        this.priorities = dao.getAllPriority();
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
        String error = null;
        String success = null;

        if(taskTitle == null || taskTitle.trim().length() == 0) error = "Veuillez renseigner un titre !";
        else if(taskDescription == null || taskDescription.trim().length() == 0) error = "Veuillez décrire la tâche";
        else if(taskDeadline == null || taskDeadline.trim().length() == 0) error = "Veuillez indiquer une échéance";

        else if(Integer.parseInt(taskPriority) == 0) error = "Vous n'avez pas sélectionner de Priorité !";
        else {

            try {
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                Date date = format.parse(taskDeadline);

                dao.createTask(taskTitle,taskDescription,date, dao.getPriorityByID(Integer.parseInt(taskPriority)));

                success = "Tâche créée !";
            }
            catch (ParseException e) {
                e.printStackTrace();

                error = "Le format de la date est incorrect, veuillez suivre les instructions";
            }

        }


        request.setAttribute("title", this.pageTitle);
        request.setAttribute("priorities",this.priorities);

        request.setAttribute("error",error);
        request.setAttribute("success",success);

        this.getServletContext().getRequestDispatcher("/new.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        request.setAttribute("title", this.pageTitle );
        request.setAttribute("priorities", this.priorities );

        this.getServletContext().getRequestDispatcher("/new.jsp").forward(request,response);
    }
}
