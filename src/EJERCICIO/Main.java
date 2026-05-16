package EJERCICIO;

import UTIL.Utilitarios;

public class Main {

    public static void main(String[] args) {
        Tienda tienda = new Tienda();
        int opcion;

        do {
            Utilitarios.mostrarmensaje("\nTienda:");
            Utilitarios.mostrarmensaje("1.- Registrar producto");
            Utilitarios.mostrarmensaje("2.- Buscar producto por código");
            Utilitarios.mostrarmensaje("3.- Almacenar producto en archivo");
            Utilitarios.mostrarmensaje("4.- Recuperar producto del archivo");
            Utilitarios.mostrarmensaje("5.- Total de stock");
            Utilitarios.mostrarmensaje("6.- Vender producto por código");
            Utilitarios.mostrarmensaje("7.- Listar productos");
            Utilitarios.mostrarmensaje("0.- Salir \n");

            opcion = Utilitarios.capturarEntero("Ingrese la opción del Menu: ");


            switch (opcion) {
                case 1:
                    tienda.registrarProducto();
                    break;
                case 2:
                    tienda.buscarProducto();
                    break;
                case 3:
                    tienda.guardarReporteProductos();
                    break;
                case 4:
                    tienda.cargarArchivoBinario();
                    break;
                case 5:
                    tienda.CalcularTotalStock();
                    break;
                case 6:
                    tienda.venderProducto();
                    break;
                case 7:
                    tienda.mostrarProductosDisponibles();
                    break;
                default:
                    Utilitarios.mostrarmensaje("Opcion invalida");
                    break;
            }
        } while (opcion != 0);
    }
}

