package services;

import models.Livro;
import utils.ValidadorISBN;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Biblioteca {
    private List<Livro> livros;
    private EmprestimoService emprestimoService;

    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.emprestimoService = new EmprestimoService();
    }

    public boolean adicionarLivro(Livro livro) {
        if (!ValidadorISBN.validarISBN13(livro.getIsbn())) {
            System.out.println("ISBN inválido!");
            return false;
        }

        if (buscarPorISBN(livro.getIsbn()) != null) {
            System.out.println("Livro já cadastrado!");
            return false;
        }

        livros.add(livro);
        System.out.println("Livro adicionado com sucesso!");
        return true;
    }

    public boolean removerLivro(String isbn) {
        return livros.removeIf(livro -> livro.getIsbn().equals(isbn));
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        return livros.stream()
            .filter(livro -> livro.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
            .collect(Collectors.toList());
    }

    public List<Livro> buscarPorAutor(String autor) {
        return livros.stream()
            .filter(livro -> livro.getAutor().toLowerCase().contains(autor.toLowerCase()))
            .collect(Collectors.toList());
    }

    public Livro buscarPorISBN(String isbn) {
        return livros.stream()
            .filter(livro -> livro.getIsbn().equals(isbn))
            .findFirst()
            .orElse(null);
    }

    public List<Livro> listarLivrosDisponiveis() {
        return livros.stream()
            .filter(Livro::isDisponivel)
            .collect(Collectors.toList());
    }

    public boolean emprestarLivro(String isbn) {
        Livro livro = buscarPorISBN(isbn);
        if (livro == null) {
            System.out.println("Livro não encontrado!");
            return false;
        }
        return emprestimoService.emprestarLivro(livro);
    }

    public boolean devolverLivro(String isbn) {
        Livro livro = buscarPorISBN(isbn);
        if (livro == null) {
            System.out.println("Livro não encontrado!");
            return false;
        }
        return emprestimoService.devolverLivro(livro);
    }

    public void listarTodosLivros() {
        if (livros.isEmpty()) {
            System.out.println("Biblioteca vazia!");
            return;
        }
        
        
        livros.forEach(livro -> {
            System.out.println("\n" + livro);
            System.out.println("----------------------------");
        });
    }
} 
