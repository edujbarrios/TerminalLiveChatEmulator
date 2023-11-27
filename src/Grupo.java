import java.util.ArrayList;
import java.util.List;

public class Grupo {
    private String nombre;            // El nombre del grupo
    private List<Usuario> miembros;   // La lista de miembros del grupo

    // Constructor que crea un grupo con un nombre y una lista vacía de miembros
    public Grupo(String nombre) {
        this.nombre = nombre;
        this.miembros = new ArrayList<>();
    }

    // Método para añadir un miembro al grupo
    public void añadirMiembro(Usuario usuario) {
        miembros.add(usuario);
    }

    // Método para obtener la lista de miembros del grupo
    public List<Usuario> getMiembros() {
        return miembros;
    }

    // Método para obtener el nombre del grupo
    public String getNombre() {
        return nombre;
    }
}
