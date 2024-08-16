package mx.unison;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EncontrarCodigo {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Por favor, proporcione al menos un codigo postal.");
            return;
        }

        String archivoCSV = "codigos_postales.csv";
        Map<String, List<String>> codigosPostalesMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String codigoPostal = datos[0].trim();
                String asentamiento = datos[1].trim();

                codigosPostalesMap.computeIfAbsent(codigoPostal, k -> new ArrayList<>()).add(asentamiento);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
            return;
        }

        for (String codigoPostal : args) {
            List<String> asentamientos = codigosPostalesMap.get(codigoPostal);
            if (asentamientos != null) {
                System.out.println("Código Postal: " + codigoPostal);
                for (String asentamiento : asentamientos) {
                    System.out.println("  - " + asentamiento);
                }
            } else {
                System.out.println("Código Postal: " + codigoPostal + " no encontrado.");
            }
        }
    }
}