package farmacia.farmacia;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebController {

    private List<Item> items = new ArrayList<>();

    @GetMapping("/") // Página de inicio
    public String mostrarInicio() {
        return "inicio";
    }

    // Pagina de compra
    @GetMapping("/comprar")
    public String index(Model model) {
        model.addAttribute("items", items); //
        return "index";

    }

    @PostMapping("/agregar")
    public String agregar(@RequestParam String medicamento, @RequestParam String cantidad) {
        double cantidadDouble = convertirADouble(cantidad);

        // Mapa de precios
        Map<String, Double> precios = new HashMap<>();
        precios.put("Acetaminofen", 1200.0);
        precios.put("Pedialyte", 3500.0);
        precios.put("ultraBengue", 1800.0);
        precios.put("AdvilFem", 5000.0);
        precios.put("AdvilMax", 5000.0);
        precios.put("NexCare", 3000.0);
        precios.put("electrolit", 8000.0);
        precios.put("Dulcolax", 4000.0);
        precios.put("floratil", 6000.0);

        Double precioUnitario = precios.get(medicamento); // Buscar el precio del medicamento

        if (cantidadDouble != -1 && precioUnitario != null) {
            double precioTotal = cantidadDouble * precioUnitario;
            items.add(new Item(medicamento, cantidadDouble, precioTotal));
        }

        return "redirect:/comprar";
    }

    // Método auxiliar para convertir un String a double de manera segura
    private double convertirADouble(String valor) {
        try {
            return Double.parseDouble(valor); // Intentar convertir el String a double
        } catch (NumberFormatException e) {
            return -1; // Si ocurre un error, devolver -1 (indica que la conversión falló)
        }
    }

    // Mostrar la lista de medicamentos en una nueva página
    @GetMapping("/lista")
    public String lista(Model model) {
        model.addAttribute("items", items); // Pasar la lista de items a la vista lista
        return "lista"; // Página donde se muestra la lista de medicamentos
    }

    // Mostrar el formulario para editar un medicamento
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Item item = items.get(id); // Obtener el medicamento por su id
        model.addAttribute("item", item); // Pasar el meddicamento a la vista
        model.addAttribute("id", id); // Pasar el id para la acción
        return "editar"; // Página de edición
    }

    // Procesar la actualización de un medicamrnto
    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable int id, @RequestParam String medicamento, @RequestParam String cantidad,
            @RequestParam String precio) {
        Item item = items.get(id); // Obtener el medicamento por su id

        // Convertir cantidad y precio de String a double de manera segura
        double cantidadDouble = convertirADouble(cantidad);
        double precioDouble = convertirADouble(precio);

        if (cantidadDouble != -1 && precioDouble != -1) {
            item.setMedicamento(medicamento); // Actualizar el nombre del medicamento
            item.setCantidad(cantidadDouble); // Actualizar la cantidad
            item.setPrecio(precioDouble); // Actualizar el precio
        }

        return "redirect:/lista"; // Redirigir a la lista de medicamento después de actualizar
    }

    // Eliminar un item de la lista
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        items.remove(id); // Eliminar el medicamento de la lista
        return "redirect:/lista"; // Redirigir a la página de lista después de eliminar
    }

}
