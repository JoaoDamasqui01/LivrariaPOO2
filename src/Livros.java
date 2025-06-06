import java.util.Date;

public class Livros {
    private int idLivro;
    private String titulo;
    private String autores;
    private String tradutores;
    private String edicao;
    private String editora;
    private String local_publicacao;
    private Date data_publicacao;
    private int numero_paginas;
    private String isbn;
    private int status;

    public Livros(){}

    public Livros(String titulo, String autores, String tradutores, String edicao, String editora, String local_publicacao, Date data_publicacao, int numero_paginas, String isbn, int status) {
        this.titulo = titulo;
        this.autores = autores;
        this.tradutores = tradutores;
        this.edicao = edicao;
        this.editora = editora;
        this.local_publicacao = local_publicacao;
        this.data_publicacao = data_publicacao;
        this.numero_paginas = numero_paginas;
        this.isbn = isbn;
        this.status = status;
    }

    public int getIdLivro() {return idLivro;}

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getTradutores() {
        return tradutores;
    }

    public void setTradutores(String tradutores) {
        this.tradutores = tradutores;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getLocal_publicacao() {
        return local_publicacao;
    }

    public void setLocal_publicacao(String local_publicacao) {
        this.local_publicacao = local_publicacao;
    }

    public Date getData_publicacao() {
        return data_publicacao;
    }

    public void setData_publicacao(Date data_publicacao) {
        this.data_publicacao = data_publicacao;
    }

    public int getNumero_paginas() {
        return numero_paginas;
    }

    public void setNumero_paginas(int numero_paginas) {
        this.numero_paginas = numero_paginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
