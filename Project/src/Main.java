import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
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

    private void run() throws UtilizadorException, TransportadoraException, IOException, ClassNotFoundException, ArtigoException, EncomendaException, SistemaException {
        int x = 0;
        String c;
        String[] s = {"Entrar no programa", "Guardar estado", "Carregar estado anterior", "Carregar ficheiro para o sistema", "Estatísticas", "Sair"};
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
                    ler = new Scanner(System.in);
                    input = ler.nextLine();
                    CarregamentoFicheiro.escreveFicheiro(this.sistema, input);
                    apresentacao.printGuardar();
                    apresentacao.printEnter("ESTADO GUARDADO COM SUCESSO!!");
                    ler = new Scanner(System.in);
                    ler.nextLine();
                    x = 0;
                    break;

                case 3:
                    apresentacao.printMenuCarregarEstado();
                    ler = new Scanner(System.in);
                    input = ler.nextLine();
                    Path path = Paths.get(input);
                    boolean existe = Files.exists(path);
                    if (existe) {
                        this.sistema = CarregamentoFicheiro.lerFicheiro(input);
                        apresentacao.printLoad();
                        apresentacao.printEnter("ESTADO CARREGADO COM SUCESSO!!");
                        ler = new Scanner(System.in);
                        ler.nextLine();
                        x = 0;
                        break;
                    }
                    else {x = 3; break;} //TODO Verificar mensagem erro quando ficheiro não existe!

                case 4 :
                    apresentacao.printMenuAutomatizacao();
                    ler = new Scanner(System.in);
                    input_backup = ler.nextLine();
                    Automatizacao backup = new Automatizacao(input_backup);
                    backup.carregaFicheiro(this.sistema);
                    if (!backup.getExcecoes().isEmpty()) {
                        apresentacao.printErrosAutomatizcao(backup.getExcecoes());
                        ler = new Scanner(System.in);
                        c = ler.nextLine();
                    }
                    apresentacao.clear();
                    apresentacao.printBackup();
                    apresentacao.printEnter("AUTOMATIZAÇÃO EXECUTADA COM SUCESSO!!");
                    ler = new Scanner(System.in);
                    ler.nextLine();
                    x = 0;
                    break;

                case 5:
                    x = runEstatisticas();
                    break;
            }
        } while (x != 6);
    }

    private int runPrograma() throws UtilizadorException, TransportadoraException, ArtigoException, SistemaException, EncomendaException {
        int x = 0;
        String email, pass, nome, morada, nomeTrans;
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
                        if (eq.equals("1")) { x = 1; break;}
                        if (eq.equals("2")) { x = 2; break;}
                        if (eq.equals("3")) { x = 3; break;}
                        if (eq.equals("4")) { x = 4; break;}
                        if (eq.equals("5")) { x = 5; break;}
                        if (eq.equals("6")) { x = 6; break;}
                    } while (true);
                    break;

                case 1: //Iniciar sessao
                    apresentacao.printLogin();
                    apresentacao.printMensagem("Insira o seu email:", 88, 1);
                    apresentacao.printEspacos(88);
                    ler = new Scanner(System.in);
                    email = ler.nextLine();
                    if (sistema.verificaUtilizador(email)) {
                        apresentacao.printMensagem("Insira a sua password:", 88, 1);
                        apresentacao.printEspacos(88);
                        ler = new Scanner(System.in);
                        pass = ler.nextLine();
                        if (sistema.verificaPassword(email, pass)) {
                            x = runUtilizador(email);
                            break;
                        } else {
                            while (x == 1) {
                                apresentacao.printClear(2);
                                apresentacao.printMensagemCentrada("PASSWORD INCORRETA!! DESEJA CONTINUAR A TENTAR?",2);
                                apresentacao.printMensagemSimOuNao(102);
                                apresentacao.printEspacos(104);

                                x = ler.nextInt();
                                if (x == 0) break;

                                apresentacao.printLogin();
                                apresentacao.printMensagem("Insira o seu email:", 88, 1);
                                apresentacao.printEspacos(88);
                                System.out.print(email);
                                apresentacao.printClear(1);
                                apresentacao.printMensagem("Insira a sua password:", 88, 1);
                                apresentacao.printEspacos(88);

                                ler = new Scanner(System.in);
                                pass = ler.nextLine();
                                if (sistema.verificaPassword(email, pass)) {
                                    x = runUtilizador(email);
                                    break;
                                }
                            }
                        }
                    } else {
                        while (x == 1) {
                            apresentacao.printMensagem("EMAIL INCORRETO OU NAO EXISTE!! DESEJA CONTINUAR A TENTAR?",74,2);
                            apresentacao.printMensagemSimOuNao(100);
                            apresentacao.printEspacos(102);

                            x = ler.nextInt();

                            if (x == 0) break;

                            apresentacao.printLogin();
                            apresentacao.printMensagem("Insira o seu email:", 88, 1);
                            apresentacao.printEspacos(88);

                            ler = new Scanner(System.in);
                            email = ler.nextLine();

                            if (sistema.verificaUtilizador(email)) {
                                apresentacao.printClear(1);
                                apresentacao.printMensagem("Insira a sua password:", 88, 1);
                                apresentacao.printEspacos(88);

                                ler = new Scanner(System.in);
                                pass = ler.nextLine();
                                if (sistema.verificaPassword(email, pass)) {
                                    x = runUtilizador(email);
                                    break;
                                } else {
                                    while (x == 1) {
                                        apresentacao.printClear(3);
                                        apresentacao.printMensagemCentrada("PASSWORD INCORRETA!! DESEJA CONTINUAR A TENTAR",2);
                                        apresentacao.printClear(1);
                                        apresentacao.printMensagemSimOuNao(101);
                                        apresentacao.printEspacos(103);
                                        x = ler.nextInt();

                                        if (x == 0) break;

                                        apresentacao.printLogin();
                                        apresentacao.printMensagem("Insira o seu email:", 88, 1);
                                        apresentacao.printEspacos(88);
                                        System.out.print(email);
                                        apresentacao.printClear(1);
                                        apresentacao.printMensagem("Insira a sua password:", 88, 1);
                                        apresentacao.printEspacos(88);

                                        ler = new Scanner(System.in);
                                        pass = ler.nextLine();
                                        if (sistema.verificaPassword(email, pass)) {
                                            x = runUtilizador(email);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;

                case 2:
                    apresentacao.printProcuraTrans();

                    System.out.println(apresentacao.YELLOW + "                                                                             Introduza a transportadora que pretende procurar:" + apresentacao.RESET);
                    System.out.println();
                    System.out.print("                                                                             ");
                    ler = new Scanner(System.in);
                    nomeTrans = ler.nextLine();
                    Transportadora transportadora = sistema.procuraTransportadora(nomeTrans);
                    apresentacao.printProcuraTrans();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                                  Nome: " + apresentacao.RESET + transportadora.getNome());
                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                      Margem Lucro: " + apresentacao.RESET + transportadora.getMargemLucro() + apresentacao.CYAN_BOLD +

                            " | Tipo: " + apresentacao.RESET + transportadora.getTipo() +  apresentacao.CYAN_BOLD + " | Imposto: " + apresentacao.RESET + sistema.getTaxas().getImposto() + apresentacao.CYAN_BOLD + " | Taxas: " + apresentacao.RESET + sistema.getTaxas().getTaxaEncPequena() + apresentacao.CYAN_BOLD + " (Pequena), " + apresentacao.RESET + sistema.getTaxas().getTaxaEncMedia() + apresentacao.CYAN_BOLD+ " (Média), " + apresentacao.RESET + sistema.getTaxas().getTaxaEncGrande() + apresentacao.CYAN_BOLD + " (Grande)" + apresentacao.RESET);
                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                                Encomendas:" + apresentacao.RESET);
                    System.out.println();
                    System.out.println(apresentacao.YELLOW + "                                                                                      Pressione enter para continuar..." + Apresentacao.RESET);
                    System.out.println();
                    System.out.print("                                                                                                     ");
                    ler = new Scanner(System.in);
                    String c = ler.nextLine();
                    x = 0;
                    break;

                case 3: //Registar utilizador
                    apresentacao.printReg();

                    apresentacao.cyan();
                    System.out.println("                                                                                      Insira o email:");
                    apresentacao.resetColor();
                    System.out.print("                                                                                      ");
                    ler = new Scanner(System.in);
                    email = ler.nextLine();
                    if (!sistema.verificaUtilizador(email)) {
                        System.out.println();

                        apresentacao.cyan();
                        System.out.println("                                                                                      Insira a password:");
                        apresentacao.resetColor();
                        System.out.print("                                                                                      ");
                        ler = new Scanner(System.in);
                        pass = ler.nextLine();

                        System.out.println();

                        apresentacao.cyan();
                        System.out.println("                                                                                      Insira o seu primeiro e ultimo nome:");
                        apresentacao.resetColor();
                        System.out.print("                                                                                      ");
                        ler = new Scanner(System.in);
                        nome = ler.nextLine();

                        System.out.println();

                        apresentacao.cyan();
                        System.out.println("                                                                                      Insira a sua morada:");
                        apresentacao.resetColor();
                        System.out.print("                                                                                      ");

                        ler = new Scanner(System.in);
                        morada = ler.nextLine();

                        System.out.println();


                        apresentacao.cyan();
                        System.out.println("                                                                                      Insira o seu numero fiscal:");
                        apresentacao.resetColor();
                        System.out.print("                                                                                      ");
                        ler = new Scanner(System.in);
                        nif = ler.nextInt();
                        sistema.adicionaUtilizador(email, pass, nome, morada, nif);

                        apresentacao.printReg();
                        apresentacao.yellow();
                        System.out.println("                                                                        UTILIZADOR REGISTADO COM SUCESSO!! DESEJA CONTINUAR A REGISTAR?");
                        apresentacao.resetColor();
                        System.out.println();
                        System.out.println("                                                                                                    1 - SIM");
                        System.out.println("                                                                                                    0 - NAO");
                        System.out.println();
                        System.out.print("                                                                                                      ");

                        ler = new Scanner(System.in);
                        x = ler.nextInt();

                        if (x == 1) x = 3;
                        else x = 0;
                    } else {
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.println(apresentacao.RED + "                                                                                 ESTE EMAIL JÁ EXISTE!! DESEJA TENTAR DE NOVO?");
                        System.out.println();
                        apresentacao.resetColor();
                        System.out.println();
                        System.out.println("                                                                                                   1 - SIM");
                        System.out.println("                                                                                                   0 - NAO");
                        System.out.println();
                        System.out.print("                                                                                                      ");
                        ler = new Scanner(System.in);
                        x = ler.nextInt();
                        if (x == 1) x = 3;
                        else x = 0;
                    }

                    break;

                case 4:
                    apresentacao.printRegTrans();
                    apresentacao.cyan();
                    System.out.println("                                                                                      Insira o nome da transportadora:");
                    apresentacao.resetColor();
                    System.out.print("                                                                                      ");
                    ler = new Scanner(System.in);
                    nomeTrans = ler.nextLine();
                    if (!sistema.verificaTransportadora(nomeTrans)) {

                        System.out.println();

                        apresentacao.cyan();
                        System.out.println("                                                                                      Insira a margem de lucro que pretende obter:");
                        apresentacao.resetColor();
                        System.out.print("                                                                                      ");
                        ler = new Scanner(System.in);
                        lucro = ler.nextDouble();

                        System.out.println();

                        apresentacao.cyan();
                        System.out.println("                                                                                      Insira 1-\"Normal\" ou 2-\"Premium\":");
                        apresentacao.resetColor();
                        System.out.print("                                                                                      ");
                        ler = new Scanner(System.in);
                        tipo = ler.nextInt();

                        sistema.adicionaTransportadora(nomeTrans, lucro, tipo,2);

                        apresentacao.printRegTrans();
                        apresentacao.yellow();
                        System.out.println("                                                                       TRANSPORTADORA REGISTADA COM SUCESSO!! DESEJA CONTINUAR A REGISTAR?");
                        apresentacao.resetColor();
                        System.out.println();
                        System.out.println("                                                                                                    1 - SIM");
                        System.out.println("                                                                                                    0 - NAO");
                        System.out.println();
                        System.out.print("                                                                                                      ");

                        ler = new Scanner(System.in);
                        x = ler.nextInt();

                        if (x == 1) x = 4;
                        else x = 0;
                    } else {
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.println(apresentacao.RED + "                                                                             ESTA TRANSPORTADORA JÁ EXISTE!! DESEJA TENTAR DE NOVO?");
                        System.out.println();
                        apresentacao.resetColor();
                        System.out.println();
                        System.out.println("                                                                                                  1 - SIM");
                        System.out.println("                                                                                                  0 - NAO");
                        System.out.println();
                        System.out.print("                                                                                                     ");
                        ler = new Scanner(System.in);
                        x = ler.nextInt();
                        if (x == 1) x = 4;
                        else x = 0;
                    }
                    break;

                case 5: // MENU CONFIGURACOES
                    x = runConfig();
                    break;
            }
        } while (x != 6);

        return 0;
    }

    private int runUtilizador(String email) throws UtilizadorException, ArtigoException, TransportadoraException, EncomendaException, SistemaException //MENU UTILIZADOR
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
                    ler = new Scanner(System.in);
                    c = ler.nextLine();
                    x = 0;
                    break;

                case 2: // MENU COMPRAR
                    apresentacao.printComprar();
                    paginateMenuCompras(sistema.getArtigosVenda(email), 2, email);
                    x = 0;
                    break;

                case 3: // MENU VENDAS
                    x = runVendas(email);
                    x = 0;
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
        int id, opcao, nrDonos, tamanho, padrao, tipo, tipoCordao;
        double precoBase, avaliacao, dimensao;
        String descricao, marca, material, data, cor;
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
                    apresentacao.printMinhaLista();

                    if (sistema.getArtigosVendaUtilizador(email).isEmpty()) {
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                       NÃO POSSUI NENHUM ARTIGO À VENDA!!");
                        System.out.println();
                        System.out.println("                                                                               DESEJA ADICONAR ALGUM ARTIGO À SUA LISTA DE VENDAS?");
                        apresentacao.resetColor();
                        System.out.println();
                        System.out.println();
                        System.out.println("                                                                                                    1 - SIM");
                        System.out.println("                                                                                                    0 - NAO");
                        System.out.println();
                        System.out.print("                                                                                                       ");

                        ler = new Scanner(System.in);
                        x = ler.nextInt();

                        if (x == 1) runArtigosVender(email);
                        else if (x == 0) {x=0; break;}
                    } else {
                        paginateMenuVendas(sistema.getArtigosVendaUtilizador(email), 2, email);

                        x =0;
                        break;
                    }

                case 2: //ADICIONAR ARTIGOS A MINHA LISTA DE VENDAS
                    x = runArtigosVender(email);
                    break;
            }

        } while (x != 3);

        return 0;
    }

    public int runArtigosVender(String email) throws ArtigoException, UtilizadorException, TransportadoraException {

        int opcao, nrDonos, tamanho, padrao, tipo, tipoCordao;
        double precoBase, avaliacao, dimensao;
        String id, descricao, marca, material, data, cor;
        int x = 0;

        Scanner ler = new Scanner(System.in);
        
        do {
            switch (x) {

                case 0:
                    apresentacao.printAdicionaArtigoVenda();
                    apresentacao.printArtigosVenda();
                    System.out.println("                                        (1)                                                              (2)                                                                 (3)");
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                            ESCOLHA O ARTIGO QUE DESEJA VENDER OU PRESSIONE 4 PARA SAIR!");
                    apresentacao.resetColor();
                    System.out.println();
                    System.out.print("                                                                                                         ");
                    x = ler.nextInt();
                    break;

                case 1:
                    apresentacao.printTshirt();
                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA O ID DA T-SHIRT (CÓDIGO DE BARRAS)");
                    apresentacao.resetColor();
                    System.out.print("                                                                                    ");
                    ler = new Scanner(System.in);
                    id = ler.nextLine();
                    if (!sistema.verificaArtigoVenda(id)) {

                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA UMA DESCRIÇÃO");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        descricao = ler.nextLine();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA A MARCA");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        marca = ler.nextLine();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA O PREÇO BASE");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        precoBase = ler.nextDouble();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INDIQUE O TAMANHO");
                        apresentacao.resetColor();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    3 - " + apresentacao.RESET + "XL");
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    2 - " + apresentacao.RESET + "L");
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    1 - " + apresentacao.RESET + "M");
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    0 - " + apresentacao.RESET + "S");
                        System.out.println();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        tamanho = ler.nextInt();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INDIQUE O PADRAO");
                        apresentacao.resetColor();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    2 - " + apresentacao.RESET + "PALMEIRAS");
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    1 - " + apresentacao.RESET + "RISCAS");
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    0 - " + apresentacao.RESET + "LISA");
                        System.out.println();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        padrao = ler.nextInt();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INDIQUE O SEU ESTADO:");
                        apresentacao.resetColor();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    1 - " + apresentacao.RESET + "NOVO");
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    0 - " + apresentacao.RESET + "USADO");
                        System.out.println();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        opcao = ler.nextInt();

                        if (opcao == 1) {
                            apresentacao.printTshirt();
                            System.out.println();
                            System.out.println();
                            String nomeTransportadora = paginateTransportadora(sistema.getListaTransportadoras(), 1, email);
                            Utilizador utilizador = sistema.procuraUtilizador(email);
                            Tshirt tshirt = new Tshirt(id, utilizador, descricao, marca, precoBase, 0,0, this.sistema.procuraTransportadora(nomeTransportadora), Atributos.VENDA, tamanho, padrao);
                            apresentacao.clear();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(tshirt.showArtigo());
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(apresentacao.CYAN_BOLD + "                                                                                 TEM A CERTEZA QUE DESEJA ADICIONAR ESTE ARTIGO?" + apresentacao.RESET);
                            System.out.println();
                            System.out.println("                                                                                                   1 - SIM");
                            System.out.println("                                                                                                   0 - NÃO");
                            System.out.println();
                            System.out.print("                                                                                                      ");

                            ler = new Scanner(System.in);
                            x = ler.nextInt();

                            if (x == 1) {
                                sistema.adicionaArtigo(tshirt);
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                apresentacao.yellow();
                                System.out.println("                                                                            ARTIGO ADICIONADO COM SUCESSO!! DESEJA ADICIONAR OUTRO?");
                                apresentacao.resetColor();
                                System.out.println();
                                System.out.println("                                                                                                    1 - SIM");
                                System.out.println("                                                                                                    0 - NAO");
                                System.out.println();
                                System.out.print("                                                                                                      ");

                                ler = new Scanner(System.in);
                                x = ler.nextInt();

                                if (x == 1) {
                                    x = 0;
                                    break;

                                } else if (x == 0) {x = 4; break;}

                            } else if (x == 0) {x = 4; break;}

                        } else {
                            System.out.println();
                            System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA A SUA AVALIAÇÃO");
                            apresentacao.resetColor();
                            System.out.print("                                                                                    ");
                            ler = new Scanner(System.in);
                            avaliacao = ler.nextDouble();
                            System.out.println();
                            System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA O NÚMERO DE DONOS QUE JÁ TEVE");
                            apresentacao.resetColor();
                            System.out.print("                                                                                    ");
                            ler = new Scanner(System.in);
                            nrDonos = ler.nextInt();

                            apresentacao.printTshirt();
                            System.out.println();
                            System.out.println();
                            String nomeTransportadora = paginateTransportadora(sistema.getListaTransportadoras(), 1, email);
                            Utilizador utilizador = sistema.procuraUtilizador(email);
                            Tshirt tshirt = new Tshirt(id, utilizador, descricao, marca, precoBase, nrDonos, avaliacao, this.sistema.procuraTransportadora(nomeTransportadora), Atributos.VENDA, tamanho, padrao);
                            apresentacao.clear();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(tshirt.showArtigo());
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(apresentacao.CYAN_BOLD + "                                                                                 TEM A CERTEZA QUE DESEJA ADICIONAR ESTE ARTIGO?" + apresentacao.RESET);
                            System.out.println();
                            System.out.println("                                                                                                   1 - SIM");
                            System.out.println("                                                                                                   0 - NÃO");
                            System.out.println();
                            System.out.print("                                                                                                      ");

                            ler = new Scanner(System.in);
                            x = ler.nextInt();

                            if (x == 1) {
                                sistema.adicionaArtigo(tshirt);
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                apresentacao.yellow();
                                System.out.println("                                                                            ARTIGO ADICIONADO COM SUCESSO!! DESEJA ADICIONAR OUTRO?");
                                apresentacao.resetColor();
                                System.out.println();
                                System.out.println("                                                                                                    1 - SIM");
                                System.out.println("                                                                                                    0 - NAO");
                                System.out.println();
                                System.out.print("                                                                                                      ");

                                ler = new Scanner(System.in);
                                x = ler.nextInt();

                                if (x == 1) {
                                    x = 0;
                                    break;
                                } else if (x == 0) {x = 4; break;}

                            } else if (x == 0) {x = 4; break;}

                        }
                    } else {
                        System.out.println();
                        System.out.println();
                        System.out.println(apresentacao.RED + "                                                                                              ESTE ARTIGO JÁ ESTÁ A VENDA!" + apresentacao.RESET);
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                                DESEJA TENTAR NOVAMENTE?" + apresentacao.RESET);
                        System.out.println();
                        System.out.println("                                                                                                        1 - SIM");
                        System.out.println("                                                                                                        0 - NAO");
                        System.out.println();
                        System.out.print("                                                                                                          ");

                        ler = new Scanner(System.in);
                        x = ler.nextInt();

                        if (x == 1) {
                            x = 1;
                            break;
                        } else if (x == 0) {x = 4; break;}

                    }

                case 2:
                    apresentacao.printMala();
                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA O ID DA MALA (CÓDIGO DE BARRAS)");
                    apresentacao.resetColor();
                    System.out.print("                                                                                    ");
                    ler = new Scanner(System.in);
                    id = ler.nextLine();
                    if (!sistema.verificaArtigoVenda(id)) {

                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA UMA DESCRIÇÃO");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        descricao = ler.nextLine();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA A MARCA");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        marca = ler.nextLine();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA O PREÇO BASE");
                        apresentacao.resetColor();
                        System.out.print("                           runA                                                         ");
                        ler = new Scanner(System.in);
                        precoBase = ler.nextDouble();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA A SUA DIMENSÃO");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        dimensao = ler.nextDouble();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA O MATERIAL");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        material = ler.nextLine();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA A SUA DATA DE LANÇAMENTO (EX: ANO-MÊS-DIA)");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        data = ler.nextLine();
                        LocalDate dataLancamento = stringParaData(data);
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INDIQUE O SEU TIPO");
                        apresentacao.resetColor();
                        System.out.println("                                                                                    1 - PREMIUM");
                        System.out.println("                                                                                    0 - NORMAL");
                        System.out.println();
                        System.out.print("                                                                                        ");
                        ler = new Scanner(System.in);
                        tipo = ler.nextInt();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INDIQUE O SEU ESTADO:");
                        apresentacao.resetColor();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    1 - " + apresentacao.RESET + "NOVO");
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    0 - " + apresentacao.RESET + "USADO");
                        System.out.println();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        opcao = ler.nextInt();
                        if (opcao == 1) {
                            EstadoArtigo estadoNovo = new EstadoNovo();
                            apresentacao.printMala();
                            System.out.println();
                            System.out.println();
                            String nomeTransportadora = paginateTransportadora(sistema.getListaTransportadoras(), 1, email);
                            Utilizador utilizador = sistema.procuraUtilizador(email);
                            Mala mala = new Mala(id, utilizador, descricao, marca, precoBase, 0,0, this.sistema.procuraTransportadora(nomeTransportadora), Atributos.VENDA, dimensao, material, dataLancamento, tipo);
                            apresentacao.clear();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(mala.showArtigo());
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(apresentacao.CYAN_BOLD + "                                                                                 TEM A CERTEZA QUE DESEJA ADICIONAR ESTE ARTIGO?" + apresentacao.RESET);
                            System.out.println();
                            System.out.println("                                                                                                   1 - SIM");
                            System.out.println("                                                                                                   0 - NÃO");
                            System.out.println();
                            System.out.print("                                                                                                      ");

                            ler = new Scanner(System.in);
                            x = ler.nextInt();

                            if (x == 1) {
                                sistema.adicionaArtigo(mala);
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                apresentacao.yellow();
                                System.out.println("                                                                            ARTIGO ADICIONADO COM SUCESSO!! DESEJA ADICIONAR OUTRO?");
                                apresentacao.resetColor();
                                System.out.println();
                                System.out.println("                                                                                                    1 - SIM");
                                System.out.println("                                                                                                    0 - NAO");
                                System.out.println();
                                System.out.print("                                                                                                      ");

                                ler = new Scanner(System.in);
                                x = ler.nextInt();

                                if (x == 1) {
                                    x = 0;
                                    break;
                                } else if (x == 0) {x = 4; break;}

                            } else if (x == 0) {x = 4; break;}

                        } else {
                            System.out.println();
                            System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA A SUA AVALIAÇÃO");
                            apresentacao.resetColor();
                            System.out.print("                                                                                    ");
                            ler = new Scanner(System.in);
                            avaliacao = ler.nextDouble();
                            System.out.println();
                            System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA O NÚMERO DE DONOS QUE JÁ TEVE");
                            apresentacao.resetColor();
                            System.out.print("                                                                                    ");
                            ler = new Scanner(System.in);
                            nrDonos = ler.nextInt();

                            apresentacao.printMala();
                            System.out.println();
                            System.out.println();
                            String nomeTransportadora = paginateTransportadora(sistema.getListaTransportadoras(), 1, email);
                            Utilizador utilizador = sistema.procuraUtilizador(email);
                            Mala mala = new Mala(id, utilizador, descricao, marca, precoBase, nrDonos, avaliacao, this.sistema.procuraTransportadora(nomeTransportadora), Atributos.VENDA, dimensao, material, dataLancamento, tipo);
                            apresentacao.clear();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(mala.showArtigo());
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(apresentacao.CYAN_BOLD + "                                                                                 TEM A CERTEZA QUE DESEJA ADICIONAR ESTE ARTIGO?" + apresentacao.RESET);
                            System.out.println();
                            System.out.println("                                                                                                   1 - SIM");
                            System.out.println("                                                                                                   0 - NÃO");
                            System.out.println();
                            System.out.print("                                                                                                      ");

                            ler = new Scanner(System.in);
                            x = ler.nextInt();

                            if (x == 1) {
                                sistema.adicionaArtigo(mala);
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                apresentacao.yellow();
                                System.out.println("                                                                            ARTIGO ADICIONADO COM SUCESSO!! DESEJA ADICIONAR OUTRO?");
                                apresentacao.resetColor();
                                System.out.println();
                                System.out.println("                                                                                                    1 - SIM");
                                System.out.println("                                                                                                    0 - NAO");
                                System.out.println();
                                System.out.print("                                                                                                      ");

                                ler = new Scanner(System.in);
                                x = ler.nextInt();

                                if (x == 1) {
                                    x = 0;
                                    break;
                                } else if (x == 0) {x = 4; break;}

                            } else if (x == 0) {x = 4; break;}

                        }
                    } else {
                        System.out.println();
                        System.out.println();
                        System.out.println(apresentacao.RED + "                                                                                              ESTE ARTIGO JÁ ESTÁ A VENDA!" + apresentacao.RESET);
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                                DESEJA TENTAR NOVAMENTE?" + apresentacao.RESET);
                        System.out.println();
                        System.out.println("                                                                                                        1 - SIM");
                        System.out.println("                                                                                                        0 - NAO");
                        System.out.println();
                        System.out.print("                                                                                                          ");

                        ler = new Scanner(System.in);
                        x = ler.nextInt();

                        if (x == 1) {
                            x = 2;
                        } else if (x == 0) {x = 4; break;}

                    }
                case 3:
                    apresentacao.printSapatilhas();
                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA O ID DA SAPATILHA (CÓDIGO DE BARRAS)");
                    apresentacao.resetColor();
                    System.out.print("                                                                                    ");
                    ler = new Scanner(System.in);
                    id = ler.nextLine();
                    if (!sistema.verificaArtigoVenda(id)) {

                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA UMA DESCRIÇÃO");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        descricao = ler.nextLine();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA A MARCA");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        marca = ler.nextLine();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA O PREÇO BASE");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        precoBase = ler.nextDouble();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA O SEU TAMANHO (Nº DE CALÇADO)");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        tamanho = ler.nextInt();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INDIQUE O TIPO DE CORDÃO");
                        apresentacao.resetColor();
                        System.out.println("                                                                                    1 - ATILHO");
                        System.out.println("                                                                                    0 - CORDÃO");
                        System.out.println();
                        System.out.print("                                                                                       ");
                        tipoCordao = ler.nextInt();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA A SUA COR");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        cor = ler.nextLine();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA A SUA DATA DE LANÇAMENTO (EX: ANO-MÊS-DIA)");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        data = ler.nextLine();
                        LocalDate dataLancamento = stringParaData(data);
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INDIQUE O SEU TIPO");
                        apresentacao.resetColor();
                        System.out.println("                                                                                    1 - PREMIUM");
                        System.out.println("                                                                                    0 - NORMAL");
                        System.out.println();
                        System.out.print("                                                                                       ");
                        ler = new Scanner(System.in);
                        tipo = ler.nextInt();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INDIQUE O SEU ESTADO:");
                        apresentacao.resetColor();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    1 - " + apresentacao.RESET + "NOVO");
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    0 - " + apresentacao.RESET + "USADO");
                        System.out.println();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        opcao = ler.nextInt();
                        if (opcao == 1) {
                            EstadoArtigo estadoNovo = new EstadoNovo();
                            apresentacao.printSapatilhas();
                            System.out.println();
                            System.out.println();
                            String nomeTransportadora = paginateTransportadora(sistema.getListaTransportadoras(), 1, email);
                            Utilizador utilizador = sistema.procuraUtilizador(email);
                            Sapatilha sapatilha = new Sapatilha(id, utilizador, descricao, marca, precoBase, 0,0, this.sistema.procuraTransportadora(nomeTransportadora), Atributos.VENDA, tamanho, tipoCordao, cor, dataLancamento, tipo);
                            apresentacao.clear();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(sapatilha.showArtigo());
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(apresentacao.CYAN_BOLD + "                                                                                 TEM A CERTEZA QUE DESEJA ADICIONAR ESTE ARTIGO?" + apresentacao.RESET);
                            System.out.println();
                            System.out.println("                                                                                                   1 - SIM");
                            System.out.println("                                                                                                   0 - NÃO");
                            System.out.println();
                            System.out.print("                                                                                                      ");

                            ler = new Scanner(System.in);
                            x = ler.nextInt();

                            if (x == 1) {
                                sistema.adicionaArtigo(sapatilha);
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                apresentacao.yellow();
                                System.out.println("                                                                            ARTIGO ADICIONADO COM SUCESSO!! DESEJA ADICIONAR OUTRO?");
                                apresentacao.resetColor();
                                System.out.println();
                                System.out.println("                                                                                                    1 - SIM");
                                System.out.println("                                                                                                    0 - NAO");
                                System.out.println();
                                System.out.print("                                                                                                      ");

                                ler = new Scanner(System.in);
                                x = ler.nextInt();

                                if (x == 1) {
                                    x = 0;
                                    break;
                                } else if (x == 0) {x = 4; break;}

                            } else if (x == 0) {x = 4; break;}

                        } else {
                            System.out.println();
                            System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA A SUA AVALIAÇÃO");
                            apresentacao.resetColor();
                            System.out.print("                                                                                    ");
                            ler = new Scanner(System.in);
                            avaliacao = ler.nextDouble();
                            System.out.println();
                            System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA O NÚMERO DE DONOS QUE JÁ TEVE");
                            apresentacao.resetColor();
                            System.out.print("                                                                                    ");
                            ler = new Scanner(System.in);
                            nrDonos = ler.nextInt();

                            apresentacao.printSapatilhas();
                            System.out.println();
                            System.out.println();
                            String nomeTransportadora = paginateTransportadora(sistema.getListaTransportadoras(), 1, email);
                            Utilizador utilizador = sistema.procuraUtilizador(email);
                            Sapatilha sapatilha = new Sapatilha(id, utilizador, descricao, marca, precoBase, nrDonos, avaliacao, this.sistema.procuraTransportadora(nomeTransportadora), Atributos.VENDA, tamanho, tipoCordao, cor, dataLancamento, tipo);
                            apresentacao.clear();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(sapatilha.showArtigo());
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(apresentacao.CYAN_BOLD + "                                                                                 TEM A CERTEZA QUE DESEJA ADICIONAR ESTE ARTIGO?" + apresentacao.RESET);
                            System.out.println();
                            System.out.println("                                                                                                   1 - SIM");
                            System.out.println("                                                                                                   0 - NÃO");
                            System.out.println();
                            System.out.print("                                                                                                      ");

                            ler = new Scanner(System.in);
                            x = ler.nextInt();

                            if (x == 1) {
                                sistema.adicionaArtigo(sapatilha);
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                apresentacao.yellow();
                                System.out.println("                                                                            ARTIGO ADICIONADO COM SUCESSO!! DESEJA ADICIONAR OUTRO?");
                                apresentacao.resetColor();
                                System.out.println();
                                System.out.println("                                                                                                    1 - SIM");
                                System.out.println("                                                                                                    0 - NAO");
                                System.out.println();
                                System.out.print("                                                                                                      ");

                                ler = new Scanner(System.in);
                                x = ler.nextInt();

                                if (x == 1) {
                                    x = 0;
                                    break;
                                } else if (x == 0) {x = 4; break;}

                            } else if (x == 0) {x = 4; break;}

                        }
                    } else {
                        System.out.println();
                        System.out.println();
                        System.out.println(apresentacao.RED + "                                                                                              ESTE ARTIGO JÁ ESTÁ A VENDA!" + apresentacao.RESET);
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                                DESEJA TENTAR NOVAMENTE?" + apresentacao.RESET);
                        System.out.println();
                        System.out.println("                                                                                                        1 - SIM");
                        System.out.println("                                                                                                        0 - NAO");
                        System.out.println();
                        System.out.print("                                                                                                          ");

                        ler = new Scanner(System.in);
                        x = ler.nextInt();

                        if (x == 1) {
                            x = 3;
                        } else if (x == 0) {x = 4; break;}

                    }
            }
        } while (x != 4);

        return 0;
    }
    
    public int runConfig() throws SistemaException {
        int x = 0;
        String [] s = {"Avancar no tempo", "Alterar taxas e impostos", "Alterar tempo de devolucao", "Retroceder"};
        Scanner ler = new Scanner(System.in);
        int opcao, dia, mes, ano;
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
                    apresentacao.printSaltaTempo();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                  Selecione o metodo para avancar no tempo:");
                    System.out.println();
                    System.out.println(apresentacao.YELLOW + "                                                                                       1 - Avancar para uma data");
                    System.out.println(                      "                                                                                       2 - Adicionar dias à data atual" + apresentacao.RESET);
                    System.out.println();
                    System.out.print("                                                                                                      ");
                    ler = new Scanner(System.in);
                    opcao = ler.nextInt();
                    LocalDate localDate;

                    if (opcao == 1) { // PARA UMA DATA
                        apresentacao.printSaltaTempo();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                            Introduza o dia (DD)" + apresentacao.RESET);
                        System.out.println();
                        System.out.print("                                                                                                     ");
                        ler = new Scanner(System.in);
                        dia = ler.nextInt();

                        System.out.println();

                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                            Introduza o mes (MM)" + apresentacao.RESET);
                        System.out.println();
                        System.out.print("                                                                                                     ");
                        ler = new Scanner(System.in);
                        mes = ler.nextInt();

                        System.out.println();

                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                           Introduza o ano (AAAA)" + apresentacao.RESET);
                        System.out.println();
                        System.out.print("                                                                                                     ");
                        ler = new Scanner(System.in);
                        ano = ler.nextInt();

                        sistema.saltaTempo(ano, mes, dia);
                        localDate = this.sistema.getDataAtual();

                        apresentacao.printSaltaTempo();
                        System.out.println(apresentacao.YELLOW + "                                                                                    SALTO NO TEMPO REALIZADO COM SUCESSO!!");
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                      Data atual do sistema: " + apresentacao.RESET + localDate);
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.println("                                                                                        Pressione enter para sair...");
                        System.out.println();
                        System.out.print("                                                                                                     ");
                        ler = new Scanner(System.in);
                        c = ler.nextLine();
                        x = 0;
                        break;
                    }

                    if (opcao == 2) { // ADICIONAR DIAS A DATA ATUAL
                        apresentacao.printSaltaTempo();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                            Introduza o numero de dias que pretende avancar (DD)" + apresentacao.RESET);
                        System.out.println();
                        System.out.print("                                                                                                      ");
                        ler = new Scanner(System.in);
                        dia = ler.nextInt();

                        sistema.saltaTempo(dia);
                        localDate = this.sistema.getDataAtual();

                        apresentacao.printSaltaTempo();
                        System.out.println(apresentacao.YELLOW + "                                                                                    SALTO NO TEMPO REALIZADO COM SUCESSO!!");
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                      Data atual do sistema: " + apresentacao.RESET + localDate);
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.println("                                                                                        Pressione enter para sair...");
                        System.out.println();
                        System.out.print("                                                                                                     ");
                        ler = new Scanner(System.in);
                        c = ler.nextLine();
                        x = 0;
                        break;
                    }

                    x = 0;
                    break;

                case 2: // MENU ALTERAR IMPOSTO E TAXAS
                    apresentacao.printTax();
                    int imposto;
                    double taxa;

                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                           Defina a taxa de imposto" + apresentacao.RESET);
                    System.out.println();
                    System.out.print("                                                                                                       ");
                    ler = new Scanner(System.in);
                    imposto = ler.nextInt();
                    sistema.getTaxas().setImposto(imposto);

                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                     Defina a taxa de uma encomanda pequena" + apresentacao.RESET);
                    System.out.println();
                    System.out.print("                                                                                                       ");
                    ler = new Scanner(System.in);
                    taxa = ler.nextDouble();
                    sistema.getTaxas().setTaxaEncPequena(taxa);

                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                     Defina a taxa de uma encomanda media" + apresentacao.RESET);
                    System.out.println();
                    System.out.print("                                                                                                       ");
                    ler = new Scanner(System.in);
                    taxa = ler.nextDouble();
                    sistema.getTaxas().setTaxaEncMedia(taxa);

                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                     Defina a taxa de uma encomanda grande" + apresentacao.RESET);
                    System.out.println();
                    System.out.print("                                                                                                       ");
                    ler = new Scanner(System.in);
                    taxa = ler.nextDouble();
                    sistema.getTaxas().setTaxaEncGrande(taxa);

                    apresentacao.printTax();

                    System.out.println(apresentacao.YELLOW + "                                                                                       ALTERACAO REALIZADA COM SUCESSO!!" + apresentacao.RESET);
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println("                                                                                         Pressione enter para sair...");
                    System.out.println();
                    System.out.print("                                                                                                      ");
                    ler = new Scanner(System.in);
                    c = ler.nextLine();
                    x = 0;
                    break;

                case 3:
                    apresentacao.printReturnTime();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                            Defina o limite de dias para a devolucao de uma encomenda" + apresentacao.RESET);
                    System.out.println();
                    System.out.print("                                                                                                       ");
                    ler = new Scanner(System.in);
                    x = ler.nextInt();

                    sistema.setTempoDevolucao(x);

                    apresentacao.printReturnTime();
                    System.out.println(apresentacao.YELLOW + "                                                                                       ALTERACAO REALIZADA COM SUCESSO!!" + apresentacao.RESET);
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println("                                                                                          Pressione enter para sair...");
                    System.out.println();
                    System.out.print("                                                                                                       ");
                    ler = new Scanner(System.in);
                    c = ler.nextLine();
                    x = 0;
                    break;

            }
        } while (x != 4);

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
                    encomendas = utilizador.getListaEncomendas(Atributos.PENDENTE);
                    paginatePendentes(encomendas,5, email);
                    x = 0;
                    break;

                case 2: //MENU EXPEDIDAS
                    encomendas = utilizador.getListaEncomendas(Atributos.EXPEDIDA);
                    paginateExpedidas(encomendas,5, email);
                    x = 0;
                    break;

                case 3: //MENU FINALIZADAS
                    encomendas = utilizador.getListaEncomendas(Atributos.FINALIZADA);
                    paginateFinalizadas(encomendas,5, email);
                    x = 0;
                    break;

                case 4:
                    encomendas = utilizador.getListaEncomendas(Atributos.DEVOLVIDA);
                    paginateDevolvidas(encomendas,5, email);
                    x = 0;
                    break;

            }

        } while (x != 5);

        return 0;
    }

    public void paginateMenuVendas(Map<String, Artigo> lista, int pageSize, String email) throws ArtigoException, UtilizadorException, TransportadoraException {
        Artigo[] menuItems = lista.values().toArray(new Artigo[0]);
        String c;
        int numPages = (int) Math.ceil((double) menuItems.length / pageSize);
        int currentPage = 1;
        int startIndex, endIndex;

        do {
            apresentacao.printVendas();
            startIndex = (currentPage - 1) * pageSize;
            endIndex = Math.min(startIndex + pageSize, menuItems.length);

            for (int i = startIndex; i < endIndex; i++) {
                int key = i + 1;
                Artigo artigo = menuItems[i];
                System.out.println(artigo.showArtigo());
            }

            if (numPages == 1){
                System.out.println();
                System.out.println();
                System.out.println(apresentacao.YELLOW + "                                                                                        Pressione enter para continuar..." + Apresentacao.RESET);
                System.out.println();
                System.out.print("                                                                                                       ");
                Scanner ler = new Scanner(System.in);
                c = ler.nextLine();
                runVendas(email);
            }

            if (numPages > 1) {
                System.out.println();
                System.out.println();
                System.out.println("                                                                                                  Pag." + currentPage + " de " + numPages);
                System.out.println();
                System.out.println(apresentacao.CYAN_BOLD + "                                                           Pressione" + apresentacao.RESET + " '+' " +
                        apresentacao.CYAN_BOLD + "para avancar," + apresentacao.RESET + " '-' " + apresentacao.CYAN_BOLD + "para a retroceder," + apresentacao.RESET + " 'r' " + apresentacao.CYAN_BOLD +
                                "para remover artigo e" + apresentacao.RESET + " 's' " + apresentacao.CYAN_BOLD + "para sair" + apresentacao.RESET);
                System.out.println();
                System.out.print("                                                                                                       ");

                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().toLowerCase();

                if (input.equals("+") && currentPage < numPages) {
                    currentPage++;
                } else if (input.equals("-") && currentPage > 1) {
                    currentPage--;
                } else if (input.equals("r")) {
                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD +"                                                                                   INTRODUZA O ID DO ARTIGO QUE DESEJA REMOVER"+ apresentacao.RESET);
                    System.out.println();
                    System.out.print("                                                                                                       ");

                    String id = scanner.nextLine();

                    Utilizador utilizador = sistema.procuraUtilizador(email);
                    if(utilizador.getListaArtigos().containsKey(id)){
                        Artigo artigo = sistema.procuraArtigoVenda(id);
                        apresentacao.clear();
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.println(artigo.showArtigo());
                        System.out.println();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD +"                                                                                           DESEJA REMOVER ESTE ARTIGO?"+ apresentacao.RESET);
                        sistema.removeArtigo(artigo);
                        System.out.println();
                        System.out.println("                                                                                                     1 - SIM");
                        System.out.println("                                                                                                     0 - NAO");
                        System.out.println();
                        System.out.print("                                                                                                        ");

                        int opcao = scanner.nextInt();
                        if (opcao == 1) {
                            sistema.removeArtigo(artigo);
                            System.out.println();
                            System.out.println(apresentacao.YELLOW +"                                                                                          ARTIGO REMOVIDO COM SUCESSO!" + apresentacao.RESET);
                            System.out.println();
                            System.out.println("                                                                                        Pressione enter para continuar...");
                            System.out.println();
                            System.out.print("                                                                                                       ");
                            Scanner ler = new Scanner(System.in);
                            c = ler.nextLine();
                            break;
                        }
                    }
                } else if (input.equals("s")) {
                    break;
                }
            }
        } while (true);
    }

    public void paginateMenuCompras(Map<String, Artigo> lista, int pageSize, String email) throws ArtigoException, UtilizadorException, TransportadoraException, EncomendaException, SistemaException {
        Artigo[] menuItems = lista.values().toArray(new Artigo[0]);
        String c;
        int numPages = (int) Math.ceil((double) menuItems.length / pageSize);
        int currentPage = 1;
        int startIndex, endIndex;
        if (lista.isEmpty())
        {
            System.out.println("                                                                                        Não existem artigos para comprar!");
            System.out.println();
            System.out.println();
            System.out.println(apresentacao.YELLOW + "                                                                                        Pressione enter para continuar..." + Apresentacao.RESET);
            System.out.println();
            System.out.print("                                                                                                       ");
            Scanner ler = new Scanner(System.in);
            c = ler.nextLine();
            runUtilizador(email);
        }
        do {
            apresentacao.printComprar();
            startIndex = (currentPage - 1) * pageSize;
            endIndex = Math.min(startIndex + pageSize, menuItems.length);

            for (int i = startIndex; i < endIndex; i++) {
                int key = i + 1;
                Artigo artigo = menuItems[i];
                System.out.println(artigo.showArtigo());
            }

            if (numPages == 1){
                System.out.println();
                System.out.println();
                System.out.println(apresentacao.YELLOW + "                                                                                        Pressione enter para continuar..." + Apresentacao.RESET);
                System.out.println();
                System.out.print("                                                                                                       ");
                Scanner ler = new Scanner(System.in);
                c = ler.nextLine();
                runVendas(email);
            }

            if (numPages > 1) {
                System.out.println();
                System.out.println();
                System.out.println("                                                                                                  Pag." + currentPage + " de " + numPages);
                System.out.println();
                System.out.println(apresentacao.CYAN_BOLD + "                                                           Pressione" + apresentacao.RESET + " '+' " +
                        apresentacao.CYAN_BOLD + "para avancar," + apresentacao.RESET + " '-' " + apresentacao.CYAN_BOLD + "para a retroceder," + apresentacao.RESET + " 'c' " + apresentacao.CYAN_BOLD +
                        "para comprar artigo e" + apresentacao.RESET + " 's' " + apresentacao.CYAN_BOLD + "para sair" + apresentacao.RESET);
                System.out.println();
                System.out.print("                                                                                                       ");

                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().toLowerCase();

                if (input.equals("+") && currentPage < numPages) {
                    currentPage++;
                } else if (input.equals("-") && currentPage > 1) {
                    currentPage--;
                } else if (input.equals("c")) {

                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD +"                                                                                   INTRODUZA O ID DO ARTIGO QUE DESEJA COMPRAR"+ apresentacao.RESET);
                    System.out.println();
                    System.out.print("                                                                                                       ");

                    String id = scanner.nextLine();

                    Artigo artigo = sistema.procuraArtigoVenda(id);
                    apresentacao.clear();
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println(artigo.showArtigo());
                    System.out.println();
                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                           DESEJA COMPRAR ESTE ARTIGO?"+ apresentacao.RESET);
                    System.out.println();
                    System.out.println("                                                                                                     1 - SIM");
                    System.out.println("                                                                                                     0 - NAO");
                    System.out.println();
                    System.out.print("                                                                                                        ");

                    int opcao = scanner.nextInt();
                    if (opcao == 1) {
                        sistema.adicionaArtigoEncomenda(artigo, email);
                        System.out.println();
                        System.out.println(apresentacao.YELLOW +"                                                                                          ARTIGO COMPRADO COM SUCESSO!" + apresentacao.RESET);
                        System.out.println();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD +"                                                                                           DESEJA COMPRAR OUTRO ARTIGO?"+ apresentacao.RESET);
                        System.out.println();
                        System.out.println("                                                                                                     1 - SIM");
                        System.out.println("                                                                                                     0 - NAO");
                        System.out.println();
                        System.out.print("                                                                                                        ");

                        int opcao2 = scanner.nextInt();
                        if (opcao2 == 1){
                            input.equals("c");
                        }
                        else if (opcao2 == 0) { break;}
                    }
                } else if (input.equals("s")) {
                    break;
                }
            }
        } while (true);
    }

    public String paginateTransportadora(Map<String, Transportadora> lista, int pageSize, String email) throws TransportadoraException, UtilizadorException, ArtigoException {
        Transportadora[] menuItems = lista.values().toArray(new Transportadora[0]);
        int numPages = (int) Math.ceil((double) menuItems.length / pageSize);
        int currentPage = 1;
        int startIndex, endIndex;
        do {
            apresentacao.printProcuraTrans();
            System.out.println();
            System.out.println();
            startIndex = (currentPage - 1) * pageSize;
            endIndex = Math.min(startIndex + pageSize, menuItems.length);

            for (int i = startIndex; i < endIndex; i++) {
                int key = i + 1;
                Transportadora transportadora = menuItems[i];
                System.out.println();
                System.out.println(transportadora.showTransportadora(key));
            }

            if (numPages > 1) {
                System.out.println();
                System.out.println();
                System.out.println("                                                                                                 Pag." + currentPage + " de " + numPages);
                System.out.println();
                System.out.println(apresentacao.CYAN_BOLD + "                                                                               Pressione" + apresentacao.RESET + " '+' " +
                        apresentacao.CYAN_BOLD + "para avancar e" + apresentacao.RESET + " '-' " + apresentacao.CYAN_BOLD + "para a retroceder ");
                System.out.println("                                                                              Para selecionar a transportadora, digite o nome dela");
                apresentacao.resetColor();
                System.out.println();
                System.out.print("                                                                                                       ");

                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().toLowerCase();

                if (input.equals("+") && currentPage < numPages) {
                    currentPage++;
                } else if (input.equals("-") && currentPage > 1) {
                    currentPage--;
                } else if (!sistema.verificaTransportadora(input)) {
                    System.out.println();
                    System.out.println(apresentacao.RED + "                                                        A TRANSPORTADORA COM O NOME "+ apresentacao.RESET + input + apresentacao.RED + " NÃO EXISTE" + apresentacao.RESET);
                    System.out.println(apresentacao.CYAN_BOLD +"                                                                            DESEJA TENTAR NOVAMENTE?" + apresentacao.RESET);
                    System.out.println();
                    System.out.println("                                                                                                            1 - SIM");
                    System.out.println("                                                                                                            0 - NAO");
                    System.out.println();
                    System.out.print("                                                                                                              ");

                    Scanner ler = new Scanner(System.in);
                    int op = ler.nextInt();

                    if (op == 1) paginateTransportadora(sistema.getListaTransportadoras(), 1, email);
                    else runVendas(email);

                }
                else
                {
                    return input;
                }
            } else if (numPages == 1) {
                System.out.println();
                System.out.println();
                System.out.println("                                                                                 Para selecionar a transportadora, digite o nome dela");
                System.out.println();
                System.out.print("                                                                                                        ");

                Scanner scanner = new Scanner(System.in);
                String nomeTransportadora = scanner.nextLine();
                if (!sistema.verificaTransportadora(nomeTransportadora)){

                    System.out.println();
                    System.out.println(apresentacao.RED + "                                                        A TRANSPORTADORA COM O NOME "+ apresentacao.RESET + nomeTransportadora + apresentacao.RED + " NÃO EXISTE" + apresentacao.RESET);
                    System.out.println(apresentacao.CYAN_BOLD +"                                                                            DESEJA TENTAR NOVAMENTE?" + apresentacao.RESET);
                    System.out.println();
                    System.out.println("                                                                                                            1 - SIM");
                    System.out.println("                                                                                                            0 - NAO");
                    System.out.println();
                    System.out.print("                                                                                                              ");

                    Scanner ler = new Scanner(System.in);
                    int op = ler.nextInt();

                    if (op == 1) paginateTransportadora(sistema.getListaTransportadoras(), 1, email);
                    else runVendas(email);

                }
                else
                {
                    return nomeTransportadora;
                }
            }
        } while (true);
    }

    public void paginatePendentes(List<Encomenda> encomendas, int pageSize, String email) throws UtilizadorException, EncomendaException, TransportadoraException, ArtigoException, SistemaException {
        Utilizador utilizador = sistema.procuraUtilizador(email);

        if (encomendas.isEmpty()) {
            System.out.println();
            System.out.println();
            apresentacao.printBox();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(apresentacao.YELLOW + "                                                                                        NAO EXISTEM ENCOMENDAS PENDENTES!!" + apresentacao.RESET);
            System.out.println();
            System.out.println();
            System.out.println("                                                                                        Pressione enter para retroceder...");
            System.out.println();
            System.out.print("                                                                                                        ");
            Scanner ler = new Scanner(System.in);
            String c = ler.nextLine();
            return;
        }

        do {
            encomendas = utilizador.getListaEncomendas(Atributos.PENDENTE);
            Encomenda[] menuItems = new Encomenda[encomendas.size()];
            encomendas.toArray(menuItems);
            int numPages = (int) Math.ceil((double) menuItems.length / pageSize);
            int currentPage = 1;
            int startIndex, endIndex;

            apresentacao.printPendentes();
            startIndex = (currentPage - 1) * pageSize;
            endIndex = Math.min(startIndex + pageSize, menuItems.length);

            System.out.println(apresentacao.RED + "[ENCOMENDAS]\n" + apresentacao.RESET);

            for (int i = startIndex; i < endIndex; i++) {
                int key = i + 1;
                Encomenda encomenda = menuItems[i];
                System.out.println(encomenda.showEncomenda());
            }
                System.out.println();
                System.out.println();
                System.out.println("                                                                                                 Pag." + currentPage + " de " + numPages);
                System.out.println();
                
                System.out.println(apresentacao.CYAN_BOLD + "                             Pressione" + apresentacao.RESET + " '+' " +
                        apresentacao.CYAN_BOLD + "para avancar," + apresentacao.RESET + " '-' " + apresentacao.CYAN_BOLD + "para a retroceder," + apresentacao.RESET + " 'c' " + apresentacao.CYAN_BOLD +
                        "para confirmar encomenda," + apresentacao.RESET + " 'r' " + apresentacao.CYAN_BOLD + "para ver/remover um artigo de uma encomenda," + apresentacao.RESET + " 's' " + apresentacao.CYAN_BOLD +
                        "para sair" + apresentacao.RESET);
                System.out.println();
                System.out.print("                                                                                                      ");

                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().toLowerCase();

                if (input.equals("+") && currentPage < numPages) {
                    currentPage++;
                    } else if (input.equals("-") && currentPage > 1) {
                    currentPage--;
                } else if (input.equals("c")) {

                    System.out.println();
                    System.out.println(apresentacao.RED +"                                                                              INTRODUZA O ID DA ENCOMENDA QUE DESEJA CONFIRMAR"+ apresentacao.RESET);
                    System.out.println();
                    System.out.print("                                                                                                      ");

                    int id = scanner.nextInt();

                    if (menuItems.length == 1) {
                        sistema.confirmaEncomenda(id,email);
                        System.out.println();
                        System.out.println();
                        apresentacao.printBox();
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.println(apresentacao.YELLOW + "                                                                                        ENCOMENDA CONFIRMADA COM SUCESSO!!");
                        System.out.println();
                        System.out.println();
                        System.out.println("                                                                                        NAO EXISTEM ENCOMENDAS PENDENTES!!" + apresentacao.RESET);
                        System.out.println();
                        System.out.println();
                        System.out.println("                                                                                        Pressione enter para retroceder...");
                        System.out.println();
                        System.out.print("                                                                                                        ");
                        Scanner ler = new Scanner(System.in);
                        String c = ler.nextLine();
                        break;
                    }
                    System.out.println();
                    System.out.println();
                    System.out.println(apresentacao.YELLOW + "                                                                                      ENCOMENDA CONFIRMADA COM SUCESSO!!" + apresentacao.RESET);

                    sistema.confirmaEncomenda(id,email);

                    System.out.println();
                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD +"                                                                                      DESEJA CONFIRMAR MAIS ENCOMENDAS?"+ apresentacao.RESET);
                    System.out.println();
                    System.out.println("                                                                                                   1 - SIM");
                    System.out.println("                                                                                                   0 - NAO");
                    System.out.println();
                    System.out.print("                                                                                                      ");

                    Scanner ler = new Scanner(System.in);
                    int op = ler.nextInt();

                    if (op == 1) {
                    }
                    else break;
                } else if (input.equals("r")) {

                    System.out.println();
                    System.out.println(apresentacao.RED +"                                                                                INTRODUZA O ID DA ENCOMENDA QUE DESEJA EDITAR"+ apresentacao.RESET);
                    System.out.println();
                    System.out.print("                                                                                                      ");

                    int id = scanner.nextInt();

                    Encomenda encomenda = sistema.procuraEncomenda(id);

                    paginateEncPendentes(encomenda, 2, email, id);
                }
                else if (input.equals("s")) {
                    break;
                }

        } while (true);
    }

    public void paginateEncPendentes(Encomenda encomenda, int pageSize, String email, int id_encomenda) throws ArtigoException, UtilizadorException, SistemaException, EncomendaException, TransportadoraException {

        do {
            encomenda = sistema.procuraEncomenda(id_encomenda);
            List<Artigo> lista = encomenda.getListaArtigos();
            Artigo[] menuItems = new Artigo[lista.size()];
            lista.toArray(menuItems);
            int numPages = (int) Math.ceil((double) menuItems.length / pageSize);
            int currentPage = 1;
            int startIndex, endIndex;

            apresentacao.printBox();
            startIndex = (currentPage - 1) * pageSize;
            endIndex = Math.min(startIndex + pageSize, menuItems.length);

            for (int i = startIndex; i < endIndex; i++) {
                int key = i + 1;
                Artigo artigo = menuItems[i];
                System.out.println(artigo.showArtigo());
            }

            System.out.println();
            System.out.println();
            System.out.println("                                                                                                  Pag." + currentPage + " de " + numPages);
            System.out.println();
            System.out.println(apresentacao.CYAN_BOLD + "                                               Pressione" + apresentacao.RESET + " '+' " +
                    apresentacao.CYAN_BOLD + "para avancar," + apresentacao.RESET + " '-' " + apresentacao.CYAN_BOLD + "para a retroceder," + apresentacao.RESET +
                    " 'r' " + apresentacao.CYAN_BOLD + "para remover um artigo de uma encomenda," + apresentacao.RESET + " 's' " + apresentacao.CYAN_BOLD +
                    "para sair" + apresentacao.RESET);
            System.out.println();
            System.out.print("                                                                                                       ");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("+") && currentPage < numPages) {
                currentPage++;
            } else if (input.equals("-") && currentPage > 1) {
                currentPage--;
            } else if (input.equals("r")) {
                System.out.println();
                System.out.println(apresentacao.RED +"                                                                                  INTRODUZA O ID DO ARTIGO QUE DESEJA REMOVER"+ apresentacao.RESET);
                System.out.println();
                System.out.print("                                                                                                       ");

                String id = scanner.nextLine();

                if (menuItems.length == 1) {
                    sistema.removeArtigoEncomenda(id,email);
                    apresentacao.printBox();
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println(apresentacao.YELLOW + "                                                                                          ESTA ENCOMENDA FOI REMOVIDA!!" + apresentacao.RESET);
                    System.out.println();
                    System.out.println();
                    System.out.println("                                                                                        Pressione enter para retroceder...");
                    System.out.println();
                    System.out.print("                                                                                                        ");
                    Scanner ler = new Scanner(System.in);
                    String c = ler.nextLine();
                    break;
                }

                System.out.println();
                System.out.println();
                System.out.println(apresentacao.YELLOW + "                                                                                         ARTIGO REMOVIDO COM SUCESSO!!" + apresentacao.RESET);
                System.out.println();

                sistema.removeArtigoEncomenda(id,email);

                System.out.println();
                System.out.println(apresentacao.CYAN_BOLD +"                                                                                   DESEJA CONTINUAR A REMOVER MAIS ARTIGOS?"+ apresentacao.RESET);
                System.out.println();
                System.out.println("                                                                                                    1 - SIM");
                System.out.println("                                                                                                    0 - NAO");
                System.out.println();
                System.out.print("                                                                                                       ");

                Scanner ler = new Scanner(System.in);
                int op = ler.nextInt();

                if (op == 1) {
                }
                else {
                    break;
                }
            } else if (input.equals("s")) {
                break;
            }
        } while (true);
     }

    public void paginateExpedidas(List<Encomenda> encomendas, int pageSize, String email) throws UtilizadorException, EncomendaException, TransportadoraException, ArtigoException, SistemaException {

        if (encomendas.isEmpty()) {
            System.out.println();
            System.out.println();
            apresentacao.printBox();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(apresentacao.YELLOW + "                                                                                        NAO EXISTEM ENCOMENDAS EXPEDIDAS!!" + apresentacao.RESET);
            System.out.println();
            System.out.println();
            System.out.println("                                                                                        Pressione enter para retroceder...");
            System.out.println();
            System.out.print("                                                                                                        ");
            Scanner ler = new Scanner(System.in);
            String c = ler.nextLine();
            return;
        }

        do {
            Encomenda[] menuItems = new Encomenda[encomendas.size()];
            encomendas.toArray(menuItems);
            Utilizador utilizador = sistema.procuraUtilizador(email);
            int numPages = (int) Math.ceil((double) menuItems.length / pageSize);
            int currentPage = 1;
            int startIndex, endIndex;

            apresentacao.printExpedidas();
            startIndex = (currentPage - 1) * pageSize;
            endIndex = Math.min(startIndex + pageSize, menuItems.length);

            System.out.println(apresentacao.RED + "[ENCOMENDAS]\n" + apresentacao.RESET);

            for (int i = startIndex; i < endIndex; i++) {
                int key = i + 1;
                Encomenda encomenda = menuItems[i];
                System.out.println(encomenda.showEncomenda());
            }
            System.out.println();
            System.out.println();
            System.out.println("                                                                                                 Pag." + currentPage + " de " + numPages);
            System.out.println();

            System.out.println(apresentacao.CYAN_BOLD + "                                               Pressione" + apresentacao.RESET + " '+' " +
                    apresentacao.CYAN_BOLD + "para avancar," + apresentacao.RESET + " '-' " + apresentacao.CYAN_BOLD + "para a retroceder," + apresentacao.RESET + "'v' " + apresentacao.CYAN_BOLD + "para ver os artigos de uma encomenda," + apresentacao.RESET + " 's' " + apresentacao.CYAN_BOLD +
                    "para sair" + apresentacao.RESET);
            System.out.println();
            System.out.print("                                                                                                      ");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("+") && currentPage < numPages) {
                currentPage++;
            } else if (input.equals("-") && currentPage > 1) {
                currentPage--;
            } else if (input.equals("v")) {
                System.out.println();
                System.out.println(apresentacao.RED +"                                                                                INTRODUZA O ID DA ENCOMENDA QUE DESEJA VER"+ apresentacao.RESET);
                System.out.println();
                System.out.print("                                                                                                      ");

                int id = scanner.nextInt();

                Encomenda encomenda = sistema.procuraEncomenda(id);

                paginateEncExpedidas(encomenda, 2, email, id);
            }
            else if (input.equals("s")) {
                break;
            }
        } while (true);
    }

    public void paginateEncExpedidas(Encomenda encomenda, int pageSize, String email, int id_encomenda) throws ArtigoException, UtilizadorException, SistemaException, EncomendaException, TransportadoraException {

        do {
            encomenda = sistema.procuraEncomenda(id_encomenda);
            List<Artigo> lista = encomenda.getListaArtigos();
            Artigo[] menuItems = new Artigo[lista.size()];
            lista.toArray(menuItems);
            int numPages = (int) Math.ceil((double) menuItems.length / pageSize);
            int currentPage = 1;
            int startIndex, endIndex;

            apresentacao.printBox();
            startIndex = (currentPage - 1) * pageSize;
            endIndex = Math.min(startIndex + pageSize, menuItems.length);

            for (int i = startIndex; i < endIndex; i++) {
                int key = i + 1;
                Artigo artigo = menuItems[i];
                System.out.println(artigo.showArtigo());
            }

            System.out.println();
            System.out.println();
            System.out.println("                                                                                                  Pag." + currentPage + " de " + numPages);
            System.out.println();
            System.out.println(apresentacao.CYAN_BOLD + "                                                                      Pressione" + apresentacao.RESET + " '+' " +
                    apresentacao.CYAN_BOLD + "para avancar," + apresentacao.RESET + " '-' " + apresentacao.CYAN_BOLD + "para a retroceder," + apresentacao.RESET +
                    " 's' " + apresentacao.CYAN_BOLD +
                    "para sair" + apresentacao.RESET);
            System.out.println();
            System.out.print("                                                                                                       ");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("+") && currentPage < numPages) {
                currentPage++;
            } else if (input.equals("-") && currentPage > 1) {
                currentPage--;
            } else if (input.equals("s")) {
                break;
            }
        } while (true);
    }

    public void paginateFinalizadas(List<Encomenda> encomendas, int pageSize, String email) throws UtilizadorException, EncomendaException, TransportadoraException, ArtigoException, SistemaException {
        Utilizador utilizador = sistema.procuraUtilizador(email);

        if (encomendas.isEmpty()) {
            System.out.println();
            System.out.println();
            apresentacao.printBox();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(apresentacao.YELLOW + "                                                                                       NAO EXISTEM ENCOMENDAS FINALIZADAS!!" + apresentacao.RESET);
            System.out.println();
            System.out.println();
            System.out.println("                                                                                        Pressione enter para retroceder...");
            System.out.println();
            System.out.print("                                                                                                        ");
            Scanner ler = new Scanner(System.in);
            String c = ler.nextLine();
            return;
        }

        do {
            encomendas = utilizador.getListaEncomendas(Atributos.FINALIZADA);
            Encomenda[] menuItems = new Encomenda[encomendas.size()];
            encomendas.toArray(menuItems);
            int numPages = (int) Math.ceil((double) menuItems.length / pageSize);
            int currentPage = 1;
            int startIndex, endIndex;

            apresentacao.printFinalizadas();
            startIndex = (currentPage - 1) * pageSize;
            endIndex = Math.min(startIndex + pageSize, menuItems.length);

            System.out.println(apresentacao.RED + "[ENCOMENDAS]\n" + apresentacao.RESET);

            for (int i = startIndex; i < endIndex; i++) {
                int key = i + 1;
                Encomenda encomenda = menuItems[i];
                System.out.println(encomenda.showEncomenda());
            }
            System.out.println();
            System.out.println();
            System.out.println("                                                                                                 Pag." + currentPage + " de " + numPages);
            System.out.println();

            System.out.println(apresentacao.CYAN_BOLD + "                                 Pressione" + apresentacao.RESET + " '+' " +
                    apresentacao.CYAN_BOLD + "para avancar," + apresentacao.RESET + " '-' " + apresentacao.CYAN_BOLD + "para a retroceder," + apresentacao.RESET + " 'd' " + apresentacao.CYAN_BOLD +
                    "para devolver encomenda," + apresentacao.RESET + " 'v' " + apresentacao.CYAN_BOLD + "para ver um artigo de uma encomenda," + apresentacao.RESET + " 's' " + apresentacao.CYAN_BOLD +
                    "para sair" + apresentacao.RESET);
            System.out.println();
            System.out.print("                                                                                                      ");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("+") && currentPage < numPages) {
                currentPage++;
            } else if (input.equals("-") && currentPage > 1) {
                currentPage--;
            } else if (input.equals("d")) {

                System.out.println();
                System.out.println(apresentacao.RED +"                                                                               INTRODUZA O ID DA ENCOMENDA QUE DESEJA DEVOLVER"+ apresentacao.RESET);
                System.out.println();
                System.out.print("                                                                                                      ");

                int id = scanner.nextInt();

                if (menuItems.length == 1) {
                    sistema.devolveEncomenda(email,id);
                    System.out.println();
                    System.out.println();
                    apresentacao.printBox();
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println(apresentacao.YELLOW + "                                                                                        ENCOMENDA DEVOLVIDA COM SUCESSO!!");
                    System.out.println();
                    System.out.println();
                    System.out.println("                                                                                        NAO EXISTEM ENCOMENDAS DEVOLVIDAS!!" + apresentacao.RESET);
                    System.out.println();
                    System.out.println();
                    System.out.println("                                                                                        Pressione enter para retroceder...");
                    System.out.println();
                    System.out.print("                                                                                                        ");
                    Scanner ler = new Scanner(System.in);
                    String c = ler.nextLine();
                    break;
                }
                System.out.println();
                System.out.println();
                System.out.println(apresentacao.YELLOW + "                                                                                      ENCOMENDA DEVOLVIDA COM SUCESSO!!" + apresentacao.RESET);

                sistema.devolveEncomenda(email,id);

                System.out.println();
                System.out.println();
                System.out.println(apresentacao.CYAN_BOLD + "                                                                                      DESEJA DEVOLVER MAIS ENCOMENDAS?"+ apresentacao.RESET);
                System.out.println();
                System.out.println("                                                                                                   1 - SIM");
                System.out.println("                                                                                                   0 - NAO");
                System.out.println();
                System.out.print("                                                                                                      ");

                Scanner ler = new Scanner(System.in);
                int op = ler.nextInt();

                if (op == 1) {
                }
                else break;
            } else if (input.equals("v")) {

                System.out.println();
                System.out.println(apresentacao.RED +"                                                                                INTRODUZA O ID DA ENCOMENDA QUE DESEJA VER"+ apresentacao.RESET);
                System.out.println();
                System.out.print("                                                                                                      ");

                int id = scanner.nextInt();

                Encomenda encomenda = sistema.procuraEncomenda(id);

                apresentacao.paginateEncFinalizadaseDevolvidas(encomenda, 2, email, id, sistema);
            }
            else if (input.equals("s")) {
                break;
            }

        } while (true);
    }



    public void paginateDevolvidas(List<Encomenda> encomendas, int pageSize, String email) throws UtilizadorException, EncomendaException, TransportadoraException, ArtigoException, SistemaException {
        Utilizador utilizador = sistema.procuraUtilizador(email);

        if (encomendas.isEmpty()) {
            System.out.println();
            System.out.println();
            apresentacao.printBox();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(apresentacao.YELLOW + "                                                                                        NAO EXISTEM ENCOMENDAS DEVOLVIDAS!!" + apresentacao.RESET);
            System.out.println();
            System.out.println();
            System.out.println("                                                                                        Pressione enter para retroceder...");
            System.out.println();
            System.out.print("                                                                                                        ");
            Scanner ler = new Scanner(System.in);
            String c = ler.nextLine();
            return;
        }

        do {
            encomendas = utilizador.getListaEncomendas(Atributos.DEVOLVIDA);
            Encomenda[] menuItems = new Encomenda[encomendas.size()];
            encomendas.toArray(menuItems);
            int numPages = (int) Math.ceil((double) menuItems.length / pageSize);
            int currentPage = 1;
            int startIndex, endIndex;

            apresentacao.printDevolvidas();
            startIndex = (currentPage - 1) * pageSize;
            endIndex = Math.min(startIndex + pageSize, menuItems.length);

            System.out.println(apresentacao.RED + "[ENCOMENDAS]\n" + apresentacao.RESET);

            for (int i = startIndex; i < endIndex; i++) {
                int key = i + 1;
                Encomenda encomenda = menuItems[i];
                System.out.println(encomenda.showEncomenda());
            }
            System.out.println();
            System.out.println();
            System.out.println("                                                                                                 Pag." + currentPage + " de " + numPages);
            System.out.println();

            System.out.println(apresentacao.CYAN_BOLD + "                                                 Pressione" + apresentacao.RESET + " '+' " +
                    apresentacao.CYAN_BOLD + "para avancar," + apresentacao.RESET + " '-' " + apresentacao.CYAN_BOLD + "para a retroceder," + apresentacao.RESET + apresentacao.RESET + " 'v' " + apresentacao.CYAN_BOLD + "para ver um artigo de uma encomenda," + apresentacao.RESET + " 's' " + apresentacao.CYAN_BOLD +
                    "para sair" + apresentacao.RESET);
            System.out.println();
            System.out.print("                                                                                                      ");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("+") && currentPage < numPages) {
                currentPage++;
            } else if (input.equals("-") && currentPage > 1) {
                currentPage--;
            } else if (input.equals("v")) {

                System.out.println();
                System.out.println(apresentacao.RED +"                                                                                INTRODUZA O ID DA ENCOMENDA QUE DESEJA EDITAR"+ apresentacao.RESET);
                System.out.println();
                System.out.print("                                                                                                      ");

                int id = scanner.nextInt();

                Encomenda encomenda = sistema.procuraEncomenda(id);

                apresentacao.paginateEncFinalizadaseDevolvidas(encomenda, 2, email, id, sistema);
            }
            else if (input.equals("s")) {
                break;
            }

        } while (true);
    }

    public void paginateCompradorVendedor(List<Utilizador> lista, int pageSize ) throws ArtigoException, UtilizadorException, SistemaException, EncomendaException, TransportadoraException {
        Utilizador[] menuItems = lista.toArray(new Utilizador[0]);
        int numPages = (int) Math.ceil((double) menuItems.length / pageSize);
        int currentPage = 1;
        int startIndex, endIndex;
        do {
            apresentacao.printCompradorVendedor();
            System.out.println();
            System.out.println();
            startIndex = (currentPage - 1) * pageSize;
            endIndex = Math.min(startIndex + pageSize, menuItems.length);

            for (int i = startIndex; i < endIndex; i++) {
                int key = i + 1;
                Utilizador utilizador = menuItems[i];
                System.out.println();
                System.out.println("                                                                                                        (" + key + ")");
                System.out.println(utilizador.toString());
            }

            if (numPages > 1) {
            System.out.println();
            System.out.println();
            System.out.println("                                                                                                  Pag." + currentPage + " de " + numPages);
            System.out.println();
            System.out.println(apresentacao.CYAN_BOLD + "                                                                               Pressione" + apresentacao.RESET + " '+' " +
                        apresentacao.CYAN_BOLD + "para avancar, " + apresentacao.RESET + " '-' " + apresentacao.CYAN_BOLD + "para a retroceder e " + apresentacao.RESET + " 's' " + apresentacao.CYAN_BOLD +
                        "para sair");
                apresentacao.resetColor();
                System.out.println();
                System.out.print("                                                                                                    ");

                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().toLowerCase();

                if (input.equals("+") && currentPage < numPages) {
                    currentPage++;
                } else if (input.equals("-") && currentPage > 1) {
                    currentPage--;

                } else if (input.equals("s")) {
                    break;
                }
            } else if (numPages == 1) {
                System.out.println();
                System.out.println();
                System.out.println(apresentacao.YELLOW +"                                                                                               Pressione enter para sair..." + apresentacao.RESET);
                System.out.println();
                System.out.print("                                                                                                          ");

                Scanner scanner = new Scanner(System.in);
                String c = scanner.nextLine();
                break;
            }
        } while (true);
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
                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                             INDIQUE A OPÇÃO QUE PRETENDE" + apresentacao.RESET);
                    System.out.println();
                    System.out.println("                                                                                                 1 - DE SEMPRE");
                    System.out.println("                                                                                                 0 - PERÍODO DE TEMPO");
                    System.out.println();
                    System.out.print("                                                                                                           ");

                    ler = new Scanner(System.in);
                    x = ler.nextInt();

                    if (x == 1){
                        apresentacao.printVendedorDinheiro();
                        System.out.println();
                        Utilizador utilizador = sistema.vendedorMaisFaturouSempre();
                        System.out.println(utilizador.toString());
                        System.out.println(apresentacao.CYAN_BOLD +"                                                                                                   Faturou: "+ Apresentacao.RESET + utilizador.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == Atributos.VENDA).mapToDouble(Fatura::getValorTotal).sum());
                        System.out.println();
                        System.out.println(apresentacao.YELLOW +"                                                                                           Pressione enter para sair..." + apresentacao.RESET);
                        System.out.println();
                        System.out.print("                                                                                                      ");
                        ler = new Scanner(System.in);
                        c = ler.nextLine();
                        x = 0;
                        break;
                    } else if (x == 0) {
                        apresentacao.printVendedorDinheiro();
                        System.out.println();
                        LocalDate dataagr = LocalDate.now();
                        System.out.println("Data agr: " + sistema.getDataAtual());

                        ler = new Scanner(System.in);

                        System.out.println(apresentacao.CYAN_BOLD +"                                                                                         INSIRA A DATA INICIAL (AAAA-MM-DD)" + apresentacao.RESET);
                        System.out.print("                                                                                                   ");
                        d1 = ler.nextLine();
                        data1 = stringParaData(d1);
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD +"                                                                                         INSIRA A DATA FINAL (AAAA-MM-DD)" + apresentacao.RESET);
                        System.out.print("                                                                                                   ");
                        d2 = ler.nextLine();
                        data2 = stringParaData(d2);
                        Utilizador utilizador = sistema.vendedorMaisFaturouEntreDatas(data1, data2);
                        apresentacao.printVendedorDinheiro();
                        System.out.println();
                        System.out.println(utilizador.toString());
                        System.out.println(apresentacao.CYAN_BOLD +"                                                                                                   Faturou: "+ Apresentacao.RESET + utilizador.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == Atributos.VENDA).mapToDouble(Fatura::getValorTotal).sum());
                        System.out.println();
                        System.out.println(apresentacao.YELLOW +"                                                                                           Pressione enter para sair..." + apresentacao.RESET);
                        System.out.println();
                        System.out.print("                                                                                                      ");
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
                    System.out.println();
                    System.out.println(apresentacao.YELLOW +"                                                                                                   Pressione enter para sair..." + apresentacao.RESET);
                    System.out.println();
                    System.out.print("                                                                                                            ");
                    ler = new Scanner(System.in);
                    c = ler.nextLine();
                    x = 0;
                    break;

                case 3:
                    apresentacao.printEncomendasVendedor();
                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                                   INTRODUZA O EMAIL DO VENDEDOR" + apresentacao.RESET);
                    System.out.println();
                    System.out.print("                                                                                                   ");

                    ler = new Scanner(System.in);
                    email = ler.nextLine();

                    List<Encomenda> listaEncomendas = sistema.listaEncomendasVendedor(email);
                    Encomenda[] lista = new Encomenda[listaEncomendas.size()];

                    int i;
                    System.out.println();

                    listaEncomendas.toArray(lista);
                    for (i = 0; i < lista.length; i++){
                        Encomenda encomenda = lista[i];
                        System.out.println("                                                                                          " + encomenda.showEncomenda());
                    }


                    ler = new Scanner(System.in);
                    x = ler.nextInt();


                case 4:
                    apresentacao.printCompradorVendedor();
                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD +"                                                                                                  INDIQUE A SUA ESCOLHA" + apresentacao.RESET);
                    System.out.println();
                    System.out.println("                                                                                                     1 - COMPRADOR");
                    System.out.println("                                                                                                     0 - VENDEDOR");
                    System.out.println();
                    System.out.print("                                                                                                          ");

                    ler = new Scanner(System.in);
                    int op = ler.nextInt();

                    if (op == 1){
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD +"                                                                                         INSIRA A DATA INICIAL (AAAA-MM-DD)" + apresentacao.RESET);
                        System.out.print("                                                                                                   ");
                        ler = new Scanner(System.in);
                        d1 = ler.nextLine();
                        data1 = stringParaData(d1);
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD +"                                                                                         INSIRA A DATA FINAL (AAAA-MM-DD)" + apresentacao.RESET);
                        System.out.print("                                                                                                   ");
                        ler = new Scanner(System.in);
                        d2 = ler.nextLine();
                        data2 = stringParaData(d2);

                        paginateCompradorVendedor(sistema.maioresUtilizadoresEntreDatas(data1, data2, Atributos.VENDIDO), 2);
                        x = 0;
                        break;
                    }
                    else if (op == 0){
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD +"                                                                                         INSIRA A DATA INICIAL (AAAA-MM-DD)" + apresentacao.RESET);
                        System.out.print("                                                                                                   ");
                        ler = new Scanner(System.in);
                        d1 = ler.nextLine();
                        data1 = stringParaData(d1);
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD +"                                                                                         INSIRA A DATA FINAL (AAAA-MM-DD)" + apresentacao.RESET);
                        System.out.print("                                                                                                   ");
                        ler = new Scanner(System.in);
                        d2 = ler.nextLine();
                        data2 = stringParaData(d2);

                        paginateCompradorVendedor(sistema.maioresUtilizadoresEntreDatas(data1, data2, Atributos.VENDA), 2);
                        x = 0;
                        break;

                    }

                case 5:
                    apresentacao.printVintageDinheiro();
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                                        $GANHOS$ : "+ apresentacao.RESET + sistema.ganhoVintage());
                    System.out.println();
                    System.out.println(apresentacao.YELLOW +"                                                                                                  Pressione enter para sair..." + apresentacao.RESET);
                    System.out.println();
                    System.out.print("                                                                                                             ");


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






//MENU - UTILIZADOR
// Comprar -> lista de todos os produtos a venda no mercado
// Vendas -> lista do que ele esta a vender + a possibilidade de adcionar artigos para venda
// Faturas -> do que ele ja vendeu/ganhou juntamente com o produto que ele vendeu)
// Sair

// MENU - COMPRAR
// Lista de produtos à venda

// MENU - LISTA DE PRODUTOS À VENDA
// Encomendar
// Filtros (por exemplo, por transportadora)

// MENU - FILTROS
// Encomendar

// MENU - VENDAS
// Minha lista (lista de produtos a ser vendida pelo utilizador)
// Adcionar novo artigo à lista

// MENU - ADICONAR NOVO ARTIGO Á LISTA
// Tipo de artigo

// MENU - FATURAS
// Lista de artigos comprados e o seu valor gasto
// Lista de artigos vendidos e o seu valor obtido

// MENU - LISTA DE ARTIGOS COMPRADOS E O SEU VALOR GASTO

// MENU - LISTA DE ARTIGOS VENDIDOS E O SEU VALOR OBTIDO
