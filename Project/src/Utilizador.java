import java.io.Serializable;
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

public class Utilizador extends Entidade implements Serializable {

    private Map<String, Artigo> listaArtigos;
    private List<Encomenda> listaEncomendas;
    private List<Fatura> listaFaturas;


    public Utilizador()
    {
        super();
        this.listaArtigos = new HashMap<>();
        this.listaEncomendas = new ArrayList<>();
        this.listaFaturas = new ArrayList<>();
    }

    public Utilizador(int id, String email, String palavraPasse, String nome, String morada, int nrFiscal, HashMap<String, Artigo> listaArtigos, ArrayList<Encomenda> listaEncomendas, ArrayList<Fatura> listaFaturas) {
        super(id,email,palavraPasse,nome,morada, nrFiscal);
        this.listaArtigos = listaArtigos;
        this.listaEncomendas = listaEncomendas;
        this.listaFaturas = listaFaturas;
    }

    public Utilizador(Utilizador utilizador) {

        super(utilizador);
        this.listaArtigos = utilizador.getListaArtigos();
        this.listaEncomendas = utilizador.getListaEncomendas();


    }

    public Map<String, Artigo> getListaArtigos(){
        return listaArtigos.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public void setListaArtigos(Map<String, Artigo> listaArtigos){
        this.listaArtigos = listaArtigos.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public List<Encomenda> getListaEncomendas(){
        return this.listaEncomendas.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    public List<Encomenda> getListaEncomendas(int estado)
    {
        return this.listaEncomendas.stream().filter(encomenda -> encomenda.getEstado() == estado).map(Encomenda::clone).collect(Collectors.toList());
    }


    public void setListaEncomendas(List<Encomenda> listaEncomendas)
    {
        this.listaEncomendas = listaEncomendas.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    public List<Fatura> getListaFaturas()
    {
        return this.listaFaturas = listaFaturas.stream().map(Fatura::clone).collect(Collectors.toList());
    }

    public void setListaFaturas(List<Fatura> listaFaturas)
    {
        this.listaFaturas = listaFaturas.stream().map(Fatura::clone).collect(Collectors.toList());
    }

    public Fatura getFatura(int idEncomenda) throws UtilizadorException {
        List<Fatura> faturas = this.listaFaturas.stream().filter(fatura -> fatura.getIdEncomenda() == idEncomenda).collect(Collectors.toList());
        if (!faturas.isEmpty())
        {
            return faturas.get(0).clone();
        } else throw new UtilizadorException("Fatura não encontrada!");
    }

    public void adicionaArtigo(Artigo artigo) throws UtilizadorException {
        if (!this.listaArtigos.containsKey(artigo.getId()))
        {
            this.listaArtigos.put(artigo.getId(),artigo);
        }
        else throw new UtilizadorException("O utilizador já tem este artigo à venda!");
    }

    public void removeArtigo(Artigo artigo) throws UtilizadorException {
        if (this.listaArtigos.containsKey(artigo.getId()))
        {
            this.listaArtigos.remove(artigo.getId(),artigo);
        }
        else throw new UtilizadorException("Este artigo não existe!");
    }

    public void adicionaEncomenda(Encomenda encomenda)
    {
        this.listaEncomendas.add(encomenda);
    }


    public void removeEncomenda(Encomenda encomenda) throws UtilizadorException {
        if (this.listaEncomendas.contains(encomenda))
        {
            this.listaEncomendas.remove(encomenda);
        } else throw new UtilizadorException("O utilizador não possui esta encomenda!");
    }


    public Encomenda getEncomenda(int idEncomenda) throws UtilizadorException {
        List<Encomenda> encomendas = this.listaEncomendas.stream().filter(encomenda -> encomenda.getId() == idEncomenda).collect(Collectors.toList());
        if (!encomendas.isEmpty())
        {
            return encomendas.get(0);
        } else throw new UtilizadorException("O utilizador " + this.getEmail() + " não possui a encomenda com o id " + idEncomenda);
    }

    public void adicionaFatura(Fatura fatura)
    {
        this.listaFaturas.add(fatura);
    }

    public void removeFatura(Fatura fatura) throws UtilizadorException {
        if (this.listaFaturas.contains(fatura))
        {
            this.listaFaturas.remove(fatura);
        } else throw new UtilizadorException("O utilizador " + this.getEmail() + " não possui fatura para a encomenda " + fatura.getIdEncomenda());
    }

    public void removeFatura(int idEncomenda) throws UtilizadorException {
        List<Fatura> listaFaturas = this.listaFaturas.stream().filter(fatura -> fatura.getIdEncomenda() == idEncomenda).collect(Collectors.toList());
        if (!listaFaturas.isEmpty())
        {
            this.listaFaturas.remove(listaFaturas.get(0));
        } else throw new UtilizadorException("O utilizador " + this.getEmail() + " não possui fatura para a encomenda " + idEncomenda);
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
        Utilizador utilizador = (Utilizador) o;
        return (super.equals(utilizador) &&
                this.getListaArtigos().equals(utilizador.getListaArtigos()) &&
                this.getListaEncomendas().equals(utilizador.getListaEncomendas())) &&
                this.getListaFaturas().equals(utilizador.getListaFaturas());
    }

    public Utilizador clone() {
        return new Utilizador(this);
    }

    public String toString()
    {
        return Apresentacao.CYAN_BOLD +"                                                                                                   [Utilizador]"+ Apresentacao.RESET + "\n" +
               Apresentacao.CYAN_BOLD +"                                                                                                   Numero: "+ Apresentacao.RESET + this.getId() + "\n" +
               Apresentacao.CYAN_BOLD +"                                                                                                   Email: "+ Apresentacao.RESET + this.getEmail() + "\n" +
               Apresentacao.CYAN_BOLD +"                                                                                                   Nome: "+ Apresentacao.RESET + this.getNome() + "\n" +
               Apresentacao.CYAN_BOLD +"                                                                                                   Morada: "+ Apresentacao.RESET + this.getMorada() + "\n" +
               Apresentacao.CYAN_BOLD +"                                                                                                   Numero Fiscal: "+ Apresentacao.RESET + this.getNrFiscal();
    }
}