import java.util.Scanner;

public class Menu {
    private Scanner sc;
    private ListaAlunos cadastro;
    private AgendaLigas agenda;

    public Menu() {
        sc = new Scanner(System.in);
        cadastro = new ListaAlunos();
        agenda = new AgendaLigas(cadastro);
    }

    public void iniciar() {
        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> cadastrarAluno();
                case 2 -> removerAlunoPorCodigo();
                case 3 -> removerAlunoPorNome();
                case 4 -> cadastro.exibeListaEstudantes();
                case 5 -> cadastrarAtividade();
                case 6 -> removerAtividade();
                case 7 -> agenda.consultaTodasAtividades();
                case 8 -> consultarAtividade();
                case 9 -> consultarParticipantes();
                case 10 -> agenda.consultaQuantidades();
                case 11 -> inscreverAluno();
                case 12 -> agenda.relatorioMaiorMenorAtividade();
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        sc.close();
    }

    private void exibirMenuPrincipal() {
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
        System.out.println("12 - Relatório de maior e menor atividade");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    private void cadastrarAluno() {
        System.out.print("\nCódigo do aluno: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        System.out.print("\nNome do aluno: ");
        String nome = sc.nextLine();

        // Exibir opções de cursos
        System.out.println("\nEscolha o curso:");
        System.out.println("1 - Engenharia de Software");
        System.out.println("2 - Engenharia da Computação");
        System.out.println("3 - Análise e Desenvolvimento de Sistemas");
        System.out.println("4 - Ciência da Computação");
        System.out.print("Opção: ");
        int opcCurso = sc.nextInt();
        sc.nextLine();

        String curso;
        switch (opcCurso) {
            case 1 -> curso = "Engenharia de Software";
            case 2 -> curso = "Engenharia da Computação";
            case 3 -> curso = "Análise e Desenvolvimento de Sistemas";
            case 4 -> curso = "Ciência da Computação";
            default -> {
                System.out.println("Opção inválida. Curso definido como 'Não informado'.");
                curso = "Não informado";
            }
        }

        cadastro.insereAlunoOrdenado(codigo, nome, curso);
        System.out.println("\nAluno cadastrado com sucesso!");
    }

    private void removerAlunoPorCodigo() {
        cadastro.exibeListaEstudantes();
        if (cadastro.getTamanho() == 0) return;

        System.out.print("\nDigite o código do aluno a remover: ");
        int codRemover = sc.nextInt();
        if (cadastro.removeAlunoPorCodigo(codRemover)) {
            agenda.removerAlunoDeTodasAtividades(codRemover);
            System.out.println("Aluno removido do cadastro e de todas as atividades.");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    private void removerAlunoPorNome() {
        cadastro.exibeListaEstudantes();
        if (cadastro.getTamanho() == 0) return;

        System.out.print("\nDigite o nome do aluno a remover: ");
        String nomeRemover = sc.nextLine();
        Aluno aluno = cadastro.buscarPorNome(nomeRemover);
        if (aluno != null) {
            cadastro.removeAlunoPorNome(nomeRemover);
            agenda.removerAlunoDeTodasAtividades(aluno.getCodigo());
            System.out.println("Aluno removido com sucesso.");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    private void cadastrarAtividade() {
        System.out.print("Nome da liga: ");
        String nomeLiga = sc.nextLine();
        System.out.print("Local (endereço ou link): ");
        String local = sc.nextLine();
        agenda.insereAtividade(nomeLiga, local);
        System.out.println("Atividade cadastrada!");
    }

    private void removerAtividade() {
        agenda.consultaTodasAtividades();
        System.out.print("Digite o nome da atividade a remover: ");
        String atividadeRemover = sc.nextLine();
        if (agenda.removeAtividade(atividadeRemover)) {
            System.out.println("Atividade removida com sucesso.");
        } else {
            System.out.println("Atividade não encontrada.");
        }
    }

    private void consultarAtividade() {
        agenda.consultaTodasAtividades();
        System.out.print("Digite o nome da atividade: ");
        String nomeConsulta = sc.nextLine();
        agenda.consultaAtividade(nomeConsulta);
    }

    private void consultarParticipantes() {
        agenda.consultaTodasAtividades();
        System.out.print("Digite o nome da atividade: ");
        String nomeAtiv = sc.nextLine();
        agenda.consultaParticipantes(nomeAtiv);
    }

    private void inscreverAluno() {
        cadastro.exibeListaEstudantes();
        if (cadastro.getTamanho() == 0) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        System.out.print("Digite o código do aluno: ");
        int codInscricao = sc.nextInt();
        sc.nextLine();

        agenda.consultaTodasAtividades();
        System.out.print("Digite o nome da atividade: ");
        String nomeAtividade = sc.nextLine();

        if (agenda.insereParticipante(nomeAtividade, codInscricao)) {
            System.out.println("Aluno inscrito com sucesso!");
        } else {
            System.out.println("Não foi possível inscrever o aluno.");
        }
    }
}
