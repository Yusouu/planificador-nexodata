package planificador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LectorProcesos {

    public static List<Proceso>leerProcesos(String rutaFichero) {
        List<Proceso>listaProcesos =new ArrayList<>();

        try {
            File archivo=new File(rutaFichero);
            Scanner lector=new Scanner(archivo);
            int numerolinea=0;

            while (lector.hasNextLine()) {
                String linea = lector.nextLine().trim();
                numerolinea++;

                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }

                String[]partes=linea.split(";");

                if (partes.length != 3) {
                    System.err.println("Error en la línea " + numerolinea + "del fichero"+rutaFichero+":Formato incorrecto.Se esperaban 3 campos (nombre;llegada;ráfaga).");
                    System.exit(1);
                }

                try {
                    String nombre=partes[0].trim();
                    int llegada=Integer.parseInt(partes[1].trim());
                    int rafaga=Integer.parseInt(partes[2].trim());

                    if (llegada < 0) {
                        System.err.println("Error en la línea " + numerolinea + ": El instante de llegada no puede ser negativo (" + llegada + ").");
                        System.exit(1);
                    }

                    if (rafaga <= 0) {
                        System.err.println("Error en la línea " + numerolinea + ": La ráfaga de CPU debe ser mayor que cero (" + rafaga + ").");
                        System.exit(1);
                    }

                    Proceso proceso = new Proceso(nombre, llegada, rafaga);
                    listaProcesos.add(proceso);

                } catch (NumberFormatException e) {
                    System.err.println("Error en la línea"+ numerolinea +": Los valores de llegada y ráfaga deben ser números enteros válidos.");
                    System.exit(1);
                }
            }
            lector.close();

        } catch (FileNotFoundException e) {
            System.err.println("Error:No se ha podido encontrar el fichero en la ruta especificada:"+rutaFichero);
            System.exit(1);
        }

        return listaProcesos;
    }
}