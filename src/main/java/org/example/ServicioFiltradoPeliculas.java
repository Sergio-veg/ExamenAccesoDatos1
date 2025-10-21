package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServicioFiltradoPeliculas {

    private String archivoOriginal;

    public ServicioFiltradoPeliculas(String archivoOriginal) {
        this.archivoOriginal = archivoOriginal;
    }


    public void filtrarPorAnho(Integer anho, Boolean posterior) throws IOException {
        List<Pelicula> todasPeliculas = leerArchivoCSV();
        List<Pelicula> peliculasFiltradas = new ArrayList<>();

        for (Pelicula pelicula : todasPeliculas) {
            if (posterior) {
                if (pelicula.getAnho() >= anho) {
                    peliculasFiltradas.add(pelicula);
                }
            } else {
                if (pelicula.getAnho() <= anho) {
                    peliculasFiltradas.add(pelicula);
                }
            }
        }

        String nombreArchivoSalida;
        if (posterior) {
            nombreArchivoSalida = "posterior" + anho + ".csv";
        } else {
            nombreArchivoSalida = "anterior" + anho + ".csv";
        }

        escribirArchivoCSV(peliculasFiltradas, nombreArchivoSalida);
        System.out.println("Archivo generado: " + nombreArchivoSalida + " con " + peliculasFiltradas.size() + " películas");
    }


    public void filtrarPorTitulo(String cadena) throws IOException {
        List<Pelicula> todasPeliculas = leerArchivoCSV();
        List<Pelicula> peliculasFiltradas = new ArrayList<>();

        for (Pelicula pelicula : todasPeliculas) {
            if (pelicula.getTitulo().toLowerCase().contains(cadena.toLowerCase())) {
                peliculasFiltradas.add(pelicula);
            }
        }

        String nombreArchivoSalida = "filtradoPorTitulo.csv";
        escribirArchivoCSV(peliculasFiltradas, nombreArchivoSalida);
        System.out.println("Archivo generado: " + nombreArchivoSalida + " con " + peliculasFiltradas.size() + " películas");
    }


    private List<Pelicula> leerArchivoCSV() throws IOException {
        List<Pelicula> peliculas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                String[] campos = linea.split(",");
                if (campos.length >= 5) {
                    Pelicula pelicula = new Pelicula();
                    pelicula.setId(campos[0].trim());
                    pelicula.setTitulo(campos[1].trim());
                    pelicula.setAnho(Integer.parseInt(campos[2].trim()));
                    pelicula.setDirector(campos[3].trim());
                    pelicula.setGenero(campos[4].trim());

                    peliculas.add(pelicula);
                }
            }
        }

        return peliculas;
    }


    private void escribirArchivoCSV(List<Pelicula> peliculas, String nombreArchivo) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {

            bw.write("ID,Título,Año,Director,Género");
            bw.newLine();


            for (Pelicula pelicula : peliculas) {
                String linea = String.format("%s,%s,%d,%s,%s",
                        pelicula.getId(),
                        pelicula.getTitulo(),
                        pelicula.getAnho(),
                        pelicula.getDirector(),
                        pelicula.getGenero());

                bw.write(linea);
                bw.newLine();
            }
        }
    }
}
