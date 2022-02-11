import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

    public String nombre;
    public String poblacion;
    public int edad;


    public void toString2(){
    System.out.println("Nombre: " +this.nombre + " Ciudad: " +this.poblacion + " Edad: " +this.edad);
    }
}
