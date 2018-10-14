<%-- 
    Document   : gerenciarEvento
    Created on : 07/06/2018, 15:05:55
    Author     : Rian Alves
--%>
<%@include file="/WEB-INF/jspf/cabecalho.jspf" %>
<script src="js/MoveCombobox.js" ></script>
<title>Gerenciamento do Evento</title>
</head>
<body>

<center><h3>Adicione os Amigos que participarão do Evento</h3></center>
<br/>
<div class="row">

    <div class="col">  
        <label for="origem" class="text-center font-weight-bold">Selecione um participante para o Evento</label>
        <select class="custom-select" size="15" id="origem">
            <c:forEach var="amigo" items="${participantes}">
                <option value="${amigo.codigo}">${amigo.nome} ( ${amigo.email} ) </option>

            </c:forEach>
        </select>

    </div>
    <div class="col btn-group-vertical">

        <button type="button" id="add" class="btn btn-primary" onclick="MoveListBoxItem('origem', 'destino', false)">Adicionar</button>
        <button type="button" id="addAll" class="btn btn-primary" onclick="MoveListBoxItem('origem', 'destino', true)">Adicionar Todos</button>
        <button type="button" id="remove" class="btn btn-danger" onclick="MoveListBoxItem('destino', 'origem', false)">Remover</button>
        <button type="button" id="removeAll" class="btn btn-danger" onclick="MoveListBoxItem('destino', 'origem', true)">Remover Todos</button>
    </div>
    <div class="col">  
        <form method="post">
            <label for="origem" class="text-center font-weight-bold">Participantes Selecionados: </label>
            <select multiple="multiple" class="custom-select" name="selecionados" size="15" id="destino">
            </select> 
            <input type="hidden" value="${codEvento}" name="evento">
            <input type="hidden" value="${usuario}" name="usuario">
            <br/>
            <br/>
            <input type="submit" value="Salvar Selecionados" class="btn btn-success btn-lg" name="acessar" onclick="selectAll('destino')" >

        </form>
    </div>

</div>

<br/>
<br/>

<%@include file="jspf/rodape.jspf" %>