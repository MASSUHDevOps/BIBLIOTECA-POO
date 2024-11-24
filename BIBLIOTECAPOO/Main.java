import models.*;
import services.Biblioteca;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Biblioteca biblioteca = new Biblioteca();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarBiblioteca();
        
        while (true) {
            exibirMenuPrincipal();
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1 -> exibirCatalogo();
                case 2 -> buscarPorTitulo();
                case 3 -> buscarPorAutor();
                case 4 -> buscarPorISBN();
                case 5 -> realizarEmprestimo();
                case 6 -> realizarDevolucao();
                case 0 -> {
                    System.out.println("Encerrando o programa...");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n=== SISTEMA DE BIBLIOTECA ===");
        System.out.println("1. Exibir Catálogo");
        System.out.println("2. Buscar por Título");
        System.out.println("3. Buscar por Autor");
        System.out.println("4. Buscar por ISBN");
        System.out.println("5. Realizar Empréstimo");
        System.out.println("6. Realizar Devolução");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void exibirCatalogo() {
        List<Livro> livros = biblioteca.listarLivrosDisponiveis();
        if (livros.isEmpty()) {
            System.out.println("Não há livros disponíveis no momento.");
            return;
        }

        System.out.println("\n=== CATÁLOGO DA BIBLIOTECA ===");
        for (int i = 0; i < livros.size(); i++) {
            Livro livro = livros.get(i);
            System.out.printf("%d. %s - %s (%s)%n", 
                i + 1, 
                livro.getTitulo(), 
                livro.getAutor(),
                livro.isDisponivel() ? "Disponível" : "Emprestado"
            );
        }

        System.out.print("\nSelecione um livro para ver detalhes (0 para voltar): ");
        int escolha = lerOpcao();
        
        if (escolha > 0 && escolha <= livros.size()) {
            System.out.println("\n=== DETALHES DO LIVRO ===");
            System.out.println(livros.get(escolha - 1));
        }
    }

    private static void buscarPorTitulo() {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();
        List<Livro> resultados = biblioteca.buscarPorTitulo(titulo);
        exibirResultadosBusca(resultados);
    }

    private static void buscarPorAutor() {
        System.out.print("Digite o nome do autor: ");
        String autor = scanner.nextLine();
        List<Livro> resultados = biblioteca.buscarPorAutor(autor);
        exibirResultadosBusca(resultados);
    }

    private static void buscarPorISBN() {
        System.out.print("Digite o ISBN: ");
        String isbn = scanner.nextLine();
        Livro livro = biblioteca.buscarPorISBN(isbn);
        if (livro != null) {
            System.out.println("\n=== LIVRO ENCONTRADO ===");
            System.out.println(livro);
        } else {
            System.out.println("Livro não encontrado!");
        }
    }

    private static void realizarEmprestimo() {
        System.out.print("Digite o ISBN do livro para empréstimo: ");
        String isbn = scanner.nextLine();
        if (biblioteca.emprestarLivro(isbn)) {
            System.out.println("Empréstimo realizado com sucesso!");
        }
    }

    private static void realizarDevolucao() {
        System.out.print("Digite o ISBN do livro para devolução: ");
        String isbn = scanner.nextLine();
        if (biblioteca.devolverLivro(isbn)) {
            System.out.println("Devolução realizada com sucesso!");
        }
    }

    private static void exibirResultadosBusca(List<Livro> livros) {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado!");
            return;
        }

        System.out.println("\n=== RESULTADOS DA BUSCA ===");
        for (int i = 0; i < livros.size(); i++) {
            System.out.printf("%d. %s - %s%n", 
                i + 1, 
                livros.get(i).getTitulo(), 
                livros.get(i).getAutor()
            );
        }

        System.out.print("\nSelecione um livro para ver detalhes (0 para voltar): ");
        int escolha = lerOpcao();
        
        if (escolha > 0 && escolha <= livros.size()) {
            System.out.println("\n=== DETALHES DO LIVRO ===");
            System.out.println(livros.get(escolha - 1));
        }
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void inicializarBiblioteca() {
        // Adicionando alguns livros de exemplo
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

        LivroFiccao livro3 = new LivroFiccao(
            "9788535902778", "1984", "George Orwell", 1949,
            "Companhia das Letras", 416, "Ficção Científica",
            "Distopia", null, 0
        );

        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
        biblioteca.adicionarLivro(livro3);
    }
} 