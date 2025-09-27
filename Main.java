public class Main {
    public static void main(String[] args) {
        ListaAlunos lista = new ListaAlunos();

        lista.insereAlunoOrdenado(2, "Carlos", "Engenharia");
        lista.insereAlunoOrdenado(1, "Ana", "Direito");
        lista.insereAlunoOrdenado(4, "Beatriz", "Medicina");
        lista.removeAlunoPorPosicao(3);
        

        lista.exibeListaEstudantes();
    }
}
