package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Inicio extends JFrame {

	private JPanel contentPane;
	private JComboBox tipo;
	private JComboBox empresa;
	private JTextField campoBuscar;
	private DefaultTableModel modelo;
	private JTable table;
	private int codigos[];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
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
	public Inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 559);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		modelo= new DefaultTableModel();
		
		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setBounds(182, 11, 46, 14);
		contentPane.add(lblBuscar);
		
		JLabel lblBuscarPor = new JLabel("Buscar por: ");
		lblBuscarPor.setBounds(29, 140, 88, 14);
		contentPane.add(lblBuscarPor);
		
		tipo = new JComboBox();
		tipo.setBounds(142, 136, 101, 22);
		contentPane.add(tipo);
		
		JLabel lblTextoABuscar = new JLabel("Texto a buscar");
		lblTextoABuscar.setBounds(29, 223, 88, 14);
		contentPane.add(lblTextoABuscar);
		
		campoBuscar = new JTextField();
		campoBuscar.setBounds(142, 220, 101, 20);
		contentPane.add(campoBuscar);
		campoBuscar.setColumns(10);
		
		JButton btnBsucar = new JButton("Buscar");
		btnBsucar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				//CODIGO PARA EL BOTON
				
				try
                {
                 
                 Class.forName("org.sqlite.JDBC"); // CARGAMOS LA BASE DE DATOS    
                 Connection conexion = DriverManager.getConnection("jdbc:sqlite:BasesDatos/bdbusqueda.db"); // BUSCAMOS LA UBICACION DE LA BASE DE DATOS
                
                 Statement preparacion = (Statement) conexion.createStatement();
                 // AQUI SELECIONAMOS LOS DATOS QUE QUEREMOS MOSTRAR. 
                 // EL CODIGO (( like \'%"+buscartexto.getText()+"%\'" )) ES PARA BUSCAR EN LA BASE DE DATOS....  buscartexto ES EL NOMBRE DEL  Jtextfield DONDE VAMOS A DIGITAR LA CEDULA DEL CLIENTE A BUSCAR
                 ResultSet resultados = preparacion.executeQuery("select nombre, nit, direccion from Empresas Where " +tipo.getSelectedItem()+ " like \'%"+campoBuscar.getText()+"%\'");// 
                
                 
                 // ESTE ES EL ENCABEZADO QUE QUEREMOS QUE NOS MUESTRE CUANDO DAMOS CLICK EN BUSCAR.
                // String[] encabezado={"Nombre","nit","Direccion"}; 
                 
                 ResultSetMetaData metaDatos = resultados.getMetaData();
					
					//Llenado de encabezados dinamicamente
					int contadorColumnas = metaDatos.getColumnCount();
					String[] encabezados = new String[contadorColumnas];
					
					for(int i=0;i<contadorColumnas;i++)
					{
						encabezados[i]= metaDatos.getColumnName(i+1).toUpperCase();
					}
                 
                 
              
                modelo.setDataVector(null,encabezados);
                    
                 
                // Bucle para cada resultado en la consulta
                
                        while (resultados.next())
                        {
                           // Se crea un array que será una de las filas de la tabla.
                           Object [] fila = new Object[3]; // Hay tres columnas en la tabla

                           // Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
                           for (int i=0;i<3;i++)
                              fila[i] = resultados.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.

                           // Se añade al modelo la fila completa.
                           modelo.addRow(fila);
                        }
                        // Cierra la ventana
                preparacion.close();
                conexion.close();
                 
                 
                }
                catch(SQLException er)
                {
                    System.out.println("error:" + er.toString());
                }
                catch(ClassNotFoundException er)
                {
                    System.out.println("error:" + er.toString());
                }
				
				
				
			}
		});
		btnBsucar.setBounds(299, 136, 91, 23);
		contentPane.add(btnBsucar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(63, 346, 311, 140);
		contentPane.add(scrollPane);
		
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
		
		JLabel lblBsucarPorEmpresa = new JLabel("Bsucar por Empresa");
		lblBsucarPorEmpresa.setBounds(29, 68, 101, 14);
		contentPane.add(lblBsucarPorEmpresa);
		
		empresa = new JComboBox();
		empresa.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				//CODIGO PARA CAMBIAR EL ITEM....?
				
				int indice = empresa.getSelectedIndex();
				
				
				try{
					Class.forName("org.sqlite.JDBC");
					Connection conexion = DriverManager.getConnection("jdbc:sqlite:BasesDatos/bdbusqueda.db");
					Statement preparacion= conexion.createStatement();
					
					ResultSet resultados = preparacion.executeQuery("SELECT nombre,nit,direccion FROM Empresas WHERE id ="+codigos[indice]);
					
					ResultSetMetaData metaDatos = resultados.getMetaData();
					
					//Llenado de encabezados dinamicamente
					int contadorColumnas = metaDatos.getColumnCount();
					String[] encabezados = new String[contadorColumnas];
					
					for(int i=0;i<contadorColumnas;i++)
					{
						encabezados[i]= metaDatos.getColumnName(i+1).toUpperCase();
					}
					
					
					
					modelo.setDataVector(null,encabezados);
					
					while(resultados.next())
					{	
						Object fila[] = new Object [contadorColumnas];
						
						for (int i=0; i<fila.length;i++)
						{
							fila[i]=resultados.getObject(i+1);
						}
						modelo.addRow(fila);
					}
					
					preparacion.close();
					conexion.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
				
				
				
				
				
			}
		});
		empresa.setBounds(142, 64, 101, 22);
		contentPane.add(empresa);
		
		//CODIGO PARA LOS TIPOS
		
		
		try
		{         
			Class.forName("org.sqlite.JDBC");    // CARGAMOS LA BASE DE DATOS
			Connection conexion = DriverManager.getConnection("jdbc:sqlite:BasesDatos/bdbusqueda.db"); // BUSCAMOS LA UBICACION DE LA BASE DE DATOS
			      
			Statement preparacion = conexion.createStatement();
			ResultSet resultados = preparacion.executeQuery("select * from Empresas;");
			ResultSetMetaData metaDatos= resultados.getMetaData();
			
			int contadorColumnas= metaDatos.getColumnCount();
			
			for(int i=2; i<contadorColumnas+1;i++ )
				
			{
				tipo.addItem(metaDatos.getColumnName(i));
			}
			
			
			
			//comboBoxCiudad.setModel(new ResultSetComboBoxModel(resultados, "idCiudad", "nombreCiudad"));

			preparacion.close();
			conexion.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		
		
		//CODIGO PARA LLENAR POR EMPRESAS
		
		try
		{         
			Class.forName("org.sqlite.JDBC");    // CARGAMOS LA BASE DE DATOS
			Connection conexion = DriverManager.getConnection("jdbc:sqlite:BasesDatos/bdbusqueda.db"); // BUSCAMOS LA UBICACION DE LA BASE DE DATOS
			      
			Statement preparacion = conexion.createStatement();
			
			ResultSet resultados = preparacion.executeQuery("select count(*) from Empresas;");
			
			int contadorFilas=(int) resultados.getObject(1);
			
			codigos= new int [contadorFilas];
			
			
			
			
			resultados = preparacion.executeQuery("select * from Empresas;");
			int i=0;
			while(resultados.next())
			{
				empresa.addItem(""+resultados.getString("nombre"));
				codigos [i] = resultados.getInt("id");
				i++;
			}
			
			//comboBoxCiudad.setModel(new ResultSetComboBoxModel(resultados, "idCiudad", "nombreCiudad"));

			preparacion.close();
			conexion.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		
		
		
		
	}
}
