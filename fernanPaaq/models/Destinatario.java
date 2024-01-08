package models;

public class Destinatario {
    //Atributos
    private String nombre;
    private String apellido;
    private String email;
    private String clave;
    private String movil;
    private String direccion;
    private String localidad;
    private String provincia;
    private Envio envio1;
    private Envio envio2;

    //Constructor
    public Destinatario(String nombre, String apellido, String email, String clave, String movil, String direccion, String localidad, String provincia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.clave = clave;
        this.movil = movil;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
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

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
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

    public boolean hayHuecoParaEnvios() {
        return envio1 == null || envio2 == null;
    }

    public boolean existenEnvios() {
        return envio1 != null || envio2 != null;
    }

    public String pintaMenu() {
        return "Bienvenido " + nombre + ". Siga sus envíos en FernanPaaq.\n" +
                """
                        \t1.- Seguir mis envíos
                        \t2.- Modificar mis datos de entrega para un envío
                        \t3.- Ver mi perfil
                        \t4.- Modificar mi perfil
                        \t5.- Cerrar sesión
                        """;
    }

    public String pintaDatos() {
        return "*DATOS DE DESTINATARIO*\n" +
                "=======================\n" +
                "\t- Nombre: " + nombre + "\n" +
                "\t- Apellido: " + apellido + "\n" +
                "\t- Email: " + email + "\n" +
                "\t- Clave: " + clave + "\n" +
                "\t- Móvil: " + movil + "\n" +
                "\t- Dirección: " + direccion + "\n" +
                "\t- Localidad: " + localidad + "\n" +
                "\t- Provincia: " + provincia + "\n";
    }

}
