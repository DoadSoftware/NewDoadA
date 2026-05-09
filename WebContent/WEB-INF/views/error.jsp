<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

  <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
  <title>Software Expired</title>

  <script type="text/javascript" src="<c:url value='/webjars/jquery/3.6.0/jquery.min.js'/>"></script>
  <script type="text/javascript" src="<c:url value='/webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/javascript/index.js'/>"></script>
  <script type="text/javascript" src="<c:url value='/webjars/npm/select2/4.1.0-rc.0/dist/js/select2.min.js'/>"></script>

  <link rel="stylesheet" href="<c:url value="/resources/css/output.css"/>">
  <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/5.1.3/css/bootstrap.min.css'/>">
  <link rel="stylesheet" href="<c:url value='/webjars/font-awesome/6.5.0/css/all.min.css'/>">
  <link rel="stylesheet" href="<c:url value='/webjars/npm/select2/4.1.0-rc.0/dist/css/select2.min.css'/>">
		
</head>
<body>
<div class="content py-5" style="background-color: #EAE8FF; color: #2E008B">
  <div class="container">
	<div class="row">
	 <div class="col-md-8 offset-md-2">
       <span class="anchor"></span>
         <div class="card card-outline-secondary">
           <div class="card-header">
             <h3 class="mb-0">${error_message}</h3>
           </div>
       </div>
    </div>
  </div>
</div>
</div>
</body>
</html>