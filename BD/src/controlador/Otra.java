package controlador;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JScrollPane;
import java.awt.SystemColor;


public class Otra extends JFrame {

	private JPanel contentPane;
	private JLabel lblTablaAprendices;
	private DefaultTableModel modelo;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Otra frame = new Otra();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Otra() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Imagenes\\sena.jpg"));
		setTitle("Consultar Aprendices");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 421);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.desktop);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		modelo= new DefaultTableModel();
		
		
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Principal miPrincipal = new Principal();
				miPrincipal.setVisible(true);
				dispose();
				
				
			}
		});
		btnAtras.setBounds(75, 279, 91, 23);
		contentPane.add(btnAtras);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					Class.forName("org.sqlite.JDBC");
					Connection conexion =  DriverManager.getConnection("jdbc:sqlite://www.iansabe.hol.es//java//Sena.db");
					Statement preparacion =  conexion.createStatement();
					ResultSet  resultados = preparacion.executeQuery("SELECT * FROM aprendices;"); 
					String [] encabezados = {"ID", "NOMBRES", "APELLIDOS", "No. DE IDENTIFICACION", "TIPO DE IDENTIFICACION", "ESTADO"};
					
					modelo.setDataVector(null, encabezados);
					/*modelo.addRow(encabezados);*/
					while(resultados.next())
					{
						Object [] fila = new Object[6];
						
						for(int i = 0; i < 6; i++)
						{
							fila[i] = resultados.getObject(i+1);
						}
						
						modelo.addRow(fila);
					}
					preparacion.close();
					conexion.close();
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
				
			}
		});

		btnMostrar.setBounds(249, 279, 91, 23);
		contentPane.add(btnMostrar);
		
		lblTablaAprendices = new JLabel("Tabla Aprendices");
		lblTablaAprendices.setBounds(160, 23, 118, 23);
		contentPane.add(lblTablaAprendices);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 71, 382, 171);
		contentPane.add(scrollPane);
		table_1 = new JTable(modelo);
		scrollPane.setViewportView(table_1);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
Integer seleccionado = table_1.getSelectedRow(); // CREAMOS VARIABLE PARA LA SELECCION DEL USUARIO PARA CUANDO VA A MODIFICAR EL DATO
				 
                
                if(seleccionado != -1) // AQUI CREAMOS LA SENTENCIA DE "SELECCIONADO"
                {
                  Integer codigo = (Integer)modelo.getValueAt(seleccionado, 0);

    			  Modificar ventana6 = new Modificar (codigo);
  				 /* ventana6.setSize(680,440);*/
  				  ventana6.setVisible(true);
  				  dispose();      
                 
                }    
                else
                {
                    JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun registro para editar. ", "Advertencia",
                             JOptionPane.WARNING_MESSAGE); //MENSAJE DE AVERTENCIA
                    
                }  
				
				
				
				
			}
		});
		btnModificar.setBounds(75, 333, 91, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
Integer seleccionado = table_1.getSelectedRow(); // CREAMOS VARIABLE PARA LA SELECCION DEL USUARIO PARA CUANDO VA A MODIFICAR EL DATO
				 
                
                if(seleccionado != -1) // AQUI CREAMOS LA SENTENCIA DE "SELECCIONADO"
                {
                  	
                	int resul = JOptionPane.showConfirmDialog(null, "Eliminar", "desea eliminar?", JOptionPane.YES_NO_OPTION );
                	
                	if(resul == 0){
                		
                		Integer codigo = (Integer)modelo.getValueAt(seleccionado, 0);
                		
                		 try
                         {
                         Class.forName("org.sqlite.JDBC"); // CARGAMOS BASE DE DATOS
                         Connection conexion =DriverManager.getConnection("jdbc:sqlite:BasesDatos/Sena.db"); //    BUSCAMOS LA UBICACION DONDE ESTE LA BASE DE DATOS
                      
                         conexion.createStatement();
                         PreparedStatement consultasql = conexion.prepareStatement("DELETE FROM aprendices WHERE id="+codigo);
              
                         conexion.setAutoCommit(false);
                         int resul2 = consultasql.executeUpdate();
                         conexion.setAutoCommit(true);
                         modelo.removeRow(seleccionado);
                         

                         if(resul2>0)
                         {	 
                        	 JOptionPane.showMessageDialog(null, "Registro eliminado con  exito","Mensaje", JOptionPane.INFORMATION_MESSAGE);
                         }
                         else
                        	 JOptionPane.showMessageDialog(null, "Error","Mensaje", JOptionPane.INFORMATION_MESSAGE);

                         }
                         catch(SQLException err)
                         {
                         System.out.println("Error:"+err.toString());
                         }
                         catch(ClassNotFoundException err)
                         {
                         System.out.println("Error:"+err.toString());
                         }		
                		 
                		 
                		 modelo.fireTableDataChanged();
                		 
                		
                	}		
                  
                }    
                else
                {
                    JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun registro para eliminar. ", "Advertencia",
                           JOptionPane.WARNING_MESSAGE); //MENSAJE DE AVERTENCIA
                    
                }  
				
				
				
				
			}
		});
		btnEliminar.setBounds(246, 333, 91, 23);
		contentPane.add(btnEliminar);
	}
}
