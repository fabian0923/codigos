package controlador;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Agregar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nombres;
	private JTextField apellidos;
	private JTextField numero;
	private JComboBox tipo;
	private JComboBox estado;
	private JLabel letras1;
	private JLabel letras2;
	private JLabel numeros1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agregar frame = new Agregar();
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
	public Agregar() {
		setTitle("Registrar Aprendiz");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 458);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				letras1.setVisible(false);
				letras2.setVisible(false);
				numeros1.setVisible(false);
				
				
				
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		JLabel lblIngreseLosDatos = new JLabel("Ingrese los Datos");
		lblIngreseLosDatos.setBounds(173, 11, 122, 14);
		contentPane.add(lblIngreseLosDatos);
		
		JLabel lblNombre = new JLabel("nombres");
		lblNombre.setBounds(23, 71, 66, 14);
		contentPane.add(lblNombre);
		
		nombres = new JTextField();
		nombres.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				letras2.setVisible(false);
				numeros1.setVisible(false);
			}
		});
		nombres.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				letras2.setVisible(false);
				numeros1.setVisible(false);
				char car = e.getKeyChar();    
				if((car<'a' || car>'z') && (car<'A' || car>'Z') 
				   && (car!=(char)KeyEvent.VK_SPACE) && (car!=(char)KeyEvent.VK_BACK_SPACE))
				{
				   e.consume();
				   
				   letras1.setVisible(true);
				}
				else{
					letras1.setVisible(false);
				}
				
				
			}
		});
		nombres.setBounds(189, 68, 91, 20);
		contentPane.add(nombres);
		nombres.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(23, 120, 66, 14);
		contentPane.add(lblApellidos);
		
		apellidos = new JTextField();
		apellidos.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				letras1.setVisible(false);
				numeros1.setVisible(false);
				
			}
		});
		apellidos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				letras1.setVisible(false);
				numeros1.setVisible(false);
				
				char car = e.getKeyChar();    
				if((car<'a' || car>'z') && (car<'A' || car>'Z') 
				   && (car!=(char)KeyEvent.VK_SPACE) && (car!=(char)KeyEvent.VK_BACK_SPACE))
				{
				   e.consume();
				   letras2.setVisible(true);
				}
				else
				{
					letras2.setVisible(false);
				}
				
			}
		});
		apellidos.setBounds(189, 117, 91, 20);
		contentPane.add(apellidos);
		apellidos.setColumns(10);
		
		JLabel lblNoIdentifiacion = new JLabel("No\u00BA identifiacion");
		lblNoIdentifiacion.setBounds(23, 174, 103, 14);
		contentPane.add(lblNoIdentifiacion);
		
		numero = new JTextField();
		numero.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				
				letras1.setVisible(false);
				letras2.setVisible(false);
			}
		});
		numero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				letras1.setVisible(false);
				letras2.setVisible(false);
				char car = e.getKeyChar();
				if((car<'0' || car>'9') && (car!=(char)KeyEvent.VK_BACK_SPACE))
				{
					e.consume();
					numeros1.setVisible(true);
				}
				else
				{
					numeros1.setVisible(false);
				}
				
				
				
			}
		});
		numero.setBounds(189, 171, 91, 20);
		contentPane.add(numero);
		numero.setColumns(10);
		
		JLabel lblTipoDeIdentificacion = new JLabel("Tipo de Identificacion");
		lblTipoDeIdentificacion.setBounds(23, 240, 124, 14);
		contentPane.add(lblTipoDeIdentificacion);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(23, 303, 66, 14);
		contentPane.add(lblEstado);
		
		JButton btnGuadar = new JButton("Guadar");
		btnGuadar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent E) {
				
			
					try {
						Class.forName("org.sqlite.JDBC");

					Connection conexion = DriverManager.getConnection("jdbc:sqlite:BasesDatos/Sena.db");
					Statement preparacion = conexion.createStatement();

					PreparedStatement agregarSql = conexion.prepareStatement("insert into aprendices (nombres,apellidos,numeroIdentificacion,tipoIdentificacion,estado) values (?,?,?,?,?);");

					agregarSql.setString(1, nombres.getText());
					agregarSql.setString(2, apellidos.getText());
					agregarSql.setInt(3, Integer.parseInt(numero.getText()));
					
					
					String tipoId= (String) tipo.getSelectedItem();
					String estadoA= (String) estado.getSelectedItem();
					
					agregarSql.setString(4, tipoId);
					agregarSql.setString(5, estadoA);
					
					agregarSql.addBatch();
					
					
					conexion.setAutoCommit(false);
					int[] resultado = agregarSql.executeBatch();
					conexion.setAutoCommit(true);
					
					
					System.out.println("va: " + resultado.length);
					
					
					preparacion.close();
					conexion.close();
					
					
					
					 if(resultado.length > 0)
					    {
					    	JOptionPane.showMessageDialog(null, "Ha ingresado exitosamente...");
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
		btnGuadar.setBounds(273, 397, 91, 23);
		contentPane.add(btnGuadar);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Principal miPrincipal = new Principal();
				miPrincipal.setVisible(true);
				dispose();
			}
		});
		btnAtras.setBounds(56, 397, 91, 23);
		contentPane.add(btnAtras);
		
		tipo= new JComboBox();
		tipo.setModel(new DefaultComboBoxModel(new String[] {"cedula", "Tarjeta de Identidad"}));
		tipo.setBounds(189, 236, 116, 22);
		contentPane.add(tipo);
		
		
		estado = new JComboBox();
		estado.setModel(new DefaultComboBoxModel(new String[] {"Activo", "Desertado"}));
		estado.setBounds(189, 299, 96, 22);
		contentPane.add(estado);
		
	    letras1 = new JLabel("SOLO SE PERMITEN LETRAS");
	    letras1.setVisible(false);
		letras1.setBounds(314, 71, 160, 14);
		contentPane.add(letras1);
		
		letras2 = new JLabel("SOLO SE PERMITEN LETRAS");
		letras2.setVisible(false);
		letras2.setBounds(314, 120, 160, 14);
		contentPane.add(letras2);
		
		numeros1 = new JLabel("SOLO SE PERMITEN NUMEROS");
		numeros1.setVisible(false);
		numeros1.setBounds(314, 177, 181, 14);
		contentPane.add(numeros1);
	}
}
