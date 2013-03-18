package vistas;

import java.awt.EventQueue;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Component;
import javax.swing.SwingConstants;

import registros.Ingresos;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sistema extends JFrame {

	private JPanel contentPane;
	private JLabel hora;
	private String ampm;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sistema frame = new Sistema();
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
	public Sistema() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				int seleccion=JOptionPane.showConfirmDialog(null, "Realmente Desea Salir","Salir",JOptionPane.YES_NO_OPTION);
			if(seleccion==0)
			{
				dispose();
			}
			
			}
			
			
		});
		
		
		
		
		
		
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
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
		mntmAgregarCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) 
			{
				Registro miRegistro = new Registro();
				miRegistro.setVisible(true);
				dispose();
				
				
				
			}
		});
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
		
		JLabel hora = new JLabel();
		hora.setBounds(157, 51, 104, 41);
		contentPane.add(hora);
		hora.setHorizontalTextPosition(SwingConstants.RIGHT);
		hora.setHorizontalAlignment(SwingConstants.RIGHT);
		hora.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		JLabel lblSeleccioneUnaOpcion = new JLabel("Seleccione una opcion");
		lblSeleccioneUnaOpcion.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSeleccioneUnaOpcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccioneUnaOpcion.setBounds(87, 51, 252, 121);
		contentPane.add(lblSeleccioneUnaOpcion);
		
		
		
		
		
	}
}
