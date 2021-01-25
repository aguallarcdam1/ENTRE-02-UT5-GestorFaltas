/**
 * Punto de entrada a la aplicación
 */
public class TestGestorFaltas {
    /**
     * Se acepta como argumento del main() el nº máximo de estudiantes
     * y una vez creado el gestor de faltas se muestra la información solicitada
     * (ver enunciado)
     */
    public static void main(String[] args) {
        if(args.length == 1){
            int n = Integer.parseInt(args[0]);
            GestorFaltas gf = new GestorFaltas(n);

            gf.leerDeFichero();

            gf.justificarFaltas("IRISO FLAMARIQUE", 6);

            gf.ordenar();
            System.out.println("Se muestra el curso ordenado por faltas injustificadas\n");
            System.out.println(gf.toString());
            gf.anularMatricula();
            System.out.println("Después de anular matrícula\n");
            System.out.println(gf.toString());
        }
        else{
            System.out.println("Error en argumentos");
            System.out.println("Sintaxis: java TestGestorFaltas <max_estudiantes>");
        }
    }

}
