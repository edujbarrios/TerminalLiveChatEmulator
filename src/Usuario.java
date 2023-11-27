
public abstract class Usuario {
    protected Mediador mediador;  // El mediador a través del cual se comunicará el usuario
    protected String nombre;      // El nombre del usuario

    // Constructor que asigna el mediador y el nombre al usuario
    public Usuario(Mediador mediador, String nombre) {
        this.mediador = mediador;
        this.nombre = nombre;
    }

    // Método abstracto para enviar un mensaje a un grupo
    public abstract void enviarMensajeGrupo(String mensaje, String nombreGrupo);

    // Método abstracto para enviar un mensaje a un usuario específico
    public abstract void enviarMensajeIndividual(String mensaje, String nombreDestinatario);

    // Método abstracto para recibir un mensaje
    public abstract void recibirMensaje(String mensaje);

    // Método para obtener el nombre del usuario
    public String getNombre() {
        return nombre;
    }
}