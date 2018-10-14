<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/jspf/cabecalho.jspf" %>


<title>Amigo Oculto - DCC192</title>
</head>

<body>

  
    <br/>
    <h3 class="text-center">Lista de Eventos que você criou</h3>
    <br/>
    <table class="table table-hover">
        <thead>
            <tr class="text-center">
                <th>Título</th>
                <th>Valor Mínimo</th>
                <th>Data do Sorteio</th>
                <th>Data do Evento</th>
                <th colspan="3">Opções</th>
                
            </tr>
        </thead>
        <tbody>
            <c:forEach var="evento" items="${eventos}">
                <tr class="text-center">
                    <td>${evento.titulo}</td> 
                    <td>${evento.dataSorteio}</td> 
                    <td>${evento.dataEvento}</td> 
                    <td>${evento.valorMinimo}</td>
                    <c:choose>
                        <c:when test="${evento.totalParticipantes == total && not evento.sorteado}">
                            <td><a href="inscritos.html?codEvento=${evento.codigo}&usuario=${usuario}" class="font-weight-bold">Ver Participantes</a></td> 
                            <td><a href="sorteio.html?codEvento=${evento.codigo}&usuario=${usuario}">Realizar Sorteio</a></td>
                        </c:when>
                        <c:when test="${evento.totalParticipantes<total && not evento.sorteado}">
                            <td><a href="inscricao.html?codEvento=${evento.codigo}&usuario=${usuario}" class="text-success font-weight-bold">Adicionar Participantes</a></td> 
                            <td><a href="inscritos.html?codEvento=${evento.codigo}&usuario=${usuario}" class="font-weight-bold">Ver Participantes</a></td> 
                            <td><a href="sorteio.html?codEvento=${evento.codigo}&usuario=${usuario}">Realizar Sorteio</a></td>
                        </c:when>
                        <c:otherwise>
                            <td colspan="3"><a href="inscritos.html?codEvento=${evento.codigo}&usuario=${usuario}" class="font-weight-bold">Ver Participantes</a></td> 
                        </c:otherwise>
                    </c:choose>
                            
                </tr>  
            </c:forEach>
        </tbody>

    </table>

    <%@include file="/WEB-INF/jspf/rodape.jspf" %>