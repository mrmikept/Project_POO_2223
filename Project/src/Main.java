import java.io.IOException;
import java.util.Scanner;

public class Main {
    private Sistema sistema;
    private Apresentacao apresentacao;

    public static void main(String[] args) throws IOException, UtilizadorException {
        new Main().run();
    }

    private Main() {
        this.sistema = new Sistema();
        this.apresentacao = new Apresentacao();
    }

    private void run() throws UtilizadorException {
        int x = 0;
        String email, pass, nome, morada;
        int nif;
        boolean bool;
        String[] s = {"Iniciar sessao", "Registar utilizador", "Sair"};
        Scanner ler = new Scanner(System.in);

        do {
            switch (x) {
                case 0:
                    apresentacao.printMenu(s,x);
                    x = ler.nextInt();
                    break;
                case 1: //Iniciar sessao
                    apresentacao.printLogin();
                    apresentacao.cyan();
                    System.out.println("                                                                                       Insira o seu email");
                    apresentacao.resetColor();
                    System.out.print("                                                                                       ");
                    ler = new Scanner(System.in);
                    email = ler.nextLine();
                    if(sistema.verificaUtilizador(email)) {
                        apresentacao.cyan();
                        System.out.println("                                                                                       Insira a sua password");
                        apresentacao.resetColor();
                        System.out.print("                                                                                       ");
                        ler = new Scanner(System.in);
                        pass = ler.nextLine();
                        if(sistema.verificaPassword(email, pass))
                        {
                            x = runUtilizador();
                            break;
                        }
                        else
                        {
                            while (x == 1) {
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                apresentacao.red();
                                System.out.println("                                                                               PASSWORD INCORRETA!! DESEJA CONTINUAR A TENTAR?");
                                apresentacao.resetColor();
                                System.out.println();
                                System.out.println("                                                                                                    1 - SIM");
                                System.out.println("                                                                                                    0 - NAO");
                                System.out.println();
                                System.out.print("                                                                                                      ");
                                x = ler.nextInt();

                                if (x == 0) break;

                                apresentacao.clear();
                                apresentacao.printLogin();
                                apresentacao.cyan();
                                System.out.println("                                                                                       Insira o seu email");
                                apresentacao.resetColor();
                                System.out.print("                                                                                       " + email);
                                System.out.println();
                                apresentacao.cyan();
                                System.out.println("                                                                                       Insira a sua password");
                                apresentacao.resetColor();
                                System.out.print("                                                                                       ");

                                ler = new Scanner(System.in);
                                pass = ler.nextLine();
                                if(sistema.verificaPassword(email, pass))
                                {
                                    x = runUtilizador();
                                    break;
                                }
                            }
                        }
                    }
                    else {
                        while (x == 1) {
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            apresentacao.red();
                            System.out.println("                                                                         EMAIL INCORRETO OU NAO EXISTE!! DESEJA CONTINUAR A TENTAR?");
                            apresentacao.resetColor();
                            System.out.println();
                            System.out.println("                                                                                                   1 - SIM");
                            System.out.println("                                                                                                   0 - NAO");
                            System.out.println();
                            System.out.print("                                                                                                      ");

                            x = ler.nextInt();

                            if (x == 0) break;

                            apresentacao.clear();
                            apresentacao.printLogin();
                            apresentacao.cyan();
                            System.out.println("                                                                                       Insira o seu email");
                            apresentacao.resetColor();
                            System.out.print("                                                                                       ");

                            ler = new Scanner(System.in);
                            email = ler.nextLine();

                            if(sistema.verificaUtilizador(email)) {
                                System.out.println();
                                apresentacao.cyan();
                                System.out.println("                                                                                       Insira a sua password");

                                apresentacao.resetColor();
                                System.out.print("                                                                                       ");
                                ler = new Scanner(System.in);
                                pass = ler.nextLine();
                                if(sistema.verificaPassword(email, pass))
                                {
                                    x = runUtilizador();
                                    break;
                                }
                                else
                                {
                                    while (x == 1) {
                                        System.out.println();
                                        System.out.println();
                                        System.out.println();
                                        apresentacao.red();
                                        System.out.println("                                                                               PASSWORD INCORRETA!! DESEJA CONTINUAR A TENTAR?");
                                        apresentacao.resetColor();
                                        System.out.println();
                                        System.out.println("                                                                                                    1 - SIM");
                                        System.out.println("                                                                                                    0 - NAO");
                                        System.out.println();
                                        System.out.print("                                                                                                      ");
                                        x = ler.nextInt();

                                        if (x == 0) break;

                                        apresentacao.clear();
                                        apresentacao.printLogin();
                                        apresentacao.cyan();
                                        System.out.println("                                                                                       Insira o seu email");
                                        apresentacao.resetColor();
                                        System.out.print("                                                                                       " + email);
                                        System.out.println();
                                        apresentacao.cyan();
                                        System.out.println("                                                                                       Insira a sua password");

                                        apresentacao.resetColor();
                                        System.out.print("                                                                                       ");

                                        ler = new Scanner(System.in);
                                        pass = ler.nextLine();
                                        if(sistema.verificaPassword(email, pass))
                                        {
                                            x = runUtilizador();
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;

                case 2: //Registar utilizador
                    apresentacao.printReg();

                    apresentacao.cyan();
                    System.out.println("                                                                                      Insira o email:");
                    apresentacao.resetColor();
                    System.out.print("                                                                                      ");
                    ler = new Scanner(System.in);
                    email = ler.nextLine();

                    System.out.println();

                    apresentacao.cyan();
                    System.out.println("                                                                                      Insira a password:");
                    apresentacao.resetColor();
                    System.out.print("                                                                                      ");
                    ler = new Scanner(System.in);
                    pass = ler.nextLine();

                    System.out.println();

                    //TODO: verificar se ja nao existe um email no sistema
                    apresentacao.cyan();
                    System.out.println("                                                                                      Insira o seu primeiro e ultimo nome:");
                    apresentacao.resetColor();
                    System.out.print("                                                                                      ");
                    ler = new Scanner(System.in);
                    nome = ler.nextLine();

                    System.out.println();

                    apresentacao.cyan();
                    System.out.println("                                                                                      Insira a sua morada: ");
                    apresentacao.resetColor();
                    System.out.print("                                                                                      ");

                    ler = new Scanner(System.in);
                    morada = ler.nextLine();

                    System.out.println();


                    apresentacao.cyan();
                    System.out.println("                                                                                      Insira o seu numero fiscal: ");
                    apresentacao.resetColor();
                    System.out.print("                                                                                      ");
                    ler = new Scanner(System.in);
                    nif = ler.nextInt();
                    //TODO: Duvida! Verificar se ja existe um numero fiiscal
                    sistema.adicionaUtilizador(email, pass, nome, morada, nif);
                    x = runUtilizador();
                    break;
            }
        } while (x != 3);
    }

    private int runUtilizador() //MENU UTILIZADOR
    {
        String[] s = {"Comprar", "Vendas", "Faturas", "Retroceder"};
        int x = 0;
        Scanner ler = new Scanner(System.in);

        apresentacao.printMenu(s,x);

        System.out.println("Insira a sua opcao: ");

        x = ler.nextInt();

        do {
            switch (x)
            {
                case 1: // MENU COMPRAR

                case 2: // MENU VENDAS

                case 3: // MENU FATURAS

                case 4: // VOLTAR PARA TRÁS
                    return 0;

            }
        } while (x != 0);

        return 0;
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
