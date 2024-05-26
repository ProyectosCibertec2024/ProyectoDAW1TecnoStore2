$(document).on("click", ".btnnuevo", function () {
   $("#modalcliente").modal("show");
   limpiarValidacionCliente();
   $("#txtidcliente").val("");
   $("#txtnomcliente").val("");
   $("#txtapecliente").val("");
   $("#txtdnicliente").val("");
   $("#txtdireccliente").val("");
   $("#txttelcliente").val("");
   $("#swcliente").hide();
   $("#swactivocli").prop("checked", true);
   obtenerIdCliente();
});

$(document).on("click", ".btnactualizar", function () {
   $("#txtidcliente").val("");
   limpiarValidacionCliente();
   $("#swcliente").show();
   $.ajax({
      type: "GET",
      url: "/cliente/" + $(this).attr("data-idcliente"),
      dataType: "json",
      success: function (resultado) {
         $("#txtidcliente").val(resultado.idcliente);
         $("#txtnomcliente").val(resultado.nomcliente);
         $("#txtapecliente").val(resultado.apecliente);
         $("#txtdnicliente").val(resultado.dnicliente);
         $("#txtdireccliente").val(resultado.direccion);
         $("#txttelcliente").val(resultado.telefono);
         if(resultado.activo) {
            $("#swactivocli").prop("checked", true);
         }else {
            $("#swactivocli").prop("checked", false);
         }
         $("#modalcliente").modal("show");
      }
   });
});

$(document).on("click", ".btnguardar", function () {
   event.preventDefault();

   if(validarCliente()) {
      $.ajax({
         type: "POST",
         url: "/cliente",
         contentType: "application/json",
         data: JSON.stringify({
            idcliente: $("#txtidcliente").val(),
            nomcliente: $("#txtnomcliente").val(),
            apecliente: $("#txtapecliente").val(),
            dnicliente: $("#txtdnicliente").val(),
            direccion: $("#txtdireccliente").val(),
            telefono: $("#txttelcliente").val(),
            activo: $("#swactivocli").prop("checked")
         }),
         success: function (resultado) {
            if(resultado.resultado) {
               alert(resultado.mensaje);
               listarClientes();
               $("#modalcliente").modal("hide");
            }else{
               alert(resultado.mensaje);
            }
         }
      });
   }

});

function listarClientes() {
   $.ajax({
      type: "GET",
      url: "/cliente-list",
      dataType: "json",
      success: function (resultado) {
         $("#tbcliente > tbody").html("");
         $.each(resultado, function (index, value) {
            $("#tbcliente > tbody").append(
                `<tr>
                    <td>${value.idcliente}</td>
                    <td>${value.nomcliente}</td>
                    <td>${value.apecliente}</td>
                    <td>${value.dnicliente}</td>
                    <td>${value.direccion}</td>
                    <td>${value.telefono}</td>
                    <td>
                        <button class="btn btn-info btnactualizar"
                                data-idcliente="${value.idcliente}"><i class="fas fa-edit"></i></button>
                    </td>
                </tr>`
            )
         });
      }
   });
}

function obtenerIdCliente() {
   $.ajax({
      type: "GET",
      url: "/idcliente",
      dataType: "json",
      success: function (resultado) {
         $("#txtidcliente").val(resultado);
      }
   })
}

function validarCliente() {
   let nomcli = document.getElementById("validnomcliente");
   let apecli = document.getElementById("validapecliente");
   let dnicli = document.getElementById("validdnicliente");
   let dircli = document.getElementById("validdirecioncliente");
   let telcli = document.getElementById("validtelfcliente");

   if(document.cliente.txtnomcliente.value === "") {
      nomcli.innerText = "Campo Requerido";
      return false;
   }else if(!document.cliente.txtnomcliente.value.match(/^\D*$/)) {
      nomcli.innerText = "El Nombre No Debe Tener digitos";
      return false;
   } else {
      nomcli.innerText = "";
   }

   if (document.cliente.txtapecliente.value === "") {
      apecli.innerText = "Campo Requerido";
      return false;
   }else if(!document.cliente.txtapecliente.value.match(/^\D*$/)) {
      apecli.innerText = "El Apellido No Debe Tener digitos";
      return false;
   } else {
      apecli.innerText = "";
   }

   if(document.cliente.txtdnicliente.value === "") {
      dnicli.innerText = "Campo Requerido";
      return false;
   }else if(!document.cliente.txtdnicliente.value.match(/^\d{8}$/)) {
      dnicli.innerText = "El Dni debe tener 8 digitos";
      return false;
   }else {
      dnicli.innerText = "";
   }

   if(document.cliente.txtdireccliente.value === "") {
      dircli.innerText = "Campo Requerido";
      return false;
   }else {
      dircli.innerText = "";
   }

   if(document.cliente.txttelcliente.value === "") {
      telcli.innerText = "Campo Requerido";
      return false;
   }else if(!document.cliente.txttelcliente.value.match(/^\d{9,11}$/)) {
      telcli.innerText = "Telefono Invalido";
      return false;
   }else {
      telcli.innerText = "";
   }

   return true;
}

function limpiarValidacionCliente() {
   let nomcli = document.getElementById("validnomcliente");
   let apecli = document.getElementById("validapecliente");
   let dnicli = document.getElementById("validdnicliente");
   let dircli = document.getElementById("validdirecioncliente");
   let telcli = document.getElementById("validtelfcliente");

   nomcli.innerText = "";
   apecli.innerText = "";
   dnicli.innerText = "";
   dircli.innerText = "";
   telcli.innerText = "";
}