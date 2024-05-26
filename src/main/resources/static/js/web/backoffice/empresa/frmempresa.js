/*Nueva Empresa - Modal*/
$(document).on("click",".btnnuevo",function () {
    $("#txtidempresa").val("");
    $("#modalempresa").modal("show");
    limpiarValidacionEmpresa();
    obtenerId();
    $("#txtnomempresa").val("");
    $("#txtdireccion").val("");
    $("#txttelefono").val("");
    $("#cboTipoEmpresa").empty();
    $("#txtemail").val("");
    $("#txtruc").val("");
    $("#switchempresa").hide();
    $("#swactivo").prop("checked", true);
    cargarTipoEmpresa(0);
});

/*Modificar Empresa - Modal*/
$(document).on("click", ".btnactualizar", function () {
    $("#txtidempresa").val("");
    limpiarValidacionEmpresa();
    $("#switchempresa").show();
    $("#cboTipoEmpresa").empty();
    $.ajax({
        type: "GET",
        url: "/empresa/" + $(this).attr("data-idempresa"),
        dataType: "json",
        success: function (resultado) {
            $("#txtidempresa").val(resultado.idempresa);
            cargarTipoEmpresa(resultado.idtipoempresa)
            $("#txtnomempresa").val(resultado.nomempresa);
            $("#txtdireccion").val(resultado.direccion);
            $("#txttelefono").val(resultado.telefono);
            $("#cboTipoEmpresa").val(resultado.idtipoempresa);
            $("#txtemail").val(resultado.email);
            $("#txtruc").val(resultado.ruc);
            if(resultado.activo) {
                $("#swactivo").prop("checked", true);
            }else {
                $("#swactivo").prop("checked", false);
            }
            $("#modalempresa").modal("show");
        }
    });
});

/*OPERACIONES*/

/*REGISTRAR EMPRESA*/
$(document).on("click", ".btnguardar", function () {
    event.preventDefault();
    if(validarEmpresa() === true) {
        $.ajax({
            type : "POST",
            url : "/empresa",
            contentType : "application/json",
            data : JSON.stringify({
                idempresa: $("#txtidempresa").val(),
                nomempresa: $("#txtnomempresa").val(),
                direccion: $("#txtdireccion").val(),
                telefono: $("#txttelefono").val(),
                idtipoempresa: $("#cboTipoEmpresa").val(),
                email: $("#txtemail").val(),
                ruc: $("#txtruc").val(),
                activo: $("#swactivo").prop("checked")
            }),
            success : function (resultado) {
                if(resultado.resultado) {
                    listadoEmpresas();
                    alert(resultado.mensaje)
                    $("#modalempresa").modal("hide");
                }else{
                    alert(resultado.mensaje)
                }
            }
        });
    }
});

/*LISTA DE EMPRESAS*/
function listadoEmpresas() {
    $.ajax({
        type: "GET",
        url: "/empresa-list",
        dataType: "json",
        success: function (resultado) {
            $("#tbempresa > tbody").html("");
            $.each(resultado, function (index, value) {
                $("#tbempresa > tbody").append(
                    ` <tr>`+
                        ` <td>${value.idempresa}</td>` +
                        ` <td>${value.nomempresa}</td>` +
                        ` <td>${value.direccion}</td>` +
                        ` <td>${value.email}</td>` +
                        ` <td>${value.telefono}</td>` +
                        ` <td>${value.ruc}</td>` +
                        ` <td class="d-flex justify-content-center align-content-center">`+
                            ` <button class="btn btn-info btnactualizar"
                                data-idempresa="${value.idempresa}"><i class="fas fa-edit"></i></button>` +
                        ` </td>` +
                    `</tr>`
                );
            });
        }
    });
}

/*CARGAR COMBO TIPO EMPRESA*/
function cargarTipoEmpresa(idtipoempresa) {
    $.ajax({
        type: "GET",
        url: "/tipoempresa-list",
        dataType : "json",
        success : function (resultado) {
            $.each(resultado, function (index, value) {
                $("#cboTipoEmpresa").append(
                    `<option value="${value.idtipoempresa}">${value.nomempresa}</option>`
                );
            });
            if(idtipoempresa > 0) {
                $("#cboTipoEmpresa").val(idtipoempresa);
            }
        }
    });
}

/*FUNCION DE OBTENER ID GENERADO*/
function obtenerId() {
    $.ajax({
        type: "GET",
        url: "/idempresa",
        dataType: "json",
        success : function (resultado) {
            $("#txtidempresa").val(resultado)
        }
    })
}

/*Funcion de validacion*/
function validarEmpresa() {
    let nomempresa = document.getElementById("validnomempresa");
    let direccion = document.getElementById("validdireccion");
    let telefono = document.getElementById("validtelefono");
    let email = document.getElementById("validemail");
    let ruc = document.getElementById("validruc");

    if(document.empresa.txtnomempresa.value === "") {
        nomempresa.innerText = "Complete Este Campo";
        return false;
    }else {
        nomempresa.innerText = "";
    }

    if(document.empresa.txtdireccion.value === "") {
        direccion.innerText = "Complete Este Campo";
        return false;
    }else {
        direccion.innerText = "";
    }

    if(document.empresa.txttelefono.value === "") {
        telefono.innerText = "Complete Este Campo";
        return false;
    }else
        if(!document.empresa.txttelefono.value.match(/^\d{9,11}$/)) {
            telefono.innerText = "Telefono Invalido, longitud es de 9 a 11 digitos";
            return false;
        }else {
            telefono.innerText = "";
        }

    if(document.empresa.txtemail.value === "") {
        email.innerText = "Completa Este Campo";
        return false;
    }else
        if(!document.empresa.txtemail.value.match(/^[^\s@]+@[^\s@]+\.[^\s@]+$/)) {
            email.innerText = "Ingrese Un Email Valido";
            return false;
        }else {
            email.innerText = "";
        }

    if(document.empresa.txtruc.value === "") {
        ruc.innerText = "Completa Este Campo";
        return false;
    }else
        if (!document.empresa.txtruc.value.match(/^\d{11,13}$/)) {
            ruc.innerText = "Ruc Invalido, ingrese de 11 a 13 digitos";
            return false;
        } else {
            ruc.innerText = "";
        }

    return true;
}

/*LIMPIAR ERRORES DE TEXTO*/
function limpiarValidacionEmpresa() {
    let nomempresa = document.getElementById("validnomempresa");
    let direccion = document.getElementById("validdireccion");
    let telefono = document.getElementById("validtelefono");
    let email = document.getElementById("validemail");
    let ruc = document.getElementById("validruc");

    nomempresa.innerText = "";
    direccion.innerText = "";
    telefono.innerText = "";
    email.innerText = "";
    ruc.innerText = "";
}