$(document).on("click", ".btnnuevo", function () {
    $("#modalusuario").modal("show");
    $("#swusuario").hide();
    $("#swactivouser").prop("check", true)
    $("#txtidusuario").val("");
    $("#txtnomusuario").val("");
    $("#txtusernameusuario").val("");
    $("#txtpasswordusuario").val("");
    $("#txtreppassword").val("");
    $("#txtdniusuario").val("");
    obtenerIdUsuario();
});

$(document).on("click", ".btnactualizar", function () {
    $("#txtidusuario").val("");
    $("#swusuario").show();
    $.ajax({
        type: "GET",
        url: "/usuario-gestion/" + $(this).attr("data-idusuario"),
        dataType: "json",
        success: function (resultado) {
            $("#txtidusuario").val(resultado.idusuario);
            $("#txtnomusuario").val(resultado.nombe);
            $("#txtusernameusuario").val(resultado.username);
            $("#txtpasswordusuario").val(resultado.password);
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
            $("#txtidusuario").val(resultado);
        }
    })
}

