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

function validarReportes() {

}