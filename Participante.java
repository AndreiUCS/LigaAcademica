public class Participante {
    private int codigo;
    private String nome;

    public Participante(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "[" + codigo + "] " + nome;
    }
}
