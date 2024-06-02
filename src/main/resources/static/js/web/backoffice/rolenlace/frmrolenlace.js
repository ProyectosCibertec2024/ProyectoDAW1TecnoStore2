$(document).on("click",".btnnuevo",function () {
    $("#modalrolenlace").modal("show");
    $("#actualizar").val("false");
    $(".check-enlace").prop("checked", false);
    $("#cboidrol").val(0);
});

$(document).on("click",".btnactualizar",function () {
    $(".check-enlace").prop("checked", false)
    $("#actualizar").val("true")
    $.ajax({
        type: "GET",
        url: "/rolenlace/" + $(this).attr("data-idrol"),
        dataType: "json",
        success : function (resultado) {
            $("#modalrolenlace").modal("show");
            console.log(resultado);
            $("#cboidrol").val(resultado.idrol);

            var idenlaces = resultado.idenlace.split(",");

            idenlaces.forEach(function(idenlace) {
                $("#menu_" + idenlace.trim()).prop("checked", true);
            });
        }
    });
});