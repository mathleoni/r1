<%@include file="/WEB-INF/jspf/cabecalho.jspf" %>
<title>Amigo Oculto - DCC192</title>
</head>

<body>

    
    <br/>
    <h3 class="text-center">Lista de Participantes do Evento: ${evento.titulo}</h3>
    <br/>
    <table class="table table-hover">
        <thead>
            <tr class="text-center">
                <th>Nome</th>
                <th>E-mail</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="participante" items="${participantes}">
                <tr class="text-center">
                    <td>${participante.nome}</td> 
                    <td>${participante.email}</td> 
                </tr>  
            </c:forEach>
        </tbody>

    </table>

    <%@include file="/WEB-INF/jspf/rodape.jspf" %>