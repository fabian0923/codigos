package sesion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;

import vistas.Sistema;

import java.awt.Toolkit;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Bienvenida extends JFrame {

	private JPanel contentPane;
	private String nombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Bienvenida frame = new Bienvenida();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param nombre 
	 */
	public Bienvenida(String nombre) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				int seleccion=JOptionPane.showConfirmDialog(null, "Realmente Desea Salir","Salir",JOptionPane.YES_NO_OPTION);
				if(seleccion==0){
					dispose();
				}
			}
		});
		
		setTitle("Tarificador SA.");
		setIconImage(Toolkit.getDefaultToolkit().getImage("Imagenes"+File.separator+"logotipo.jpg"));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.nombre = nombre;
		
		JLabel lblBienvendoAlSistema = new JLabel("Bienvendo al sistema ");
		lblBienvendoAlSistema.setFont(new Font("Arial", Font.PLAIN, 14));
		lblBienvendoAlSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvendoAlSistema.setBounds(95, 22, 268, 32);
		contentPane.add(lblBienvendoAlSistema);
		
		JLabel lblSeor = new JLabel(this.nombre);
		lblSeor.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSeor.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeor.setBounds(125, 82, 214, 23);
		contentPane.add(lblSeor);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Sistema miSistema = new Sistema();
				miSistema.setVisible(true);
				dispose();			
				
				
				
				
			}
		});
		btnAceptar.setBounds(181, 182, 91, 23);
		contentPane.add(btnAceptar);
	}

}
