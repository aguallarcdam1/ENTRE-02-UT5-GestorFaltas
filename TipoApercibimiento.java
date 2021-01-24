
/**
 * Enumeration class TipoApercibimiento - write a description of the enum class here
 * 
 * @author Andrés Guallar Chamorro
 * @version (version number or date here)
 */
public enum TipoApercibimiento
{
    DIEZ (10), 

    VEINTE (20), 

    TREINTA (30);

    private int valor;

    private TipoApercibimiento(int valor)
    {
        this.valor = valor;
    }

    public int getValor()
    {
        return valor;
    }
}
