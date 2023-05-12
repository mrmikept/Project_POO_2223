import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private Sistema sistema;
    private Apresentacao apresentacao;

    public static void main(String[] args) throws IOException, UtilizadorException, TransportadoraException, ClassNotFoundException, ArtigoException, EncomendaException, SistemaException {
        new Main().run();
    }

    private Main() {
        this.sistema = new Sistema();
        this.apresentacao = new Apresentacao();
    }

    public static boolean isInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isString(Object obj) {
        return obj instanceof String;
    }

    private static int stringToInt(String str) {
        return Integer.parseInt(str);
    }

    private void run() throws UtilizadorException, TransportadoraException, IOException, ClassNotFoundException, ArtigoException, EncomendaException, SistemaException {
        int x = 0;
        String c;
        String[] s = {"Entrar no programa", "Guardar estado", "Carregar estado anterior", "Carregar ficheiro de Automatização", "Estatísticas", "Sair"};
        Scanner ler = new Scanner(System.in);
        String eq;
        String input, input_backup;

        do {
            switch (x) {
                case 0:

                    do {
                        apresentacao.printMenu(s, x, "");
                        eq = ler.nextLine().toLowerCase();
                        if (eq.equals("1")) { x = 1; break;}
                        if (eq.equals("2")) { x = 2; break;}
                        if (eq.equals("3")) { x = 3; break;}
                        if (eq.equals("4")) { x = 4; break;}
                        if (eq.equals("5")) { x = 5; break;}
                        if (eq.equals("6")) { x = 6; break;}
                    } while (true);
                    break;

                case 1:
                    try {
                        sistema.atualizaSistema();
                        x = runPrograma();
                    } catch (UtilizadorException a) {
                        System.out.println(a.getMessage());
                    } catch (SistemaException e) {
                        throw new RuntimeException(e);
                    } catch (EncomendaException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case 2:
                    apresentacao.printMenuGuardar();
                    input = ler.nextLine();
                    try {
                        CarregamentoFicheiro.escreveFicheiro(this.sistema, input);
                        apresentacao.printGuardar();
                        apresentacao.printEnter("ESTADO GUARDADO COM SUCESSO!!");
                        ler.nextLine();
                        x = 0;
                        break;
                    }
                    catch (IOException e){
                        apresentacao.printMensagem("ERRO AO GUARDAR FICHEIRO",86,2);
                    }


                case 3:
                    apresentacao.printMenuCarregarEstado();
                    input = ler.nextLine();
                    Path path = Paths.get(input);
                    boolean existe = Files.exists(path);
                    if (existe) {
                        try {
                            this.sistema = CarregamentoFicheiro.lerFicheiro(input);
                            apresentacao.printLoad();
                            apresentacao.printEnter("ESTADO CARREGADO COM SUCESSO!!");
                            ler.nextLine();
                            x = 0;
                            break;
                        }
                        catch (IOException e){
                            apresentacao.printErroFicheiro();
                            apresentacao.printEnterSair();
                            ler.nextLine();
                            x = 0;
                            break;
                        }
                    }
                    else {x = 3; break;} //TODO Verificar mensagem erro quando ficheiro não existe!

                case 4 :
                    apresentacao.printMenuAutomatizacao();
                    input_backup = ler.nextLine();
                    try {
                        Automatizacao backup = new Automatizacao(input_backup);
                        backup.carregaFicheiro(this.sistema);
                        if (!backup.getExcecoes().isEmpty()) {
                            apresentacao.printErrosAutomatizcao(backup.getExcecoes());
                            ler.nextLine();
                        }
                        apresentacao.clear();
                        apresentacao.printBackup();
                        apresentacao.printEnter("AUTOMATIZAÇÃO EXECUTADA COM SUCESSO!!");
                        ler.nextLine();
                        x = 0;
                        break;
                    }
                    catch (IOException e){
                        apresentacao.printErroFicheiro();
                        apresentacao.printEnterSair();
                        ler.nextLine();
                        x = 0;
                        break;
                    }

                case 5:
                    x = runEstatisticas();
                    break;
            }
        } while (x != 6);
        apresentacao.clear();
    }

    private int runPrograma() throws UtilizadorException, TransportadoraException, ArtigoException, SistemaException, EncomendaException {
        int x = 0;
        String email, pass, nome, morada, nomeTrans, c;
        int nif;
        int tipo = 0;
        double lucro;
        String[] s = {"Iniciar sessao - Utlizador", "Procurar Transportadora", "Registar - Utilizador", "Registar - Transportadora", "Configuracoes", "Retroceder"};
        Scanner ler = new Scanner(System.in);
        String eq;

        do {
            switch (x) {
                case 0:
                    do {
                        apresentacao.printMenu(s, x, "");
                        eq = ler.nextLine().toLowerCase();
                        if (eq.equals("1")) {
                            x = 1;
                            break;
                        }
                        if (eq.equals("2")) {
                            x = 2;
                            break;
                        }
                        if (eq.equals("3")) {
                            x = 3;
                            break;
                        }
                        if (eq.equals("4")) {
                            x = 4;
                            break;
                        }
                        if (eq.equals("5")) {
                            x = 5;
                            break;
                        }
                        if (eq.equals("6")) {
                            x = 6;
                            break;
                        }
                    } while (true);
                    break;

                case 1: //Iniciar sessao
                    apresentacao.printMenuLogin();
                    apresentacao.printEspacos(88);
                    ler = new Scanner(System.in);
                    email = ler.nextLine();
                    try {
                        sistema.verificaUtilizador(email);
                    } catch (UtilizadorException a) {
                        apresentacao.printMensagem(a.getMessage(), 74, 2);
                        apresentacao.printMensagem("DESEJA CONTINUAR A TENTAR?", 74, 2);
                        apresentacao.printMensagemSimOuNao(100);
                        x = ler.nextInt();
                        break;
                    }
                    int teste = 0;
                    while (teste == 0) {
                        apresentacao.clear();
                        apresentacao.printMenuLogin();
                        apresentacao.printMensagem(email, 88, 0);
                        apresentacao.printMensagem("Insira a sua password:", 88, 1);
                        apresentacao.printEspacos(88);
                        ler = new Scanner(System.in);
                        pass = ler.nextLine();
                        try {
                            sistema.verificaPassword(email, pass);
                            x = runUtilizador(email);
                            break;
                        } catch (UtilizadorException a) {
                            apresentacao.printMensagem(a.getMessage(),94,2);
                            apresentacao.printMensagemCentrada("DESEJA CONTINUAR A TENTAR?", 2);
                            apresentacao.printMensagemSimOuNao(102);
                            x = ler.nextInt();

                        }if (x==0){
                            teste = 1;
                        }
                    }
                    break;

                case 2:
                    apresentacao.printProcuraTrans();
                    ler = new Scanner(System.in);
                    nomeTrans = ler.nextLine();
                    try {
                        Transportadora transportadora = sistema.procuraTransportadora(nomeTrans);
                        apresentacao.printDadosTransportadora(transportadora);
                        ler.nextLine();
                        x = 0;
                        break;
                    }
                    catch (TransportadoraException a){
                        apresentacao.printMensagem(a.getMessage(),94,2);
                        apresentacao.printMensagemCentrada("DESEJA CONTINUAR A TENTAR?", 2);
                        apresentacao.printMensagemSimOuNao(102);
                        x = ler.nextInt();

                        if (x == 1){
                            x = 2;
                            break;
                        }
                    }
                    break;



                case 3: //Registar utilizador
                    apresentacao.printReg();
                    apresentacao.printMensagem("Insira o email:", 86, 1);
                    apresentacao.printEspacos(86);
                    ler = new Scanner(System.in);
                    email = ler.nextLine();

                    apresentacao.printClear(1);
                    apresentacao.printMensagem("Insira a password:", 86, 1);
                    apresentacao.printEspacos(86);
                    pass = ler.nextLine();

                    apresentacao.printClear(1);
                    apresentacao.printMensagem("Insira o seu primeiro e ultimo nome:", 86, 1);
                    apresentacao.printEspacos(86);

                    nome = ler.nextLine();

                    apresentacao.printClear(1);
                    apresentacao.printMensagem("Insira a sua morada:", 86, 1);
                    apresentacao.printEspacos(86);

                    morada = ler.nextLine();

                    apresentacao.printClear(1);
                    apresentacao.printMensagem("Insira o seu numero fiscal:", 86, 1);
                    apresentacao.printEspacos(86);

                    nif = ler.nextInt();

                    try {
                        sistema.adicionaUtilizador(email, pass, nome, morada, nif);
                        apresentacao.printReg();
                        apresentacao.printMensagem("UTILIZADOR REGISTADO COM SUCESSO!! DESEJA CONTINUAR A REGISTAR?", 72, 3);
                        apresentacao.printClear(1);
                        apresentacao.printMensagemSimOuNao(100);

                        x = ler.nextInt();

                        if (x == 1) x = 3;
                        else x = 0;
                    }
                    catch (UtilizadorException a) {

                        apresentacao.printClear(1);
                        apresentacao.printMensagem(a.getMessage(),81,2);
                        apresentacao.printMensagem("DESEJA TENTAR DE NOVO?", 81, 2);
                        apresentacao.printClear(1);
                        apresentacao.printMensagemSimOuNao(99);

                        x = ler.nextInt();
                        if (x == 1) x = 3;
                        else x = 0;

                    }
                    break;

                case 4:
                    apresentacao.printRegTrans();
                    apresentacao.printMensagem("Insira o nome da transportadora:", 86, 1);
                    apresentacao.printEspacos(86);
                    ler = new Scanner(System.in);
                    nomeTrans = ler.nextLine();

                    apresentacao.printClear(1);
                    apresentacao.printMensagem("Insira a margem de lucro que pretende obter:", 86, 1);
                    apresentacao.printEspacos(86);

                    lucro = ler.nextDouble();

                    apresentacao.printClear(1);
                    apresentacao.printMensagem("Insira 1-\"Normal\" ou 2-\"Premium\":", 86, 1);
                    apresentacao.printEspacos(86);

                    tipo = ler.nextInt();

                    try {
                        sistema.adicionaTransportadora(nomeTrans, lucro, tipo, 2);
                        apresentacao.printRegTrans();
                        apresentacao.printMensagem("TRANSPORTADORA REGISTADA COM SUCESSO!! DESEJA CONTINUAR A REGISTAR?", 71, 3);
                        apresentacao.printClear(1);
                        apresentacao.printMensagemSimOuNao(100);

                        x = ler.nextInt();

                        if (x == 1) x = 4;
                        else x = 0;
                    }
                    catch (TransportadoraException a){
                        apresentacao.printClear(3);
                        apresentacao.printMensagem(a.getMessage(),86,2);
                        apresentacao.printMensagem("DESEJA TENTAR DE NOVO?", 86, 2);
                        apresentacao.printClear(1);
                        apresentacao.printMensagemSimOuNao(98);

                        x = ler.nextInt();
                        if (x == 1) x = 4;
                        else x = 0;
                    }
                    break;

                case 5: // MENU CONFIGURACOES
                    x = runConfig();
                    break;
            }

            }while (x != 6);
        return 0;
        }

    public int runUtilizador(String email) throws UtilizadorException, ArtigoException, TransportadoraException, EncomendaException, SistemaException //MENU UTILIZADOR
    {
        String[] s = {"Ver perfil", "Comprar", "Vendas", "Encomendas", "Faturas", "Retroceder"};
        int x = 0;
        String c, eq;
        Utilizador utilizador = sistema.procuraUtilizador(email);
        String nome = utilizador.getNome();
        Scanner ler = new Scanner(System.in);
        apresentacao.printMenu(s, 1, nome);

        do {
            switch (x) {
                case 0:

                    do {
                        apresentacao.printMenu(s, 1, nome);
                        eq = ler.nextLine().toLowerCase();
                        if (eq.equals("1")) { x = 1; break;}
                        if (eq.equals("2")) { x = 2; break;}
                        if (eq.equals("3")) { x = 3; break;}
                        if (eq.equals("4")) { x = 4; break;}
                        if (eq.equals("5")) { x = 5; break;}
                        if (eq.equals("6")) { x = 6; break;}
                    } while (true);
                    break;

                case 1:

                    apresentacao.printLogin();
                    apresentacao.printPerfil(utilizador);
                    c = ler.nextLine();
                    x = 0;
                    break;

                case 2: // MENU COMPRAR
                    x = runMenuComprar(email);
                    break;

                case 3: // MENU VENDAS
                    x = runVendas(email);
                    break;

                case 4: // MENU ENCOMENDAS
                    x = runEncomendas(email);
                    break;

                case 5: // MENU FATURAS
                    x = runFaturas(email);
                    break;

                case 6:

            }
        } while (x != 6);

        return 0;
    }

    public int runMenuComprar(String email) throws UtilizadorException, ArtigoException, EncomendaException {
        Scanner ler = new Scanner(System.in);
        String opcao;
        int paginaAtual = 1;
        do {
            List<Artigo> artigos = sistema.getArtigosVenda(email).values().stream().collect(Collectors.toList());
            if (artigos.isEmpty()) {
                apresentacao.printComprar();
                apresentacao.printMensagem("Não existem artigos para comprar!", 88, 2);
                apresentacao.printEnter("");
                ler.nextLine();
                break;
            }
            List<String> strings = new ArrayList<>();
            for (Artigo artigo : artigos) {
                strings.add(apresentacao.showArtigoString(artigo, sistema.getDataAtual().getYear()));
            }
            int quantidade = 2;
            int numPaginas = (int) Math.ceil((double) strings.size() / quantidade);
            int inicio = (paginaAtual - 1) * quantidade, fim = Math.min(inicio + quantidade, strings.size());
                apresentacao.printComprar();
                apresentacao.paginateMenu(strings, quantidade, paginaAtual, numPaginas, inicio, fim);
                apresentacao.printClear(2);
                System.out.println(Apresentacao.CYAN_BOLD + "                                                           Pressione" + Apresentacao.RESET + " '+' " +
                        Apresentacao.CYAN_BOLD + "para avancar," + Apresentacao.RESET + " '-' " + Apresentacao.CYAN_BOLD + "para a retroceder," + Apresentacao.RESET + " 'c' " + Apresentacao.CYAN_BOLD +
                        "para comprar artigo e" + Apresentacao.RESET + " 's' " + Apresentacao.CYAN_BOLD + "para sair" + Apresentacao.RESET);
                apresentacao.printEspacos(103);
                ler = new Scanner(System.in);
                opcao = ler.nextLine();
                if (opcao.equals("+") && paginaAtual < numPaginas) {
                    paginaAtual++;
                } else if (opcao.equals("-") && paginaAtual > 1) {
                    paginaAtual--;
                } else if (opcao.equals("c")) {
                    apresentacao.printClear(1);
                    apresentacao.printMensagem("INTRODUZA O ID DO ARTIGO QUE DESEJA COMPRAR", 83, 1);
                    apresentacao.printClear(2);
                    apresentacao.printEspacos(103);
                    String id = ler.nextLine().toUpperCase();
                    apresentacao.clear();
                    apresentacao.printComprar();
                    apresentacao.showArtigo(sistema.procuraArtigo(id.toUpperCase()), sistema.getDataAtual().getYear());
                    apresentacao.printMensagem("DESEJA COMPRAR ESTE ARTIGO?", 91, 1);
                    apresentacao.printMensagemSimOuNao(101);
                    apresentacao.printClear(1);
                    apresentacao.printEspacos(103);
                    if (ler.nextLine().equals("1")) {
                        sistema.adicionaArtigoEncomenda(id, email);
                        apresentacao.printClear(1);
                        apresentacao.printMensagem("ARTIGO COMPRADO COM SUCESSO!", 90, 3);
                        apresentacao.printEnter("");
                        ler.nextLine();
                    }
                } else if (opcao.equals("s"))
                {
                    break;
                }
        } while (true);
        return 0;
    }

    public int runFaturas(String email) throws UtilizadorException, ArtigoException, SistemaException, EncomendaException, TransportadoraException {
        String [] s = {"Faturas de compras", "Faturas de vendas", "Retroceder"};
        apresentacao.printMenu(s,5,"");
        int x = 0;
        Scanner ler = new Scanner(System.in);
        Utilizador utilizador = sistema.procuraUtilizador(email);
        List<Fatura> faturasCompras = utilizador.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == Atributos.VENDIDO).collect(Collectors.toList());
        List<Fatura> faturasVenda = utilizador.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == Atributos.VENDA).collect(Collectors.toList());
        String eq;

        do {
            switch (x) {
                case 0:

                    do {
                        apresentacao.printMenu(s,5,"");
                        eq = ler.nextLine().toLowerCase();
                        if (eq.equals("1")) { x = 1; break;}
                        if (eq.equals("2")) { x = 2; break;}
                        if (eq.equals("3")) { x = 3; break;}
                    } while (true);
                    break;

                case 1:
                    apresentacao.paginateFaturas(faturasCompras, 6,email,0, sistema);
                    x = 0;
                    break;

                case 2 :
                    apresentacao.paginateFaturas(faturasVenda, 6,email,1, sistema);
                    x = 0;
                    break;
            }
        } while (x != 3);

        return 0;
    }

    public int runVendas(String email) throws UtilizadorException, ArtigoException, TransportadoraException {
        String[] s = {"Minha lista de vendas", "Adicionar artigos a minha lista de vendas", "Retroceder"};
        Scanner ler = new Scanner(System.in);
        int x = 0;
        String eq;

        do {
            switch (x) {
                case 0:

                    do {
                        apresentacao.printMenu(s, 0, "");
                        eq = ler.nextLine().toLowerCase();
                        if (eq.equals("1")) { x = 1; break;}
                        if (eq.equals("2")) { x = 2; break;}
                        if (eq.equals("3")) { x = 3; break;}
                    } while (true);
                    break;

                case 1: //MINHA LISTA DE ARTIGOS A VENDA
                    //apresentacao.printMinhaLista();
                    runMenuUtilizadorArtigosVenda(email);
                    //apresentacao.paginateMenuVendas(sistema.getArtigosVendaUtilizador(email), 2, email, sistema);
                    x =0;
                    break;
                case 2: //ADICIONAR ARTIGOS A MINHA LISTA DE VENDAS
                    x = runArtigosVender(email);
                    break;
            }

        } while (x != 3);

        return 0;
    }

    public int runMenuUtilizadorArtigosVenda(String email) throws UtilizadorException, ArtigoException, TransportadoraException {
        String opcao;
        Scanner ler = new Scanner(System.in);
        do {
            List<Artigo> lista = sistema.getArtigosVendaUtilizador(email).values().stream().collect(Collectors.toList());
            if (lista.isEmpty()) {
                apresentacao.printMinhaLista();
                apresentacao.printMensagem("NÃO POSSUI NENHUM ARTIGO À VENDA!!", 87, 1);
                apresentacao.printClear(1);
                apresentacao.printMensagem("DESEJA ADICONAR ALGUM ARTIGO À SUA LISTA DE VENDAS?", 79, 3);
                apresentacao.printClear(2);
                apresentacao.printMensagemSimOuNao(100);
                opcao = ler.nextLine();
                if (opcao.equals("1")) {
                    runArtigosVender(email);
                    break;
                } else if (opcao.equals("0")) {
                    break;
                }

            }
            List<String> strings = new ArrayList<>();
            for (Artigo artigo : lista) {
                strings.add(apresentacao.showArtigoString(artigo, sistema.getDataAtual().getYear()));
            }
            int quantidade = 2;
            int numPaginas = (int) Math.ceil((double) strings.size() / quantidade);
            int paginaAtual = 1;
            int inicio = (paginaAtual - 1) * quantidade, fim = Math.min(inicio + quantidade, strings.size());
            do {
                apresentacao.printMinhaLista();
                apresentacao.paginateMenu(strings, quantidade, paginaAtual, numPaginas, inicio, fim);
                    apresentacao.printClear(2);
                    System.out.println(Apresentacao.CYAN_BOLD + "                                                           Pressione" + Apresentacao.RESET + " '+' " +
                            Apresentacao.CYAN_BOLD + "para avancar," + Apresentacao.RESET + " '-' " + Apresentacao.CYAN_BOLD + "para a retroceder," + Apresentacao.RESET + " 'r' " + Apresentacao.CYAN_BOLD +
                            "para remover artigo e" + Apresentacao.RESET + " 's' " + Apresentacao.CYAN_BOLD + "para sair" + Apresentacao.RESET);
                    apresentacao.printEspacos(103);
                    opcao = ler.nextLine();
                    if (opcao.equals("+") && paginaAtual < numPaginas) {
                        paginaAtual++;
                    } else if (opcao.equals("-") && paginaAtual > 1) {
                        paginaAtual--;
                    } else if (opcao.equals("r"))
                    {
                        apresentacao.printClear(1);
                        apresentacao.printMensagem("INTRODUZA O ID DO ARTIGO QUE DESEJA REMOVER",83,1);
                        apresentacao.printClear(1);
                        apresentacao.printEspacos(104);
                        String id = ler.nextLine();
                        if (sistema.verificaArtigoUtilizador(email,id))
                        {
                            apresentacao.clear();
                            apresentacao.printMinhaLista();
                            apresentacao.showArtigo(sistema.procuraArtigoVenda(id),sistema.getDataAtual().getYear());
                            apresentacao.printClear(2);
                            apresentacao.printMensagem("DESEJA REMOVER ESTE ARTIGO?", 91,1);
                            apresentacao.printMensagemSimOuNao(101);
                            opcao = ler.nextLine();
                            if (opcao.equals("1"))
                            {
                                sistema.removeArtigo(id);
                                apresentacao.printClear(1);
                                apresentacao.printMensagem("ARTIGO REMOVIDO COM SUCESSO!",90,3);
                                apresentacao.printEnter("");
                                ler.nextLine();
                                break;
                            }
                        }
                    } else if (opcao.equals("s")) break;
            } while (true);
            break;
        } while (true);
        return 0;
    }

    public String runEscolhaTransportadora(int tipoTransportadora) {
        String opcao;
        Scanner ler = new Scanner(System.in);
        List<Transportadora> transportadoras = sistema.getListaTransportadoras().values().stream().filter(transportadora -> transportadora.getTipo() == tipoTransportadora).collect(Collectors.toList());
        List<String> strings = new ArrayList<>();
        int i = 1;
        for (Transportadora transportadora : transportadoras) {
            strings.add(apresentacao.showTransportadora(i, transportadora));
            i++;
        }
        int quantidade = 1;
        int numPaginas = (int) Math.ceil((double) strings.size() / quantidade);
        int paginaAtual = 1;
        int inicio = (paginaAtual - 1) * quantidade, fim = Math.min(inicio + quantidade, strings.size());
        do {
            apresentacao.printProcuraTrans();
            apresentacao.paginateMenu(strings, quantidade, paginaAtual, numPaginas, i, fim);
            apresentacao.printClear(2);
            System.out.println(apresentacao.CYAN_BOLD + "                                                                               Pressione" + apresentacao.RESET + " '+' " +
                    apresentacao.CYAN_BOLD + "para avancar e" + apresentacao.RESET + " '-' " + apresentacao.CYAN_BOLD + "para a retroceder ");
            apresentacao.printMensagem("Digite o nome da Transportadora para a selecionar!", 78, 3);
            apresentacao.printClear(1);
            apresentacao.printEspacos(103);
            opcao = ler.nextLine().toUpperCase();
            if (opcao.equals("+") && paginaAtual < numPaginas) {
                paginaAtual++;
            } else if (opcao.equals("-") && paginaAtual > 1) {
                paginaAtual--;
            } else if (sistema.verificaTransportadora(opcao)) {
                break;
            } else if (!(opcao.equals("+") || opcao.equals("-"))){
            apresentacao.printClear(1);
            apresentacao.printMensagem("COMANDO INVALIDO OU TRANSPORTADORA NÃO ENCONTRADA!",78,2);
            apresentacao.printEnter("");
            ler.nextLine();
            }
        }while (true);
        return opcao;
    }

    public int runArtigosVender(String email) throws ArtigoException, UtilizadorException, TransportadoraException {

        int opcao, nrDonos = 0, tamanho, padrao, tipo, tipoCordao, dataLancamento;
        double precoBase, avaliacao = 0, dimensao;
        String id = "", descricao, marca, material, cor, nomeTransportadora;
        Utilizador utilizador;
        int x = 0;

        Scanner ler = new Scanner(System.in);

        do {
            switch (x) {

                case 0:
                    apresentacao.printRunArtigosVendaCase0();
                    x = ler.nextInt();
                    break;

                case 1:
                    Tshirt tshirt = new Tshirt();
                    apresentacao.printTshirt();
                    apresentacao.printMensagem("INTRODUZA O ID DA T-SHIRT (CÓDIGO DE BARRAS)", 84, 1);
                    ler = new Scanner(System.in);
                    apresentacao.printEspacos(84);
                    id = ler.nextLine();

                    if (!sistema.verificaArtigosID(id)) {

                        tshirt.setId(id);

                        apresentacao.printClear(1);
                        apresentacao.printMensagem("INTRODUZA UMA DESCRIÇÃO", 84, 1);
                        apresentacao.printEspacos(84);

                        descricao = ler.nextLine();
                        tshirt.setDescricao(descricao);

                        apresentacao.printClear(1);
                        apresentacao.printMensagem("INTRODUZA A MARCA", 84, 1);
                        apresentacao.printEspacos(84);

                        marca = ler.nextLine();
                        tshirt.setMarca(marca);

                        apresentacao.printClear(1);
                        apresentacao.printMensagem("INTRODUZA O PREÇO BASE", 84, 1);
                        apresentacao.printEspacos(84);

                        precoBase = ler.nextDouble();
                        tshirt.setPrecoBase(precoBase);

                        apresentacao.printClear(1);
                        apresentacao.printTamanhosTshirt();

                        tamanho = ler.nextInt();
                        tshirt.setTamanho(tamanho);

                        apresentacao.printClear(1);
                        apresentacao.printPadroesTshirt();

                        padrao = ler.nextInt();
                        tshirt.setPadrao(padrao);

                        apresentacao.printClear(1);
                        apresentacao.printEstadoArtigo();

                        opcao = ler.nextInt();

                        if (opcao == 0) {
                            apresentacao.printClear(1);
                            apresentacao.printMensagem("INTRODUZA A SUA AVALIAÇÃO", 84, 1);
                            apresentacao.printEspacos(84);

                            avaliacao = ler.nextDouble();

                            apresentacao.printClear(1);
                            apresentacao.printMensagem("INTRODUZA O NÚMERO DE DONOS QUE JÁ TEVE", 84, 1);
                            apresentacao.printEspacos(84);

                            nrDonos = ler.nextInt();
                        }

                        tshirt.setAvaliacao(avaliacao);
                        tshirt.setNrDonos(nrDonos);

                        nomeTransportadora = runEscolhaTransportadora(0);
                        tshirt.setTransportadora(sistema.procuraTransportadora(nomeTransportadora));


                        utilizador = sistema.procuraUtilizador(email);
                        tshirt.setVendedor(utilizador);

                        apresentacao.clear();
                        apresentacao.printClear(5);
                        apresentacao.showArtigo(tshirt, sistema.getDataAtual().getYear());
                        //System.out.println(tshirt.showArtigo()); //TODO: Passar para o showArtigo para a Apresentação
                        apresentacao.printClear(3);
                        apresentacao.printMensagem("TEM A CERTEZA QUE DESEJA ADICIONAR ESTE ARTIGO?", 81, 1);
                        apresentacao.printClear(1);
                        apresentacao.printMensagemSimOuNao(99);

                        x = ler.nextInt();

                        if (x == 1) {
                            try {
                                sistema.adicionaArtigo(tshirt);
                                apresentacao.printClear(1);
                                apresentacao.printMensagem("ARTIGO ADICIONADO COM SUCESSO!!", 87, 3);
                                apresentacao.printMensagem("DESEJA ADICIONAR OUTRO ARTIGO?", 87, 3);
                                apresentacao.printClear(1);
                                apresentacao.printMensagemSimOuNao(99);

                                x = ler.nextInt();

                                if (x == 1) {
                                    x = 0;
                                    break;
                                } else if (x == 0) {
                                    x = 4;
                                    break;
                                }
                            } catch (ArtigoException a) {
                                apresentacao.printMensagem(a.getMessage(), 87, 2);
                                apresentacao.printMensagem("DESEJA TENTAR NOVAMENTE?", 87, 2);
                                apresentacao.printMensagemSimOuNao(87);
                                x = ler.nextInt();

                                if (x == 1) {
                                    x = 1;
                                    break;
                                } else if (x == 0) {
                                    x = 4;
                                    break;
                                }
                            }
                        }
                    } else if (x == 0){
                        x = 4;
                        break;
                    }
                    break;


                case 2:
                    Mala mala = new Mala();
                    apresentacao.printMala();
                    apresentacao.printMensagem("INTRODUZA O ID DA MALA (CÓDIGO DE BARRAS)",84,1);
                    apresentacao.printEspacos(84);
                    ler = new Scanner(System.in);

                    id = ler.nextLine();
                    mala.setId(id);

                    apresentacao.printClear(1);
                    apresentacao.printMensagem("INTRODUZA UMA DESCRIÇÃO",84,1);
                    apresentacao.printEspacos(84);

                    descricao = ler.nextLine();
                    mala.setDescricao(descricao);

                    apresentacao.printClear(1);
                    apresentacao.printMensagem("INTRODUZA A MARCA",84,1);
                    apresentacao.printEspacos(84);

                    marca = ler.nextLine();
                    mala.setMarca(marca);

                    apresentacao.printClear(1);
                    apresentacao.printMensagem("INTRODUZA O PRECO BASE",84,1);
                    apresentacao.printEspacos(84);

                    precoBase = ler.nextDouble();
                    mala.setPrecoBase(precoBase);

                    apresentacao.printClear(1);
                    apresentacao.printMensagem("INTRODUZA A SUA DIMENSÃO",84,1);
                    apresentacao.printEspacos(84);

                    dimensao = ler.nextDouble();
                    mala.setDimensao(dimensao);

                    apresentacao.printClear(1);
                    apresentacao.printMensagem("INTRODUZA O MATERIAL",84,1);
                    apresentacao.printEspacos(84);

                    ler = new Scanner(System.in);
                    material = ler.nextLine();
                    mala.setMaterial(material);

                    apresentacao.printClear(1);
                    apresentacao.printMensagem("INTRODUZA O SEU ANO DE LANÇAMENTO (EX: 2023)",84,1);
                    apresentacao.printEspacos(84);

                    dataLancamento = ler.nextInt();
                    mala.setAnoLancamento(dataLancamento);

                    apresentacao.printClear(1);
                    apresentacao.printArtigoPremiumOuNormal();

                    tipo = ler.nextInt();
                    mala.setTipo(tipo);

                    apresentacao.printClear(1);
                    apresentacao.printEstadoArtigo();

                    opcao = ler.nextInt();


                    if (opcao == 0)
                    {
                        apresentacao.printClear(1);
                        apresentacao.printMensagem("INTRODUZA A SUA AVALIAÇÃO",84,1);
                        apresentacao.printEspacos(84);
                        avaliacao = ler.nextDouble();
                        apresentacao.printClear(1);
                        apresentacao.printMensagem("INTRODUZA O NÚMERO DE DONOS QUE JÁ TEVE",84,1);
                        apresentacao.printEspacos(84);

                        nrDonos = ler.nextInt();

                    }

                    mala.setAvaliacao(avaliacao);
                    mala.setNrDonos(nrDonos);


                    nomeTransportadora = runEscolhaTransportadora(tipo);
                    mala.setTransportadora(sistema.procuraTransportadora(nomeTransportadora));

                    utilizador = sistema.procuraUtilizador(email);
                    mala.setVendedor(utilizador);

                    apresentacao.clear();
                    apresentacao.printClear(5);
                    apresentacao.showArtigo(mala,sistema.getDataAtual().getYear());
                    //System.out.println(mala.showArtigo()); //TODO: Passar para o showArtigo para a Apresentação
                    apresentacao.printClear(3);
                    apresentacao.printMensagem("TEM A CERTEZA QUE DESEJA ADICIONAR ESTE ARTIGO?",81,1);
                    apresentacao.printClear(1);
                    apresentacao.printMensagemSimOuNao(99);

                    x = ler.nextInt();

                    if (x == 1)
                    {
                        try {
                            sistema.adicionaArtigo(mala);
                            apresentacao.printClear(1);
                            apresentacao.printMensagem("ARTIGO ADICIONADO COM SUCESSO!!",87,3);

                            apresentacao.printMensagem("DESEJA ADICIONAR OUTRO ARTIGO?",87,3);
                            apresentacao.printClear(1);
                            apresentacao.printMensagemSimOuNao(99);

                            x = ler.nextInt();

                            if (x == 1)
                            {
                                x = 0;
                                break;
                            } else if (x == 0)
                            {
                                x = 4;
                                break;
                            }
                        }
                        catch (ArtigoException a){
                            apresentacao.printMensagem(a.getMessage(), 87, 2);
                            apresentacao.printMensagem("DESEJA TENTAR NOVAMENTE?", 87, 2);
                            apresentacao.printMensagemSimOuNao(87);
                            x = ler.nextInt();

                            if (x == 1) {
                                x = 2;
                                break;
                            } else if (x == 0) {
                                x = 4;
                                break;
                            }
                        }
                    } else if (x == 0) {
                        x = 4;
                        break;
                    }
                    break;


                case 3:
                    Sapatilha sapatilha = new Sapatilha();
                    apresentacao.printSapatilhas();
                    apresentacao.printMensagem("INTRODUZA O ID DA SAPATILHA (CÓDIGO DE BARRAS)",84,1);
                    apresentacao.printEspacos(84);
                    ler = new Scanner(System.in);

                    id = ler.nextLine();
                    sapatilha.setId(id);

                    apresentacao.printClear(1);
                    apresentacao.printMensagem("INTRODUZA UMA DESCRIÇÃO",84,1);
                    apresentacao.printEspacos(84);

                    descricao = ler.nextLine();
                    sapatilha.setDescricao(descricao);

                    apresentacao.printClear(1);
                    apresentacao.printMensagem("INTRODUZA A MARCA",84,1);
                    apresentacao.printEspacos(84);

                    marca = ler.nextLine();
                    sapatilha.setMarca(marca);

                    apresentacao.printClear(1);
                    apresentacao.printMensagem("INTRODUZA O PREÇO BASE",84,1);
                    apresentacao.printEspacos(84);

                    precoBase = ler.nextDouble();
                    sapatilha.setPrecoBase(precoBase);

                    apresentacao.printClear(1);
                    apresentacao.printMensagem("INTRODUZA O SEU TAMANHO (Nº DE CALÇADO)",84,1);
                    apresentacao.printEspacos(84);

                    tamanho = ler.nextInt();
                    sapatilha.setTamanho(tamanho);

                    apresentacao.printClear(1);
                    apresentacao.printTipoCordao();

                    tipoCordao = ler.nextInt();
                    sapatilha.setTipoCordao(tipoCordao);

                    apresentacao.printClear(1);
                    apresentacao.printMensagem("INTRODUZA A SUA COR",84,1);
                    apresentacao.printEspacos(84);

                    ler = new Scanner(System.in);
                    cor = ler.nextLine();
                    sapatilha.setCor(cor);

                    apresentacao.printClear(1);
                    apresentacao.printMensagem("INTRODUZA O ANO DE LANÇAMENTO",84,1);
                    apresentacao.printEspacos(84);

                    dataLancamento = ler.nextInt();
                    sapatilha.setDataLancamento(dataLancamento);

                    apresentacao.printClear(1);
                    apresentacao.printArtigoPremiumOuNormal();

                    tipo = ler.nextInt();
                    sapatilha.setTipo(tipo);

                    apresentacao.printClear(1);
                    apresentacao.printEstadoArtigo();

                    opcao = ler.nextInt();

                    if (opcao == 0)
                    {
                        apresentacao.printClear(1);
                        apresentacao.printMensagem("INTRODUZA A SUA AVALIAÇÃO",84,1);
                        apresentacao.printEspacos(84);

                        avaliacao = ler.nextDouble();

                        apresentacao.printClear(1);
                        apresentacao.printMensagem("INTRODUZA O NÚMERO DE DONOS QUE JÁ TEVE",84,1);
                        apresentacao.printEspacos(84);

                        nrDonos = ler.nextInt();
                     }
                    sapatilha.setAvaliacao(avaliacao);
                    sapatilha.setNrDonos(nrDonos);
                    nomeTransportadora = runEscolhaTransportadora(tipo);
                    sapatilha.setTransportadora(sistema.procuraTransportadora(nomeTransportadora));
                    utilizador = sistema.procuraUtilizador(email);
                    sapatilha.setVendedor(utilizador);

                    apresentacao.clear();
                    apresentacao.printClear(5);
                    apresentacao.showArtigo(sapatilha,sistema.getDataAtual().getYear());
                    //System.out.println(sapatilha.showArtigo()); //TODO: Passar para o showArtigo para a Apresentação
                    apresentacao.printClear(3);
                    apresentacao.printMensagem("TEM A CERTEZA QUE DESEJA ADICIONAR ESTE ARTIGO?",81,1);
                    apresentacao.printClear(1);
                    apresentacao.printMensagemSimOuNao(99);

                    x = ler.nextInt();

                    if (x == 1)
                    {
                        try {
                            sistema.adicionaArtigo(sapatilha);
                            apresentacao.printClear(1);
                            apresentacao.printMensagem("ARTIGO ADICIONADO COM SUCESSO!!",87,3);
                            apresentacao.printMensagem("DESEJA ADICIONAR OUTRO ARTIGO?",87,3);
                            apresentacao.printClear(1);
                            apresentacao.printMensagemSimOuNao(99);

                            x = ler.nextInt();

                            if (x == 1)
                            {
                                x = 0;
                                break;
                            } else if (x == 0)
                            {
                                x = 4;
                                break;
                            }
                        }
                        catch (ArtigoException a){
                            apresentacao.printMensagem(a.getMessage(), 87, 2);
                            apresentacao.printMensagem("DESEJA TENTAR NOVAMENTE?", 87, 2);
                            apresentacao.printMensagemSimOuNao(87);
                            x = ler.nextInt();

                            if (x == 1) {
                                x = 3;
                                break;
                            } else if (x == 0) {
                                x = 4;
                                break;
                            }
                        }
                    } else if (x == 0) {
                        x = 4;
                        break;
                    }
            }
        } while (x != 4);

        return 0;
    }

    public int runMenuImpostos()
    {
        int imposto, flag = 0;
        double taxa;
        String escolha;
        String [] opcoes = {"Alterar taxa de Imposto", "Alterar taxa encomendas pequenas","Alterar taxa encomendas médias","Alterar taxa encomendas grandes","Retroceder"};
        Scanner ler = new Scanner(System.in);

        do {
            switch (flag) {
                case 0:
                    do {
                        apresentacao.printTax();
                        apresentacao.printEspacos(60);
                        System.out.println(Apresentacao.CYAN_BOLD + "Taxa de imposto: " + Apresentacao.RESET + sistema.getTaxas().getImposto() +
                                Apresentacao.CYAN_BOLD + " | Taxa enc. Peq.: " + Apresentacao.RESET + sistema.getTaxas().getTaxaEncPequena() +
                                Apresentacao.CYAN_BOLD + " | Taxa enc. Med.: " + Apresentacao.RESET + sistema.getTaxas().getTaxaEncMedia() +
                                Apresentacao.CYAN_BOLD + " | Taxa enc. Grd.: " + Apresentacao.RESET + sistema.getTaxas().getTaxaEncGrande());
                        apresentacao.printMenu(opcoes, 100, "");
                        escolha = ler.nextLine().toLowerCase();
                        if (escolha.equals("1")) {
                            flag = 1;
                            break;
                        }
                        if (escolha.equals("2")) {
                            flag = 2;
                            break;
                        }
                        if (escolha.equals("3")) {
                            flag = 3;
                            break;
                        }
                        if (escolha.equals("4")) {
                            flag = 4;
                            break;
                        }
                        if (escolha.equals("5")) {
                            flag = 5;
                            break;
                        }
                        if (escolha.equals("6")) {
                            flag = 6;
                            break;
                        }
                    } while (true);
                    break;
                case 1: //Alterar imposto
                    apresentacao.printTax();
                    apresentacao.printEspacos(86);
                    System.out.println(Apresentacao.CYAN_BOLD + "Taxa de imposto atual: " + Apresentacao.RESET + +sistema.getTaxas().getImposto());
                    System.out.println();
                    apresentacao.printMensagem("Defina a taxa de imposto", 86, 1);
                    System.out.println();
                    apresentacao.printEspacos(86);
                    ler = new Scanner(System.in);
                    imposto = ler.nextInt();
                    sistema.getTaxas().setImposto(imposto);
                    flag = 6;
                    break;
                case 2:
                    apresentacao.printTax();
                    apresentacao.printEspacos(86);
                    System.out.println(Apresentacao.CYAN_BOLD + "Taxa atual: " + Apresentacao.RESET + sistema.getTaxas().getTaxaEncPequena());
                    System.out.println();
                    apresentacao.printMensagem("Defina a Taxa de uma Encomenda Pequena", 86, 1);
                    System.out.println();
                    apresentacao.printEspacos(86);
                    ler = new Scanner(System.in);
                    taxa = ler.nextDouble();
                    sistema.getTaxas().setTaxaEncPequena(taxa);
                    flag = 6;
                    break;
                case 3:
                    apresentacao.printTax();
                    apresentacao.printEspacos(86);
                    System.out.println(Apresentacao.CYAN_BOLD + "Taxa atual: " + Apresentacao.RESET + sistema.getTaxas().getTaxaEncMedia());
                    System.out.println();
                    apresentacao.printMensagem("Defina a Taxa de uma Encomenda Média", 86, 1);
                    System.out.println();
                    apresentacao.printEspacos(86);
                    ler = new Scanner(System.in);
                    taxa = ler.nextDouble();
                    sistema.getTaxas().setTaxaEncMedia(taxa);
                    flag = 6;
                    break;
                case 4:
                    apresentacao.printTax();
                    apresentacao.printEspacos(86);
                    System.out.println(Apresentacao.CYAN_BOLD + "Taxa atual: " + Apresentacao.RESET + sistema.getTaxas().getTaxaEncGrande());
                    System.out.println();
                    apresentacao.printMensagem("Defina a Taxa de uma Encomenda Grande", 86, 1);
                    System.out.println();
                    apresentacao.printEspacos(86);
                    ler = new Scanner(System.in);
                    taxa = ler.nextDouble();
                    sistema.getTaxas().setTaxaEncGrande(taxa);
                    flag = 6;
                    break;
                case 5:
                    flag = 5;
                    break;
                case 6:
                    apresentacao.printTax();
                    apresentacao.printMensagem("ALTERACAO REALIZADA COM SUCESSO!!", 87, 3);
                    apresentacao.printEnterSair();
                    ler = new Scanner(System.in);
                    escolha = ler.nextLine();
                    flag = 0;
                    break;
            }
        } while (flag != 5);
        return 0;
    }

    public int runConfig() throws SistemaException {
        int x = 0;
        String [] s = {"Avancar no tempo", "Alterar taxas e impostos", "Alterar tempo de devolucao", "Retroceder"};
        Scanner ler = new Scanner(System.in);
        String c, eq;

        do {
            switch (x) {
                case 0:

                    do {
                        apresentacao.printMenu(s, 2, "");
                        eq = ler.nextLine().toLowerCase();
                        if (eq.equals("1")) { x = 1; break;}
                        if (eq.equals("2")) { x = 2; break;}
                        if (eq.equals("3")) { x = 3; break;}
                        if (eq.equals("4")) { x = 4; break;}
                    } while (true);
                    break;

                case 1: // MENU AVANCAR NO TEMPO
                    x = runAvancaTempo();
                    break;
                case 2: // MENU ALTERAR IMPOSTO E TAXAS
                    x = runMenuImpostos();
                    break;
                case 3:
                    apresentacao.printReturnTime();
                    apresentacao.printMensagem("Defina o limite de dias para a devolucao de uma encomenda",77,1);
                    System.out.println();
                    apresentacao.printEspacos(77);
                    ler = new Scanner(System.in);
                    c = ler.nextLine();

                    if (!isInt(c)) {
                        do {
                            apresentacao.printSaltaTempo();
                            apresentacao.printMensagemErro(1);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) { x = 1; break;}
                            if (c.equals("0")) { x = 0; break;}
                        } while (true);
                        break;
                    }

                    sistema.setTempoDevolucao(x);

                    apresentacao.printReturnTime();
                    apresentacao.printMensagem("ALTERACAO REALIZADA COM SUCESSO!!",87,3);
                    apresentacao.printEnterSair();
                    ler = new Scanner(System.in);
                    c = ler.nextLine();
                    x = 0;
                    break;
            }
        } while (x != 4);

        return 0;
    }

    public int runAvancaTempo() throws SistemaException {
        int x = 0, nDia, nMes, nAno;
        String[] s ={"Avançar para uma data", "Adicionar dias à data atual", "Retroceder"};
        String c, dia, mes, ano;
        Scanner ler;
        LocalDate localDate;

        do {
            switch (x) {
                case 0:
                    do {
                        apresentacao.printSaltaTempo();
                        apresentacao.printOpcoes("Selecione o metodo para avançar no tempo:", s);
                        apresentacao.printEspacos(103);
                        ler = new Scanner(System.in);
                        c = ler.nextLine();

                        if (c.equals("1")) { x = 1; break;}
                        if (c.equals("2")) { x = 2; break;}
                        if (c.equals("3")) { x = 3; break;}

                    } while (true);
                    break;

                case 1:
                    apresentacao.printSaltaTempo();
                    apresentacao.printMensagem("Introduza o dia (DD)",87,1);
                    System.out.println();
                    apresentacao.printEspacos(87);
                    ler = new Scanner(System.in);
                    dia = ler.nextLine();

                    if(!isInt(dia)){
                        do {
                            apresentacao.printSaltaTempo();
                            apresentacao.printMensagemErro(1);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) { x = 1; break;}
                            if (c.equals("0")) { x = 0; break;}
                        } while (true);
                        break;
                    }

                    nDia = stringToInt(dia);
                    if (nDia < 1 || nDia > 31) {
                        do {
                            apresentacao.printSaltaTempo();
                            apresentacao.printMensagemCentrada("DIA INVALIDO!!",2);
                            apresentacao.printClear(1);
                            apresentacao.printMensagemCentrada("DESEJA TENTAR DE NOVO?",0);
                            apresentacao.printMensagemSimOuNao(101);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) { x = 1; break;}
                            if (c.equals("0")) { x = 0; break;}
                        } while (true);
                        break;
                    }

                    System.out.println();

                    apresentacao.printMensagem("Introduza o mês (MM)",87,1);
                    System.out.println();
                    apresentacao.printEspacos(87);
                    ler = new Scanner(System.in);
                    mes = ler.nextLine();

                    if(!isInt(mes)) {
                        do {
                            apresentacao.printSaltaTempo();
                            apresentacao.printMensagemErro(1);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) { x = 1; break;}
                            if (c.equals("0")) { x = 0; break;}
                        } while (true);
                        break;
                    }

                    nMes = stringToInt(mes);
                    if (nMes < 1 || nMes > 12) {
                        do {
                            apresentacao.printSaltaTempo();
                            apresentacao.printMensagemCentrada("MES INVALIDO!!",2);
                            apresentacao.printClear(1);
                            apresentacao.printMensagemCentrada("DESEJA TENTAR DE NOVO?",0);
                            apresentacao.printMensagemSimOuNao(101);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) { x = 1; break;}
                            if (c.equals("0")) { x = 0; break;}
                        } while (true);
                        break;
                    }

                    System.out.println();

                    apresentacao.printMensagem("Introduza o ano (AAAA)",87,1);
                    System.out.println();
                    apresentacao.printEspacos(87);
                    ler = new Scanner(System.in);
                    ano = ler.nextLine();

                    if(!isInt(ano)) {
                        do {
                            apresentacao.printSaltaTempo();
                            apresentacao.printMensagemErro(1);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) { x = 1; break;}
                            if (c.equals("0")) { x = 0; break;}
                        } while (true);
                        break;
                    }

                    nAno = stringToInt(ano);

                    try {
                        sistema.saltaTempo(nAno, nMes, nDia);
                        localDate = this.sistema.getDataAtual();
                        System.out.println();
                        apresentacao.printEnterSair();

                        apresentacao.printSaltaTempo();
                        apresentacao.printMensagem("SALTO NO TEMPO REALIZADO COM SUCESSO!!", 84,3);
                        System.out.println();
                        apresentacao.printMensagemLocaldate("Data atual do sistema: ",86,1,localDate);
                        apresentacao.printEnterSair();
                        ler = new Scanner(System.in);
                        c = ler.nextLine();
                        x = 0;
                        break;
                    }
                    catch (SistemaException e) {
                        apresentacao.printClear(3);
                        apresentacao.printMensagemCentrada(e.getMessage(),2);
                        apresentacao.printEnter("");
                        ler = new Scanner(System.in);
                        c = ler.nextLine();
                        x = 0;
                    }
                    break;

                case 2:
                    apresentacao.printSaltaTempo();
                    apresentacao.printMensagem("Introduza o numero de dias que pretende avançar (DD)",78,1);
                    System.out.println();
                    apresentacao.printEspacos(78);
                    ler = new Scanner(System.in);
                    dia = ler.nextLine();

                    if(!isInt(dia)){
                        do {
                            apresentacao.printSaltaTempo();
                            apresentacao.printMensagemErro(1);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) { x = 2; break;}
                            if (c.equals("0")) { x = 0; break;}
                        } while (true);
                        break;
                    }

                    nDia = stringToInt(dia);

                    if (nDia < 0) {
                        do {
                            apresentacao.printSaltaTempo();
                            apresentacao.printMensagemCentrada("IMPOSSIVEL VOLTAR ATRAS NO TEMPO!!",2);
                            apresentacao.printClear(1);
                            apresentacao.printMensagemCentrada("DESEJA TENTAR DE NOVO?",0);
                            apresentacao.printMensagemSimOuNao(101);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) { x = 2; break;}
                            if (c.equals("0")) { x = 0; break;}
                        } while (true);
                        break;
                    }

                    sistema.saltaTempo(nDia);
                    localDate = this.sistema.getDataAtual();

                    apresentacao.printSaltaTempo();
                    apresentacao.printMensagem("SALTO NO TEMPO REALIZADO COM SUCESSO!!",84,3);
                    System.out.println();
                    apresentacao.printMensagemLocaldate("Data atual do sistema: ",86,1,localDate);
                    apresentacao.printEnterSair();
                    ler = new Scanner(System.in);
                    c = ler.nextLine();
                    x = 0;
                    break;
            }

        } while (x!=3);

        return 0;
    }

    public int runEncomendas(String email) throws ArtigoException, UtilizadorException, EncomendaException, TransportadoraException, SistemaException {
        int x = 0;
        String [] s = {"Pendentes", "Expedidas", "Finalizadas", "Devolvidas", "Retroceder"};
        Utilizador utilizador = sistema.procuraUtilizador(email);
        List<Encomenda> encomendas;
        Scanner ler = new Scanner(System.in);
        String eq;

        do {
            switch (x) {
                case 0: // MENU ENCOMENDAS

                    do {
                        apresentacao.printMenu(s,3,"");
                        eq = ler.nextLine().toLowerCase();
                        if (eq.equals("1")) { x = 1; break;}
                        if (eq.equals("2")) { x = 2; break;}
                        if (eq.equals("3")) { x = 3; break;}
                        if (eq.equals("4")) { x = 4; break;}
                        if (eq.equals("5")) { x = 5; break;}
                    } while (true);
                    break;

                case 1: // MENU PENDENTES
                    //encomendas = utilizador.getListaEncomendas(Atributos.PENDENTE);
                    //apresentacao.paginateEncomendas(encomendas,5, email, sistema,1);
                    runListarEncomendas(email,Atributos.PENDENTE);
                    x = 0;
                    break;

                case 2: //MENU EXPEDIDAS
                    //encomendas = utilizador.getListaEncomendas(Atributos.EXPEDIDA);
                    //apresentacao.paginateEncomendas(encomendas,5, email, sistema,2);
                    runListarEncomendas(email,Atributos.EXPEDIDA);
                    x = 0;
                    break;

                case 3: //MENU FINALIZADAS
                    //encomendas = utilizador.getListaEncomendas(Atributos.FINALIZADA);
                    //apresentacao.paginateEncomendas(encomendas,5, email, sistema,3);
                    runListarEncomendas(email,Atributos.FINALIZADA);
                    x = 0;
                    break;

                case 4:
                    encomendas = utilizador.getListaEncomendas(Atributos.DEVOLVIDA);
                    apresentacao.paginateEncomendas(encomendas,5, email, sistema,4);
                    x = 0;
                    break;
            }

        } while (x != 5);

        return 0;
    }

    public int runEncomendaPendente(String email, int id) throws EncomendaException, UtilizadorException, TransportadoraException, ArtigoException {
        String opcao;
        Scanner ler = new Scanner(System.in);
        int paginaAtual = 1;
        do {
            List<Artigo> artigos = sistema.procuraEncomenda(id, email).getListaArtigos();
            if (artigos.isEmpty()) {
                apresentacao.printBox(); //90
                apresentacao.printMensagem("Esta encomenda não possui artigos!", 87, 2);
                apresentacao.printEnter("");
                ler.nextLine();
                break;
            }
            List<String> strings = new ArrayList<>();
            for (Artigo artigo : artigos) {
                strings.add(apresentacao.showArtigoString(artigo, sistema.getDataAtual().getYear()));
            }
            int quantidade = 2;
            int numPaginas = (int) Math.ceil((double) strings.size() / quantidade);
            int inicio = (paginaAtual - 1) * quantidade, fim = Math.min(inicio + quantidade, strings.size());
            apresentacao.printPendentes();
            apresentacao.paginateMenu(strings, quantidade, paginaAtual, numPaginas, inicio, fim);
            apresentacao.printClear(1);
            System.out.println(Apresentacao.CYAN_BOLD + "                    Vendedor: " + Apresentacao.RESET + sistema.procuraEncomenda(id, email).getVendedor().getEmail() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Valor dos artigos: " + Apresentacao.RESET + sistema.procuraEncomenda(id, email).calculaValorArtigos() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Valor Taxas Artigos: " + Apresentacao.RESET + sistema.procuraEncomenda(id,email).calculaTaxaArtigos() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Valor Taxa Expedição: " + Apresentacao.RESET + sistema.procuraEncomenda(id,email).calculaValorExpedicao() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Preço Total: " + Apresentacao.RESET + sistema.procuraEncomenda(id,email).getPrecoFinal() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Data de Criação: " + Apresentacao.RESET + sistema.procuraEncomenda(id, email).getDataCriacao().toString());
            apresentacao.printClear(1);
            System.out.println(Apresentacao.CYAN_BOLD + "                             Pressione" + Apresentacao.RESET + " '+' " +
                    Apresentacao.CYAN_BOLD + "para avancar," + Apresentacao.RESET + " '-' " + Apresentacao.CYAN_BOLD + "para a retroceder," + Apresentacao.RESET + " 'c' " + Apresentacao.CYAN_BOLD +
                    "para confirmar encomenda," + Apresentacao.RESET + " 'r' " + Apresentacao.CYAN_BOLD + "remover um artigo da encomenda," + Apresentacao.RESET + " 's' " + Apresentacao.CYAN_BOLD +
                    "para sair" + Apresentacao.RESET);
            apresentacao.printClear(1);
            apresentacao.printEspacos(102);
            opcao = ler.nextLine().toLowerCase();

            if (opcao.equals("+") && paginaAtual < numPaginas) {
                paginaAtual++;
            } else if (opcao.equals("-") && paginaAtual > 1) {
                paginaAtual--;
            } else if (opcao.equals("c")) {
                sistema.confirmaEncomenda(id, email);
                apresentacao.printMensagem("ENCOMENDA CONFIRMADA COM SUCESSO!!", 88, 3);
                apresentacao.printEnter("");
                ler.nextLine();
                break;
            } else if (opcao.equals("r")) {
                apresentacao.printMensagem("INTRODUZA O ID DO ARTIGO QUE DESEJA REMOVER", 82, 2);
                apresentacao.printEspacos(103);
                String idArtigo = ler.nextLine().toUpperCase();
                sistema.removeArtigoEncomenda(idArtigo, email);
                apresentacao.printClear(2);
                apresentacao.printMensagem("ARTIGO REMOVIDO COM SUCESSO!!", 89, 3);
                apresentacao.printEnter("");
                ler.nextLine();
            } else if (opcao.equals("s")) {
                break;
            }
        } while (true);
        return 0;
    }

    public int runEncomendaExpedida(String email, int id) throws EncomendaException, UtilizadorException, TransportadoraException, ArtigoException {
        String opcao;
        Scanner ler = new Scanner(System.in);
        int paginaAtual = 1;
        do {
            List<Artigo> artigos = sistema.procuraEncomenda(id, email).getListaArtigos();
            if (artigos.isEmpty()) {
                apresentacao.printBox(); //90
                apresentacao.printMensagem("Esta encomenda não possui artigos!", 87, 2);
                apresentacao.printEnter("");
                ler.nextLine();
                break;
            }
            List<String> strings = new ArrayList<>();
            for (Artigo artigo : artigos) {
                strings.add(apresentacao.showArtigoString(artigo, sistema.getDataAtual().getYear()));
            }
            int quantidade = 2;
            int numPaginas = (int) Math.ceil((double) strings.size() / quantidade);
            int inicio = (paginaAtual - 1) * quantidade, fim = Math.min(inicio + quantidade, strings.size());
            apresentacao.printExpedidas();
            apresentacao.paginateMenu(strings, quantidade, paginaAtual, numPaginas, inicio, fim);
            apresentacao.printClear(1);
            System.out.println(Apresentacao.CYAN_BOLD + "                    Vendedor: " + Apresentacao.RESET + sistema.procuraEncomenda(id, email).getVendedor().getEmail() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Valor dos artigos: " + Apresentacao.RESET + sistema.procuraEncomenda(id, email).calculaValorArtigos() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Valor Taxas Artigos: " + Apresentacao.RESET + sistema.procuraEncomenda(id,email).calculaTaxaArtigos() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Valor Taxa Expedição: " + Apresentacao.RESET + sistema.procuraEncomenda(id,email).calculaValorExpedicao() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Preço Total: " + Apresentacao.RESET + sistema.procuraEncomenda(id,email).getPrecoFinal() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Data Prevista de Entrega: " + Apresentacao.RESET + sistema.procuraEncomenda(id, email).getDataPrevistaEntrega());
            apresentacao.printClear(1);
            apresentacao.printEspacos(74);
            System.out.println(Apresentacao.CYAN_BOLD + "Pressione" + Apresentacao.RESET + " '+' " +
                    Apresentacao.CYAN_BOLD + "para avancar," + Apresentacao.RESET + " '-' " + Apresentacao.CYAN_BOLD + "para a retroceder," + Apresentacao.RESET + " 's' " + Apresentacao.CYAN_BOLD +
                    "para sair" + Apresentacao.RESET);
            apresentacao.printClear(1);
            apresentacao.printEspacos(102);
            opcao = ler.nextLine().toLowerCase();

            if (opcao.equals("+") && paginaAtual < numPaginas) {
                paginaAtual++;
            } else if (opcao.equals("-") && paginaAtual > 1) {
                paginaAtual--;
            }  else if (opcao.equals("s")) {
                break;
            }
        } while (true);
        return 0;
    }

    public int runEncomendaFinalizada(String email, int id) throws EncomendaException, UtilizadorException, TransportadoraException, ArtigoException {
        String opcao;
        Scanner ler = new Scanner(System.in);
        int paginaAtual = 1;
        do {
            List<Artigo> artigos = sistema.procuraEncomenda(id, email).getListaArtigos();
            if (artigos.isEmpty()) {
                apresentacao.printBox(); //90
                apresentacao.printMensagem("Esta encomenda não possui artigos!", 87, 2);
                apresentacao.printEnter("");
                ler.nextLine();
                break;
            }
            List<String> strings = new ArrayList<>();
            for (Artigo artigo : artigos) {
                strings.add(apresentacao.showArtigoString(artigo, sistema.getDataAtual().getYear()));
            }
            int quantidade = 2;
            int numPaginas = (int) Math.ceil((double) strings.size() / quantidade);
            int inicio = (paginaAtual - 1) * quantidade, fim = Math.min(inicio + quantidade, strings.size());
            apresentacao.printFinalizadas();
            apresentacao.paginateMenu(strings, quantidade, paginaAtual, numPaginas, inicio, fim);
            apresentacao.printClear(1);
            System.out.println(Apresentacao.CYAN_BOLD + "   Vendedor: " + Apresentacao.RESET + sistema.procuraEncomenda(id, email).getVendedor().getEmail() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Valor dos artigos: " + Apresentacao.RESET + sistema.procuraEncomenda(id, email).calculaValorArtigos() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Valor Taxas Artigos: " + Apresentacao.RESET + sistema.procuraEncomenda(id,email).calculaTaxaArtigos() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Valor Taxa Expedição: " + Apresentacao.RESET + sistema.procuraEncomenda(id,email).calculaValorExpedicao() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Preço Total: " + Apresentacao.RESET + sistema.procuraEncomenda(id,email).getPrecoFinal() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Data de Entrega: " + Apresentacao.RESET + sistema.procuraEncomenda(id, email).getDataAtualizacao() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Data limite de Devolução: " + Apresentacao.RESET + sistema.procuraEncomenda(id,email).getDataAtualizacao().plusDays(sistema.getTempoDevolucao()) + Apresentacao.YELLOW);
            apresentacao.printClear(1);
            System.out.println(Apresentacao.CYAN_BOLD + "                                                        Pressione" + Apresentacao.RESET + " '+' " +
                    Apresentacao.CYAN_BOLD + "para avancar," + Apresentacao.RESET + " '-' " + Apresentacao.CYAN_BOLD + "para a retroceder," + Apresentacao.RESET + " 'd' " + Apresentacao.CYAN_BOLD + "para devolver a encomenda" + Apresentacao.RESET + " 's' " + Apresentacao.CYAN_BOLD +
                    "para sair" + Apresentacao.RESET);
            apresentacao.printClear(1);
            apresentacao.printEspacos(102);
            opcao = ler.nextLine().toLowerCase();

            if (opcao.equals("+") && paginaAtual < numPaginas) {
                paginaAtual++;
            } else if (opcao.equals("-") && paginaAtual > 1) {
                paginaAtual--;
            } else if (opcao.equals("d")) {
                try {
                    sistema.devolveEncomenda(email, id);
                    apresentacao.printMensagem("ENCOMENDA DEVOLVIDA COM SUCESSO!!", 88, 3);
                    apresentacao.printEnter("");
                    ler.nextLine();
                    break;
                }
                catch (EncomendaException e) {
                    apresentacao.printMensagem(e.getMessage(),85,2);
                    apresentacao.printEnter("");
                    ler.nextLine();
                }
            } else if (opcao.equals("s")) {
                break;
            }
        } while (true);
        return 0;
    }

    public int runEncomendaDevolvidas(String email, int id) throws EncomendaException, UtilizadorException, TransportadoraException, ArtigoException {
        String opcao;
        Scanner ler = new Scanner(System.in);
        int paginaAtual = 1;
        do {
            List<Artigo> artigos = sistema.procuraEncomenda(id, email).getListaArtigos();
            if (artigos.isEmpty()) {
                apresentacao.printBox(); //90
                apresentacao.printMensagem("Esta encomenda não possui artigos!", 87, 2);
                apresentacao.printEnter("");
                ler.nextLine();
                break;
            }
            List<String> strings = new ArrayList<>();
            for (Artigo artigo : artigos) {
                strings.add(apresentacao.showArtigoString(artigo, sistema.getDataAtual().getYear()));
            }
            int quantidade = 2;
            int numPaginas = (int) Math.ceil((double) strings.size() / quantidade);
            int inicio = (paginaAtual - 1) * quantidade, fim = Math.min(inicio + quantidade, strings.size());
            apresentacao.printPendentes();
            apresentacao.paginateMenu(strings, quantidade, paginaAtual, numPaginas, inicio, fim);
            apresentacao.printClear(1);
            System.out.println(Apresentacao.CYAN_BOLD + "Vendedor: " + Apresentacao.RESET + sistema.procuraEncomenda(id, email).getVendedor().getEmail() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Valor dos artigos: " + Apresentacao.RESET + sistema.procuraEncomenda(id, email).calculaValorArtigos() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Valor Taxas Artigos: " + Apresentacao.RESET + sistema.procuraEncomenda(id,email).calculaTaxaArtigos() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Valor Taxa Expedição: " + Apresentacao.RESET + sistema.procuraEncomenda(id,email).calculaValorExpedicao() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Preço Total: " + Apresentacao.RESET + sistema.procuraEncomenda(id,email).getPrecoFinal() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Data de Devolução: " + Apresentacao.RESET + sistema.procuraEncomenda(id, email).getDataAtualizacao().toString());
            System.out.println(Apresentacao.CYAN_BOLD + "                                                        Pressione" + Apresentacao.RESET + " '+' " +
                    Apresentacao.CYAN_BOLD + "para avancar," + Apresentacao.RESET + " '-' " + Apresentacao.CYAN_BOLD + "para a retroceder," + Apresentacao.RESET + " 's' " + Apresentacao.CYAN_BOLD +
                    "para sair" + Apresentacao.RESET);
            apresentacao.printClear(1);
            apresentacao.printEspacos(102);
            opcao = ler.nextLine().toLowerCase();

            if (opcao.equals("+") && paginaAtual < numPaginas) {
                paginaAtual++;
            } else if (opcao.equals("-") && paginaAtual > 1) {
                paginaAtual--;
            } else if (opcao.equals("s")) {
                break;
            }
        } while (true);
        return 0;
    }

    public void runListarEncomendas(String email, int estado) throws UtilizadorException, EncomendaException, ArtigoException, TransportadoraException {
        String opcao;
        Scanner ler = new Scanner(System.in);
        int paginaAtual = 1;
        do {
            List<Encomenda> encomendas = sistema.procuraUtilizador(email).getListaEncomendas(estado).stream().collect(Collectors.toList());
            if (encomendas.isEmpty())
            {
                apresentacao.printClear(2);
                apresentacao.printBox();
                apresentacao.printClear(3);
                apresentacao.printMensagem("NÃO EXISTEM ENCOMENDAS " + apresentacao.estadoEncomendaString(estado), 88, 3);
                apresentacao.printClear(2);
                apresentacao.printEnter("");
                ler.nextLine();
                break;
            }
            List<String> strings = new ArrayList<>();
            for (Encomenda encomenda: encomendas)
            {
                strings.add(apresentacao.showEncomenda(encomenda,sistema.getTempoDevolucao()));
            }
            int quantidade = 5;
            int numPaginas = (int) Math.ceil((double) strings.size() / quantidade);
            int inicio = (paginaAtual - 1) * quantidade, fim = Math.min(inicio + quantidade, strings.size());

                apresentacao.printEncomendas();
                System.out.println(Apresentacao.RED + "[ENCOMENDAS " + apresentacao.estadoEncomendaString(estado) + "]\n" + Apresentacao.RESET);
                apresentacao.paginateMenu(strings,quantidade,paginaAtual,numPaginas,inicio,fim);
                apresentacao.printClear(1);
                System.out.println(Apresentacao.CYAN_BOLD + "                                                        Pressione" + Apresentacao.RESET + " '+' " +
                        Apresentacao.CYAN_BOLD + "para avancar," + Apresentacao.RESET + " '-' " + Apresentacao.CYAN_BOLD + "para a retroceder," + Apresentacao.RESET + " 'v' " + Apresentacao.CYAN_BOLD +
                        "para ver/editar uma encomenda,"  + Apresentacao.RESET + " 's' " + Apresentacao.CYAN_BOLD +
                        "para sair" + Apresentacao.RESET);
                apresentacao.printClear(1);
                apresentacao.printEspacos(105);
                opcao = ler.nextLine().toLowerCase();
                if (opcao.equals("+") && paginaAtual < numPaginas) {
                    paginaAtual++;
                } else if (opcao.equals("-") && paginaAtual > 1) {
                    paginaAtual--;
                } else if (opcao.equals("v"))
                {
                    apresentacao.printMensagem("INTRODUZA O ID DA ENCOMENDA QUE DESEJA VER/EDITAR",81, 3);
                    apresentacao.printClear(1);
                    apresentacao.printEspacos(105);
                    int idEncomenda = ler.nextInt();
                    if (sistema.verificaEstadoEncomenda(email,idEncomenda,estado))
                    {
                        if (estado == Atributos.PENDENTE) runEncomendaPendente(email, idEncomenda);
                        if (estado == Atributos.EXPEDIDA) runEncomendaExpedida(email, idEncomenda);
                        if (estado == Atributos.FINALIZADA) runEncomendaFinalizada(email, idEncomenda);
                        if (estado == Atributos.DEVOLVIDA) runEncomendaDevolvidas(email, idEncomenda);
                    }
                    else
                    {
                        apresentacao.printMensagem("ENCOMENDA NÃO ENCONTRADA",94,2);
                        apresentacao.printEnter("");
                        ler = new Scanner(System.in);
                        ler.nextLine();
                    }
                } else if (opcao.equals("s"))
                {
                    break;
                }
        }while (true);
    }

    public int runEstatisticas() throws ArtigoException, UtilizadorException, EncomendaException, TransportadoraException, SistemaException {
        int x = 0;
        String [] s = {"Vendedor que mais facturou desde sempre ou num período de tempo", "Transportadora com maior facturação", "Encomendas de um vendedor", "Maiores Vendedores/Compradores" +
                " em um período de tempo", "Ganhos Vintage", "Retroceder"};
        String d1,d2,c, email;
        LocalDate data1, data2;
        Scanner ler = new Scanner(System.in);
        String eq;

        do {
            switch (x){
                case 0:

                    do {
                        apresentacao.printMenu(s, 4, "");
                        eq = ler.nextLine().toLowerCase();
                        if (eq.equals("1")) { x = 1; break;}
                        if (eq.equals("2")) { x = 2; break;}
                        if (eq.equals("3")) { x = 3; break;}
                        if (eq.equals("4")) { x = 4; break;}
                        if (eq.equals("5")) { x = 5; break;}
                        if (eq.equals("6")) { x = 6; break;}
                    } while (true);
                    break;

                case 1:
                    apresentacao.printVendedorDinheiro();
                    String[] o = {"DE SEMPRE", "PERÍODO DE TEMPO"};
                    apresentacao.printOpcoes("INDIQUE A OPÇÃO QUE PRETENDE",o);
                    apresentacao.printEspacos(93);
                    ler = new Scanner(System.in);
                    x = ler.nextInt();

                    if (x == 1){
                        apresentacao.printVendedorDinheiro();
                        System.out.println();
                        Utilizador utilizador = sistema.vendedorMaisFaturouSempre();
                        System.out.print(utilizador.toString());
                        apresentacao.printFaturacao("Faturou: ",99,1,utilizador.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == Atributos.VENDA).mapToDouble(Fatura::getValorTotal).sum());
                        apresentacao.printEnterSair();
                        ler = new Scanner(System.in);
                        c = ler.nextLine();
                        x = 0;
                        break;
                    } else if (x == 0) {
                        apresentacao.printVendedorDinheiro();
                        System.out.println();

                        ler = new Scanner(System.in);

                        apresentacao.printMensagem("INSIRA A DATA INICIAL (AAAA-MM-DD)",87,1);
                        apresentacao.printEspacos(87);
                        d1 = ler.nextLine();
                        data1 = stringParaData(d1);
                        System.out.println();
                        apresentacao.printMensagem("INSIRA A DATA FINAL (AAAA-MM-DD",87,1);
                        apresentacao.printEspacos(87);
                        d2 = ler.nextLine();
                        data2 = stringParaData(d2);
                        Utilizador utilizador = sistema.vendedorMaisFaturouEntreDatas(data1, data2);
                        apresentacao.printVendedorDinheiro();
                        System.out.println();
                        System.out.print(utilizador.toString());
                        apresentacao.printFaturacao("Faturou: ",99,1,utilizador.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == Atributos.VENDA).mapToDouble(Fatura::getValorTotal).sum());
                        apresentacao.printEnterSair();
                        ler = new Scanner(System.in);
                        c = ler.nextLine();
                        x = 0;
                        break;
                    }
                    break;

                case 2:
                    apresentacao.printTransportadoraDinheiro();
                    System.out.println();
                    Transportadora transportadora = sistema.transportadoraMaiorFaturacao();
                    System.out.println();
                    System.out.println(transportadora.toString());
                    apresentacao.printEnterSair();
                    ler = new Scanner(System.in);
                    c = ler.nextLine();
                    x = 0;
                    break;

                case 3:
                    apresentacao.printEncomendasVendedor();
                    System.out.println();
                    apresentacao.printMensagem("INTRODUZA O EMAIL DO VENDEDOR",88,1);
                    apresentacao.printEspacos(88);
                    ler = new Scanner(System.in);
                    email = ler.nextLine();

                    List<Encomenda> listaEncomendas = sistema.listaEncomendasVendedor(email);
                    if (listaEncomendas.isEmpty()){
                        apresentacao.printMensagem("NÃO EXISTEM ENCOMENDAS PARA ESTE UTILIZADOR!",82,2);
                        apresentacao.printEnterSair();
                        c = ler.nextLine();
                        x = 0;
                        break;
                    }
                    Encomenda[] lista = new Encomenda[listaEncomendas.size()];
                    int i;
                    System.out.println();
                    listaEncomendas.toArray(lista);
                    for (i = 0; i < lista.length; i++){
                        Encomenda encomenda = lista[i];
                        apresentacao.printEspacos(10);
                        System.out.print(apresentacao.showEncomenda(encomenda, sistema.getTempoDevolucao()));
                        apresentacao.printEnterSair();
                    }
                    ler = new Scanner(System.in);
                    c = ler.nextLine();
                    x = 0;
                    break;

                case 4:
                    apresentacao.printCompradorVendedor();
                    String[] ops = {"COMPRADOR", "VENDEDOR"};
                    apresentacao.printOpcoes("INDIQUE A SUA ESCOLHA",ops);
                    //System.out.println();
                    //System.out.println(apresentacao.CYAN_BOLD +"                                                                                                  INDIQUE A SUA ESCOLHA" + apresentacao.RESET);
                    //System.out.println();
                    //System.out.println("                                                                                                     1 - COMPRADOR");
                    //System.out.println("                                                                                                     0 - VENDEDOR");
                    //System.out.println();
                    apresentacao.printEspacos(86);
                    //System.out.print("                                                                                                          ");

                    ler = new Scanner(System.in);
                    int op = ler.nextInt();

                    if (op == 1){
                        apresentacao.printMensagem("INSIRA A DATA INICIAL (AAAA-MM-DD)",86,1);
                        apresentacao.printEspacos(86);
                        ler = new Scanner(System.in);
                        d1 = ler.nextLine();
                        data1 = stringParaData(d1);
                        apresentacao.printMensagem("INSIRA A DATA FINAL (AAAA-MM-DD)",86,1);
                        apresentacao.printEspacos(86);
                        ler = new Scanner(System.in);
                        d2 = ler.nextLine();
                        data2 = stringParaData(d2);

                        apresentacao.paginateCompradorVendedor(sistema.maioresUtilizadoresEntreDatas(data1, data2, Atributos.VENDIDO), 2);
                        x = 0;
                        break;
                    }
                    else if (op == 0){
                        apresentacao.printMensagem("INSIRA A DATA INICIAL (AAAA-MM-DD)",86,1);
                        apresentacao.printEspacos(86);
                        ler = new Scanner(System.in);
                        d1 = ler.nextLine();
                        data1 = stringParaData(d1);
                        apresentacao.printMensagem("INSIRA A DATA FINAL (AAAA-MM-DD)",86,1);
                        apresentacao.printEspacos(86);
                        ler = new Scanner(System.in);
                        d2 = ler.nextLine();
                        data2 = stringParaData(d2);

                        apresentacao.paginateCompradorVendedor(sistema.maioresUtilizadoresEntreDatas(data1, data2, Atributos.VENDA), 2);
                        x = 0;
                        break;

                    }

                case 5:
                    apresentacao.printVintageDinheiro();
                    apresentacao.printClear(3);
                    apresentacao.printGanhos("$GANHOS$ : ",96,1, sistema.ganhoVintage());
                    //System.out.println(apresentacao.CYAN_BOLD + "                                                                                                        $GANHOS$ : "+ apresentacao.RESET + sistema.ganhoVintage());
                    apresentacao.printEnterSair();
                    ler = new Scanner(System.in);
                    c = ler.nextLine();
                    x = 0;
                    break;
            }
        }while (x != 6);

        return 0;
    }

    public LocalDate stringParaData(String dma){
        String[] dataf = dma.split("-");
        int ano = Integer.valueOf(dataf[0]);
        int mes = Integer.valueOf(dataf[1]);
        int dia = Integer.valueOf(dataf[2]);
        return LocalDate.of(ano,mes,dia);
    }
}
