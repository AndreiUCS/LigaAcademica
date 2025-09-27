import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaAlunos cadastro = new ListaAlunos();
        AgendaLigas agenda = new AgendaLigas(cadastro);

        int opcao;
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Remover aluno (por código)");
            System.out.println("3 - Remover aluno (por nome)");
            System.out.println("4 - Exibir alunos cadastrados");
            System.out.println("5 - Cadastrar atividade");
            System.out.println("6 - Remover atividade");
            System.out.println("7 - Exibir todas atividades");
            System.out.println("8 - Exibir informações de uma atividade");
            System.out.println("9 - Exibir participantes de uma atividade");
            System.out.println("10 - Exibir quantidade de participantes por atividade");
            System.out.println("11 - Inscrever aluno em atividade");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Código do aluno: ");
                    int codigo = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nome do aluno: ");
                    String nome = sc.nextLine();
                    System.out.print("Curso do aluno: ");
                    String curso = sc.nextLine();
                    cadastro.insereAlunoOrdenado(codigo, nome, curso);
                    System.out.println("Aluno cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.print("Digite o código do aluno a remover: ");
                    int codRemover = sc.nextInt();
                    if (cadastro.removeAlunoPorCodigo(codRemover)) {
                        agenda.removerAlunoDeTodasAtividades(codRemover);
                        System.out.println("Aluno removido do cadastro e de todas as atividades.");
                    } else {
                        System.out.println("Aluno não encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Digite o nome do aluno a remover: ");
                    String nomeRemover = sc.nextLine();
                    Aluno aluno = cadastro.buscarPorNome(nomeRemover);
                    if (aluno != null) {
                        int cod = aluno.getCodigo();
                        cadastro.removeAlunoPorNome(nomeRemover);
                        agenda.removerAlunoDeTodasAtividades(cod);
                        System.out.println("Aluno removido do cadastro e de todas as atividades.");
                    } else {
                        System.out.println("Aluno não encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("=== Alunos cadastrados ===");
                    cadastro.exibeListaEstudantes();
                    break;

                case 5:
                    System.out.print("Nome da liga: ");
                    String nomeLiga = sc.nextLine();
                    System.out.print("Local (endereço ou link): ");
                    String local = sc.nextLine();
                    agenda.insereAtividade(nomeLiga, local);
                    System.out.println("Atividade cadastrada!");
                    break;

                case 6:
                    System.out.print("Digite o nome da atividade a remover: ");
                    String atividadeRemover = sc.nextLine();
                    if (agenda.removeAtividade(atividadeRemover)) {
                        System.out.println("Atividade removida com sucesso.");
                    } else {
                        System.out.println("Atividade não encontrada.");
                    }
                    break;

                case 7:
                    agenda.consultaTodasAtividades();
                    break;

                case 8:
                    System.out.print("Digite o nome da atividade: ");
                    String nomeConsulta = sc.nextLine();
                    agenda.consultaAtividade(nomeConsulta);
                    break;

                case 9:
                    System.out.print("Digite o nome da atividade: ");
                    String nomeAtiv = sc.nextLine();
                    agenda.consultaParticipantes(nomeAtiv);
                    break;

                case 10:
                    agenda.consultaQuantidades();
                    break;

                case 11:
                    System.out.print("Digite o código do aluno: ");
                    int codInscricao = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Digite o nome da atividade: ");
                    String nomeAtividade = sc.nextLine();
                    if (agenda.insereParticipante(nomeAtividade, codInscricao)) {
                        System.out.println("Aluno inscrito com sucesso!");
                    } else {
                        System.out.println("Não foi possível inscrever o aluno.");
                    }
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        sc.close();
    }
}
