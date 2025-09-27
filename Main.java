public class Main {
    public static void main(String[] args) {
        AgendaLigas agenda = new AgendaLigas();

        System.out.println("=== Cadastro de Participantes ===");
        agenda.cadastrarParticipante(1, "Ana");
        agenda.cadastrarParticipante(2, "Bruno");
        agenda.cadastrarParticipante(3, "Carlos");

        System.out.println("\n=== Cadastro de Atividades ===");
        agenda.insereAtividade("Palestra IA", "Discussão sobre Inteligência Artificial");
        agenda.insereAtividade("Oficina Java", "Programação básica em Java");
        agenda.insereAtividade("Workshop de Redes", "Conceitos avançados de redes");

        System.out.println("\n=== Inserção de Participantes nas Atividades ===");
        boolean inseriu;

        inseriu = agenda.insereParticipante("Palestra IA", 1); // Ana
        System.out.println("Inserção de Ana na Palestra IA: " + (inseriu ? "Sucesso" : "Falha"));

        inseriu = agenda.insereParticipante("Oficina Java", 2); // Bruno
        System.out.println("Inserção de Bruno na Oficina Java: " + (inseriu ? "Sucesso" : "Falha"));

        inseriu = agenda.insereParticipante("Oficina Java", 1); // Ana tenta em outra atividade
        System.out.println("Inserção de Ana na Oficina Java: " + (inseriu ? "Sucesso" : "Falha"));

        inseriu = agenda.insereParticipante("Workshop de Redes", 3); // Carlos
        System.out.println("Inserção de Carlos no Workshop de Redes: " + (inseriu ? "Sucesso" : "Falha"));

        System.out.println("\n=== Consulta todas as atividades ===");
        agenda.consultaTodasAtividades();

        System.out.println("\n=== Consulta atividade: Palestra IA ===");
        agenda.consultaAtividade("Palestra IA");

        System.out.println("\n=== Consulta participantes da Oficina Java ===");
        agenda.consultaParticipantes("Oficina Java");

        System.out.println("\n=== Consulta quantidades de participantes por atividade ===");
        agenda.consultaQuantidades();

        System.out.println("\n=== Remover atividade 'Workshop de Redes' ===");
        boolean removeu = agenda.removeAtividade("Workshop de Redes");
        System.out.println("Remoção: " + (removeu ? "Sucesso" : "Falha"));

        System.out.println("\n=== Lista de atividades após remoção ===");
        agenda.consultaTodasAtividades();
    }
}
