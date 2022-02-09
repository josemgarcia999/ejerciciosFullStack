import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArchivoDeDatos {

  public void lectura(String rutaArchivo) {

    String linea = "";
    Optional<String> nombre;
    Optional<String> poblacion;
    int edad = 0;

    List<Persona> personas = new ArrayList<>();
    try {
      BufferedReader bf = new BufferedReader(new FileReader(rutaArchivo));
      String temp = "";
      String bfRead;

      while ((bfRead = bf.readLine()) != null) {
        String[] split = bfRead.split(":");

        nombre = Optional.of(split[0]);

        poblacion = Optional.of(split[1]);

        try {
          if(split[2] != null)
            edad = Integer.parseInt(split[2]);

        } catch (IndexOutOfBoundsException ex) {
          edad=-1;
        }

        Persona p = new Persona(nombre.get(), poblacion.orElse("Desconocido"), edad);
        personas.add(p);
      }

    } catch (Exception e) {
      System.err.println("No se encontró un archivo");
    }
    // Filtrar personas con edad menor a 25
    personas.stream().filter(persona -> persona.getEdad() < 25).forEach((p) -> p.toString2());

  }
}
