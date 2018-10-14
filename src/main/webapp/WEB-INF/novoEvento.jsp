<%-- 
    Document   : novoEvento
    Created on : 04/06/2018, 15:46:20
    Author     : Rian Alves
--%>

<%@include file="jspf/cabecalho.jspf" %>
<title>Crie um novo Evento e divirta-se!</title>
</head>
<body>
    <br/>
<center><h3>Insira abaixo os dados solicitados para a criação de um novo Evento</h3></center>
<div class="row">
    <div class="col"></div>
    <div class="col"><br/>

        <form method="post">
            <div class="form-group">
                <label>Título do Evento:</label>
                <input class="form-control" type="text" placeholder="Insira aqui um Título para seu Evento" name="titulo" required="required"/><br/>
            </div>
            <div class="form-group">
                <label>Valor Mínimo do Presente: </label>    
                <input class="form-control" type="text" placeholder="Insira aqui o valor Mínimo do presente" name="minimo" required="required"/><br/>
            </div>
            <div class="form-group">
                <label>Data do Evento:</label>    
                <input class="form-control" type="date" name="evento" required="required"/><br/>
            </div>
            <div class="form-group">
                <label>Data do Sorteio:</label>    
                <input class="form-control" type="date" name="sorteio" required="required"/><br/>
            </div>


            <input type="hidden" value="${usuario}" name="usuario">
            <input type="submit" class="btn btn-success"/>
            <input type="reset" class="btn btn-danger"/>
        </form>
    </div>
    <div class="col"></div>
</div>


<br/>
<br/>
<%@include file="jspf/rodape.jspf" %>
