package views;

import models.Administrador;
import models.Conductor;
import models.Destinatario;
import models.Envio;
import utils.Utils;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Objetos
        Destinatario destinatario2 = null;
        Conductor conductor2 = null;
        Conductor conductor3 = null;
        Envio envioSeleccionado;
        // Mock
        Administrador admin = new Administrador("Carlos", "Barroso", "carlosbarroso@test.com",
                "admin");
        Destinatario destinatario1 = new Destinatario("Pepe", "López", "pepelopez@test.com",
                "1234", "123456789", "Calle Nogal 42", "Linares", "Jaén");

        Conductor conductor1 = new Conductor("Marta", "Martínez", "martamartinez@test.com",
                "5678", "987654321", "Jaén");


        // Variables
        var s = new Scanner(System.in);

        String emailTeclado = "", claveTeclado, nombreTeclado, apellidoTeclado, movilTeclado, direccionTeclado, localidadTeclado,
                provinciaTeclado, provinciaRefTeclado, claveConfirmacion;

        int op, intentosLogin, tipoUsuario = 0;

        boolean finPrograma = false, login, comienzaRegistro, emailValido, finRegistro, envioHaSidoAsignado, envioHaSidoElegido,
                envioHaSidoActualizado;

        // Programa

        do { // Bucle principal del programa
            System.out.println("""
                    **********************************************************************************************
                            ______                                          ____                        \s
                           / ____/  ___    _____   ____   ____ _   ____    / __ \\  ____ _  ____ _  ____ _
                          / /_     / _ \\  / ___/  / __ \\ / __ `/  / __ \\  / /_/ / / __ `/ / __ `/ / __ `/
                         / __/    /  __/ / /     / / / // /_/ /  / / / / / ____/ / /_/ / / /_/ / / /_/ /\s
                        /_/       \\___/ /_/     /_/ /_/ \\__,_/  /_/ /_/ /_/      \\__,_/  \\__,_/  \\__, / \s
                                                                                                   /_/  \s
                    **********************************************************************************************
                    """);
            System.out.print("""
                    ***************************
                    * Bienvenido a FernanPaaq *
                    *   1.- Iniciar sesión    *
                    *   2.- Registrarse       *
                    *   3.- Apagar            *
                    ***************************
                                        
                     Introduzca la opción deseada:\s""");
            op = Integer.parseInt(s.nextLine());
            Utils.limpiarPantalla();

            switch (op) {
                case 1 -> { // Iniciar sesion
                    login = false;
                    intentosLogin = 3;
                    Destinatario destinatarioLogueado = null;
                    Conductor conductorLogueado = null;
                    do {
                        System.out.print("Introduzca email: ");
                        emailTeclado = s.nextLine();
                        System.out.print("Introduzca contraseña: ");
                        claveTeclado = s.nextLine();

                        if ((admin != null && admin.login(emailTeclado, claveTeclado)) ||
                                (destinatario1 != null && destinatario1.login(emailTeclado, claveTeclado)) ||
                                (destinatario2 != null && destinatario2.login(emailTeclado, claveTeclado)) ||
                                (conductor1 != null && conductor1.login(emailTeclado, claveTeclado)) ||
                                (conductor2 != null && conductor2.login(emailTeclado, claveTeclado)) ||
                                (conductor3 != null && conductor3.login(emailTeclado, claveTeclado))) {
                            login = true;

                            // Comprobamos el tipo de usuario que se loguea (1 = Destinatario ; 2 = Conductor ;
                            // 3 = Administrador)

                            if ((destinatario1 != null && destinatario1.login(emailTeclado, claveTeclado)) ||
                                    (destinatario2 != null && destinatario2.login(emailTeclado, claveTeclado))) {
                                tipoUsuario = 1;
                                // Comprobamos quién se loguea
                                if (destinatario1 != null && emailTeclado.equals(destinatario1.getEmail()))
                                    destinatarioLogueado = destinatario1;
                                if (destinatario2 != null && emailTeclado.equals(destinatario2.getEmail()))
                                    destinatarioLogueado = destinatario2;
                            }

                            if ((conductor1 != null && conductor1.login(emailTeclado, claveTeclado)) ||
                                    (conductor2 != null && conductor2.login(emailTeclado, claveTeclado)) ||
                                    (conductor3 != null && conductor3.login(emailTeclado, claveTeclado))) {
                                tipoUsuario = 2;
                                // Comprobamos quién se loguea
                                if (conductor1 != null && emailTeclado.equals(conductor1.getEmail()))
                                    conductorLogueado = conductor1;
                                if (conductor2 != null && emailTeclado.equals(conductor2.getEmail()))
                                    conductorLogueado = conductor2;
                                if (conductor3 != null && emailTeclado.equals(conductor3.getEmail()))
                                    conductorLogueado = conductor3;

                            }

                            if (admin.login(emailTeclado, claveTeclado)) tipoUsuario = 3;

                        } else {
                            intentosLogin--;
                            System.out.printf("Error al iniciar sesión. Quedan %d intentos\n", intentosLogin);
                        }
                    } while (!login && intentosLogin != 0);

                    if (!login) {
                        Utils.animacion("Volviendo al menú principal");
                        Utils.limpiarPantalla();
                    } else {
                        Utils.animacion("Iniciando sesión");
                        Utils.limpiarPantalla();
                        // Mostramos el menú dependiendo del tipo de usuario que se logueó

                        switch (tipoUsuario) {
                            case 1 -> { // Destinatario
                                do {
                                    System.out.println(destinatarioLogueado.pintaMenu());
                                    System.out.print("Introduzca una opción: ");
                                    op = Integer.parseInt(s.nextLine());
                                    Utils.limpiarPantalla();
                                    switch (op) {
                                        case 1 -> { // Seguir mis envíos
                                            if (!destinatarioLogueado.existenEnvios()) {
                                                System.out.println("Aún no se han registrado envíos");
                                                Utils.pulsaParaContinuar();
                                            }else {
                                                if (destinatarioLogueado.getEnvio1() != null) {
                                                    System.out.println(destinatarioLogueado.getEnvio1().pintaDatos());
                                                    Utils.pulsaParaContinuar();
                                                }
                                                if (destinatarioLogueado.getEnvio2() != null) {
                                                    System.out.println(destinatarioLogueado.getEnvio2().pintaDatos());
                                                    Utils.pulsaParaContinuar();
                                                }
                                            }


                                            Utils.limpiarPantalla();
                                        }

                                        case 2 -> { // Modificar mis datos de entrega para un envío
                                            if (!destinatarioLogueado.existenEnvios()) {
                                                System.out.println("Aún no se han registrado envíos");
                                                Utils.pulsaParaContinuar();
                                            } else {
                                                envioHaSidoElegido = false;
                                                envioSeleccionado = null;
                                                do {
                                                    System.out.println("=== LISTADO DE PEDIDOS ===");
                                                    System.out.println("**********************************************************");
                                                    if (destinatarioLogueado.getEnvio1() != null && !destinatarioLogueado.getEnvio1().estaEntregado()) {
                                                        System.out.println("* 1 -> " + destinatarioLogueado.getEnvio1().pintaEnvioDestinatario());
                                                    }

                                                    if (destinatarioLogueado.getEnvio2() != null && !destinatarioLogueado.getEnvio2().estaEntregado()) {
                                                        System.out.println("* 2 -> " + destinatarioLogueado.getEnvio2().pintaEnvioDestinatario());
                                                    }
                                                    System.out.println("* 3 -> Volver al menú anterior");
                                                    System.out.println("**********************************************************");
                                                    System.out.print("Elige el envío que deseas modificar: ");
                                                    op = Integer.parseInt(s.nextLine());
                                                    Utils.limpiarPantalla();
                                                    if ((op == 1 && destinatarioLogueado.getEnvio1() == null) ||
                                                            (op == 2 && destinatarioLogueado.getEnvio2() == null)) {
                                                        System.out.println("Opción no válida actualmente");
                                                        Utils.pulsaParaContinuar();
                                                    } else {
                                                        switch (op) {
                                                            case 1 -> { //envio1
                                                                envioSeleccionado = destinatarioLogueado.getEnvio1();
                                                                envioHaSidoElegido = true;
                                                            }

                                                            case 2 -> { //envio2
                                                                envioSeleccionado = destinatarioLogueado.getEnvio2();
                                                                envioHaSidoElegido = true;
                                                            }

                                                            case 3 -> { // Volver al menu anterior
                                                                Utils.animacion("Volviendo al menú anterior");
                                                            }

                                                            default -> System.out.println("Opción no válida");
                                                        }
                                                    }
                                                    Utils.limpiarPantalla();

                                                } while (op != 3 && !envioHaSidoElegido);


                                                if (envioHaSidoElegido) {
                                                    do {
                                                        System.out.println(envioSeleccionado.pintaEnvioDestinatario());
                                                        System.out.println("""
                                                            ****************************
                                                            *   1.- Destinatario       *
                                                            *   2.- Dirección          *
                                                            *   3.- Localidad          *
                                                            *   4.- Provincia          *
                                                            *   5.- Teléfono           *
                                                            *   6.- Finalizar cambios  *
                                                            ****************************                                                       
                                                            """);
                                                        System.out.print("Elige el campo a modificar: ");
                                                        op = Integer.parseInt(s.nextLine());
                                                        Utils.limpiarPantalla();
                                                        switch (op) {
                                                            case 1 -> { //Destinatario
                                                                System.out.print("Introduce nuevo nombre: ");
                                                                envioSeleccionado.getDestinatario().setNombre(s.nextLine());
                                                                System.out.println("Campo actualizado");
                                                                Utils.pulsaParaContinuar();

                                                                System.out.print("Introduce nuevo apellido: ");
                                                                envioSeleccionado.getDestinatario().setApellido(s.nextLine());
                                                                System.out.println("Campo actualizado");
                                                                Utils.pulsaParaContinuar();
                                                                Utils.limpiarPantalla();
                                                            }


                                                            case 2 -> { //Direccion
                                                                System.out.print("Introduce nueva dirección: ");
                                                                envioSeleccionado.getDestinatario().setDireccion(s.nextLine());
                                                                System.out.println("Campo actualizado");
                                                                Utils.pulsaParaContinuar();
                                                                Utils.limpiarPantalla();
                                                            }

                                                            case 3 -> { //Localidad
                                                                System.out.print("Introduce nueva localidad: ");
                                                                envioSeleccionado.getDestinatario().setLocalidad(s.nextLine());
                                                                System.out.println("Campo actualizado");
                                                                Utils.pulsaParaContinuar();
                                                                Utils.limpiarPantalla();
                                                            }

                                                            case 4 -> { //Provincia
                                                                System.out.print("Introduce nueva provincia: ");
                                                                envioSeleccionado.getDestinatario().setProvincia(s.nextLine());
                                                                System.out.println("Campo actualizado");
                                                                Utils.pulsaParaContinuar();
                                                                Utils.limpiarPantalla();
                                                            }

                                                            case 5 -> {//Telefono
                                                                System.out.print("Introduce nuevo teléfono: ");
                                                                envioSeleccionado.getDestinatario().setMovil(s.nextLine());
                                                                System.out.println("Campo actualizado");
                                                                Utils.pulsaParaContinuar();
                                                                Utils.limpiarPantalla();
                                                            }

                                                            case 6 -> { //Finalizar cambios
                                                                Utils.animacion("Volviendo al menú anterior");
                                                            }
                                                        }
                                                    } while (op != 6);
                                                }
                                            }
                                            Utils.limpiarPantalla();
                                        }

                                        case 3 -> { // Ver mi perfil
                                            System.out.println(destinatarioLogueado.pintaDatos());
                                            Utils.pulsaParaContinuar();
                                            Utils.limpiarPantalla();
                                        }

                                        case 4 -> { // Modificar mi perfil
                                            System.out.println(destinatarioLogueado.pintaDatos());
                                            Utils.pulsaParaContinuar();
                                            Utils.limpiarPantalla();

                                            do {
                                                System.out.println("""
                                                    ***************************************
                                                    *   1.- Nombre                        *
                                                    *   2.- Apellido                      *
                                                    *   3.- Clave                         *
                                                    *   4.- Teléfono                      *
                                                    *   5.- Dirección                     *
                                                    *   6.- Localidad                     *
                                                    *   7.- Provincia                     *
                                                    *   8.- Volver al menú anterior       *
                                                    ***************************************
                                                    """);
                                                System.out.print("Introduce el campo a modificar: ");
                                                op = Integer.parseInt(s.nextLine());

                                                switch (op) {
                                                    case 1 -> { // nombre
                                                        System.out.print("Introduce nuevo nombre: ");
                                                        destinatarioLogueado.setNombre(s.nextLine());
                                                        System.out.println("Campo actualizado");
                                                        Utils.pulsaParaContinuar();
                                                    }

                                                    case 2 -> { // apellido
                                                        System.out.print("Introduce nuevo apellido: ");
                                                        destinatarioLogueado.setApellido(s.nextLine());
                                                        System.out.println("Campo actualizado");
                                                        Utils.pulsaParaContinuar();
                                                    }

                                                    case 3 -> { // clave
                                                        do {
                                                            System.out.print("Introduce nueva contraseña: ");
                                                            claveTeclado = s.nextLine();
                                                            System.out.print("Introduce nueva contraseña de nuevo: ");
                                                            claveConfirmacion = s.nextLine();
                                                            if (!claveTeclado.equals(claveConfirmacion))
                                                                System.out.println("Hubo un error");
                                                            else destinatarioLogueado.setClave(claveTeclado);
                                                        } while (!claveTeclado.equals(claveConfirmacion));
                                                        System.out.println("Campo actualizado");
                                                        Utils.pulsaParaContinuar();
                                                    }

                                                    case 4 -> { // telefono
                                                        System.out.print("Introduce nuevo teléfono: ");
                                                        destinatarioLogueado.setMovil(s.nextLine());
                                                        System.out.println("Campo actualizado");
                                                        Utils.pulsaParaContinuar();
                                                    }

                                                    case 5 -> { // direccion
                                                        System.out.print("Introduce nueva dirección: ");
                                                        destinatarioLogueado.setDireccion(s.nextLine());
                                                        System.out.println("Campo actualizado");
                                                        Utils.pulsaParaContinuar();
                                                    }

                                                    case 6 -> { // localidad
                                                        System.out.print("Introduce nueva localidad: ");
                                                        destinatarioLogueado.setLocalidad(s.nextLine());
                                                        System.out.println("Campo actualizado");
                                                        Utils.pulsaParaContinuar();
                                                    }

                                                    case 7 -> { // provincia
                                                        System.out.print("Introduce nueva provincia: ");
                                                        destinatarioLogueado.setProvincia(s.nextLine());
                                                        System.out.println("Campo actualizado");
                                                        Utils.pulsaParaContinuar();
                                                    }

                                                    case 8 -> { // volver al menu anterior
                                                        Utils.animacion("Volviendo al menú anterior");
                                                    }

                                                    default -> {
                                                        System.out.println("Opción no válida");
                                                        Utils.pulsaParaContinuar();
                                                    }
                                                }
                                                Utils.limpiarPantalla();
                                            } while (op != 8);
                                        }

                                        case 5 -> { // Cerrar sesión
                                            Utils.animacion("Cerrando sesión");
                                        }

                                        default -> {
                                            System.out.println("Opción no válida");
                                        }
                                    }
                                    Utils.limpiarPantalla();
                                }while(op != 5);
                            }

                            case 2 -> { // Conductor
                                do {
                                    System.out.println(conductorLogueado.pintaMenu());
                                    System.out.print("Introduzca una opción: ");
                                    op = Integer.parseInt(s.nextLine());
                                    Utils.limpiarPantalla();
                                    switch (op) {
                                        case 1 -> { // Ver la información de mis envíos
                                            if (!conductorLogueado.existenEnvios()) {
                                                System.out.println("Aún no se han asignado envíos");
                                                Utils.pulsaParaContinuar();
                                            }else {
                                                if (conductorLogueado.getEnvio1() != null) {
                                                    System.out.println(conductorLogueado.getEnvio1().pintaEnvioConductor());
                                                    Utils.pulsaParaContinuar();
                                                }
                                                if (conductorLogueado.getEnvio2() != null) {
                                                    System.out.println(conductorLogueado.getEnvio2().pintaEnvioConductor());
                                                    Utils.pulsaParaContinuar();
                                                }
                                            }
                                            Utils.limpiarPantalla();
                                        }

                                        case 2 -> { // Cambiar el estado de un envío
                                            if (!conductorLogueado.existenEnvios()) {
                                                System.out.println("Aún no se han asignado envíos");
                                                Utils.pulsaParaContinuar();
                                            }else {
                                                envioHaSidoElegido = false;
                                                envioSeleccionado = null;
                                                envioHaSidoActualizado = false;
                                                do {
                                                    System.out.println("=== LISTADO DE PEDIDOS ===");
                                                    System.out.println("**********************************************************");
                                                    if (conductorLogueado.getEnvio1() != null && !conductorLogueado.getEnvio1().estaEntregado()) {
                                                        System.out.println("* 1 -> " + conductorLogueado.getEnvio1().pintaEnvioSimple());
                                                    }

                                                    if (conductorLogueado.getEnvio2() != null && !conductorLogueado.getEnvio2().estaEntregado()) {
                                                        System.out.println("* 2 -> " + conductorLogueado.getEnvio2().pintaEnvioSimple());
                                                    }
                                                    System.out.println("* 3 -> Volver al menú anterior");
                                                    System.out.println("**********************************************************");
                                                    System.out.print("Elige el envío que deseas modificar: ");
                                                    op = Integer.parseInt(s.nextLine());
                                                    Utils.limpiarPantalla();
                                                    if ((op == 1 && conductorLogueado.getEnvio1() == null) ||
                                                            (op == 2 && conductorLogueado.getEnvio2() == null)) {
                                                        System.out.println("Opción no válida actualmente");
                                                        Utils.pulsaParaContinuar();
                                                    } else {
                                                        switch (op) {
                                                            case 1 -> { //envio1
                                                                envioSeleccionado = conductorLogueado.getEnvio1();
                                                                envioHaSidoElegido = true;
                                                            }

                                                            case 2 -> { //envio2
                                                                envioSeleccionado = conductorLogueado.getEnvio2();
                                                                envioHaSidoElegido = true;
                                                            }

                                                            case 3 -> { // Volver al menu anterior
                                                                Utils.animacion("Volviendo al menú anterior");
                                                            }

                                                            default -> System.out.println("Opción no válida");
                                                        }
                                                    }
                                                    Utils.limpiarPantalla();
                                                } while (op != 3 && !envioHaSidoElegido);

                                                if (envioHaSidoElegido) {
                                                    do {
                                                        System.out.println(envioSeleccionado.pintaEnvioConductorSimple());
                                                        System.out.printf("""
                                                            === Estado del pedido %d ===
                                                                    1.- En almacén
                                                                    2.- En reparto
                                                                    3.- Entregado
                                                                    4.- Volver al menú anterior
                                                                Seleccione una opción:\s""", envioSeleccionado.getNumSeguimiento());
                                                        op = Integer.parseInt(s.nextLine());
                                                        Utils.limpiarPantalla();
                                                        switch (op) {
                                                            case 1 -> { // en almacen
                                                                if (envioSeleccionado.estaEnAlmacen()) System.out.println("El envío ya se encuentra en ese estado");
                                                                else {
                                                                    if (envioSeleccionado.estaEntregado())
                                                                        System.out.println("El pedido no puede actualizarse porque ya fue entregado");
                                                                    else {
                                                                        envioHaSidoActualizado = true;
                                                                        envioSeleccionado.setEstado("En almacén");
                                                                        System.out.println("Estado actualizado");
                                                                    }
                                                                }
                                                                Utils.pulsaParaContinuar();
                                                            }

                                                            case 2 -> { // en reparto
                                                                if (envioSeleccionado.estaEnReparto()) System.out.println("El envío ya se encuentra en ese estado");
                                                                else {
                                                                    if (envioSeleccionado.estaEntregado())
                                                                        System.out.println("El pedido no puede actualizarse porque ya fue entregado");
                                                                    else {
                                                                        int diasEntrega = 0;
                                                                        envioHaSidoActualizado = true;
                                                                        envioSeleccionado.setEstado("En reparto");
                                                                        envioSeleccionado.setFechaEnvio();
                                                                        do {
                                                                            System.out.print("Indique los días aproximados para realizar la entrega: ");
                                                                            diasEntrega = Integer.parseInt(s.nextLine());
                                                                            if (diasEntrega < 0) System.out.println("Valor no válido");
                                                                            else envioSeleccionado.setDiasEntrega(diasEntrega);
                                                                        }while(diasEntrega < 0);
                                                                        System.out.println("Estado actualizado");
                                                                    }
                                                                }
                                                                Utils.pulsaParaContinuar();
                                                            }

                                                            case 3 -> { // entregado
                                                                if (envioSeleccionado.estaEntregado()) System.out.println("El envío ya se encuentra en ese estado");
                                                                else {
                                                                    if (!envioSeleccionado.estaEnReparto())
                                                                        System.out.println("El envío aún no se encuentra en reparto para poder entregarlo");
                                                                    else {
                                                                        int diaEnvio = envioSeleccionado.getFechaEnvio().getDayOfMonth();
                                                                        do {
                                                                            System.out.print("Introduzca el día que se ha realizado la entrega: ");
                                                                            int diaEntrega = Integer.parseInt(s.nextLine());
                                                                            System.out.print("Introduzca el mes que se ha realizado la entrega: ");
                                                                            int mesEntrega = Integer.parseInt(s.nextLine());

                                                                            if ( (mesEntrega == 1 && (diaEntrega < diaEnvio || diaEntrega > 31)) ||
                                                                                    (mesEntrega == 2 && (diaEntrega < diaEnvio || diaEntrega > 29)) ||
                                                                                    (mesEntrega == 3 && (diaEntrega < diaEnvio || diaEntrega > 31)) ||
                                                                                    (mesEntrega == 4 && (diaEntrega < diaEnvio || diaEntrega > 30)) ||
                                                                                    (mesEntrega == 5 && (diaEntrega < diaEnvio || diaEntrega > 31)) ||
                                                                                    (mesEntrega == 6 && (diaEntrega < diaEnvio || diaEntrega > 30)) ||
                                                                                    (mesEntrega == 7 && (diaEntrega < diaEnvio || diaEntrega > 31)) ||
                                                                                    (mesEntrega == 8 && (diaEntrega < diaEnvio || diaEntrega > 31)) ||
                                                                                    (mesEntrega == 9 && (diaEntrega < diaEnvio || diaEntrega > 30)) ||
                                                                                    (mesEntrega == 10 && (diaEntrega < diaEnvio || diaEntrega > 31)) ||
                                                                                    (mesEntrega == 11 && (diaEntrega < diaEnvio || diaEntrega > 30)) ||
                                                                                    (mesEntrega == 12 && (diaEntrega < diaEnvio || diaEntrega > 31)) ) {
                                                                                System.out.println("Hubo un error en los datos");
                                                                            }
                                                                            else {
                                                                                envioHaSidoActualizado = true;
                                                                                envioSeleccionado.setFechaHoraEntrega(diaEntrega, mesEntrega);
                                                                                envioSeleccionado.setEstado("Entregado");
                                                                                System.out.println("Estado actualizado");

                                                                            }
                                                                        }while(!envioHaSidoActualizado);
                                                                    }
                                                                }
                                                                Utils.pulsaParaContinuar();
                                                            }

                                                            case 4 -> { //Volver al menu anterior
                                                                Utils.animacion("Volviendo al menú anterior");
                                                            }

                                                            default -> System.out.println("Opción no válida");
                                                        }
                                                        Utils.limpiarPantalla();
                                                    }while(op != 4 && !envioHaSidoActualizado);
                                                }
                                            }
                                            Utils.limpiarPantalla();
                                        }

                                        case 3 -> { // Ver el histórico de paquetes entregados
                                            if (!conductorLogueado.existenEnvios()) {
                                                System.out.println("Aún no se han asignado envíos");
                                                Utils.pulsaParaContinuar();
                                            }
                                            else {
                                                if (conductorLogueado.getEnvio1() != null && !conductorLogueado.getEnvio1().estaEntregado() &&
                                                        conductorLogueado.getEnvio2() != null && !conductorLogueado.getEnvio2().estaEntregado())
                                                    System.out.println("Aún no se ha entregado ningún envío");
                                                else {
                                                    if (conductorLogueado.getEnvio1() != null && conductorLogueado.getEnvio1().estaEntregado()) {
                                                        System.out.println(conductorLogueado.getEnvio1().pintaEnvioEntregado());
                                                        Utils.pulsaParaContinuar();
                                                    }

                                                    if (conductorLogueado.getEnvio2() != null && conductorLogueado.getEnvio2().estaEntregado()) {
                                                        System.out.println(conductorLogueado.getEnvio2().pintaEnvioEntregado());
                                                        Utils.pulsaParaContinuar();
                                                    }
                                                }
                                            }
                                            Utils.limpiarPantalla();
                                        }

                                        case 4 -> { // Modificar mi perfil
                                            System.out.println(conductorLogueado.pintaDatos());
                                            Utils.pulsaParaContinuar();
                                            Utils.limpiarPantalla();

                                            do {
                                                System.out.println("""
                                                    ***************************************
                                                    *   1.- Nombre                        *
                                                    *   2.- Apellido                      *
                                                    *   3.- Clave                         *
                                                    *   4.- Teléfono                      *
                                                    *   5.- Provincia referencia          *
                                                    *   6.- Volver al menú anterior       *
                                                    ***************************************
                                                    """);
                                                System.out.print("Introduce el campo a modificar: ");
                                                op = Integer.parseInt(s.nextLine());
                                                switch (op) {
                                                    case 1 -> { // nombre
                                                        System.out.print("Introduce nuevo nombre: ");
                                                        conductorLogueado.setNombre(s.nextLine());
                                                        System.out.println("Campo actualizado");
                                                        Utils.pulsaParaContinuar();
                                                    }

                                                    case 2 -> { // apellido
                                                        System.out.print("Introduce nuevo apellido: ");
                                                        conductorLogueado.setApellido(s.nextLine());
                                                        System.out.println("Campo actualizado");
                                                        Utils.pulsaParaContinuar();
                                                    }

                                                    case 3 -> { // clave
                                                        do {
                                                            System.out.print("Introduce nueva contraseña: ");
                                                            claveTeclado = s.nextLine();
                                                            System.out.print("Introduce nueva contraseña de nuevo: ");
                                                            claveConfirmacion = s.nextLine();
                                                            if (!claveTeclado.equals(claveConfirmacion))
                                                                System.out.println("Hubo un error");
                                                            else conductorLogueado.setClave(claveTeclado);
                                                        } while (!claveTeclado.equals(claveConfirmacion));
                                                        System.out.println("Campo actualizado");
                                                        Utils.pulsaParaContinuar();
                                                    }

                                                    case 4 -> { // telefono
                                                        System.out.print("Introduce nuevo teléfono: ");
                                                        conductorLogueado.setMovil(s.nextLine());
                                                        System.out.println("Campo actualizado");
                                                        Utils.pulsaParaContinuar();
                                                    }

                                                    case 5 -> { // direccion
                                                        System.out.print("Introduce nueva provincia de referencia: ");
                                                        conductorLogueado.setProvinciaRef(s.nextLine());
                                                        System.out.println("Campo actualizado");
                                                        Utils.pulsaParaContinuar();
                                                    }

                                                    case 6 -> { // volver al menu anterior
                                                        Utils.animacion("Volviendo al menú anterior");
                                                    }


                                                    default -> {
                                                        System.out.println("Opción no válida");
                                                        Utils.pulsaParaContinuar();
                                                    }
                                                }
                                                Utils.limpiarPantalla();
                                            } while (op != 6);
                                        }

                                        case 5 -> { // Cerrar sesión
                                            Utils.animacion("Cerrando sesión");
                                        }

                                        default -> {
                                            System.out.println("Opción no válida");
                                        }
                                    }
                                    Utils.limpiarPantalla();
                                }while(op != 5);
                            }

                            case 3 -> { // Administrador
                                do {
                                    envioHaSidoAsignado = false;
                                    System.out.println(admin.pintaMenu());
                                    System.out.print("Introduzca una opción: ");
                                    op = Integer.parseInt(s.nextLine());
                                    Utils.limpiarPantalla();
                                    switch (op) {
                                        case 1 -> { // Registrar un nuevo envío
                                            finRegistro = false;
                                            if (!admin.hayHuecoParaRegistrar())
                                                System.out.println("No se pueden registrar más envíos");
                                            else {
                                                do {
                                                    System.out.println("*LISTA DE DESTINATARIOS*");
                                                    System.out.println("**************************************");
                                                    if (destinatario1 != null)
                                                        System.out.printf("*  1->  %s\n", destinatario1.getEmail());
                                                    if (destinatario2 != null)
                                                        System.out.printf("*  2->  %s\n", destinatario2.getEmail());
                                                    if (destinatario1 == null || destinatario2 == null)
                                                        System.out.println("*  3-> Añadir nuevo destinatario");
                                                    System.out.println("*  4-> Volver al menú anterior");
                                                    System.out.println("**************************************");
                                                    System.out.print("Introduzca opción: ");
                                                    op = Integer.parseInt(s.nextLine());

                                                    // Evitamos que en el switch se acceda a una opción sin que exista el destinatario correspondiente
                                                    if ((op == 1 && destinatario1 == null) || (op == 2 && destinatario2 == null) ||
                                                            (op == 3 && destinatario1 != null && destinatario2 != null)) {
                                                        System.out.println("Opción no válida actualmente");
                                                        Utils.pulsaParaContinuar();
                                                    } else {
                                                        switch (op) {
                                                            case 1 -> { // destinatario1
                                                                if (destinatario1 != null && !destinatario1.hayHuecoParaEnvios())
                                                                    System.out.println("No puede recibir más envíos");
                                                                else {
                                                                    admin.registrarEnvio(destinatario1);
                                                                    // Se comprueba si puede asignarse el envío a algún conductor
                                                                    if (conductor1 != null && admin.asignarConductorAutomaticamente(conductor1))
                                                                        envioHaSidoAsignado = true;
                                                                    if (!envioHaSidoAsignado && conductor2 != null && admin.asignarConductorAutomaticamente(conductor2))
                                                                        envioHaSidoAsignado = true;
                                                                    if (!envioHaSidoAsignado && conductor3 != null && admin.asignarConductorAutomaticamente(conductor3))
                                                                        envioHaSidoAsignado = true;
                                                                    finRegistro = true;
                                                                }
                                                            }

                                                            case 2 -> { // destinatario2
                                                                if (destinatario2 != null && !destinatario2.hayHuecoParaEnvios())
                                                                    System.out.println("No puede recibir más envíos");
                                                                else {
                                                                    admin.registrarEnvio(destinatario2);
                                                                    if (admin.asignarConductorAutomaticamente(conductor1))
                                                                        envioHaSidoAsignado = true;
                                                                    if (!envioHaSidoAsignado && conductor2 != null && admin.asignarConductorAutomaticamente(conductor2))
                                                                        envioHaSidoAsignado = true;
                                                                    if (!envioHaSidoAsignado && conductor3 != null && admin.asignarConductorAutomaticamente(conductor3))
                                                                        envioHaSidoAsignado = true;
                                                                    finRegistro = true;
                                                                }
                                                            }

                                                            case 3 -> { // Registrar nuevo destinatario
                                                                emailValido = false;
                                                                System.out.print("Introduzca nombre: ");
                                                                nombreTeclado = s.nextLine();
                                                                System.out.print("Introduzca apellido: ");
                                                                apellidoTeclado = s.nextLine();
                                                                do {
                                                                    System.out.print("Introduzca email: ");
                                                                    emailTeclado = s.nextLine();
                                                                    // Comprobamos si el email tiene un formato correcto
                                                                    if (!emailTeclado.contains("@") && !emailTeclado.contains("."))
                                                                        System.out.println("Email no válido");
                                                                    else { // Comprobamos si el email ya estaba registrado anteriormente
                                                                        if ((destinatario1 != null && destinatario1.getEmail().equals(emailTeclado)) ||
                                                                                (destinatario2 != null && destinatario2.getEmail().equals(emailTeclado)) ||
                                                                                (conductor1 != null && conductor1.getEmail().equals(emailTeclado)) ||
                                                                                (conductor2 != null && conductor2.getEmail().equals(emailTeclado)) ||
                                                                                (conductor3 != null && conductor3.getEmail().equals(emailTeclado)) ||
                                                                                (admin != null && admin.getEmail().equals(emailTeclado)))
                                                                            System.out.println("El email introducido ya se encuentra en uso");
                                                                        else emailValido = true;
                                                                    }
                                                                    if (!emailValido) {
                                                                        Utils.pulsaParaContinuar();
                                                                        Utils.limpiarPantalla();
                                                                    }
                                                                } while (!emailValido);
                                                                System.out.print("Introduzca número móvil: ");
                                                                movilTeclado = s.nextLine();
                                                                System.out.print("Introduzca una dirección: ");
                                                                direccionTeclado = s.nextLine();
                                                                System.out.print("Introduzca la localidad: ");
                                                                localidadTeclado = s.nextLine();
                                                                System.out.print("Introduzca la provincia correspondiente: ");
                                                                provinciaTeclado = s.nextLine();
                                                                // Se termina el registro comprobando qué objeto tiene valor null
                                                                if (destinatario1 == null) {
                                                                    destinatario1 = new Destinatario(nombreTeclado, apellidoTeclado, emailTeclado,
                                                                            "1234", movilTeclado, direccionTeclado, localidadTeclado, provinciaTeclado);
                                                                    admin.registrarEnvio(destinatario1);

                                                                } else {
                                                                    if (destinatario1 != null && destinatario2 == null) {
                                                                        destinatario2 = new Destinatario(nombreTeclado, apellidoTeclado, emailTeclado,
                                                                                "1234", movilTeclado, direccionTeclado, localidadTeclado, provinciaTeclado);
                                                                        admin.registrarEnvio(destinatario2);
                                                                    }
                                                                }
                                                                if (admin.asignarConductorAutomaticamente(conductor1))
                                                                    envioHaSidoAsignado = true;
                                                                if (!envioHaSidoAsignado && conductor2 != null && admin.asignarConductorAutomaticamente(conductor2))
                                                                    envioHaSidoAsignado = true;
                                                                if (!envioHaSidoAsignado && conductor3 != null && admin.asignarConductorAutomaticamente(conductor3))
                                                                    envioHaSidoAsignado = true;
                                                                finRegistro = true;
                                                            }

                                                            case 4 -> { // Volver al menú anterior
                                                                Utils.animacion("Volviendo al menú anterior");
                                                            }

                                                            default -> System.out.println("Opción no válida");
                                                        }
                                                    }

                                                } while (op != 4 && !finRegistro);

                                                if (finRegistro) {
                                                    Utils.animacion("Realizando registro");
                                                    Utils.limpiarPantalla();
                                                    System.out.println("Envío registrado correctamente");
                                                    System.out.println((envioHaSidoAsignado) ? "Se ha asignado el envío a un conductor" :
                                                            "No se ha podido asignar el envío a ningún conductor");
                                                }
                                            }
                                            if (op != 4) Utils.pulsaParaContinuar();
                                            Utils.limpiarPantalla();
                                        }

                                        case 2 -> { // Asignar un envío a un conductor
                                            envioHaSidoElegido = false;
                                            envioHaSidoAsignado = false;
                                            Envio envioAAsignar = null;
                                            if (!admin.hayEnviosRegistrados())
                                                System.out.println("Aún no se han registrados envíos");
                                            else {
                                                if (admin.calculaEnviosSinAsignar() == 0)
                                                    System.out.println("No hay envíos por asignar");
                                                else {
                                                    do {
                                                        System.out.println("=== Asignación de envíos sin conductor ===");
                                                        if (admin.getEnvio1() != null && admin.getEnvio1().getConductor() == null)
                                                            System.out.println("* 1 -> " + admin.getEnvio1().pintaEnvioSimple());

                                                        if (admin.getEnvio2() != null && admin.getEnvio2().getConductor() == null)
                                                            System.out.println("* 2 -> " + admin.getEnvio2().pintaEnvioSimple());

                                                        if (admin.getEnvio3() != null && admin.getEnvio3().getConductor() == null)
                                                            System.out.println("* 3 -> " + admin.getEnvio3().pintaEnvioSimple());

                                                        if (admin.getEnvio4() != null && admin.getEnvio4().getConductor() == null)
                                                            System.out.println("* 4 -> " + admin.getEnvio4().pintaEnvioSimple());

                                                        System.out.println("* 5 -> Volver al menú anterior");
                                                        System.out.print("Selecciona el envío a asignar: ");
                                                        op = Integer.parseInt(s.nextLine());

                                                        // Evitamos que se acceda una opción del switch no deseada
                                                        if ((op == 1 && admin.getEnvio1() == null) || (op == 2 && admin.getEnvio2() == null) ||
                                                                (op == 3 && admin.getEnvio3() == null) || (op == 4 && admin.getEnvio4() == null)){
                                                            System.out.println("Opción no válida actualmente");
                                                            Utils.pulsaParaContinuar();
                                                        } else {
                                                            switch (op) {
                                                                case 1 -> { // envio1
                                                                    envioAAsignar = admin.getEnvio1();
                                                                    envioHaSidoElegido = true;
                                                                }

                                                                case 2 -> { // envio2
                                                                    envioAAsignar = admin.getEnvio2();
                                                                    envioHaSidoElegido = true;
                                                                }

                                                                case 3 -> { // envio3
                                                                    envioAAsignar = admin.getEnvio3();
                                                                    envioHaSidoElegido = true;
                                                                }

                                                                case 4 -> { // envio4
                                                                    envioAAsignar = admin.getEnvio4();
                                                                    envioHaSidoElegido = true;
                                                                }

                                                                case 5 -> { // Volver al menú anterior
                                                                    Utils.animacion("Volviendo al menú anterior");
                                                                }

                                                                default -> System.out.println("Opción no válida");
                                                            }
                                                        }
                                                    } while (op != 5 && !envioHaSidoElegido);

                                                    Utils.limpiarPantalla();

                                                    if (envioHaSidoElegido) {
                                                        do {
                                                            System.out.printf("=== Asignación del pedido %d ===\n", envioAAsignar.getNumSeguimiento());
                                                            if (conductor1 != null && conductor1.hayHueco())
                                                                System.out.println("* 1 -> " + conductor1.pintaConductorAAsignar());

                                                            if (conductor2 != null && conductor2.hayHueco())
                                                                System.out.println("* 2 -> " + conductor2.pintaConductorAAsignar());

                                                            if (conductor3 != null && conductor3.hayHueco())
                                                                System.out.println("* 3 -> " + conductor3.pintaConductorAAsignar());

                                                            System.out.println("* 4 -> Volver al menú anterior");
                                                            System.out.print("Seleccione el conductor: ");
                                                            op = Integer.parseInt(s.nextLine());

                                                            // Evitamos que se acceda a una opción del switch no deseada
                                                            if ((op == 1 && conductor1 == null) || (op == 2 && conductor2 == null) ||
                                                                    (op == 3 && conductor3 == null)) {
                                                                System.out.println("Opción no válida actualmente");
                                                                Utils.pulsaParaContinuar();
                                                            } else {
                                                                if ( (op == 1 && !conductor1.hayHueco()) || (op == 2 && !conductor2.hayHueco()) ||
                                                                        (op == 3 && !conductor3.hayHueco())) {
                                                                    System.out.println("Al repartidor no se le pueden asignar más envíos");
                                                                    Utils.pulsaParaContinuar();
                                                                }else {
                                                                    switch (op) {
                                                                        case 1 -> { // conductor1
                                                                            admin.asignarConductor(envioAAsignar, conductor1);
                                                                            envioHaSidoAsignado = true;
                                                                        }

                                                                        case 2 -> { // conductor2
                                                                            admin.asignarConductor(envioAAsignar, conductor2);
                                                                            envioHaSidoAsignado = true;
                                                                        }

                                                                        case 3 -> { // conductor3
                                                                            admin.asignarConductor(envioAAsignar, conductor3);
                                                                            envioHaSidoAsignado = true;
                                                                        }

                                                                        case 4 -> { // Volver al menú anterior
                                                                            Utils.animacion("Volviendo al menú anterior");
                                                                        }

                                                                        default -> System.out.println("Opción no válida");
                                                                    }
                                                                }
                                                            }

                                                        } while (op != 4 && !envioHaSidoAsignado);

                                                        if (envioHaSidoAsignado) {
                                                            Utils.animacion("Asignando envío");
                                                            System.out.println("Asignado a " + envioAAsignar.getConductor().getNombre());
                                                        }
                                                    }
                                                }
                                            }

                                            Utils.pulsaParaContinuar();
                                            Utils.limpiarPantalla();

                                        }

                                        case 3 -> { // Ver los datos de todos los destinatarios
                                            if (destinatario1 == null && destinatario2 == null)
                                                System.out.println("No hay destinatarios registrados aún");
                                            else {
                                                Utils.animacion("Cargando datos");
                                                Utils.limpiarPantalla();

                                                if (destinatario1 != null) {
                                                    System.out.println(destinatario1.pintaDatos());
                                                    Utils.pulsaParaContinuar();
                                                }
                                                if (destinatario2 != null) {
                                                    System.out.println(destinatario2.pintaDatos());
                                                    Utils.pulsaParaContinuar();
                                                }
                                            }
                                            Utils.limpiarPantalla();
                                        }

                                        case 4 -> { // Ver los datos de todos los envíos
                                            if (!admin.hayEnviosRegistrados())
                                                System.out.println("No hay envíos registrados aún");
                                            else {
                                                Utils.animacion("Cargando datos");
                                                Utils.limpiarPantalla();

                                                if (admin.getEnvio1() != null) {
                                                    System.out.println(admin.getEnvio1().pintaDatos());
                                                    Utils.pulsaParaContinuar();
                                                }

                                                if (admin.getEnvio2() != null) {
                                                    System.out.println(admin.getEnvio2().pintaDatos());
                                                    Utils.pulsaParaContinuar();
                                                }

                                                if (admin.getEnvio3() != null) {
                                                    System.out.println(admin.getEnvio3().pintaDatos());
                                                    Utils.pulsaParaContinuar();
                                                }

                                                if (admin.getEnvio4() != null){
                                                    System.out.println(admin.getEnvio4().pintaDatos());
                                                    Utils.pulsaParaContinuar();
                                                }

                                            }

                                            Utils.limpiarPantalla();
                                        }

                                        case 5 -> { // Ver los datos de todos los conductores
                                            if (conductor1 == null && conductor2 == null && conductor3 == null)
                                                System.out.println("Aún no hay conductores registrados");
                                            else {

                                                if (conductor1 != null) {
                                                    System.out.println(conductor1.pintaDatos());
                                                    Utils.pulsaParaContinuar();
                                                }

                                                if (conductor2 != null) {
                                                    System.out.println(conductor2.pintaDatos());
                                                    Utils.pulsaParaContinuar();
                                                }

                                                if (conductor3 != null) {
                                                    System.out.println(conductor3.pintaDatos());
                                                    Utils.pulsaParaContinuar();
                                                }
                                            }
                                            Utils.limpiarPantalla();
                                        }

                                        case 6 -> { // Modificar mi perfil
                                            System.out.println(admin.pintaDatos());
                                            Utils.pulsaParaContinuar();
                                            do {
                                                System.out.println("""
                                                        \t1.- Nombre
                                                        \t2.- Apellido
                                                        \t3.- Clave
                                                        \t4.- Finalizar cambios
                                                        """);
                                                System.out.print("Introduce el campo que quieras modificar: ");
                                                op = Integer.parseInt(s.nextLine());
                                                switch (op) {
                                                    case 1 -> { // Nombre
                                                        System.out.print("Introduce nuevo nombre: ");
                                                        admin.setNombre(s.nextLine());
                                                        System.out.println("Campo actualizado");
                                                        Utils.pulsaParaContinuar();
                                                        Utils.limpiarPantalla();
                                                    }

                                                    case 2 -> { // Apellido
                                                        System.out.print("Introduce nuevo apellido: ");
                                                        admin.setApellido(s.nextLine());
                                                        System.out.println("Campo actualizado");
                                                        Utils.pulsaParaContinuar();
                                                        Utils.limpiarPantalla();
                                                    }


                                                    case 3 -> { // Clave
                                                        do {
                                                            System.out.print("Introduce nueva contraseña: ");
                                                            claveTeclado = s.nextLine();
                                                            System.out.print("Introduce nueva contraseña de nuevo: ");
                                                            claveConfirmacion = s.nextLine();
                                                            if (!claveTeclado.equals(claveConfirmacion))
                                                                System.out.println("Hubo un error");
                                                            else admin.setClave(claveTeclado);
                                                        } while (!claveTeclado.equals(claveConfirmacion));
                                                        System.out.println("Campo actualizado");
                                                        Utils.pulsaParaContinuar();
                                                        Utils.limpiarPantalla();
                                                    }

                                                    case 4 -> { // Volver al menu anterior
                                                        Utils.animacion("Volviendo al menú anterior");
                                                    }

                                                    default -> System.out.println("Opción no válida");
                                                }
                                            } while (op != 4);

                                            Utils.limpiarPantalla();
                                        }

                                        case 7 -> { // Cerrar sesión
                                            Utils.animacion("Cerrando sesión");
                                        }

                                        default -> {
                                            System.out.println("Opción no válida");
                                        }
                                    }
                                } while (op != 7);
                            }
                        }
                        Utils.limpiarPantalla();
                    }
                }

                case 2 -> { // Registrarse
                    if (destinatario1 != null && destinatario2 != null && conductor1 != null && conductor2 != null &&
                            conductor3 != null) System.out.println("No pueden registrarse más usuarios");
                    else {
                        comienzaRegistro = false;
                        emailValido = false;
                        Destinatario destinatarioRegistrado = null;
                        Conductor conductorRegistrado = null;
                        do {
                            System.out.print("""
                                    **************************************
                                    *       REGISTRO DE USUARIO          *
                                    *   1.- Destinatario                 *
                                    *   2.- Conductor                    *
                                    *   3.- Volver al menú principal     *
                                    **************************************
                                     Seleccione el tipo de usuario que desea:\s""");
                            op = Integer.parseInt(s.nextLine());

                            // Se comprueba de qué tipo se pueden registrar usuarios y se asigna el tipo para el formulario posterior
                            switch (op) {
                                case 1 -> { // Destinatario
                                    if (destinatario1 != null && destinatario2 != null)
                                        System.out.println("No se pueden registrar más destinatarios");
                                    else {
                                        comienzaRegistro = true;
                                        tipoUsuario = 1;
                                    }
                                }

                                case 2 -> { // Conductor
                                    if (conductor1 != null && conductor2 != null && conductor3 != null)
                                        System.out.println("No se pueden registrar más conductores");
                                    else {
                                        comienzaRegistro = true;
                                        tipoUsuario = 2;
                                    }
                                }

                                // Volver al menú principal
                                case 3 -> Utils.animacion("Volviendo al menú principal");

                                default -> {
                                    System.out.println("Opción no válida");
                                    Utils.pulsaParaContinuar();
                                    Utils.limpiarPantalla();
                                }
                            }

                        } while (op != 1 && op != 2 && op != 3 && !comienzaRegistro);

                        //Formulario de registro
                        if (comienzaRegistro) {
                            System.out.print("Introduzca nombre: ");
                            nombreTeclado = s.nextLine();
                            System.out.print("Introduzca apellido: ");
                            apellidoTeclado = s.nextLine();
                            do {
                                System.out.print("Introduzca email: ");
                                emailTeclado = s.nextLine();
                                // Comprobamos si el email tiene un formato correcto
                                if (!emailTeclado.contains("@") && !emailTeclado.contains("."))
                                    System.out.println("Email no válido");
                                else { // Comprobamos si el email ya estaba registrado anteriormente
                                    if ((destinatario1 != null && destinatario1.getEmail().equals(emailTeclado)) ||
                                            (destinatario2 != null && destinatario2.getEmail().equals(emailTeclado)) ||
                                            (conductor1 != null && conductor1.getEmail().equals(emailTeclado)) ||
                                            (conductor2 != null && conductor2.getEmail().equals(emailTeclado)) ||
                                            (conductor3 != null && conductor3.getEmail().equals(emailTeclado)) ||
                                            (admin != null && admin.getEmail().equals(emailTeclado)))
                                        System.out.println("El email introducido ya se encuentra en uso");
                                    else emailValido = true;
                                }
                                if (!emailValido) {
                                    Utils.pulsaParaContinuar();
                                    Utils.limpiarPantalla();
                                }
                            } while (!emailValido);
                            System.out.print("Introduzca contraseña: ");
                            claveTeclado = s.nextLine();
                            System.out.print("Introduzca número móvil: ");
                            movilTeclado = s.nextLine();

                            // Los siguientes campos dependerán del tipo de usuario que se seleccionó

                            switch (tipoUsuario) {
                                case 1 -> { // Destinatario
                                    System.out.print("Introduzca una dirección: ");
                                    direccionTeclado = s.nextLine();
                                    System.out.print("Introduzca la localidad: ");
                                    localidadTeclado = s.nextLine();
                                    System.out.print("Introduzca la provincia correspondiente: ");
                                    provinciaTeclado = s.nextLine();
                                    // Se termina el registro comprobando qué objeto tiene valor null
                                    if (destinatario1 == null) {
                                        destinatario1 = new Destinatario(nombreTeclado, apellidoTeclado, emailTeclado,
                                                claveTeclado, movilTeclado, direccionTeclado, localidadTeclado, provinciaTeclado);
                                        destinatarioRegistrado = destinatario1;
                                    } else {
                                        if (destinatario1 != null && destinatario2 == null) {
                                            destinatario2 = new Destinatario(nombreTeclado, apellidoTeclado, emailTeclado,
                                                    claveTeclado, movilTeclado, direccionTeclado, localidadTeclado, provinciaTeclado);
                                            destinatarioRegistrado = destinatario2;
                                        }
                                    }

                                }

                                case 2 -> { // Conductor
                                    System.out.print("Introduzca la provincia en la que hará los repartos: ");
                                    provinciaRefTeclado = s.nextLine();
                                    // Se termina el registro comprobando qué objeto tiene valor null
                                    if (conductor1 == null) {
                                        conductor1 = new Conductor(nombreTeclado, apellidoTeclado, emailTeclado, claveTeclado,
                                                movilTeclado, provinciaRefTeclado);
                                        conductorRegistrado = conductor1;
                                    } else {
                                        if (conductor1 != null && conductor2 == null) {
                                            conductor2 = new Conductor(nombreTeclado, apellidoTeclado, emailTeclado, claveTeclado,
                                                    movilTeclado, provinciaRefTeclado);
                                            conductorRegistrado = conductor2;
                                        } else {
                                            if (conductor2 != null && conductor3 == null) {
                                                conductor3 = new Conductor(nombreTeclado, apellidoTeclado, emailTeclado, claveTeclado,
                                                        movilTeclado, provinciaRefTeclado);
                                                conductorRegistrado = conductor3;
                                            }
                                        }
                                    }
                                }
                            }
                            // Mostramos los datos del usuario recién registrado
                            Utils.animacion("Registrando usuario");
                            Utils.limpiarPantalla();
                            switch (tipoUsuario) {
                                case 1 -> System.out.println(destinatarioRegistrado.pintaDatos());
                                case 2 -> System.out.println(conductorRegistrado.pintaDatos());
                            }

                        }
                    }
                    Utils.pulsaParaContinuar();
                    Utils.limpiarPantalla();
                }

                case 3 -> { // Apagar
                    finPrograma = true;
                    Utils.animacion("Apagando programa");
                }

                default -> {
                    System.out.println("Opción no válida");
                    Utils.pulsaParaContinuar();
                    Utils.limpiarPantalla();
                }
            }

        } while (!finPrograma);
    }
}
