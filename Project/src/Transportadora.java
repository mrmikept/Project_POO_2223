import java.io.Serializable;

/**
 * Classe que contem todos os métodos da transportadora Vintage
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */

public class Transportadora extends Entidade implements Serializable {
    private double margemLucro;
    private int tipo;
    private int tempoExpedicao;
    private double valorFaturado;
    private TaxasImpostos taxasImpostos;

    ///////////////
    //Contrutores//
    ///////////////

    public Transportadora()
    {
        super();
        this.margemLucro = 0.0;
        this.tipo = Atributos.NORMAL;
        this.tempoExpedicao = 0;
        this.valorFaturado = 0;
        this.taxasImpostos = new TaxasImpostos();
    }

    public Transportadora(int id, String email, String palavraPasse, String nome, String morada, int nrFiscal, double lucro, int tipo, int tempoExpedicao, double valorFaturado, TaxasImpostos taxasImpostos)
    {
        super(id,email,palavraPasse,nome,morada, nrFiscal);
        this.margemLucro = lucro;
        this.tipo = tipo;
        this.tempoExpedicao = tempoExpedicao;
        this.valorFaturado = valorFaturado;
        this.taxasImpostos = taxasImpostos.clone();
    }

    public Transportadora(Transportadora transportadora)
    {
        super(transportadora);
        this.margemLucro = transportadora.getMargemLucro();
        this.tipo = transportadora.getTipo();
        this.tempoExpedicao = transportadora.getTempoExpedicao();
        this.valorFaturado = transportadora.getValorFaturado();
        this.taxasImpostos = transportadora.getTaxasImpostos();
    }

    /////////////////////
    //Getters e Setters//
    ////////////////////

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

    public int getTempoExpedicao() {
        return tempoExpedicao;
    }

    public void setTempoExpedicao(int tempoExpedicao) {
        this.tempoExpedicao = tempoExpedicao;
    }

    public double getValorFaturado()
    {
        return this.valorFaturado;
    }

    public void setValorFaturado(double valorFaturado)
    {
        this.valorFaturado = valorFaturado;
    }

    public TaxasImpostos getTaxasImpostos() {
        return taxasImpostos;
    }

    public void setTaxasImpostos(TaxasImpostos taxasImpostos) {
        this.taxasImpostos = taxasImpostos;
    }

    /**
     * Função que devolve a taxa de expedição de uma encomenda
     * @param tamanhoEnc Tamanho da encomenda
     * @return Taxa de expedição
     */
    public double getTxExpedicao(int tamanhoEnc)
    {
        if (tamanhoEnc < 2)
        {
            return this.getTaxasImpostos().getTaxaEncPequena();
        }
        if (tamanhoEnc > 2 && tamanhoEnc < 6)
        {
            return this.getTaxasImpostos().getTaxaEncMedia();
        }
        return this.getTaxasImpostos().getTaxaEncGrande();
    }

    /**
     * Função que calcula a taxa de expedição de uma encomenda
     * @param tamanhoEnc Tamanho da encomenda
     * @return Taxa de expedição
     */
    public double calculaValorExpedicao(int tamanhoEnc)
    {
        if (this.getTipo() == Atributos.PREMIUM)
        {
            return 5 * (double) Math.round((this.getTxExpedicao(tamanhoEnc) * 2) + (this.getTxExpedicao(tamanhoEnc) * (this.getMargemLucro() * 1.5) * (1 + ((double)this.getTaxasImpostos().getImposto() / 100))) * 100) / 100;
        }
       return (double) Math.round((this.getTxExpedicao(tamanhoEnc) + (this.getTxExpedicao(tamanhoEnc) * this.getMargemLucro() * (1 + ((double)this.getTaxasImpostos().getImposto() / 100)))) * 100) / 100;
    }

    /**
     * Função que adiciona o valor ganho ao valor já faturado pela transportadora
     * @param valor Valor ganho
     */
    public void adicionaValorGanho(double valor)
    {
        this.valorFaturado += valor;
    }

    /**
     * Função que converte para string os dados de uma transportadora
     * @return String dados de uma transportadora
     */
    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append(Apresentacao.CYAN_BOLD +"                                                                                               [Transportadora]\n" + Apresentacao.RESET);
        string.append(Apresentacao.CYAN_BOLD +"                                                                                               Nome: " + Apresentacao.RESET + this.getNome() + "\n");
        string.append(Apresentacao.CYAN_BOLD +"                                                                                               Tipo: " + Apresentacao.RESET + this.getTipo() + "\n");
        string.append(Apresentacao.CYAN_BOLD +"                                                                                               Margem Lucro: " + Apresentacao.RESET + this.getMargemLucro() + "\n");
        string.append(Apresentacao.CYAN_BOLD +"                                                                                               Tempo de expedição: " + Apresentacao.RESET + this.getTempoExpedicao() + Apresentacao.CYAN_BOLD + " dias\n");
        string.append(Apresentacao.CYAN_BOLD +"                                                                                               Valor Faturado: " + Apresentacao.RESET + this.getValorFaturado() + "\n");
        return string.toString();
    }

    /**
     * Função que verifica se a transportadora é igual ao objeto
     * @param o Objecto
     * @return True se o objecto for igual à transportadora, caso contrário False
     */
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || this.getClass() != o.getClass())
        {
            return false;
        }
        Transportadora transportadora = (Transportadora) o;
        return (super.equals(transportadora) &&
                this.getTipo() == transportadora.getTipo() &&
                this.getMargemLucro() == transportadora.getMargemLucro()) &&
                this.getTempoExpedicao() == transportadora.getTempoExpedicao() &&
                this.getValorFaturado() == transportadora.getValorFaturado() &&
                this.getTaxasImpostos().equals(transportadora.getTaxasImpostos());
    }

    /**
     * Função que faz a cópia do objecto
     * @return Cópia do objecto
     */
    public Transportadora clone()
    {
        return new Transportadora(this);
    }
}
