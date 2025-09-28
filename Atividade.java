public class Atividade {

    private String nomeLiga;
    private String local; // endereço ou link
    private Nodo<Aluno> participantesHeader;
    private int quantidadeParticipantes;

    public Atividade(String nomeLiga, String local) {
        this.nomeLiga = nomeLiga;
        this.local = local;
        this.participantesHeader = new Nodo<>(null);
        this.quantidadeParticipantes = 0;
    }

    public String getNomeLiga() {
        return nomeLiga;
    }

    public String getLocal() {
        return local;
    }

    public int getQuantidadeParticipantes() {
        return quantidadeParticipantes;
    }

    public boolean adicionarParticipante(Aluno aluno) {
        if (participanteExiste(aluno.getCodigo())) {
            return false;
        }

        Nodo<Aluno> novo = new Nodo<>(aluno);
        novo.setProximo(participantesHeader.getProximo());
        participantesHeader.setProximo(novo);
        quantidadeParticipantes++;
        return true;
    }

    public void removerParticipantePorCodigo(int codigo) {
        Nodo<Aluno> ant = participantesHeader;
        Nodo<Aluno> atual = participantesHeader.getProximo();

        while (atual != null) {
            if (atual.getElemento().getCodigo() == codigo) {
                ant.setProximo(atual.getProximo());
                quantidadeParticipantes--;
                return;
            }
            ant = atual;
            atual = atual.getProximo();
        }
    }

    public boolean participanteExiste(int codigo) {
        Nodo<Aluno> atual = participantesHeader.getProximo();
        while (atual != null) {
            if (atual.getElemento().getCodigo() == codigo) {
                return true;
            }
            atual = atual.getProximo();
        }
        return false;
    }

    public void exibeParticipantes() {
        Nodo<Aluno> atual = participantesHeader.getProximo();
        if (atual == null) {
            System.out.println("Nenhum participante inscrito.");
            return;
        }
        while (atual != null) {
            System.out.println(" - " + atual.getElemento());
            atual = atual.getProximo();
        }
    }

    @Override
    public String toString() {
        return "Liga: " + nomeLiga + "\nLocal: " + local +
                "\nParticipantes inscritos: " + quantidadeParticipantes;
    }
}
