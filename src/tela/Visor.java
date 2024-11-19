package tela;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import bancodedados.ControlePessoas;
import cadastro.Pessoa;
import javax.swing.ImageIcon;

public class Visor extends JFrame {
	
	ControlePessoas cv = new ControlePessoas();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnSair;
	private JButton btnListarPacientes;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Visor frame = new Visor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Visor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 540);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(129, 0, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pessoa pessoa = new Pessoa();
				
				pessoa.setNome(textField.getText());
				pessoa.setData(textField_1.getText());
				pessoa.setEndereco(textField_2.getText());
				pessoa.setObservacoes(textField_3.getText());
				
				if (cv.salvar(pessoa)) {
					JOptionPane.showMessageDialog(null, "pessoa cadastrada com sucesso.");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField.requestFocus();
				}else {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar veiculo.");
				}
				
			}
		});
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\xdani\\Downloads\\Captura de tela 2024-06-04 011942.png"));
		lblNewLabel_2.setBounds(195, 2, 267, 120);
		contentPane.add(lblNewLabel_2);
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(0, 255, 128));
		btnNewButton.setBounds(216, 338, 181, 20);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(216, 149, 181, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Digite seu nome:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(216, 133, 152, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblDigiteSuaData = new JLabel("Digite sua data de nascimento:");
		lblDigiteSuaData.setForeground(new Color(255, 255, 255));
		lblDigiteSuaData.setBounds(216, 187, 240, 14);
		contentPane.add(lblDigiteSuaData);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(216, 201, 181, 20);
		contentPane.add(textField_1);
		
		JLabel lblDigiteSeuEndereo = new JLabel("Digite seu endereço:");
		lblDigiteSeuEndereo.setForeground(new Color(255, 255, 255));
		lblDigiteSeuEndereo.setBounds(216, 232, 152, 14);
		contentPane.add(lblDigiteSeuEndereo);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(216, 247, 181, 20);
		contentPane.add(textField_2);
		
		JLabel lblObservaes = new JLabel("Observações:");
		lblObservaes.setForeground(new Color(255, 255, 255));
		lblObservaes.setBounds(216, 279, 152, 14);
		contentPane.add(lblObservaes);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(216, 294, 181, 20);
		contentPane.add(textField_3);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSair.setForeground(Color.BLACK);
		btnSair.setBackground(new Color(255, 0, 0));
		btnSair.setBounds(216, 369, 181, 20);
		contentPane.add(btnSair);
		
		btnListarPacientes = new JButton("Listar Pacientes");
		btnListarPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostraPessoas pacientes = new MostraPessoas(null, true, cv);
				pacientes.setVisible(true);
			}
		});
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\xdani\\Downloads\\Captura de tela 2024-06-04 010559.png"));
		lblNewLabel_1.setBounds(-38, 90, 572, 297);
		contentPane.add(lblNewLabel_1);
		btnListarPacientes.setForeground(Color.BLACK);
		btnListarPacientes.setBackground(new Color(128, 128, 255));
		btnListarPacientes.setBounds(216, 404, 181, 20);
		contentPane.add(btnListarPacientes);
		
		label = new JLabel("New label");
		label.setBounds(488, 108, 46, 14);
		contentPane.add(label);
	}
}