/**
 * Descrição classe
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public class Transportadora {
    private double margemLucro;
    private int tipo;

    public static final int NORMAL = 0;
    public static final int PREMIUM = 1;

    public Transportadora()
    {
        this.margemLucro = 0.0;
        this.tipo = NORMAL;
    }

    public Transportadora(double lucro, int tipo)
    {
        this.margemLucro = lucro;
        this.tipo = tipo;
    }

    public Transportadora(Transportadora transportadora)
    {
        this.margemLucro = transportadora.getMargemLucro();
        this.tipo = transportadora.getTipo();
    }

    public double getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Transportadora clone()
    {
        return new Transportadora(this);
    }
}
