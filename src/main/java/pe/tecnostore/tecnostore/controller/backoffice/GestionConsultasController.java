package pe.tecnostore.tecnostore.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.EmpresaConsultaDTO;
import pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.ProductoConsultaDTO;
import pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.ProveedorConsultaDTO;
import pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.UsuarioConsultaDTO;
import pe.tecnostore.tecnostore.service.Impl.UsuarioService;
import pe.tecnostore.tecnostore.service.interfaces.IRolService;
import pe.tecnostore.tecnostore.service.interfaces.ITipoEmpresaService;
import pe.tecnostore.tecnostore.service.interfaces.gestion.consultas.IGestionConsultaEmpresaService;
import pe.tecnostore.tecnostore.service.interfaces.gestion.consultas.IGestionConsultaProductoService;
import pe.tecnostore.tecnostore.service.interfaces.gestion.consultas.IGestionConsultaProveedorService;
import pe.tecnostore.tecnostore.service.interfaces.gestion.consultas.IGestionConsultaUsuarioService;

import java.util.List;

@SessionAttributes({"ENLACES","USUARIO"})

@AllArgsConstructor
@Controller
public class GestionConsultasController {

    private final UsuarioService usuarioService;
    private IGestionConsultaUsuarioService gestionConsultaUsuarioService;
    private IRolService rolService;
    private ITipoEmpresaService tipoEmpresaService;
    private IGestionConsultaEmpresaService gestionConsultaEmpresaService;
    private IGestionConsultaProveedorService gestionConsultaProveedorService;
    private IGestionConsultaProductoService gestionConsultaProductoService;

    /**GESTION CONSULTAS**/
    @GetMapping(value = "/gestion-consultas")
    public String gestionConsultas() {
        return "backoffice/inventario/GestionConsultas/frmgestionconsulta";
    }

    /*GESTION CONSULTAS USUARIOS*/
    @GetMapping(value = "/gestionconsulta-usuarios")
    public String gestionConsultasUsuarios(Model model) {
        model.addAttribute("rolesgestionuser", rolService.listadoRols());
        model.addAttribute("consultaUsuario", gestionConsultaUsuarioService.consultaUsuario());
        return "backoffice/inventario/GestionConsultas/usuarios/frmconsultausuario";
    }

    /*@GetMapping(value = "/gestionconsultausuarios-list")
    @ResponseBody
    public List<UsuarioConsultaDTO> gestionlistadousuario() {
        return gestionConsultaUsuarioService.consultaUsuario();
    }*/

    @PostMapping(value = "/gestionconsulta-usuarios")
    public String gestionConsultaUsuarios(@RequestParam("idrol") int idrol, Model model) {
        try {
            String mensaje;
            if(idrol == 0) {
                mensaje = "Seleccione Un Rol";
                model.addAttribute("error", mensaje);
            }else {
                List<UsuarioConsultaDTO> lista = gestionConsultaUsuarioService.consultaUsuarioxRol(idrol);
                if(lista.isEmpty()) {
                    model.addAttribute("error", "No se encontraron Consultas para el rol seleccionado");
                } else {
                    model.addAttribute("gestuserconsulta", lista);
                }
            }
            model.addAttribute("rolesgestionuser", rolService.listadoRols());
        }catch (Exception e) {
            System.out.println("Error en : " + e.getMessage());
        }
        return "backoffice/inventario/GestionConsultas/usuarios/frmconsultausuario";
    }
    /*GESTION CONSULTAS EMPRESA*/
    @GetMapping(value = "/gestionconsulta-empresas")
    public String gestionConsultaEmpresa(Model model) {
        model.addAttribute("tipoempresagestion", tipoEmpresaService.listadoTipoEmpresas());
        model.addAttribute("gestionempresasinparam", gestionConsultaEmpresaService.consultarEmpresaSinParam());
        return "backoffice/inventario/GestionConsultas/empresa/frmgestionconsultaempresa";
    }

    @GetMapping(value = "/gestionempresa-list")
    @ResponseBody
    public List<EmpresaConsultaDTO> gestionConsultaList() {
        return gestionConsultaEmpresaService.consultarEmpresaSinParam();
    }

    @PostMapping(value = "/gestionconsulta-empresas")
    public String gestionConsultaEmpresa(@RequestParam("idtipoempresa") int idtipoempresa,
                                         Model model) {
        try{
            String mensaje;
            if(idtipoempresa == 0) {
                mensaje = "Seleccione El Tipo de Empresa";
                model.addAttribute("error", mensaje);
                model.addAttribute("gestionempresasinparam", gestionConsultaEmpresaService.consultarEmpresaSinParam());
            }else {
                List<EmpresaConsultaDTO> lista = gestionConsultaEmpresaService.consultaEmpresa(idtipoempresa);
                if(lista.isEmpty()) {
                    mensaje = "No se encontraron empresas para el tipo empresa seleccionado";
                    model.addAttribute("error", mensaje);
                    model.addAttribute("gestionempresasinparam", gestionConsultaEmpresaService.consultarEmpresaSinParam());
                }else {
                    model.addAttribute("empresaconsultagestion", lista);
                }
            }
            model.addAttribute("tipoempresagestion", tipoEmpresaService.listadoTipoEmpresas());
        }catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return "backoffice/inventario/GestionConsultas/empresa/frmgestionconsultaempresa";
    }

    /*GESTION CONSULTAS PROVEEDOR*/
    @GetMapping(value = "/gestionconsulta-proveedores")
    public String gestionConsultaProveedores(Model model) {
        model.addAttribute("proveedorconsulta", gestionConsultaProveedorService.consultaProveedor());
        return "backoffice/inventario/GestionConsultas/proveedor/frmgestionconsultaproveedor";
    }

    @PostMapping(value = "/gestionconsulta-proveedores")
    public String gestionConsultaProveedores(@RequestParam("nomproveedor") String nomproveedor,
                                             Model model) {
        try {
            String mensaje;
            if(nomproveedor.isEmpty()) {
                mensaje = "El Nombre Proveedor Esta Vacio";
                model.addAttribute("error", mensaje);
            }else {
                List<ProveedorConsultaDTO> lista = gestionConsultaProveedorService.consultaProveedorxNomprov(nomproveedor);
                if(lista.isEmpty()) {
                    mensaje = "No Se Encontraron Proveedores";
                    model.addAttribute("error", mensaje);
                }else {
                    model.addAttribute("proveedorconsultagestion", lista);
                }
            }
        }catch (Exception e) {
            System.out.println("Error en : " + e.getMessage());
        }
        return "backoffice/inventario/GestionConsultas/proveedor/frmgestionconsultaproveedor";
    }

    /*GESTION CONSULTAS PRODUCTO*/
    @GetMapping(value = "/gestionconsulta-productos")
    public String gestionConsultaProductos(Model model) {
        model.addAttribute("gestionproductoconsulta", gestionConsultaProductoService.consultaProducto());
        return "backoffice/inventario/GestionConsultas/producto/frmgestionconsultaproductos";
    }

    @PostMapping(value = "/gestionconsulta-productos")
    public String gestionConsultaProductos(@RequestParam("nomproducto") String nomproducto,
                                           Model model) {
        try {
            String mensaje;
            if(nomproducto.isEmpty()) {
                mensaje = "El Nombre del Producto Esta Vacio";
                model.addAttribute("error", mensaje);
            }else {
                List<ProductoConsultaDTO> lista = gestionConsultaProductoService.consultaProductoXMarca(nomproducto);
                if(lista.isEmpty()) {
                    mensaje = "No Se Encontraron Productos";
                    model.addAttribute("error", mensaje);
                }else {
                    model.addAttribute("productoconsultagestion", lista);
                }
            }
        }catch (Exception e) {
            System.out.println("Error en : " + e.getMessage());
        }
        return "backoffice/inventario/GestionConsultas/producto/frmgestionconsultaproductos";
    }

}
