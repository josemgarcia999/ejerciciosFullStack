public class Persona {

    public String nombre;
    public String poblacion;
    public int edad;

    public Persona(String nombre, String poblacion, int edad) {
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void toString2(){
    System.out.println("Nombre: " +this.nombre + " Ciudad: " +this.poblacion + " Edad: " +this.edad);
    }
}
