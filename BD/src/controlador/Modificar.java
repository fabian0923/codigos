package controlador;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Modificar extends JFrame {

	private JPanel contentPane;
	private JTextField nombres;
	private JTextField apellidos;
	private JTextField numero;
	private int codigo;
	private JComboBox estado;
	private JComboBox tipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					/*Modificar frame = new Modificar();
					frame.setVisible(true);*/
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Modificar(int modificar)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		codigo=modificar;
		
		JLabel lblModifiqueSusDatos = new JLabel("modifique sus datos");
		lblModifiqueSusDatos.setBounds(216, 11, 120, 14);
		contentPane.add(lblModifiqueSusDatos);
		
		JLabel lblNombres = new JLabel("nombres");
		lblNombres.setBounds(28, 63, 46, 14);
		contentPane.add(lblNombres);
		
		nombres = new JTextField();
		nombres.setBounds(159, 60, 86, 20);
		contentPane.add(nombres);
		nombres.setColumns(10);
		
		JLabel lblApellidos = new JLabel("apellidos");
		lblApellidos.setBounds(28, 125, 46, 14);
		contentPane.add(lblApellidos);
		
		apellidos = new JTextField();
		apellidos.setBounds(159, 122, 86, 20);
		contentPane.add(apellidos);
		apellidos.setColumns(10);
		
		JLabel lblNoIdentificacion = new JLabel("No\u00BA identificacion");
		lblNoIdentificacion.setBounds(28, 196, 95, 14);
		contentPane.add(lblNoIdentificacion);
		
		numero = new JTextField();
		numero.setBounds(159, 193, 86, 20);
		contentPane.add(numero);
		numero.setColumns(10);
		
		JLabel lblTipoIdentificacion = new JLabel("tipo identificacion");
		lblTipoIdentificacion.setBounds(28, 275, 86, 14);
		contentPane.add(lblTipoIdentificacion);
		
		tipo = new JComboBox();
		tipo.setModel(new DefaultComboBoxModel(new String[] {"cedula", "Tarjeta de Identidad"}));
		tipo.setBounds(176, 275, 69, 22);
		contentPane.add(tipo);
		
		JLabel lblEstado = new JLabel("estado");
		lblEstado.setBounds(28, 352, 46, 14);
		contentPane.add(lblEstado);
		
		estado = new JComboBox();
		estado.setModel(new DefaultComboBoxModel(new String[] {"Activo", "Desertado"}));
		estado.setBounds(176, 362, 69, 22);
		contentPane.add(estado);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
				Class.forName("org.sqlite.JDBC"); // CARGAMOS BASE DE DATOS
				Connection conexion = DriverManager.getConnection("jdbc:sqlite:BasesDatos/Sena.db"); // BUSCAMOS LA UBICACION DONDE ESTE LA BASE DE DATOS
				conexion.createStatement();
				
				// Aqui vamos a insertar los datos del USUARIO. dependiendo de los campos. si son 7 entonces se ponen 7 signos de "?". en "Values" separados por comas.
				PreparedStatement consultasql = conexion.prepareStatement("UPDATE aprendices SET nombres = ?, apellidos = ?,numeroIdentificacion = ?,tipoIdentificacion=?,estado=? where id="+codigo );
				
				
				consultasql.setString(1,nombres.getText());
				consultasql.setString(2,apellidos.getText());
				consultasql.setString(3,numero.getText());
				consultasql.setString(4,(String)tipo.getSelectedItem());
				consultasql.setString(5,(String)estado.getSelectedItem());
               

				
				conexion.setAutoCommit(false);
				int resul = consultasql.executeUpdate();
				conexion.setAutoCommit(true);
				
				// Mensaje informativo donde nos informa que el "registro a ingresado con exito"
				if (resul>0) 
					JOptionPane.showMessageDialog(null, "Registro ingresado con exito","Mensaje", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Error","Mensaje", JOptionPane.INFORMATION_MESSAGE);
				
				Principal miPrincipal = new Principal();
				miPrincipal.setVisible(true);
				dispose();
				
				}
				catch(SQLException err)
				{
				System.out.println("Error:"+err.toString());
				}
				catch(ClassNotFoundException err)
				{
				System.out.println("Error:"+err.toString());
				}
			
				
				
				
				
				
				
			}
		});
		btnModificar.setBounds(285, 463, 91, 23);
		contentPane.add(btnModificar);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Otra miOtra= new Otra();
				miOtra.setVisible(true);
				dispose();
			}
		});
		btnAtras.setBounds(68, 463, 91, 23);
		contentPane.add(btnAtras);
		
		
		
		 try
         {
          
          Class.forName("org.sqlite.JDBC");    // CARGAMOS LA BASE DE DATOS
          Connection conexion = DriverManager.getConnection("jdbc:sqlite:BasesDatos/Sena.db"); // BUSCAMOS LA UBICACION DE LA BASE DE DATOS
         
          Statement preparacion = conexion.createStatement();
          ResultSet resultados = preparacion.executeQuery("select id,nombres,apellidos,numeroIdentificacion,tipoIdentificacion,estado from aprendices where id="+codigo);// AQUI LLAMAMOS CODIGO PARA LA OTRA VENTANA


         
          
         
          resultados.next();
          
          
         //System.out.println("vale: " + codigo + "sele: " + resultados.getInt(6));
          
          // LLENAR LOS CAMPOS
          //TODOS SON TEXTO ASI QUE SE APLICA EL CODIGO SIGUIENTE
          /*codigo.setText(""+resultados.getString(1));*/ 
          nombres.setText(""+resultados.getString(2));
          apellidos.setText(""+resultados.getString(3));
          numero.setText(""+resultados.getString(4));
          tipo.setSelectedItem(resultados.getString(5)); // JCOMBOX..
         
          estado.setSelectedItem(resultados.getString(6)); // JCOMBOX.. 
         

              

         preparacion.close();
         conexion.close();
         	
	
         }
		 catch(SQLException err)
			{
				System.out.println("Error:"+err.toString());
			}
			catch(ClassNotFoundException err)
			{
				System.out.println("Error:"+err.toString());
			}
		 
	}}
