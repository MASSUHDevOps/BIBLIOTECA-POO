package models;

import java.time.LocalDate;

public abstract class Livro {
    private String isbn;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private String editora;
    private int numeroPaginas;
    private boolean disponivel;
    private LocalDate dataUltimoEmprestimo;

    public Livro(String isbn, String titulo, String autor, int anoPublicacao, 
                 String editora, int numeroPaginas) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.editora = editora;
        this.numeroPaginas = numeroPaginas;
        this.disponivel = true;
    }

    // Getters e Setters
    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnoPublicacao() { return anoPublicacao; }
    public String getEditora() { return editora; }
    public int getNumeroPaginas() { return numeroPaginas; }
    public boolean isDisponivel() { return disponivel; }
    public LocalDate getDataUltimoEmprestimo() { return dataUltimoEmprestimo; }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
        if (!disponivel) {
            this.dataUltimoEmprestimo = LocalDate.now();
        }
    }

    @Override
    public String toString() {
        return String.format("""
            ISBN: %s
            Título: %s
            Autor: %s
            Ano: %d
            Editora: %s
            Páginas: %d
            Status: %s""",
            isbn, titulo, autor, anoPublicacao, editora, numeroPaginas,
            disponivel ? "Disponível" : "Emprestado");
    }
} 