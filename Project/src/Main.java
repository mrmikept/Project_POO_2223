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
                    input = ler.nextLine();
                    CarregamentoFicheiro.escreveFicheiro(this.sistema, input);
                    apresentacao.printGuardar();
                    apresentacao.printEnter("ESTADO GUARDADO COM SUCESSO!!");
                    ler.nextLine();
                    x = 0;
                    break;

                case 3:
                    apresentacao.printMenuCarregarEstado();
                    input = ler.nextLine();
                    Path path = Paths.get(input);
                    boolean existe = Files.exists(path);
                    if (existe) {
                        this.sistema = CarregamentoFicheiro.lerFicheiro(input);
                        apresentacao.printLoad();
                        apresentacao.printEnter("ESTADO CARREGADO COM SUCESSO!!");
                        ler.nextLine();
                        x = 0;
                        break;
                    }
                    else {x = 3; break;} //TODO Verificar mensagem erro quando ficheiro não existe!

                case 4 :
                    apresentacao.printMenuAutomatizacao();
                    input_backup = ler.nextLine();
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
                    email = ler.nextLine();
                    if (sistema.verificaUtilizador(email)) {
                        apresentacao.printMensagem("Insira a sua password:", 88, 1);
                        apresentacao.printEspacos(88);
                        pass = ler.nextLine();
                        if (sistema.verificaPassword(email, pass)) {
                            x = runUtilizador(email);
                            break;
                        } else {
                            while (x == 1) {
                                apresentacao.printClear(2);
                                apresentacao.printMensagemCentrada("PASSWORD INCORRETA!! DESEJA CONTINUAR A TENTAR?",2);
                                apresentacao.printMensagemSimOuNao(102);

                                x = ler.nextInt();
                                if (x == 0) break;

                                apresentacao.printLogin();
                                apresentacao.printMensagem("Insira o seu email:", 88, 1);
                                apresentacao.printEspacos(88);
                                System.out.print(email);
                                apresentacao.printClear(1);
                                apresentacao.printMensagem("Insira a sua password:", 88, 1);
                                apresentacao.printEspacos(88);

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

                            x = ler.nextInt();

                            if (x == 0) break;

                            apresentacao.printLogin();
                            apresentacao.printMensagem("Insira o seu email:", 88, 1);
                            apresentacao.printEspacos(88);

                            email = ler.nextLine();

                            if (sistema.verificaUtilizador(email)) {
                                apresentacao.printClear(1);
                                apresentacao.printMensagem("Insira a sua password:", 88, 1);
                                apresentacao.printEspacos(88);

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
                                        x = ler.nextInt();

                                        if (x == 0) break;

                                        apresentacao.printLogin();
                                        apresentacao.printMensagem("Insira o seu email:", 88, 1);
                                        apresentacao.printEspacos(88);
                                        System.out.print(email);
                                        apresentacao.printClear(1);
                                        apresentacao.printMensagem("Insira a sua password:", 88, 1);
                                        apresentacao.printEspacos(88);

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
                    nomeTrans = ler.nextLine();
                    Transportadora transportadora = sistema.procuraTransportadora(nomeTrans);
                    apresentacao.printDadosTransportadora(transportadora);
                    ler.nextLine();
                    x = 0;
                    break;

                case 3: //Registar utilizador
                    apresentacao.printReg();
                    apresentacao.printMensagem("Insira o email:", 86, 1);
                    apresentacao.printEspacos(86);
                    email = ler.nextLine();
                    if (!sistema.verificaUtilizador(email)) {
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
                        sistema.adicionaUtilizador(email, pass, nome, morada, nif);

                        apresentacao.printReg();

                        apresentacao.printMensagem("UTILIZADOR REGISTADO COM SUCESSO!! DESEJA CONTINUAR A REGISTAR?",72,3);

                        apresentacao.printClear(1);
                        apresentacao.printMensagemSimOuNao(100);

                        x = ler.nextInt();

                        if (x == 1) x = 3;
                        else x = 0;
                    } else {
                        apresentacao.printClear(3);
                        apresentacao.printMensagem("ESTE EMAIL JÁ EXISTE!! DESEJA TENTAR DE NOVO?",81,2);
                        apresentacao.printClear(2);
                        apresentacao.printMensagemSimOuNao(99);

                        x = ler.nextInt();
                        if (x == 1) x = 3;
                        else x = 0;
                    }
                    break;

                case 4:
                    apresentacao.printRegTrans();
                    apresentacao.printMensagem("Insira o nome da transportadora:",86,1);
                    apresentacao.printEspacos(86);
                    nomeTrans = ler.nextLine();
                    if (!sistema.verificaTransportadora(nomeTrans)) {

                        apresentacao.printClear(1);

                        apresentacao.printMensagem("Insira a margem de lucro que pretende obter:",86,1);
                        apresentacao.printEspacos(86);

                        lucro = ler.nextDouble();

                        apresentacao.printClear(1);

                        apresentacao.printMensagem("Insira 1-\"Normal\" ou 2-\"Premium\":", 86,1);
                        apresentacao.printEspacos(86);

                        tipo = ler.nextInt();

                        sistema.adicionaTransportadora(nomeTrans, lucro, tipo,2);

                        apresentacao.printRegTrans();
                        apresentacao.printMensagem("TRANSPORTADORA REGISTADA COM SUCESSO!! DESEJA CONTINUAR A REGISTAR?",71,3);

                        apresentacao.printClear(1);
                        apresentacao.printMensagemSimOuNao(100);

                        x = ler.nextInt();

                        if (x == 1) x = 4;
                        else x = 0;
                    } else {
                        apresentacao.printClear(3);
                        apresentacao.printMensagem("ESTA TRANSPORTADORA JÁ EXISTE!! DESEJA TENTAR DE NOVO?",77,2);
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
        } while (x != 6);

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
                    apresentacao.paginateMenuCompras(sistema.getArtigosVenda(email), 2, email, sistema);
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
                        apresentacao.printMensagem("NÃO POSSUI NENHUM ARTIGO À VENDA!!",87,1);
                        apresentacao.printClear(1);
                        apresentacao.printMensagem("DESEJA ADICONAR ALGUM ARTIGO À SUA LISTA DE VENDAS?",79,0);
                        apresentacao.printClear(2);
                        apresentacao.printMensagemSimOuNao(100);

                        ler = new Scanner(System.in);
                        x = ler.nextInt();

                        if (x == 1) runArtigosVender(email);
                        else if (x == 0) {x=0; break;}
                    } else {
                        apresentacao.paginateMenuVendas(sistema.getArtigosVendaUtilizador(email), 2, email, sistema);
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

        int opcao, nrDonos, tamanho, padrao, tipo, tipoCordao, dataLancamento;
        double precoBase, avaliacao, dimensao;
        String id, descricao, marca, material, cor;
        int x = 0;

        Scanner ler = new Scanner(System.in);
        
        do {
            switch (x) {

                case 0:
                    apresentacao.printRunArtigosVendaCase0();
                    x = ler.nextInt();
                    break;

                case 1:
                    apresentacao.printTshirt();
                    apresentacao.printMensagem("INTRODUZA O ID DA T-SHIRT (CÓDIGO DE BARRAS)",84,1);
                    apresentacao.printEspacos(84);
                    id = ler.nextLine();

                    if (!sistema.verificaArtigoVenda(id)) {

                        apresentacao.printClear(1);

                        apresentacao.printMensagem("INTRODUZA UMA DESCRIÇÃO",84,1);
                        apresentacao.printEspacos(84);
                        descricao = ler.nextLine();

                        apresentacao.printClear(1);

                        apresentacao.printMensagem("INTRODUZA A MARCA",84,1);
                        apresentacao.printEspacos(84);
                        marca = ler.nextLine();

                        apresentacao.printClear(1);

                        apresentacao.printMensagem("INTRODUZA O PREÇO BASE",84,1);
                        apresentacao.printEspacos(84);

                        precoBase = ler.nextDouble();

                        apresentacao.printClear(1);

                        apresentacao.printTamanhosTshirt();
                        tamanho = ler.nextInt();

                        apresentacao.printClear(1);

                        apresentacao.printPadroesTshirt();
                        padrao = ler.nextInt();

                        apresentacao.printClear(1);

                        apresentacao.printEstadoArtigo();
                        opcao = ler.nextInt();

                        if (opcao == 1) {
                            apresentacao.printTshirt();
                            apresentacao.printClear(2);
                            String nomeTransportadora = apresentacao.paginateTransportadora(sistema.getListaTransportadoras(), 1, email, sistema);
                            Utilizador utilizador = sistema.procuraUtilizador(email);
                            Tshirt tshirt = new Tshirt(id, utilizador, descricao, marca, precoBase, 0,0, this.sistema.procuraTransportadora(nomeTransportadora), Atributos.VENDA, tamanho, padrao);
                            apresentacao.clear();
                            apresentacao.printClear(5);
                            System.out.println(tshirt.showArtigo()); //TODO: Passar para o showArtigo para a Apresentação
                            apresentacao.printClear(3);
                            apresentacao.printMensagem("TEM A CERTEZA QUE DESEJA ADICIONAR ESTE ARTIGO?",81,1);
                            apresentacao.printClear(1);
                            apresentacao.printMensagemSimOuNao(99);

                            x = ler.nextInt();

                            if (x == 1) {
                                sistema.adicionaArtigo(tshirt);
                                apresentacao.printClear(3);
                                apresentacao.printMensagem("ARTIGO ADICIONADO COM SUCESSO!! DESEJA ADICIONAR OUTRO?",76,3);
                                apresentacao.printClear(1);
                                apresentacao.printMensagemSimOuNao(100);

                                x = ler.nextInt();

                                if (x == 1) {
                                    x = 0;
                                    break;

                                } else if (x == 0) {x = 4; break;}

                            } else if (x == 0) {x = 4; break;}

                        } else {
                            apresentacao.printClear(1);
                            apresentacao.printMensagem("INTRODUZA A SUA AVALIAÇÃO",84,1);
                            apresentacao.printEspacos(84);
                            avaliacao = ler.nextDouble();

                            apresentacao.printClear(1);

                            apresentacao.printMensagem("INTRODUZA O NÚMERO DE DONOS QUE JÁ TEVE",84,1);
                            apresentacao.printEspacos(84);
                            nrDonos = ler.nextInt();

                            apresentacao.printTshirt();
                            apresentacao.printClear(2);
                            String nomeTransportadora = apresentacao.paginateTransportadora(sistema.getListaTransportadoras(), 1, email, sistema);
                            Utilizador utilizador = sistema.procuraUtilizador(email);
                            Tshirt tshirt = new Tshirt(id, utilizador, descricao, marca, precoBase, nrDonos, avaliacao, this.sistema.procuraTransportadora(nomeTransportadora), Atributos.VENDA, tamanho, padrao);

                            apresentacao.clear();
                            apresentacao.printClear(5);
                            System.out.println(tshirt.showArtigo()); //TODO: TAMBEMMMMMMM
                            apresentacao.printClear(3);

                            apresentacao.printMensagem("TEM A CERTEZA QUE DESEJA ADICIONAR ESTE ARTIGO?",81,1);
                            apresentacao.printClear(1);
                            apresentacao.printMensagemSimOuNao(99);

                            x = ler.nextInt();

                            if (x == 1) {
                                sistema.adicionaArtigo(tshirt);
                                apresentacao.printClear(3);
                                apresentacao.printMensagem("ARTIGO ADICIONADO COM SUCESSO!! DESEJA ADICIONAR OUTRO?",76,3);
                                apresentacao.printClear(1);
                                apresentacao.printMensagemSimOuNao(100);

                                x = ler.nextInt();

                                if (x == 1) {
                                    x = 0;
                                    break;
                                } else if (x == 0) {x = 4; break;}

                            } else if (x == 0) {x = 4; break;}

                        }
                    } else {
                        apresentacao.printClear(2);
                        apresentacao.printMensagem("ESTE ARTIGO JÁ ESTÁ A VENDA!",94,2);
                        apresentacao.printClear(1);
                        apresentacao.printMensagem("DESEJA TENTAR NOVAMENTE?",86,1);
                        apresentacao.printClear(1);
                        apresentacao.printMensagemSimOuNao(014);

                        x = ler.nextInt();

                        if (x == 1) {
                            x = 1;
                            break;
                        } else if (x == 0) {x = 4; break;}
                    }

                case 2:
                    apresentacao.printMala();
                    apresentacao.printMensagem("INTRODUZA O ID DA MALA (CÓDIGO DE BARRAS)",84,1);
                    apresentacao.printEspacos(84);
                    id = ler.nextLine();

                    if (!sistema.verificaArtigoVenda(id)) {

                        apresentacao.printClear(1);
                        apresentacao.printMensagem("INTRODUZA UMA DESCRIÇÃO",84,1);
                        apresentacao.printEspacos(84);
                        descricao = ler.nextLine();

                        apresentacao.printClear(1);

                        apresentacao.printMensagem("INTRODUZA A MARCA",84,1);
                        apresentacao.printEspacos(84);
                        marca = ler.nextLine();

                        apresentacao.printClear(1);

                        apresentacao.printMensagem("INTRODUZA O PRECO BASE",84,1);
                        apresentacao.printEspacos(84);
                        precoBase = ler.nextDouble();

                        apresentacao.printClear(1);

                        apresentacao.printMensagem("INTRODUZA A SUA DIMENSÃO",84,1);
                        apresentacao.printEspacos(84);
                        dimensao = ler.nextDouble();

                        apresentacao.printClear(1);

                        apresentacao.printMensagem("INTRODUZA O MATERIAL",84,1);
                        apresentacao.printEspacos(84);
                        material = ler.nextLine();

                        apresentacao.printClear(1);

                        apresentacao.printMensagem("INTRODUZA O SEU ANO DE LANÇAMENTO (EX: 2023)",84,1);
                        apresentacao.printEspacos(84);
                        dataLancamento = ler.nextInt();

                        apresentacao.printClear(1);

                        apresentacao.printArtigoPremiumOuNormal();
                        tipo = ler.nextInt();

                        apresentacao.printClear(1);

                        apresentacao.printEstadoArtigo();

                        opcao = ler.nextInt();

                        if (opcao == 1) {
                            apresentacao.printMala();
                            apresentacao.printClear(2);
                            String nomeTransportadora = apresentacao.paginateTransportadora(sistema.getListaTransportadoras(), 1, email, sistema);
                            Utilizador utilizador = sistema.procuraUtilizador(email);
                            Mala mala = new Mala(id, utilizador, descricao, marca, precoBase, 0,0, this.sistema.procuraTransportadora(nomeTransportadora), Atributos.VENDA, dimensao, material, dataLancamento, tipo);

                            apresentacao.clear();
                            apresentacao.printClear(5);
                            System.out.println(mala.showArtigo()); //TODO: TAMBEMMMMMMM
                            apresentacao.printClear(3);
                            apresentacao.printMensagem("TEM A CERTEZA QUE DESEJA ADICIONAR ESTE ARTIGO?",81,1);
                            apresentacao.printClear(1);
                            apresentacao.printMensagemSimOuNao(99);

                            x = ler.nextInt();

                            if (x == 1) {
                                sistema.adicionaArtigo(mala);
                                apresentacao.printClear(3);
                                apresentacao.printMensagem("ARTIGO ADICIONADO COM SUCESSO!! DESEJA ADICIONAR OUTRO?",76,3);
                                apresentacao.printClear(1);
                                apresentacao.printMensagemSimOuNao(100);

                                x = ler.nextInt();

                                if (x == 1) {
                                    x = 0;
                                    break;
                                } else if (x == 0) {x = 4; break;}

                            } else if (x == 0) {x = 4; break;}

                        } else {
                            apresentacao.printClear(1);
                            apresentacao.printMensagem("INTRODUZA A SUA AVALIAÇÃO",84,1);
                            apresentacao.printEspacos(84);
                            avaliacao = ler.nextDouble();

                            apresentacao.printClear(1);

                            apresentacao.printMensagem("INTRODUZA O NÚMERO DE DONOS QUE JÁ TEVE",84,1);
                            apresentacao.printEspacos(84);
                            nrDonos = ler.nextInt();

                            apresentacao.printMala();
                            apresentacao.printClear(2);

                            String nomeTransportadora = apresentacao.paginateTransportadora(sistema.getListaTransportadoras(), 1, email, sistema);
                            Utilizador utilizador = sistema.procuraUtilizador(email);
                            Mala mala = new Mala(id, utilizador, descricao, marca, precoBase, nrDonos, avaliacao, this.sistema.procuraTransportadora(nomeTransportadora), Atributos.VENDA, dimensao, material, dataLancamento, tipo);
                            apresentacao.clear();
                            apresentacao.printClear(5);
                            System.out.println(mala.showArtigo());
                            apresentacao.printClear(3);
                            apresentacao.printMensagem("TEM A CERTEZA QUE DESEJA ADICIONAR ESTE ARTIGO?",81,1);
                            apresentacao.printClear(1);
                            apresentacao.printMensagemSimOuNao(99);

                            x = ler.nextInt();

                            if (x == 1) {
                                sistema.adicionaArtigo(mala);
                                apresentacao.printClear(3);
                                apresentacao.printMensagem("ARTIGO ADICIONADO COM SUCESSO!! DESEJA ADICIONAR OUTRO?",76,3);
                                apresentacao.printClear(1);
                                apresentacao.printMensagemSimOuNao(100);

                                x = ler.nextInt();

                                if (x == 1) {
                                    x = 0;
                                    break;
                                } else if (x == 0) {x = 4; break;}

                            } else if (x == 0) {x = 4; break;}

                        }
                    } else {
                        apresentacao.printClear(2);
                        apresentacao.printMensagem("ESTE ARTIGO JÁ ESTÁ A VENDA!",94,2);
                        apresentacao.printClear(1);
                        apresentacao.printMensagem("DESEJA TENTAR NOVAMENTE?",96,1);
                        apresentacao.printClear(1);
                        apresentacao.printMensagemSimOuNao(104);

                        x = ler.nextInt();

                        if (x == 1) {
                            x = 2;
                        } else if (x == 0) {x = 4; break;}
                    }

                case 3:
                    apresentacao.printSapatilhas();
                    apresentacao.printMensagem("INTRODUZA O ID DA SAPATILHA (CÓDIGO DE BARRAS)",84,1);
                    apresentacao.printEspacos(84);
                    id = ler.nextLine();

                    if (!sistema.verificaArtigoVenda(id)) {

                        apresentacao.printClear(1);
                        apresentacao.printMensagem("INTRODUZA UMA DESCRIÇÃO",84,1);
                        apresentacao.printEspacos(84);
                        descricao = ler.nextLine();

                        apresentacao.printClear(1);

                        apresentacao.printMensagem("INTRODUZA A MARCA",84,1);
                        apresentacao.printEspacos(84);
                        marca = ler.nextLine();

                        apresentacao.printClear(1);

                        apresentacao.printMensagem("INTRODUZA O PREÇO BASE",84,1);
                        apresentacao.printEspacos(84);
                        precoBase = ler.nextDouble();

                        apresentacao.printClear(1);

                        apresentacao.printMensagem("INTRODUZA O SEU TAMANHO (Nº DE CALÇADO)",84,1);
                        apresentacao.printEspacos(84);
                        tamanho = ler.nextInt();

                        apresentacao.printClear(1);

                        apresentacao.printTipoCordao();
                        tipoCordao = ler.nextInt();

                        apresentacao.printClear(1);

                        apresentacao.printMensagem("INTRODUZA A SUA COR",84,1);
                        apresentacao.printEspacos(84);
                        cor = ler.nextLine();

                        apresentacao.printClear(1);

                        apresentacao.printMensagem("INTRODUZA O ANO DE LANÇAMENTO",84,1);
                        apresentacao.printEspacos(84);
                        dataLancamento = ler.nextInt();

                        apresentacao.printClear(1);

                        apresentacao.printArtigoPremiumOuNormal();
                        tipo = ler.nextInt();

                        apresentacao.printClear(1);

                        apresentacao.printArtigosVenda();
                        opcao = ler.nextInt();

                        if (opcao == 1) {
                            apresentacao.printSapatilhas();
                            apresentacao.printClear(2);
                            String nomeTransportadora = apresentacao.paginateTransportadora(sistema.getListaTransportadoras(), 1, email, sistema);
                            Utilizador utilizador = sistema.procuraUtilizador(email);
                            Sapatilha sapatilha = new Sapatilha(id, utilizador, descricao, marca, precoBase, 0,0, this.sistema.procuraTransportadora(nomeTransportadora), Atributos.VENDA, tamanho, tipoCordao, cor, dataLancamento, tipo);

                            apresentacao.clear();
                            apresentacao.printClear(5);
                            System.out.println(sapatilha.showArtigo());
                            apresentacao.printClear(3);
                            apresentacao.printMensagem("TEM A CERTEZA QUE DESEJA ADICIONAR ESTE ARTIGO?",81,1);
                            apresentacao.printClear(1);
                            apresentacao.printMensagemSimOuNao(99);

                            x = ler.nextInt();

                            if (x == 1) {
                                sistema.adicionaArtigo(sapatilha);
                                apresentacao.printClear(3);
                                apresentacao.printMensagem("ARTIGO ADICIONADO COM SUCESSO!! DESEJA ADICIONAR OUTRO?",76,3);
                                apresentacao.printClear(1);
                                apresentacao.printMensagemSimOuNao(100);

                                x = ler.nextInt();

                                if (x == 1) {
                                    x = 0;
                                    break;
                                } else if (x == 0) {x = 4; break;}

                            } else if (x == 0) {x = 4; break;}

                        } else {
                            apresentacao.printClear(1);
                            apresentacao.printMensagem("INTRODUZA A SUA AVALIAÇÃO",84,1);
                            apresentacao.printEspacos(84);
                            avaliacao = ler.nextDouble();

                            apresentacao.printClear(1);
                            apresentacao.printMensagem("INTRODUZA O NÚMERO DE DONOS QUE JÁ TEVE",84,1);
                            apresentacao.printEspacos(84);
                            nrDonos = ler.nextInt();

                            apresentacao.printSapatilhas();
                            apresentacao.printClear(2);
                            String nomeTransportadora = apresentacao.paginateTransportadora(sistema.getListaTransportadoras(), 1, email, sistema);
                            Utilizador utilizador = sistema.procuraUtilizador(email);
                            Sapatilha sapatilha = new Sapatilha(id, utilizador, descricao, marca, precoBase, nrDonos, avaliacao, this.sistema.procuraTransportadora(nomeTransportadora), Atributos.VENDA, tamanho, tipoCordao, cor, dataLancamento, tipo);

                            apresentacao.clear();
                            apresentacao.printClear(5);
                            System.out.println(sapatilha.showArtigo());
                            apresentacao.printClear(3);
                            apresentacao.printMensagem("TEM A CERTEZA QUE DESEJA ADICIONAR ESTE ARTIGO?",81,1);
                            apresentacao.printClear(1);
                            apresentacao.printMensagemSimOuNao(99);

                            x = ler.nextInt();

                            if (x == 1) {
                                sistema.adicionaArtigo(sapatilha); //TODO: TAMBEMMMMM
                                apresentacao.printClear(3);
                                apresentacao.printMensagem("ARTIGO ADICIONADO COM SUCESSO!! DESEJA ADICIONAR OUTRO?",76,3);
                                apresentacao.printClear(1);
                                apresentacao.printMensagemSimOuNao(100);

                                x = ler.nextInt();

                                if (x == 1) {
                                    x = 0;
                                    break;
                                } else if (x == 0) {x = 4; break;}

                            } else if (x == 0) {x = 4; break;}

                        }
                    } else {
                        apresentacao.printClear(2);
                        apresentacao.printMensagem("ESTE ARTIGO JÁ ESTÁ A VENDA!",94,2);
                        apresentacao.printClear(1);
                        apresentacao.printMensagem("DESEJA TENTAR NOVAMENTE?",96,1);
                        apresentacao.printClear(1);
                        apresentacao.printMensagemSimOuNao(104);

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
                    String[] o ={"Avançar para uma data", "Adicionar dias à data atual"};
                    apresentacao.printOpcoes("Selecione o metodo para avançar no tempo:", o);
                    apresentacao.printEspacos(103);
                    ler = new Scanner(System.in);
                    opcao = ler.nextInt();
                    LocalDate localDate;

                    if (opcao == 1) { // PARA UMA DATA
                        apresentacao.printSaltaTempo();
                        apresentacao.printMensagem("Introduza o dia (DD)",87,1);
                        System.out.println();
                        apresentacao.printEspacos(87);
                        ler = new Scanner(System.in);
                        dia = ler.nextInt();

                        System.out.println();

                        apresentacao.printMensagem("Introduza o mês (MM)",87,1);
                        System.out.println();
                        apresentacao.printEspacos(87);
                        ler = new Scanner(System.in);
                        mes = ler.nextInt();

                        System.out.println();

                        apresentacao.printMensagem("Introduza o ano (AAAA)",87,1);
                        System.out.println();
                        apresentacao.printEspacos(87);
                        ler = new Scanner(System.in);
                        ano = ler.nextInt();

                        sistema.saltaTempo(ano, mes, dia);
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

                    if (opcao == 2) { // ADICIONAR DIAS A DATA ATUAL
                        apresentacao.printSaltaTempo();
                        apresentacao.printMensagem("Introduza o numero de dias que pretende avançar (DD)",78,1);
                        System.out.println();
                        apresentacao.printEspacos(78);
                        ler = new Scanner(System.in);
                        dia = ler.nextInt();

                        sistema.saltaTempo(dia);
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
                    x = 0;
                    break;

                case 2: // MENU ALTERAR IMPOSTO E TAXAS
                    apresentacao.printTax();
                    int imposto;
                    double taxa;

                    apresentacao.printMensagem("Defina a taxa de imposto",86,1);
                    System.out.println();
                    apresentacao.printEspacos(86);
                    ler = new Scanner(System.in);
                    imposto = ler.nextInt();
                    sistema.getTaxas().setImposto(imposto);

                    System.out.println();
                    apresentacao.printMensagem("Defina a taxa de uma encomenda pequena",86,1);
                    System.out.println();
                    apresentacao.printEspacos(86);
                    ler = new Scanner(System.in);
                    taxa = ler.nextDouble();
                    sistema.getTaxas().setTaxaEncPequena(taxa);

                    System.out.println();
                    apresentacao.printMensagem("Defina a taxa de uma encomenda media",86,1);
                    System.out.println();
                    apresentacao.printEspacos(86);
                    ler = new Scanner(System.in);
                    taxa = ler.nextDouble();
                    sistema.getTaxas().setTaxaEncMedia(taxa);

                    System.out.println();
                    apresentacao.printMensagem("Defina a taxa de uma encomenda grande",86,1);
                    System.out.println();
                    apresentacao.printEspacos(86);
                    ler = new Scanner(System.in);
                    taxa = ler.nextDouble();
                    sistema.getTaxas().setTaxaEncGrande(taxa);

                    apresentacao.printTax();

                    apresentacao.printMensagem("ALTERACAO REALIZADA COM SUCESSO!!",87,3);
                    apresentacao.printEnterSair();
                    ler = new Scanner(System.in);
                    c = ler.nextLine();
                    x = 0;
                    break;

                case 3:
                    apresentacao.printReturnTime();
                    apresentacao.printMensagem("Defina o limite de dias para a devolucao de uma encomenda",77,1);
                    System.out.println();
                    apresentacao.printEspacos(77);
                    ler = new Scanner(System.in);
                    x = ler.nextInt();

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
                    apresentacao.paginatePendentes(encomendas,5, email, sistema);
                    x = 0;
                    break;

                case 2: //MENU EXPEDIDAS
                    encomendas = utilizador.getListaEncomendas(Atributos.EXPEDIDA);
                    apresentacao.paginateExpedidas(encomendas,5, email, sistema);
                    x = 0;
                    break;

                case 3: //MENU FINALIZADAS
                    encomendas = utilizador.getListaEncomendas(Atributos.FINALIZADA);
                    apresentacao.paginateFinalizadas(encomendas,5, email, sistema);
                    x = 0;
                    break;

                case 4:
                    encomendas = utilizador.getListaEncomendas(Atributos.DEVOLVIDA);
                    apresentacao.paginateDevolvidas(encomendas,5, email, sistema);
                    x = 0;
                    break;

            }

        } while (x != 5);

        return 0;
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

                        apresentacao.paginateCompradorVendedor(sistema.maioresUtilizadoresEntreDatas(data1, data2, Atributos.VENDIDO), 2);
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

                        apresentacao.paginateCompradorVendedor(sistema.maioresUtilizadoresEntreDatas(data1, data2, Atributos.VENDA), 2);
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
