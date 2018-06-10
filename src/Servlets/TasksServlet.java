package Servlets;

import DAO.DAO;
import Models.Priority;
import Models.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@WebServlet( name        ="TasksServlet",
             urlPatterns = "/Tasks")
public class TasksServlet extends javax.servlet.http.HttpServlet {

    private static final String CONF_DAO = "dao";
    private DAO dao;

    public void init() throws ServletException {
        this.dao = (DAO) getServletContext().getAttribute(CONF_DAO);
    }

    public TasksServlet(){
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String taskTitle = request.getParameter("title");
        String taskDescription = request.getParameter("description");
        String taskDeadline = request.getParameter("deadline");
        String taskPriority = request.getParameter("priority");
        String taskID = request.getParameter("ID");

        if(taskID != null || taskID.trim().length() > 0){

            Task task = this.dao.getTaskByID( Integer.parseInt(taskID) );

            if(taskTitle != null || taskTitle.trim().length() > 0) {
                if( task.getName() != taskTitle ){
                   this.dao.updateName(task,taskTitle);
                }
            }

            if(taskDescription != null || taskDescription.trim().length() > 0) {
                if( task.getDescription() != taskDescription ){
                    this.dao.updateDescription(task,taskDescription);
                }
            }


            if(Integer.parseInt(taskPriority) != 0) {
                if( task.getPriority().getID() != Integer.parseInt(taskPriority) ){
                    Priority priority = this.dao.getPriorityByID( Integer.parseInt(taskPriority) );
                    this.dao.updatePriority(task,priority);
                }
            }


            if(taskDeadline != null || taskDeadline.trim().length() > 0) {

                try {
                    DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                    Date date = format.parse(taskDeadline);

                    if(task.getDeadline() != date){
                        this.dao.updateDeadline(task,date);
                    }

                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }

             response.sendRedirect("/Tasks");

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String title = "Tâches";
        int category = 0;
        int edit = 0;

        List<Task> tasks;

        String categoryParameter = request.getParameter("category");
        String dropParameter = request.getParameter("drop");
        String editParameter = request.getParameter("edit");

        if(dropParameter != null){
            int taskID = Integer.parseInt(dropParameter);
            this.dao.deleteTask( this.dao.getTaskByID(taskID));
        }

        if(editParameter != null){
            edit = Integer.parseInt(editParameter);
        }

        if (categoryParameter != null ){
            category = Integer.parseInt(categoryParameter);
            tasks = this.dao.getTaskByPriority(category);

        }
        else tasks = dao.getAllTask();

        List<Priority> priorities = this.dao.getAllPriority();

        request.setAttribute("title", title);
        request.setAttribute("category",category);
        request.setAttribute("edit",edit);

        request.setAttribute("tasks",tasks);
        request.setAttribute("priorities",priorities);

        this.getServletContext().getRequestDispatcher("/tasks.jsp").forward(request,response);

    }
}
