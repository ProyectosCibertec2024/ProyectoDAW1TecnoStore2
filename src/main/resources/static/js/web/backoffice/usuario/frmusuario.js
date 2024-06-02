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
            //$("#txtpasswordusuario").val(resultado.password);
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

