import java.util.Arrays;

/**
 * 
 * Un objeto de esta clase guarda la información de un estudiante
 * Andrés Guallar Chamorro
 */
public class Estudiante {
    private final static String SEPARADOR = ",";
    private String nombre;
    private String apellidos;
    private int faltasNoJustificadas;
    private int faltasJustificadas;
    private TipoApercibimiento apercibimiento;

    /**
     *  
     *  Inicializa los atributos a partir de la información recibida
     *  Esta información se encuentra en lineaDatos
     *  (ver enunciado) 
     *  
     */
    public Estudiante(String lineaDatos) {
        this.apercibimiento = apercibimiento.DIEZ;

        String[] datos = lineaDatos.split(SEPARADOR);
        String nom = "";

        String FNJ = datos[2].trim();
        String FJ = datos[3].trim();

        this.nombre = "";
        nom = datos[0].trim();
        String[] nomAux = nom.split(" ");

        if(nomAux.length > 1){
            for(int i = 0; i < nomAux.length - 1; i++){
                if(!nomAux[i].equals("")){
                    String str = nomAux[i].substring(0, 1);
                    this.nombre += str.toUpperCase() + ".";
                }
            }
            String str2 = nomAux[nomAux.length - 1].substring(0,1);
            String str1 = nomAux[nomAux.length - 1].substring(1);
            this.nombre += str2.toUpperCase() + str1;
        }
        else{
            String str1 = nomAux[nomAux.length - 1].substring(0,1);
            String str2 = nomAux[nomAux.length - 1].substring(1);
            this.nombre += str1.toUpperCase() + str2;
        }

        this.apellidos = datos[1].trim();
        this.apellidos = this.apellidos.toUpperCase();

        this.faltasNoJustificadas = Integer.parseInt(FNJ);
        this.faltasJustificadas = Integer.parseInt(FJ);

    }

    /**
     * accesor para el nombre completo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * mutador para el nombre
     *  
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * accesor para los apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     *  mutador para los apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * accesor para el nº de faltas no justificadas
     */
    public int getFaltasNoJustificadas() {
        return faltasNoJustificadas;
    }

    /**
     * mutador para el nº de faltas no justificadas
     */
    public void setFaltasNoJustificadas(int faltasNoJustificadas) {
        this.faltasNoJustificadas = faltasNoJustificadas;
    }

    /**
     * accesor para el nº de faltas justificadas
     */
    public int getFaltasJustificadas() {
        return faltasJustificadas;
    }

    /**
     *  mutador para el nº de faltas justificadas
     */
    public void setFaltasJustificadas(int faltasJustificadas) {
        this.faltasJustificadas = faltasJustificadas;
    }

    /**
     *  se justifican n faltas que hasta el momento eran injustificadas
     *  Se asume n correcto
     */
    public void justificar(int n) {
        this.faltasNoJustificadas -= n;
        this.faltasJustificadas += n;
    }

    /**
     * Representación textual del estudiante
     * (ver enunciado)
     */
    public String toString() {
        String estudiante = "";
        String nomCompleto = "Apellidos y Nombre:\t" + apellidos + ", " + nombre + "\n";
        String FNJ = "Faltas No Justificadas:\t" + faltasNoJustificadas + "\n";
        String FJ = "Faltas Justificadas:\t" + faltasJustificadas + "\n";
        String aper = "Apercibimientos:\t";
        if(faltasNoJustificadas < 10){
            aper += "Sin apercibimientos";
        }
        else if(faltasNoJustificadas < 20){
            aper += apercibimiento.DIEZ;
        }
        else if(faltasNoJustificadas < 30){
            aper += apercibimiento.DIEZ + " " + apercibimiento.VEINTE;
        }
        else{
            aper += apercibimiento.DIEZ + " " 
                    + apercibimiento.VEINTE+ " " + apercibimiento.TREINTA;
        }
        estudiante += estudiante.format("%-25s", nomCompleto) + 
                        estudiante.format("%-25s", FNJ) + 
                        estudiante.format("%-25s", FJ) + 
                        estudiante.format("%-25s", aper) + "\n";
        return estudiante;

    }

    public static void main(String[] args) {
        Estudiante e1 = new Estudiante("  ander ibai  ,  Ruiz Sena , 12, 23");
        System.out.println(e1);
        System.out.println();
        Estudiante e2 = new Estudiante(
                " pedro josé   andrés  ,  Troya Baztarrica , 42, 6 ");
        System.out.println(e2);
        System.out.println();
        Estudiante e3 = new Estudiante("  Javier  ,  Suescun  Andreu , 39, 9 ");
        System.out.println(e3);
        System.out.println();
        Estudiante e4 = new Estudiante("julen, Duque Puyal, 5, 55");
        System.out.println(e4);
        System.out.println();

        System.out.println("---------------------------------");
        e1.justificar(3);
        System.out.println(e1);
        System.out.println();

        System.out.println("---------------------------------");

        e3.justificar(12);
        System.out.println(e3);
        System.out.println();

    }

}
