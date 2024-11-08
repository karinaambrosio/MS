package com.ka.ms.facturaController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/facturas")
public class FacturaController {
    private List<Factura> listaFacturas = new ArrayList<>();
    private int idCounter = 1;
    @GetMapping("/listar")
    public List<Factura> listarFacturas() {
        return listaFacturas;
    }
    @GetMapping("/{id}")
    public Factura obtenerFacturaPorId(@PathVariable int id) {
        return listaFacturas.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
    }
    @PostMapping("/agregar")
    public String agregarFactura(@RequestBody Factura factura) {
        RestTemplate restTemplate = new RestTemplate();
        Cliente cliente = restTemplate.getForObject("http://localhost:8080/clientes/" + factura.getClienteId(), Cliente.class);
        if (cliente != null) {
            factura.setId(idCounter++);
            listaFacturas.add(factura);
            return "Factura agregada: " + cliente.getNombre();
        } else {
            return "Cliente no encontrado.";
        }
    }
    @PutMapping("/actualizar/{id}")
    public String actualizarFactura(@PathVariable int id, @RequestBody Factura facturaActualizada) {
        Factura factura = listaFacturas.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
        if (factura != null) {
            factura.setDescripcion(facturaActualizada.getDescripcion());
            factura.setMonto(facturaActualizada.getMonto());
            factura.setClienteId(facturaActualizada.getClienteId());
            return "Factura actualizada.";
        }
        return "Factura no encontrada.";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarFactura(@PathVariable int id) {
        return listaFacturas.removeIf(f -> f.getId() == id) ? "Factura eliminada." : "Factura no existente.";
    }
    static class Cliente {
        private int id;
        private String nombre;
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getNombre() {
            return nombre;
        }
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
    }
}
