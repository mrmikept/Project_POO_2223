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
    private Map<Integer, Encomenda> listaEncomendas;
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
        this.listaEncomendas = new HashMap<>();
        this.listaFaturas = new ArrayList<>();
        this.dataCriacao = LocalDate.now();
        this.dataAtual = LocalDate.now();
        this.dataUltimoAcesso = LocalDate.now();
        this.taxas = new TaxasImpostos();
        this.tempoDevolucao = TEMPODEVOLUCAO_OMISSAO;
    }

    public Sistema(Map<String, Utilizador> listaUtilizadores, Map<String, Transportadora> listaTransportadoras, Map<String, Artigo> listaArtigos, Map<Integer,Encomenda> listaEncomendas, List<Fatura> listaFaturas, LocalDate dataCriacao, LocalDate dataAtual, LocalDate dataUltimoAcesso, TaxasImpostos taxas, int tempoDevolucao) {
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
        this.taxas = sistema.getTaxas();
    }

    /////////////////////
    //Getters e Setters//
    ////////////////////

    public Map<String, Utilizador> getListaUtilizadores() {
        return listaUtilizadores.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
    }

    public void setListaUtilizadores(Map<String, Utilizador> listaUtilizadores) {
        this.listaUtilizadores = listaUtilizadores.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
    }

    public Map<String, Transportadora> getListaTransportadoras() {
        return listaTransportadoras.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
    }

    public void setListaTransportadoras(Map<String, Transportadora> listaTransportadoras) {
        this.listaTransportadoras = listaTransportadoras.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
    }

    public Map<String, Artigo> getListaArtigos() {
        return this.listaArtigos.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
    }

    public void setListaArtigos(Map<String, Artigo> listaArtigos) {
        this.listaArtigos = listaArtigos.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
    }

    public Map<Integer, Encomenda> getListaEncomendas() {
        return listaEncomendas.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, encomenda -> encomenda.getValue().clone()));
    }

    public void setListaEncomendas(Map<Integer, Encomenda> listaEncomendas) {
        this.listaEncomendas = listaEncomendas.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, encomenda -> encomenda.getValue().clone()));
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
     */
    public Map<String, Artigo> getArtigosVenda(String email) {
        return listaArtigos.entrySet().stream().filter(artigo -> !artigo.getValue().getEmailVendedor().equals(email) && artigo.getValue().getEstadoVenda() == Atributos.VENDA).collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
    }

    /**
     * Devolve os artigos à venda de um utilizador
     * @param email Email de um utilizador
     * @return Hashmap de Artigos à venda
     */
    public Map<String, Artigo> getArtigosVendaUtilizador(String email) {
        return listaArtigos.entrySet().stream().filter(artigo -> artigo.getValue().getEmailVendedor().equals(email) && artigo.getValue().getEstadoVenda() == Atributos.VENDA).collect(Collectors.toMap(Map.Entry::getKey, e->e.getValue().clone()));
    }

    /**
     * Verifica se um utilizador possui artigos há venda
     * @param email Email de um utilizador
     * @return true se o utilizador tiver artigos há venda, false se não tiver artigos há venda.
     * @throws UtilizadorException Caso o utilizador não exista.
     */
    public boolean verificaArtigoUtilizador(String email, String id) throws UtilizadorException {
        return (!this.procuraUtilizadorSistema(email).getListaArtigos().values().stream().filter(artigo -> artigo.getEstadoVenda() == Atributos.VENDA && artigo.getId().equals(id)).collect(Collectors.toList()).isEmpty());
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
        if (!this.listaTransportadoras.containsKey(transportadora.getEmail()) && this.verificaNomeTransportadora(transportadora.getNome())) { //TODO VERIFICAR NOME TRANSPORTADORA
            this.listaTransportadoras.put(transportadora.getEmail(), transportadora.clone());
        } else {
            throw new TransportadoraException("A Transportadora " + transportadora.getNome().toUpperCase() + " já existe!");
        }
    }

    /**
     * Adiciona uma transportadora à lista de transportadoras do Sistema
     * @param nome  Nome da transportadora
     * @param lucro Margem de lucro da Transportadora
     * @param tipo  Tipo de transportadora
     * @throws TransportadoraException Caso a Transportadora já exista
     */
    public void adicionaTransportadora(String email, String palavraPasse, String nome, String morada, int nrFiscal, double lucro, int tipo, int tempExpedicao) throws TransportadoraException {
        if (!this.listaTransportadoras.containsKey(email)) {
            if (!this.verificaNomeTransportadora(nome.toUpperCase()))
            {
                int id = this.listaTransportadoras.size() + 1;
                Transportadora transportadora = new Transportadora(id, email, palavraPasse, nome.toUpperCase(), morada, nrFiscal, lucro, tipo, tempExpedicao, 0, this.getTaxas());
                this.listaTransportadoras.put(email, transportadora);
            } else throw new TransportadoraException("A Transportadora com o nome " + nome.toUpperCase() + " já existe!");
        } else {
            throw new TransportadoraException("A Transportadora com o email " + email + " já existe!");
        }
    }

    /**
     * Adiciona um artigo ao sistema.
     * @param artigo Artigo a adicionar ao sistema
     * @throws ArtigoException Caso o artigo já exista
     * @throws UtilizadorException Caso o utilizador não exista
     */
    public void adicionaArtigo(Artigo artigo) throws ArtigoException, UtilizadorException {
        if (!this.listaArtigos.containsKey(artigo.getId())) {
            Utilizador utilizador = this.procuraUtilizadorSistema(artigo.getEmailVendedor());
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
            Utilizador utilizador = this.procuraUtilizadorSistema(artigo.getEmailVendedor());
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
            Utilizador utilizador = this.procuraUtilizadorSistema(artigo.getEmailVendedor());
            this.listaArtigos.remove(artigo.getId(), artigo);
            utilizador.removeArtigo(artigo);
        }
        else throw new ArtigoException("Este artigo não existe!!");
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
            if (this.verificaUtilizador(email))
            {
                if (this.procuraTransportadora(transportadora).getTipo() == 0)
                {
                    Tshirt tshirt = new Tshirt(id.toUpperCase(), email, descricao, marca, precoBase, nrDonos, avaliacao, this.procuraTransportadora(transportadora), Atributos.VENDA, tamanho, padrao);
                    this.listaArtigos.put(id,tshirt);
                    this.listaUtilizadores.get(email).adicionaArtigo(this.listaArtigos.get(id));
                } else throw new ArtigoException("O TIPO DA TRANSPORTADORA NÃO CORRESPONDE AO TIPO DO ARTIGO!");
            }
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
     * @param anoLancamento Data de lançamento da sapatilha
     * @param tipo Tipo de sapatilha, Normal ou Premium
     * @throws ArtigoException Caso o artigo já exista
     * @throws UtilizadorException Caso o utilizador não exista
     * @throws TransportadoraException Caso a transportadora não exista
     */
    public void adicionaSapatilhaVenda(String id, String email, String descricao, String marca, double precoBase, double avaliacao, int nrDonos, String transportadora, int tamanho, int tipoCordao, String cor, int anoLancamento, int tipo) throws ArtigoException, UtilizadorException, TransportadoraException {
        if (!this.listaArtigos.containsKey(id)) {
            if (this.verificaUtilizador(email))
            {
                if (this.procuraTransportadora(transportadora).getTipo() == tipo)
                {
                    if (tipo == Atributos.PREMIUM && anoLancamento >= this.getDataAtual().getYear()) throw new ArtigoException("Artigos Premium não podem possuir data de lançamento superior ou igual ao ano atual!");
                    Sapatilha sapatilha = new Sapatilha(id.toUpperCase(), email, descricao, marca, precoBase, nrDonos, avaliacao, this.procuraTransportadora(transportadora).clone(), Atributos.VENDA, tamanho, tipoCordao, cor, anoLancamento, tipo);
                    this.listaArtigos.put(id, sapatilha);
                    this.listaUtilizadores.get(email).adicionaArtigo(this.listaArtigos.get(id));
                } else throw new ArtigoException("O TIPO DA TRANSPORTADORA NÃO CORRESPONDE AO TIPO DO ARTIGO!");
            }
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
            if (this.verificaUtilizador(email))
            {
                if (this.procuraTransportadora(transportadora).getTipo() == tipo)
                {
                    if (tipo == Atributos.PREMIUM && anoLancamento >= this.getDataAtual().getYear()) throw new ArtigoException("Artigos Premium não podem possuir data de lançamento superior ou igual ao ano atual!");
                    Mala mala = new Mala(id.toUpperCase(), email, descricao,marca,precoBase, nrDonos, avaliacao, this.procuraTransportadora(transportadora).clone(), Atributos.VENDA, dimensao, material, anoLancamento, tipo);
                    this.listaArtigos.put(id,mala);
                    this.listaUtilizadores.get(email).adicionaArtigo(this.listaArtigos.get(id));
                } else throw new ArtigoException("O TIPO DA TRANSPORTADORA NÃO CORRESPONDE AO TIPO DO ARTIGO!");
            }
        } else {
            throw new ArtigoException("Este Artigo já está à venda");
        }
    }


    /**
     * Devolve um utilizador
     * @param email Email do utilizador
     * @return Um utilizador
     * @throws UtilizadorException Caso o utilizador não exista
     */
    private Utilizador procuraUtilizadorSistema(String email) throws UtilizadorException {
        if (listaUtilizadores.containsKey(email)) {
            return listaUtilizadores.get(email);
        } else {
            throw new UtilizadorException("O utilizador com o email:" + email + "não encontrado");
        }
    }

    /**
     * Devolve uma copia de um utilizador
     * @param email Email de um Utilizador
     * @return Um Utilizador
     * @throws UtilizadorException
     */
    public Utilizador procuraUtilizador(String email) throws UtilizadorException {
        if (listaUtilizadores.containsKey(email)) {
            return listaUtilizadores.get(email).clone();
        } else {
            throw new UtilizadorException("O utilizador com o email:" + email + "não encontrado");
        }
    }

    /**
     * Devolve a copia de uma transportadora pelo nome
     * @param nome Nome da transportadora
     * @return Uma transportadora
     * @throws TransportadoraException Caso a transportadora não exista
     */
    public Transportadora procuraTransportadoraNome(String nome) throws TransportadoraException {
        List<Transportadora> transportadoras = this.listaTransportadoras.values().stream().filter(transportadora -> transportadora.getNome().equals(nome.toUpperCase())).collect(Collectors.toList());
        if (!transportadoras.isEmpty())
        {
            return transportadoras.get(0).clone();
        } else {
            throw new TransportadoraException("A Transportadora com o nome, " + nome + " não existe!");
        }
    }

    /**
     * Devolve a copia de uma transportadora
     * @param email email da Transportadora
     * @return Uma transportadora
     * @throws TransportadoraException Caso a transportadora não exista!
     */
    public Transportadora procuraTransportadoraEmail(String email) throws TransportadoraException {
        if (this.listaTransportadoras.containsKey(email))
        {
            return this.listaTransportadoras.get(email).clone();
        } else throw new TransportadoraException("A Transportadora com o email " + email + " não foi encontrada!");
    }

    /**
     * Devolve a referencia de uma transportadora
     * @param nome Nome da Transportadora
     * @return Uma transportadora
     * @throws TransportadoraException Caso a transportadora não exista!
     */
    private Transportadora procuraTransportadora(String nome) throws TransportadoraException {
        List<Transportadora> transportadoras = this.listaTransportadoras.values().stream().filter(transportadora -> transportadora.getNome().equals(nome)).collect(Collectors.toList());
        if (!transportadoras.isEmpty())
        {
            return transportadoras.get(0);
        } else {
            throw new TransportadoraException("A Transportadora com o nome, " + nome + " não existe!");
        }
    }

    /**
     * Altera a margem de lucro de uma transportadora do Sistema
     * @param email email da Transportadora
     * @param margemLucro Margem de lucro
     * @throws TransportadoraException
     */
    public void alteraMargemLucroTransportadora(String email, double margemLucro) throws TransportadoraException {
        if (this.listaTransportadoras.containsKey(email))
        {
            this.listaTransportadoras.get(email).setMargemLucro(margemLucro);
        } else throw new TransportadoraException("A transportadora com o email " + email + " não foi encontrada!");
    }

    /**
     * Altera o tempo de expedicao de uma transportadora
     * @param email email da transportadora
     * @param tempoExpedicao Tempo de expedicao em dias
     * @throws TransportadoraException
     */
    public void alteraTempoExpedicaoTransportadora(String email, int tempoExpedicao) throws TransportadoraException {
        if (this.listaTransportadoras.containsKey(email))
        {
            this.listaTransportadoras.get(email).setTempoExpedicao(tempoExpedicao);
        } else throw new TransportadoraException("A transportadora com o email " + email + " não foi encontrada!");
    }

    /**
     * Procura um artigo
     * @param id Id do artigo
     * @return Um artigo
     * @throws ArtigoException Caso o artigo não exista
     */
    private Artigo procuraArtigo(String id) throws ArtigoException {
        if (listaArtigos.containsKey(id.toUpperCase())) {
            return listaArtigos.get(id.toUpperCase());
        } else {
            throw new ArtigoException("O artigo com o id " + id + " não existe");
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
                return artigo.clone();
            }
            else throw new ArtigoException("Este artigo não está à venda!");
        } else throw new ArtigoException("Este artigo não existe!");
    }

    /**
     * Verifica se um artigo se encontra à venda pelo seu identificador.
     * @param id Id do artigo
     * @return True caso o artigo esteja à venda, False se o artigo não estiver à venda.
     * @throws ArtigoException Caso o artigo não exista.
     */
    public boolean verificaArtigoVenda(String id) throws ArtigoException
    {
        if(listaArtigos.containsKey(id)){
            Artigo artigo = this.listaArtigos.get(id);
            return artigo.getEstadoVenda() == Atributos.VENDA;
        }
        else throw new ArtigoException("ESTE ARTIGO NÃO ESTÁ À VENDA!!");
    }

    /**
     * Procura uma encomenda
     * @param id Id da encomenda
     * @return Uma encomenda
     * @throws EncomendaException Caso a encomenda não pertença ao utilizador
     */
    public Encomenda procuraEncomendaComprador(int id, String email) throws EncomendaException, UtilizadorException {
        if (this.verificaEncomenda(email,id))
        {
            Encomenda encomenda = this.procuraEncomendaSistema(id);
            if (encomenda.getComprador().equals(email))
            {
                return this.listaEncomendas.get(id).clone();
            } else throw new EncomendaException("ENCOMENDA NÃO ENCONTRADA OU NÃO LHE PERTENCE!");
        } else throw new EncomendaException("ENCOMENDA NÃO ENCONTRADA OU NÃO LHE PERTENCE!");
    }

    /**
     * Devolve a referencia de uma encomenda no sistema
     * @param id Id da encomenda
     * @return Uma encomenda
     * @throws EncomendaException Caso a encomenda não exista
     */
    private Encomenda procuraEncomendaSistema(int id) throws EncomendaException {
        if (this.listaEncomendas.containsKey(id))
        {
            return this.listaEncomendas.get(id);
        } else throw new EncomendaException("ENCOMENDA NÃO ENCONTRADA!!");
    }

    /**
     * Procura uma encomenda pelo seu vendedor
     * @param id Id da encomenda
     * @param email email do vendedor
     * @return Uma encomenda
     * @throws EncomendaException Caso a encomenda não exista ou não pertenca ao utilizador
     * @throws UtilizadorException Caso o utilizador não seja encontrado
     */
    public Encomenda procuraEncomendaVendedor(int id, String email) throws EncomendaException, UtilizadorException {
        if (this.verificaEncomenda(email,id))
        {
            Encomenda encomenda = this.procuraEncomendaSistema(id);
            if (encomenda.getVendedor().equals(email))
            {
                return this.listaEncomendas.get(id).clone();
            } else throw new EncomendaException("ENCOMENDA NÃO ENCONTRADA OU NÃO LHE PERTENCE!");
        } else throw new EncomendaException("ENCOMENDA NÃO ENCONTRADA OU NÃO LHE PERTENCE!");
    }

    /**
     * Adiciona um encomenda
     * @param encomenda Uma encomenda
     * @param email Email do utilizador(comprador)
     * @throws UtilizadorException Caso o utilizador não exista
     * @throws EncomendaException Caso a encomenda já exista no sistema
     */
    private void adicionaEncomenda(Encomenda encomenda, String email) throws UtilizadorException, EncomendaException {
        if (this.listaUtilizadores.containsKey(email)) {
            if (!this.listaEncomendas.containsValue(encomenda)) {
                this.listaEncomendas.put(encomenda.getId(),encomenda);
            } else throw new EncomendaException("Encomenda já existente!");
        } else throw new UtilizadorException("Utilizador não encontrado!");
    }

    /**
     * Adiciona uma fatura
     * @param fatura Uma fatura
     */
    private void adicionaFatura(Fatura fatura)
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
        if (this.listaEncomendas.containsValue(encomenda)) {
            this.listaEncomendas.remove(encomenda.getId());
        } else throw new EncomendaException("Esta encomenda não existe no sistema!");
    }

    /**
     * Remove uma encomenda
     * @param idEncomenda Id da encomenda
     * @param email Email do utilizador que faz a encomenda
     */
    public void removeEncomenda(int idEncomenda, String email) {
        if (this.listaUtilizadores.containsKey(email)) {
            Encomenda encomenda = this.listaEncomendas.get(idEncomenda);
            this.listaEncomendas.remove(idEncomenda,encomenda);
        }
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
            List<Encomenda> encomendas = this.listaEncomendas.values().stream().filter(encomenda -> encomenda.getComprador().equals(email) && encomenda.getVendedor().equals(artigo.getEmailVendedor()) && encomenda.getEstado() == Atributos.PENDENTE && encomenda.getTransportadora().getNome().equals(artigo.getTransportadora().getNome())).collect(Collectors.toList());
            if (!encomendas.isEmpty())
            {
                Encomenda encomenda = encomendas.get(0);
                this.procuraArtigo(artigo.getId()).setEstadoVenda(Atributos.VENDIDO);
                encomenda.adicionaArtigo(this.procuraArtigo(artigo.getId()));
            }
            else
            {
                Encomenda encomenda = new Encomenda(this.listaEncomendas.size() + 1, this.getDataAtual(), this.procuraUtilizadorSistema(email).getEmail() ,this.procuraUtilizadorSistema(artigo.getEmailVendedor()).getEmail());
                this.procuraArtigo(artigo.getId()).setEstadoVenda(Atributos.VENDIDO);
                encomenda.adicionaArtigo(this.procuraArtigo(artigo.getId()));
                this.procuraUtilizadorSistema(email).adicionaEncomenda(encomenda);
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
    public void removeArtigoEncomenda(Artigo artigo, String email) throws EncomendaException, UtilizadorException, ArtigoException {
        if (this.listaUtilizadores.containsKey(email))
        {
            List<Encomenda> encomendas = this.listaEncomendas.values().stream().filter(encomenda -> encomenda.getComprador().equals(email) && encomenda.getVendedor().equals(artigo.getEmailVendedor()) && encomenda.getEstado() == Atributos.PENDENTE && encomenda.getTransportadora().getNome().equals(artigo.getTransportadora().getNome())).collect(Collectors.toList());
            if (!encomendas.isEmpty())
            {
                Encomenda encomenda = encomendas.get(0);
                this.procuraArtigo(artigo.getId()).setEstadoVenda(Atributos.VENDA);
                encomenda.removeArtigo(artigo);

                if (encomenda.getListaArtigos().isEmpty())
                {
                    this.listaUtilizadores.get(email).removeEncomenda(encomenda);
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
            List<Encomenda> encomendas = this.listaEncomendas.values().stream().filter(encomenda -> encomenda.getComprador().equals(email) && encomenda.getVendedor().equals(artigo.getEmailVendedor()) && encomenda.getEstado() == Atributos.PENDENTE && encomenda.getTransportadora().getNome().equals(artigo.getTransportadora().getNome())).collect(Collectors.toList());
            if (!encomendas.isEmpty())
            {
                Encomenda encomenda = encomendas.get(0);
                this.procuraArtigo(artigo.getId()).setEstadoVenda(Atributos.VENDA);
                encomenda.removeArtigo(this.procuraArtigo(idArtigo));

                if (encomenda.getListaArtigos().isEmpty())
                {
                    this.removeEncomenda(encomenda);
                    this.listaUtilizadores.get(email).removeEncomenda(encomenda);
                }

            } else throw new EncomendaException("Encomenda não existe!");
        } else throw new UtilizadorException("Utilizador não existente!");
    }

    /**
     * Confirma a seleção de artigos de uma encomenda, passando o seu estado de PENDENTE para EXPEDIDO
     * @param idEncomenda Id da encomenda
     * @param email Email do utilizador que faz a encomenda
     * @throws UtilizadorException Caso o utilizador não exista
     * @throws EncomendaException Caso a encomenda não exista ou não possa ser devolvida!
     * @throws TransportadoraException Caso a transportadora não exista
     */
    public void confirmaEncomenda(int idEncomenda, String email) throws UtilizadorException, EncomendaException, TransportadoraException {
        if (this.listaUtilizadores.containsKey(email))
        {
            if (this.verificaEncomenda(email,idEncomenda))
            {
                Encomenda encomenda = this.listaEncomendas.get(idEncomenda);
                if (encomenda.getComprador().equals(email) && encomenda.getEstado() == Atributos.PENDENTE)
                {
                    encomenda.alteraEstadoExpedido(this.getDataAtual());
                    this.listaEncomendas.put(idEncomenda,encomenda.clone());
                    this.emiteFatura(encomenda,email);
                    this.procuraTransportadora(encomenda.getTransportadora().getNome()).adicionaValorGanho(encomenda.calculaValorExpedicao());
                } else throw new EncomendaException("ENCOMENDA NÃO ENCONTRADA OU NÃO PODE SER CONFIRMADA!");
            }
        } else throw new UtilizadorException("Utilizador não encontrado!");
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
            List<Encomenda> encomendas = this.listaEncomendas.values().stream().filter(encomenda -> encomenda.getComprador().equals(email) && encomenda.getId() == idEncomenda && encomenda.getEstado() == Atributos.FINALIZADA).collect(Collectors.toList());
            if (!encomendas.isEmpty())
            {
                Encomenda encomenda = encomendas.get(0);
                if (this.getDataAtual().isBefore(encomenda.getDataAtualizacao().plusDays(this.tempoDevolucao)))
                {
                    encomenda.alteraEstadoDevolvida(this.getDataAtual());
                } else throw new EncomendaException("Esta encomenda não pode ser devolvida!");
            } else throw new EncomendaException("Encomenda não encontrada!");
        } else throw new UtilizadorException("Utilizador não encontrado!");
    }

    /**
     * Verifica o estado de uma encomenda do utilizador
     * @param email email do utilizador
     * @param idEncomenda Id da encomenda
     * @param estado Estado da encomenda
     * @return true se o estado corresponder ao passado por parametro, false caso contrario
     * @throws UtilizadorException Caso o utilizador não seja encontrado
     */
    public boolean verificaEstadoEncomenda(String email, int idEncomenda, int estado) throws UtilizadorException
    {
        return !this.procuraUtilizadorSistema(email).getListaEncomendas(estado).stream().filter(encomenda -> encomenda.getId() == idEncomenda && encomenda.getEstado() == estado).collect(Collectors.toList()).isEmpty();
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
     * Função que avança a data do sistema em um número de dias.
     * @param dias Numero de dias a avançar.
     */
    public void saltaTempo(int dias) {
        this.setDataAtual(this.getDataAtual().plusDays(dias));
        this.atualizaSistema();
    }

    /**
     * Função que acança a data do sistema para uma data específica
     * @param ano Ano
     * @param mes mes
     * @param dia dia
     * @throws SistemaException Caso a data inserida seja anterior à data atual.
     */
    public void saltaTempo(int ano, int mes, int dia) throws SistemaException {
        LocalDate dataInput = LocalDate.of(ano, mes, dia);
        if (dataInput.isAfter(this.getDataAtual()) || dataInput.equals(this.getDataAtual())) {
            this.setDataAtual(dataInput);
            this.atualizaSistema();
        } else throw new SistemaException("Data inserida não pode ser anterior à data atual!");
    }

    /**
     * Função que atualiza as encomendas
     */
    public void atualizaEncomendas()
    {
        this.listaEncomendas.values().stream().filter(encomenda -> encomenda.getEstado() == Atributos.EXPEDIDA).forEach(encomenda -> encomenda.alteraEstadoFinalizado(this.getDataAtual()));
    }

    /**
     * Função que atualiza o sistema baseado na data de acesso ao sistema
     */
    public void atualizaSistema() {
        this.atualizaEncomendas();
        this.atualizaData();
    }

    /**
     * Função que emite as faturas para os compradores/vendedores de uma dada encomenda
     * @param encomenda Uma encomenda
     * @param emailComprador Email do utilizador que fez a encomenda
     * @throws UtilizadorException Caso o utilizador não exista
     */
    private void emiteFatura(Encomenda encomenda, String emailComprador) throws UtilizadorException {

        Utilizador vendedor = this.procuraUtilizadorSistema(encomenda.getVendedor());
        Utilizador comprador = this.procuraUtilizadorSistema(emailComprador);

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
    public Utilizador vendedorMaisFaturouSempre() throws SistemaException//Querie 1
    {
        Comparator<Utilizador> comparador = (Utilizador utilizador1, Utilizador utilizador2) -> {
            if (utilizador1.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == Atributos.VENDA).mapToDouble(Fatura::getValorTotal).sum() >
                    utilizador2.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == Atributos.VENDA).mapToDouble(Fatura::getValorTotal).sum()) return -1;
            if (utilizador1.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == Atributos.VENDA).mapToDouble(Fatura::getValorTotal).sum() <
                    utilizador2.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == Atributos.VENDA).mapToDouble(Fatura::getValorTotal).sum()) return 1;
            return 0;
        };
        if(!listaUtilizadores.values().stream().sorted(comparador).collect(Collectors.toList()).isEmpty())
        {
            return listaUtilizadores.values().stream().sorted(comparador).collect(Collectors.toList()).get(0);
        } else throw new SistemaException("Não existem utilizadores com faturação!!");
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
    public Transportadora transportadoraMaiorFaturacao() throws SistemaException//Querie 2
    {
        Comparator<Transportadora> comparator = (Transportadora trans1, Transportadora trans2) -> Double.compare(trans2.getValorFaturado(), trans1.getValorFaturado());
        if (!this.getListaTransportadoras().values().stream().sorted(comparator).collect(Collectors.toList()).isEmpty()){
            return this.getListaTransportadoras().values().stream().sorted(comparator).collect(Collectors.toList()).get(0);
        }
        else throw new SistemaException("Não existem transportadoras com faturação!!");
    }
    
    /**
     * Procura as encomendas de um vendedor
     * @param emailVendedor Email de um utilizdor
     * @return Lista de Encomendas
     */
    public List<Encomenda> listaEncomendasVendedor(String emailVendedor) // Querie 3
    {
        return this.listaEncomendas.values().stream().filter(encomenda -> encomenda.getVendedor().equals(emailVendedor) && encomenda.getEstado() != Atributos.PENDENTE).map(Encomenda::clone).collect(Collectors.toList());
    }

    /**
     * Procura as encomendas de uma transportadora
     * @param email Email de uma transportadora
     * @return Lista de Encomendas
     */
    public List<Encomenda> listaEncomendaTransportadoras(String email){
        return this.listaEncomendas.values().stream().filter(encomenda -> encomenda.getTransportadora().getEmail().equals(email) && encomenda.getEstado() != Atributos.PENDENTE).map(Encomenda::clone).collect(Collectors.toList());
    }

    /**
     * Devolve uma lista de utilizadores ordenados pelo volume de compras/vendas entre datas.
     * @param primeiraData Data inicial
     * @param segundaData Data final
     * @param tipoVenda Tipo de venda (venda ou compra/vendido)
     * @return Array list de Utilizadores ordenados.
     */
    public List<Utilizador> maioresUtilizadoresEntreDatas(LocalDate primeiraData, LocalDate segundaData, int tipoVenda) //Querie 4
    {
        Comparator<Utilizador> comparador = (Utilizador utilizador1, Utilizador utilizador2) -> {
            if (utilizador1.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == tipoVenda).count() >
                    utilizador2.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == tipoVenda).count()) return -1;
            if (utilizador1.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == tipoVenda).count() <
                    utilizador2.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == tipoVenda).count()) return 1;
            return 0;
        };
        return this.listaUtilizadores.values().stream().filter(utilizador -> utilizador.getListaFaturas().stream().anyMatch((fatura -> ((fatura.getDataFaturacao().isAfter(primeiraData) && fatura.getDataFaturacao().isBefore(segundaData)) ||
                fatura.getDataFaturacao().equals(primeiraData)) && fatura.getTipo() == tipoVenda))).sorted(comparador).map(Utilizador::clone).collect(Collectors.toList());
    }



    /**
     * Quanto dinheiro ganhou a Vintage
     * @return Valor que a Vintage ganhou
     */
    public double ganhoVintage() //Querie 5
    {
        return this.listaEncomendas.values().stream().filter(encomenda -> encomenda.getEstado() != Atributos.PENDENTE).mapToDouble(Encomenda::calculaTaxaArtigos).sum();
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
    public boolean verificaNomeTransportadora(String nome) throws TransportadoraException {
        return !this.listaTransportadoras.values().stream().filter(transportadora -> transportadora.getNome().equals(nome)).collect(Collectors.toList()).isEmpty();
    }

    /**
     * Verifica se o email de uma transportadora já se encontra registrado no sistema
     * @param email email de uma transportadora
     * @return true se já estiver no sistema, false caso contrario
     * @throws TransportadoraException Caso a transportadora não exista
     */
    public boolean verificaEmailTransportadora(String email) throws TransportadoraException
    {
        if (this.listaTransportadoras.containsKey(email))
        {
            return true;
        } else throw new TransportadoraException("Esta Transportadora ou email não existe!!");
    }

    /**
     * Verifica a palavra-passe de uma transpotadora
     * @param email email da transportadora
     * @param palavraPasse palavra-passe
     * @return true se a palavra-passe for correta, false caso contrario
     * @throws TransportadoraException Caso a palavra-passe seja incorreta
     */
    public boolean verificaPasswordTransportadora(String email, String palavraPasse) throws TransportadoraException {
        if (this.listaTransportadoras.get(email).getPalavraPasse().equals(palavraPasse))
        {
            return true;
        } else throw new TransportadoraException("Password Incorreta!!");
    }

    /**
     * Verifica a password de um utilizador
     * @param email Email do utilizador
     * @param password Password do utilizador
     * @return True se a password é a mesma do utilizador
     * @throws UtilizadorException Caso o utilizador não exista
     */
    public boolean verificaPasswordUtilizador(String email, String password) throws UtilizadorException
    {
        Utilizador utilizador = procuraUtilizadorSistema(email);
        if (password.equals(utilizador.getPalavraPasse())){
            return true;
        }
        else throw new UtilizadorException("Password Incorreta!!");
    }

    /**
     * Verifica se uma encomenda do sistema pertecence a um comprador ou vendedor
     * @param email Email de um utilizador/Vendedor
     * @param idEncomenda Identificador de uma encomenda
     * @return true se a encomenda pertencer ao utilizador, false caso contrario.
     * @throws UtilizadorException Caso o utilizador não exista.
     * @throws EncomendaException Caso a encomenda não exista.
     */
    public boolean verificaEncomenda(String email, int idEncomenda) throws UtilizadorException, EncomendaException {
        if (this.listaUtilizadores.containsKey(email))
        {
            if (this.listaEncomendas.containsKey(idEncomenda))
            {
                return (this.listaEncomendas.get(idEncomenda).getComprador().equals(email) || this.listaEncomendas.get(idEncomenda).getVendedor().equals(email));
            } else throw new EncomendaException("ENCOMENDA NÃO ENCONTRADA!");
        } else throw new UtilizadorException("Utilizador não encontrado!");
    }


    /**
     * Metodo que verifica a igualdade entre objectos
     * @param o Um objeto
     * @return true se forem iguais, false caso contrario
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

    /**
     * Metodo toString do sistema
     * @return String com o conteudo do sistema
     */
    public String toString()
    {
        return "[Sistema]\n" +
                "[Lista Utilizadores]\n" + this.getListaUtilizadores().toString() +
                "\n[Lista de Transportadoras]\n" + this.getListaTransportadoras().toString() +
                "\n[Lista de Artigos]\n" + this.getListaArtigos().toString() +
                "\n[Lista de Encomendas]\n" + this.getListaEncomendas().toString() +
                "\n[Lista de Faturas]\n" + this.getListaFaturas().toString() +
                "\nData de criação do sistema: " + this.getDataCriacao() +
                "\nData do ultimo acesso ao sistema: " + this.getDataUltimoAcesso() +
                "\nData atual do sistema: " + this.getDataAtual() +
                "\n" + this.getTaxas().toString() +
                "\nTempo de devolução: " + this.getTempoDevolucao();
    }

    public Sistema clone()
    {
        return new Sistema(this);
    }
}
