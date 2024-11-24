package models;

public class LivroFiccao extends Livro {
    private String genero;
    private String subgenero;
    private String serieOuSaga;
    private int volumeSerie;

    public LivroFiccao(String isbn, String titulo, String autor, int anoPublicacao,
                       String editora, int numeroPaginas, String genero, 
                       String subgenero, String serieOuSaga, int volumeSerie) {
        super(isbn, titulo, autor, anoPublicacao, editora, numeroPaginas);
        this.genero = genero;
        this.subgenero = subgenero;
        this.serieOuSaga = serieOuSaga;
        this.volumeSerie = volumeSerie;
    }

    // Getters
    public String getGenero() { return genero; }
    public String getSubgenero() { return subgenero; }
    public String getSerieOuSaga() { return serieOuSaga; }
    public int getVolumeSerie() { return volumeSerie; }

    @Override
    public String toString() {
        return String.format("""
            %s
            Gênero: %s
            Subgênero: %s
            Série/Saga: %s
            Volume: %d""",
            super.toString(), genero, subgenero,
            serieOuSaga != null ? serieOuSaga : "N/A",
            volumeSerie);
    }
} 