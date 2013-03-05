package clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Inicio extends JFrame {

	private JPanel contentPane;
	private JTextField usuario;
	private JPasswordField clave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
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
	public Inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIniciarSesion = new JLabel("iniciar sesion");
		lblIniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblIniciarSesion.setBounds(185, 11, 112, 29);
		contentPane.add(lblIniciarSesion);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(25, 73, 46, 14);
		contentPane.add(lblUsuario);
		
		usuario = new JTextField();
		usuario.setBounds(121, 70, 86, 20);
		contentPane.add(usuario);
		usuario.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(25, 143, 77, 14);
		contentPane.add(lblContrasea);
		
		clave = new JPasswordField();
		clave.setBounds(121, 140, 86, 20);
		contentPane.add(clave);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				 try 
                 {
                         Class.forName("org.sqlite.JDBC");
                         Connection conexion = DriverManager.getConnection("jdbc:sqlite:basesDatos/sesion.db");
                         Statement preparacion = conexion.createStatement();
                         PreparedStatement agregarSql = conexion.prepareStatement("select * from usuarios,sesion where usuario=? and clave=? and usuarios.id = sesion.id_usuario");
                         
                         
                         
                         
                         
                         
                         String contra = "";
                         char array [] = clave.getPassword();
                         
                         for (int i = 0; i< array.length; i++)
                         {
                                 contra = contra + array [i];
                         }
                         
                         agregarSql.setString(1,usuario.getText());
                         agregarSql.setString(2,contra);
                         
                         ResultSet rs = agregarSql.executeQuery();
 						int cont = 0;
 						
 						String nombreApellidos="";
						while(rs.next())
						{
							nombreApellidos = rs.getString("nombre")+" "+rs.getString("apellido"); 
							cont++;
						}
						
						conexion.setAutoCommit(false);
						agregarSql.executeBatch();
						conexion.setAutoCommit(true);
						preparacion.close();
						conexion.close();
						
						
						if(cont > 0)
						{
							JOptionPane.showMessageDialog(null, "Ingreso exitoso!", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
						
							Bienvenida miBienvenida = new Bienvenida(nombreApellidos);
							miBienvenida.setVisible(true);
							dispose();
						}else{
							JOptionPane.showMessageDialog(null, "Error al ingresar. Usuario o Password incorrectos");
						}
					}
                         
                         
                         
                         
                         
                      
                         
                 
                 
				 catch(ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();	
				}
		
			
				
				
				
				
				
			}//cerrar funcion
		});
		btnIngresar.setBounds(273, 219, 91, 23);
		contentPane.add(btnIngresar);
	}
}
