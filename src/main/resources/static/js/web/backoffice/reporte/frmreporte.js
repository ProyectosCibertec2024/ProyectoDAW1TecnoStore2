$(document).on("click",".btnactualizar",function () {

   $.ajax({
      type: "GET",
      url: "/factura/" + $(this).attr("data-numerofactura"),
      dataType: "json",
      success: function (data) {
         $("#txtidfactura").val(data.idfactura);
         $("#txtidventa").val(data.idventa);
         $("#txtnumerofactura").val(data.numerofactura);
         if(data.estado) {
            $("#txtchactivo").prop("checked", true);
            $("#estadoHidden").val("true");
         }else {
            $("#txtchactivo").prop("checked", false);
            $("#estadoHidden").val("false");
         }
         $("#modalactualizarfactura").modal("show");
      }
   });

});

$(document).ready(function() {
   $("#txtchactivo").on("change", updateEstadoHidden);
});

function updateEstadoHidden() {
   if ($("#txtchactivo").is(":checked")) {
      $("#estadoHidden").val("true");
   } else {
      $("#estadoHidden").val("false");
   }
}

function validarFechas() {
   const fecha1 = document.getElementById("txtfecha1").value;
   const fecha2 = document.getElementById("txtfecha2").value;
   const errorMensaje = document.getElementById("errorMensaje");

   if (!fecha1 || !fecha2) {
      errorMensaje.textContent = "Por favor, complete ambos campos de fecha.";
      errorMensaje.style.display = "block";
      return false;
   }

   if (new Date(fecha1) > new Date(fecha2)) {
      errorMensaje.textContent = "La fecha Desde no puede ser posterior a la fecha Hasta.";
      errorMensaje.style.display = "block";
      return false;
   }

   errorMensaje.style.display = "none";
   return true;
}