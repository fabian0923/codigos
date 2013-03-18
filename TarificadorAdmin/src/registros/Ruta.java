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

public class Ruta extends JFrame {

	private JPanel contentPane;
	private JTextField nombre;
	private JTextField valor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ruta frame = new Ruta();
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
	public Ruta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIntroduzcaLosDatos = new JLabel("Introduzca los datos");
		lblIntroduzcaLosDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroduzcaLosDatos.setBounds(128, 11, 141, 14);
		contentPane.add(lblIntroduzcaLosDatos);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(46, 56, 46, 14);
		contentPane.add(lblNombre);
		
		nombre = new JTextField();
		nombre.setBounds(138, 53, 86, 20);
		contentPane.add(nombre);
		nombre.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(46, 129, 46, 14);
		contentPane.add(lblValor);
		
		valor = new JTextField();
		valor.setBounds(138, 126, 86, 20);
		contentPane.add(valor);
		valor.setColumns(10);
		
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
		btnCancelar.setBounds(79, 237, 91, 23);
		contentPane.add(btnCancelar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
					Class.forName("org.sqlite.JDBC");

				Connection conexion = DriverManager.getConnection("jdbc:sqlite:baseDatos/tarificador.db");
				Statement preparacion = conexion.createStatement();

				PreparedStatement agregarSql = conexion.prepareStatement("insert into ruta (nombre,valor) values (?,?);");

				agregarSql.setString(1, nombre .getText());
				agregarSql.setString(2, valor.getText());
				
				
			
				
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
		btnGuardar.setBounds(235, 237, 91, 23);
		contentPane.add(btnGuardar);
	}

}
