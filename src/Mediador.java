import java.util.List;

public interface Mediador {
    void enviarMensajeGrupo(String mensaje, String nombreGrupo, Usuario remitente);
    void enviarMensajeIndividual(String mensaje, String nombreDestinatario, Usuario remitente);
    void anadirUsuario(Usuario usuario);
    void crearGrupo(String nombreGrupo);
    void anadirUsuarioAGrupo(String nombreUsuario, String nombreGrupo);
    Usuario obtenerUsuario(String nombre);
    Grupo obtenerGrupo(String nombre);
    boolean grupoExiste(String nombreGrupo); // Método agregado para verificar si un grupo existe
    List<Usuario> obtenerTodosLosUsuarios();
    List<Grupo> obtenerTodosLosGrupos();
}
