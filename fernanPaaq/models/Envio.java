package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Envio {
    // Atributos
    private int numSeguimiento;
    private String estado;
    private Destinatario destinatario;
    private Conductor conductor;
    private LocalDate fechaCreacion;
    private LocalDate fechaEnvio;
    private int diasEntrega;
    private LocalDateTime fechaHoraEntrega;
    private static int numEnvios;

    // Constructor
    public Envio(Destinatario destinatario) {
        numEnvios++;
        estado = "En almacén";
        this.destinatario = destinatario;
        numSeguimiento = (numEnvios * 10000 + (int) (Math.random() * 1001));
        fechaCreacion = LocalDate.now();
    }

    // G&S
    public int getNumSeguimiento() {
        return numSeguimiento;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Destinatario getDestinatario() {
        return destinatario;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public LocalDate getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio() { fechaEnvio = LocalDate.now();
    }

    public void setDiasEntrega(int diasEntrega) {
        this.diasEntrega = diasEntrega;
    }


    // Otros métodos
    public boolean estaEnAlmacen() {
        return estado.equalsIgnoreCase("En almacén");
    }
    public boolean estaEnReparto() {
        return estado.equalsIgnoreCase("En reparto");
    }
    public boolean estaEntregado() {
        return estado.equalsIgnoreCase("Entregado");
    }


    public LocalDate calculaFechaEntregaAprox() {
        return fechaEnvio.plusDays(diasEntrega);
    }

    public void setFechaHoraEntrega(int dia, int mes) {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        fechaHoraEntrega = LocalDateTime.of(fechaHoraActual.getYear(), mes, dia, fechaHoraActual.getHour(), fechaHoraActual.getMinute(), fechaHoraActual.getSecond());
    }


    public String pintaDatos() {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        
        LocalDate fechaEntregaAprox = null;
        if (fechaEnvio != null) fechaEntregaAprox = calculaFechaEntregaAprox();

        LocalDate fechaEntrega = null;
        LocalTime horaEntrega = null;
        if (fechaHoraEntrega != null) {
            fechaEntrega = fechaHoraEntrega.toLocalDate();
            horaEntrega = fechaHoraEntrega.toLocalTime();
        }

        return "***********************************************************\n" +
                "*** Envío con número de seguimiento " + numSeguimiento + " ***\n" +
                "Estado: " + estado + "\n" +
                "Destinatario: " + destinatario.getNombre() + " " + destinatario.getApellido() + "\n" +
                "Dirección: " + destinatario.getDireccion() + "\n" +
                "Localidad: " + destinatario.getLocalidad() + "\n" +
                "Provincia: " + destinatario.getProvincia() + "\n" +
                "Teléfono: " + destinatario.getMovil() + "\n" +
                "Conductor: " + ((conductor == null) ? "Aún no asignado" : (conductor.getNombre() + " " + conductor.getApellido())) + "\n" +
                "Teléfono del conductor: " + ((conductor == null) ? "-" : conductor.getMovil()) + "\n" +
                "Fecha de creación: " + fechaCreacion.format(formatoFecha) + "\n" +
                "Fecha de envío: " + ((fechaEnvio == null) ? "-" : fechaEnvio.format(formatoFecha)) + "\n" +
                "Fecha de entrega aprox.: " + ((fechaEnvio == null) ? "-" : fechaEntregaAprox.format(formatoFecha)) + "\n" +
                "Fecha y hora de entrega: " + ((estaEntregado()) ?
                (fechaEntrega.format(formatoFecha) + " - " + horaEntrega.format(formatoHora)) : "-") + "\n" +
                "***********************************************************\n";
    }

    public String pintaEnvioSimple() {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return numSeguimiento + " - " + destinatario.getLocalidad() + " (" + destinatario.getProvincia() + ") - " +
                fechaCreacion.format(formatoFecha) + "\n";
    }

    public String pintaEnvioDestinatario() {
        return  "*** Num. Seguimiento " + numSeguimiento + " ***\n" +
                "\tEstado: " + estado + "\n" +
                "\tDestinatario: " + destinatario.getNombre() + " " + destinatario.getApellido() + "\n" +
                "\tDirección: " + destinatario.getDireccion() + "\n" +
                "\tLocalidad: " + destinatario.getLocalidad() + "\n" +
                "\tProvincia: " + destinatario.getProvincia() + "\n" +
                "\tTeléfono: " + destinatario.getMovil() + "\n" +
                "***********************************************************\n";

    }

    public String pintaEnvioConductor() {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaEntregaAprox = null;
        if (fechaEnvio != null) fechaEntregaAprox = calculaFechaEntregaAprox();

        return "*** Num. Seguimiento " + numSeguimiento + " ***\n" +
                "\tEstado: " + estado + "\n" +
                "\tDestinatario: " + destinatario.getNombre() + " " + destinatario.getApellido() + "\n" +
                "\tDirección: " + destinatario.getDireccion() + "\n" +
                "\tLocalidad: " + destinatario.getLocalidad() + "\n" +
                "\tProvincia: " + destinatario.getProvincia() + "\n" +
                "\tTeléfono: " + destinatario.getMovil() + "\n" +
                "\tFecha de creación: " + fechaCreacion.format(formatoFecha) + "\n" +
                "\tFecha de envío: " + ((fechaEnvio == null) ? "-" : fechaEnvio.format(formatoFecha)) + "\n" +
                "\tFecha de entrega aprox: " + ((fechaEnvio == null) ? "-" : fechaEntregaAprox.format(formatoFecha)) + "\n" +
                "***********************************************************\n";

    }

    public String pintaEnvioConductorSimple() {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

        LocalDate fechaEntregaAprox = null;
        if (fechaEnvio != null) fechaEntregaAprox = calculaFechaEntregaAprox();

        LocalDate fechaEntrega = null;
        LocalTime horaEntrega = null;
        if (fechaHoraEntrega != null) {
            fechaEntrega = fechaHoraEntrega.toLocalDate();
            horaEntrega = fechaHoraEntrega.toLocalTime();
        }

        return "***********************************************************\n" +
                "*" +numSeguimiento + " // " + estado + "\n" +
                "Fecha envío: " + ((fechaEnvio == null) ? "-" : fechaEnvio.format(formatoFecha)) + "\n" +
                "Fecha entrega aprox.: " + ((fechaEnvio == null) ? "-" : fechaEntregaAprox.format(formatoFecha)) + "\n" +
                "Fecha y hora entrega: " + ((estaEntregado()) ?
                (fechaEntrega.format(formatoFecha) + " - " + horaEntrega.format(formatoHora)) : "-") + "\n" +
                "***********************************************************\n";
    }

    public String pintaEnvioEntregado() {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

        LocalDate fechaEntregaAprox = null;
        if (fechaEnvio != null) fechaEntregaAprox = calculaFechaEntregaAprox();

        LocalDate fechaEntrega = null;
        LocalTime horaEntrega = null;
        if (fechaHoraEntrega != null) {
            fechaEntrega = fechaHoraEntrega.toLocalDate();
            horaEntrega = fechaHoraEntrega.toLocalTime();
        }

        return "***********************************************************\n" +
                "*** Num. seguimiento " + numSeguimiento + " ***\n" +
                "Destinatario: " + destinatario.getNombre() + " " + destinatario.getApellido() + "\n" +
                "Dirección: " + destinatario.getDireccion() + "\n" +
                "Localidad: " + destinatario.getLocalidad() + "\n" +
                "Provincia: " + destinatario.getProvincia() + "\n" +
                "Teléfono: " + destinatario.getMovil() + "\n" +
                "Fecha de creación: " + fechaCreacion.format(formatoFecha) + "\n" +
                "Fecha de envío: " + ((fechaEnvio == null) ? "-" : fechaEnvio.format(formatoFecha)) + "\n" +
                "Fecha de entrega aprox.: " + ((fechaEnvio == null) ? "-" : fechaEntregaAprox.format(formatoFecha)) + "\n" +
                "Fecha y hora de entrega: " + ((estaEntregado()) ?
                (fechaEntrega.format(formatoFecha) + " - " + horaEntrega.format(formatoHora)) : "-") + "\n" +
                "***********************************************************\n";
    }
}
