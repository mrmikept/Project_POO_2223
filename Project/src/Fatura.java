import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Fatura
{
    private int idEncomenda;
    private int tipo;
    private double valorArtigos;
    private double valorTaxas;
    private double valorExpedicao;
    private double valorTotal;
    private LocalDate dataFaturacao;

    public Fatura()
    {
        this.idEncomenda = 0;
        this.tipo = Atributos.VENDA;
        this.valorArtigos = 0;
        this.valorExpedicao = 0;
        this.valorTaxas = 0;
        this.valorTotal = 0;
        this.dataFaturacao = LocalDate.now();
    }

    public Fatura(int idEncomenda, int tipo, double valorArtigos, double valorTaxas, double valorExpedicao, LocalDate dataFaturacao)
    {
        this.idEncomenda = idEncomenda;
        this.tipo = tipo;
        this.valorArtigos = valorArtigos;
        this.valorTaxas = valorTaxas;
        this.valorExpedicao = valorExpedicao;
        this.valorTotal = (double) Math.round((valorArtigos + valorTaxas + valorExpedicao) * 100) / 100;
        this.dataFaturacao = dataFaturacao;
    }

    public Fatura(Fatura fatura)
    {
        this.idEncomenda = fatura.getIdEncomenda();
        this.tipo = fatura.getTipo();
        this.valorArtigos = fatura.getValorArtigos();
        this.valorTaxas = fatura.getValorTaxas();
        this.valorExpedicao = fatura.getValorExpedicao();
        this.valorTotal = fatura.getValorTotal();
        this.dataFaturacao = fatura.getDataFaturacao();
    }

    public int getIdEncomenda() {
        return idEncomenda;
    }

    public void setIdEncomenda(int idEncomenda) {
        this.idEncomenda = idEncomenda;
    }

    public int getTipo()
    {
        return this.tipo;
    }

    public void setTipo(int tipo)
    {
        this.tipo = tipo;
    }

    public double getValorArtigos() {
        return valorArtigos;
    }

    public void setValorArtigos(double valorArtigos) {
        this.valorArtigos = valorArtigos;
    }

    public double getValorTaxas() {
        return valorTaxas;
    }

    public void setValorTaxas(double valorTaxas) {
        this.valorTaxas = valorTaxas;
    }

    public double getValorExpedicao() {
        return valorExpedicao;
    }

    public void setValorExpedicao(double valorExpedicao) {
        this.valorExpedicao = valorExpedicao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getDataFaturacao() {
        return dataFaturacao;
    }

    public void setDataFaturacao(LocalDate dataFaturacao) {
        this.dataFaturacao = dataFaturacao;
    }

    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append("[Fatura]\n");
        string.append("Fatura referente à encomenda: " + this.getIdEncomenda() + "\n");
        string.append("Tipo: " + this.getTipo() + "\n");
        string.append("Valor dos Artigos: " + this.getValorArtigos() + "\n");
        string.append("Valor Taxas sobre Artigos Novos e Usados: " + this.getValorTaxas() + "\n");
        string.append("Valor Taxa de expedição: " + this.getValorExpedicao() + "\n");
        string.append("Valor total: " + this.getValorTotal() + "\n");
        string.append("Data de Faturacao: " + this.getDataFaturacao() + "\n");
        return string.toString();
    }

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
        Fatura fatura = (Fatura) o;
        return (this.getIdEncomenda() == fatura.getIdEncomenda() &&
                this.getTipo() == fatura.getTipo() &&
                this.getValorArtigos() == fatura.getValorArtigos() &&
                this.getValorTaxas() == fatura.getValorTaxas() &&
                this.getValorExpedicao() == fatura.getValorTaxas() &&
                this.getValorTotal() == fatura.getValorTotal() &&
                this.getDataFaturacao().equals(fatura.getDataFaturacao()));
    }

    public Fatura clone()
    {
        return new Fatura(this);
    }
}
