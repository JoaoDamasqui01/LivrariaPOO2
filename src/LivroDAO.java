
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    private AbstractButton RadioBtn_Ativos;
    private AbstractButton RadioBtn_Baixos;
    private AbstractButton RadioBtn_Manutencao;

    public void inserir(Livros livros) {
        String sql = "INSERT INTO livros (titulo, autores, tradutores, edicao, editora, local_publicacao, data_publicacao, numero_paginas, isbn, status)" +
                "VALUE (?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livros.getTitulo());
            stmt.setString(2, livros.getAutores());
            stmt.setString(3, livros.getTradutores());
            stmt.setString(4, livros.getEdicao());
            stmt.setString(5, livros.getEditora());
            stmt.setString(6, livros.getLocal_publicacao());
            stmt.setDate(7, (Date) livros.getData_publicacao());
            stmt.setInt(8, livros.getNumero_paginas());
            stmt.setString(9, livros.getIsbn());
            stmt.setInt(10, (livros.getStatus())); // Assumindo que getStatus() retorna "1", "2" ou "3" como String
            stmt.executeUpdate();

            System.out.println("Novo livro cadastrado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Livros> listarTodos() {
        List<Livros> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";

        /*if (this.RadioBtn_Todos.isSelected()) {
            sql = "SELECT * FROM livros";
        } else if (this.RadioBtn_Baixos.isSelected()) {
            sql = "SELECT * FROM livros WHERE status = 1";
        } else if (this.RadioBtn_Manutencao.isSelected()) {
            sql = "SELECT * FROM livros WHERE status = 2";
        }else if(this.RadioBtn_Baixos.isSelected()){
            sql = "SELECT * FROM livros WHERE status = 3";
        }*/
        /*status TINYINT NOT NULL DEFAULT 3, -- 1: Baixa, 2: Manutenção, 3: Ativo
        CONSTRAINT ck_livro_status CHECK (status IN (1, 2, 3))*/

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Livros l = new Livros();
                l.setIdLivro(rs.getInt("IdLivro"));
                l.setTitulo(rs.getString("Titulo"));
                l.setAutores(rs.getString("Autores"));
                l.setTradutores(rs.getString("Tradutores"));
                l.setEdicao(rs.getString("Edicao"));
                l.setEditora(rs.getString("Editora"));
                l.setLocal_publicacao(rs.getString("local_publicacao"));
                l.setData_publicacao(rs.getDate("Data_publicacao"));
                l.setNumero_paginas(rs.getInt("Numero_paginas"));
                l.setIsbn(rs.getString("Isbn"));
                l.setStatus(rs.getInt("Status"));
                livros.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public List<Livros> listarStatus() {
        List<Livros> livros = new ArrayList<>();
        String sql = "";

        if (this.RadioBtn_Baixos.isSelected()) {
            sql = "SELECT * FROM livros WHERE status = 1";
        } else if (this.RadioBtn_Manutencao.isSelected()) {
            sql = "SELECT * FROM livros WHERE status = 2";
        } else if (this.RadioBtn_Ativos.isSelected()) {
            sql = "SELECT * FROM livros WHERE status = 3";
        }

        /*status TINYINT NOT NULL DEFAULT 3, -- 1: Baixa, 2: Manutenção, 3: Ativo
        CONSTRAINT ck_livro_status CHECK (status IN (1, 2, 3))*/

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int status = 0;
            stmt.setInt(1, status);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Livros l = new Livros();
                l.setIdLivro(rs.getInt("id"));
                l.setTitulo(rs.getString("titulo"));
                l.setAutores(rs.getString("autores"));
                l.setTradutores(rs.getString("tradutores"));
                l.setEdicao(rs.getString("edicao"));
                l.setEditora(rs.getString("editora"));
                l.setLocal_publicacao(rs.getString("local_publicacao"));
                l.setData_publicacao(rs.getDate("data_publicacao"));
                l.setNumero_paginas(rs.getInt("numero_paginas"));
                l.setIsbn(rs.getString("isbn"));
                l.setStatus(Integer.parseInt(String.valueOf(rs.getInt("status")))); // Converte TINYINT para String
                livros.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public void atualizar(Livros livros) {
        String sql = "UPDATE livros SET titulo = ? , autores = ? , tradutores = ? , edicao = ? , editora = ? , local_publicacao = ? , data_publicacao = ? , numero_paginas = ? , isbn = ? , status = ? ";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livros.getTitulo());
            stmt.setString(2, livros.getAutores());
            stmt.setString(3, livros.getTradutores());
            stmt.setString(4, livros.getEdicao());
            stmt.setString(5, livros.getEditora());
            stmt.setString(6, livros.getLocal_publicacao());
            stmt.setDate(7, (Date) livros.getData_publicacao());
            stmt.setInt(8, livros.getNumero_paginas());
            stmt.setString(9, livros.getIsbn());
            stmt.setInt(10, livros.getStatus());
            stmt.executeUpdate();

            System.out.println("Livro atualizado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void excluir(int idLivro) {
        String sql = "DELETE FROM livros WHERE idLivro = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLivro);
            stmt.executeUpdate();

            System.out.println("Livro excluído com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Livros buscarPorId(int idLivro) {
        List<Livros> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros WHERE IdLivro = ?"; // SQL para buscar por ID

            /*try (Connection conn = Conexao.conectar();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 stmt.setInt(1, idLivro);
                 ResultSet rs = stmt.executeQuery()) {*/
                //String sql = "SELECT * FROM produtos";
        /*try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) { // stmt é declarado aqui

            stmt.setInt(1, idLivro); // Agora, execute a operação setInt DENTRO do bloco try

            try (ResultSet rs = stmt.executeQuery()) { // Agora, rs é inicializado com a execução do stmt

                while (rs.next()) {
                    Livros p = new Livros();
                    p.setIdLivro(rs.getInt("idLivro"));
                    p.setTitulo(rs.getString("Titulo"));
                    livros.add(p);
                }
                return (Livros) livros;
            } catch (Exception e) {
                e.printStackTrace();
            }*/

            Livros livroEncontrado = null; // Inicializa como null
            //String sql = "SELECT * FROM livros WHERE IdLivro = ?"; // Sua SQL aqui

            try (Connection conn = Conexao.conectar();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, idLivro);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) { // Use 'if' porque você espera no máximo um resultado para um ID único
                        livroEncontrado = new Livros(); // Cria o objeto Livros
                        livroEncontrado.setIdLivro(rs.getInt("IdLivro")); // Use o nome da coluna correta
                        livroEncontrado.setTitulo(rs.getString("Titulo"));
                        // !!! Popule TODOS os outros campos do livro aqui !!!
                        // Exemplo:
                        // livroEncontrado.setAutores(rs.getString("Autores"));
                        // livroEncontrado.setEdicao(rs.getString("Edicao"));
                        // ... e assim por diante para todos os atributos do seu Livros
                    }
                }
            } catch (SQLException e) { // Capture SQLException, que é mais específico para problemas de banco
                System.err.println("Erro ao buscar livro por ID: " + e.getMessage());
                e.printStackTrace();
            }
            return livroEncontrado; // Retorna o livro encontrado ou null
    }




    /*titulo VARCHAR(100) NOT NULL,
    autores VARCHAR(100) NOT NULL,
    tradutores VARCHAR(100),
    edicao VARCHAR(100),
    editora VARCHAR(100) NOT NULL,
    local_publicacao VARCHAR(100),
    data_publicacao DATE,
    numero_paginas INT UNSIGNED,
    isbn VARCHAR(20) UNIQUE,
    status TINYINT NOT NULL DEFAULT 3, -- 1: Baixa, 2: Manutenção, 3: Ativo
    CONSTRAINT ck_livro_status CHECK (status IN (1, 2, 3))*/

}
