var addCols = function (eventos) {
    for (var i = 0; i < eventos.length; i++) {
        var day = eventos[i].dataEvento.day;
        var month = eventos[i].dataEvento.month - 1;
        var yearS = eventos[i].dataEvento.year;
        var dayS = eventos[i].dataSorteio.day;
        var monthS = eventos[i].dataSorteio.month - 1;
        var year = eventos[i].dataSorteio.year;
        var color = eventos[i].sorteado == true ? "success" : "info";
        var amigo = eventos[i].sorteado == true ? '<h6><a href="amigo.html?usuario=' + usuario + '&evento='+eventos[i].codigo+'" class="btn btn-primary">Ver Amigo Oculto Sorteado</a></h6>' : "";
        var myCol = $('<div class="col-sm-3 col-md-3 col-lg-3 pb-2"></div>');
        var myPanel = $('<div class="card text-white bg-' + color + ' mb-3" style="max-width: 16rem">'
                + '<div class="card-header"><h4>' + eventos[i].titulo + '</h4></div>'
                + '<div class="card-body">'
                + '<h6>Valor Mínimo: R$ ' + parseFloat(eventos[i].valorMinimo).toFixed(2) + '</h6>'
                + '<h6>Data do Evento: ' + new Date(year, month, day).toLocaleDateString() + '</h6>'
                + '<h6>Data do Sorteio: ' + new Date(yearS, monthS, dayS).toLocaleDateString() + '</h6>'
                + amigo
                + '</div>'
                + '</div>');
        myPanel.appendTo(myCol);
        myCol.appendTo('#contentPanel');
    }
}


$(document).ready(function () {
    addCols(eventos);
});


