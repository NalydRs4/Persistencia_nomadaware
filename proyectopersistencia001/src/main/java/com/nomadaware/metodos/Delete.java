package com.nomadaware.metodos;

import com.nomadaware.entities.Producto;
import jakarta.persistence.EntityManager;
import java.util.Scanner;

public class Delete {
    private static final Scanner scanner = new Scanner(System.in);

    public static void eliminarProducto(EntityManager em) {
        System.out.print("Ingresa el ID del producto que deseas eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        em.getTransaction().begin();

        Producto producto = em.find(Producto.class, id);

        if (producto != null) {
            em.remove(producto); // Instruye a la base de datos para borrar la fila
            em.getTransaction().commit();
            System.out.println("¡Producto eliminado exitosamente!");
        } else {
            System.out.println("No se encontró ningún producto con el ID " + id);
            em.getTransaction().rollback();
        }
    }
}
