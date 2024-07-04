$(document).on("click", ".btnnuevo", function () {
    $("#modalusuario").modal("show");
    $("#swusuario").hide();
    $("#swactivouser").prop("checked", true)
    $("#txtidusuario").val("");
    $("#txtnomusuario").val("");
    $("#txtusernameusuario").val("");
    $("#txtpasswordusuario").val("");
    $("#txtreppassword").val("");
    $("#txtdniusuario").val("");
    $("#cboRol").val(0);
    obtenerIdUsuario();
});

$(document).on("click", ".btnactualizar", function () {
    $("#txtidusuario").val("");
    $("#swusuario").show();

    var idusuario = parseInt($(this).attr("data-idusuario"));
    $.ajax({
        type: "GET",
        url: "/usuario-gestion/" + idusuario,
        dataType: "json",
        success: function (resultado) {
            $("#txtidusuario").val(resultado.idusuario);
            $("#txtnomusuario").val(resultado.nombre);
            $("#txtusernameusuario").val(resultado.username);
            $("#txtreppassword").val(resultado.rep_pass);
            $("#txtdniusuario").val(resultado.dni);
            $("#cboRol").val(resultado.idrol);
            if(resultado.activo) {
                $("#swactivouser").prop("checked", true);
            }else{
                $("#swactivouser").prop("checked", false);
            }
            $("#modalusuario").modal("show");
        }
    });
});

function obtenerIdUsuario() {
    $.ajax({
        type: "GET",
        url: "/idusuario",
        dataType: "json",
        success: function (resultado) {
            $("#txtidusuario").val(parseInt(resultado));
        }
    })
}

$(document).on("submit", "form[name='usuario']", function(event) {
    event.preventDefault();
    if(validarUsuario()) {
        $.ajax({
            type: $(this).attr("method"),
            url: $(this).attr("action"),
            data: new FormData(this),
            cache: false,
            contentType: false,
            processData: false,
            success: function (response) {
                mostrarMensajeExito("Guardado Exitosamente");

                $.get("/usuario-list", function (data) {
                    $("#tbusuario").html(data);
                });

                $("#modalusuario").modal("hide");
                setTimeout(function () {
                    window.location.reload();
                }, 1500);
            },
            error: function (xhr, status, error) {
                mostrarMensajeError("Error al guardar el usuario");
            }
        });
    }
});

function validarUsuario() {
    var nombre = $('#txtnomusuario').val();
    var email = $('#txtusernameusuario').val();
    var repPassword = $('#txtreppassword').val();
    var dni = $('#txtdniusuario').val();

    if (!nombre.trim()) {
        $('#validnomusuario').text('Debe ingresar un nombre').show();
        return false;
    } else {
        $('#validnomusuario').text('').hide();
    }

    if (!email.trim()) {
        $('#validusernameusuario').text('Debe ingresar un email').show();
        return false;
    } else {
        $('#validusernameusuario').text('').hide();
    }

    if (!repPassword.trim()) {
        $('#validreppassword').text('Ingrese La contraseña').show();
        return false;
    } else {
        $('#validreppassword').text('').hide();
    }

    if (!dni.trim()) {
        $('#validdniusuario').text('Debe ingresar un DNI').show();
        return false;
    }else if(!dni.match("^\\d{8}$")) {
        $('#validdniusuario').text('El DNI debe tener 8 digitos').show();
        return false;
    } else {
        $('#validdniusuario').text('').hide();
    }

    return true;
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

