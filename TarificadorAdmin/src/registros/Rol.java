package registros;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Rol extends JFrame {

	private JPanel contentPane;
	private JTextField rol;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rol frame = new Rol();
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
	public Rol() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 353, 288);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngreseLosDatos = new JLabel("Ingrese los Datos");
		lblIngreseLosDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseLosDatos.setBounds(103, 11, 148, 14);
		contentPane.add(lblIngreseLosDatos);
		
		JLabel lblTipoDeRol = new JLabel("Tipo De Rol");
		lblTipoDeRol.setBounds(30, 88, 92, 27);
		contentPane.add(lblTipoDeRol);
		
		rol = new JTextField();
		rol.setBounds(135, 91, 86, 20);
		contentPane.add(rol);
		rol.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				//CODIGO PARA REGRESAR
				
				int seleccion=JOptionPane.showConfirmDialog(null, "Realmente Desea Salir","Salir",JOptionPane.YES_NO_OPTION);
				if(seleccion==0){
					
					Ingresos miIngresos = new Ingresos();
			    	miIngresos.setVisible(true);
					dispose();
					
				}
				
				
			}
		});
		btnCancelar.setBounds(56, 205, 91, 23);
		contentPane.add(btnCancelar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//CODIGO PARA GUARDAR
				
				try {
					Class.forName("org.sqlite.JDBC");

				Connection conexion = DriverManager.getConnection("jdbc:sqlite:baseDatos/tarificador.db");
				Statement preparacion = conexion.createStatement();

				PreparedStatement agregarSql = conexion.prepareStatement("insert into rol (nombre) values (?);");

				agregarSql.setString(1, rol .getText());
				
				
				
			
				
				agregarSql.addBatch();
				
				
				conexion.setAutoCommit(false);
				int[] resultado = agregarSql.executeBatch();
				conexion.setAutoCommit(true);
				
				
				
				
				
				preparacion.close();
				conexion.close();
				
				
				
				 if(resultado.length > 0)
				    {
				    	JOptionPane.showMessageDialog(null, "Ha ingresado exitosamente...");
				    	Ingresos miIngresos = new Ingresos();
				    	miIngresos.setVisible(true);
				    	dispose();
				    }
				    else
				    {
				    	JOptionPane.showMessageDialog(null, "Error al ingresar");
				    }
				
				
				} 
				catch (ClassNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnGuardar.setBounds(215, 205, 91, 23);
		contentPane.add(btnGuardar);
	}

}
