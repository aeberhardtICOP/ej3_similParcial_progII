package igu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import persistence.LoginPersistence;
import service.IntegranteService;
import service.OrganizacionService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField jpasswordfield;
	private LoginPersistence login;
	private JLabel lblUsuarioInexistente;
	private JLabel lblContraseñaIncorrecta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Log-In");
		this.login =  new LoginPersistence();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Iniciar sesion:");
		lblLogin.setFont(new Font("Tw Cen MT", Font.PLAIN, 17));
		lblLogin.setForeground(new Color(0, 128, 192));
		lblLogin.setBounds(10, 0, 124, 31);
		contentPane.add(lblLogin);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(57, 42, 63, 14);
		contentPane.add(lblUsuario);
		
		txtUser = new JTextField();
		txtUser.setBounds(120, 34, 248, 31);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblPassword = new JLabel("Contraseña:");
		lblPassword.setBounds(38, 95, 96, 14);
		contentPane.add(lblPassword);
		
		jpasswordfield = new JPasswordField();
		jpasswordfield.setBounds(120, 87, 248, 31);
		contentPane.add(jpasswordfield);
		
		lblUsuarioInexistente = new JLabel("");
		lblUsuarioInexistente.setBounds(378, 42, 178, 14);
		contentPane.add(lblUsuarioInexistente);
		
		lblContraseñaIncorrecta = new JLabel("");
		lblContraseñaIncorrecta.setBounds(378, 95, 196, 14);
		contentPane.add(lblContraseñaIncorrecta);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.setBounds(175, 168, 124, 40);
		contentPane.add(btnIniciarSesion);
		btnIniciarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(login.existeUsuario(txtUser.getText())) {
					char[] password = jpasswordfield.getPassword();
	                String passwordString = new String(password);
					if(login.buscarUsuario(txtUser.getText(), passwordString)) {
						JOptionPane.showMessageDialog(null, "Inicio de sesión correcto!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
						IntegranteService inteser = new IntegranteService();
						OrganizacionService orgser = new OrganizacionService();
						Menu menu = new Menu(orgser,inteser);
						menu.setVisible(true);
						dispose();
					}else {
						lblUsuarioInexistente.setText("");
						lblContraseñaIncorrecta.setText("Contraseña incorrecta!");
					}
				}else {
					lblContraseñaIncorrecta.setText("");
					lblUsuarioInexistente.setText("No existe ese usuario!");
				}
				
			}
		});
		
		JLabel lblNuevoUser = new JLabel("Registrarse");
		lblNuevoUser.setBounds(200, 219, 83, 14);
		contentPane.add(lblNuevoUser);
	}
}
