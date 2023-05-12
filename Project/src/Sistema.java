import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe que contem todos as funcionalidades do sistema Vintage
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */

public class Sistema implements Serializable,Atributos {
    private Map<String, Utilizador> listaUtilizadores;
    private Map<String, Transportadora> listaTransportadoras;
    private Map<String, Artigo> listaArtigos;
    private List<Encomenda> listaEncomendas;
    private List<Fatura> listaFaturas;
    private LocalDate dataCriacao;
    private LocalDate dataUltimoAcesso;
    private LocalDate dataAtual;
    private TaxasImpostos taxas;
    private int tempoDevolucao;
    private static final int TEMPODEVOLUCAO_OMISSAO = 2;

    ///////////////
    //Contrutores//
    ///////////////

    public Sistema() {
        this.listaUtilizadores = new HashMap<>();
        this.listaTransportadoras = new HashMap<>();
        this.listaArtigos = new HashMap<>();
        this.listaEncomendas = new ArrayList<>();
        this.listaFaturas = new ArrayList<>();
        this.dataCriacao = LocalDate.now();
        this.dataAtual = LocalDate.now();
        this.dataUltimoAcesso = LocalDate.now();
        this.taxas = new TaxasImpostos();
        this.tempoDevolucao = TEMPODEVOLUCAO_OMISSAO;
    }

    public Sistema(Map<String, Utilizador> listaUtilizadores, Map<String, Transportadora> listaTransportadoras, Map<String, Artigo> listaArtigos, List<Encomenda> listaEncomendas, List<Fatura> listaFaturas, LocalDate dataCriacao, LocalDate dataAtual, LocalDate dataUltimoAcesso, TaxasImpostos taxas, int tempoDevolucao) {
        this.listaUtilizadores = listaUtilizadores;
        this.listaTransportadoras = listaTransportadoras;
        this.listaArtigos = listaArtigos;
        this.listaEncomendas = listaEncomendas;
        this.listaFaturas = listaFaturas;
        this.dataCriacao = dataCriacao;
        this.dataAtual = dataAtual;
        this.dataUltimoAcesso = dataUltimoAcesso;
        this.taxas = taxas;
        this.tempoDevolucao = tempoDevolucao;
    }

    public Sistema(Sistema sistema) {
        this.listaUtilizadores = sistema.getListaUtilizadores();
        this.listaTransportadoras = sistema.getListaTransportadoras();
        this.listaArtigos = sistema.getListaArtigos();
        this.listaEncomendas = sistema.getListaEncomendas();
        this.listaFaturas = sistema.getListaFaturas();
        this.dataCriacao = sistema.getDataCriacao();
        this.dataUltimoAcesso = sistema.getDataUltimoAcesso();
        this.dataAtual = sistema.getDataAtual();
        this.tempoDevolucao = sistema.getTempoDevolucao();
    }

    /////////////////////
    //Getters e Setters//
    ////////////////////

    public Map<String, Utilizador> getListaUtilizadores() {
        return listaUtilizadores.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().clone()));
    }

    public void setListaUtilizadores(Map<String, Utilizador> listaUtilizadores) {
        this.listaUtilizadores = listaUtilizadores.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().clone()));
        ;
    }

    public Map<String, Transportadora> getListaTransportadoras() {
        return listaTransportadoras.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().clone()));
    }

    public void setListaTransportadoras(Map<String, Transportadora> listaTransportadoras) {
        this.listaTransportadoras = listaTransportadoras.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().clone()));
        ;
    }

    public Map<String, Artigo> getListaArtigos() {
        return this.listaArtigos.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().clone()));
    }

    public void setListaArtigos(Map<String, Artigo> listaArtigos) {
        this.listaArtigos = listaArtigos.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().clone()));
    }

    public List<Encomenda> getListaEncomendas() {
        return listaEncomendas.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    public void setListaEncomendas(List<Encomenda> listaEncomendas) {
        this.listaEncomendas = listaEncomendas.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    public List<Fatura> getListaFaturas()
    {
        return this.listaFaturas.stream().map(Fatura::clone).collect(Collectors.toList());
    }

    public void setListaFaturas(List<Fatura> listaFaturas)
    {
        this.listaFaturas = listaFaturas.stream().map(Fatura::clone).collect(Collectors.toList());
    }

    public LocalDate getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(LocalDate data) {
        this.dataCriacao = data;
    }

    public LocalDate getDataAtual() {
        return this.dataAtual;
    }

    public void setDataAtual(LocalDate data) {
        this.dataAtual = data;
    }

    public LocalDate getDataUltimoAcesso() {
        return dataUltimoAcesso;
    }

    public void setDataUltimoAcesso(LocalDate dataUltimoAcesso) {
        this.dataUltimoAcesso = dataUltimoAcesso;
    }

    public TaxasImpostos getTaxas() {
        return taxas;
    }

    public void setTaxas(TaxasImpostos taxas) {
        this.taxas = taxas.clone();
    }

    public int getTempoDevolucao() {
        return tempoDevolucao;
    }

    public void setTempoDevolucao(int tempoDevolucao) {
        this.tempoDevolucao = tempoDevolucao;
    }

    /**
     * Devolve todos os artigos à venda no sistema, filtrado pelo utilizador que está a comprar
     * @param email Email de um utilizador
     * @return Hashmap Artigos à venda
     * @throws UtilizadorException Caso o utilizador não exista.
     */
    public Map<String, Artigo> getArtigosVenda(String email) throws UtilizadorException {
        Utilizador utilizador = this.procuraUtilizador(email);
        return this.listaArtigos = listaArtigos.entrySet().stream().filter(artigo -> artigo.getValue().getVendedor().getId() != utilizador.getId() && artigo.getValue().getEstadoVenda() != Atributos.VENDIDO).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().clone()));
    }

    /**
     * Devolve os artigos à venda de um utilizador
     * @param email Email de um utilizador
     * @return Hashmap de Artigos à venda
     * @throws UtilizadorException Caso o utilizador não exista.
     */
    public Map<String, Artigo> getArtigosVendaUtilizador(String email) throws UtilizadorException {
        Utilizador utilizador = this.procuraUtilizador(email);
        return this.listaArtigos = listaArtigos.entrySet().stream().filter(artigo -> artigo.getValue().getVendedor().getId() == utilizador.getId() && artigo.getValue().getEstadoVenda() != Atributos.VENDIDO).collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    /**
     * Verifica se um utilizador possui artigos há venda
     * @param email Email de um utilizador
     * @return true se o utilizador tiver artigos há venda, false se não tiver artigos há venda.
     * @throws UtilizadorException Caso o utilizador não exista.
     */
    public boolean verificaArtigosVendaUtilizador(String email) throws UtilizadorException {
        return (!this.getArtigosVendaUtilizador(email).isEmpty());
    }

    public boolean verificaArtigoUtilizador(String email, String id) throws UtilizadorException {
        return (!this.procuraUtilizador(email).getListaArtigos().values().stream().filter(artigo -> artigo.getEstadoVenda() == Atributos.VENDA).collect(Collectors.toList()).isEmpty());
    }

    public boolean verificaArtigosVenda(String email)
    {
        return (!this.listaArtigos.entrySet().stream().filter(artigo -> !artigo.getValue().getVendedor().getEmail().equals(email)).collect(Collectors.toList()).isEmpty());
    }

    public boolean verificaArtigosID(String id)
    {
        return this.listaArtigos.containsKey(id);
    }

    /**
     * Adiciona um utilizador à lista de utilizadores do Sistema.
     *
     * @param utilizador Um utilizdor
     * @throws UtilizadorException Caso o utilizador já exista
     */
    public void adicionaUtilizador(Utilizador utilizador) throws UtilizadorException {
        if (!this.listaUtilizadores.containsKey(utilizador.getEmail())) {
            int id = this.listaUtilizadores.size() + 1;
            utilizador.setId(id);
            this.listaUtilizadores.put(utilizador.getEmail(), utilizador.clone());
        } else {
            throw new UtilizadorException("O utilizador com email: " + utilizador.getEmail() + " já existe!");
        }
    }

    /**
     * Adiciona um utilizador à lista de utilizadores do Sistema
     *
     * @param email        Email do utilizador.
     * @param palavraPasse Palavra-passe do utilizador.
     * @param nome         Nome do utilizador.
     * @param morada       Morada Fiscal do utilizador.
     * @param nrFiscal     Numero de contribuinte do utilizador.
     * @throws UtilizadorException Caso o utilizador já exista.
     */
    public void adicionaUtilizador(String email, String palavraPasse, String nome, String morada, int nrFiscal) throws UtilizadorException {
        if (!this.listaUtilizadores.containsKey(email)) {
            int id = this.listaUtilizadores.size() + 1;
            Utilizador utilizador = new Utilizador(id, email, palavraPasse, nome, morada, nrFiscal, new HashMap<>(), new ArrayList<>(), new ArrayList<>());
            this.listaUtilizadores.put(email, utilizador);
        } else {
            throw new UtilizadorException("O utilizador com email: " + email + " já existe!");
        }
    }

    /**
     * Adiciona uma transportadora à lista de transportadoras do Sistema
     *
     * @param transportadora Uma transportadora
     * @throws TransportadoraException Caso a Transportadora já exista
     */
    public void adicionaTransportadora(Transportadora transportadora) throws TransportadoraException {
        if (!this.listaTransportadoras.containsKey(transportadora.getNome())) {
            this.listaTransportadoras.put(transportadora.getNome(), transportadora.clone());
        } else {
            throw new TransportadoraException("A Transportadora " + transportadora.getNome() + " já existe!");
        }
    }

    /**
     * Adiciona uma transportadora à lista de transportadoras do Sistema
     *
     * @param nome  Nome da transportadora
     * @param lucro Margem de lucro da Transportadora
     * @param tipo  Tipo de transportadora
     * @throws TransportadoraException Caso a Transportadora já exista
     */
    public void adicionaTransportadora(String nome, double lucro, int tipo, int tempExpedicao) throws TransportadoraException {
        if (!this.listaTransportadoras.containsKey(nome.toUpperCase())) {
            Transportadora transportadora = new Transportadora(nome.toUpperCase(), lucro, tipo, tempExpedicao, 0, this.getTaxas());
            this.listaTransportadoras.put(nome.toUpperCase(), transportadora);
        } else {
            throw new TransportadoraException("A Transportadora " + nome + " já existe!");
        }
    }

    /**
     * Adiciona um artigo ao sistema.
     * @param artigo Artigo à adicionar ao sistema
     * @throws ArtigoException Caso o artigo já exista
     * @throws UtilizadorException Caso o utilizador não exista
     */
    public void adicionaArtigo(Artigo artigo) throws ArtigoException, UtilizadorException {
        if (!this.listaArtigos.containsKey(artigo.getId())) {
            Utilizador utilizador = this.procuraUtilizador(artigo.getVendedor().getEmail());
            this.listaArtigos.put(artigo.getId(), artigo.clone());
            utilizador.adicionaArtigo(this.listaArtigos.get(artigo.getId()));
        } else throw new ArtigoException("Artigo já há venda!");
    }

    /**
     * Remove um artigo do sistema.
     * @param artigo Artigo a remover
     * @throws UtilizadorException Caso o utilizador não exista.
     */
    public void removeArtigo(Artigo artigo) throws UtilizadorException {
        if (this.listaArtigos.containsKey(artigo.getId())) {
            Utilizador utilizador = this.procuraUtilizador(artigo.getVendedor().getEmail());
            this.listaArtigos.remove(artigo.getId(), artigo);
            utilizador.removeArtigo(artigo);
        }
    }

    /**
     * Remove um artigo ao sistema
     * @param id Id de um artigo
     * @throws UtilizadorException Caso o utilizador não exista
     * @throws ArtigoException Caso o Artigo não exista
     */
    public void removeArtigo(String id) throws UtilizadorException, ArtigoException {
        if (this.listaArtigos.containsKey(id)) {
            Artigo artigo = this.procuraArtigo(id);
            Utilizador utilizador = this.procuraUtilizador(artigo.getVendedor().getEmail());
            this.listaArtigos.remove(artigo.getId(), artigo);
            utilizador.removeArtigo(artigo);
        }
    }

    /**
     * Adiciona um tshirt ao sistema
     * @param id id da Tshirt
     * @param email Email do vendedor
     * @param descricao Descrição do artigo
     * @param marca Marca do artigo
     * @param precoBase Preço Base do artigo
     * @param avaliacao Avaliação do artigo
     * @param nrDonos Numero de donos do artigo
     * @param transportadora Nome da transportadora
     * @param tamanho Tamanho da Tshirt
     * @param padrao Padrão da tshirt
     * @throws ArtigoException Caso o artigo já exista
     * @throws UtilizadorException Caso o utilizador não exista
     * @throws TransportadoraException Caso a transportadora não exista
     */
    public void adicionaTshirtVenda(String id, String email, String descricao, String marca, double precoBase, double avaliacao, int nrDonos, String transportadora, int tamanho, int padrao) throws ArtigoException, UtilizadorException, TransportadoraException {
        if (!this.listaArtigos.containsKey(id))
        {
            Utilizador utilizador = this.procuraUtilizador(email);
            Tshirt tshirt = new Tshirt(id, utilizador, descricao, marca, precoBase, nrDonos, avaliacao, this.procuraTransportadora(transportadora), Atributos.VENDA, tamanho, padrao);
            this.listaArtigos.put(id,tshirt);
            utilizador.adicionaArtigo(this.listaArtigos.get(id));
        } else {
            throw new ArtigoException("Este Artigo já está à venda");
        }
    }

    /**
     * Adiciona um tshirt ao sistema
     * @param id id da Tshirt
     * @param email Email do vendedor
     * @param descricao Descrição do artigo
     * @param marca Marca do artigo
     * @param precoBase Preço Base do artigo
     * @param avaliacao Avaliação do artigo
     * @param nrDonos Numero de donos do artigo
     * @param transportadora Nome da transportadora
     * @param tamanho Tamanho do artigo Sapatilha
     * @param tipoCordao Tipo do cordão da sapatilha
     * @param cor Cor da sapatilha
     * @param data Data de lançamento da sapatilha
     * @param tipo Tipo de sapatilha, Normal ou Premium
     * @throws ArtigoException Caso o artigo já exista
     * @throws UtilizadorException Caso o utilizador não exista
     * @throws TransportadoraException Caso a transportadora não exista
     */
    public void adicionaSapatilhaVenda(String id, String email, String descricao, String marca, double precoBase, double avaliacao, int nrDonos, String transportadora, int tamanho, int tipoCordao, String cor, int data, int tipo) throws ArtigoException, UtilizadorException, TransportadoraException {
        if (!this.listaArtigos.containsKey(id)) {
            Utilizador utilizador = this.procuraUtilizador(email);
            Sapatilha sapatilha = new Sapatilha(id, utilizador, descricao, marca, precoBase, nrDonos, avaliacao, this.procuraTransportadora(transportadora).clone(), Atributos.VENDA, tamanho, tipoCordao, cor, data, tipo);
            this.listaArtigos.put(id, sapatilha);
            utilizador.adicionaArtigo(sapatilha);
        } else {
            throw new ArtigoException("Este Artigo já está à venda");
        }
    }

    /**
     * Adiciona uma Mala ao sistema
     * @param id id da Mala
     * @param email Email do vendedor
     * @param descricao Descrição do artigo
     * @param marca Marca do artigo
     * @param precoBase Preço Base do artigo
     * @param avaliacao Avaliação do artigo
     * @param nrDonos Numero de donos do artigo
     * @param transportadora Nome da transportadora
     * @param dimensao Dimensão da mala
     * @param material Material da mala
     * @param anoLancamento Ano de lançamento da mala
     * @param tipo Tipo de artigo, Normal ou Premium
     * @throws ArtigoException Caso o artigo já exista
     * @throws UtilizadorException Caso o utilizador não exista
     * @throws TransportadoraException Caso a transportadora não exista
     */
    public void adicionaMalaVenda(String id, String email, String descricao, String marca, double precoBase, double avaliacao, int nrDonos, String transportadora, double dimensao, String material, int anoLancamento, int tipo) throws ArtigoException, UtilizadorException, TransportadoraException {
        if (!this.listaArtigos.containsKey(id))
        {
            Utilizador utilizador = this.procuraUtilizador(email);
            Mala mala = new Mala(id, utilizador, descricao,marca,precoBase, nrDonos, avaliacao, this.procuraTransportadora(transportadora).clone(), Atributos.VENDA, dimensao, material, anoLancamento, tipo);
            this.listaArtigos.put(id,mala);
            utilizador.adicionaArtigo(this.listaArtigos.get(id));
        } else {
            throw new ArtigoException("Este Artigo já está à venda");
        }
    }


    /**
     * Porcura um utilizador
     * @param email Email do utilizador
     * @return Um utilizador
     * @throws UtilizadorException Caso o utilizador não exista
     */
    public Utilizador procuraUtilizador(String email) throws UtilizadorException {
        if (listaUtilizadores.containsKey(email)) {
            return listaUtilizadores.get(email);
        } else {
            throw new UtilizadorException("O utilizador com o email:" + email + "não encontrado");
        }
    }

    /**
     * Procura uma transportadora
     * @param nome Nome da transportadora
     * @return Uma transportadora
     * @throws TransportadoraException Caso a transportadora não exista
     */
    public Transportadora procuraTransportadora(String nome) throws TransportadoraException {
        if (listaTransportadoras.containsKey(nome)) {
            return listaTransportadoras.get(nome).clone();
        } else {
            throw new TransportadoraException("A Transportadora com o nome, " + nome + " não existe!");
        }
    }

    /**
     * Procura um artigo
     * @param id Id do artigo
     * @return Um artigo
     * @throws ArtigoException Caso o artigo não exista
     */
    public Artigo procuraArtigo(String id) throws ArtigoException {
        if (listaArtigos.containsKey(id)) {
            return listaArtigos.get(id);

        } else {
            throw new ArtigoException("O artigo com o id" + id + "não existe");
        }
    }

    /**
     * Procura um artigo à venda
     * @param id O id do artigo
     * @return Um artigo
     * @throws ArtigoException Caso o artigo não exista ou não esteja à venda.
     */
    public Artigo procuraArtigoVenda(String id) throws ArtigoException
    {
        if(listaArtigos.containsKey(id)){
            Artigo artigo = this.listaArtigos.get(id);
            if(artigo.getEstadoVenda() == Atributos.VENDA){
                return artigo;
            }
            else throw new ArtigoException("Este artigo não está à venda!");
         } else throw new ArtigoException("Este artigo não existe!");
      }


    /**
     * Procura uma encomenda
     * @param encomenda Uma encomenda
     * @return Uma encomenda
     * @throws EncomendaException Caso a encomenda não exista
     */
    public Encomenda procuraEncomenda(Encomenda encomenda) throws EncomendaException {
        if (this.listaEncomendas.contains(encomenda)) {
            return this.listaEncomendas.stream().filter(enc -> enc.equals(encomenda)).collect(Collectors.toList()).get(0);
        } else throw new EncomendaException("Esta encomenda não existe!");
    }

    /**
     * Verifica se um artigo se encontra à venda.
     * @param id Id do artigo
     * @return True caso o artigo esteja à venda, False se o artigo não estiver à venda.
     * @throws ArtigoException Caso o artigo não exista.
     */
    public boolean verificaArtigoVenda(String id) throws ArtigoException
    {
        if(listaArtigos.containsKey(id)){
            Artigo artigo = this.listaArtigos.get(id);
            if(artigo.getEstadoVenda() == Atributos.VENDA){
                return true;
            }
        }
        else throw new ArtigoException("ESTE ARTIGO NÃO ESTÁ À VENDA!!");
        return false;
    }

    /**
     * Procura uma encomenda
     * @param id Id da encomenda
     * @return Uma encomenda
     * @throws EncomendaException Caso a encomenda não exista
     */
    public Encomenda procuraEncomenda(int id) throws EncomendaException {
        List<Encomenda> encomendas = this.listaEncomendas.stream().filter(encomenda -> encomenda.getId() == id).collect(Collectors.toList());
        if (!encomendas.isEmpty())
        {
            return encomendas.get(0);
        } else throw new EncomendaException("Esta encomenda não existe!");
    }

    /**
     * Adiciona um encomenda
     * @param encomenda Uma encomenda
     * @param email Email do utilizador(comprador)
     * @throws UtilizadorException Caso o utilizador não exista
     * @throws EncomendaException Caso a encomenda já exista no sistema
     */
    public void adicionaEncomenda(Encomenda encomenda, String email) throws UtilizadorException, EncomendaException {
        if (this.listaUtilizadores.containsKey(email)) {
            if (!this.listaEncomendas.contains(encomenda)) {
                this.listaEncomendas.add(encomenda);
            } else throw new EncomendaException("Encomenda já existente!");
        } else throw new UtilizadorException("Utilizador não encontrado!");
    }

    /**
     * Adiciona uma fatura
     * @param fatura Uma fatura
     */
    public void adicionaFatura(Fatura fatura)
    {
        this.listaFaturas.add(fatura);
    }

    /**
     * Remove uma fatura
     * @param fatura Uma fatura
     */
    public void removeFatura(Fatura fatura)
    {
        this.listaFaturas.remove(fatura);
    }

    /**
     * Remove uma encomenda
     * @param encomenda Uma encomenda
     * @throws EncomendaException Caso a encomenda não exista.
     */
    public void removeEncomenda(Encomenda encomenda) throws EncomendaException {
        if (this.listaEncomendas.contains(encomenda)) {
            this.listaEncomendas.remove(encomenda);
        } else throw new EncomendaException("Esta encomenda não existe no sistema!");
    }

    /**
     * Remove uma encomenda
     * @param idEncomenda Id da encomenda
     * @param email Email do utilizador que faz a encomenda
     */
    public void removeEncomenda(int idEncomenda, String email) {
        if (this.listaUtilizadores.containsKey(email)) {
            Encomenda encomenda = this.listaEncomendas.get(idEncomenda - 1);
        }
    }

    /**
     * Adiciona um artigo a uma encomenda
     * @param artigo Um artigo
     * @param email Email do utilizador que faz a encomenda
     * @throws EncomendaException Caso a encomenda não exista
     * @throws UtilizadorException Caso o utilizador não exista
     * @throws ArtigoException Caso o artigo não exista
     */
    public void adicionaArtigoEncomenda(Artigo artigo, String email) throws EncomendaException, UtilizadorException, ArtigoException {
        if (this.listaUtilizadores.containsKey(email))
        {
            List<Encomenda> encomendas = this.listaEncomendas.stream().filter(encomenda -> encomenda.getComprador().getEmail().equals(email) && encomenda.getVendedor().getId() == artigo.getVendedor().getId() && encomenda.getEstado() == Atributos.PENDENTE && encomenda.getTransportadora().getNome().equals(artigo.getTransportadora().getNome())).collect(Collectors.toList());
            if (!encomendas.isEmpty())
            {
                Encomenda encomenda = encomendas.get(0);
                encomenda.adicionaArtigo(this.procuraArtigo(artigo.getId()));
            }
            else
            {
                Encomenda encomenda = new Encomenda(this.listaEncomendas.size() + 1, this.getDataAtual(), this.procuraUtilizador(email) ,this.procuraUtilizador(artigo.getVendedor().getEmail()));
                encomenda.adicionaArtigo(artigo);
                this.procuraUtilizador(email).adicionaEncomenda(encomenda);
                this.adicionaEncomenda(encomenda,email);
            }
        } else throw new UtilizadorException("Utilizador não existente!");
    }

    /**
     * Adiciona um artigo a uma encomenda
     * @param idArtigo Id do artigo
     * @param email Email do utilizador que faz a encomenda
     * @throws EncomendaException Caso a encomenda não exista
     * @throws UtilizadorException Caso o utilizador não exista
     * @throws ArtigoException Caso o artigo não exista
     */
    public void adicionaArtigoEncomenda(String idArtigo, String email) throws EncomendaException, UtilizadorException, ArtigoException {
        if (this.listaUtilizadores.containsKey(email))
        {
            Artigo artigo = this.procuraArtigo(idArtigo);
            List<Encomenda> encomendas = this.listaEncomendas.stream().filter(encomenda -> encomenda.getComprador().getEmail().equals(email) && encomenda.getVendedor().getId() == artigo.getVendedor().getId() && encomenda.getEstado() == Atributos.PENDENTE && encomenda.getTransportadora().getNome().equals(artigo.getTransportadora().getNome())).collect(Collectors.toList());
            if (!encomendas.isEmpty())
            {
                Encomenda encomenda = encomendas.get(0);
                encomenda.adicionaArtigo(this.procuraArtigo(artigo.getId()));
            }
            else
            {
                Encomenda encomenda = new Encomenda(this.listaEncomendas.size() + 1, this.getDataAtual(), this.procuraUtilizador(email) ,this.procuraUtilizador(artigo.getVendedor().getEmail()));
                encomenda.adicionaArtigo(artigo);
                this.procuraUtilizador(email).adicionaEncomenda(encomenda);
                this.adicionaEncomenda(encomenda,email);
            }
        } else throw new UtilizadorException("Utilizador não existente!");
    }

    /**
     * Remove um artigo de uma encomenda
     * @param artigo Um artigo
     * @param email Email do utilizador que faz a encomenda
     * @throws EncomendaException Caso a encomenda não exista
     * @throws UtilizadorException Caso o utilizador não exista
     */
    public void removeArtigoEncomenda(Artigo artigo, String email) throws EncomendaException, UtilizadorException {
        if (this.listaUtilizadores.containsKey(email))
        {
            List<Encomenda> encomendas = this.listaEncomendas.stream().filter(encomenda -> encomenda.getComprador().getEmail().equals(email) && encomenda.getVendedor().getEmail().equals(artigo.getVendedor().getEmail()) && encomenda.getEstado() == Atributos.PENDENTE && encomenda.getTransportadora().getNome().equals(artigo.getTransportadora().getNome())).collect(Collectors.toList());
            if (!encomendas.isEmpty())
            {
                Encomenda encomenda = encomendas.get(0);
                encomenda.removeArtigo(artigo);

                if (encomenda.getListaArtigos().isEmpty())
                {
                    encomenda.getComprador().removeEncomenda(encomenda);
                    this.removeEncomenda(encomenda);
                }
            } else throw new EncomendaException("Encomenda não existe!");
        } else throw new UtilizadorException("Utilizador não existente!");
    }

    /**
     * Remove um artigo de uma encomenda
     * @param idArtigo Id de um artigo
     * @param email Email do utilizador que faz a encomenda
     * @throws EncomendaException Caso a encomenda não exista
     * @throws UtilizadorException Caso o utilizador não exista
     * @throws ArtigoException Caso o artigo não exista
     */
    public void removeArtigoEncomenda(String idArtigo, String email) throws EncomendaException, UtilizadorException, ArtigoException {
        if (this.listaUtilizadores.containsKey(email))
        {
            Artigo artigo = this.procuraArtigo(idArtigo);
            List<Encomenda> encomendas = this.listaEncomendas.stream().filter(encomenda -> encomenda.getComprador().getEmail().equals(email) && encomenda.getVendedor().getEmail().equals(artigo.getVendedor().getEmail()) && encomenda.getEstado() == Atributos.PENDENTE && encomenda.getTransportadora().getNome().equals(artigo.getTransportadora().getNome())).collect(Collectors.toList());
            if (!encomendas.isEmpty())
            {
                Encomenda encomenda = encomendas.get(0);
                encomenda.removeArtigo(this.procuraArtigo(idArtigo));

                if (encomenda.getListaArtigos().isEmpty())
                {
                    encomenda.getComprador().removeEncomenda(encomenda);
                    this.removeEncomenda(encomenda);
                }

            } else throw new EncomendaException("Encomenda não existe!");
        } else throw new UtilizadorException("Utilizador não existente!");
    }

    /**
     * Confirma a seleção de artigos de uma encomenda, passando o seu estado de PENDENTE para EXPEDIDO
     * @param idEncomenda Id da encomenda
     * @param email Email do utilizador que faz a encomenda
     * @throws UtilizadorException Caso o utilizador não exista
     * @throws EncomendaException Caso a encomenda não exista
     * @throws TransportadoraException Caso a transportadora não exista
     */
    public void confirmaEncomenda(int idEncomenda, String email) throws UtilizadorException, EncomendaException, TransportadoraException {
        if (this.listaUtilizadores.containsKey(email))
        {
            Encomenda encomenda = this.procuraEncomenda(idEncomenda);
            if (encomenda.getEstado() == Atributos.PENDENTE)
            {

                encomenda.alteraEstadoExpedido(this.getDataAtual());
                this.emiteFatura(encomenda,email);
                this.procuraTransportadora(encomenda.getTransportadora().getNome()).adicionaValorGanho(encomenda.calculaValorExpedicao());
            }
        }
    }

    /**
     * Devolve uma encomenda, passado o seu estado de FINALIZADO para DEVOLVIDO
     * @param email Email do Utilizador que faz a encomenda
     * @param idEncomenda Id da encomenda
     * @throws UtilizadorException Caso o utilizador não exista
     * @throws EncomendaException Caso a encomenda não exista ou não possa ser devolvida.
     */
    public void devolveEncomenda(String email, int idEncomenda) throws UtilizadorException, EncomendaException {
        if (this.listaUtilizadores.containsKey(email))
        {
            List<Encomenda> encomendas = this.listaEncomendas.stream().filter(encomenda -> encomenda.getComprador().getEmail().equals(email) && encomenda.getId() == idEncomenda && encomenda.getEstado() == Atributos.FINALIZADA).collect(Collectors.toList());;
            if (!encomendas.isEmpty())
            {
                Encomenda encomenda = encomendas.get(0);
                if (this.getDataAtual().compareTo(encomenda.getDataAtualizacao()) <= this.getTempoDevolucao())
                {
                   encomenda.alteraEstadoDevolvida(this.getDataAtual());
                } else throw new EncomendaException("Esta encomenda não pode ser devolvida!");
            } else throw new EncomendaException("Encomenda não encontrada!");
        } else throw new UtilizadorException("Utilizador não encontrado!");
    }


    /**
     * Atualiza a data do sistema, baseado no numero de dias, reais, que se passaram
     */
    public void atualizaData() {
        int diferenca = (int) this.getDataUltimoAcesso().until(LocalDate.now(), ChronoUnit.DAYS);
        this.setDataAtual(this.getDataAtual().plusDays(diferenca));
        this.setDataUltimoAcesso(LocalDate.now());
    }

    /**
     * Função que avança a data do sistema em um numero de dias.
     * @param dias Numero de dias a avançar.
     */
    public void saltaTempo(int dias) {
        this.setDataAtual(this.getDataAtual().plusDays(dias));
        this.atualizaSistema();
    }

    /**
     * Função que acança a data do sistema para uma data especifica
     * @param ano Ano
     * @param mes mes
     * @param dia dia
     * @throws SistemaException Caso a data inserida seja anterior à data atual.
     */
    public void saltaTempo(int ano, int mes, int dia) throws SistemaException {
        LocalDate dataInput = LocalDate.of(ano, mes, dia);
        if (dataInput.isAfter(this.getDataAtual())) {
            this.setDataAtual(dataInput);
            this.atualizaSistema();
        } else throw new SistemaException("Data inserida não pode ser anterior à data atual!");
    }

    /**
     * Função que atualiza as encomendas
     */
    public void atualizaEncomendas()
    {
        this.listaEncomendas.stream().filter(encomenda -> encomenda.getEstado() == Atributos.EXPEDIDA).forEach(encomenda -> encomenda.alteraEstadoFinalizado(this.getDataAtual()));
    }

    /**
     * Função que atualiza o sistema baseado na data de acesso ao sistema
     */
    public void atualizaSistema() { //TODO Função que atualiza o sistema: Entregar encomendas, diminuit stock, e emissão de fatura para cada comprador/vendedor
        this.atualizaEncomendas();
        this.atualizaData();
    }

    /**
     * Função que emite as faturas para os compradores/vendedores de uma dada encomenda
     * @param encomenda Uma encomenda
     * @param emailComprador Email do utilizador que fez a encomenda
     * @throws UtilizadorException Caso o utilizador não exista
     */
    public void emiteFatura(Encomenda encomenda, String emailComprador) throws UtilizadorException {

        Utilizador vendedor = this.procuraUtilizador(encomenda.getVendedor().getEmail());
        Utilizador comprador = this.procuraUtilizador(emailComprador);

        Fatura faturaComprador = new Fatura(encomenda.getId(), comprador, Atributos.VENDIDO, encomenda.calculaValorArtigos(), encomenda.calculaTaxaArtigos(), encomenda.calculaValorExpedicao(), this.getDataAtual());
        Fatura faturaVendedor = new Fatura(encomenda.getId(), vendedor, Atributos.VENDA, encomenda.calculaValorArtigos(), 0,0, this.getDataAtual());

        vendedor.adicionaFatura(faturaVendedor);
        comprador.adicionaFatura(faturaComprador);

        this.adicionaFatura(faturaComprador);
        this.adicionaFatura(faturaVendedor);
    }


    /**
     * Função que procura o vendedor que mais faturou desde sempre
     * @return Um utilizador
     */
    public Utilizador vendedorMaisFaturouSempre() //Querie 1
    {
        Comparator<Utilizador> comparador = (Utilizador utilizador1, Utilizador utilizador2) -> {
            if (utilizador1.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == Atributos.VENDA).mapToDouble(Fatura::getValorTotal).sum() >
                    utilizador2.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == Atributos.VENDA).mapToDouble(Fatura::getValorTotal).sum()) return -1;
            if (utilizador1.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == Atributos.VENDA).mapToDouble(Fatura::getValorTotal).sum() <
                    utilizador2.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == Atributos.VENDA).mapToDouble(Fatura::getValorTotal).sum()) return 1;
            return 0;
        };
        return listaUtilizadores.values().stream().sorted(comparador).collect(Collectors.toList()).get(0);
    }

    /**
     * Função procura um vendedor que mais faturou entre datas
     * @param primeiraData Primeira data
     * @param segundaData Segunda data
     * @return Um utilizador
     * @throws SistemaException Caso não existam utilizadores com faturação entre as datas
     */
    public Utilizador vendedorMaisFaturouEntreDatas(LocalDate primeiraData, LocalDate segundaData) throws SistemaException //Querie 1
    {
        List<Utilizador> utilizadores = this.maioresUtilizadoresEntreDatas(primeiraData,segundaData,Atributos.VENDA);
        if (!utilizadores.isEmpty())
        {
            return utilizadores.get(0);
        } else throw new SistemaException("Não existe utilizadores com faturação entre as datas!");
    }

    /**
     * Proura a transportadora que mais faturou
     * @return Uma transportadora
     */
    public Transportadora transportadoraMaiorFaturacao() //Querie 2
    {
        Comparator<Transportadora> comparator = (Transportadora trans1, Transportadora trans2) -> {
            if (trans1.getValorFaturado() > trans2.getValorFaturado()) return -1;
            if (trans1.getValorFaturado() < trans2.getValorFaturado()) return  1;
            return 0;
        };
        //TODO Verificação se não houver transportadora com faturação.
        return this.getListaTransportadoras().values().stream().sorted(comparator).collect(Collectors.toList()).get(0);
    }


    /**
     * Procura as encomendas de um vendedor
     * @param emailVendedor Email de um utilizdor
     * @return Lista de Encomendas
     */
    public List<Encomenda> listaEncomendasVendedor(String emailVendedor) // Querie 3
    {
        return this.listaEncomendas.stream().filter(encomenda -> encomenda.getVendedor().getEmail().equals(emailVendedor) && encomenda.getEstado() == Atributos.FINALIZADA).collect(Collectors.toList());
    }

    /**
     * Função que devolve uma lista dos utilizadores que mais faturaram entre datas
     * @param primeiraData Primeira data
     * @param segundaData Segunda data
     * @param tipoVenda Tipo de venda, VENDA ou VENDIDO
     * @return Lista de utilizadores.
     */
    private List<Utilizador> utilizadoresFaturaramEntreDatas(LocalDate primeiraData, LocalDate segundaData, int tipoVenda)
    {
        return this.listaUtilizadores.values().stream()
                .filter(utilizador -> utilizador.getListaFaturas().stream().filter(fatura -> ((fatura.getDataFaturacao().isAfter(primeiraData) && fatura.getDataFaturacao().isBefore(segundaData)) || (fatura.getDataFaturacao().equals(primeiraData) && fatura.getDataFaturacao().equals(segundaData))) && fatura.getTipo() == tipoVenda).collect(Collectors.toList()).size() > 0).collect(Collectors.toList());
    }

    /**
     * Devolve uma lista de utilizadores ordenados pelo volume de compras/vendas entre datas.
     * @param primeiraData
     * @param segundaData
     * @param tipoVenda
     * @return
     */
    public List<Utilizador> maioresUtilizadoresEntreDatas(LocalDate primeiraData, LocalDate segundaData, int tipoVenda)
    {
        Comparator<Utilizador> comparador = (Utilizador utilizador1, Utilizador utilizador2) -> {
          if (utilizador1.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == tipoVenda).collect(Collectors.toList()).size() >
          utilizador2.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == tipoVenda).collect(Collectors.toList()).size()) return -1;
          if (utilizador1.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == tipoVenda).collect(Collectors.toList()).size() <
                  utilizador2.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == tipoVenda).collect(Collectors.toList()).size()) return 1;
          return 0;
        };
        return this.utilizadoresFaturaramEntreDatas(primeiraData,segundaData, tipoVenda).stream().sorted(comparador).collect(Collectors.toList());
    }



    /**
     * Quanto dinheiro ganhou a Vintage
     * @return Valor que a Vintage ganhou
     */
    public double ganhoVintage() //Querie 5
    {
        return this.listaEncomendas.stream().filter(encomenda -> encomenda.getEstado() != Atributos.PENDENTE).mapToDouble(encomenda -> encomenda.calculaTaxaArtigos()).sum();
    }


    /**
     * Verifica se um utilizador existe
     * @param email Email de um utilizador
     * @return True se o utilizdor existir, False se o utilizador não existir
     */
    public boolean verificaUtilizador(String email) throws UtilizadorException {

        if (listaUtilizadores.containsKey(email)){
            return true;
        }
        else throw new UtilizadorException("Este Utilizador ou email não existe!!");
    }

    /**
     * Verifica se uma transportadora existe
     * @param nome Nome da transportadora
     * @return True se a utilizadora exista, False se a transportadora não existir
     */
    public boolean verificaTransportadora(String nome)
    {
        return (listaTransportadoras.containsKey(nome));
    }

    /**
     * Verifica a password de um utilizador
     * @param email Email do utilizador
     * @param password Password do utilizador
     * @return True se a password é a mesma do utilizador
     * @throws UtilizadorException Caso o utilizador não exista
     */
    public boolean verificaPassword(String email, String password) throws UtilizadorException
    {
        Utilizador utilizador = procuraUtilizador(email);
        if (password.equals(utilizador.getPalavraPasse())){
            return true;
        }
        else throw new UtilizadorException("Password Incorreta!!");
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
        Sistema sistema = (Sistema) o;
        return this.getListaUtilizadores().equals(sistema.getListaUtilizadores()) &&
                this.getListaTransportadoras().equals(sistema.getListaTransportadoras()) &&
                this.getListaArtigos().equals(sistema.getListaArtigos()) &&
                this.getListaEncomendas().equals(sistema.getListaEncomendas()) &&
                this.getListaFaturas().equals(sistema.getListaFaturas()) &&
                this.getDataCriacao().equals(sistema.getDataCriacao()) &&
                this.getDataUltimoAcesso().equals(sistema.getDataUltimoAcesso()) &&
                this.getDataAtual().equals(sistema.getDataAtual()) &&
                this.getTaxas().equals(sistema.getTaxas()) &&
                this.getTempoDevolucao() == sistema.getTempoDevolucao();
    }

    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append("[Sistema]\n");
        string.append("[Lista Utilizadores]\n" + this.getListaUtilizadores().toString());
        string.append("\n[Lista de Transportadoras]\n" + this.getListaTransportadoras().toString());
        string.append("\n[Lista de Artigos]\n" + this.getListaArtigos().toString());
        string.append("\n[Lista de Encomendas]\n" + this.getListaEncomendas().toString());
        string.append("\n[Lista de Faturas]\n" + this.getListaFaturas().toString());
        string.append("\nData de criação do sistema: " + this.getDataCriacao());
        string.append("\nData do ultimo acesso ao sistema: " + this.getDataUltimoAcesso());
        string.append("\nData atual do sistema: " + this.getDataAtual());
        string.append("\n"+this.getTaxas().toString());
        string.append("\nTempo de devolução: " + this.getTempoDevolucao());
        return string.toString();
    }

    public Sistema clone()
    {
        return new Sistema(this);
    }
}
