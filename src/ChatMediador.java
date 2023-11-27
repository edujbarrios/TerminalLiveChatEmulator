import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatMediador implements Mediador {
    private Map<String, Usuario> usuarios;
    private Map<String, Grupo> grupos;

    public ChatMediador() {
        this.usuarios = new HashMap<>();
        this.grupos = new HashMap<>();
    }

    @Override
public void enviarMensajeGrupo(String mensaje, String nombreGrupo, Usuario remitente) {
        if (grupos.containsKey(nombreGrupo)) {
            for (Usuario miembro : grupos.get(nombreGrupo).getMiembros()) {
                if (!miembro.getNombre().equals(remitente.getNombre())) {
                    // Incluir el nombre del remitente en el mensaje con color azul celeste
                    String mensajeColorido = "\u001B[36mMensaje de " + remitente.getNombre() + ": " + mensaje + "\u001B[0m";
                    miembro.recibirMensaje(mensajeColorido);
                }
            }
        }
    z}

    @Override
    public void enviarMensajeIndividual(String mensaje, String nombreDestinatario, Usuario remitente) {
        if (usuarios.containsKey(nombreDestinatario)) {
            usuarios.get(nombreDestinatario).recibirMensaje(" Mensaje privado de " + remitente.getNombre() + ": " + mensaje);
        }
    }

    @Override
    public void anadirUsuario(Usuario usuario) {
        usuarios.put(usuario.getNombre(), usuario);
    }

    @Override
    public void crearGrupo(String nombreGrupo) {
        grupos.put(nombreGrupo, new Grupo(nombreGrupo));
    }

    @Override
    public void anadirUsuarioAGrupo(String nombreUsuario, String nombreGrupo) {
        if (usuarios.containsKey(nombreUsuario) && grupos.containsKey(nombreGrupo)) {
            grupos.get(nombreGrupo).añadirMiembro(usuarios.get(nombreUsuario));
        }
    }

    @Override
    public Usuario obtenerUsuario(String nombre) {
        return usuarios.get(nombre);
    }

    @Override
    public Grupo obtenerGrupo(String nombre) {
        return grupos.get(nombre);
    }

    // Método agregado para verificar si un grupo existe
    @Override
    public boolean grupoExiste(String nombreGrupo) {
        return grupos.containsKey(nombreGrupo);
    }

    @Override
    public List<Grupo> obtenerTodosLosGrupos() {
        return new ArrayList<>(grupos.values());
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return new ArrayList<>(usuarios.values());
    }
}



