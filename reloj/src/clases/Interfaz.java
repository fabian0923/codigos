package clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Interfaz extends JFrame {

	private JPanel contentPane;
	private Reloj reloj;
	private JLabel horaLabel;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz frame = new Interfaz();
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
	public Interfaz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 reloj = new Reloj();
		
		
		
		
		JLabel lblHoraActual = new JLabel("Hora Actual");
		lblHoraActual.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoraActual.setBounds(155, 11, 144, 14);
		contentPane.add(lblHoraActual);
		
		horaLabel = new JLabel("");
		horaLabel.setBounds(200, 110, 144, 38);
		contentPane.add(horaLabel);
	}
	
	
	public void run()
	{
		
		while(true){
			
			            reloj.establecerHora();
			
			            if(reloj.getMeridiem() == 1)
			
			                horaLabel.setText(reloj.getHora() + ":" + reloj.getMinuto() + ":" + reloj.getSegundo() + " PM");
			
			            else
			
			                horaLabel.setText(reloj.getHora() + ":" + reloj.getMinuto() + ":" + reloj.getSegundo() + " AM");
			
			            try{
			
			                Thread.sleep(1000);
			
			            }catch(Exception e){
			
			                e.printStackTrace();
			
			            }

		
		
	}
	}
	
	
}
