import models.*;
import services.Biblioteca;
import java.util.Scanner;
import java.util.List;

public class Main {
    private static Biblioteca biblioteca;
    private static Scanner scanner;

    public static void main(String[] args) {
        biblioteca = new Biblioteca();
        scanner = new Scanner(System.in);

        
        inicializarBiblioteca();

        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    listarTodosLivros();
                    break;
                case 2:
                    buscarLivroPorTitulo();
                    break;
                case 3:
                    buscarLivroPorAutor();
                    break;
                case 4:
                    emprestarLivro();
                    break;
                case 5:
                    devolverLivro();
                    break;
                case 6:
                    listarLivrosDisponiveis();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }

            System.out.println("\nPressione ENTER para continuar...");
            scanner.nextLine();
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE BIBLIOTECA ===");
        System.out.println("1. Listar todos os livros");
        System.out.println("2. Buscar livro por título");
        System.out.println("3. Buscar livro por autor");
        System.out.println("4. Emprestar livro");
        System.out.println("5. Devolver livro");
        System.out.println("6. Listar livros disponíveis");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void inicializarBiblioteca() {
        LivroFiccao livro1 = new LivroFiccao(
                "9788533615540", "O Senhor dos Anéis", "J.R.R. Tolkien", 1954,
                "Martins Fontes", 1200, "Fantasia", "Alta Fantasia",
                "O Senhor dos Anéis", 1
        );

        LivroDidatico livro2 = new LivroDidatico(
                "9788580550443", "Cálculo I", "James Stewart", 2015,
                "Cengage Learning", 680, "Matemática",
                "Superior", "8ª", true
        );

        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
    }

    private static void listarTodosLivros() {
        biblioteca.listarTodosLivros();
    }

    private static void buscarLivroPorTitulo() {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();
        List<Livro> livrosEncontrados = biblioteca.buscarPorTitulo(titulo);

        if (livrosEncontrados.isEmpty()) {
            System.out.println("Nenhum livro encontrado com este título.");
        } else {
            System.out.println("\nLivros encontrados:");
            livrosEncontrados.forEach(System.out::println);
        }
    }

    private static void buscarLivroPorAutor() {
        System.out.print("Digite o nome do autor: ");
        String autor = scanner.nextLine();
        List<Livro> livrosEncontrados = biblioteca.buscarPorAutor(autor);

        if (livrosEncontrados.isEmpty()) {
            System.out.println("Nenhum livro encontrado deste autor.");
        } else {
            System.out.println("\nLivros encontrados:");
            livrosEncontrados.forEach(System.out::println);
        }
    }

    private static void emprestarLivro() {
        System.out.print("Digite o ISBN do livro: ");
        String isbn = scanner.nextLine();
        if (biblioteca.emprestarLivro(isbn)) {
            System.out.println("Livro emprestado com sucesso!");
        }
    }

    private static void devolverLivro() {
        System.out.print("Digite o ISBN do livro: ");
        String isbn = scanner.nextLine();
        if (biblioteca.devolverLivro(isbn)) {
            System.out.println("Livro devolvido com sucesso!");
        }
    }

    private static void listarLivrosDisponiveis() {
        List<Livro> livrosDisponiveis = biblioteca.listarLivrosDisponiveis();
        if (livrosDisponiveis.isEmpty()) {
            System.out.println("Não há livros disponíveis no momento.");
        } else {
            System.out.println("\n=== LIVROS DISPONÍVEIS ===");
            livrosDisponiveis.forEach(livro -> {
                System.out.println("\n" + livro);
                System.out.println("----------------------------");
            });
        }
    }
} 
