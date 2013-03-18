package sesion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;



import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Contrasena extends JFrame {

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
					Contrasena frame = new Contrasena();
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
	public Contrasena() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				int seleccion=JOptionPane.showConfirmDialog(null, "Realmente Desea Salir","Salir",JOptionPane.YES_NO_OPTION);
				if(seleccion==0){
					dispose();
				}
			}
		});
		setTitle("Tarificador SA.");
		setIconImage(Toolkit.getDefaultToolkit().getImage("Imagenes"+File.separator+"logotipo.jpg"));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngreseSuContrasea = new JLabel("Ingrese Sus Datos");
		lblIngreseSuContrasea.setFont(new Font("Arial", Font.PLAIN, 14));
		lblIngreseSuContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseSuContrasea.setBounds(125, 11, 191, 25);
		contentPane.add(lblIngreseSuContrasea);
		
		usuario = new JTextField();
		usuario.setBounds(159, 78, 86, 20);
		contentPane.add(usuario);
		usuario.setColumns(10);
		
		JLabel lblNombre = new JLabel("Usuario:");
		lblNombre.setBounds(33, 81, 86, 14);
		contentPane.add(lblNombre);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(33, 132, 86, 14);
		contentPane.add(lblContrasea);
		
		clave = new JPasswordField();
		clave.setBounds(159, 129, 86, 20);
		contentPane.add(clave);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//CODIGO PARA INGRESAR AL SISTEMA
				 try 
                 {
                         Class.forName("org.sqlite.JDBC");
                         Connection conexion = DriverManager.getConnection("jdbc:sqlite:baseDatos/tarificador.db");
                         Statement preparacion = conexion.createStatement();
                         PreparedStatement agregarSql = conexion.prepareStatement("select * from usuario where usuario=? and clave=?");
                         
                         
                         
                         
                         
                         
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
						}
						else{
							JOptionPane.showMessageDialog(null, "Error al ingresar. Usuario o Password incorrectos");
						}
					}
                         
                         
                         
                         
                         
                      
                         
                 
                 
				 catch(ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
					e1.printStackTrace();	
				}
			}
		});
		btnAceptar.setBounds(257, 221, 91, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int s= JOptionPane.showConfirmDialog(null,"¿seguro que desea salir?");
				if(s==0){
					Inicio miInicio = new Inicio();
					miInicio.setVisible(true);
					dispose();
				}
			
				
				
			}
		});
		btnCancelar.setBounds(80, 221, 91, 23);
		contentPane.add(btnCancelar);
	}
}
