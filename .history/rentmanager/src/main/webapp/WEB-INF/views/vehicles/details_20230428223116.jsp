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
                <!-- Main content -->
                <section class="content">
            
                    <div class="row">
                        <div class="col-md-3">
            
                            <!-- Profile Image -->
                            <div class="box box-primary">
                                <div class="box-body box-profile">
                                    <h3 class="profile-username text-center">${vehicle.constructeur} ${vehicle.modele}</h3>
                                    <p class="text-center">${vehicle.nb_places} places</p>
            
                                    <ul class="list-group list-group-unbordered">
                                        <li class="list-group-item">
                                            <b>Réservation(s)</b> <a class="pull-right">${reservations.size()}</a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Client(s)</b> <a class="pull-right">${clients.size()}</a>
                                        </li>
                                    </ul>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>
                        <!-- /.col -->
                        <div class="col-md-9">
                            <div class="nav-tabs-custom">
                                <ul class="nav nav-tabs">
                                    <li class="active"><a href="#rents" data-toggle="tab">Réservation(s)</a></li>
                                    <li><a href="#clients" data-toggle="tab">Client(s)</a></li>
                                </ul>
                                <div class="tab-content">
                                    <div class="active tab-pane" id="rents">
                                        <div class="box-body no-padding">
                                            <table class="table table-striped">
                                                <tr>
                                                    <th style="width: 10px">#</th>
                                                    <th>Client</th>
                                                    <th>Date de début</th>
                                                    <th>Date de fin</th>
                                                </tr>
                                                <c:forEach items="${reservations}" var="reservation">
                                                    <tr>
                                                        <td>${reservation.id}</td>
                                                        <td>${reservation.client.nom} ${reservation.client.prenom}</td>
                                                        <td>${reservation.debut}</td>
                                                        <td>${reservation.fin}</td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </div>
                                    </div>
                                    <!-- /.tab-pane -->
                                    <div class="tab-pane" id="cars">
                                        <!-- /.box-header -->
                                        <div class="box-body no-padding">
                                            <table class="table table-striped">
                                                <tr>
                                                    <th style="width: 10px">#</th>
                                                    <th>Nom</th>
                                                    <th>Prénom</th>
                                                    <th style=>Date de naissance</th>
                                                </tr>
                                                <c:forEach items="${clients}" var="client">
                                                    <tr>
                                                        <td>${client.id}</td>
                                                        <td>${client.nom}</td>
                                                        <td>${client.prenom}</td>
                                                        <td>${client.naissance}</td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </div>
                                    </div>
                                    <!-- /.tab-pane -->
                                </div>
                                <!-- /.tab-content -->
                            </div>
                            <!-- /.nav-tabs-custom -->
                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- /.row -->
            
                </section>
                <!-- /.content -->
            </div>

            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <!-- ./wrapper -->

        <%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
    </body>
</html>
