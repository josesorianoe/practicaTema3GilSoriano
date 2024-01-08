package models;

public class Conductor {
    // Atributos
    private String nombre;
    private String apellido;
    private String email;
    private String clave;
    private String movil;
    private String provinciaRef;
    private Envio envio1;
    private Envio envio2;

    // Constructor
    public Conductor(String nombre, String apellido, String email, String clave, String movil, String provinciaRef) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.clave = clave;
        this.movil = movil;
        this.provinciaRef = provinciaRef;
    }

    // G&S
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getProvinciaRef() {
        return provinciaRef;
    }

    public void setProvinciaRef(String provinciaRef) {
        this.provinciaRef = provinciaRef;
    }

    public Envio getEnvio1() {
        return envio1;
    }

    public void setEnvio1(Envio envio1) {
        this.envio1 = envio1;
    }

    public Envio getEnvio2() {
        return envio2;
    }

    public void setEnvio2(Envio envio2) {
        this.envio2 = envio2;
    }


    // Otros métodos
    public boolean login(String emailTeclado, String claveTeclado){
        return email.equals(emailTeclado) && clave.equals(claveTeclado);
    }
    public boolean hayHueco() {
        return envio1 == null || envio2 == null;
    }

    public boolean existenEnvios() {
        return envio1 != null || envio2 != null;
    }



    public String pintaMenu() {
        return "Bienvenido " + nombre + ". Gestione sus envíos asignados.\n" +
                """
                        \t1.- Ver la información de mis envíos
                        \t2.- Cambiar el estado de un envío
                        \t3.- Ver el histórico de paquetes entregados
                        \t4.- Modificar mi perfil
                        \t5.- Cerrar sesión
                        """;
    }

    public String pintaDatos() {
        return "*DATOS DE CONDUCTOR*\n" +
                "=======================\n" +
                "\t- Nombre: " + nombre + "\n" +
                "\t- Apellido: " + apellido + "\n" +
                "\t- Email: " + email + "\n" +
                "\t- Clave: " + clave + "\n" +
                "\t- Móvil: " + movil + "\n" +
                "\t- Provincia asignada: " + provinciaRef + "\n";
    }

    public String pintaConductorAAsignar() {
        return nombre + " - " + provinciaRef + " - " + movil + "\n";
    }
}
