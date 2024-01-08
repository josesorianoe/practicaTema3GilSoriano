package utils;

import java.util.Scanner;

public class Utils {
    public static void pulsaParaContinuar(){
        var s = new Scanner(System.in);
        System.out.print("Pulse una tecla para continuar...");
        s.nextLine();
    }

    public static void limpiarPantalla(){
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    public static void animacion(String texto){
        System.out.print(texto);
        for (int i = 0; i < 4; i++) { //Animación
            System.out.print(".");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        System.out.println();
    }

    public static float redondear(float num, int nDecimales){
        return (float) (Math.round(num * (Math.pow(10, nDecimales))) / (Math.pow(10, nDecimales)));
    }
}
