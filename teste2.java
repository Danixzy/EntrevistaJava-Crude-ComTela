package tela;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bancodedados.ControlePessoas;
import cadastro.Pessoa;

public class MostraPessoas extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;

	ControlePessoas cv;
	
	public MostraPessoas(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		//initComponents();
	}
	
	public MostraPessoas(java.awt.Frame parent, boolean modal, ControlePessoas cv) {
		super(parent, modal);
		this.cv = cv;
		//initComponents();
		exibeInformacoes();
	}
	
	private void exibeInformacoes() {
		ArrayList<Pessoa> pessoas = cv.imprimir();
		
		
		//limpar tabela
		for(int i = 0; i < pessoas.size(); i++) {
			table.setValueAt("", i, 0);
			table.setValueAt("", i, 1);
			table.setValueAt("", i, 2);
			table.setValueAt("", i, 3);
		}
		
		//colocar na tabela
		for(int i = 0; i < pessoas.size() && pessoas.get(i) != null; i++) {
			table.setValueAt(pessoas.get(i).getNome(), i, 0);
			table.setValueAt(pessoas.get(i).getData(), i, 0);
			table.setValueAt(pessoas.get(i).getEndereço(), i, 0);
			table.setValueAt(pessoas.get(i).getObservações(), i, 0);
		}
	}

	public static void main(String[] args) {
		try {
			MostraPessoas dialog = new MostraPessoas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MostraPessoas() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
				},
				new String[] {
					"Data", "Nome", "Endere\u00E7o", "Observa\u00E7\u00F5es"
				}
			));
			table.setBounds(61, 35, 300, 193);
			contentPanel.add(table);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Voltar");
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