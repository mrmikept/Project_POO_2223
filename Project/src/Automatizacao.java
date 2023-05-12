import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public void carregaFicheiro(Sistema sistema) throws IOException, AutomatizacaoException {
        String pasta = Paths.get(this.path).toAbsolutePath().getParent().toString();
        String pasta2 = pasta.replace("/src", "/Automatizacao");
        File dir = new File(pasta2);
        if (dir.exists()) {
            File ficheiro = new File(pasta2 + File.separator + this.path);
            if (ficheiro.exists()) {
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

                        if (linha.contains("Adiciona Compra:")) {
                            try {
                                artCompra(aux, sistema);
                            } catch (ArtigoException | UtilizadorException | EncomendaException | SistemaException a) {
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

                        if (linha.contains("Adiciona Encomenda:")) {
                            try {
                                encom(aux, sistema);
                            } catch (EncomendaException | ArtigoException | TransportadoraException |
                                     UtilizadorException |
                                     SistemaException a) {
                                this.excecoes.add(a.getMessage());
                            }
                        }

                        if (linha.contains("Confirma Encomenda:")) {
                            try {
                                confEncomenda(aux, sistema);
                            } catch (EncomendaException | SistemaException | UtilizadorException |
                                     TransportadoraException a) {
                                this.excecoes.add(a.getMessage());
                            }

                        }

                        if (linha.contains("Devolve Encomenda:")) {
                            try {
                                devolEncomenda(aux, sistema);
                            } catch (EncomendaException | SistemaException | UtilizadorException a) {
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

                        if (linha.contains("Adiciona Compra:")) {
                            try {
                                artCompra(aux, sistema);
                            } catch (ArtigoException | UtilizadorException | EncomendaException | SistemaException a) {
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

                        if (linha.contains("Adiciona Encomenda:")) {
                            try {
                                encom(aux, sistema);
                            } catch (EncomendaException | ArtigoException | TransportadoraException |
                                     UtilizadorException |
                                     SistemaException a) {
                                this.excecoes.add(a.getMessage());
                            }
                        }

                        if (linha.contains("Confirma Encomenda:")) {
                            try {
                                confEncomenda(aux, sistema);
                            } catch (EncomendaException | SistemaException | UtilizadorException |
                                     TransportadoraException a) {
                                this.excecoes.add(a.getMessage());
                            }

                        }

                        if (linha.contains("Devolve Encomenda:")) {
                            try {
                                devolEncomenda(aux, sistema);
                            } catch (EncomendaException | SistemaException | UtilizadorException a) {
                                this.excecoes.add(a.getMessage());
                            }
                        }
                    }
                }
                comeco.close();
            } else throw new AutomatizacaoException("O ficheiro não existe!");
        }
    }

    public void util(String[] aux, Sistema sistema) throws UtilizadorException, SistemaException {
        String[] camposUtil = aux[1].split(";");
        LocalDate data = LocalDate.parse(camposUtil[0]);
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
        String[] camposArt = aux[1].split(";");
        LocalDate data = LocalDate.parse(camposArt[0]);
        System.out.println(data.toString());
        if (sistema.getDataAtual().isBefore(data)){
           int ano = data.getYear();
           int mes = data.getMonthValue();
           int dia = data.getDayOfMonth();
           sistema.saltaTempo(ano, mes, dia);
        }
        String email = camposArt[2];
        String id = camposArt[3];
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

    public void artCompra(String[] aux, Sistema sistema) throws ArtigoException, UtilizadorException, EncomendaException, SistemaException {
        String[] camposArtComp = aux[1].split(";");
        LocalDate data = LocalDate.parse(camposArtComp[0]);
        if (sistema.getDataAtual().isBefore(data)){
            int ano = data.getYear();
            int mes = data.getMonthValue();
            int dia = data.getDayOfMonth();
            sistema.saltaTempo(ano, mes, dia);
        }
        String email = camposArtComp[1];
        String idArtigo = camposArtComp[2];
        Artigo artigo = sistema.procuraArtigoVenda(idArtigo);
        sistema.adicionaArtigoEncomenda(artigo, email);
    }

    public void transp(String[] aux, Sistema sistema) throws TransportadoraException, SistemaException {
        String[] camposTransp = aux[1].split(";");
        LocalDate data = LocalDate.parse(camposTransp[0]);
        if (sistema.getDataAtual().isBefore(data)){
            int ano = data.getYear();
            int mes = data.getMonthValue();
            int dia = data.getDayOfMonth();
            sistema.saltaTempo(ano, mes, dia);
        }
        String nome = camposTransp[1].toUpperCase();
        double margemLucro = Double.parseDouble(camposTransp[2]);
        if (camposTransp[3].contains("Normal")){
            int tempoExpedicao = Integer.valueOf(camposTransp[4]);
            sistema.adicionaTransportadora(nome, margemLucro, Atributos.NORMAL, tempoExpedicao);
        } else if (camposTransp[3].contains("Premium")) {
            int tempoExpedicao = Integer.valueOf(camposTransp[4]);
            sistema.adicionaTransportadora(nome, margemLucro, Atributos.PREMIUM, tempoExpedicao);
        }
    }

    public void encom(String[] aux, Sistema sistema) throws EncomendaException, ArtigoException, TransportadoraException, UtilizadorException, SistemaException {
        String[] camposEncom = aux[1].split(";");
        LocalDate data = LocalDate.parse(camposEncom[0]);
        if (sistema.getDataAtual().isBefore(data)){
            int ano = data.getYear();
            int mes = data.getMonthValue();
            int dia = data.getDayOfMonth();
            sistema.saltaTempo(ano, mes, dia);
        }
        String email = camposEncom[1];
        String nomeTransportadora = camposEncom[2].toUpperCase();
        Transportadora transportadora = sistema.procuraTransportadora(nomeTransportadora);
        Encomenda encomenda = new Encomenda();
        encomenda.setTransportadora(transportadora);
        int x = 3;
        while (camposEncom[x] != null){
            String idArtigo = camposEncom[x];
            Artigo artigo = sistema.procuraArtigoVenda(idArtigo);
            encomenda.adicionaArtigo(artigo);
            x++;
        }
        sistema.adicionaEncomenda(encomenda, email);
    }

    public void confEncomenda(String[] aux, Sistema sistema) throws EncomendaException, SistemaException, UtilizadorException, TransportadoraException {
        String[] camposConfEncom = aux[1].split(";");
        LocalDate data = LocalDate.parse(camposConfEncom[0]);
        if (sistema.getDataAtual().isBefore(data)){
            int ano = data.getYear();
            int mes = data.getMonthValue();
            int dia = data.getDayOfMonth();
            sistema.saltaTempo(ano, mes, dia);
        }
        int idEncomenda = Integer.valueOf(camposConfEncom[1]);
        String email = camposConfEncom[2];
        sistema.confirmaEncomenda(idEncomenda, email);
    }

    public void devolEncomenda(String[] aux, Sistema sistema) throws EncomendaException, SistemaException, UtilizadorException {
        String[] camposDevolEncom = aux[1].split(";");
        LocalDate data = LocalDate.parse(camposDevolEncom[0]);
        if (sistema.getDataAtual().isBefore(data)){
            int ano = data.getYear();
            int mes = data.getMonthValue();
            int dia = data.getDayOfMonth();
            sistema.saltaTempo(ano, mes, dia);
        }
        String email = camposDevolEncom[1];
        int idEncomenda = Integer.valueOf(camposDevolEncom[2]);
        sistema.devolveEncomenda(email, idEncomenda);
    }

    public int retornaTamanho(String tamanho){
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

    public int retornaTipo(String tipo){
        if (tipo.contains("Normal")){
            return Atributos.NORMAL;
        } else if (tipo.contains("Premium")) {
            return Atributos.PREMIUM;
        }
        return 2;
    }
    public int retornaPadrao(String padrao){
        if (padrao.contains("Lisa")){
            return Tshirt.LISA;
        } else if (padrao.contains("Riscas")) {
            return Tshirt.RISCAS;
        } else if (padrao.contains("Palmeiras")) {
            return Tshirt.PALMEIRAS;
        }
        return 3;
    }

    public int retornaCordao(String cordao){
        if (cordao.contains("Cordao")){
            return Atributos.CORDAO;
        } else if (cordao.contains("Atilho")) {
            return Atributos.ATILHO;
        }
        return 2;
    }
}
