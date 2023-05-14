
/**
 * Interface que implementa propriedades de artigos premium
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public interface Premium
{
    /**
     * Calcula a valorizacao de preço de um artigo premium
     * @param date Ano atual
     * @return Taxa de valorizacao
     */
    public double getValorizacaoPremium(int date);
}
