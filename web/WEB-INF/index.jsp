<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
  Created by IntelliJ IDEA.
  User: ludwigroger
  Date: 16/05/2018
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>

<html>
  <head>
    <title>Accueil</title>
    <jsp:include page="../header.jsp"/>
  </head>
  <body>

  <jsp:include page="../navbar.jsp"/>

  <div class="wrap">
    <div class="jumbotron">
      <h1 class="display-3">Les Tâches</h1>
      <p class="lead">Une simple petite application web pour vous permettre de gérer vos tâches !</p>
      <hr class="my-4">
      <p>Groupe 10 - Aimée ELMKIES DUC -Mickaël AFONSO - Ludwig ROGER</p>
      <p class="lead">
        <a class="btn btn-primary btn-lg" href="/Tasks" role="button">C'est parti !</a>
      </p>
    </div>
  </div>

  </body>
</html>
