package com.zubaray.programa2;

import java.awt.BorderLayout;



import java.sql.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;


public class App extends JFrame {

	private JPanel contentPane;
	private JTextField text_nombre;
	private JTextField text_edad;
	private JTextField text_profecion;
	private JTextField text_telefono;
	private JTextField text_buscar;
	private JLabel leyenda1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
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
	public App() {
		this.setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 682);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 76, 28);
		contentPane.add(lblNewLabel);
		
		text_nombre = new JTextField();
		text_nombre.setBounds(88, 16, 336, 20);
		contentPane.add(text_nombre);
		text_nombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Edad");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 49, 76, 28);
		contentPane.add(lblNewLabel_1);
		
		text_edad = new JTextField();
		text_edad.setBounds(88, 54, 336, 20);
		contentPane.add(text_edad);
		text_edad.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Profesion");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 89, 76, 28);
		contentPane.add(lblNewLabel_2);
		
		text_profecion = new JTextField();
		text_profecion.setBounds(88, 89, 336, 20);
		contentPane.add(text_profecion);
		text_profecion.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Telefono");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3.setBounds(10, 131, 76, 14);
		contentPane.add(lblNewLabel_3);
		
		text_telefono = new JTextField();
		text_telefono.setBounds(88, 129, 336, 20);
		contentPane.add(text_telefono);
		text_telefono.setColumns(10);
		
		JButton btnNewButton = new JButton("Registar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personas","root","");
					 // en el preparedstatement va el query sql
		             PreparedStatement pst = cn.prepareStatement("insert into persona values (?,?,?,?,?)"); //(id,nombre,edad,profecion,telefono) 
		             
		             pst.setString(1,"0");
		             pst.setString(2,text_nombre.getText().trim());
		             pst.setString(3,text_edad.getText().trim());
		             pst.setString(4,text_profecion.getText().trim());
		             pst.setString(5,text_telefono.getText().trim());
		             
		             //nos permite ejecutar las instrucciones que le enviamos a la base de datos.
		             pst.executeUpdate();
		                     
		             //limpiar campos
		             text_nombre.setText("");
		             text_edad.setText("");
		             text_profecion.setText("");
		             text_telefono.setText("");
		                     
		              // escribir en el ultimo label
		             leyenda1.setText("registro exitoso");
		             
		                     
		                     cn.close();
				} catch (Exception e2) {
					System.err.println("Error numero 1");
					e2.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnNewButton.setBounds(10, 244, 155, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modificar");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
		            
		            String ID = text_buscar.getText().trim();
		            
		            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personas","root","");
		            
		            PreparedStatement pst = cn.prepareStatement(" update persona set nombre = ?, edad = ?, profesion=?, telefono=? where id = " + ID);
		            
		             pst.setString(1,text_nombre.getText().trim() );		            
		             pst.setString(2,text_edad.getText().trim() );
		             pst.setString(3,text_profecion.getText().trim() );
		             pst.setString(4,text_telefono.getText().trim() );
		            
		            pst.executeUpdate();
		            
		            leyenda1.setText("Modificacion Exitosa!");
		            
		            cn.close();
		            
		        } catch (Exception e3) {
		            e3.printStackTrace();
		            System.err.println("Se rompe en el 3");
		        }
			}
		});
		btnNewButton_1.setBackground(new Color(0, 0, 0));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnNewButton_1.setBounds(216, 244, 129, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Eliminar");
		btnNewButton_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 try {
					 Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personas","root","");
			            
			            PreparedStatement pst = cn.prepareStatement(" delete from persona where ID = ?");
			            
			            pst.setString(1, text_buscar.getText().trim());
			            pst.executeUpdate();
			            
			            text_nombre.setText("");
			            text_edad.setText("");
			            text_profecion.setText("");
			            text_telefono.setText("");
			            text_buscar.setText("");
			            
			            
			            leyenda1.setText("Registro eliminado Correctamente.");
			            
			            cn.close();
			        } catch (Exception e4) {
			            e4.printStackTrace();
			        }
			}
		});
		btnNewButton_2.setBackground(SystemColor.desktop);
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnNewButton_2.setBounds(393, 244, 129, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_4 = new JLabel("Ingrese ID");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_4.setBounds(10, 333, 99, 20);
		contentPane.add(lblNewLabel_4);
		
		text_buscar = new JTextField();
		text_buscar.setBounds(100, 334, 86, 20);
		contentPane.add(text_buscar);
		text_buscar.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Buscar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
		            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personas","root","");
		             // en el preparedstatement va el query sql
		             PreparedStatement pst = cn.prepareStatement("select * from persona where id = ?");
		             
		             
		             pst.setString(1, text_buscar.getText().trim()); //metodo .getText() recupera el valor deñ campo
		             
		             //permite obtener el resultado de la bbdd si se encontaron los valores o no
		             ResultSet rs = pst.executeQuery();
		             
		             if(rs.next()){
		            	 text_nombre.setText(rs.getString("nombre"));
		            	 text_edad.setText(rs.getString("edad"));
		            	 text_profecion.setText(rs.getString("profesion"));
		            	 text_telefono.setText(rs.getString("telefono"));
		             } else {
		                 JOptionPane.showMessageDialog(null, "Alumno no registrado");
		             }
		             
		             cn.close();
		        } catch (Exception e2) {
		             e2.printStackTrace();
		               System.out.println("No se esta conectando 2");
		              
		        }
			}
		});
		btnNewButton_3.setBackground(Color.BLACK);
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnNewButton_3.setBounds(10, 506, 89, 23);
		contentPane.add(btnNewButton_3);
		
		leyenda1 = new JLabel("");
		leyenda1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		leyenda1.setBounds(45, 563, 565, 42);
		contentPane.add(leyenda1);
		
		
		
		
	}
}
