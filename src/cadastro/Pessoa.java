package cadastro;

public class Pessoa {
    private static int idCounter = 1;
    private int id;
    private String nome;
    private String data;
    private String endereco;
    private String observacoes;

    public Pessoa(int id, String nome, String data, String endereco, String observacoes) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.endereco = endereco;
        this.observacoes = observacoes;
    }

    public Pessoa(String nome, String data, String endereco, String observacoes) {
        this.id = idCounter++;
        this.nome = nome;
        this.data = data;
        this.endereco = endereco;
        this.observacoes = observacoes;
    }

    public Pessoa() {
        this.id = idCounter++;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
