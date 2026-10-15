package com.nomadaware.metodos;

import java.math.BigDecimal;

import com.nomadaware.entities.Categoria;
import com.nomadaware.entities.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import java.util.Scanner;

public class Create {
    private static final Scanner scanner = new Scanner(System.in);

    public static void crearProducto(EntityManager em) {
        System.out.print("Ingresa el nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingresa la cantidad: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingresa el precio: ");
        BigDecimal precio = new BigDecimal(scanner.nextLine());

        System.out.print("Ingresa la categoría del producto: ");
        String nombreCategoria = scanner.nextLine();

        em.getTransaction().begin();

        Categoria categoria;
        try {
            categoria = em.createQuery("SELECT c FROM Categoria c WHERE c.descripcion = :desc", Categoria.class)
                    .setParameter("desc", nombreCategoria)
                    .getSingleResult();
        } catch (NoResultException e) {
            categoria = new Categoria(nombreCategoria);
            em.persist(categoria);
        }

        Producto producto = new Producto(nombre, cantidad, precio, categoria);
        em.persist(producto);

        em.getTransaction().commit();
        System.out.println("¡Producto '" + producto.getNombre() + "' creado exitosamente con ID " + producto.getId() + "!");
    }
}
