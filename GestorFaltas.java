import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
/**
 * Un objeto de esta clase permite registrar estudiantes de un
 * curso (leyendo la informaci�n de un fichero de texto) y 
 * emitir listados con las faltas de los estudiantes, justificar faltas, 
 * anular matr�cula dependiendo del n� de faltas, .....
 * @author Andr�s Guallar Chamorro
 */
public class GestorFaltas {
    private Estudiante[] estudiantes; 
    private int total;
    public GestorFaltas(int n) {
        this.estudiantes = new Estudiante[n];
        this.total = 0;
    }

    /**
     * Devuelve true si el array de estudiantes est� completo,
     * false en otro caso
     */
    public boolean cursoCompleto() {
        return total == estudiantes.length;
    }

    /**
     *    A�ade un nuevo estudiante solo si el curso no est� completo y no existe ya otro
     *    estudiante igual (con los mismos apellidos). 
     *    Si no se puede a�adir se muestra los mensajes adecuados 
     *    (diferentes en cada caso)
     *    
     *    El estudiante se a�ade de tal forma que queda insertado en orden alfab�tico de apellidos
     *    (de menor a mayor)
     *    !!OJO!! No hay que ordenar ni utilizar ning�n algoritmo de ordenaci�n
     *    Hay que insertar en orden 
     *    
     */
    public void addEstudiante(Estudiante nuevo) {
        if(!cursoCompleto() && buscarEstudiante(nuevo.getApellidos()) == -1) {
            int i = total - 1;

            while (i >= 0 && (estudiantes[i].getApellidos()).compareTo(nuevo.getApellidos()) > 0) {
                estudiantes[i + 1] = estudiantes[i];
                i -- ;
            }
            estudiantes[i + 1] = nuevo; 
            total ++; 
        }

    }

    /**
     * buscar un estudiante por sus apellidos
     * Si est� se devuelve la posici�n, si no est� se devuelve -1
     * Es indiferente may�sculas / min�sculas
     * Puesto que el curso est� ordenado por apellido haremos la b�squeda m�s
     * eficiente
     *  
     */
    public int buscarEstudiante(String apellidos) {
        String[] ape = new String[total];
        for(int i = 0; i < total; i++){
            ape[i] = estudiantes[i].getApellidos();
        }

        int posicion = Arrays.binarySearch(ape, apellidos);

        if(posicion >= 0){
            return posicion;
        }
        else{
            return -1;
        }

    }

    /**
     * Representaci�n textual del curso
     * Utiliza StringBuilder como clase de apoyo.
     *  
     */
    public String toString() {

        String totalEs = "Relaci�n de estudiantes(" + total + ")";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < this.total; i++){
            sb = sb.append("\n").append(estudiantes[i].toString()).append("\n------------------\n");
        }
        return totalEs + "\n" + sb;

    }

    /**
     *  Se justifican las faltas del estudiante cuyos apellidos se proporcionan
     *  El m�todo muestra un mensaje indicando a qui�n se ha justificado las faltas
     *  y cu�ntas
     *  
     *  Se asume todo correcto (el estudiante existe y el n� de faltas a
     *  justificar tambi�n)
     */
    public void justificarFaltas(String apellidos, int faltas) {
        Estudiante justificado = estudiantes[buscarEstudiante(apellidos)];
        justificado.justificar(faltas);
        System.out.println("Justificadas " + faltas + " a " 
            + justificado.getApellidos() + "," + justificado.getNombre() + "\n") ;
    }

    /**
     * ordenar los estudiantes de mayor a menor n� de faltas injustificadas
     * si coinciden se tiene en cuenta las justificadas
     * M�todo de selecci�n directa
     */
    public void ordenar() {
        for (int i = 0; i < total; i++) {
            int posmin = i;
            for (int j = i + 1; j < total; j++) {
                if(estudiantes[j].getFaltasNoJustificadas() != estudiantes[posmin].getFaltasNoJustificadas()){
                    if (estudiantes[j].getFaltasNoJustificadas() > estudiantes[posmin].getFaltasNoJustificadas()) {
                        posmin = j;
                    }
                }
                else{
                    if (estudiantes[j].getFaltasJustificadas() > estudiantes[posmin].getFaltasJustificadas()) {
                        posmin = j;
                    }
                }
            }
            Estudiante aux = estudiantes[posmin];
            estudiantes[posmin] = estudiantes[i];
            estudiantes[i] = aux;
        }
    }

    /**
     * anular la matr�cula (dar de baja) a 
     * aquellos estudiantes con 30 o m�s faltas injustificadas
     */
    public void anularMatricula() {
        for(int i = total - 1; i >= 0; i--){
            if(estudiantes[i].getFaltasNoJustificadas() >= 30){
                System.arraycopy(estudiantes, i + 1, estudiantes, i, total - i - 1);
                total--;
            }
        }
    }

    /**
     * Lee de un fichero de texto los datos de los estudiantes
     *   con ayuda de un objeto de la  clase Scanner
     *   y los guarda en el array. 
     */
    public void leerDeFichero() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("estudiantes.txt"));
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                Estudiante estudiante = new Estudiante(linea);
                this.addEstudiante(estudiante);
            }
        }
        catch (IOException e) {
            System.out.println("Error al leer del fichero");
        }
        finally {
            if (sc != null) {
                sc.close();
            }
        }

    }

}
