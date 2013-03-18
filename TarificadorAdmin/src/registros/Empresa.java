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
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Empresa extends JFrame {

	private JPanel contentPane;
	private JTextField nombre;
	private JTextField nit;
	private JTextField direccion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Empresa frame = new Empresa();
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
	public Empresa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngreseLosDatos = new JLabel("Ingrese los datos de su empresa");
		lblIngreseLosDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseLosDatos.setBounds(149, 11, 207, 20);
		contentPane.add(lblIngreseLosDatos);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(21, 81, 102, 20);
		contentPane.add(lblNombre);
		
		nombre = new JTextField();
		nombre.setBounds(149, 81, 86, 20);
		contentPane.add(nombre);
		nombre.setColumns(10);
		
		JLabel lblNit = new JLabel("Nit");
		lblNit.setBounds(53, 149, 46, 14);
		contentPane.add(lblNit);
		
		nit = new JTextField();
		nit.setBounds(149, 146, 86, 20);
		contentPane.add(nit);
		nit.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(53, 207, 46, 14);
		contentPane.add(lblDireccion);
		
		direccion = new JTextField();
		direccion.setBounds(149, 204, 86, 20);
		contentPane.add(direccion);
		direccion.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				//CODIGO PARA GUARDAR
				
				try {
					Class.forName("org.sqlite.JDBC");

				Connection conexion = DriverManager.getConnection("jdbc:sqlite:baseDatos/tarificador.db");
				Statement preparacion = conexion.createStatement();

				PreparedStatement agregarSql = conexion.prepareStatement("insert into empresa (nombre,nit,direccion) values (?,?,?);");

				agregarSql.setString(1, nombre .getText());
				agregarSql.setString(2, nit.getText());
				agregarSql.setString(3, direccion.getText());
				
			
				
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
		btnGuardar.setBounds(287, 292, 91, 23);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//CODIGO PARA PEDIR CONFIRMACION
				
				
					int seleccion=JOptionPane.showConfirmDialog(null, "Realmente Desea Salir","Salir",JOptionPane.YES_NO_OPTION);
					if(seleccion==0){
						
						Ingresos miIngresos = new Ingresos();
				    	miIngresos.setVisible(true);
						dispose();
						
					}
				
				
			}
		});
		btnCancelar.setBounds(53, 292, 91, 23);
		contentPane.add(btnCancelar);
	}
}
