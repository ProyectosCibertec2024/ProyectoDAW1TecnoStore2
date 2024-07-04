$(document).on("click",".btnnuevo", function () {
    limpiarValidacionProducto();
    $("#modalproducto").modal("show");
    $("#txtidproducto").val("");
    $("#txtmarcaprod").val("");
    $("#cbocategoriaprod").val(0);
    $("#txtstockprod").val("");
    $("#txtprecioprod").val("");
    $("#txtcolorprod").val("");
    $("#txtresolucionprod").val("");
    $("#txtprocesadorprod").val("");
    $("#txtdescuentoprod").val("");
    $("#txtramprod").val("");
    $("#txtespacioprod").val("");
    $("#txtpantallaprod").val("");
    $("#cboproveedorprod").val(0);
    $("#swproducto").hide();
    $("#swactivoprod").prop("checked", true);
    obtenerIdProducto();
});

$(document).on("click", ".btnguardar", function () {
   if(validarProducto()) {
      $("#modalproducto").modal("hide");
       setTimeout(function () {
           window.location.reload();
       }, 1500);
   }
});

$(document).on("submit", "form[name='producto']", function(event) {
    event.preventDefault();

    $.ajax({
        type: $(this).attr("method"),
        url: $(this).attr("action"),
        data: new FormData(this),
        cache: false,
        contentType: false,
        processData: false,
        success: function(response) {
            mostrarMensajeExito("Guardado Exitosamente");

            $.get("/producto-list", function(data) {
                $("#tbproducto").html(data);
            });

            $("#modalproducto").modal("hide");
        },
        error: function(xhr, status, error) {
            mostrarMensajeError("Error al guardar el producto");
        }
    });
});

$(document).on("click",".btnactualizar",function () {
    limpiarValidacionProducto();
    $("#txtidproducto").val("");
    $("#swproducto").show();
    $.ajax({
       type: "GET",
       url: "/producto/" + $(this).attr("data-idproducto"),
       dataType: "json",
       success: function (resultado) {
           $("#txtidproducto").val(resultado.idproducto);
           $("#txtmarcaprod").val(resultado.marca);
           $("#cbocategoriaprod").val(resultado.idcategoria);
           $("#txtstockprod").val(resultado.stock);
           $("#txtprecioprod").val(resultado.precio);
           $("#txtcolorprod").val(resultado.color);
           $("#txtresolucionprod").val(resultado.resolucion);
           $("#txtprocesadorprod").val(resultado.procesador);
           $("#txtdescuentoprod").val(resultado.descuento);
           $("#txtramprod").val(resultado.ram);
           $("#txtespacioprod").val(resultado.espaciodisco);
           $("#txtpantallaprod").val(resultado.pantallatamanio);
           $("#cboproveedorprod").val(resultado.idproveedor);
           if(resultado.activo) {
               $("#swactivoprod").prop("checked", true);
           }else {
               $("#swactivoprod").prop("checked", false);
           }
           $("#modalproducto").modal("show");
       }
   });
});

function validarProducto() {
   let marca = document.getElementById("validmarcaprod");
   let idcategoria = document.getElementById("valididcategoriaprod");
   let stock = document.getElementById("validstockprod");
   let precio = document.getElementById("validprecioprod");
   let color = document.getElementById("validcolorprod");
   let resolucion = document.getElementById("validresolucionprod");
   let procesador = document.getElementById("validprocesadorprod");
   let descuento = document.getElementById("validdescuentoprod");
   let ram = document.getElementById("validramprod");
   let espaciodisco = document.getElementById("validespacioprod");
   let pantallatamanio = document.getElementById("validpantallaprod");
   let idproveedor = document.getElementById("valididproveedorprod");

    limpiarValidacionProducto()

   if(document.producto.marca.value === "") {
      marca.innerText = "Campo Requerido";
      return false;
   }else {
      marca.innerText = "";
   }

   if(document.producto.idcategoria.value === "0") {
      idcategoria.innerText = "Seleccione La Categoria";
      return false;
   }else {
      idcategoria.innerText = "";
   }

   if(document.producto.stock.value === "") {
      stock.innerText = "Campo Requerido";
      return false;
   }else if(document.producto.stock.value < 0) {
      stock.innerText = "El valor minimo es 0";
      return false;
   } else if(!document.producto.stock.value.match(/^\d*$/)) {
      stock.innerText = "El Stock debe de tener solo digitos";
      return false;
   }else {
      stock.innerText = "";
   }

   if(document.producto.precio.value === "") {
      precio.innerText = "Campo Requerido";
      return false;
   }else if (document.producto.stock.value < 0) {
      precio.innerText = "El valor minimo es 0";
      return false;
   }else if(!document.producto.precio.value.match(/^\d+(\.\d+)?$/)){
      precio.innerText = "Solo Se Permite Digitos & Decimales";
      return false;
   }else {
      precio.innerText = "";
   }

   if(document.producto.color.value === "") {
      color.innerText = "Campo Requerido";
      return false;
   } else if(!document.producto.color.value.match(/^\D*$/)) {
      color.innerText = "No se admiten digitos";
      return false;
   }else {
      color.innerText = "";
   }

   if(document.producto.resolucion.value === "") {
      resolucion.innerText = "Campo Requerido";
      return false;
   } else {
      resolucion.innerText = "";
   }

   if(document.producto.procesador.value === "") {
      procesador.innerText = "Campo Requerido";
      return false;
   } else {
      procesador.innerText = "";
   }

   if(document.producto.ram.value === "") {
      ram.innerText = "Campo Requerido";
      return false;
   } else if(!document.producto.ram.value.match(/^\d*$/)){
      ram.innerText = "Solo Se Permite Digitos";
      return false;
   } else {
      ram.innerText = "";
   }

   if(document.producto.espaciodisco.value === "") {
      espaciodisco.innerText = "Campo Requerido";
      return false;
   } else {
      espaciodisco.innerText = "";
   }

   if(document.producto.pantallatamanio.value === "") {
      pantallatamanio.innerText = "Campo Requerido";
      return false;
   } else if(!document.producto.pantallatamanio.value.match(/^\d+(\.\d+)?$/)){
      pantallatamanio.innerText = "Solo Se Permite Digitos & Decimales";
      return false;
   } else {
      pantallatamanio.innerText = "";
   }

   if(document.producto.idproveedor.value  === "0") {
      idproveedor.innerText = "Seleccione El Proveedor";
      return false;
   }else {
      idproveedor.innerText = "";
   }

   if(document.producto.descuento.value === "") {
      descuento.innerText = "Campo Requerido";
      return false;
   } else if(!document.producto.descuento.value.match(/^\d+(\.\d+)?$/)){
      descuento.innerText = "Solo Se Permite Digitos & Decimales";
      return false;
   }else if (document.producto.descuento.value > 100 || document.producto.descuento.value < 0) {
       descuento.innerText = "El Desuento debe ser entre 0 y 100";
       return false;
   }else {
      descuento.innerText = "";
   }

   return true;
}

function limpiarValidacionProducto() {
    let errores = document.querySelectorAll(".text-danger");
    errores.forEach(error => error.innerText = "");
}

function obtenerIdProducto() {
   $.ajax({
      type: "GET",
      url: "/idproducto",
      dataType: "json",
      success: function (resultado) {
         $("#txtidproducto").val(resultado);
      }
   });
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