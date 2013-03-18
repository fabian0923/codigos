package vistas;

import registros.Ingresos;
import sesion.Inicio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPasswordField;





import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Registro2 extends JFrame {

	private JPanel contentPane;
	
	private JTextField nombre;
	private JTextField Id;
	private JTextField direccion;
	private JTextField telefono;
	private JTextField correo;
	private JComboBox cargo;
	private JTextField apellidos;
	private JComboBox rutas;
	private JComboBox empresa;
	private JPasswordField clave;
	private JTextField usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro2 frame = new Registro2();
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
	public Registro2() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				int seleccion=JOptionPane.showConfirmDialog(null, "Realmente Desea Salir","Salir",JOptionPane.YES_NO_OPTION);
				if(seleccion==0){
					dispose();
				}
			}
		});
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 620, 839);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngreseSusDatos = new JLabel("Introduzca sus datos");
		lblIngreseSusDatos.setFont(new Font("Arial", Font.PLAIN, 14));
		lblIngreseSusDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseSusDatos.setBounds(253, 11, 136, 14);
		contentPane.add(lblIngreseSusDatos);
		
		JLabel lblNombre = new JLabel("Nombres");
		lblNombre.setBounds(38, 137, 64, 14);
		contentPane.add(lblNombre);
		
		nombre = new JTextField();
		nombre.setBounds(159, 134, 112, 20);
		contentPane.add(nombre);
		nombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Identificacion");
		lblApellidos.setBounds(38, 195, 86, 14);
		contentPane.add(lblApellidos);
		
		Id = new JTextField();
		Id.setBounds(159, 192, 112, 20);
		contentPane.add(Id);
		Id.setColumns(10);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setBounds(38, 254, 46, 14);
		contentPane.add(lblCargo);
		
		cargo = new JComboBox();
		cargo.setBounds(159, 246, 112, 22);
		contentPane.add(cargo);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(38, 319, 69, 14);
		contentPane.add(lblDireccion);
		
		direccion = new JTextField();
		direccion.setBounds(159, 313, 112, 20);
		contentPane.add(direccion);
		direccion.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(333, 195, 69, 14);
		contentPane.add(lblTelefono);
		
		telefono = new JTextField();
		telefono.setBounds(466, 192, 86, 20);
		contentPane.add(telefono);
		telefono.setColumns(10);
		
		JLabel lblCorreoElectronico = new JLabel("Correo Electronico");
		lblCorreoElectronico.setBounds(333, 254, 112, 14);
		contentPane.add(lblCorreoElectronico);
		
		correo = new JTextField();
		correo.setBounds(466, 251, 86, 20);
		contentPane.add(correo);
		correo.setColumns(10);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//CODIGO PARA GUARDAR
				
				Roles dato = (Roles) cargo.getSelectedItem() ;
				Integer idRol = dato.getValor() ;
				
				empresa.getSelectedItem() ;
				Integer idEmpresa = dato.getValor() ;
				
				rutas.getSelectedItem() ;
				Integer idRuta = dato.getValor() ;
				
				
				
				
				try {
					Class.forName("org.sqlite.JDBC");

				Connection conexion = DriverManager.getConnection("jdbc:sqlite:baseDatos/tarificador.db");
				Statement preparacion = conexion.createStatement();

				PreparedStatement agregarSql = conexion.prepareStatement("insert into usuario (nombre,apellido,numeroDocumento,telefono,direccion,id_rol,id_ruta,id_empresa,usuario,clave,correo) values (?,?,?,?,?,?,?,?,?,?,?);");

				agregarSql.setString(1, nombre .getText());
				agregarSql.setString(2, apellidos.getText());
				agregarSql.setString(3, Id.getText());
				agregarSql.setString(4, telefono.getText());
				agregarSql.setString(5, direccion.getText());
				agregarSql.setInt(6, idRol);
				agregarSql.setInt(7, idRuta);
				agregarSql.setInt(8, idEmpresa);
				agregarSql.setString(9, usuario.getText());
				
				// CODIGO PARA SACAR LA CLAVE DEL CAMPO PASSWORD
				
				String contra = "";
                char array [] = clave.getPassword();
                
                for (int i = 0; i< array.length; i++)
                {
                        contra = contra + array [i];
                }
                
                agregarSql.setString(10, contra);
                agregarSql.setString(11, correo.getText());
				
			
				
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
		btnNewButton.setBounds(386, 746, 91, 23);
		contentPane.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int seleccion=JOptionPane.showConfirmDialog(null, "Realmente Desea Salir","Salir",JOptionPane.YES_NO_OPTION);
				if(seleccion==0){
					Inicio miInicio = new Inicio();
					miInicio.setVisible(true);
					dispose();
				}
				
			}
		});
		btnCancelar.setBounds(159, 746, 91, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblDatosPersonales = new JLabel("Datos Personales");
		lblDatosPersonales.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDatosPersonales.setBounds(12, 45, 112, 29);
		contentPane.add(lblDatosPersonales);
		
		JLabel lblApeliido = new JLabel("Apeliidos");
		lblApeliido.setBounds(333, 137, 56, 14);
		contentPane.add(lblApeliido);
		
		apellidos = new JTextField();
		apellidos.setBounds(466, 134, 86, 20);
		contentPane.add(apellidos);
		apellidos.setColumns(10);
		
		JLabel lblRuta = new JLabel("Ruta");
		lblRuta.setBounds(333, 319, 46, 14);
		contentPane.add(lblRuta);
		
		rutas = new JComboBox();
		rutas.setBounds(466, 315, 86, 22);
		contentPane.add(rutas);
		
		JLabel lblEmpresa = new JLabel("Empresa");
		lblEmpresa.setBounds(174, 406, 64, 14);
		contentPane.add(lblEmpresa);
		
		empresa = new JComboBox();
		empresa.setBounds(290, 402, 112, 22);
		contentPane.add(empresa);
		
		JLabel lblDatosDeUsuario = new JLabel("Datos De Usuario");
		lblDatosDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosDeUsuario.setBounds(229, 477, 194, 29);
		contentPane.add(lblDatosDeUsuario);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(72, 592, 46, 14);
		contentPane.add(lblUsuario);
		
		clave = new JPasswordField();
		clave.setBounds(443, 589, 86, 20);
		contentPane.add(clave);
		
		usuario = new JTextField();
		usuario.setBounds(174, 589, 86, 20);
		contentPane.add(usuario);
		usuario.setColumns(10);
		
		JLabel lblClave = new JLabel("Clave");
		lblClave.setBounds(358, 592, 46, 14);
		contentPane.add(lblClave);
		
		// LLENAR EL COMBOBOX DE ROLES
		
		try
		{   
			Class.forName("org.sqlite.JDBC");    // CARGAMOS LA BASE DE DATOS
			Connection conexion = DriverManager.getConnection("jdbc:sqlite:baseDatos/tarificador.db"); // BUSCAMOS LA UBICACION DE LA BASE DE DATOS
			      
			Statement preparacion = conexion.createStatement();
			ResultSet resultados = preparacion.executeQuery("select * from rol;");

		
			while(resultados.next())
			{	
				cargo.addItem(new Roles(resultados.getString("nombre"), resultados.getInt("id")));
				
			}
			
			
			
			preparacion.close();
			conexion.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		
		
		
		
		// LLENAR EL COBOBOX DE EMPRESAS
		
		try
		{   
			Class.forName("org.sqlite.JDBC");    // CARGAMOS LA BASE DE DATOS
			Connection conexion = DriverManager.getConnection("jdbc:sqlite:baseDatos/tarificador.db"); // BUSCAMOS LA UBICACION DE LA BASE DE DATOS
			      
			Statement preparacion = conexion.createStatement();
			ResultSet resultados = preparacion.executeQuery("select * from empresa;");

		
			while(resultados.next())
			{	
				empresa.addItem(new Roles(resultados.getString("nombre"), resultados.getInt("id")));
				
			}
			
			
			
			preparacion.close();
			conexion.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		
		
		
		// LLENAR COMOBOBOX DE RUTAS
		
		try
		{   
			Class.forName("org.sqlite.JDBC");    // CARGAMOS LA BASE DE DATOS
			Connection conexion = DriverManager.getConnection("jdbc:sqlite:baseDatos/tarificador.db"); // BUSCAMOS LA UBICACION DE LA BASE DE DATOS
			      
			Statement preparacion = conexion.createStatement();
			ResultSet resultados = preparacion.executeQuery("select * from ruta;");

		
			while(resultados.next())
			{	
				rutas.addItem(new Roles(resultados.getString("nombre"), resultados.getInt("id")));
				
			}
			
			
			
			preparacion.close();
			conexion.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		
		
		
		
		
		
		
	}
}
