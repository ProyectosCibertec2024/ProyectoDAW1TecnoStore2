package pe.tecnostore.tecnostore.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.tecnostore.tecnostore.firebase.storage.ImagenService;
import pe.tecnostore.tecnostore.model.bd.Producto;
import pe.tecnostore.tecnostore.service.interfaces.ICategoriaService;
import pe.tecnostore.tecnostore.service.interfaces.IProductoService;
import pe.tecnostore.tecnostore.service.interfaces.IProveedorService;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

@SessionAttributes({"ENLACES","USUARIO"})

@AllArgsConstructor
@Controller
public class ProductoController {

    private IProductoService productoService;
    private ImagenService imagenService;
    private ICategoriaService categoriaService;
    private IProveedorService proveedorService;

    @GetMapping(value = "/producto")
    public String producto(Model model) {
        model.addAttribute("c", categoriaService.listadoCategorias());
        model.addAttribute("prov", proveedorService.listaProveedores());
        model.addAttribute("p", new Producto());
        model.addAttribute("producto", productoService.listaProductos());
        return "backoffice/inventario/GestionInventario/producto/frmproducto";
    }

    @GetMapping(value = "/producto-list")
    @ResponseBody
    public List<Producto> listaProductos() {
        return productoService.listaProductos();
    }

    @PostMapping(value = "/producto")
    public String guardarProducto(@ModelAttribute("p") Producto producto, @RequestParam("imagen") MultipartFile imagen, Model model) {
        boolean rs = true;
        String men = "Guardado Exitosamente";
        try {
            int cod = productoService.obtenerIdProducto();
            if(producto.getIdproducto() < cod) {
                if(!imagen.isEmpty()) {
                    String nombreArchivo = imagen.getOriginalFilename();
                    File archivo = imagenService.convertToFile(imagen);
                    String contenido = imagen.getContentType();
                    String imagenUrl = imagenService.uploadFile(archivo, nombreArchivo, contenido, "productos");
                    producto.setNombreimagen(nombreArchivo);
                    producto.setUrlimagen(imagenUrl);
                    LocalDate fecha = LocalDate.now();
                    producto.setFecharegistro(fecha);
                    productoService.actualizarProducto(producto.getMarca(),
                            producto.getIdcategoria(),producto.getStock(),producto.getUrlimagen(),
                            producto.getNombreimagen(),producto.getPrecio(),producto.getColor(),
                            producto.getResolucion(), producto.getProcesador(),producto.getDescuento(),
                            producto.getRam(), producto.getEspaciodisco(),producto.getEspaciodisco(),
                            producto.getIdproveedor(), producto.getActivo(),producto.getIdproducto());
                    archivo.delete();
                    model.addAttribute("p", new Producto());
                }else{
                    productoService.actualizarProductoSinImagen(producto.getMarca(),
                            producto.getIdcategoria(),producto.getStock(),producto.getPrecio(),
                            producto.getColor(), producto.getResolucion(), producto.getProcesador(),
                            producto.getDescuento(), producto.getRam(), producto.getEspaciodisco(),producto.getEspaciodisco(),
                            producto.getIdproveedor(), producto.getActivo(),producto.getIdproducto());
                    model.addAttribute("p", new Producto());
                }
            }else {
                if(!imagen.isEmpty()) {
                    String nombreArchivo = imagen.getOriginalFilename();
                    File archivo = imagenService.convertToFile(imagen);
                    String contenido = imagen.getContentType();
                    String imagenUrl = imagenService.uploadFile(archivo, nombreArchivo, contenido, "productos");
                    producto.setNombreimagen(nombreArchivo);
                    producto.setUrlimagen(imagenUrl);
                    LocalDate fecha = LocalDate.now();
                    producto.setFecharegistro(fecha);
                    productoService.guardarProducto(producto);
                    archivo.delete();
                    model.addAttribute("p", new Producto());
                }
            }
        }catch (Exception e) {
            rs = false;
            men = "Ups, Hubo un error : " + e.getCause().getMessage();
            System.out.println(e.getCause().getLocalizedMessage());
        }
        return "redirect:/producto";
    }

    @GetMapping(value = "/producto/{idproducto}")
    @ResponseBody
    public Producto buscarProducto(@PathVariable Integer idproducto) {
        return productoService.buscarProducto(idproducto);
    }

    @GetMapping(value = "/idproducto")
    @ResponseBody
    public Integer obtenerIdProducto() {
        return productoService.obtenerIdProducto();
    }
}
