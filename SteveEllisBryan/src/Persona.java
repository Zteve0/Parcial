import java.io.*;
import java.util.Scanner;

public class Persona {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] diaSem = {"lunes", "martes", "miercoles", "jueves", "viernes", "sabado", "domingo"};

        String[][] data = new String[7][100];
        double[] maxValorPorDia = new double[7];

        // Leer datos de archivos y calcular los máximos por día
        for (int i = 0; i < 7; i++) {
            String fileName = "C:\\Users\\Steve Ellis\\Desktop\\Archivos txt\\" + diaSem[i] + ".txt";
            double maxDiaValor = 0.0;

            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                int a = 0;
                while ((line = br.readLine()) != null) {
                    data[i][a] = line;
                    String[] parts = line.split(";");
                    if (parts.length >= 5) {
                        double valor = Double.parseDouble(parts[1]);
                        if (valor > maxDiaValor) {
                            maxDiaValor = valor;
                        }
                    }
                    a++;
                }
                maxValorPorDia[i] = maxDiaValor;
            } catch (Exception e) {
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
                    findMaxColumn2Value(data);
                    break;
                case 3:
                    System.out.print("Ingrese un ID para buscar información: ");
                    String idToFind = scanner.nextLine();
                    // Implementa la lógica para encontrar información por ID
                    break;
                case 4:
                    System.out.println("Datos disponibles:");
                    for (int i = 0; i < 7; i++) {
                        System.out.println(diaSem[i] + ": ");
                        for (int a = 0; a < data[i].length; a++) {
                            if (data[i][a] != null) {
                                System.out.println(data[i][a]);
                            }
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

    private static void findMaxColumn2Value(String[][] data) {
        double maxColumna2Valor = 0.0;

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != null) {
                    String[] parts = data[i][j].split(";");
                    if (parts.length >= 5) {
                        try {
                            double columna2Valor = Double.parseDouble(parts[4]);
                            if (columna2Valor > maxColumna2Valor) {
                                maxColumna2Valor = columna2Valor;
                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }
        }

        System.out.println("La hora es: " + maxColumna2Valor);
    }
}
