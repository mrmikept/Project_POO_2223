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

public class Utilizador implements Serializable {
    private int id;
    private String email;
    private String palavraPasse;
    private String nome;
    private String morada;
    private int nrFiscal;
    private Map<String, Artigo> listaArtigos;
    private List<Encomenda> listaEncomendas;
    private List<Fatura> listaFaturas;


    public Utilizador()
    {
        this.id = 0;
        this.email = "";
        this.palavraPasse = "";
        this.nome = "";
        this.morada = "";
        this.nrFiscal = 0;
        this.listaArtigos = new HashMap<>();
        this.listaEncomendas = new ArrayList<>();
        this.listaFaturas = new ArrayList<>();
    }

    public Utilizador(int id, String email, String palavraPasse, String nome, String morada, int nrFiscal, HashMap<String, Artigo> listaArtigos, ArrayList<Encomenda> listaEncomendas, ArrayList<Fatura> listaFaturas) {

        this.id = id;
        this.email = email;
        this.palavraPasse = palavraPasse;
        this.nome = nome;
        this.morada = morada;
        this.nrFiscal = nrFiscal;
        this.listaArtigos = listaArtigos;
        this.listaEncomendas = listaEncomendas;
        this.listaFaturas = listaFaturas;
    }

    public Utilizador(Utilizador utilizador) {

        this.id = utilizador.getId();
        this.email = utilizador.getEmail();
        this.palavraPasse = utilizador.getPalavraPasse();
        this.nome = utilizador.getNome();
        this.morada = utilizador.getMorada();
        this.nrFiscal = utilizador.getNrFiscal();
        this.listaArtigos = utilizador.getListaArtigos();
        this.listaEncomendas = utilizador.getListaEncomendas();


    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPalavraPasse()
    {
        return palavraPasse;
    }

    public void setPalavraPasse(String palavraPasse)
    {
        this.palavraPasse = palavraPasse;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return this.morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getNrFiscal() {
        return this.nrFiscal;
    }

    public void setNrFiscal(int nrFiscal) {
        this.nrFiscal = nrFiscal;
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
        return (this.getId() == utilizador.getId() &&
                this.getEmail().equals(utilizador.getEmail()) &&
                this.getPalavraPasse().equals(utilizador.getPalavraPasse()) &&
                this.getNome().equals(utilizador.getNome()) &&
                this.getMorada().equals(utilizador.getMorada()) &&
                this.getNrFiscal() == utilizador.getNrFiscal() &&
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