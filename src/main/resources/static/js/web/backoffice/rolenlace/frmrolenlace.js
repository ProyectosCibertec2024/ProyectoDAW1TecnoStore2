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

$(document).on("submit", "form[name='rolenlace']", function(event) {
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

            $.get("/rolenlace-list", function(data) {
                $("#tbrolenlace").html(data);
            });

            $("#modalrolenlace").modal("hide");
        },
        error: function(xhr, status, error) {
            mostrarMensajeError("Error al guardar el producto");
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