package EJERCICIO;

import UTIL.Utilitarios;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class Tienda {

    private ArrayList<Producto> lproductos;

    public Tienda() {
        lproductos = new ArrayList<>();
    }

    public void registrarProducto() {
        Utilitarios.mostrarmensaje("Registro del producto:");
        Producto producto = new Producto();

        String codigoProducto = Utilitarios.capturarString("Ingrese el codigo del producto: ");

        int indexprod = verificarCodigoProducto(codigoProducto);
        if (indexprod == -1) {
            producto.setCodigo(codigoProducto);
        } else {
            Utilitarios.mostrarmensaje("Producto ya se encuentra registrado con el codigo: " + codigoProducto);
            return;
        }

        String nombreProducto = Utilitarios.capturarString("Ingrese el nombre del producto: ");
        if (Utilitarios.validarCampoVacioString(nombreProducto)) {
            producto.setNombre(nombreProducto);
        } else {
            Utilitarios.mostrarmensaje("Nombre del producto no puede estar vacio: ");
            return;
        }

        double precioProducto = Utilitarios.capturarDouble("Ingrese el precio del producto: ");
        if(Utilitarios.validarCampoDouble(precioProducto)){
            producto.setPrecio(precioProducto);
        }else{
            Utilitarios.mostrarmensaje("El valor del producto no puede ser cero: ");
            return;
        }

        int cantidadProducto = Utilitarios.capturarEntero("Ingrese el stock del producto: ");
        if (Utilitarios.validarCampoEntero(cantidadProducto)) {
            producto.setCantidad(cantidadProducto);
        }else{
            Utilitarios.mostrarmensaje("La cantidad del producto no puede ser menor a cero: ");
            return;
        }

        producto.setCategoria(Utilitarios.capturarString("Ingrese la categoria del producto: "));
        Utilitarios.mostrarmensaje("");
        lproductos.add(producto);

        if(lproductos.size()>1){
            ordenarListaNombrePrecioStock();
        }
    }


    public void buscarProducto() {
        if (lproductos.isEmpty()) {
            Utilitarios.mostrarmensaje("No hay productos registrados:");
        } else {
            Utilitarios.mostrarmensaje("Buscar producto:");
            String codigo = Utilitarios.capturarString("Ingresar el codigo del producto: ");

            int indexprod = verificarCodigoProducto(codigo);
            if (indexprod == -1) {
                Utilitarios.mostrarmensaje("No se encontro el producto con el codigo: " + codigo);
            } else {
                Utilitarios.mostrarmensaje("Codigo" + "\t" + "Nombre" + "\t" + "Cantidad" + "\t" + "Precio" + "\t" + "Categoria");
                Utilitarios.mostrarmensaje("Producto: " + lproductos.get(indexprod).toString());
            }

        }
    }


    public void venderProducto() {
        if (lproductos.isEmpty()) {
            Utilitarios.mostrarmensaje("No hay productos registrados:");
            return;
        }
        mostrarProductosDisponibles();

        String codigo = Utilitarios.capturarString("Ingresar el codigo del producto: ");

        int indexprod = verificarCodigoProducto(codigo);

        if (indexprod == -1) {
            Utilitarios.mostrarmensaje("No se encontro el producto con el codigo: " + codigo);
            return;
        }

        if(!verificarStock(indexprod)){
            Utilitarios.mostrarmensaje("El producto sin stock disponible");
            return;
        }

        int cantidad = Utilitarios.capturarEntero("Ingresar la cantidad: ");

        if (!Utilitarios.validarCampoEntero(cantidad)) {
            Utilitarios.mostrarmensaje("Cantidad ingresada no es correcta");
            return;
        }

        int cantidadactual = lproductos.get(indexprod).getCantidad();

        if (cantidad > cantidadactual) {
            Utilitarios.mostrarmensaje("No hay suficiente stock. Disponible: " + cantidadactual);
            return;
        }

        lproductos.get(indexprod).setCantidad(cantidadactual - cantidad);
        Utilitarios.mostrarmensaje("Venta realizada correctamente");
    }

    public void mostrarProductosDisponibles(){
        if (lproductos.isEmpty()) {
            Utilitarios.mostrarmensaje("No hay productos registrados:");
        } else {
            Utilitarios.mostrarmensaje("Lista de productos:");
            Utilitarios.mostrarmensaje("Codigo" + "\t" + "Nombre" + "\t" + "Cantidad" + "\t" + "Precio" + "\t" + "Categoria");
            for (Producto producto : lproductos){
                Utilitarios.mostrarmensaje(producto.toString());
            }
        }
    }

    //VERIFICA LA CANTIDAD DISPONIBLE SEA MAYOR A CERO
    public boolean verificarStock(int indexproducto){
        return lproductos.get(indexproducto).getCantidad()> 0;
    }

    //VERIFCA PRODUCTO EN LA LISTA PRINCIPAL -1 SI NO EXISTE
    public int verificarCodigoProducto(String codigo) {
        for (int i = 0; i < lproductos.size(); i++) {
            Producto producto = lproductos.get(i);
            if (Objects.equals(producto.getCodigo(), codigo)) {
                return i;
            }
        }
        return -1;
    }

    public void ordenarListaNombrePrecioStock(){
        Collections.sort(lproductos,
                Comparator.comparing(Producto::getNombre)
                        .thenComparing(Producto::getPrecio)
                        .thenComparing(Producto::getCantidad)
        );
    }

    public void CalcularTotalStock(){
        int cantidadStock = 0;
        for (int i = 0; i < lproductos.size(); i++) {
            cantidadStock += lproductos.get(i).getCantidad();
        }
        Utilitarios.mostrarmensaje("Total stock: " + cantidadStock);
    }


    public void guardarReporteProductos(){
        if (lproductos.isEmpty()) {
            Utilitarios.mostrarmensaje("No hay productos registrados:");
        } else {
            Utilitarios.mostrarmensaje("Guardar Archivo: ");

            try{
                FileWriter archivo = new FileWriter("productos.txt");
                PrintWriter escritor = new PrintWriter(archivo);

                escritor.print("*****REPORTE DE PRODUCTOS*****");
                escritor.println("----------------------------------------------------------------------------------");
                escritor.println("Codigo" + "\t" + "Nombre" + "\t" + "Cantidad" + "\t" + "Precio" + "\t" + "Categoria");

                for(Producto producto: lproductos){
                    escritor.println(producto.toString());
                }

                escritor.close();
                Utilitarios.mostrarmensaje("Archivo guardado correctamente: ");
            }catch (Exception e){
                Utilitarios.mostrarmensaje("Error durante el almacenamiento del archivo: " + e.getMessage());
            }
        }
    }


    public void cargarArchivoBinario(){

        ArrayList<Producto> prueba = new ArrayList<>();
        try{
            FileInputStream archivo = new FileInputStream("productos.txt");
            ObjectInputStream entrada = new ObjectInputStream(archivo);

            prueba = (ArrayList<Producto>) entrada.readObject();

            entrada.close();
            Utilitarios.mostrarmensaje("Archivo cargado correctamente: ");

            for (Producto producto: prueba){
                Utilitarios.mostrarmensaje("Producto: " + producto.toString());
            }

        } catch (FileNotFoundException e) {
            Utilitarios.mostrarmensaje("Error durante la ejecucion: " + e.getMessage());
        } catch (IOException e) {
            Utilitarios.mostrarmensaje("Error durante la ejecucion: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            Utilitarios.mostrarmensaje("Error durante la ejecucion: " + e.getMessage());
        }
    }

}
























