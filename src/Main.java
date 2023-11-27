import java.util.*;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Mediador mediador = new ChatMediador();
        Scanner scanner = new Scanner(System.in);

        inicializarUsuarios(mediador);
        inicializarGrupos(mediador);

        // Eduardo es el remitente por defecto
        Usuario usuarioRemitente = mediador.obtenerUsuario("Eduardo");

        while (true) {
            limpiarPantalla();
            System.out.println("\033[1m\033[3m\033[32mHola " + usuarioRemitente.getNombre() + ", bienvenido a WhatsApp\033[0m");
            System.out.println("Elija una opción:");
            System.out.println("1. Enviar mensaje a un usuario");
            System.out.println("2. Enviar mensaje a un grupo");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            String opcion = scanner.nextLine();

            if (opcion.equals("3")) {
                System.out.println("Saliendo de Whatsapp.");
                break;
            }

            ejecutarOpcion(opcion, mediador, scanner);
        }
        scanner.close();
    }

    // Método para limpiar la pantalla
    private static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void inicializarUsuarios(Mediador mediador) {
        String[] nombres = {"Paula", "Samuel", "Patricia","Laura", "Pedro", "Maria", "Eduardo", "Rebeca"};
        for (String nombre : nombres) {
            mediador.anadirUsuario(new UsuarioConcreto(mediador, nombre));
        }
    }

    private static void inicializarGrupos(Mediador mediador) {
        Map<String, List<String>> grupos = new HashMap<>();
        grupos.put("Grupo1", Arrays.asList("Pedro", "Maria"));
        grupos.put("Grupo Laboratorio", Arrays.asList("Paula", "Laura"));
        grupos.put("Grupo3", Arrays.asList("Patricia", "Paula", "Maria", "Rebeca"));

        for (Map.Entry<String, List<String>> grupo : grupos.entrySet()) {
            mediador.crearGrupo(grupo.getKey());
            for (String usuario : grupo.getValue()) {
                mediador.anadirUsuarioAGrupo(usuario, grupo.getKey());
            }
        }
    }

    private static void ejecutarOpcion(String opcion, Mediador mediador, Scanner scanner) {
        // Eduardo es el remitente por defecto
        Usuario usuarioRemitente = mediador.obtenerUsuario("Eduardo");

        switch (opcion) {
            case "1":
                // Mostrar usuarios disponibles, excluyendo a Eduardo
                limpiarPantalla();
                mostrarUsuariosDisponibles(mediador, usuarioRemitente.getNombre());
                Usuario usuarioDestinatario = obtenerUsuario(mediador, scanner, "Ingrese el nombre del destinatario: ");
                if (usuarioDestinatario != null && !usuarioDestinatario.getNombre().equalsIgnoreCase("Eduardo")) {
                    limpiarPantalla();
                    System.out.println("\033[1m\033[3m\033[33mSe inició el chat entre " + usuarioRemitente.getNombre() + " y " + usuarioDestinatario.getNombre() + "\033[0m");
                    enviarMensajes(scanner, mensaje -> mediador.enviarMensajeIndividual(mensaje, usuarioDestinatario.getNombre(), usuarioRemitente));
                } else {
                    System.out.println("Destinatario no válido o no encontrado.");
                }
                break;
            case "2":
                // Mostrar grupos disponibles
                limpiarPantalla();
                mostrarGruposDisponibles(mediador);
                System.out.print("Ingrese el nombre del grupo: ");
                String grupo = scanner.nextLine();
                if (mediador.grupoExiste(grupo)) {
                    limpiarPantalla();
                    System.out.println("\033[1m\033[3m\033[33mEstás escribiendo en el grupo " + grupo + "\033[0m");
                    enviarMensajes(scanner, mensaje -> mediador.enviarMensajeGrupo(mensaje, grupo, usuarioRemitente));
                } else {
                    System.out.println("Grupo no encontrado.");
                }
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
                break;
        }
    }

    private static void mostrarUsuariosDisponibles(Mediador mediador, String remitente) {
        System.out.println("\u001B[1m\u001B[3mLista de contactos:\u001B[0m");
        mediador.obtenerTodosLosUsuarios().stream()
                .filter(usuario -> !usuario.getNombre().equalsIgnoreCase(remitente))
                .forEach(usuario -> System.out.println(" - " + usuario.getNombre()));
    }


    private static void mostrarGruposDisponibles(Mediador mediador) {
        System.out.println("\u001B[1m\u001B[3mLista de grupos en los que te han añadido:\u001B[0m");
        mediador.obtenerTodosLosGrupos().forEach(grupo -> System.out.println(" - " + grupo.getNombre()));
    }

    private static Usuario obtenerUsuario(Mediador mediador, Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        String nombre = scanner.nextLine();
        return mediador.obtenerUsuario(nombre);
    }

    private static void enviarMensajes(Scanner scanner, Consumer<String> envioMensaje) {
        while (true) {
            System.out.print("Enviar mensaje: ");
            String mensaje = scanner.nextLine();
            if (mensaje.equalsIgnoreCase("END")) {
                System.out.println("Saliendo de la conversación...");
                break;
            }
            envioMensaje.accept(mensaje);
        }
    }
}
