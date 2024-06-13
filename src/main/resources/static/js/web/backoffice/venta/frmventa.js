$(document).on("click", ".btnnuevo", function () {
    $("#modalventas").modal("show");
    $("#txtidventa").val("");
    $("#txtnumeroventa").val("");
    $("#txtidusuario").val(usuarioId);
    $("#txtidcliente").val("");
    $("#txtvalorcliente").val("");
    cargarNumVenta();
    cargarIdVenta();
});

$(document).on("click",".btnguardar",function () {
    if (validarVentas()) {
        $.ajax({
            type: "POST",
            url: "/ventas",
            contentType: "application/json",
            data: JSON.stringify({
                idventa: $("#txtidventa").val(),
                idcliente: $("#txtidcliente").val(),
                idusuario: $("#txtidusuario").val()
            }),
            success: function (resultado) {
                if(resultado.resultado) {
                    alert(resultado.mensaje);
                    $("#modalventas").modal("hide");
                    listadoVentas();
                }else {
                    alert(resultado.mensaje);
                }
            }
        });
    }
});

$(document).on("click", "#btnactualizar", function () {
    $("#txtidventa").val("");
    $("#txtnumeroventa").val("");
    $("#txtidusuario").val("");
    $("#txtvalorcliente").val("");
    $("#txtidcliente").val("");
    $.ajax({
        type: "GET",
        url: "/ventas/" + $(this).attr("data-idventa"),
        dataType: "json",
        success: function (resultado) {
            $("#txtidventa").val(resultado.idventa);
            $("#txtnumeroventa").val(resultado.numeroventa);
            $("#txtidusuario").val(resultado.idusuario);
            $("#txtidcliente").val(resultado.idcliente);
            $("#txtvalorcliente").val(resultado.nomcliente + ' ' + resultado.apecliente);
            $("#modalventas").modal("show");
        }
    });

});

function listadoVentas() {
    $.ajax({
        type: "GET",
        url: "/venta-list",
        dataType: "json",
        success: function (resultado) {
            $("#tbventas > tbody").html("");
            $.each(resultado, function (index, value) {
                $("#tbventas > tbody").append(
                    `<tr>
                        <td>${value.idventa}</td>
                        <td>${value.numeroventa}</td>
                        <td>${value.usuario.nombre}</td>
                        <td>${value.cliente.nomcliente}</td>
                        <td>${value.fecharegistro}</td>
                        <td>${value.total}</td>
                        <td>
                        <button class="btn btn-info" id="btnactualizar"
                                data-idventa="${value.idventa}"><i class="fas fa-edit"></i></button>
                        <a href="/venta-detalle/${value.idventa}" class="btn btn-warning"><i class="far fa-eye me-2"></i>Detalle</a>
                    </td>
                    </tr>`
                )
            });
        }
    });
}

function cargarNumVenta() {
    $.ajax({
        type: "GET",
        url: "/numeroventa",
        dataType: "text",
        success: function (resultado) {
            $("#txtnumeroventa").val(resultado);
        }
    });
}

function cargarIdVenta() {
    $.ajax({
        type: "GET",
        url: "/idventa",
        dataType: "json",
        success: function (resultado) {
            $("#txtidventa").val(resultado);
        }
    });
}

function validarVentas() {
    var txvalorcliente = document.getElementById("txtvalorcliente");
    var validcliente = document.getElementById("validnomcliente");
    if(txvalorcliente.value === "") {
        validcliente.innerText = "Completa Este Campo";
        return false;
    }
    return true;
}

//AUTOCOMPLETAR CLIENTE
document.addEventListener("DOMContentLoaded", function() {
    const txtValorCliente = document.getElementById("txtvalorcliente");
    const suggestionsContainer = document.createElement("div");
    suggestionsContainer.id = "suggestions";
    txtValorCliente.parentNode.insertBefore(suggestionsContainer, txtValorCliente.nextSibling);

    txtValorCliente.addEventListener("input", function() {
        const query = this.value.trim();
        if (query.length >= 1) {
            fetch("/nomcliente-auto?nomcliente=" + query)
                .then(response => response.json())
                .then(data => {
                    mostrarResultados(data);
                })
                .catch(error => {
                    console.error('Error al cargar clientes:', error);
                });
        } else {
            ocultarSugerencias();
        }
    });

    txtValorCliente.addEventListener("keydown", function(event) {
        const suggestions = document.querySelectorAll("#suggestions div");
        if (event.key === "Tab" && suggestions.length > 0) {
            event.preventDefault();
            txtValorCliente.value = suggestions[0].innerText;
            txtValorCliente.focus();
            txtValorCliente.setSelectionRange(txtValorCliente.value.length, txtValorCliente.value.length);
            ocultarSugerencias();
        }
    });

    function mostrarResultados(data) {
        suggestionsContainer.innerHTML = "";
        data.forEach(cliente => {
            const suggestion = document.createElement("div");
            suggestion.textContent = cliente.nomcliente + " " + cliente.apecliente;
            suggestion.addEventListener("mousedown", function() {
                txtValorCliente.value = cliente.nomcliente + " " + cliente.apecliente;
                document.getElementById("txtidcliente").value = cliente.idcliente;
                txtValorCliente.focus();
                txtValorCliente.setSelectionRange(txtValorCliente.value.length, txtValorCliente.value.length);
                ocultarSugerencias();
            });
            suggestionsContainer.appendChild(suggestion);
        });
        suggestionsContainer.style.display = "block"; // Mostrar sugerencias
    }

    function ocultarSugerencias() {
        suggestionsContainer.style.display = "none"; // Ocultar sugerencias
    }
});