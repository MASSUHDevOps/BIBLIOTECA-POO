package services;

import models.Livro;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class EmprestimoService {
    private Map<String, LocalDate> emprestimos;

    public EmprestimoService() {
        this.emprestimos = new HashMap<>();
    }

    public boolean emprestarLivro(Livro livro) {
        if (!livro.isDisponivel()) {
            System.out.println("Livro não está disponível para empréstimo.");
            return false;
        }

        livro.setDisponivel(false);
        emprestimos.put(livro.getIsbn(), LocalDate.now());
        return true;
    }

    public boolean devolverLivro(Livro livro) {
        if (livro.isDisponivel()) {
            System.out.println("Este livro não está emprestado.");
            return false;
        }

        livro.setDisponivel(true);
        emprestimos.remove(livro.getIsbn());
        return true;
    }

    public LocalDate consultarDataEmprestimo(String isbn) {
        return emprestimos.get(isbn);
    }
} 