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

$(document).on("submit", "form[name='bancos']", function(event) {
    event.preventDefault();

    $.ajax({
        type: $(this).attr("method"),
        url: $(this).attr("action"),
        data: new FormData(this),
        cache: false,
        contentType: false,
        processData: false,
        success: function(response) {
            mostrarMensajeExito("Guardado Exitosamente");

            $.get("/bancos-list", function(data) {
                $("#tbgestionbancos").html(data);
            });

            $("#modalgestionbancos").modal("hide");
        },
        error: function(xhr, status, error) {
            mostrarMensajeError("Error al guardar el banco");
        }
    });
});

$(document).on("click", ".btnguardar", function () {
    setTimeout(function () {
        window.location.reload();
    }, 1500);
});

function mostrarMensajeExito(mensaje) {
    Swal.fire({
        position: "top-center",
        icon: "success",
        title: mensaje,
        showConfirmButton: false,
        timer: 1500
    });
}

function mostrarMensajeError(mensaje) {
    Swal.fire({
        position: "top-center",
        icon: "error",
        title: mensaje,
        showConfirmButton: false,
        timer: 1500
    });
}