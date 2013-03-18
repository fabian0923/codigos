package registros;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import vistas.Sistema;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ingresos extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ingresos frame = new Ingresos();
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
	public Ingresos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 403);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);
		
		JMenuItem mntmMenuPrincipal = new JMenuItem("Menu Principal");
		mntmMenuPrincipal.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) 
			{
				
				Sistema miSistema = new Sistema();
				miSistema.setVisible(true);
				dispose();
				
				
				
				
			}
		});
		mnSistema.add(mntmMenuPrincipal);
		
		JMenu mnMedioIdentificacion = new JMenu("Medio Identificacion");
		menuBar.add(mnMedioIdentificacion);
		
		JMenuItem mntmRegistrarMedio = new JMenuItem("Registrar Medio");
		mntmRegistrarMedio.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				// CODIGO PARA LLAMAR LA CLASE
				
				
				Medio miMedio = new Medio();
				miMedio.setVisible(true);
				dispose();
				
				
				
			}
		});
		mnMedioIdentificacion.add(mntmRegistrarMedio);
		
		JMenu mnRuta = new JMenu("Ruta");
		menuBar.add(mnRuta);
		
		JMenuItem mntmRegistrarRuta = new JMenuItem("Registrar Ruta");
		mntmRegistrarRuta.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				// CODIGO PARA LLARMAR LA CLASE
				
				Ruta miRuta = new Ruta();
				miRuta.setVisible(true);
				dispose();
				
				
				
				
				
			}
		});
		mnRuta.add(mntmRegistrarRuta);
		
		JMenu mnNewMenu = new JMenu("Roles");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmRegistrarRol = new JMenuItem("Registrar Rol");
		mntmRegistrarRol.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				Rol miRol = new Rol();
				miRol.setVisible(true);
				dispose();
				
				
				
				
				
			}
		});
		mnNewMenu.add(mntmRegistrarRol);
		
		JMenu mnEmpresa = new JMenu("Empresa");
		menuBar.add(mnEmpresa);
		
		JMenuItem mntmRegistrarEmpresa = new JMenuItem("Editar Datos");
		mntmRegistrarEmpresa.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				Empresa miEmpresa = new Empresa();
				miEmpresa.setVisible(true);
				dispose();
				
				
				
				
			}
		});
		mnEmpresa.add(mntmRegistrarEmpresa);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeleccioneUnaOpcion = new JLabel("Seleccione una opcion");
		lblSeleccioneUnaOpcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeleccioneUnaOpcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccioneUnaOpcion.setBounds(133, 67, 261, 82);
		contentPane.add(lblSeleccioneUnaOpcion);
	}
}
