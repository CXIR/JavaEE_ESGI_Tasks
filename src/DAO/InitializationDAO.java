package DAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitializationDAO implements ServletContextListener {

    private DAO dao;
    private static final String ATT_DAO = "dao";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();
        this.dao = DAO.getInstance();
        servletContext.setAttribute(ATT_DAO,this.dao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
