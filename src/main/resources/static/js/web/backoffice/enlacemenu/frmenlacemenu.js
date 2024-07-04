$(document).on("click",".btnnuevo", function () {
    limpiarValidacion();
    $("#modalenlacemenu").modal("show");
    $("#txtidenlacemenu").val("");
    $("#txtdescripcionenlacemenu").val("");
    $("#txtrutaenlacemenu").val("");
    $("#txticonoenlacemenu").val("");
    cargarIdEnlaceMenu();
});

$(document).on("click",".btnguardar",function () {
    event.preventDefault()
    if (validarEnlaceMenu()) {
        $.ajax({
            type: "POST",
            url: "/menu-gestion",
            contentType: "application/json",
            data: JSON.stringify({
                idenlace: $("#txtidenlacemenu").val(),
                descripcion: $("#txtdescripcionenlacemenu").val(),
                ruta: $("#txtrutaenlacemenu").val(),
                icon: $("#txticonoenlacemenu").val()
            }),
            success: function (resultado) {
                if(resultado.resultado) {
                    mostrarMensajeExito(resultado.mensaje)
                    listadoEnlaceMenu();
                    $("#modalenlacemenu").modal("hide");
                }else{
                    mostrarMensajeError(resultado.mensaje)
                }
            }
        });
    }

});

$(document).on("click",".btnactualizar", function () {
    var idEnlace = parseInt($(this).attr("data-idenlace"));
    $.ajax({
        type: "GET",
        url: "/menu-gestion/" + idEnlace,
        dataType: "json",
        success: function (resultado) {
            $("#txtidenlacemenu").val(resultado.idenlace);
            $("#txtdescripcionenlacemenu").val(resultado.descripcion);
            $("#txtrutaenlacemenu").val(resultado.ruta);
            $("#txticonoenlacemenu").val(resultado.icon);
            $("#modalenlacemenu").modal("show");
        }
    });

});

function listadoEnlaceMenu() {
    $.ajax({
        type: "GET",
        url: "/menus-gestion-list",
        dataType: "json",
        success: function (resultado) {
            $("#tbenlacemenu > tbody").html("");
            $.each(resultado, function (index, value) {
                $("#tbenlacemenu > tbody").append(
                    `<tr>
                        <td>${value.idenlace}</td>
                        <td>${value.descripcion}</td>
                        <td class="text-center">
                            <button class="btn btn-info btnactualizar mt-3"
                                    data-idenlace="${value.idenlace}"><i class="fas fa-edit"></i></button>
                        </td>
                    </tr>`
                )
            });
        }
    });
}

function cargarIdEnlaceMenu() {
    $.ajax({
        type: "GET",
        url: "/idenlacemenu",
        dataType: "json",
        success: function (resultado) {
            $("#txtidenlacemenu").val(resultado);
        }
    });
}

function validarEnlaceMenu() {
    var descripcion = document.getElementById("txtdescripcionenlacemenu");
    var descripcionvalid = document.getElementById("validdescripenlacemenu");
    var ruta = document.getElementById("txtrutaenlacemenu");
    var rutavalid = document.getElementById("validrutaenlacemenu");
    var icono = document.getElementById("txtrutaenlacemenu");
    var iconovalid = document.getElementById("validrutaenlacemenu");

    if(descripcion.value === "") {
        descripcionvalid.innerText = "Complete Este campo";
        return false;
    }else {
        descripcionvalid.innerText = "";
    }

    if (ruta.value === "") {
        rutavalid.innerText = "Complete Este Campo";
        return false;
    }else {
        rutavalid.innerText = "";
    }

    if(icono.value === "") {
        iconovalid.innerText = "Complete Este Campo";
        return false;
    }else {
        iconovalid.innerText = "";
    }

    return true;
}

function limpiarValidacion() {
    var descripcionvalid = document.getElementById("validdescripenlacemenu");
    var rutavalid = document.getElementById("validrutaenlacemenu");
    var iconovalid = document.getElementById("validrutaenlacemenu");

    descripcionvalid.innerText = "";
    rutavalid.innerText = "";
    iconovalid.innerText = "";
}

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