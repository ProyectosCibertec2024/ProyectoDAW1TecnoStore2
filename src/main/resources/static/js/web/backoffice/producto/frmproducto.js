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
      alert("Se Inserto Correctamente");
      $("#modalproducto").modal("hide");
   }
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
    let marca = document.getElementById("validmarcaprod");
    let idcategoria = document.getElementById("valididcategoriaprod");
    let stock = document.getElementById("validstockprod");
    let precio = document.getElementById("validprecioprod");
    let color = document.getElementById("validcolorprod");
    let resolucion = document.getElementById("validprocesadorprod");
    let procesador = document.getElementById("validmarcaprod");
    let descuento = document.getElementById("validdescuentoprod");
    let ram = document.getElementById("validramprod");
    let espaciodisco = document.getElementById("validespacioprod");
    let pantallatamanio = document.getElementById("validpantallaprod");
    let idproveedor = document.getElementById("valididproveedorprod");

    marca.innerText = "";
    idcategoria.innerText = "";
    stock.innerText = "";
    precio.innerText = "";
    color.innerText = "";
    resolucion.innerText = "";
    procesador.innerText = "";
    descuento.innerText = "";
    ram.innerText = "";
    espaciodisco.innerText = "";
    pantallatamanio.innerText = "";
    idproveedor.innerText = "";

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

/*$("#txtidproducto").val("");
  $("#txtmarcaprod").val("");
  $("#cbocategoriaprod").empty();
  $("#txtstockprod").val("");
  $("#txtprecioprod").val("");
  $("#txtcolorprod").val("");
  $("#txtresolucionprod").val("");
  $("#txtprocesadorprod").val("");
  $("#txtramprod").val("");
  $("#txtespacioprod").val("");
  $("#txtpantallaprod").val("");
  $("#cboproveedorprod").empty();
  $("#txtdescuentoprod").val("");
  $("#txturlimagen").val("");
  //cargarCombos
  cargarComboCategoriaxProveedor(0,0);
  //cargar ID Producto
  obtenerIdProducto();*/

/*$(document).on("click", ".btnguardar", function () {

   $.ajax({
      type: "POST",
      url: "/producto",
      contentType: "application/json",
      data: JSON.stringify({
         idproducto: $("#txtidproducto").val(),
         marca: $("#txtmarcaprod").val(),
         idcategoria: $("#cbocategoriaprod").val(),
         stock: $("#txtstockprod").val(),
         precio: $("#txtprecioprod").val(),
         color: $("#txtcolorprod").val(),
         resolucion: $("#txtresolucionprod").val(),
         procesador: $("#txtprocesadorprod").val(),
         descuento: $("#txtdescuentoprod").val(),
         ram: $("#txtramprod").val(),
         espaciodisco: $("#txtespacioprod").val(),
         pantallatamanio: $("#txtpantallaprod").val(),
         idproveedor: $("#cboproveedorprod").val(),
         activo: $("#swactivoprod").prop("checked")
      }),
      success: function (resultado) {
         if(resultado.resultado) {
            alert(resultado.mensaje);
            $("#modalproducto").modal("hide");
            listadoProductos();
         }else{
            alert(resultado.mensaje);
         }
      }
   });

});*/

/*$(document).on("click", ".btnguardar", function () {
   var formData = new FormData();

   formData.append('idproducto', $("#txtidproducto").val());
   formData.append('marca', $("#txtmarcaprod").val());
   formData.append('idcategoria', $("#cbocategoriaprod").val());
   formData.append('stock', $("#txtstockprod").val());
   formData.append('precio', $("#txtprecioprod").val());
   formData.append('color', $("#txtcolorprod").val());
   formData.append('resolucion', $("#txtresolucionprod").val());
   formData.append('procesador', $("#txtprocesadorprod").val());
   formData.append('descuento', $("#txtdescuentoprod").val());
   formData.append('ram', $("#txtramprod").val());
   formData.append('espaciodisco', $("#txtespacioprod").val());
   formData.append('pantallatamanio', $("#txtpantallaprod").val());
   formData.append('idproveedor', $("#cboproveedorprod").val());
   formData.append('activo', $("#swactivoprod").prop("checked"));

   // Agregar la imagen al objeto FormData
   var fileInput = document.getElementById('txturlimagen'); // Ajusta el ID según el de tu input de archivo
   var file = fileInput.files[0];
   formData.append('imagen', file);

   $.ajax({
      type: "POST",
      url: "/producto",
      contentType: false,
      processData: false, // importante para evitar que jQuery procese el objeto FormData
      data: formData,
      success: function (resultado) {
         if(resultado.resultado) {
            alert(resultado.mensaje);
            $("#modalproducto").modal("hide");
            listadoProductos();
         } else {
            alert(resultado.mensaje);
         }
      }
   });
});*/

/*function listadoProductos() {
   $.ajax({
      type: "GET",
      url: "/producto-list",
      dataType: "json",
      success: function (resultado) {
         $("#tbproducto > tbody").html("");
         $.each(resultado, function (index, value) {
            $("#tbproducto > tbody").append(
                `<tr>
                    <td>${value.idproducto}</td>
                    <td>${value.marca}</td>
                    <td>${value.categoria.descripcion}</td>
                    <td>${value.precio}</td>
                    <td>${value.fecharegistro}</td>
                    <td>
                        <img src="${value.urlimagen}" alt="nada">
                    </td>
                    <td class="d-flex justify-content-center align-content-center">
                        <button class="btn btn-info btnactualizar"
                                data-idproducto="${value.idproducto}"><i class="fas fa-edit"></i></button>
                    </td>
                </tr>`
            )
         });
      }
   });
}

function cargarComboCategoriaxProveedor(idcategoria, idproveedor) {
   $.ajax({
      type: "GET",
      url: "/categoria-list",
      dataType: "json",
      success: function (resultado) {
         $.each(resultado, function (index, value) {
            $("#cbocategoriaprod").append(
                `<option value="${value.idcategoria}">${value.descripcion}</option>`
            );
         });
         if(idcategoria > 0) {
            $("#cbocategoriaprod").val(resultado.idcategoria);
         }
         $.ajax({
            type: "GET",
            url: "/proveedor-list",
            dataType: "json",
            success: function (resultado) {
               $.each(resultado, function (index, value) {
                  $("#cboproveedorprod").append(
                      `<option value="${value.idproveedor}">${value.nomproveedor}</option>`
                  );
               });
               if (idproveedor > 0){
                  $("#cboproveedorprod").val(resultado.idproveedor);
               }
            }
         });
      }
   });
}*/
