<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <%@include file="/WEB-INF/views/common/head.jsp"%>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <%@ include file="/WEB-INF/views/common/header.jsp" %>
            <!-- Left side column. contains the logo and sidebar -->
            <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
            <%@ page pageEncoding="UTF-8" %>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Reservations
                    </h1>
                </section>

                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-12">
                            <!-- Horizontal Form -->
                            <div class="box">
                                <!-- form start -->
                                <form class="form-horizontal" method="post">
                                    <div class="box-body">
                                        <div class="form-group">
                                            <label for="car" class="col-sm-2 control-label">Voiture</label>
                
                                            <div class="col-sm-10">
                                                <select class="form-control" id="car" name="car" required>
                                                    <option value="none" selected disabled hidden>Choisir une voiture</option>
                                                    <c:forEach items="${vehicles}" var="vehicle">
                                                        <option value="${vehicle.id}">${vehicle.constructeur} ${vehicle.modele}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="client" class="col-sm-2 control-label">Client</label>
                
                                            <div class="col-sm-10">
                                                <select class="form-control" id="client" name="client" required>
                                                    <option value="none" selected disabled hidden>Choisir un client</option>
                                                    <c:forEach items="${clients}" var="client">
                                                        <option value="${client.id}">${client.nom} ${client.prenom}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="begin" class="col-sm-2 control-label">Date de debut</label>
                
                                            <div class="col-sm-10">
                                                <input type="date" class="form-control" id="begin" name="begin" min="2023-05-01"
                                                    required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="end" class="col-sm-2 control-label">Date de fin</label>
                
                                            <div class="col-sm-10">
                                                <input type="date" class="form-control" id="end" name="end" min="2023-05-01" required>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.box-body -->
                                    <div class="box-footer">
                                        <button type="submit" class="btn btn-info pull-right">Ajouter</button>
                                    </div>
                                    <!-- /.box-footer -->
                                </form>
                            </div>
                            <!-- /.box -->
                        </div>
                        <!-- /.col -->
                    </div>
                </section>
                <!-- /.content -->
            </div>

            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <!-- ./wrapper -->

        <%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
    </body>
</html>
