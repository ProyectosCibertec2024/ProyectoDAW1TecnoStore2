$(document).on("click", ".btnnuevo", function () {
    $("#modalroles").modal("show");
    limpiarValidRoles();
    $("#txtnomrol").val("");
    $("#txtidrol").val("");
    obtenerIdRol();
});

$(document).on("click",".btnguardar", function () {

    if(validarRoles()) {
        $.ajax({
            type: "POST",
            url: "/roles-gestion",
            contentType: "application/json",
            data: JSON.stringify({
                idrol: $("#txtidrol").val(),
                descripcion: $("#txtnomrol").val()
            }),
            success: function (resultado) {
                if(resultado.resultado) {
                    alert(resultado.mensaje)
                    listarRoles();
                    $("#modalroles").modal("hide");
                }else {
                    alert(resultado.mensaje)
                }
            }
        });
    }
});

$(document).on("click",".btnactualizar",function () {
    limpiarValidRoles();
    $("#txtidrol").val("");
    $.ajax({
        type: "GET",
        url: "/rol-gestion/" + $(this).attr("data-idrol"),
        dataType: "json",
        success: function (resultado) {
            $("#txtidrol").val(resultado.idrol);
            $("#txtnomrol").val(resultado.descripcion);
            $("#modalroles").modal("show");
        }
    });
});

function listarRoles() {
    $.ajax({
        type: "GET",
        url: "/rol-list",
        dataType: "json",
        success: function (resultado) {
            $("#tbroles > tbody").html("");
            $.each(resultado, function (index, value) {
                $("#tbroles > tbody").append(
                    `<tr>
                        <td>${value.idrol}</td>
                        <td>${value.descripcion}</td>
                        <td class="text-center">
                            <button class="btn btn-info btnactualizar mt-3"
                                data-idrol="${value.idrol}"><i class="fas fa-edit"></i></button>
                        </td>
                    </tr>`
                );
            });
        }
    });
}

function obtenerIdRol() {
    $.ajax({
        type: "GET",
        url: "/idrol",
        dataType: "json",
        success: function (resultado) {
            $("#txtidrol").val(resultado);
        }
    });
}

/**VALIDAR CAMPO**/
function validarRoles() {
    let nomrol= document.getElementById("txtnomrol");
    let validRol= document.getElementById("validnomrol");

    if(nomrol.value === "") {
        validRol.innerText = "Complete Este Campo"
        return false;
    }
    return true;
}

function limpiarValidRoles() {
    let validRol = document.getElementById("validnomrol");
    validRol.innerText = ""
}