import java.io.IOException;
import java.util.Scanner;

public class Main {
    private Sistema sistema;
    private Apresentacao apresentacao;

    public static void main(String[] args) throws IOException, UtilizadorException {
        //int x = 0;
        //String c;
        //String[] s = {"Iniciar sessao", "Registar utilizador"};
        //System.out.println("Bem-vindo!!!");
        //System.out.println("1 - Iniciar sessao");
        //System.out.println("2 - Registar utilizador");
        //Scanner ler = new Scanner(System.in);
        //x = ler.nextInt();
        new Main().run();
    }

    private Main() {
        this.sistema = new Sistema();
        this.apresentacao = new Apresentacao();
    }

    private void run() throws UtilizadorException {
        String x = "Menu";
        String email, pass, nome, morada;
        int nif;
        boolean bool;
        String[] s = {"Iniciar sessao", "Registar utilizador"};
        Scanner ler = new Scanner(System.in);

        do {
            switch (x) {
                case 0:
                    apresentacao.printMenu(s);
                    x = ler.nextLine();
                    break;
                case 1: //Iniciar sessao
                    System.out.println("Insira o seu email: ");
                    ler = new Scanner(System.in);
                    email = ler.nextLine();
                    if(sistema.verificaUtilizador(email)) {
                        System.out.println("Insira a sua password: ");
                        ler = new Scanner(System.in);
                        pass = ler.nextLine();
                        if(sistema.verificaPassword(email, pass))
                        {
                            x = 3;
                            break;
                        }
                        else
                        {
                            bool = false;
                            while (!bool && x == 1) {
                                System.out.println("PASS INCORRETA!! TENTE DE NOVO");
                                System.out.println("Insira a sua password: ");
                                ler = new Scanner(System.in);
                                pass = ler.nextLine();
                                if(sistema.verificaPassword(email, pass))
                                {
                                    x = 3;
                                    break;
                                }
                            }
                        }
                    }

                    break;

                case 2: //Registar utilizador
                    System.out.println("Insira o email: ");
                    ler = new Scanner(System.in);
                    email = ler.nextLine();
                    System.out.println("Insira a password: ");
                    ler = new Scanner(System.in);
                    pass = ler.nextLine();
                    //TODO: verificar se ja nao existe um email no sistema
                    System.out.println("Insira o primeiro e ultimo nome: ");
                    ler = new Scanner(System.in);
                    nome = ler.nextLine();
                    System.out.println("Insira a morada: ");
                    ler = new Scanner(System.in);
                    morada = ler.nextLine();
                    System.out.println("Insira o numero fiscal: ");
                    ler = new Scanner(System.in);
                    nif = ler.nextInt();
                    //TODO: Duvida! Verificar se ja existe um numero fiiscal
                    sistema.adicionaUtilizador(email, pass, nome, morada, nif);
                    x = 3; //TODO: mudanca de menu
                    break;

                case 3: // MENU DO UTILIZADOR
                    System.out.println("1 - Comprar");
                    System.out.println("2 - Vendas");
                    System.out.println("3 - Faturas");
                    System.out.println("4 - Sair");
                    ler = new Scanner(System.in);
                    x = ler.nextInt();
                    break;

                case 4:
            }



        } while (x != 7);
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
