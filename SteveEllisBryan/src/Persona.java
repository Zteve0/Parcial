import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

class Persona {
    private String nombre;
    private String identificacion;

    public Persona(String nombre, String identificacion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }
}

class PersonaNatural extends Persona {
    private String fechaNacimiento;

    public PersonaNatural(String nombre, String identificacion, String fechaNacimiento) {
        super(nombre, identificacion);
        this.fechaNacimiento = fechaNacimiento;
    }
}

class PersonaJuridica extends Persona {
    private String razonSocial;

    public PersonaJuridica(String nombre, String identificacion, String razonSocial) {
        super(nombre, identificacion);
        this.razonSocial = razonSocial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }
}

class ONG extends Persona {
    private String nombreONG;

    public ONG(String nombre, String identificacion, String nombreONG) {
        super(nombre, identificacion);
        this.nombreONG = nombreONG;
    }

    public String getNombreONG() {
        return nombreONG;
    }
}

class Transaccion {
    private int idTransaccion;
    private double monto;
    private String tipoClienteEmisor;
    private String tipoClienteReceptor;
    private int horaDelDia;
    private String ciudadBase;

    public Transaccion(int idTransaccion, double monto, String tipoClienteEmisor, String tipoClienteReceptor, int horaDelDia, String ciudadBase) {
        this.idTransaccion = idTransaccion;
        this.monto = monto;
        this.tipoClienteEmisor = tipoClienteEmisor;
        this.tipoClienteReceptor = tipoClienteReceptor;
        this.horaDelDia = horaDelDia;
        this.ciudadBase = ciudadBase;
    }
}

public class BilleteraDigital {
    private HashMap<String, Persona> personas = new HashMap<String, Persona>();

    public void agregarPersona(String identificacion, Persona persona) {
        personas.put(identificacion, persona);
    }

    public void realizarRecargo(String emisor, String receptor, double monto) {
        try {
            Persona emisorPersona = personas.get(emisor);
            Persona receptorPersona = personas.get(receptor);

            if (emisorPersona instanceof PersonaJuridica) {
                monto *= 2.8;
            } else if (emisorPersona instanceof ONG) {
                monto *= 2.0;
            }

            // Realizar la operación de recarga aquí

        } catch (NullPointerException e) {
            System.out.println("No se pudo realizar la recarga. Verifique las identificaciones.");
        }
    }

    public void realizarTransaccion(int idTransaccion, double monto, String tipoClienteEmisor, String tipoClienteReceptor, int horaDelDia, String ciudadBase, String fecha) {
        Transaccion transaccion = new Transaccion(idTransaccion, monto, tipoClienteEmisor, tipoClienteReceptor, horaDelDia, ciudadBase);

        // Guardar los datos de la transacción en un archivo .txt correspondiente al día
        try {
            String fileName = fecha + ".txt";
            FileWriter fileWriter = new FileWriter(fileName, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("ID=" + idTransaccion + ", Monto=" + monto + ", Emisor=" + tipoClienteEmisor + ", Receptor=" + tipoClienteReceptor + ", Hora=" + horaDelDia + ", Ciudad=" + ciudadBase);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BilleteraDigital billetera = new BilleteraDigital();

        PersonaNatural persona1 = new PersonaNatural("Juan", "123456789", "01/01/1990");
        PersonaJuridica persona2 = new PersonaJuridica("EmpresaX", "987654321", "RazonSocialX");
        ONG ong1 = new ONG("ONGY", "555555555", "NombreONGY");

        billetera.agregarPersona(persona1.getIdentificacion(), persona1);
        billetera.agregarPersona(persona2.getIdentificacion(), persona2);
        billetera.agregarPersona(ong1.getIdentificacion(), ong1);

        // Realizar transacciones diarias
        for (int i = 1; i <= 7; i++) {
            String fecha = "2023-11-0" + i; // Cambia la fecha según corresponda
            billetera.realizarTransaccion(i, 100000, "PersonaJuridica", "ONG", 12, "CiudadX", fecha);
        }

        // Realizar recargos
        billetera.realizarRecargo(persona2.getIdentificacion(), ong1.getIdentificacion(), 100000);
    }
}