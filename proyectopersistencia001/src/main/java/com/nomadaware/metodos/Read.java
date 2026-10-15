package com.nomadaware.metodos;

import com.nomadaware.entities.Producto;
import jakarta.persistence.EntityManager;
import java.util.List;

public class Read {

    public static void leerProductos(EntityManager em) {
        System.out.println("\n--- LISTA DE PRODUCTOS ---");
        List<Producto> productos = em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados en la base de datos.");
        } else {
            for (Producto p : productos) {
                String catDesc = (p.getCategoria() != null) ? p.getCategoria().getDescripcion() : "Sin Categoría";
                System.out.printf("ID: %d | Nombre: %s | Cantidad: %d | Precio: $%.2f | Categoría: %s%n",
                        p.getId(), p.getNombre(), p.getCantidad(), p.getPrecio(), catDesc);
            }
        }
    }
}
