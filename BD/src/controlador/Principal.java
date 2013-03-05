package controlador;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;

public class Principal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
			
					
				 try 
				    {
				      UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
				    } 
				    catch (Exception e) 
				    {
				      e.printStackTrace();
				    }
					
				
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Imagenes\\sena.jpg"));
		setTitle("Menu Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 407, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnConsultarAprendices = new JButton("Consultar Aprendices");
		btnConsultarAprendices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Otra miOtra = new Otra();
				miOtra.setVisible(true);
				dispose();
			}
		});
		btnConsultarAprendices.setBounds(37, 239, 174, 23);
		contentPane.add(btnConsultarAprendices);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Agregar miAgregar = new Agregar();
				miAgregar.setVisible(true);
				dispose();
				
			}
		});
		btnAgregar.setBounds(262, 239, 91, 23);
		contentPane.add(btnAgregar);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("Imagenes\\sena.jpg"));
		lblNewLabel.setBounds(0, 0, 405, 273);
		contentPane.add(lblNewLabel);
	}
}
