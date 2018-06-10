<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="Models.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.Priority" %>

<%--
  Created by IntelliJ IDEA.
  User: ludwigroger
  Date: 10/06/2018
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="header.jsp"/>
    <title>Les Tâches</title>
</head>
<body>

<jsp:include page="navbar.jsp"/>

<div class="wrap">

    <h2><%= request.getAttribute("title") %></h2>

    <br/><br/>

    <!-- tasks list section -->

    <h4>Filtre par Priorité</h4>
    <ul class="nav nav-pills">
        <li class="nav-item">
            <a class=" nav-link
                       <%= (Integer) request.getAttribute("category") == 0 ? "active" : ""%>"
               href="/Tasks">Toutes</a>
        </li>
        <% for(Priority priority : (List<Priority>) request.getAttribute("priorities")) { %>
        <li class="nav-item">
            <a class=" nav-link
                       <%= ( priority.getID() == 10) ? "btn btn-outline-danger" : ""%>
                       <%= ( priority.getID() == (Integer) request.getAttribute("category") ? "active" : "")%>"
               href="/Tasks?category=<%= priority.getID() %>"><%= priority.getName() %></a>
        </li>
        <% } %>
    </ul>

    <br/><br/>

    <table class="table table-striped">
        <thead>
        <tr class="bg-dark text-white">
            <th>ID</th>
            <th>NOM</th>
            <th>DESCRIPTION</th>
            <th>CATEGORIE</th>
            <th>ÉCHÉANCE</th>
            <th>ACTION</th>
        </tr>
        </thead>
        <tbody>

        <%
            for(Task task : (List<Task>) request.getAttribute("tasks")) {

                if( (Integer) request.getAttribute("edit") == task.getID() ) {
        %>

            <tr>
                <form method="post" action="/Tasks">
                    <td>
                        <%= task.getID() %>
                        <input type="hidden" name="ID" value="<%=task.getID()%>">
                    </td>
                    <td>
                        <legend></legend>
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Titre..." name="title" value="<%=task.getName()%>" />
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Description..." name="description" value="<%=task.getDescription()%>"/>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <input type="date" class="form-control" placeholder="Échéance... jj/mm/aaaa" name="deadline" value="<fmt:formatDate value="<%= task.getDeadline() %>" pattern="dd/MM/yyyy" />">
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select class="custom-select" name="priority">
                                <option value="0"> -- Priorité -- </option>
                                <% for(Priority priority : (List<Priority>) request.getAttribute("priorities")) { %>
                                <option value="<%= priority.getID() %>"><%= priority.getName() %></option>
                                <% } %>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="btn-group">
                            <button type="submit" class="btn btn-primary btn-sm float-right">Valider</button>
                            <a class="btn btn-danger btn-sm text-white float-right" href="/Tasks?edit=0">Annuler</a>
                        </div>
                    </td>
                </form>
            </tr>
        <%
            } else {
        %>

        <tr>
          <td><%= task.getID() %></td>
          <td><%= task.getName() %></td>
          <td><%= task.getDescription() %></td>
          <td><%= task.getPriority().getName() %></td>
        <td><fmt:formatDate value="<%= task.getDeadline() %>" pattern="dd MMMM yyyy" /></td>
        <td>
            <a class="btn btn-success btn-sm float-right text-white" href="/Tasks?drop=<%= task.getID() %>">Fait</a>
            <a class="btn btn-info btn-sm float-right text-white" href="/Tasks?edit=<%=task.getID()%>">Éditer</a>
        </td>
        </tr>

        <%
                }
           }
        %>

        </tbody>
    </table>

</div>

</body>
</html>
