
<%@include file="jspf/cabecalho.jspf" %>

<title>Amigo Oculto</title>
</head>
<body>
    <table class="table table-hover">
        <thead>
            <tr class="text-center">
                <th>Título</th>
                <th>Valor Mínimo</th>
                <th>Data do Sorteio</th>
                <th>Data do Evento</th>
            </tr>
        </thead>
        <tbody>
            <tr class="text-center">
                <td>${evento.titulo}</td> 
                <td>${evento.dataSorteio}</td> 
                <td>${evento.dataEvento}</td> 
                <td>${evento.valorMinimo}</td>
            </tr>  
        </tbody>

    </table>
    <br/>
    <br/>
    <br/>
<div class="text-center text-success">
    <p><h4>Seu amigo oculto é: ${amigo.nome}</h4></p>
    <p><h4>Email: ${amigo.email}</h4></p>
</div>
<br/>
<br/>
<br/>            
<%@include file="jspf/rodape.jspf" %>

