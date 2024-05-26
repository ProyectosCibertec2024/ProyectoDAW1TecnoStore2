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
                    alert(resultado.mensaje);
                    $("#modalcategoria").modal("hide");
                }else {
                    alert(resultado.mensaje);
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
    let mensaje = confirm('¿Deseas Eliminar Esta Categoria?');
    if(mensaje) {
        $.ajax({
            type: "GET",
            url: "/categoria-eliminar/" + $(this).attr("data-idcategoria"),
            dataType: "json",
            success: function (resultado) {
                if(resultado.resultado) {
                    alert(resultado.mensaje);
                    listaCategorias();
                }else {
                    alert(resultado.mensaje);
                }
            }
        });
    }
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