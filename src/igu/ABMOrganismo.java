package igu;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import service.OrganizacionService;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import model.Cargos;
import model.Empleado;
import model.Funcionario;
import model.Integrante;
import model.Organismo;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTable;

public class ABMOrganismo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private OrganizacionService orgser;
	private JTextField txtDomicilio;
	private JTextField txtNombre;
	private JScrollPane scrollPane;
	private JButton btnCancelar;
	private JFormattedTextField ftxtFechaCreacion;
	private JTable tblOrganismos;
	private DefaultTableModel model;
	private JButton btnGuardar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMOrganismo frame = new ABMOrganismo(new OrganizacionService());
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
	public ABMOrganismo(OrganizacionService orgser) {
		setTitle("ABMOrganismo");
		this.orgser=orgser;
		orgser.organismosAMemoria();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 955, 646);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelAltaInt = new JPanel();
		panelAltaInt.setLayout(null);
		panelAltaInt.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Carga Organismo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelAltaInt.setBounds(10, 11, 919, 358);
		contentPane.add(panelAltaInt);
		
		txtDomicilio = new JTextField();
		txtDomicilio.setColumns(10);
		txtDomicilio.setBounds(484, 38, 260, 34);
		panelAltaInt.add(txtDomicilio);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(29, 38, 260, 34);
		panelAltaInt.add(txtNombre);
		
		JLabel lblDomicilio = new JLabel("Domicilio:");
		lblDomicilio.setBounds(484, 21, 74, 14);
		panelAltaInt.add(lblDomicilio);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(34, 21, 54, 14);
		panelAltaInt.add(lblNombre);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(779, 297, 111, 34);
		panelAltaInt.add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre=txtNombre.getText();
				String fechaCreacion=ftxtFechaCreacion.getText();
				String domicilio=txtDomicilio.getText();
				
				orgser.crearOrganismo(nombre, fechaCreacion, domicilio);
				vaciarTabla(model.getRowCount());
				llenarTabla();
				
				txtNombre.setText("");
				ftxtFechaCreacion.setText("");
				txtDomicilio.setText("");
				
			}
		});
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(658, 297, 111, 34);
		panelAltaInt.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		} );
		ftxtFechaCreacion = new JFormattedTextField();
		ftxtFechaCreacion.setBounds(29, 182, 252, 34);
		panelAltaInt.add(ftxtFechaCreacion);
		try {
		    MaskFormatter dateFormatter = new MaskFormatter("##/##/####");
		    dateFormatter.setPlaceholderCharacter('_'); 
		    DefaultFormatterFactory formatterFactory = new DefaultFormatterFactory(dateFormatter);

		    ftxtFechaCreacion.setFormatterFactory(formatterFactory);    
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		JLabel lblFechaCreacion = new JLabel("Fecha Creacion:");
		lblFechaCreacion.setBounds(29, 165, 141, 14);
		panelAltaInt.add(lblFechaCreacion);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 380, 919, 216);
		contentPane.add(scrollPane);
		
		tblOrganismos = new JTable();
		model = new DefaultTableModel(){
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	}
        };
        tblOrganismos.setModel(model);
        tblOrganismos.setRowHeight(30);
        model.addColumn("ID"); //0
        model.addColumn("NOMBRE"); //1
        model.addColumn("FECHA CREACION"); //2
        model.addColumn("DOMICILIO"); //3
        
        llenarTabla();
        scrollPane.setViewportView(tblOrganismos);
	}
	
	public void llenarTabla() {
		
		HashMap<Long, Organismo>organismos=orgser.obtenerOrganismos();
		for (Map.Entry<Long, Organismo> entry : organismos.entrySet()) {
			Object[] fila = new Object[10];
			final Long id=entry.getKey();
			final Organismo org=entry.getValue();
			fila[0]=org.getId();
			fila[1]=org.getNombre();
			fila[2]=org.getFechaCreaciom();
			fila[3]=org.getDomicilio();
			model.addRow(fila); 
		}
		tblOrganismos.revalidate();
		tblOrganismos.repaint();
	}
	public void vaciarTabla(int cantFilas) {
		for (int i = cantFilas - 1; i >= 0; i--) {
	        model.removeRow(i);
	    }
	}
}
