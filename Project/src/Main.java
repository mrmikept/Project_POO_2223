import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        int x;
        String c;

        System.out.println("1 - Iniciar sessao");
        System.out.println("2 - Registar utilizador");

        Scanner ler = new Scanner(System.in);

        x = ler.nextInt();

        switch (x)
        {
            case 1:
                System.out.println("Insira o seu email: ");
                ler = new Scanner(System.in);
                c = ler.nextLine();
                //TODO: verificar se existe o email
                System.out.println("Insira a sua password: ");
                ler = new Scanner(System.in);
                c = ler.nextLine();
                //TODO: verificar se existe a password
                //TODO: mudanca de menu
                x = 3; // MENU DO UTILIZADOR
                break;

            case 2:
                System.out.println("Insira o email: ");
                ler = new Scanner(System.in);
                c = ler.nextLine();
                //TODO: verificar se ja nao existe um email no sistema
                System.out.println("Insira o primeiro e ultimo nome: ");
                ler = new Scanner(System.in);
                c = ler.nextLine();
                System.out.println("Insira a morada: ");
                ler = new Scanner(System.in);
                c = ler.nextLine();
                System.out.println("Insira o numero fiscal: ");
                ler = new Scanner(System.in);
                c = ler.nextLine();
                //TODO: Duvida! Verificar se ja existe um numero fiiscal
                //TODO: mudanca de menu
                break;

            case 3: // MENU DO UTILIZADOR

        }

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
