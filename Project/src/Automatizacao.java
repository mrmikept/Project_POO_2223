import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Classe que contem os métodos para ler e analizar o ficheiro de automatizaçao
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public class Automatizacao {

    private String path;
    private List<String> excecoes;
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

    /**
     * Carrega o ficheiro de automatização
     * @param sistema Objeto Sistema a ser carregado os comandos
     * @throws IOException
     * @throws AutomatizacaoException
     * @throws ArtigoException
     * @throws UtilizadorException
     * @throws SistemaException
     * @throws EncomendaException
     * @throws TransportadoraException
     */
    public void carregaFicheiro(Sistema sistema) throws IOException, AutomatizacaoException, ArtigoException, UtilizadorException, SistemaException, EncomendaException, TransportadoraException {
        String pasta = Paths.get(this.path).toAbsolutePath().getParent().toString();
        String pasta2 = pasta.replace("/src", "/Automatizacao");
        File dir = new File(pasta2);
        if (dir.exists()) {
            File ficheiro = new File(pasta2 + File.separator + this.path);
            if (ficheiro.exists()) {

                BufferedReader teste = new BufferedReader(new FileReader(pasta2 + File.separator + this.path));

                String texto = teste.readLine();
                String[] aux1 = texto.split(":");
                String[] campoData = aux1[1].split(";");
                LocalDate data = LocalDate.parse(campoData[0]);
                sistema.setDataAtual(data);
                teste.close();

                BufferedReader comeco = new BufferedReader(new FileReader(pasta2 + File.separator + this.path));

                while (comeco.ready()) {
                    String linha = comeco.readLine();
                    if (!linha.contains("--")) {
                        String[] aux = linha.split(":");

                        if (linha.contains("Adiciona Utilizador:")) {
                            try {
                                util(aux, sistema);
                            } catch (UtilizadorException | SistemaException a) {
                                this.excecoes.add(a.getMessage());
                            }
                        }

                        if (linha.contains("Adiciona Venda:")) {
                            try {
                                artVenda(aux, sistema);
                            } catch (ArtigoException | TransportadoraException | UtilizadorException |
                                     SistemaException a) {
                                this.excecoes.add(a.getMessage());
                            }
                        }

                        if (linha.contains("Adiciona Transportadora:")) {
                            try {
                                transp(aux, sistema);
                            } catch (TransportadoraException | SistemaException a) {
                                this.excecoes.add(a.getMessage());
                            }
                        }

                        if (linha.contains("Adiciona Artigo Encomenda:")){
                            try {
                                adArtEnc(aux,sistema);
                            }
                            catch (EncomendaException | ArtigoException | UtilizadorException | SistemaException a){
                                this.excecoes.add(a.getMessage());
                            }
                        }

                        if (linha.contains("Remove Artigo Encomenda:")){
                            try {
                                remArtEnc(aux,sistema);
                            }
                            catch (EncomendaException | ArtigoException | UtilizadorException | SistemaException a){
                                this.excecoes.add(a.getMessage());
                            }
                        }

                        if (linha.contains("Confirma Encomenda:")) {
                            try {
                                confEnc(aux, sistema);
                            } catch (EncomendaException | SistemaException | UtilizadorException |
                                     TransportadoraException a) {
                                this.excecoes.add(a.getMessage());
                            }

                        }

                        if (linha.contains("Devolve Encomenda:")) {
                            try {
                                devEnc(aux, sistema);
                            } catch (EncomendaException | SistemaException | UtilizadorException a) {
                                this.excecoes.add(a.getMessage());
                            }
                        }

                        if (linha.contains("Altera Tempo Expedicao:")){
                            try {
                                altTempExp(aux,sistema);
                            }
                            catch (SistemaException | TransportadoraException a){
                                this.excecoes.add(a.getMessage());
                            }
                        }

                        if (linha.contains("Altera Margem Lucro:")){
                            try {
                                altMargLucro(aux,sistema);
                            }
                            catch (SistemaException | TransportadoraException a){
                                this.excecoes.add(a.getMessage());
                            }
                        }

                        if (linha.contains("Altera Imposto:")){
                            try {
                                altImp(aux,sistema);
                            }
                            catch (SistemaException a){
                                this.excecoes.add(a.getMessage());
                            }
                        }

                        if (linha.contains("Altera Taxa Encomenda pequena:")){
                            try {
                                altTaxEncPeq(aux,sistema);
                            }
                            catch (SistemaException a){
                                this.excecoes.add(a.getMessage());
                            }
                        }

                        if (linha.contains("Altera Taxa Encomenda media:")){
                            try {
                                altTaxEncMed(aux,sistema);
                            }
                            catch (SistemaException a){
                                this.excecoes.add(a.getMessage());
                            }
                        }

                        if (linha.contains("Altera Taxa Encomenda grande:")){
                            try {
                                altTaxEncGrd(aux,sistema);
                            }
                            catch (SistemaException a){
                                this.excecoes.add(a.getMessage());
                            }
                        }
                    }
                }
                comeco.close();
            } else throw new AutomatizacaoException("O ficheiro não existe!");
        }
        else {
            dir.mkdirs();
            File ficheiro = new File(pasta2 + File.separator + this.path);
            if (ficheiro.exists()) {

                BufferedReader teste = new BufferedReader(new FileReader(pasta2 + File.separator + this.path));

                LocalDate saveData = sistema.getDataAtual();
                String texto = teste.readLine();
                String[] aux1 = texto.split(":");
                String[] campoData = aux1[1].split(";");
                LocalDate data = LocalDate.parse(campoData[0]);
                sistema.setDataAtual(data);
                teste.close();

                BufferedReader comeco = new BufferedReader(new FileReader(pasta2 + File.separator + this.path));

                while (comeco.ready()) {
                    String linha = comeco.readLine();
                    if (!linha.contains("--")) {
                        String[] aux = linha.split(":");

                        if (linha.contains("Adiciona Utilizador:")) {
                            try {
                                util(aux, sistema);
                            } catch (UtilizadorException | SistemaException a) {
                                this.excecoes.add(a.getMessage());
                            }
                        }

                        if (linha.contains("Adiciona Venda:")) {
                            try {
                                artVenda(aux, sistema);
                            } catch (ArtigoException | TransportadoraException | UtilizadorException |
                                     SistemaException a) {
                                this.excecoes.add(a.getMessage());
                            }
                        }

                        if (linha.contains("Adiciona Transportadora:")) {
                            try {
                                transp(aux, sistema);
                            } catch (TransportadoraException | SistemaException a) {
                                this.excecoes.add(a.getMessage());
                            }
                        }

                        if (linha.contains("Adiciona Artigo Encomenda:")){
                            try {
                                adArtEnc(aux,sistema);
                            }
                            catch (EncomendaException | ArtigoException | UtilizadorException | SistemaException a){
                                this.excecoes.add(a.getMessage());
                            }
                        }

                        if (linha.contains("Remove Artigo Encomenda:")){
                            try {
                                remArtEnc(aux,sistema);
                            }
                            catch (EncomendaException | ArtigoException | UtilizadorException | SistemaException a){
                                this.excecoes.add(a.getMessage());
                            }
                        }

                        if (linha.contains("Confirma Encomenda:")) {
                            try {
                                confEnc(aux, sistema);
                            } catch (EncomendaException | SistemaException | UtilizadorException |
                                     TransportadoraException a) {
                                this.excecoes.add(a.getMessage());
                            }

                        }

                        if (linha.contains("Devolve Encomenda:")) {
                            try {
                                devEnc(aux, sistema);
                            } catch (EncomendaException | SistemaException | UtilizadorException a) {
                                this.excecoes.add(a.getMessage());
                            }
                        }

                        if (linha.contains("Altera Tempo Expedicao:")){
                            try {
                                altTempExp(aux,sistema);
                            }
                            catch (SistemaException | TransportadoraException a){
                                this.excecoes.add(a.getMessage());
                            }
                        }

                        if (linha.contains("Altera Margem Lucro:")){
                            try {
                                altMargLucro(aux,sistema);
                            }
                            catch (SistemaException | TransportadoraException a){
                                this.excecoes.add(a.getMessage());
                            }
                        }

                        if (linha.contains("Altera Imposto:")){
                            try {
                                altImp(aux,sistema);
                            }
                            catch (SistemaException a){
                                this.excecoes.add(a.getMessage());
                            }
                        }

                        if (linha.contains("Altera Taxa Encomenda Pequena:")){
                            try {
                                altTaxEncPeq(aux,sistema);
                            }
                            catch (SistemaException a){
                                this.excecoes.add(a.getMessage());
                            }
                        }

                        if (linha.contains("Altera Taxa Encomenda Media:")){
                            try {
                                altTaxEncMed(aux,sistema);
                            }
                            catch (SistemaException a){
                                this.excecoes.add(a.getMessage());
                            }
                        }

                        if (linha.contains("Altera Taxa Encomenda Grande:")){
                            try {
                                altTaxEncGrd(aux,sistema);
                            }
                            catch (SistemaException a){
                                this.excecoes.add(a.getMessage());
                            }
                        }

                    }
                }
                comeco.close();
                sistema.setDataAtual(saveData);
            } else throw new AutomatizacaoException("O ficheiro não existe!");
        }
    }

    /**
     * Trata as linhas de adicionar utilizador
     * @param aux
     * @param sistema
     * @throws UtilizadorException
     * @throws SistemaException
     */
    private void util(String[] aux, Sistema sistema) throws UtilizadorException, SistemaException {
        String[] camposUtil = aux[1].split(";");
        LocalDate data = LocalDate.parse(camposUtil[0]);
        int ano = data.getYear();
        int mes = data.getMonthValue();
        int dia = data.getDayOfMonth();
        sistema.saltaTempo(ano, mes, dia);

        String email = camposUtil[1];
        String palavraPasse = camposUtil[2];
        String nome = camposUtil[3];
        String morada = camposUtil[4];
        int nrFiscal = Integer.parseInt(camposUtil[5]);
        sistema.adicionaUtilizador(email, palavraPasse, nome, morada, nrFiscal);
    }


    /**
     * Trata as linhas de adicionar um artigo a venda
     * @param aux
     * @param sistema
     * @throws ArtigoException
     * @throws TransportadoraException
     * @throws UtilizadorException
     * @throws SistemaException
     */
    private void artVenda(String[] aux, Sistema sistema) throws ArtigoException, TransportadoraException, UtilizadorException, SistemaException {
        String[] camposArt = aux[1].split(";");
        LocalDate data = LocalDate.parse(camposArt[0]);
        int ano = data.getYear();
        int mes = data.getMonthValue();
        int dia = data.getDayOfMonth();
        sistema.saltaTempo(ano, mes, dia);

        String email = camposArt[2];
        String id = camposArt[3].toUpperCase();
        String descricao = camposArt[4];
        String marca = camposArt[5];
        double precoBase = Double.parseDouble(camposArt[6]);
        if (camposArt[7].contains("Novo")){

            String nomeTransportadora = camposArt[8].toUpperCase();
            if(camposArt[1].contains("Mala")){
                double dimensao = Double.parseDouble(camposArt[9]);
                String material = camposArt[10];
                int anoLancamento = Integer.parseInt(camposArt[11]);
                int tipo = retornaTipo(camposArt[12]);
                sistema.adicionaMalaVenda(id, email, descricao, marca, precoBase, 0, 0, nomeTransportadora, dimensao, material, anoLancamento, tipo);
            }
            else if(camposArt[1].contains("Sapatilha")){
                int tamanho = Integer.valueOf(camposArt[9]);
                int tipoCordao = retornaCordao(camposArt[10]);
                String cor = camposArt[11];
                int dataLancamento = Integer.parseInt(camposArt[12]);
                int tipo = retornaTipo(camposArt[13]);
                sistema.adicionaSapatilhaVenda(id, email, descricao, marca, precoBase,  0, 0, nomeTransportadora, tamanho, tipoCordao, cor, dataLancamento, tipo);
            }
            else if(camposArt[1].contains("Tshirt")){
                int tamanho = retornaTamanho(camposArt[9]);
                int padrao = retornaPadrao(camposArt[10]);
                sistema.adicionaTshirtVenda(id, email, descricao, marca, precoBase,  0, 0, nomeTransportadora, tamanho, padrao);
            }
        }
        else if (camposArt[7].contains("Usado")){

            Double avaliacao = Double.parseDouble(camposArt[8]);
            int nrDonos = Integer.valueOf(camposArt[9]);
            String nomeTransportadora = camposArt[10].toUpperCase();

            if(camposArt[1].contains("Mala")){
                double dimensao = Double.parseDouble(camposArt[11]);
                String material = camposArt[12];
                int anoLancamento = Integer.parseInt(camposArt[13]);
                int tipo = retornaTipo(camposArt[14]);
                sistema.adicionaMalaVenda(id, email, descricao, marca, precoBase,  avaliacao, nrDonos, nomeTransportadora, dimensao, material, anoLancamento, tipo);
            }
            else if(camposArt[1].contains("Sapatilha")){
                int tamanho = Integer.valueOf(camposArt[11]);
                int tipoCordao = retornaCordao(camposArt[12]);
                String cor = camposArt[13];
                int dataLancamento = Integer.parseInt(camposArt[14]);
                int tipo = retornaTipo(camposArt[15]);
                sistema.adicionaSapatilhaVenda(id, email, descricao, marca, precoBase, avaliacao, nrDonos, nomeTransportadora, tamanho, tipoCordao, cor, dataLancamento, tipo);
            }
            else if(camposArt[1].contains("Tshirt")){
                int tamanho = retornaTamanho(camposArt[11]);
                int padrao = retornaPadrao(camposArt[12]);
                sistema.adicionaTshirtVenda(id, email, descricao, marca, precoBase, avaliacao, nrDonos, nomeTransportadora, tamanho, padrao);
            }
        }
    }

    /**
     * Trata as linhas de adicionar uma transportadora
     * @param aux
     * @param sistema
     * @throws TransportadoraException
     * @throws SistemaException
     */
    private void transp(String[] aux, Sistema sistema) throws TransportadoraException, SistemaException {
        String[] camposTransp = aux[1].split(";");
        LocalDate data = LocalDate.parse(camposTransp[0]);
        int ano = data.getYear();
        int mes = data.getMonthValue();
        int dia = data.getDayOfMonth();
        sistema.saltaTempo(ano, mes, dia);

        String email = camposTransp[1];
        String palavraPasse = camposTransp[2];
        String nome = camposTransp[3].toUpperCase();
        String morada = camposTransp[4];
        int nif = Integer.parseInt(camposTransp[5]);
        double margemLucro = Double.parseDouble(camposTransp[6]);
        int tempoExpedicao = Integer.parseInt(camposTransp[8]);
        if (camposTransp[7].contains("Normal")){
            sistema.adicionaTransportadora(email, palavraPasse, nome, morada, nif, margemLucro, Atributos.NORMAL, tempoExpedicao);
        } else if (camposTransp[7].contains("Premium")) {
            sistema.adicionaTransportadora(email, palavraPasse, nome, morada, nif, margemLucro, Atributos.PREMIUM, tempoExpedicao);
        }
    }

    /**
     * Trata as linhas de adicionar um artigo a uma encomenda
     * @param aux
     * @param sistema
     * @throws EncomendaException
     * @throws ArtigoException
     * @throws UtilizadorException
     * @throws SistemaException
     */
    private void adArtEnc(String[] aux, Sistema sistema) throws EncomendaException, ArtigoException, UtilizadorException, SistemaException {
        String[] camposAdArtEnc = aux[1].split(";");
        LocalDate data = LocalDate.parse(camposAdArtEnc[0]);
        int ano = data.getYear();
        int mes = data.getMonthValue();
        int dia = data.getDayOfMonth();
        sistema.saltaTempo(ano, mes, dia);

        String idArtigo = camposAdArtEnc[1];
        String email = camposAdArtEnc[2];
        sistema.adicionaArtigoEncomenda(idArtigo,email);
    }

    /**
     * Trata as linhas de remover um artigo de uma encomenda
     * @param aux
     * @param sistema
     * @throws EncomendaException
     * @throws ArtigoException
     * @throws UtilizadorException
     * @throws SistemaException
     */
    private void remArtEnc(String[] aux, Sistema sistema) throws EncomendaException, ArtigoException, UtilizadorException, SistemaException{
        String[] camposRemArtEnc = aux[1].split(";");
        LocalDate data = LocalDate.parse(camposRemArtEnc[0]);
        int ano = data.getYear();
        int mes = data.getMonthValue();
        int dia = data.getDayOfMonth();
        sistema.saltaTempo(ano, mes, dia);

        String idArtigo = camposRemArtEnc[1];
        String email = camposRemArtEnc[2];
        sistema.removeArtigoEncomenda(idArtigo,email);
    }

    /**
     * Trata as linhas de confirmar uma encomenda
     * @param aux
     * @param sistema
     * @throws EncomendaException
     * @throws SistemaException
     * @throws UtilizadorException
     * @throws TransportadoraException
     */
    private void confEnc(String[] aux, Sistema sistema) throws EncomendaException, SistemaException, UtilizadorException, TransportadoraException {
        String[] camposConfEnc = aux[1].split(";");
        LocalDate data = LocalDate.parse(camposConfEnc[0]);
        int ano = data.getYear();
        int mes = data.getMonthValue();
        int dia = data.getDayOfMonth();
        sistema.saltaTempo(ano, mes, dia);

        int idEncomenda = Integer.valueOf(camposConfEnc[1]);
        String email = camposConfEnc[2];
        sistema.confirmaEncomenda(idEncomenda, email);
    }

    /**
     * Trata as linhas de devolver uma encomenda
     * @param aux
     * @param sistema
     * @throws EncomendaException
     * @throws SistemaException
     * @throws UtilizadorException
     */
    private void devEnc(String[] aux, Sistema sistema) throws EncomendaException, SistemaException, UtilizadorException {
        String[] camposDevEnc = aux[1].split(";");
        LocalDate data = LocalDate.parse(camposDevEnc[0]);
        int ano = data.getYear();
        int mes = data.getMonthValue();
        int dia = data.getDayOfMonth();
        sistema.saltaTempo(ano, mes, dia);

        int idEncomenda = Integer.valueOf(camposDevEnc[1]);
        String email = camposDevEnc[2];
        sistema.devolveEncomenda(email, idEncomenda);
    }

    /**
     * Trata as linhas de alterar o tempo de expediçao de uma transportadora
     * @param aux
     * @param sistema
     * @throws SistemaException
     * @throws TransportadoraException
     */
    private void altTempExp(String[] aux, Sistema sistema) throws SistemaException, TransportadoraException {
        String[] camposAltTempExp = aux[1].split(";");
        LocalDate data = LocalDate.parse(camposAltTempExp[0]);
        int ano = data.getYear();
        int mes = data.getMonthValue();
        int dia = data.getDayOfMonth();
        sistema.saltaTempo(ano, mes, dia);

        String email = camposAltTempExp[1];
        int tempo = Integer.valueOf(camposAltTempExp[2]);
        sistema.alteraTempoExpedicaoTransportadora(email, tempo);
    }

    /**
     * Trata as linhas de alterar a margem de lucro de uma transportadora
     * @param aux
     * @param sistema
     * @throws SistemaException
     * @throws TransportadoraException
     */
    private void altMargLucro(String[] aux, Sistema sistema) throws SistemaException, TransportadoraException{
        String[] camposAltMargLucro = aux[1].split(";");
        LocalDate data = LocalDate.parse(camposAltMargLucro[0]);
        int ano = data.getYear();
        int mes = data.getMonthValue();
        int dia = data.getDayOfMonth();
        sistema.saltaTempo(ano, mes, dia);

        String email = camposAltMargLucro[1];
        double margemLucro = Double.parseDouble(camposAltMargLucro[2]);
        sistema.alteraMargemLucroTransportadora(email, margemLucro);
    }

    /**
     * Trata as linhas de alterar o valor do imposto
     * @param aux
     * @param sistema
     * @throws SistemaException
     */
    private void altImp(String[] aux, Sistema sistema) throws SistemaException{
        String[] camposAltImp = aux[1].split(";");
        LocalDate data = LocalDate.parse(camposAltImp[0]);
        int ano = data.getYear();
        int mes = data.getMonthValue();
        int dia = data.getDayOfMonth();
        sistema.saltaTempo(ano, mes, dia);

        int imposto = Integer.valueOf(camposAltImp[1]);
        sistema.getTaxas().setImposto(imposto);
    }

    /**
     * Trata as linhas de alterar a taxa de uma encomenda pequena
     * @param aux
     * @param sistema
     * @throws SistemaException
     */
    private void altTaxEncPeq(String[] aux, Sistema sistema) throws SistemaException{
        String[] camposAltTaxEncPeq = aux[1].split(";");
        LocalDate data = LocalDate.parse(camposAltTaxEncPeq[0]);
        int ano = data.getYear();
        int mes = data.getMonthValue();
        int dia = data.getDayOfMonth();
        sistema.saltaTempo(ano, mes, dia);

        double taxa = Double.parseDouble(camposAltTaxEncPeq[1]);
        sistema.getTaxas().setTaxaEncPequena(taxa);
    }

    /**
     * Trata as linhas de alterar a taxa de uma encomenda media
     * @param aux
     * @param sistema
     * @throws SistemaException
     */
    private void altTaxEncMed(String[] aux, Sistema sistema) throws SistemaException{
        String[] camposAltTaxEncMed = aux[1].split(";");
        LocalDate data = LocalDate.parse(camposAltTaxEncMed[0]);
        int ano = data.getYear();
        int mes = data.getMonthValue();
        int dia = data.getDayOfMonth();
        sistema.saltaTempo(ano, mes, dia);

        double taxa = Double.parseDouble(camposAltTaxEncMed[1]);
        sistema.getTaxas().setTaxaEncMedia(taxa);
    }

    /**
     * Trata as linhas de alterar a taxa de uma encomenda grande
     * @param aux
     * @param sistema
     * @throws SistemaException
     */
    private void altTaxEncGrd(String[] aux, Sistema sistema) throws SistemaException{
        String[] camposAltTaxEncGrd = aux[1].split(";");
        LocalDate data = LocalDate.parse(camposAltTaxEncGrd[0]);
        int ano = data.getYear();
        int mes = data.getMonthValue();
        int dia = data.getDayOfMonth();
        sistema.saltaTempo(ano, mes, dia);

        double taxa = Double.parseDouble(camposAltTaxEncGrd[1]);
        sistema.getTaxas().setTaxaEncGrande(taxa);
    }

    /**
     * funçao que converte a String de uma tamanho para o seu valor definido na classe Atibutos
     * @param tamanho
     * @return int valor do tamanho
     */
    private int retornaTamanho(String tamanho){
        if (tamanho.contains("S")){
            return Atributos.S;
        } else if (tamanho.contains("L")) {
            return Atributos.L;
        } else if (tamanho.contains("M")) {
            return Atributos.M;
        } else if (tamanho.contains("XL")) {
            return Atributos.XL;
        }
        return 4;
    }

    /**
     * funçao que converte a String de um para o seu valor definido na classe Atributos
     * @param tipo
     * @return int valor do tipo
     */
    private int retornaTipo(String tipo){
        if (tipo.contains("Normal")){
            return Atributos.NORMAL;
        } else if (tipo.contains("Premium")) {
            return Atributos.PREMIUM;
        }
        return 2;
    }

    /**
     * funçao que converte a String de um padrao para o seu valor definido na classe Tshirt
     * @param padrao
     * @return int valor do padrao
     */
    private int retornaPadrao(String padrao){
        if (padrao.contains("Lisa")){
            return Tshirt.LISA;
        } else if (padrao.contains("Riscas")) {
            return Tshirt.RISCAS;
        } else if (padrao.contains("Palmeiras")) {
            return Tshirt.PALMEIRAS;
        }
        return 3;
    }

    /**
     * funçao que converte a String de um cordao para o seu valor definido na classe Atributos
     * @param cordao
     * @return int valor do cordao
     */
    private int retornaCordao(String cordao){
        if (cordao.contains("Cordao")){
            return Atributos.CORDAO;
        } else if (cordao.contains("Atilho")) {
            return Atributos.ATILHO;
        }
        return 2;
    }
}
