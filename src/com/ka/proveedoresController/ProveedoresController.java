package com.ka.proveedoresController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/proveedores")
public class ProveedoresController {
    private List<Proveedores> listaProveedores = new ArrayList<>();
    private int idCounter = 1;
    @GetMapping("/listar")
    public List<Proveedores> listarProveedores() {
        return listaProveedores;
    }
    @GetMapping("/{id}")
    public Proveedores obtenerProveedoresPorId(@PathVariable int id) {
        return listaProveedores.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
    @PostMapping("/agregar")
    public String agregarProveedores(@RequestBody Proveedores proveedores) {
        proveedores.setId(idCounter++);
        listaProveedores.add(proveedores);
        return "Proveedor agregado: " + proveedores.getNombre();
    }
    @PutMapping("/actualizar/{id}")
    public String actualizarProveedores(@PathVariable int id, @RequestBody Proveedores proveedoresActualizado) {
        Proveedores proveedores = listaProveedores.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (proveedores != null) {
            proveedores.setNombre(proveedoresActualizado.getNombre());
            proveedores.setProducto(proveedoresActualizado.getProducto());
            return "Proveedor actualizado.";
        }
        return "Proveedor no encontrado.";
    }
    @DeleteMapping("/eliminar/{id}")
    public String eliminarProveedores(@PathVariable int id) {
        return listaProveedores.removeIf(p -> p.getId() == id) ? "Proveedor eliminado." : "Proveedor no encontrado.";
    }
    @GetMapping("/clientes")
    public String listarClientes() {
        RestTemplate restTemplate = new RestTemplate();
        String resultado = restTemplate.getForObject("http://localhost:8080/clientes/listar", String.class);
        return "Clientes disponibles: " + resultado;
    }
    @GetMapping("/facturas")
    public String listarFacturas() {
        RestTemplate restTemplate = new RestTemplate();
        String resultado = restTemplate.getForObject("http://localhost:8080/facturas/listar", String.class);
        return "Facturas disponibles: " + resultado;
    }
}
