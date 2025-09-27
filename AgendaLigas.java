public class AgendaLigas {
    private Nodo<Atividade> headerAtividades;
    private int quantidadeAtividades;
    private ListaAlunos cadastro;

    public AgendaLigas(ListaAlunos cadastro) {
        this.cadastro = cadastro;
        headerAtividades = new Nodo<>(null); // header de atividades
        quantidadeAtividades = 0;
    }

    public void insereAtividade(String nomeLiga, String local) {
        Atividade nova = new Atividade(nomeLiga, local);
        Nodo<Atividade> novoNodo = new Nodo<>(nova);
        novoNodo.setProximo(headerAtividades.getProximo());
        headerAtividades.setProximo(novoNodo);
        quantidadeAtividades++;
    }

    public boolean removeAtividade(String nomeLiga) {
        Nodo<Atividade> ant = headerAtividades;
        Nodo<Atividade> atual = headerAtividades.getProximo();

        while (atual != null) {
            if (atual.getElemento().getNomeLiga().equalsIgnoreCase(nomeLiga)) {
                ant.setProximo(atual.getProximo());
                quantidadeAtividades--;
                return true;
            }
            ant = atual;
            atual = atual.getProximo();
        }
        return false;
    }

    public boolean insereParticipante(String nomeLiga, int codigoAluno) {
        Aluno aluno = cadastro.buscarPorCodigo(codigoAluno);
        if (aluno == null) {
            System.out.println("Aluno não cadastrado.");
            return false;
        }

        if (participanteInscritoEmAlgumaAtividade(codigoAluno)) {
            System.out.println("Aluno já está inscrito em outra atividade.");
            return false;
        }

        Atividade atividade = buscarAtividadePorNome(nomeLiga);
        if (atividade == null) {
            System.out.println("Atividade não encontrada.");
            return false;
        }

        return atividade.adicionarParticipante(aluno);
    }

    public void consultaAtividade(String nomeLiga) {
        Atividade a = buscarAtividadePorNome(nomeLiga);
        if (a == null) {
            System.out.println("Atividade não encontrada.");
            return;
        }
        System.out.println(a);
        System.out.println("Participantes:");
        a.exibeParticipantes();
    }

    public void consultaQuantidades() {
        Nodo<Atividade> atual = headerAtividades.getProximo();
        while (atual != null) {
            System.out.println("Atividade: " + atual.getElemento().getNomeLiga() +
                    " | Participantes: " + atual.getElemento().getQuantidadeParticipantes());
            atual = atual.getProximo();
        }
    }

    public void consultaTodasAtividades() {
        Nodo<Atividade> atual = headerAtividades.getProximo();
        if (atual == null) {
            System.out.println("Nenhuma atividade cadastrada.");
            return;
        }
        System.out.println("Atividades cadastradas:");
        while (atual != null) {
            System.out.println("- " + atual.getElemento().getNomeLiga());
            atual = atual.getProximo();
        }
    }

    public void consultaParticipantes(String nomeLiga) {
        Atividade a = buscarAtividadePorNome(nomeLiga);
        if (a == null) {
            System.out.println("Atividade não encontrada.");
            return;
        }
        System.out.println("Participantes da atividade '" + a.getNomeLiga() + "':");
        a.exibeParticipantes();
    }

    private Atividade buscarAtividadePorNome(String nome) {
        Nodo<Atividade> atual = headerAtividades.getProximo();
        while (atual != null) {
            if (atual.getElemento().getNomeLiga().equalsIgnoreCase(nome)) {
                return atual.getElemento();
            }
            atual = atual.getProximo();
        }
        return null;
    }

    private boolean participanteInscritoEmAlgumaAtividade(int codigoAluno) {
        Nodo<Atividade> atual = headerAtividades.getProximo();
        while (atual != null) {
            if (atual.getElemento().participanteExiste(codigoAluno)) {
                return true;
            }
            atual = atual.getProximo();
        }
        return false;
    }

    // Remover aluno de todas as atividades (quando sair do cadastro)
    public void removerAlunoDeTodasAtividades(int codigoAluno) {
        Nodo<Atividade> atual = headerAtividades.getProximo();
        while (atual != null) {
            atual.getElemento().removerParticipantePorCodigo(codigoAluno);
            atual = atual.getProximo();
        }
    }
}
