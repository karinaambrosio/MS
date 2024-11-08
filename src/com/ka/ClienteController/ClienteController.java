package com.ka.ClienteController;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private List<Cliente> listaClientes = new ArrayList<>();
    private int idCounter = 1;
    @GetMapping("/listar")
    public List<Cliente> listarClientes() {
        return listaClientes;
    }
    @GetMapping("/{id}")
    public Cliente obtenerClientePorId(@PathVariable int id) {
        return listaClientes.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }
    @PostMapping("/agregar")
    public String agregarCliente(@RequestBody Cliente cliente) {
        cliente.setId(idCounter++);
        listaClientes.add(cliente);
        return "Cliente agregado";
    }
    @PutMapping("/actualizar/{id}")
    public String actualizarCliente(@PathVariable int id, @RequestBody Cliente clienteActualizado) {
        Cliente cliente = listaClientes.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (cliente != null) {
            cliente.setNombre(clienteActualizado.getNombre());
            cliente.setCorreo(clienteActualizado.getCorreo());
            return "Cliente actualizado ";
        } else {
            return "404 no encontrado";
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable int id) {
        listaClientes.removeIf(c -> c.getId() == id);
        return "Cliente eliminado ";
    }
}
