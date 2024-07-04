$(document).on("click", ".btnnuevo", function (){
    limpiarValidacionCategoria();
    $("#modalcategoria").modal("show");
    $("#txtidcategoria").val("");
    $("#txtnomcategoria").val("");
    obtenerId()
});

$(document).on("click", ".btnactualizar", function (){
    limpiarValidacionCategoria();
    $("#txtidcategoria").val("");
    $("#txtnomcategoria").val("")
    $.ajax({
        type: "GET",
        url: "/categoria/" + $(this).attr("data-idcategoria"),
        dataType: "json",
        success : function (resultado) {
            $("#txtidcategoria").val(resultado.idcategoria);
            $("#txtnomcategoria").val(resultado.descripcion);
            $("#modalcategoria").modal("show");
        }
    });
});

$(document).on("click", ".btnguardar", function (){
    event.preventDefault();
    if(validarCategoria()) {
        $.ajax({
            type: "POST",
            url: "/categoria",
            contentType: "application/json",
            data: JSON.stringify({
                idcategoria: $("#txtidcategoria").val(),
                descripcion: $("#txtnomcategoria").val()
            }),
            success: function (resultado) {
                if(resultado.resultado) {
                    listaCategorias();
                    mostrarMensajeExito(resultado.mensaje);
                    $("#modalcategoria").modal("hide");
                }else {
                    mostrarMensajeError(resultado.mensaje);
                }
            }
        });
    }
});

function listaCategorias() {
    $.ajax({
        type: "GET",
        url: "/categoria-list",
        dataType: "json",
        success: function (resultado) {
            $("#tbcategoria > tbody").html("")
            $.each(resultado, function (index, value) {
                $("#tbcategoria > tbody").append(
                    `<tr>`+
                        ` <td>${value.idcategoria}</td>` +
                        ` <td>${value.descripcion}</td>` +
                        ` <td class="d-flex justify-content-center align-content-center">` +
                            ` <button class="btn btn-info btnactualizar me-1" data-idcategoria="${value.idcategoria}"><i class="fas fa-edit"></i></button>`+
                            ` <button class="btn btn-danger btneliminar" data-idcategoria="${value.idcategoria}"><i class="fas fa-trash-alt"></i></button>`+
                        ` </td>` +
                    ` </tr>`
                )
            });
        }
    });
}

$(document).on("click", ".btneliminar", function () {
    let idCategoria = $(this).attr("data-idcategoria");

    Swal.fire({
        title: '¿Estás seguro?',
        text: "Esta acción no se puede deshacer. ¿Quieres eliminar esta categoría?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                type: "GET",
                url: "/categoria-eliminar/" + idCategoria,
                dataType: "json",
                success: function (resultado) {
                    if (resultado.resultado) {
                        mostrarMensajeExito(resultado.mensaje);
                        listaCategorias();
                    } else {
                        mostrarMensajeError(resultado.mensaje);
                    }
                },
                error: function () {
                    mostrarMensajeError("Error al eliminar la categoría.");
                }
            });
        }
    });
});

function obtenerId() {
    $("#txtidcategoria").val("");
    $.ajax({
        type: "GET",
        url: "/idcategoria",
        dataType: "json",
        success: function (resultado) {
            $("#txtidcategoria").val(resultado)
        }
    });
}

function validarCategoria() {
    let nomcategoria = document.getElementById("validnomcategoria");

    if(document.categoria.txtnomcategoria.value === "") {
        nomcategoria.innerText = "Campo Requerido";
        return false;
    }else {
        nomcategoria.innerText = "";
    }
    return true;
}

function limpiarValidacionCategoria() {
    let nomcategoria = document.getElementById("validnomcategoria");
    nomcategoria.innerText = "";
}

/*MENSAJES*/

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