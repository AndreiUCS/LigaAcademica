public class ListaAlunos {
    private Nodo<Aluno> header;
    private int tamanho;

    public ListaAlunos() {
        header = new Nodo<>(null);
        tamanho = 0;
    }

    public void insereAlunoOrdenado(int codigo, String nome, String curso) {
        Aluno aluno = new Aluno(codigo, nome, curso);
        Nodo<Aluno> novo = new Nodo<>(aluno);

        Nodo<Aluno> ant = header;
        Nodo<Aluno> atual = header.getProximo();

        while (atual != null && aluno.getNome().compareToIgnoreCase(atual.getElemento().getNome()) > 0) {
            ant = atual;
            atual = atual.getProximo();
        }

        novo.setProximo(atual);
        ant.setProximo(novo);
        tamanho++;
    }

    public boolean removeAlunoPorCodigo(int codigo) {
        Nodo<Aluno> ant = header;
        Nodo<Aluno> atual = header.getProximo();

        while (atual != null) {
            if (atual.getElemento().getCodigo() == codigo) {
                ant.setProximo(atual.getProximo());
                tamanho--;
                return true;
            }
            ant = atual;
            atual = atual.getProximo();
        }
        return false;
    }

    public void removeAlunoPorNome(String nome) {
        Nodo<Aluno> ant = header;
        Nodo<Aluno> atual = header.getProximo();

        while (atual != null) {
            if (atual.getElemento().getNome().equalsIgnoreCase(nome)) {
                ant.setProximo(atual.getProximo());
                tamanho--;
                return;
            }
            ant = atual;
            atual = atual.getProximo();
        }
    }

    public void exibeListaEstudantes() {
        Nodo<Aluno> atual = header.getProximo();
        while (atual != null) {
            System.out.println(atual.getElemento());
            atual = atual.getProximo();
        }
        if (tamanho == 0) {
            System.out.println("Nenhum aluno cadastrado.");
        }
    }

    public Aluno buscarPorCodigo(int codigo) {
        Nodo<Aluno> atual = header.getProximo();
        while (atual != null) {
            if (atual.getElemento().getCodigo() == codigo) {
                return atual.getElemento();
            }
            atual = atual.getProximo();
        }
        return null;
    }

    public Aluno buscarPorNome(String nome) {
        Nodo<Aluno> atual = header.getProximo();
        while (atual != null) {
            if (atual.getElemento().getNome().equalsIgnoreCase(nome)) {
                return atual.getElemento();
            }
            atual = atual.getProximo();
        }
        return null;
    }

    public int getTamanho() {
        return tamanho;
    }
}
