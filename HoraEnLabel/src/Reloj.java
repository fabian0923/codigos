
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;



public class Reloj extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel nombre;
	private String ampm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reloj frame = new Reloj();
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
	public Reloj() {
		
		JButton btnIniciarSesion = new JButton("INICIAR SESION");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		
		Timer timer = new Timer (1000, new ActionListener () 
		{ 
		    public void actionPerformed(ActionEvent e) 
		    { 
		    	Calendar ahoraCal = Calendar.getInstance();
		    	 
		    	String dia = Integer.toString(ahoraCal.get(Calendar.DATE));
		    	String mes = Integer.toString(ahoraCal.get(Calendar.MONTH)+1);
		    	String annio = Integer.toString(ahoraCal.get(Calendar.YEAR));
		    	String hora = Integer.toString(ahoraCal.get(Calendar.HOUR));
		    	String minutos = Integer.toString(ahoraCal.get(Calendar.MINUTE));
		    	String segundos = Integer.toString(ahoraCal.get(Calendar.SECOND));
		    	
		    	nombre.setText("Fecha: " + dia + "/" + mes + "/" + annio + " - " + hora + 
		    			":" + minutos + ":" + segundos+" "+ampm);
		    	
		    	
		    	//ensayo de AM O PM
		    	
		    	 ampm = ahoraCal.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
		    	
		    	if (ampm.equals("PM")) {
		            int h = ahoraCal.get(Calendar.HOUR_OF_DAY) - 12;
		            hora = h > 9 ? "" + h : "0" + h;
		        } else {
		            hora = ahoraCal.get(Calendar.HOUR_OF_DAY) > 9 ? "" + ahoraCal.get(Calendar.HOUR_OF_DAY) : "0" + ahoraCal.get(Calendar.HOUR_OF_DAY);
		        }
		    	
		    	
		     } 
		}); 
		

		timer.start();
		
	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nombre = new JLabel("New label");
		nombre.setBounds(106, 84, 213, 14);
		contentPane.add(nombre);
		

		
	}
}
