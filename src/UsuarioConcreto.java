

import java.util.Random;
import java.util.List;
import java.util.Arrays;

public class UsuarioConcreto extends Usuario {
    private String colorANSI; // Atributo para el color
    private static final List<String> RESPUESTAS = Arrays.asList(
            "¡Interesante!",
            "No estoy seguro de eso.",
            "¡Totalmente de acuerdo!",
            "¿Podrías explicar más?",
            "Eso suena genial.",
            "Necesito pensar en eso.",
            "Eso no me convence.",
            "¡Qué curioso!",
            "Nunca lo había visto así.",
            "Eso es completamente nuevo para mí.",
            "¿En serio?",
            "Eso me sorprende.",
            "No lo había pensado.",
            "Eso cambia las cosas.",
            "Tengo que investigar más sobre eso.",
            "¿Puedes darme más detalles?",
            "Eso es bastante claro.",
            "Me deja pensando.",
            "Interesante perspectiva.",
            "Eso es un desafío.",
            "Vale la pena considerarlo.",
            "No estoy completamente convencido.",
            "Eso abre nuevas posibilidades.",
            "Nunca lo hubiera imaginado.",
            "Eso es algo a considerar.",
            "¿Cómo llegaste a esa conclusión?",
            "Me gustaría saber más.",
            "Eso es bastante intrigante.",
            "Necesito más información para opinar.",
            "Eso es un enfoque único.",
            "Hay que verlo desde diferentes ángulos.",
            "Eso merece más discusión.",
            "¿Podrías profundizar en eso?",
            "Eso es un punto válido.",
            "Hay que analizarlo detenidamente.",
            "Es un tema complicado.",
            "Eso me deja perplejo.",
            "Es un tema para reflexionar.",
            "Requiere un análisis más detallado.",
            "Eso es digno de debate."
    );

    public UsuarioConcreto(Mediador mediador, String nombre) {
        super(mediador, nombre);
        this.colorANSI = generarColorANSIAleatorio(); // Asignar un color aleatorio
    }



    @Override
    public void enviarMensajeGrupo(String mensaje, String nombreGrupo) {
        System.out.println(this.nombre + " envía a Grupo " + nombreGrupo + "]: " + mensaje);
        mediador.enviarMensajeGrupo(mensaje, nombreGrupo, this);
    }

    @Override
    public void enviarMensajeIndividual(String mensaje, String nombreDestinatario) {
        System.out.println(this.nombre + " envía a " + nombreDestinatario + ": " + mensaje);
        mediador.enviarMensajeIndividual(mensaje, nombreDestinatario, this);
    }


  @Override
    public void recibirMensaje(String mensaje) {
        System.out.println("\u001B[36m" + this.nombre + " recibe: " + mensaje + "\u001B[0m");
        responderAutomaticamente();
    }

    private void responderAutomaticamente() {
        String respuesta = RESPUESTAS.get(new Random().nextInt(RESPUESTAS.size()));
        System.out.println(this.colorANSI + this.nombre + " responde: " + respuesta + "\u001B[0m");
    }



    // Genera un color ANSI aleatorio
    private static String generarColorANSIAleatorio() {
        String[] coloresANSI = {


                "\u001B[34m", // Azul
                "\u001B[35m", // Púrpura
                "\u001B[95m",  // Magenta Claro
                "\u001B[94m",  // Azul Claro
                "\u001B[97m",  // Blanco
                "\u001B[90m",  // Gris
                "\u001B[93m",  // Amarillo Claro
                "\u001B[96m",  // Cian Claro
                "\u001B[92m",  // Verde Claro
                "\u001B[38;5;208m", // Naranja / Marrón
                "\u001B[38;5;200m", // Rosa
                "\u001B[38;5;183m"  // Violeta / Lavanda

        };
        return coloresANSI[new Random().nextInt(coloresANSI.length)];
    }

}

