package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private String ruta;
	private Document documentoInforme;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
					Principal miPrincipal = new Principal();
					miPrincipal.crearPdf();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws DocumentException 
	 * @throws FileNotFoundException 
	 */
	public Principal() throws FileNotFoundException, DocumentException
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				
			}
		});
		btnNewButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnNewButton.setBounds(142, 103, 148, 75);
		contentPane.add(btnNewButton);
		
	
		
	
	}
		
		
	
	
	public void crearPdf() throws DocumentException, MalformedURLException, IOException
	{
		
		// CREAR EL PDF CON SUS TEXTOS Y PAGINAS
		
		String ruta = "InformesPDF/hola.pdf";

		
		
		PdfWriter.getInstance(documentoInforme , new FileOutputStream(ruta));
		
		 documentoInforme.open();
		  
		 documentoInforme.add(new Paragraph("hola mundo!"));
		 
		 documentoInforme.newPage();
	       
		 
		 documentoInforme.add(new Paragraph("segunga pagina!"));
		 this.lineaVacia(documentoInforme, 2);
		 documentoInforme.add(new Paragraph("segunga palabra!"));
		 
		 
		 // CODIGO PARA AÑADIR IMAGEN AL PDF
		 
		
			 Image foto = Image.getInstance("Imagenes/sena.jpg"); 
		 
		 foto.scaleToFit(100, 100);
		 foto.setAlignment(Chunk.ALIGN_MIDDLE); 
		 documentoInforme.add(foto);
		
	
		 
		 this.lineaVacia(documentoInforme, 2);
		
		 		// CREAR TABLA
		 
		 		PdfPTable tabla = new PdfPTable(3);
		 		
				tabla.addCell("Identificación ");
				tabla.addCell("NOMBRE");
				tabla.addCell("APELLIDOS");
				
				tabla.addCell("1.0");
			    tabla.addCell("1.1");
			    tabla.addCell("1.2");
			    tabla.addCell("2.1");
			    tabla.addCell("2.2");
			    tabla.addCell("2.3");
			    
			    documentoInforme.add(tabla);
			    
			    documentoInforme.close();
		
	}
	
	
	// FUNCION PARA AGREGAR ESPACIOS
	
	 private static void lineaVacia(Document informeDocumento, int number) throws DocumentException 
     {
                for (int i = 0; i < number; i++) 
                {
                  informeDocumento.add(new Paragraph(" "));
                }
     }
	 
	 
	
	
	
	
}
