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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Agradecimiento extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agradecimiento frame = new Agradecimiento();
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
	public Agradecimiento() {
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
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Recargar");
		mnSistema.add(mntmNewMenuItem);
		
		JMenuItem mntmSalirDelSistema = new JMenuItem("Salir Del Sistema");
		mnSistema.add(mntmSalirDelSistema);
		
		JMenu mnUsuario = new JMenu("Cliente");
		menuBar.add(mnUsuario);
		
		JMenuItem mntmBuscarCliente = new JMenuItem("Buscar Cliente");
		mnUsuario.add(mntmBuscarCliente);
		
		JMenuItem mntmAgregarCliente = new JMenuItem("Registrar Cliente");
		mnUsuario.add(mntmAgregarCliente);
		
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
	}

}
