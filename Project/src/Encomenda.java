import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Descrição classe
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public class Encomenda implements Serializable
{
    private Map<String,List<Artigo>> listaArtigos;
    private int dimensao;
    private double precoFinal;
    private int estado;

    private LocalDate data;

    //Pequeno: Encomenda com apenas 1 artigo.
    public static final int PEQUENO = 1;
    //Medio: Encomenda com 2 a 5 artigos.
    public static final int MEDIO = 2;
    //Grande: Encomenda com mais de 5.
    public static final int GRANDE = 3;

    public static final int PENDENTE = 0;
    public static final int FINALIZADA = 1;
    public static final int EXPEDIDA = 2;

    public Encomenda()
    {
        this.listaArtigos = new HashMap<>();
        this.dimensao = PEQUENO;
        this.precoFinal = 0.0;
        this.estado = PENDENTE;
        this.data = LocalDate.now();
    }

    public Encomenda(ArrayList<Artigo> listaArtigos, int dimensao, double precoFinal, int estado, LocalDate data)
    {
        //TODO clone do arraylist!
        this.dimensao = dimensao;
        this.precoFinal = precoFinal;
        this.estado = estado;
        this.data = data;
    }

    public Encomenda(Encomenda encomenda)
    {
        //TODO clone do arraylist!
        this.dimensao = encomenda.getDimensao();
        this.precoFinal = encomenda.getPrecoFinal();
        this.estado = encomenda.getEstado();
        this.data = encomenda.getData();
    }

    public Map<String, List<Artigo>> getListaArtigos() {
        return listaArtigos.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().stream().map(Artigo::clone).collect(Collectors.toList())));
    }

    public int getDimensao() {
        return dimensao;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }

    public double getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(double precoFinal) {
        this.precoFinal = precoFinal;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void alteraDimensãoEncomenda(int tamanho)
    {
        if (tamanho <= 1)
        {
            this.setDimensao(PEQUENO);
        }
        if (tamanho >= 2 && tamanho <= 5)
        {
            this.setDimensao(MEDIO);
        }
        if (tamanho >= 6)
        {
            this.setDimensao(GRANDE);
        }
    }

   // public void alteraPreco()
   // {
      //  double valorArtigos = this.listaArtigos.stream().mapToDouble(Artigo::getPrecoBase).sum() +
     //           this.listaArtigos.stream().mapToDouble(Artigo::getCorrecaoPreco).sum();
    //    double valorArtigosUsados = (this.listaArtigos.stream().filter(artigo -> artigo.getEstado().getTipoEstado() == EstadoArtigo.USADO).count()) * 0.25;
    //    double valorArtigosNovos = (this.listaArtigos.stream().filter(artigo -> artigo.getEstado().getTipoEstado() == EstadoArtigo.NOVO).count()) * 0.5;
  //      List<Artigo> arrayListEnc = this.getListaArtigos();
//
 //       this.setPrecoFinal(valorArtigos + valorArtigosUsados + valorArtigosNovos + valorTransportadoras);
 //   }

    public void adicionaArtigoEncomenda(Artigo artigo) throws EncomendaException
    {
        if (this.getListaArtigos().containsKey(artigo.getTransportadora().getNome()))
        {
            List<Artigo> listaArtigos = this.getListaArtigos().get(artigo.getTransportadora().getNome());
            if (!listaArtigos.contains(artigo))
            {
                listaArtigos.add(artigo.clone());
                this.listaArtigos.replace(artigo.getTransportadora().getNome(),listaArtigos);
            }
            else throw new EncomendaException("O artigo já se encontra na encomenda");
        }
        else
        {
            List<Artigo> listaArtigos = new ArrayList<Artigo>();
            listaArtigos.add(artigo.clone());
            this.listaArtigos.put(artigo.getTransportadora().getNome(),listaArtigos);
        }
    }

    public void removeArtigoEncomenda(Artigo artigo) throws EncomendaException
    {
        //TODO: Ver se existe chave.
        //TODO: Se existir, verificar se o arrayList possui o artigo
        //TODO: Se sim, remover o artigo.

        //TODO: Se não existir chave então throw exception
    }

    public Encomenda clone()
    {
        return new Encomenda(this);
    }

    private String dimensaoToString()
    {
        if (this.getDimensao() == PEQUENO)
        {
            return "Pequena";
        }
        if (this.getDimensao() == MEDIO)
        {
            return "Media";
        }
        return "Grande";
    }

    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append("[Encomenda] ");
        string.append("Artigos: " + this.listaArtigos.toString() + "\n");
        string.append("Dimensão: " + this.dimensaoToString() + "\n");
        string.append("Preço Final: " + this.getPrecoFinal() + "\n");
        string.append("Estado: " + this.getEstado() + "\n");
        string.append("Data criação: " + this.getData().toString());
        return string.toString();
    }
}
