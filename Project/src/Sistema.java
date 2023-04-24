import java.io.Serializable;
import java.security.PublicKey;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Descrição classe
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public class Sistema implements Serializable
{
    private Map<String,Utilizador> listaUtilizadores;
    private Map<String, Transportadora> listaTransportadoras;
    private Map<Integer, Artigo> listaArtigosVenda;
    private Map<Integer, Artigo> listaArtigosComprados;
    private Map<String, Encomenda> listaEncomendas;
    private LocalDate dataSistema;

    public Sistema()
    {
        this.listaUtilizadores = new HashMap<>();
        this.listaTransportadoras = new HashMap<>();
        this.listaArtigosVenda = new HashMap<>();
        this.listaArtigosComprados = new HashMap<>();
        this.listaEncomendas = new HashMap<>();
        this.dataSistema = LocalDate.now();
    }

    public Sistema(Map<String, Utilizador> listaUtilizadores, Map<String,Transportadora> listaTransportadoras, Map<Integer,Artigo> listaArtigosVenda, Map<Integer,Artigo> listaArtigosComprados, Map<String,Encomenda> listaEncomendas, LocalDate dataSistema)
    {
        this.listaUtilizadores = listaUtilizadores;
        this.listaTransportadoras = listaTransportadoras;
        this.listaArtigosVenda = listaArtigosVenda;
        this.listaArtigosComprados = listaArtigosComprados;
        this.listaEncomendas = listaEncomendas;
        this.dataSistema = dataSistema;
    }

    public Sistema(Sistema sistema)
    {
        this.listaUtilizadores = sistema.getListaUtilizadores();
        this.listaTransportadoras = sistema.getListaTransportadoras();
        this.listaArtigosVenda = sistema.getListaArtigosVenda();
        this.listaArtigosComprados = sistema.getListaArtigosComprados();
        this.listaEncomendas = sistema.getListaEncomendas();
        this.dataSistema = sistema.getDataSistema();
    }

    public Map<String, Utilizador> getListaUtilizadores() {
        return listaUtilizadores.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public void setListaUtilizadores(Map<String, Utilizador> listaUtilizadores) {
        this.listaUtilizadores = listaUtilizadores.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));;
    }

    public Map<String, Transportadora> getListaTransportadoras() {
        return listaTransportadoras.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public void setListaTransportadoras(Map<String, Transportadora> listaTransportadoras) {
        this.listaTransportadoras = listaTransportadoras.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));;
    }

    public Map<Integer, Artigo> getListaArtigosVenda() {
        return listaArtigosVenda.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e-> e.getValue().clone()));
    }

    public void setListaArtigosVenda(Map<Integer, Artigo> listaArtigosVenda) {
        this.listaArtigosVenda = listaArtigosVenda.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e-> e.getValue().clone()));
    }

    public Map<Integer, Artigo> getListaArtigosComprados() {
        return listaArtigosComprados.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public void setListaArtigosComprados(Map<Integer, Artigo> listaArtigosComprados) {
        this.listaArtigosComprados = listaArtigosComprados.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public Map<String, Encomenda> getListaEncomendas() {
        return listaEncomendas.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public void setListaEncomendas(Map<String, Encomenda> listaEncomendas) {
        this.listaEncomendas = listaEncomendas.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public LocalDate getDataSistema()
    {
        return this.dataSistema;
    }

    public void setDataSistema(LocalDate data)
    {
        this.dataSistema = data;
    }

    public void setDataSistema(int dias)
    {
        this.dataSistema.plusDays(dias);
        //TODO Atualizar sistema e gerar faturas!
    }

    /**
     * Adiciona um utilizador à lista de utilizadores do Sistema.
     *
     * @param utilizador Um utilizdor
     * @throws UtilizadorException Caso o utilizdor já exista
     */
    public void adicionaUtilizador(Utilizador utilizador) throws UtilizadorException
    {
        if (!this.listaUtilizadores.containsKey(utilizador.getEmail()))
        {
            this.listaUtilizadores.put(utilizador.getEmail(),utilizador.clone());
        }
        else{
            throw new UtilizadorException("O utilizador com email: "+ utilizador.getEmail() + " já existe!");
        }
    }

    /**
     * Adiciona um utilizador à lista de utilizadores do Sistema
     *
     * @param email Email do utilizador
     * @param palavraPasse Palavra-passe do utilizador
     * @param nome Nome do utilizador
     * @param morada Morada Fiscal do utilizador
     * @param nrFiscal Numero de contribuinte do utilizador
     * @throws UtilizadorException Caso o utilizador já exista
     */
    public void adicionaUtilizador( String email, String palavraPasse, String nome, String morada, int nrFiscal) throws UtilizadorException
    {
        if (!this.listaUtilizadores.containsKey(email))
        {
            int id = this.listaUtilizadores.size() + 1;
            Utilizador utilizador = new Utilizador(id, email, palavraPasse, nome, morada, nrFiscal);
            this.listaUtilizadores.put(email,utilizador);
        }
        else
        {
            throw new UtilizadorException("O utilizador com email: "+ email + " já existe!");
        }
    }

    /**
     * Adiciona uma transportadora à lista de transportadoras do Sistema
     *
     * @param transportadora Uma transportadora
     * @throws TransportadoraException Caso a Transportadora já exista
     */
    public void adicionaTransportadora(Transportadora transportadora) throws TransportadoraException
    {
        if(!this.listaTransportadoras.containsKey(transportadora.getNome()))
        {
            this.listaTransportadoras.put(transportadora.getNome(),transportadora.clone());
        }
        else
        {
            throw new TransportadoraException("A Transportadora " + transportadora.getNome() + " já existe!");
        }
    }

    /**
     * Adiciona uma transportadora à lista de transportadoras do Sistema
     *
     * @param nome Nome da transportadora
     * @param lucro Margem de lucro da Transportadora
     * @param tipo Tipo de transportadora
     * @throws TransportadoraException Caso a Transportadora já exista
     */
    public void adicionaTransportadora(String nome, double lucro, int tipo) throws TransportadoraException
    {
        if (!this.listaTransportadoras.containsKey(nome))
        {
            Transportadora transportadora = new Transportadora(nome, lucro, tipo);
            this.listaTransportadoras.put(nome,transportadora);
        }
        else
        {
            throw new TransportadoraException("A Transportadora " + nome + " já existe!");
        }
    }

    public void adicionaArtigoVenda(Artigo artigo) throws ArtigoException
    {
        if (!this.listaArtigosVenda.containsKey(artigo.getId()))
        {
            this.listaArtigosVenda.put(artigo.getId(), artigo.clone());
        }
    }

    /**
     * Adiciona o artigo Tshirt à lista de artigos
     *
     * @param tshirt
     * @throws ArtigoException
     */
    public void adicionaTshirtVenda(Tshirt tshirt) throws ArtigoException {
        if (!this.listaArtigosVenda.containsKey(tshirt.getId()))
        {
            this.listaArtigosVenda.put(tshirt.getId(),tshirt.clone());
        }
        else{
            throw new ArtigoException("Este Artigo já está à venda");
        }
    }

    public void adicionaTshirtVenda(int id, String descricao, String marca, double precoBase, double correcaoPreco, EstadoArtigo estado, Transportadora transportadora, int tamanho, int padrao) throws ArtigoException {
        if (!this.listaArtigosVenda.containsKey(id))
        {
            Tshirt tshirt = new Tshirt(id, descricao, marca, precoBase, correcaoPreco, estado, transportadora, tamanho, padrao);
            this.listaArtigosVenda.put(id,tshirt);
        }
        else{
            throw new ArtigoException("Este Artigo já está à venda");
        }
    }

    public void adicionaSapatilhaVenda(Sapatilha sapatilha) throws ArtigoException {
        if (!this.listaArtigosVenda.containsKey(sapatilha.getId()))
        {
            this.listaArtigosVenda.put(sapatilha.getId(),sapatilha.clone());
        }
        else{
            throw new ArtigoException("Este Artigo já está à venda");
        }
    }
    public void adicionaSapatilhaVenda(int id, String descricao, String marca, double precoBase, double correcaoPreco, EstadoArtigo estado, Transportadora transportadora, int tamanho, int tipoCordao, String cor, LocalDate data, int tipo) throws ArtigoException {
        if (!this.listaArtigosVenda.containsKey(id))
        {
            Sapatilha sapatilha = new Sapatilha(id, descricao, marca, precoBase, correcaoPreco, estado, transportadora, tamanho, tipoCordao, cor, data, tipo);
            this.listaArtigosVenda.put(id,sapatilha);
        }
        else{
            throw new ArtigoException("Este Artigo já está à venda");
        }
    }

    public void adicionaMalaVenda(Mala mala) throws ArtigoException {
        if (!this.listaArtigosVenda.containsKey(mala.getId()))
        {
            this.listaArtigosVenda.put(mala.getId(),mala.clone());
        }
        else{
            throw new ArtigoException("Este Artigo já está à venda");
        }
    }

    public void adicionaMalaVenda(int id, String descricao, String marca, double precoBase, double correcaoPreco, EstadoArtigo estado, Transportadora transportadora, double dimensao, String material, LocalDate anoLancamento, int tipo) throws ArtigoException {
        if (!this.listaArtigosVenda.containsKey(id))
        {
            Mala mala = new Mala(id,descricao,marca,precoBase, correcaoPreco, estado, transportadora, dimensao, material, anoLancamento, tipo);
            this.listaArtigosVenda.put(id,mala);
        }
        else{
            throw new ArtigoException("Este Artigo já está à venda");
        }
    }

    
    public Utilizador procuraUtilizador(String email) throws UtilizadorException
    {
        if (listaUtilizadores.containsKey(email))
        {
            return listaUtilizadores.get(email);
        }
        else{
            throw new UtilizadorException("O utilizador com o email:" + email + "não encontrado");
        }
    }

    public boolean verificaUtilizador(String email) throws UtilizadorException
    {
        return (listaUtilizadores.containsKey(email));
    }

    public boolean verificaPassword(String email, String pass) throws UtilizadorException
    {
        Utilizador utilizador = procuraUtilizador(email);
        return (pass.compareTo(utilizador.getPalavraPasse()) == 0);
    }

    public Artigo procuraArtigoVenda(int id) throws ArtigoException
    {
        if (listaArtigosVenda.containsKey(id))
        {
            return listaArtigosVenda.get(id);
        }
        else{
            throw new ArtigoException("O artigo com o id" + id + "não existe");
        }
    }

    public Encomenda procuraEncomenda(String email) throws EncomendaException {
        if (listaEncomendas.containsKey(email))
        {
            return listaEncomendas.get(email);
        }
        else
        {
            throw new EncomendaException("O Utilizador com email, " + email + " não tem encomendas!");
        }
    }


    public Sistema clone()
    {
        return new Sistema(this);
    }
}
