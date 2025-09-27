public class ListaAlunos {
    private Nodo<Aluno> inicio;
    private int tamanho;

    // Inserção ordenada por ordem alfabetica de nome
    public void insereAlunoOrdenado(int codigo, String nome, String curso) {
        Aluno aluno = new Aluno(codigo, nome, curso);
        Nodo<Aluno> novo = new Nodo<>(aluno);

        if (inicio == null || aluno.getNome().compareToIgnoreCase(inicio.getElemento().getNome()) <= 0) {
            novo.setProximo(inicio);
            inicio = novo;
        } else {
            Nodo<Aluno> atual = inicio;
            while (atual.getProximo() != null &&
                   aluno.getNome().compareToIgnoreCase(atual.getProximo().getElemento().getNome()) > 0) { 
                atual = atual.getProximo();
            }
            novo.setProximo(atual.getProximo()); 
            atual.setProximo(novo);
        }

        tamanho++;
    }

    // Remove aluno por nome
    public void removeAlunoPorNome(String nome) {
        while (inicio != null && inicio.getElemento().getNome().equalsIgnoreCase(nome)) {
            inicio = inicio.getProximo();
            tamanho--;
        }

        Nodo<Aluno> atual = inicio;
        while (atual != null && atual.getProximo() != null) {
            if (atual.getProximo().getElemento().getNome().equalsIgnoreCase(nome)) {
                atual.setProximo(atual.getProximo().getProximo());
                tamanho--;
            } else {
                atual = atual.getProximo();
            }
        }
    }

    // Remove aluno por posição
    public void removeAlunoPorPosicao(int posicao) {
        if (posicao < 1 || posicao > tamanho) {
            throw new IllegalArgumentException("Posição inválida");
        }
        if (posicao == 1) {
            inicio = inicio.getProximo();
        } else {
            Nodo<Aluno> atual = inicio;
            for (int i = 1; i < posicao - 1; i++) {
                atual = atual.getProximo();
            }
            atual.setProximo(atual.getProximo().getProximo());
        }
        tamanho--;
    }

    // Exibe os alunos da lista
    public void exibeListaEstudantes() {
        Nodo<Aluno> atual = inicio;
        while (atual != null) {
            System.out.println(atual.getElemento());
            atual = atual.getProximo();
        }
        System.out.println("FIM DA LISTA");
    }

    public int getTamanho() {
        return tamanho;
    }
}
