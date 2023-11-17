import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Persona {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] diaSem = {"lunes", "martes", "miercoles", "jueves", "viernes", "sabado", "domingo"};

        ArrayList<String[]> dataList = new ArrayList<>();
        double[] maxValorPorDia = new double[7];

        // Leer datos de archivos y calcular los máximos por día
        for (int i = 0; i < 7; i++) {
            String fileName = "C:\\Users\\Steve Ellis\\Final Logica\\SteveEllisBryan\\" + diaSem[i] + ".txt";
            double maxDiaValor = 0.0;

            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                int a = 0;
                ArrayList<String> dayData = new ArrayList<>();

                while ((line = br.readLine()) != null) {
                    dayData.add(line);
                    String[] parts = line.split(";");
                    if (parts.length >= 5) {
                        double valor = Double.parseDouble(parts[1]);
                        if (valor > maxDiaValor) {
                            maxDiaValor = valor;
                        }
                    }
                    a++;
                }
                dataList.add(dayData.toArray(new String[0]));
                maxValorPorDia[i] = maxDiaValor;
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error al leer el archivo de " + diaSem[i] + ": " + e.getMessage());
            }
        }

        while (true) {
            System.out.println("Bienvenido, ¿qué deseas hacer?");
            System.out.println("1. Día de la semana en que más se movió el dinero");
            System.out.println("2. Hora del día en que más se movió el dinero");
            System.out.println("3. Encontrar información por el ID");
            System.out.println("4. Para ver datos");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    double maxValor = 0.0;
                    String diaMaxValor = "";

                    for (int i = 0; i < 7; i++) {
                        if (maxValorPorDia[i] > maxValor) {
                            maxValor = maxValorPorDia[i];
                            diaMaxValor = diaSem[i];
                        }
                    }

                    if (!diaMaxValor.isEmpty()) {
                        System.out.println("El día de la semana con el mayor valor de transacción es " + diaMaxValor);
                        System.out.println("El valor máximo es: " + maxValor);
                    }
                    break;
                case 2:
                    findMaxColumn2Value(dataList);
                    break;
                case 3:
                System.out.print("Ingrese un ID para buscar información: ");
                String idToFind = scanner.nextLine();
            
                boolean found = false;
                for (String[] dayData : dataList) {
                    for (String entry : dayData) {
                        String[] parts = entry.split(";");
                        if (parts.length >= 1 && parts[0].equals(idToFind)) {
                            // Se encontró la información con el ID proporcionado
                            System.out.println("Información encontrada:");
                            System.out.println(entry);
                            found = true;
                            break;  // Sale del bucle interno si encuentra el ID
                        }
                    }
                    if (found) {
                        break;  // Sale del bucle externo si encuentra el ID
                    }
                }
            
                if (!found) {
                    System.out.println("No se encontró información con el ID proporcionado.");
                }
                    break;
                case 4:
                    System.out.println("Datos disponibles:");
                    for (int i = 0; i < 7; i++) {
                        System.out.println(diaSem[i] + ": ");
                        for (String entry : dataList.get(i)) {
                            System.out.println(entry);
                        }
                    }
                    break;
                case 5:
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }

    private static void findMaxColumn2Value(ArrayList<String[]> dataList) {
        double maxColumna2Valor = 0.0;

        for (String[] dayData : dataList) {
            for (String entry : dayData) {
                String[] parts = entry.split(";");
                if (parts.length >= 5) {
                    try {
                        double columna2Valor = Double.parseDouble(parts[4]);
                        if (columna2Valor > maxColumna2Valor) {
                            maxColumna2Valor = columna2Valor;
                        }
                    } catch (NumberFormatException ignored) {
                    }
                }
            }
        }

        System.out.println("La hora es: " + maxColumna2Valor);
    }
}
