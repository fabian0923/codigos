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

public class Medio extends JFrame {

	private JPanel contentPane;
	private JTextField nombre;
	private JTextField codigo;
	private JTextField tipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Medio frame = new Medio();
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
	public Medio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngreseLosDatos = new JLabel("Ingrese los Datos");
		lblIngreseLosDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseLosDatos.setBounds(121, 11, 175, 14);
		contentPane.add(lblIngreseLosDatos);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(33, 53, 74, 14);
		contentPane.add(lblNombre);
		
		nombre = new JTextField();
		nombre.setBounds(121, 50, 86, 20);
		contentPane.add(nombre);
		nombre.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setBounds(33, 113, 46, 14);
		contentPane.add(lblNewLabel);
		
		codigo = new JTextField();
		codigo.setBounds(121, 110, 86, 20);
		contentPane.add(codigo);
		codigo.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(33, 168, 46, 14);
		contentPane.add(lblTipo);
		
		tipo = new JTextField();
		tipo.setBounds(121, 165, 86, 20);
		contentPane.add(tipo);
		tipo.setColumns(10);
		
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
		btnCancelar.setBounds(67, 261, 91, 23);
		contentPane.add(btnCancelar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//CODIGO PARA GUARDAR 
				
				try {
					Class.forName("org.sqlite.JDBC");

				Connection conexion = DriverManager.getConnection("jdbc:sqlite:baseDatos/tarificador.db");
				Statement preparacion = conexion.createStatement();

				PreparedStatement agregarSql = conexion.prepareStatement("insert into medioIdentificacion (nombre,codigo,tipo) values (?,?,?);");

				agregarSql.setString(1, nombre .getText());
				agregarSql.setString(2, codigo.getText());
				agregarSql.setString(3, tipo.getText());
				
			
				
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
		btnGuardar.setBounds(253, 261, 91, 23);
		contentPane.add(btnGuardar);
	}

}
