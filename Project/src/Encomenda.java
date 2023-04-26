import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Descrição classe
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public class Encomenda {
    private List<Artigo> listaArtigos;
    private Transportadora transportadora;
    private int dimensao;
    private double precoTotalArtigos;

    public static final int PEQUENO = 1;
    //Medio: Encomenda com 2 a 5 artigos.
    public static final int MEDIO = 2;
    //Grande: Encomenda com mais de 5.
    public static final int GRANDE = 3;

    public Encomenda() {
        this.listaArtigos = new ArrayList<>();
        this.transportadora = new Transportadora();
        this.dimensao = PEQUENO;
        this.precoTotalArtigos = 0;
    }

    public Encomenda(List<Artigo> listaArtigos, Transportadora transportadora, int dimensao, double precoTotalArtigos) {
        this.listaArtigos = listaArtigos;
        this.transportadora = transportadora;
        this.dimensao = dimensao;
        this.precoTotalArtigos = precoTotalArtigos;
    }

    public Encomenda(Encomenda encomenda) {
        this.listaArtigos = encomenda.getListaArtigos();
        this.transportadora = encomenda.getTransportadora();
        this.dimensao = encomenda.getDimensao();
        this.precoTotalArtigos = encomenda.getPrecoTotalArtigos();
    }

    public List<Artigo> getListaArtigos() {
        return listaArtigos.stream().map(Artigo::clone).collect(Collectors.toList());
    }

    public void setListaArtigos(List<Artigo> listaArtigos) {
        this.listaArtigos = listaArtigos.stream().map(Artigo::clone).collect(Collectors.toList());
    }

    public Transportadora getTransportadora()
    {
        return this.transportadora.clone();
    }

    public void setTransportadora(Transportadora transportadora)
    {
        this.transportadora = transportadora.clone();
    }

    public int getDimensao() {
        return dimensao;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }

    public double getPrecoTotalArtigos() {
        return precoTotalArtigos;
    }

    public void setPrecoTotalArtigos(double precoTotalArtigos) {
        this.precoTotalArtigos = Math.round(precoTotalArtigos * 100.0) / 100.0;
    }

    public void adicionaArtigo(Artigo artigo) throws EncomendaException {
        if (!this.listaArtigos.contains(artigo)) {
            this.listaArtigos.add(artigo.clone());
            this.alteraPreco();
            this.alteraDimensão(listaArtigos.size());
        } else throw new EncomendaException("Artigo já existente na encomenda!");
    }

    public void removeArtigo(Artigo artigo) throws EncomendaException {
        if (this.listaArtigos.contains(artigo)) {
            this.listaArtigos.remove(artigo);
            this.alteraDimensão(listaArtigos.size());
            this.alteraPreco();
        } else throw new EncomendaException("Artigo não existe na encomenda!");
    }

    public void alteraPreco() {
        double valorArtigos = this.listaArtigos.stream().mapToDouble(Artigo::getPrecoBase).sum() +
                this.listaArtigos.stream().mapToDouble(Artigo::getCorrecaoPreco).sum();
        double valorArtigosUsados = (this.listaArtigos.stream().filter(artigo -> artigo.getEstado().getTipoEstado() == EstadoArtigo.USADO).count()) * 0.25;
        double valorArtigosNovos = (this.listaArtigos.stream().filter(artigo -> artigo.getEstado().getTipoEstado() == EstadoArtigo.NOVO).count()) * 0.5;
        double valorExpedicao = this.transportadora.calculaValorExpedicao(this.listaArtigos.size());
        this.setPrecoTotalArtigos(valorArtigos + valorArtigosUsados + valorArtigosNovos + valorExpedicao);
    }

    public void alteraDimensão(int tamanho) {
        if (tamanho <= 1) {
            this.setDimensao(PEQUENO);
        }
        if (tamanho >= 2 && tamanho <= 5) {
            this.setDimensao(MEDIO);
        }
        if (tamanho >= 6) {
            this.setDimensao(GRANDE);
        }
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
        Encomenda encomenda = (Encomenda) o;
        return (this.getListaArtigos().equals(encomenda.getListaArtigos()) &&
                this.getTransportadora().equals(encomenda.getTransportadora()) &&
                this.getPrecoTotalArtigos() == encomenda.getPrecoTotalArtigos() &&
                this.getDimensao() == encomenda.getDimensao());
    }

    private String dimensaoToString() {
        if (this.getDimensao() == PEQUENO) {
            return "Pequena";
        }
        if (this.getDimensao() == MEDIO) {
            return "Media";
        }
        return "Grande";
    }


    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(this.listaArtigos.toString());
        string.append("Dimensão: " + this.dimensaoToString() + "\n");
        string.append("Preço: " + this.precoTotalArtigos + "\n");
        return string.toString();
    }

    public Encomenda clone() {
        return new Encomenda(this);
    }
}
