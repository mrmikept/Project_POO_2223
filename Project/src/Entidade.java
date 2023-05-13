public abstract class Entidade
{
    private int id;
    private String email;
    private String palavraPasse;
    private String nome;
    private String morada;
    private int nrFiscal;

    public Entidade()
    {
        this.id = 0;
        this.email = "";
        this.palavraPasse = "";
        this.nome = "";
        this.morada = "";
        this.nrFiscal = 0;
    }

    public Entidade(int id, String email, String palavraPasse, String nome, String morada, int nrFiscal)
    {
        this.id = id;
        this.email = email;
        this.palavraPasse = palavraPasse;
        this.nome = nome;
        this.morada = morada;
        this.nrFiscal = nrFiscal;
    }

    public Entidade(Entidade entidade)
    {
        this.id = entidade.getId();
        this.email = entidade.getEmail();
        this.palavraPasse = entidade.getPalavraPasse();
        this.nome = entidade.getNome();
        this.morada = entidade.getMorada();
        this.nrFiscal = entidade.getNrFiscal();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPalavraPasse() {
        return palavraPasse;
    }

    public void setPalavraPasse(String palavraPasse) {
        this.palavraPasse = palavraPasse;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getNrFiscal() {
        return nrFiscal;
    }

    public void setNrFiscal(int nrFiscal) {
        this.nrFiscal = nrFiscal;
    }

    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || this.getClass() != o.getClass())
        {
            return false;
        }
        Entidade entidade = (Entidade) o;
        return this.getId() == entidade.getId() &&
                this.getEmail().equals(entidade.getEmail()) &&
                this.getPalavraPasse().equals(entidade.getPalavraPasse()) &&
                this.getNome().equals(entidade.getNome()) &&
                this.getMorada().equals(entidade.getMorada()) &&
                this.getNrFiscal() == entidade.getNrFiscal();
    }

    public String toString()
    {
        return Apresentacao.CYAN_BOLD +"                                                                                                   [Utilizador]"+ Apresentacao.RESET + "\n" +
                Apresentacao.CYAN_BOLD +"                                                                                                   Numero: "+ Apresentacao.RESET + this.getId() + "\n" +
                Apresentacao.CYAN_BOLD +"                                                                                                   Email: "+ Apresentacao.RESET + this.getEmail() + "\n" +
                Apresentacao.CYAN_BOLD +"                                                                                                   Nome: "+ Apresentacao.RESET + this.getNome() + "\n" +
                Apresentacao.CYAN_BOLD +"                                                                                                   Morada: "+ Apresentacao.RESET + this.getMorada() + "\n" +
                Apresentacao.CYAN_BOLD +"                                                                                                   Numero Fiscal: "+ Apresentacao.RESET + this.getNrFiscal();
    }

    public abstract Entidade clone();

}
