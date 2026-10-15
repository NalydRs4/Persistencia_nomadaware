package com.nomadaware.metodos;

import java.math.BigDecimal;
import com.nomadaware.entities.Producto;
import jakarta.persistence.EntityManager;
import java.util.Scanner;

public class Update {
    private static final Scanner scanner = new Scanner(System.in);

    public static void actualizarProducto(EntityManager em) {
        System.out.print("Ingresa el ID del producto que se desea actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        em.getTransaction().begin();
        
        Producto producto = em.find(Producto.class, id);

        if (producto != null) {
            System.out.println("Producto actual: " + producto.getNombre() + " - Precio: $" + producto.getPrecio());
            System.out.print("Ingresa el nuevo precio: ");
            BigDecimal nuevoPrecio = new BigDecimal(scanner.nextLine());

            producto.setPrecio(nuevoPrecio);
            
            em.getTransaction().commit();
            System.out.println("¡Precio actualizado exitosamente!");
        } else {
            System.out.println("No se encontró ningún producto con el ID " + id);
            em.getTransaction().rollback();
        }
    }
}
