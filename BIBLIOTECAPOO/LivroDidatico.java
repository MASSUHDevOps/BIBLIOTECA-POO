package models;

public class LivroDidatico extends Livro {
    private String disciplina;
    private String nivelEnsino;
    private String edicao;
    private boolean possuiExercicios;

    public LivroDidatico(String isbn, String titulo, String autor, int anoPublicacao,
                        String editora, int numeroPaginas, String disciplina,
                        String nivelEnsino, String edicao, boolean possuiExercicios) {
        super(isbn, titulo, autor, anoPublicacao, editora, numeroPaginas);
        this.disciplina = disciplina;
        this.nivelEnsino = nivelEnsino;
        this.edicao = edicao;
        this.possuiExercicios = possuiExercicios;
    }

 
    public String getDisciplina() { return disciplina; }
    public String getNivelEnsino() { return nivelEnsino; }
    public String getEdicao() { return edicao; }
    public boolean isPossuiExercicios() { return possuiExercicios; }

    @Override
    public String toString() {
        return String.format("""
            %s
            Disciplina: %s
            Nível de Ensino: %s
            Edição: %s
            Possui Exercícios: %s""",
            super.toString(), disciplina, nivelEnsino, edicao,
            possuiExercicios ? "Sim" : "Não");
    }
} 
