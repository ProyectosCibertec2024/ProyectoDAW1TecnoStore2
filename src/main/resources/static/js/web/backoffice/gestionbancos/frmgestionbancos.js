$(document).on("click",".btnnuevo", function () {
    $("#modalgestionbancos").modal("show");
    $("#actualizar").val("false");
    $("#txtidempresa").val(0);
    $(".check-menu").prop("checked", false);
});

$(document).on("click",".btnactualizar", function () {
    $(".check-menu").prop("checked", false);
    $("#actualizar").val("true");
    var idempresa = parseInt($(this).attr("data-idempresa"));
    $.ajax({
        type: "GET",
        url: "/gestionbancos/" + idempresa,
        dataType: "json",
        success: function (resultado) {
            $("#txtidempresa").val(resultado.idempresa);
            var idtipobancos = resultado.tipoBancoList.map(function (value) {
                return value.idtipobanco;
            });
            idtipobancos.forEach(function (idtipobanco) {
                $("#banco_" + idtipobanco).prop("checked", true);
            });
            $("#modalgestionbancos").modal("show");
        }
    });

});