$(document).on("click",".btnnuevo", function () {
    $("#modaldetalleventa").modal("show");
    limpiarValidacionDetalleVenta();
    $("#txtiddetalleventa").val("");
    $("#txtvalorproducto").val("");
    $("#txtcantidadprod").val("0");
    $("#txtprecioprod").val("0.0");
    $("#txttotalprod").val("0.0");
    $("#txtidventa").val("");
    $("#txtidproducto").val("");
    valorIdVenta();
    cargarIdDetalleVenta();
});

$(document).on("click",".btnguardar", function () {

    if(validarDetalleVentas()) {
        $.ajax({
            type: "POST",
            url: "/venta-detalle",
            contentType: "application/json",
            data: JSON.stringify({
                iddetalleventa: $("#txtiddetalleventa").val(),
                idventa: $("#txtidventa").val(),
                idproducto: $("#txtidproducto").val(),
                cantidad: $("#txtcantidadprod").val(),
                precio: $("#txtprecioprod").val(),
                subtotal: $("#txttotalprod").val()
            }),
            success: function (resultado) {
                if(resultado.resultado) {
                    alert(resultado.mensaje);
                    actualizarTotal();
                    listadoDetalleVenta();
                    $("#modaldetalleventa").modal("hide");
                    $("#message").hide();
                }else {
                    alert(resultado.mensaje);
                }
            }
        });
    }

});

$(document).on("click", ".btnactualizar", function () {
    limpiarValidacionDetalleVenta();
    $("#txtiddetalleventa").val("");
    $("#txtvalorproducto").val("");
    $("#txtcantidadprod").val("0");
    $("#txtprecioprod").val("0.0");
    $("#txttotalprod").val("0.0");
    $("#txtidproducto").val("");
    $("#txtidventa").val("");

    $.ajax({
        type: "GET",
        url: "/detalle-venta/" + $(this).attr("data-iddetalleventa"),
        dataType: "json",
        success: function (resultado) {
            $("#txtiddetalleventa").val(resultado.iddetalleventa);
            $("#txtidventa").val(resultado.idventa);
            $("#txtidproducto").val(resultado.idproducto);
            $("#txtvalorproducto").val(resultado.categoria + ' - ' + resultado.marca);
            $("#txtcantidadprod").val(resultado.cantidad);
            $("#txtprecioprod").val(resultado.precio);
            $("#txttotalprod").val(resultado.subtotal);
            $("#modaldetalleventa").modal("show");
        }
    });

});

function actualizarTotal() {
    var idventa = document.getElementById("txtvaloridventa").value;
    $.ajax({
        type: "POST",
        url: "/actualizar-venta/" + parseInt(idventa),
        dataType: "json",
        success: function () {
            console.log("Se Actualizo Exitosamente");
        },
        error: function (error) {
            console.log(error);
        }
    });
}

function cargarIdDetalleVenta() {
    $.ajax({
        type: "GET",
        url: "/iddetalleventa",
        dataType: "json",
        success: function (resultado) {
            $("#txtiddetalleventa").val(resultado);
        }
    });
}

function listadoDetalleVenta() {
    var idventa = document.getElementById("txtvaloridventa").value;
    console.log(idventa);
    $.ajax({
        type: "GET",
        url: "/detalle-venta-list/" + idventa,
        dataType: "json",
        success: function (resultado) {
            $("#tbdetalleventa > tbody").html("")
            $.each(resultado, function (index, value) {
                $("#tbdetalleventa > tbody").append(
                    `<tr>
                        <td>${value.iddetalleventa}</td>
                        <td>${value.venta.numeroventa}</td>
                        <td>${value.producto.marca}</td>
                        <td>${value.cantidad}</td>
                        <td>${value.precio}</td>
                        <td>${value.subtotal}</td>
                        <td class="text-center">
                            <button class="btn btn-info btnactualizar"
                                    data-iddetalleventa="${value.iddetalleventa}"><i class="fas fa-edit"></i></button>
                        </td>
                    </tr>`
                )
            });
        }
    });
}

function validarDetalleVentas() {
    let valorProducto = document.getElementById("txtvalorproducto");
    let valorProductoError = document.getElementById("validvalorproducto");
    let cantidad = document.getElementById("txtcantidadprod");
    let cantidadError = document.getElementById("validcantidadprod");

    if(valorProducto.value === "") {
        valorProductoError.innerText = "Selecciona El Producto";
        return false;
    }else {
        valorProductoError.innerText = "";
    }

    if(cantidad.value === 0) {
        cantidadError.innerText = "La Cantidad debe ser mayor a 0";
        return false;
    }else if(!cantidad.value.match(/^\d+$/)) {
        cantidadError.innerText = "Solo Digitos";
        return false;
    }else {
        cantidadError.innerText = "";
    }

    return true;
}

function limpiarValidacionDetalleVenta() {
    let valorProductoError = document.getElementById("validvalorproducto");
    let cantidadError = document.getElementById("validcantidadprod");
    valorProductoError.innerText = "";
    cantidadError.innerText = "";
}

function valorIdVenta() {
    var idventa = document.getElementById("txtvaloridventa");
    $("#txtidventa").val(idventa.value);
}

$(document).ready(function() {
    $('.btnverproducto').click(function(event) {
        event.preventDefault();
        $('#modalselectproduct').modal('show');
        listarProductos();
    });

    function listarProductos() {
        $.ajax({
            url: '/producto-list',
            method: 'GET',
            success: function(data) {
                var tbody = $('#tbproducto tbody');
                tbody.empty();
                data.forEach(function(producto) {
                    var row = `<tr>
                                <td>${producto.idproducto}</td>
                                <td>${producto.marca}</td>
                                <td>${producto.categoria.descripcion}</td>
                                <td>${producto.precio}</td>
                                <td>${producto.fecharegistro}</td>
                                <td>
                                    <img th:src="${producto.urlimagen}" alt="nada" width="80" height="80">
                                </td>
                            </tr>`;
                    tbody.append(row);
                });

                $('#tbproducto tbody tr').click(function() {
                    var idproducto = $(this).find('td').eq(0).text();
                    var marca = $(this).find('td').eq(1).text();
                    var categoria = $(this).find('td').eq(2).text();
                    var precio = $(this).find('td').eq(3).text();

                    $('#txtidproducto').val(idproducto);
                    $('#txtvalorproducto').val(categoria + ' - ' + marca);
                    $('#txtprecioprod').val(precio);
                    $("#txtcantidadprod").val("0");
                    $("#txttotalprod").val("0.0");
                    $('#modalselectproduct').modal('hide');
                });
            },
            error: function(error) {
                console.log("Error al obtener los productos: ", error);
            }
        });
    }
});

//calcular total
document.addEventListener('DOMContentLoaded', function() {
    const cantidadInput = document.getElementById('txtcantidadprod');
    const precioInput = document.getElementById('txtprecioprod');
    const totalInput = document.getElementById('txttotalprod');

    function calcularTotal() {
        const cantidad = parseFloat(cantidadInput.value) || 0;
        const precio = parseFloat(precioInput.value) || 0;
        const total = cantidad * precio;
        totalInput.value = total.toFixed(2);
    }

    cantidadInput.addEventListener('input', calcularTotal);
    precioInput.addEventListener('input', calcularTotal);
});