package pe.tecnostore.tecnostore.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.tecnostore.tecnostore.model.bd.Categoria;
import pe.tecnostore.tecnostore.model.dto.response.RespuestaResponse;
import pe.tecnostore.tecnostore.service.interfaces.ICategoriaService;

import java.util.List;

@SessionAttributes({"ENLACES","USUARIO"})

@AllArgsConstructor
@Controller
public class CategoriaController {
    private ICategoriaService categoriaService;

    @GetMapping(value = "/categoria")
    public String categorias(Model model) {
        model.addAttribute("categoria", categoriaService.listadoCategorias());
        return "backoffice/inventario/GestionInventario/categoria/frmcategoria";
    }

    @GetMapping(value = "/categoria-list")
    @ResponseBody
    public List<Categoria> listaCategoria() {
        return categoriaService.listadoCategorias();
    }

    @PostMapping(value = "/categoria")
    @ResponseBody
    public RespuestaResponse guardarCategoria(@RequestBody Categoria categoria) {
        boolean rs = true;
        String men = "Guardado Exitosamente";
        try {
            categoriaService.guardarCategoria(categoria);
        }catch (Exception e) {
            men = "Ups, Hubo un error : " + e.getCause().getMessage();
            rs = false;
        }
        return RespuestaResponse.builder().resultado(rs).mensaje(men).build();
    }

    @GetMapping(value = "/idcategoria")
    @ResponseBody
    public Integer obtenerCodigo() {
        return categoriaService.obtenerID();
    }

    @GetMapping(value = "/categoria/{idcategoria}")
    @ResponseBody
    public Categoria buscarEmpresa(@PathVariable Integer idcategoria) {
        return categoriaService.buscarCategoria(idcategoria);
    }

    @GetMapping(value = "/categoria-eliminar/{idcategoria}")
    @ResponseBody
    public RespuestaResponse eliminarCategoria(@PathVariable Integer idcategoria) {
        boolean rs = true;
        String men = "Eliminado Exitosmente";
        try {
            categoriaService.eliminarCategoria(idcategoria);
        }catch (Exception e) {
            men = "Ups, Hubo un error no se pudo eliminar: " + e.getCause().getMessage();
            rs = false;
        }
        return RespuestaResponse.builder().mensaje(men).resultado(rs).build();
    }
}
