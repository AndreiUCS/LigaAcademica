public class Atividade {
    private String nome;
    private String descricao;

    // Lista ligada de participantes
    private Nodo<Participante> participantes;
    private int quantidadeParticipantes;

    public Atividade(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.participantes = null;
        this.quantidadeParticipantes = 0;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidadeParticipantes() {
        return quantidadeParticipantes;
    }

    // Insere participante no final da lista (sem repetição)
    public boolean adicionarParticipante(Participante p) {
        if (participanteExiste(p)) {
            return false; // Já está inscrito nesta atividade
        }

        Nodo<Participante> novo = new Nodo<>(p);

        if (participantes == null) {
            participantes = novo;
        } else {
            Nodo<Participante> atual = participantes;
            while (atual.getProximo() != null) {
                atual = atual.getProximo();
            }
            atual.setProximo(novo);
        }
        quantidadeParticipantes++;
        return true;
    }

    // Verifica se participante já está na lista desta atividade
    public boolean participanteExiste(Participante p) {
        Nodo<Participante> atual = participantes;
        while (atual != null) {
            if (atual.getElemento().getCodigo() == p.getCodigo()) {
                return true;
            }
            atual = atual.getProximo();
        }
        return false;
    }

    // Exibe os participantes
    public void exibeParticipantes() {
        Nodo<Participante> atual = participantes;
        while (atual != null) {
            System.out.println(" - " + atual.getElemento());
            atual = atual.getProximo();
        }
    }

    @Override
    public String toString() {
        return "Atividade: " + nome + "\nDescrição: " + descricao + "\nParticipantes: " + quantidadeParticipantes;
    }
}
