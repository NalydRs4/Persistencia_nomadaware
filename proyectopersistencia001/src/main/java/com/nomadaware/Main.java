package com.nomadaware;

import com.nomadaware.metodos.Create;
import com.nomadaware.metodos.Delete;
import com.nomadaware.metodos.Read;
import com.nomadaware.metodos.Update;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadPersistencia");
        EntityManager em = emf.createEntityManager();

        boolean salir = false;

        while (!salir) {
            System.out.println("=============================================");
            System.out.println("=========== MENU CRUD NOMADAWARE ===========");
            System.out.println("1. Crear (Create) un nuevo producto");
            System.out.println("2. Leer (Read) todos los productos");
            System.out.println("3. Actualizar (Update) precio de un producto");
            System.out.println("4. Eliminar (Delete) un producto");
            System.out.println("5. Salir");
            System.out.println("=============================================");
            System.out.print("Elige una opción: ");

            int opcion = -1;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un número válido.");
                continue;
            }

            try {
                switch (opcion) {
                    case 1:
                        Create.crearProducto(em);
                        break;
                    case 2:
                        Read.leerProductos(em);
                        break;
                    case 3:
                        Update.actualizarProducto(em);
                        break;
                    case 4:
                        Delete.eliminarProducto(em);
                        break;
                    case 5:
                        salir = true;
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción no reconocida.");
                }
            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                System.out.println("Ocurrió un error en la base de datos: " + e.getMessage());
            }
        }
        em.close();
        emf.close();
        scanner.close();
    }
}
