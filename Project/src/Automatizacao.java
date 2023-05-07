import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Automatizacao {

    private String path;
    private List<String> excecoes;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public Automatizacao(){

        this.path = "";
        this.excecoes = new ArrayList<>();
    }

    public Automatizacao(String path){

        this.path = path;
        this.excecoes = new ArrayList<>();
    }

    public String getPath(){
        return this.path;
    }

    public void setPath(String path){
        this.path = path;
    }

    public List<String> getExcecoes(){
        return excecoes;
    }

    public void setExcecoes(List<String> excecoes){
        this.excecoes = excecoes;
    }

    public void carregaFicheiro(Sistema sistema) throws IOException{
        BufferedReader comeco = new BufferedReader(new FileReader(this.path));

        while(comeco.ready()){
            String linha = comeco.readLine();
            if(!linha.contains("--")){
                String[] aux = linha.split(":");

                if(linha.contains("Adiciona Utilizador:")){
                    try {
                        util(aux, sistema);
                    }
                    catch(UtilizadorException | SistemaException a){
                        this.excecoes.add(a.getMessage());
                    }
                }

                if(linha.contains("Adiciona Venda:")){
                    try{
                        artVenda(aux, sistema);
                    }
                    catch(ArtigoException | TransportadoraException | UtilizadorException | SistemaException a){
                        this.excecoes.add(a.getMessage());
                    }
                }

                if(linha.contains("Adiciona Compra:")){
                    try{
                        artCompra(aux, sistema);
                    }
                    catch(ArtigoException | UtilizadorException | EncomendaException | SistemaException a){
                        this.excecoes.add(a.getMessage());
                    }
                }

                if(linha.contains("Adiciona Transportadora:")){
                    try{
                        transp(aux, sistema);
                    }
                    catch(TransportadoraException | SistemaException a){
                        this.excecoes.add(a.getMessage());
                    }
                }

                if(linha.contains("Adiciona Encomenda:")){
                    try {
                        encom(aux, sistema);
                    }
                    catch (EncomendaException | ArtigoException | TransportadoraException | UtilizadorException |
                           SistemaException a){
                        this.excecoes.add(a.getMessage());
                    }
                }
            }
        }
        comeco.close();
    }

    public void util(String[] aux, Sistema sistema) throws UtilizadorException, SistemaException {
        String[] camposUtil = aux[1].split(",");
        LocalDate data = LocalDate.parse(camposUtil[0], formatter);
        if (sistema.getDataAtual().isBefore(data)){
            int ano = data.getYear();
            int mes = data.getMonthValue();
            int dia = data.getDayOfMonth();
            sistema.saltaTempo(ano, mes, dia);
        }
        String email = camposUtil[1];
        String palavraPasse = camposUtil[2];
        String nome = camposUtil[3];
        String morada = camposUtil[4];
        int nrFiscal = Integer.parseInt(camposUtil[5]);
        sistema.adicionaUtilizador(email, palavraPasse, nome, morada, nrFiscal);
    }
    

    public void artVenda(String[] aux, Sistema sistema) throws ArtigoException, TransportadoraException, UtilizadorException, SistemaException {
        String[] camposArt = aux[1].split(",");
        LocalDate data = LocalDate.parse(camposArt[0], formatter);
        if (sistema.getDataAtual().isBefore(data)){
           int ano = data.getYear();
           int mes = data.getMonthValue();
           int dia = data.getDayOfMonth();
           sistema.saltaTempo(ano, mes, dia);
        }
        String email = camposArt[2];
        int id = Integer.valueOf(camposArt[3]);
        String descricao = camposArt[4];
        String marca = camposArt[5];
        double precoBase = Double.parseDouble(camposArt[6]);
        if (camposArt[7].contains("NOVO")){

            String nomeTransportadora = camposArt[8];

            if(camposArt[1].contains("Mala")){
                double dimensao = Double.parseDouble(camposArt[9]);
                String material = camposArt[10];
                LocalDate anoLancamento = LocalDate.parse(camposArt[11]);
                int tipo = Integer.valueOf(camposArt[12]);
                sistema.adicionaMalaVenda(id, email, descricao, marca, precoBase, Atributos.NOVO, 0, 0, nomeTransportadora, dimensao, material, anoLancamento, tipo);
            }
            else if(camposArt[1].contains("Sapatilha")){
                int tamanho = Integer.valueOf(camposArt[9]);
                int tipoCordao = Integer.valueOf(camposArt[10]);
                String cor = camposArt[11];
                LocalDate dataLancamento = LocalDate.parse(camposArt[12]);
                int tipo = Integer.valueOf(camposArt[13]);
                sistema.adicionaSapatilhaVenda(id, email, descricao, marca, precoBase, Atributos.NOVO, 0, 0, nomeTransportadora, tamanho, tipoCordao, cor, dataLancamento, tipo);
            }
            else if(camposArt[1].contains("Tshirt")){
                int tamanho = Integer.valueOf(camposArt[9]);
                int padrao = Integer.valueOf(camposArt[10]);
                sistema.adicionaTshirtVenda(id, email, descricao, marca, precoBase, Atributos.NOVO, 0, 0, nomeTransportadora, tamanho, padrao);
            }
        }
        else if (camposArt[7].contains("USADO")){

            Double avaliacao = Double.parseDouble(camposArt[8]);
            int nrDonos = Integer.valueOf(camposArt[9]);
            String nomeTransportadora = camposArt[10];

            if(camposArt[1].contains("Mala")){
                double dimensao = Double.parseDouble(camposArt[11]);
                String material = camposArt[12];
                LocalDate anoLancamento = LocalDate.parse(camposArt[13]);
                int tipo = Integer.valueOf(camposArt[14]);
                sistema.adicionaMalaVenda(id, email, descricao, marca, precoBase, Atributos.USADO, avaliacao, nrDonos, nomeTransportadora, dimensao, material, anoLancamento, tipo);
            }
            else if(camposArt[1].contains("Sapatilha")){
                int tamanho = Integer.valueOf(camposArt[11]);
                int tipoCordao = Integer.valueOf(camposArt[12]);
                String cor = camposArt[13];
                LocalDate dataLancamento = LocalDate.parse(camposArt[14]);
                int tipo = Integer.valueOf(camposArt[15]);
                sistema.adicionaSapatilhaVenda(id, email, descricao, marca, precoBase, Atributos.USADO, avaliacao, nrDonos, nomeTransportadora, tamanho, tipoCordao, cor, dataLancamento, tipo);
            }
            else if(camposArt[1].contains("Tshirt")){
                int tamanho = Integer.valueOf(camposArt[11]);
                int padrao = Integer.valueOf(camposArt[12]);
                sistema.adicionaTshirtVenda(id, email, descricao, marca, precoBase, Atributos.USADO, avaliacao, nrDonos, nomeTransportadora, tamanho, padrao);
            }
        }
    }

    public void artCompra(String[] aux, Sistema sistema) throws ArtigoException, UtilizadorException, EncomendaException, SistemaException {
        String[] camposArtComp = aux[1].split(",");
        LocalDate data = LocalDate.parse(camposArtComp[0], formatter);
        if (sistema.getDataAtual().isBefore(data)){
            int ano = data.getYear();
            int mes = data.getMonthValue();
            int dia = data.getDayOfMonth();
            sistema.saltaTempo(ano, mes, dia);
        }
        String email = camposArtComp[1];
        int idArtigo = Integer.valueOf(camposArtComp[2]);
        Artigo artigo = sistema.procuraArtigoVenda(idArtigo);
        sistema.adicionaArtigoEncomenda(artigo, email);
    }

    public void transp(String[] aux, Sistema sistema) throws TransportadoraException, SistemaException {
        String[] camposTransp = aux[1].split(",");
        LocalDate data = LocalDate.parse(camposTransp[0], formatter);
        if (sistema.getDataAtual().isBefore(data)){
            int ano = data.getYear();
            int mes = data.getMonthValue();
            int dia = data.getDayOfMonth();
            sistema.saltaTempo(ano, mes, dia);
        }
        String nome = camposTransp[1];
        double margemLucro = Double.parseDouble(camposTransp[2]);
        int tipo = Integer.valueOf(camposTransp[3]);
        int tempoExpedicao = Integer.valueOf(camposTransp[4]);
        sistema.adicionaTransportadora(nome, margemLucro, tipo, tempoExpedicao);
    }

    public void encom(String[] aux, Sistema sistema) throws EncomendaException, ArtigoException, TransportadoraException, UtilizadorException, SistemaException {
        String[] camposEncom = aux[1].split(",");
        LocalDate data = LocalDate.parse(camposEncom[0], formatter);
        if (sistema.getDataAtual().isBefore(data)){
            int ano = data.getYear();
            int mes = data.getMonthValue();
            int dia = data.getDayOfMonth();
            sistema.saltaTempo(ano, mes, dia);
        }
        String email = camposEncom[1];
        String nomeTransportadora = camposEncom[2];
        Transportadora transportadora = sistema.procuraTransportadora(nomeTransportadora);
        Encomenda encomenda = new Encomenda();
        encomenda.setTransportadora(transportadora);
        int x = 3;
        while (camposEncom[x] != null){
            int idArtigo = Integer.valueOf(camposEncom[x]);
            Artigo artigo = sistema.procuraArtigoVenda(idArtigo);
            encomenda.adicionaArtigo(artigo);
            x++;
        }
        sistema.adicionaEncomenda(encomenda, email);
    }
}
