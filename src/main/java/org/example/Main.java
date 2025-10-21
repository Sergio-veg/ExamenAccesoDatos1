package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ServicioFiltradoPeliculas servicio = new ServicioFiltradoPeliculas("peliculas.csv");

        try {
            System.out.println("=== INICIANDO FILTRADO DE PELÍCULAS ===");

            System.out.println("1. Filtrando películas posteriores a 2010:");
            servicio.filtrarPorAnho(2010, true);

            System.out.println("2. Filtrando películas anteriores a 2000:");
            servicio.filtrarPorAnho(2000, false);

            System.out.println("3. Filtrando películas con 'El' en el título:");
            servicio.filtrarPorTitulo("el");

            System.out.println("=== FILTRADO COMPLETADO ===");

        } catch (IOException e) {
            System.err.println("Error durante el filtrado: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Error en formato de números: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
