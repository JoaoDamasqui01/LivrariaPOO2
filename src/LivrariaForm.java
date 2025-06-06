import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LivrariaForm extends JFrame {
    private JButton BtnSalvar;
    private JButton BtnListarTudo;
    private JButton BtnExcluir;
    private JButton BtnEditar;
    private JTextField TF_Titulo;
    private JTextField TF_Autor;
    private JTextField TF_Editora;
    private JComboBox CBox_Status;
    private JLabel JL_Status;
    private JLabel JL_Editora;
    private JLabel JL_Autor;
    private JLabel JL_Titulo;
    private JPanel JP_Formulario;
    private JLabel JL_GenLivraria;
    private JTable table1;
    private JTextField TF_Tradutores;
    private JLabel JL_Tradutores;
    private JTextField TF_Edicao;
    private JLabel JL_Edicao;
    private JTextField TF_LocalPubli;
    private JPanel Principal;
    private JTextField TF_DataPubli;
    private JTextField TF_Pagina;
    private JTextField TF_Isbn;
    private JPanel JP_Menu;
    private JPanel JP_CheckBox;
    private JRadioButton RadioBtn_Ativos;
    private JRadioButton RadioBtn_Manutencao;
    private JRadioButton RadioBtn_Baixos;
    private JPanel JP_Button;
    private JButton BtnFiltar;
    private JButton filtarButton;

    LivroDAO dao = new LivroDAO();

    DefaultTableModel modeloTabela = new DefaultTableModel(
            new Object[]{"IdLivro", "titulo", "autores", "tradutores", "edicao", "editora", "local_publicacao", "data_publicacao", "numero_paginas", "isbn", "status"}, 0
    );

    public LivrariaForm() {
        setContentPane(Principal);
        table1.setModel(modeloTabela);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setTitle("Gerenciador de Produdos");


        BtnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String titulo = TF_Titulo.getText();
                    String autores = TF_Autor.getText();
                    String tradutores = TF_Tradutores.getText();
                    String edicao = TF_Edicao.getText();
                    String editora = TF_Editora.getText();
                    String local_publi = TF_LocalPubli.getText();

                    String data_publi = TF_DataPubli.getText();
                    Date data_publi_util = null;
                    java.util.Date data_publi_sql = null;

                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        data_publi_util = sdf.parse(data_publi);
                        data_publi_sql = new java.util.Date(data_publi_util.getTime());
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(null, "Formato de data inválido (dd/MM/yyyy)", "Erro de Data", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int num_paginas = Integer.parseInt(TF_Pagina.getText());
                    String isbn = TF_Isbn.getText();
                    String statusText = (String) CBox_Status.getSelectedItem();
                    int status = 0;
                    switch (statusText) {
                        case "Baixa":
                            status = 1;
                            break;
                        case "Manutenção":
                            status = 2;
                            break;
                        case "Ativo":
                            status = 3;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Status inválido selecionado.", "Erro de Status", JOptionPane.ERROR_MESSAGE);
                            return; // Interrompe a ação se o status for inválido
                    }



                    if (BtnSalvar.getText().equals("Atualizar")) {
                        int linhaSelecionada = table1.getSelectedRow();
                        int id = (int) table1.getValueAt(linhaSelecionada, 0);
                        Livros livro = new Livros(titulo, autores, tradutores, edicao, editora, local_publi, data_publi_sql, num_paginas, isbn, status);
                        livro.setIdLivro(id);
                        dao.atualizar(livro);
                        BtnSalvar.setText("Salvar");
                        JOptionPane.showMessageDialog(null, "Livro atualizado com sucesso!");
                    } else {
                        dao.inserir(new Livros(titulo, autores, tradutores, edicao, editora, local_publi, data_publi_sql, num_paginas, isbn, status));
                        JOptionPane.showMessageDialog(null, "Livro inserido com sucesso!");
                    }
                    LimparCampos();
                    atualizarTabela();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um número válido para preço e quantidade.", "Erro de entrada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        BtnListarTudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    modeloTabela.setRowCount(0);
                    for (Livros li : dao.listarTodos()){
                        modeloTabela.addRow(new Object[]{
                                li.getIdLivro(),
                                li.getTitulo(),
                                li.getAutores(),
                                li.getTradutores(),
                                li.getEdicao(),
                                li.getEditora(),
                                li.getLocal_publicacao(),
                                li.getData_publicacao(),
                                li.getNumero_paginas(),
                                li.getIsbn(),
                                li.getStatus()

                        });
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        BtnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = table1.getSelectedRow();
                if (linhaSelecionada != -1) {
                    // Preenche os campos com os dados da linha selecionada
                    TF_Titulo.setText(table1.getValueAt(linhaSelecionada, 1).toString());
                    TF_Autor.setText(table1.getValueAt(linhaSelecionada, 2).toString());
                    TF_Tradutores.setText(table1.getValueAt(linhaSelecionada, 3).toString());
                    TF_Edicao.setText(table1.getValueAt(linhaSelecionada, 4).toString());
                    TF_Editora.setText(table1.getValueAt(linhaSelecionada, 5).toString());
                    TF_LocalPubli.setText(table1.getValueAt(linhaSelecionada, 6).toString());
                    TF_DataPubli.setText(table1.getValueAt(linhaSelecionada, 7).toString());
                    TF_Pagina.setText(table1.getValueAt(linhaSelecionada, 8).toString());
                    TF_Isbn.setText(table1.getValueAt(linhaSelecionada, 9).toString());
                    //habilitarCampos(true);
                    BtnSalvar.setText("Atualizar");
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma linha para editar.");
                }
            }
        });

        BtnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = table1.getSelectedRow();
                if (linhaSelecionada != -1) {
                    int id = (int) table1.getValueAt(linhaSelecionada, 0);
                    int confirm = JOptionPane.showConfirmDialog(null, "Confirma a exclusão?", "Atenção", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        dao.excluir(id);
                        atualizarTabela();
                        JOptionPane.showMessageDialog(null, "Livro excluído com sucesso!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma linha para excluir.");
                }
            }
        });

        BtnFiltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    modeloTabela.setRowCount(0);
                    for (Livros li : dao.listarStatus()){
                        modeloTabela.addRow(new Object[]{
                                li.getIdLivro(),
                                li.getTitulo(),
                                li.getAutores(),
                                li.getTradutores(),
                                li.getEdicao(),
                                li.getEditora(),
                                li.getLocal_publicacao(),
                                li.getData_publicacao(),
                                li.getNumero_paginas(),
                                li.getIsbn(),
                                li.getStatus()

                        });
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    private void atualizarTabela() {
        List<Livros> lista = (List<Livros>) dao.listarTodos();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("IdLivro");
        model.addColumn("titulo");
        model.addColumn("autores");
        model.addColumn("tradutores");
        model.addColumn("edicao");
        model.addColumn("editora");
        model.addColumn("local_publicacao");
        model.addColumn("data_publicacao");
        model.addColumn("numero_paginas");
        model.addColumn("isbn");
        model.addColumn("status");

        for (Livros li : lista) {
            model.addRow(new Object[]{li.getIdLivro(), li.getTitulo(), li.getAutores(),
                                      li.getTradutores(), li.getEdicao(), li.getEditora(),
                                      li.getLocal_publicacao(), li.getData_publicacao(),
                                      li.getNumero_paginas(), li.getIsbn(), li.getStatus()});
        }

        table1.setModel(model);
    }

    private void LimparCampos() {
        TF_Titulo.setText("");
        TF_Autor.setText("");
        TF_Tradutores.setText("");
        TF_Edicao.setText("");
        TF_Editora.setText("");
        TF_LocalPubli.setText("");
        TF_DataPubli.setText("");
        TF_Pagina.setText("");
        TF_Isbn.setText("");


    }
    /*private void habilitarCampos(boolean status) {
        TF_nome.setEnabled(status);
        TF_preco.setEnabled(status);
        TF_quantidade.setEnabled(status);
        TA_descricao.setEnabled(status);
    }*/
}