public class AgendaLigas {

    // Lista ligada de atividades
    private Nodo<Atividade> atividades;
    private int quantidadeAtividades;

    // Lista ligada de participantes cadastrados (para controle)
    private Nodo<Participante> participantesCadastrados;
    private int quantidadeParticipantes;

    public AgendaLigas() {
        atividades = null;
        quantidadeAtividades = 0;
        participantesCadastrados = null;
        quantidadeParticipantes = 0;
    }

    // Cadastrar participante no sistema (antes de inserir em atividade)
    public boolean cadastrarParticipante(int codigo, String nome) {
        if (buscarParticipantePorCodigo(codigo) != null) {
            return false; // Já existe participante com esse código
        }
        Participante p = new Participante(codigo, nome);
        Nodo<Participante> novo = new Nodo<>(p);
        novo.setProximo(participantesCadastrados);
        participantesCadastrados = novo;
        quantidadeParticipantes++;
        return true;
    }

    // Insere uma nova atividade na agenda (no início da lista)
    public void insereAtividade(String nome, String descricao) {
        Atividade nova = new Atividade(nome, descricao);
        Nodo<Atividade> novoNodo = new Nodo<>(nova);
        novoNodo.setProximo(atividades);
        atividades = novoNodo;
        quantidadeAtividades++;
    }

    // Remove atividade pelo nome (primeira ocorrência)
    public boolean removeAtividade(String nomeAtividade) {
        Nodo<Atividade> atual = atividades;
        Nodo<Atividade> anterior = null;

        while (atual != null) {
            if (atual.getElemento().getNome().equalsIgnoreCase(nomeAtividade)) {
                if (anterior == null) {
                    atividades = atual.getProximo();
                } else {
                    anterior.setProximo(atual.getProximo());
                }
                quantidadeAtividades--;
                return true;
            }
            anterior = atual;
            atual = atual.getProximo();
        }
        return false; // Não encontrou a atividade
    }

    // Insere participante em atividade (se estiver cadastrado e não inscrito em outra)
    public boolean insereParticipante(String nomeAtividade, int codigoParticipante) {
        Participante p = buscarParticipantePorCodigo(codigoParticipante);
        if (p == null) {
            System.out.println("Participante não cadastrado.");
            return false;
        }

        if (participanteInscritoEmAlgumaAtividade(p)) {
            System.out.println("Participante já está inscrito em outra atividade.");
            return false;
        }

        Atividade atividade = buscarAtividadePorNome(nomeAtividade);
        if (atividade == null) {
            System.out.println("Atividade não encontrada.");
            return false;
        }

        return atividade.adicionarParticipante(p);
    }

    // Consulta uma atividade pelo nome e exibe suas informações
    public void consultaAtividade(String nomeAtividade) {
        Atividade a = buscarAtividadePorNome(nomeAtividade);
        if (a == null) {
            System.out.println("Atividade não encontrada.");
            return;
        }
        System.out.println(a);
        System.out.println("Participantes:");
        a.exibeParticipantes();
    }

    // Consulta quantidades de participantes por atividade
    public void consultaQuantidades() {
        Nodo<Atividade> atual = atividades;
        while (atual != null) {
            System.out.println("Atividade: " + atual.getElemento().getNome()
                    + " | Participantes: " + atual.getElemento().getQuantidadeParticipantes());
            atual = atual.getProximo();
        }
    }

    // Lista todas as atividades da agenda
    public void consultaTodasAtividades() {
        if (atividades == null) {
            System.out.println("Nenhuma atividade cadastrada.");
            return;
        }
        Nodo<Atividade> atual = atividades;
        System.out.println("Atividades cadastradas:");
        while (atual != null) {
            System.out.println("- " + atual.getElemento().getNome());
            atual = atual.getProximo();
        }
    }

    // Consulta participantes de uma atividade específica
    public void consultaParticipantes(String nomeAtividade) {
        Atividade a = buscarAtividadePorNome(nomeAtividade);
        if (a == null) {
            System.out.println("Atividade não encontrada.");
            return;
        }
        System.out.println("Participantes da atividade '" + a.getNome() + "':");
        a.exibeParticipantes();
    }

    // --- MÉTODOS AUXILIARES ---

    // Busca participante cadastrado pelo código
    private Participante buscarParticipantePorCodigo(int codigo) {
        Nodo<Participante> atual = participantesCadastrados;
        while (atual != null) {
            if (atual.getElemento().getCodigo() == codigo) {
                return atual.getElemento();
            }
            atual = atual.getProximo();
        }
        return null;
    }

    // Busca atividade pelo nome
    private Atividade buscarAtividadePorNome(String nome) {
        Nodo<Atividade> atual = atividades;
        while (atual != null) {
            if (atual.getElemento().getNome().equalsIgnoreCase(nome)) {
                return atual.getElemento();
            }
            atual = atual.getProximo();
        }
        return null;
    }

    // Verifica se participante está inscrito em alguma atividade
    private boolean participanteInscritoEmAlgumaAtividade(Participante p) {
        Nodo<Atividade> atual = atividades;
        while (atual != null) {
            if (atual.getElemento().participanteExiste(p)) {
                return true;
            }
            atual = atual.getProximo();
        }
        return false;
    }
}
