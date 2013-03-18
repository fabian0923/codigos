package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import registros.Ingresos;
import sesion.Inicio;

public class Registro extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
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
	public Registro() {
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
		setBounds(100, 100, 450, 547);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Recargar");
		mnSistema.add(mntmNewMenuItem);
		
		JMenuItem mntmSalirDelSistema = new JMenuItem("Salir Del Sistema");
		mnSistema.add(mntmSalirDelSistema);
		
		JMenu mnUsuario = new JMenu("Clientes");
		menuBar.add(mnUsuario);
		
		JMenuItem mntmBuscarCliente = new JMenuItem("Buscar Cliente");
		mnUsuario.add(mntmBuscarCliente);
		
		JMenuItem mntmAgregarCliente = new JMenuItem("Registrar Cliente");
		mnUsuario.add(mntmAgregarCliente);
		
		JMenu mnUsuarios = new JMenu("Usuarios");
		menuBar.add(mnUsuarios);
		
		JMenuItem mntmRegistrarUsuarios = new JMenuItem("Registrar Usuarios");
		mnUsuarios.add(mntmRegistrarUsuarios);
		
		JMenuItem mntmBuscarUsuarios = new JMenuItem("Buscar Usuarios");
		mnUsuarios.add(mntmBuscarUsuarios);
		
		JMenu mnIngresos = new JMenu("Ingresos");
		menuBar.add(mnIngresos);
		
		JMenuItem mntmMenuIngresos = new JMenuItem("Menu Ingresos");
		mntmMenuIngresos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				Ingresos misIngresos = new Ingresos();
				misIngresos.setVisible(true);
				dispose();
				
				
				
			}
		});
		mnIngresos.add(mntmMenuIngresos);
		
		JMenu mnAcercaDe = new JMenu("Acerca De");
		menuBar.add(mnAcercaDe);
		
		JMenuItem mntmAyuda = new JMenuItem("Ayuda");
		mnAcercaDe.add(mntmAyuda);
		
		JMenuItem mntmLaEmpresa = new JMenuItem("La Empresa");
		mnAcercaDe.add(mntmLaEmpresa);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIntroduzcaSusDatos = new JLabel("Introduzca sus datos");
		lblIntroduzcaSusDatos.setBounds(166, 11, 143, 14);
		contentPane.add(lblIntroduzcaSusDatos);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(26, 96, 70, 14);
		contentPane.add(lblNombre);
		
		textField = new JTextField();
		textField.setBounds(152, 93, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblIdentificacion = new JLabel("Identificacion");
		lblIdentificacion.setBounds(26, 147, 86, 14);
		contentPane.add(lblIdentificacion);
		
		textField_1 = new JTextField();
		textField_1.setBounds(152, 144, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(26, 205, 70, 14);
		contentPane.add(lblTelefono);
		
		textField_2 = new JTextField();
		textField_2.setBounds(152, 202, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(26, 254, 59, 14);
		contentPane.add(lblDireccion);
		
		textField_3 = new JTextField();
		textField_3.setBounds(152, 251, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(266, 465, 91, 23);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Inicio miInicio = new Inicio();
				miInicio.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(51, 465, 91, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblCorreoElectronico = new JLabel("Correo Electronico");
		lblCorreoElectronico.setBounds(26, 300, 123, 14);
		contentPane.add(lblCorreoElectronico);
		
		textField_4 = new JTextField();
		textField_4.setBounds(152, 297, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNumeroDeTarjeta = new JLabel("numero de tarjeta");
		lblNumeroDeTarjeta.setBounds(26, 358, 113, 14);
		contentPane.add(lblNumeroDeTarjeta);
		
		textField_5 = new JTextField();
		textField_5.setBounds(152, 355, 86, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
	}
}
