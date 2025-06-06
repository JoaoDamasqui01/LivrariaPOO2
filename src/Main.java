import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Livros livros = new Livros();
    static LivroDAO dao = new LivroDAO();
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {


        /*LivrariaForm frame = new LivrariaForm();
        frame.setVisible(true);*/

        int opcao = 0;
        do {
            System.out.println("\n===== MENU DE GERENCIAMENTO =====");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Listar Livros");
            System.out.println("3. Atualizar Livros");
            System.out.println("4. Excluir Livro");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = input.nextInt();
            input.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    inserirLivro(input);
                    break;

                case 2:
                    listaTodosLivro();
                    break;

                case 3:
                    System.out.println("Numero do id:");
                    int idLivro = input.nextInt();
                    alterarDados(input, idLivro);
                    /*System.out.print("ID do produto a atualizar: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine(); // Limpar buffer
                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Novo preço: ");
                    double novoPreco = scanner.nextDouble();
                    Produto atualizado = new Produto(novoNome, novoPreco);
                    atualizado.setId(idAtualizar);
                    dao.atualizar(atualizado);
                    break;
                case 4:
                     excluirLivro();
                     break;
                case 5:
                       ListagemFiltrada();
                     */
                    /*System.out.print("ID do produto a excluir: ");
                    int idExcluir = scanner.nextInt();
                    dao.excluir(idExcluir);
                    break;

                case 0:
                   /*System.out.println("Saindo...");
                    break;*/

                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
        input.close();
    }
    public static void inserirLivro(Scanner input) {
        System.out.println("\n--- Inserir Novo Livro ---");

        System.out.print("Título: ");
        String titulo = input.nextLine();

        System.out.print("Autores: ");
        String autores = input.nextLine();

        System.out.print("Tradutores: ");
        String tradutores = input.nextLine();

        System.out.print("Edição: ");
        String edicao = input.nextLine();

        System.out.print("Editora: ");
        String editora = input.nextLine();

        System.out.print("Local de Publicação: ");
        String localPublicacao = input.nextLine();

        // Tratamento da data
        Date dataPublicacaoSql = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Formato esperado
        boolean dataValida = false;
        Date dataParse = null;
        while (!dataValida) {
            System.out.print("Data de Publicação (dd/MM/yyyy): ");
            String dataStr = input.nextLine();
            try {
                Date parsedUtilDate = sdf.parse(dataStr); // Primeiro parseia para java.util.Date
                // AQUI É A CONVERSÃO: Crie um novo java.sql.Date a partir do java.util.Date
                dataPublicacaoSql = new java.sql.Date(parsedUtilDate.getTime());
                dataValida = true;
            } catch (ParseException e) {
                System.out.println("Formato de data inválido. Use dd/MM/yyyy.");
            }
        }

        int numeroPaginas = 0;
        boolean paginasValidas = false;
        while (!paginasValidas) {
            System.out.print("Número de Páginas: ");
            try {
                numeroPaginas = Integer.parseInt(input.nextLine());
                paginasValidas = true;
            } catch (NumberFormatException e) {
                System.out.println("Número de páginas inválido. Digite um valor numérico.");
            }
        }

        System.out.print("ISBN: ");
        String isbn = input.nextLine();

        System.out.println("--- Tabela de Status ---");
        System.out.println("------------------------");
        System.out.println("| 1 - Baixa            |");
        System.out.println("| 2 - Manutenção       |");
        System.out.println("| 3- Ativo             |");
        System.out.println("------------------------");
        System.out.println("Digá o status: ");
        int status = input.nextInt();

        switch (status) {
            case 1:
                String tipoStatus = "Baixa";
                break;
            case 2:
                tipoStatus = "Manutenção";
                break;
            case 3:
                tipoStatus = "Ativo";
                break;
        }

        dao.inserir(new Livros(titulo, autores, tradutores, edicao, editora, localPublicacao, dataPublicacaoSql, numeroPaginas, isbn, status));
    }

    public static void alterarDados(Scanner input, int idLivro){
        Livros livroParaAlterar = dao.buscarPorId(idLivro);;

        if (livroParaAlterar.getIdLivro() == idLivro) { // Verifica se o livro foi encontrado
            input.nextLine();
            System.out.print("Título: ");
            String Titulo = input.nextLine();


            System.out.print("Autores: ");
            String autores = input.nextLine();

            System.out.print("Tradutores: ");
            String tradutores = input.nextLine();

            System.out.print("Edição: ");
            String edicao = input.nextLine();

            System.out.print("Editora: ");
            String editora = input.nextLine();

            System.out.print("Local de Publicação: ");
            String localPublicacao = input.nextLine();

            // Tratamento da data
            Date dataPublicacaoSql = null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Formato esperado
            boolean dataValida = false;
            Date dataParse = null;
            while (!dataValida) {
                System.out.print("Data de Publicação (dd/MM/yyyy): ");
                String dataStr = input.nextLine();
                try {
                    Date parsedUtilDate = sdf.parse(dataStr); // Primeiro parseia para java.util.Date
                    // AQUI É A CONVERSÃO: Crie um novo java.sql.Date a partir do java.util.Date
                    dataPublicacaoSql = new java.sql.Date(parsedUtilDate.getTime());
                    dataValida = true;
                } catch (ParseException e) {
                    System.out.println("Formato de data inválido. Use dd/MM/yyyy.");
                }
            }

            int numeroPaginas = 0;
            boolean paginasValidas = false;
            while (!paginasValidas) {
                System.out.print("Número de Páginas: ");
                try {
                    numeroPaginas = Integer.parseInt(input.nextLine());
                    paginasValidas = true;
                } catch (NumberFormatException e) {
                    System.out.println("Número de páginas inválido. Digite um valor numérico.");
                }
            }

            System.out.print("ISBN: ");
            String isbn = input.nextLine();

            System.out.println("--- Tabela de Status ---");
            System.out.println("------------------------");
            System.out.println("| 1 - Baixa            |");
            System.out.println("| 2 - Manutenção       |");
            System.out.println("| 3- Ativo             |");
            System.out.println("------------------------");
            System.out.println("Digá o status: ");
            int status = input.nextInt();

            switch (status) {
                case 1:
                    String tipoStatus = "Baixa";
                    break;
                case 2:
                    tipoStatus = "Manutenção";
                    break;
                case 3:
                    tipoStatus = "Ativo";
                    break;
            }

            dao.atualizar(new Livros(Titulo, autores, tradutores, edicao, editora, localPublicacao, dataPublicacaoSql, numeroPaginas, isbn, status));
        } else{
            System.out.println("Livro com ID " + idLivro + " não encontrado.");
            return; // Sai da função se o livro não existir
        }

    };

    public static void listaTodosLivro() {
        System.out.println("\n--- Detalhes Completos de Todos os Livros ---");

        List<Livros> todosOsLivros = dao.listarTodos(); // Obtém a lista de livros do seu DAO

        if (todosOsLivros.isEmpty()) {
            System.out.println("Nenhum livro encontrado no banco de dados.");
        } else {
            // Formatador de data para exibição
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (Livros livro : todosOsLivros) {
                System.out.println("------------------------------------------");
                System.out.println("ID Livro: " + livro.getIdLivro());
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autores: " + livro.getAutores());
                System.out.println("Tradutores: " + livro.getTradutores());
                System.out.println("Edição: " + livro.getEdicao());
                System.out.println("Editora: " + livro.getEditora());
                System.out.println("Local de Publicação: " + livro.getLocal_publicacao());

                // Formata a data para uma String legível, verificando se não é nula
                Date dataPublicacao = livro.getData_publicacao();
                String dataFormatada = (dataPublicacao != null) ? sdf.format(dataPublicacao) : "Não informada";
                System.out.println("Data de Publicação: " + dataFormatada);

                System.out.println("Número de Páginas: " + livro.getNumero_paginas());
                System.out.println("ISBN: " + livro.getIsbn());
                // Mapeia o status numérico para uma descrição amigável
                System.out.println("Status: " + livro.getStatus());
            }
            System.out.println("------------------------------------------");
        }
        System.out.println("--- Fim da Listagem Detalhada ---");

    }
}

    /*Adiconar o JDBC que vem*/



