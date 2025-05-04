package farmacia.farmacia;

public class Item {
    private String medicamento; // Nombre del medicamento
    private double cantidad; // Cantidad de medicamentos
    private double precio; // Precio unitario del medic

    // Constructor que inicializa los valores
    public Item(String medicamento, double cantidad, double precio) {
        this.medicamento = medicamento;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getMedicamento() {
        return medicamento;
    }

    // Setter para el nombre del medicamento
    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    // Getter para la cantidad de medicamento
    public double getCantidad() {
        return cantidad;
    }

    // Setter para la cantidad de medicamento
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    // Getter para el precio unitario del medicamento
    public double getPrecio() {
        return precio;
    }

    // Setter para el precio unitario del mediamento
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Método para calcular el precio total (cantidad * precio unitario)
    public double getPrecioTotal() {
        return cantidad * precio; // Calcula el precio total según cantidad y precio
    }

    // Método que devuelve una representación legible del objeto Item
    @Override
    public String toString() {
        return "Item{" +
                "medicamento='" + medicamento + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", precioTotal=" + getPrecioTotal() +
                '}';
    }
}
