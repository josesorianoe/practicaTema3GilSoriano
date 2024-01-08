package models;

import java.time.LocalDate;

public class Administrador {
    // Atributos
    private String nombre;
    private String apellido;
    private String email;
    private String clave;
    private Envio envio1;
    private Envio envio2;
    private Envio envio3;
    private Envio envio4;

    // Constructor
    public Administrador(String nombre, String apellido, String email, String clave) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.clave = clave;
    }

    // G&S
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Envio getEnvio1() {
        return envio1;
    }

    public Envio getEnvio2() {
        return envio2;
    }

    public Envio getEnvio3() {
        return envio3;
    }

    public Envio getEnvio4() {
        return envio4;
    }


    // Otros métodos
    public boolean login(String emailTeclado, String claveTeclado){
        return email.equals(emailTeclado) && clave.equals(claveTeclado);
    }

    public int calculaEnviosSinAsignar() {
        int contador = 0;
        if (envio1 != null && envio1.getConductor() == null) contador++;
        if (envio2 != null && envio2.getConductor() == null) contador++;
        if (envio3 != null && envio3.getConductor() == null) contador++;
        if (envio4 != null && envio4.getConductor() == null) contador++;
        return contador;
    }

    public boolean hayHuecoParaRegistrar() {
        return envio1 == null || envio2 == null || envio3 == null || envio4 == null;
    }

    public boolean hayEnviosRegistrados() {
        return envio1 != null || envio2 != null || envio3 != null || envio4 != null;
    }

    public void registrarEnvio(Destinatario destinatario) {
        if (envio1 == null) {
            envio1 = new Envio(destinatario);
            if (destinatario.getEnvio1() == null) destinatario.setEnvio1(envio1);
            else if (destinatario.getEnvio1() != null && destinatario.getEnvio2() == null) destinatario.setEnvio2(envio1);
        }else {
            if (envio1 != null && envio2 == null) {
                envio2 = new Envio(destinatario);
                if (destinatario.getEnvio1() == null) destinatario.setEnvio1(envio2);
                else if (destinatario.getEnvio1() != null && destinatario.getEnvio2() == null) destinatario.setEnvio2(envio2);
            }else {
                if (envio2 != null && envio3 == null) {
                    envio3 = new Envio(destinatario);
                    if (destinatario.getEnvio1() == null) destinatario.setEnvio1(envio3);
                    else if (destinatario.getEnvio1() != null && destinatario.getEnvio2() == null) destinatario.setEnvio2(envio3);
                } else {
                    if (envio3 != null && envio4 == null) {
                        envio4 = new Envio(destinatario);
                        if (destinatario.getEnvio1() == null) destinatario.setEnvio1(envio4);
                        else if (destinatario.getEnvio1() != null && destinatario.getEnvio2() == null) destinatario.setEnvio2(envio4);
                    }
                }
            }
        }
    }

    public boolean asignarConductorAutomaticamente(Conductor conductor) {
        if (envio1 != null && envio1.getConductor() == null && conductor.getProvinciaRef().equalsIgnoreCase(envio1.getDestinatario().getProvincia())) {
            envio1.setConductor(conductor);
            if (conductor.getEnvio1() == null) conductor.setEnvio1(envio1);
            else if (conductor.getEnvio2() == null) conductor.setEnvio2(envio1);
            return true;
        } else {
            if (envio2 != null && envio2.getConductor() == null && conductor.getProvinciaRef().equalsIgnoreCase(envio2.getDestinatario().getProvincia())) {
                envio2.setConductor(conductor);
                if (conductor.getEnvio1() == null) conductor.setEnvio1(envio2);
                else if (conductor.getEnvio2() == null) conductor.setEnvio2(envio2);
                return true;
            } else {
                if (envio3 != null && envio3.getConductor() == null && conductor.getProvinciaRef().equalsIgnoreCase(envio3.getDestinatario().getProvincia())) {
                    envio3.setConductor(conductor);
                    if (conductor.getEnvio1() == null) conductor.setEnvio1(envio3);
                    else if (conductor.getEnvio2() == null) conductor.setEnvio2(envio3);
                    return true;
                } else {
                    if (envio4 != null && envio4.getConductor() == null && conductor.getProvinciaRef().equalsIgnoreCase(envio4.getDestinatario().getProvincia())){
                        envio4.setConductor(conductor);
                        if (conductor.getEnvio1() == null) conductor.setEnvio1(envio4);
                        else if (conductor.getEnvio2() == null) conductor.setEnvio2(envio4);
                        return true;
                    }
                    return false;
                }
            }
        }
    }

    public void asignarConductor(Envio envio, Conductor conductor) {
        if (conductor.getEnvio1() == null) {
            envio.setConductor(conductor);
            conductor.setEnvio1(envio);
        } else {
            if (conductor.getEnvio2() == null) {
                envio.setConductor(conductor);
                conductor.setEnvio2(envio);
            }
        }
    }

    public String pintaMenu() {
        return "Bienvenido " + nombre + ". Administración FernanPaaq. " +
                ((calculaEnviosSinAsignar() == 0) ? "No tiene envíos pendientes de asignar" :
                        "Tiene " + calculaEnviosSinAsignar() + " envíos sin asignar") +  "\n" +
                """
                        \t1.- Registrar un nuevo envío
                        \t2.- Asignar un envío a un conductor
                        \t3.- Ver los datos de todos los destinatarios
                        \t4.- Ver los datos de todos los envíos
                        \t5.- Ver los datos de todos los conductores
                        \t6.- Modificar mi perfil
                        \t7.- Cerrar sesión
                        """;
    }

    public String pintaDatos() {
        return "*DATOS DE ADMINISTRADOR*\n" +
                "===========================\n" +
                "\t- Nombre: " + nombre + "\n" +
                "\t- Apellido: " + apellido + "\n" +
                "\t- Email: " + email + "\n" +
                "\t- Clave: " + clave + "\n";
    }

}
