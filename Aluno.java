public class Aluno {
    private int codigo;
    private String nome;
    private String curso;

    public Aluno(int codigo, String nome, String curso) {
        this.codigo = codigo;
        this.nome = nome;
        this.curso = curso;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCurso() {
        return curso;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s - %s", codigo, nome, curso);
    }
}
