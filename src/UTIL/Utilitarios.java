package UTIL;

import jdk.jshell.execution.Util;

import java.util.Objects;
import java.util.Scanner;

public class Utilitarios {

    static Scanner sc = new Scanner(System.in);

    public static int capturarEntero(String mensaje){
        System.out.println(mensaje);
        int valor = sc.nextInt();
        sc.nextLine();
        return valor;
    }


    public static String capturarString(String mensaje){
        System.out.println(mensaje);
        return sc.nextLine();
    }

    public static double capturarDouble(String mensaje){
        System.out.println(mensaje);
        return sc.nextDouble();
    }

    public static void mostrarmensaje(String mensaje){
        System.out.println(mensaje);
    }

    public static boolean validarCampoVacioString(String dato){
        if(dato.equals("")){
            return false;
        }
        return true;
    }

    public static boolean validarCampoDouble(Double dato){
        return dato > 0;
    }

    public static boolean validarCampoEntero(int dato){
        return dato >= 0;
    }


}
