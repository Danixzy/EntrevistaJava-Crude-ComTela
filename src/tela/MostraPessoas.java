package tela;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import bancodedados.ControlePessoas;
import cadastro.Pessoa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.border.MatteBorder;

public class MostraPessoas extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private ControlePessoas cv;
    private boolean isEditingMode = false;
    private JTextField txtNome;
    private JTextField txtData;
    private JTextField txtEndereo;
    private JTextField txtObservaes;

    public MostraPessoas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public MostraPessoas(java.awt.Frame parent, boolean modal, ControlePessoas cv) {
        super(parent, modal);
        this.cv = cv;
        initComponents();
        exibeInformacoes();
    }

    private void exibeInformacoes() {
        ArrayList<Pessoa> pessoas = cv.imprimir();

        // Limpar tabela
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        // Colocar na tabela
        for (Pessoa pessoa : pessoas) {
            model.addRow(new Object[] {pessoa.getId(), pessoa.getNome(), pessoa.getData(), pessoa.getEndereco(), pessoa.getObservacoes()});
        }

       //erroid
        TableColumnModel tcm = table.getColumnModel();
        TableColumn idColumn = tcm.getColumn(0);
        idColumn.setMinWidth(0);
        idColumn.setMaxWidth(0);
        idColumn.setPreferredWidth(0);
    }

    private void removerPessoa() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int id = (int) model.getValueAt(selectedRow, 0);

            cv.remover(id);
            model.removeRow(selectedRow);
        } else {
            System.out.println("Nenhuma pessoa selecionada para remover.");
        }
    }

    private void toggleEditMode(JButton editButton) {
        isEditingMode = !isEditingMode;
        editButton.setText(isEditingMode ? "Salvar" : "Editar");

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.fireTableStructureChanged();

       //erroid
        TableColumnModel tcm = table.getColumnModel();
        TableColumn idColumn = tcm.getColumn(0);
        idColumn.setMinWidth(0);
        idColumn.setMaxWidth(0);
        idColumn.setPreferredWidth(0);

        if (isEditingMode) {
            System.out.println("Modo de edição habilitado.");
        } else {
            TableModel tm = table.getModel();
            int rowCount = tm.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                int id = (int) tm.getValueAt(i, 0);
                String nome = (String) tm.getValueAt(i, 1);
                String data = (String) tm.getValueAt(i, 2);
                String endereco = (String) tm.getValueAt(i, 3);
                String observacoes = (String) tm.getValueAt(i, 4);

                Pessoa pessoa = new Pessoa(id, nome, data, endereco, observacoes);

                if (!cv.atualizar(pessoa)) {
                    cv.salvar(pessoa);
                }
            }
            System.out.println("Modo de edição desabilitado e alterações salvas.");
            exibeInformacoes(); 
        }
    }

    public static void main(String[] args) {
        try {
            MostraPessoas dialog = new MostraPessoas(null, true);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        setBounds(100, 100, 834, 547);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        {
            table = new JTable() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return isEditingMode;
                }
            };
            table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
            table.setFont(new Font("Tahoma", Font.PLAIN, 11));
            table.setForeground(new Color(255, 255, 255));
            table.setBackground(new Color(129, 0, 204));
            table.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {"ID", "Nome", "Data", "Endereço", "Observações"}
            ));
            table.setBounds(22, 52, 768, 401);
            table.getModel().addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    if (isEditingMode && e.getType() == TableModelEvent.UPDATE) {
                        int row = e.getFirstRow();
                        int column = e.getColumn();
                        if (row < 0 || column < 0) {
                            return;
                        }

                        TableModel model = (TableModel) e.getSource();

                        int id = (int) model.getValueAt(row, 0);
                        String nome = (String) model.getValueAt(row, 1);
                        String data = (String) model.getValueAt(row, 2);
                        String endereco = (String) model.getValueAt(row, 3);
                        String observacoes = (String) model.getValueAt(row, 4);

                        Pessoa pessoa = new Pessoa(id, nome, data, endereco, observacoes);
                        cv.atualizar(pessoa);
                    }
                }
            });

            contentPanel.add(table);
        }

        txtNome = new JTextField();
        txtNome.setBackground(SystemColor.controlHighlight);
        txtNome.setEditable(false);
        txtNome.setText("                          NOME");
        txtNome.setBounds(22, 33, 192, 20);
        contentPanel.add(txtNome);
        txtNome.setColumns(10);

        txtData = new JTextField();
        txtData.setBackground(SystemColor.controlHighlight);
        txtData.setEditable(false);
        txtData.setText("                          DATA");
        txtData.setColumns(10);
        txtData.setBounds(212, 33, 194, 20);
        contentPanel.add(txtData);
        {
            txtEndereo = new JTextField();
            txtEndereo.setBackground(SystemColor.controlHighlight);
            txtEndereo.setEditable(false);
            txtEndereo.setText("                      ENDEREÇO      ");
            txtEndereo.setColumns(10);
            txtEndereo.setBounds(405, 33, 194, 20);
            contentPanel.add(txtEndereo);
        }
        {
            txtObservaes = new JTextField();
            txtObservaes.setBackground(SystemColor.controlHighlight);
            txtObservaes.setEditable(false);
            txtObservaes.setText("                   OBSERVAÇÕES");
            txtObservaes.setColumns(10);
            txtObservaes.setBounds(597, 33, 193, 20);
            contentPanel.add(txtObservaes);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton editButton = new JButton("Editar");
                editButton.setBackground(new Color(0, 255, 128));
                editButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        toggleEditMode((JButton) e.getSource());
                    }
                });
                buttonPane.add(editButton);
            }
            {
                JButton removeButton = new JButton("Remover");
                removeButton.setBackground(Color.RED);
                removeButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        removerPessoa();
                    }
                });
                buttonPane.add(removeButton);
            }
            {
                JButton cancelButton = new JButton("Voltar");
                cancelButton.setBackground(new Color(128, 128, 255));
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }
}
