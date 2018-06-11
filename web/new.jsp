<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="Models.Priority" %>
<%@ page import="java.util.List" %>
<%@ page pageEncoding="UTF-8" %>

<%--
  Created by IntelliJ IDEA.
  User: ludwigroger
  Date: 10/06/2018
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="header.jsp"/>
    <title><%= request.getAttribute("title") %></title>
</head>
<body>

    <jsp:include page="navbar.jsp">
        <jsp:param name="tab" value="2"/>
    </jsp:include>

    <div class="wrap">

        <a class="btn btn-link btn-lg" href="/Tasks">< Retour aux Tâches</a>

        <br/>
        <br/>

        <form class="mini-form" method="post" action="/New">
            <fieldset>
                <legend>Créer une Tâche</legend>

                <h6 class="text-danger"><%= request.getAttribute("error") != null ? (String) request.getAttribute("error") : ""%></h6>
                <h6 class="text-success"><%= request.getAttribute("success") != null ? (String) request.getAttribute("success") : ""%></h6>

                <div class="form-group">
                    <label for="title">Titre :</label>
                    <input type="text" class="form-control" id="title" placeholder="Titre..." name="title" value="<c:out value="${ param.title }"/>">
                </div>
                <div class="form-group">
                    <label for="description">Description :</label>
                    <textarea class="form-control" id="description" rows="3" placeholder="Description..." name="description" value="<c:out value="${ param.description }"/>"></textarea>
                </div>
                <div class="form-group">
                    <label for="deadline">Échéance : <em class="text-muted">jj/mm/aaaa</em></label>
                    <input type="date" class="form-control" id="deadline" placeholder="Échéance..." name="deadline" value="<c:out value="${ param.deadline }"/>">
                </div>
                <div class="form-group">
                    <label for="priority">Priorité :</label>
                    <select class="custom-select" id="priority" name="priority">
                        <option value="0"> -- Priorité -- </option>
                        <% for(Priority priority : (List<Priority>) request.getAttribute("priorities")) { %>
                        <option value="<%= priority.getID() %>"><%= priority.getName() %></option>
                        <% } %>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Créer</button>
                <a class="btn btn-danger text-white" href="/New">Effacer</a>
            </fieldset>
        </form>

    </div>



</body>
</html>
