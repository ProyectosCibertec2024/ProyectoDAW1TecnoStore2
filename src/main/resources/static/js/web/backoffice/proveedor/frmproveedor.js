$(document).on("click", ".btnnuevo", function () {
   $("#modalproveedor").modal("show");
   cargarComboEmpresa(0);
   $("#txtidproveedor").val("");
   $("#txtnomproveedor").val("");
   $("#txttelefonoprov").val("");
   $("#txtcorreoprov").val("");
   $("#cboEmpresa").empty();
   $("#swproveedor").hide();
   $("#swactivoprov").prop("checked", true);
   obtenerIdProveedor()
});

$(document).on("click", ".btnactualizar", function () {
   $("#cboEmpresa").empty();
   $("#swproveedor").show();
   $("#txtidproveedor").val("");
   $.ajax({
      type: "GET",
      url: "/proveedor/" + $(this).attr("data-idproveedor"),
      dataType: "json",
      success: function (resultado) {
         $("#txtidproveedor").val(resultado.idproveedor);
         $("#txtnomproveedor").val(resultado.nomproveedor);
         $("#txttelefonoprov").val(resultado.telefono);
         $("#txtcorreoprov").val(resultado.correo);
         $("#cboEmpresa").val(resultado.idempresa);
         cargarComboEmpresa(resultado.idempresa);
         if (resultado.estado) {
            $("#swactivoprov").prop("checked", true);
         }else{
            $("#swactivoprov").prop("checked", false);
         }
         $("#modalproveedor").modal("show");
      }
   })
});

$(document).on("click", ".btnguardar", function () {
   event.preventDefault();
   if(validarProveedor()) {
      $.ajax({
         type: "POST",
         url: "/proveedor",
         contentType: "application/json",
         data: JSON.stringify({
            idproveedor: $("#txtidproveedor").val(),
            nomproveedor: $("#txtnomproveedor").val(),
            telefono: $("#txttelefonoprov").val(),
            correo: $("#txtcorreoprov").val(),
            estado: $("#swactivoprov").prop("checked"),
            idempresa: $("#cboEmpresa").val()
         }),
         success: function (resultado) {
            if(resultado.resultado) {
               alert(resultado.mensaje);
               listarProveedores();
               $("#modalproveedor").modal("hide");
            }else{
               alert(resultado.mensaje);
            }
         }
      });
   }

});

/*CARGAR COMBO EMPRESA*/
function cargarComboEmpresa(idempresa) {
   $.ajax({
      type: "GET",
      url: "/empresa-list",
      dataType: "json",
      success: function (resultado) {
         $.each(resultado, function (index, value) {
            $("#cboEmpresa").append(
                ` <option value="${value.idempresa}">${value.nomempresa}</option>`
            )
         });
         if(idempresa > 0) {
            $("#cboEmpresa").val(idempresa);
         }
      }
   });
}

/*FUNCION PARA LISTAR TODOS LOS PROVEEDORES*/
function listarProveedores() {
   $.ajax({
      type: "GET",
      url: "/proveedor-list",
      dataType: "json",
      success: function (resultado) {
         $("#tbproveedor > tbody").html("");
         $.each(resultado, function (index, value) {
            $("#tbproveedor > tbody").append(
                `<tr>
                    <td>${value.idproveedor}</td>
                    <td>${value.nomproveedor}</td>
                    <td>${value.telefono}</td>
                    <td>${value.correo}</td>
                    <td>${value.empresa.nomempresa}</td>
                    <td class="d-flex justify-content-center align-content-center">
                        <button class="btn btn-info btnactualizar"
                                data-idproveedor="${value.idproveedor}"><i class="fas fa-edit"></i></button>
                    </td>
                </tr>`
            )
         });
      }
   });
}

/*FUNCION PARA OBTENER EL ID AUTOGENERADO*/
function obtenerIdProveedor() {
   $.ajax({
      type: "GET",
      url: "/idproveedor",
      dataType: "json",
      success: function (resultado) {
         $("#txtidproveedor").val(resultado);
      }
   });
}

function validarProveedor() {
   let nomprov = document.getElementById("validnomproveedor");
   let fono = document.getElementById("validtelefonoprov");
   let correo = document.getElementById("validcorreoprov");

   if(document.provedor.txtnomproveedor.value === "") {
      nomprov.innerText = "Campo Requerido";
      return false;
   }else {
      nomprov.innerText = "";
   }

   if (document.provedor.txttelefonoprov.value === "") {
      fono.innerText = "Campo Requerido";
      return false;
   }else if (!document.provedor.txttelefonoprov.value.match(/^\d{9,11}$/)) {
      fono.innerText = "El Telefono debe tener entre 9 y 11 digitos";
      return false;
   }else {
      fono.innerText = "";
   }

   if(document.provedor.txtcorreoprov.value === "") {
      correo.innerText = "Campo Requerido";
      return false;
   }else if (!document.provedor.txtcorreoprov.value.match(/^[^\s@]+@[^\s@]+\.[^\s@]+$/)) {
      correo.innerText = "Correo Invalido";
      return false;
   }else {
      correo.innerText = "";
   }

   return true;
}

