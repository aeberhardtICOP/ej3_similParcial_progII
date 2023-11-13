package igu;

import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import service.IntegranteService;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import model.Cargos;
import model.Funciones;
import model.Organismo;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class ABMIntegrante extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblIntegrantes;
	private DefaultTableModel model;
	private JTextField txtNroLegajo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JTextField txtEdad;
	private JTextField txtAñoNac;
	private JComboBox cmbxTipo;
	private JFormattedTextField ftxtFechaPosecion;
	private JCheckBox chkbxAcuerdoLegis;
	private JComboBox cmbxCargo;
	private JComboBox cmbxFuncion;
	private JCheckBox chkbxAfiliado;
	private JFormattedTextField ftxtFechaIngreso;
	private JButton btnCancelar;
	private JButton btnGuardar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMIntegrante frame = new ABMIntegrante(new IntegranteService());
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
	public ABMIntegrante(IntegranteService intser) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 955, 646);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelAltaInt = new JPanel();
		panelAltaInt.setBorder(new TitledBorder(null, "Carga Integrante", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAltaInt.setBounds(10, 11, 919, 358);
		contentPane.add(panelAltaInt);
		panelAltaInt.setLayout(null);
		
		txtNroLegajo = new JTextField();
		txtNroLegajo.setBounds(34, 42, 133, 20);
		panelAltaInt.add(txtNroLegajo);
		txtNroLegajo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(34, 97, 86, 20);
		panelAltaInt.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(34, 168, 86, 20);
		panelAltaInt.add(txtApellido);
		
		txtDni = new JTextField();
		txtDni.setBounds(34, 239, 86, 20);
		panelAltaInt.add(txtDni);
		txtDni.setColumns(10);
		
		JLabel lblNroLeg = new JLabel("Nro. Legajo:");
		lblNroLeg.setBounds(34, 27, 74, 14);
		panelAltaInt.add(lblNroLeg);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(34, 83, 54, 14);
		panelAltaInt.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(34, 153, 74, 14);
		panelAltaInt.add(lblApellido);
		
		JLabel lblDni = new JLabel("Dni:");
		lblDni.setBounds(34, 223, 46, 14);
		panelAltaInt.add(lblDni);
		
		txtEdad = new JTextField();
		txtEdad.setBounds(180, 97, 86, 20);
		panelAltaInt.add(txtEdad);
		txtEdad.setColumns(10);
		
		txtAñoNac = new JTextField();
		txtAñoNac.setBounds(180, 168, 86, 20);
		panelAltaInt.add(txtAñoNac);
		txtAñoNac.setColumns(10);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(180, 83, 66, 14);
		panelAltaInt.add(lblEdad);
		
		JLabel lblAñoNac = new JLabel("Año");
		lblAñoNac.setBounds(180, 153, 111, 14);
		panelAltaInt.add(lblAñoNac);
		
		cmbxTipo = new JComboBox();
		cmbxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cmbxTipo.getSelectedItem().equals("Funcionario")) {
					ftxtFechaPosecion.setEnabled(true);
					chkbxAcuerdoLegis.setEnabled(true);
					cmbxCargo.setEnabled(true);
					ftxtFechaIngreso.setEnabled(false);
					ftxtFechaIngreso.setText("");
					cmbxFuncion.setEnabled(false);
					chkbxAfiliado.setEnabled(false);
					chkbxAfiliado.setSelected(false);
				}else {
					ftxtFechaPosecion.setEnabled(false);
					ftxtFechaPosecion.setText("");
					chkbxAcuerdoLegis.setEnabled(false);
					chkbxAcuerdoLegis.setSelected(false);
					cmbxCargo.setEnabled(false);
					ftxtFechaIngreso.setEnabled(true);
					cmbxFuncion.setEnabled(true);
					chkbxAfiliado.setEnabled(true);
				}
			}
		});
		cmbxTipo.setModel(new DefaultComboBoxModel(new String[] {"Funcionario", "Empleado"}));
		cmbxTipo.setBounds(216, 41, 86, 22);
		panelAltaInt.add(cmbxTipo);
		
		JComboBox cmbxOrganizacion = new JComboBox();
		cmbxOrganizacion.setBounds(180, 238, 86, 22);
		panelAltaInt.add(cmbxOrganizacion);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(216, 27, 46, 14);
		panelAltaInt.add(lblTipo);
		
		JLabel lblOrganizacion = new JLabel("Organismo:");
		lblOrganizacion.setBounds(180, 223, 86, 14);
		panelAltaInt.add(lblOrganizacion);
		
		JComboBox cmbxCircunscripcion = new JComboBox();
		cmbxCircunscripcion.setModel(new DefaultComboBoxModel(new String[] {"Santa Fe", "Rafaela", "Reconquista"}));
		cmbxCircunscripcion.setBounds(363, 41, 86, 22);
		panelAltaInt.add(cmbxCircunscripcion);
		
		JLabel lblCircunscripcion = new JLabel("Circunscripcion:");
		lblCircunscripcion.setBounds(363, 27, 86, 14);
		panelAltaInt.add(lblCircunscripcion);
		
		ftxtFechaPosecion = new JFormattedTextField();
		ftxtFechaPosecion.setBounds(375, 97, 86, 20);
		panelAltaInt.add(ftxtFechaPosecion);

		try {
		    MaskFormatter dateFormatter = new MaskFormatter("##/##/####");
		    dateFormatter.setPlaceholderCharacter('_'); // Carácter de marcador de posición
		    ftxtFechaPosecion.setFormatterFactory(new DefaultFormatterFactory(dateFormatter));
		} catch (ParseException e) {
		    e.printStackTrace();
		}

		
		JLabel lblFechaPosecion = new JLabel("Fecha Posecion:");
		lblFechaPosecion.setBounds(375, 83, 86, 14);
		panelAltaInt.add(lblFechaPosecion);
		
		chkbxAcuerdoLegis = new JCheckBox("Acuerdo Legislativo?");
		chkbxAcuerdoLegis.setBounds(363, 167, 123, 23);
		panelAltaInt.add(chkbxAcuerdoLegis);
		
		cmbxCargo = new JComboBox<>(Cargos.values());
		cmbxCargo.setBounds(375, 238, 86, 22);
		panelAltaInt.add(cmbxCargo);
		
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setBounds(375, 223, 46, 14);
		panelAltaInt.add(lblCargo);
		
		chkbxAfiliado = new JCheckBox("Es Afiliado?");
		chkbxAfiliado.setBounds(555, 167, 97, 23);
		panelAltaInt.add(chkbxAfiliado);
		
		JLabel lblFechaIngreso = new JLabel("Fecha Ingreso:");
		lblFechaIngreso.setBounds(555, 83, 88, 14);
		panelAltaInt.add(lblFechaIngreso);
		
		cmbxFuncion = new JComboBox(Funciones.values());
		cmbxFuncion.setBounds(555, 238, 86, 22);
		panelAltaInt.add(cmbxFuncion);
		
		JLabel lblFuncion = new JLabel("Funcion:");
		lblFuncion.setBounds(555, 223, 46, 14);
		panelAltaInt.add(lblFuncion);
		
		ftxtFechaIngreso = new JFormattedTextField();
		ftxtFechaIngreso.setBounds(555, 97, 86, 20);
		panelAltaInt.add(ftxtFechaIngreso);
		try {
		    MaskFormatter dateFormatter = new MaskFormatter("##/##/####");
		    dateFormatter.setPlaceholderCharacter('_'); // Carácter de marcador de posición
		    DefaultFormatterFactory formatterFactory = new DefaultFormatterFactory(dateFormatter);

		    ftxtFechaIngreso.setFormatterFactory(formatterFactory);    
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		btnGuardar = new JButton("Guardar");
	    btnGuardar.setBounds(779, 297, 111, 34);
	    panelAltaInt.add(btnGuardar);
	    btnGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nroLegajo=txtNroLegajo.getText();
				String tipo = cmbxTipo.getSelectedItem().toString();
				String circunscripcion=cmbxCircunscripcion.getSelectedItem().toString();
				String nombre = txtNombre.getText();
				String apellido = txtApellido.getText();
				String dni = txtDni.getText();
				String edad = txtEdad.getText();
				String añoNacimiento = txtAñoNac.getText();
				Organismo organismo = null;
				String fechaPosecion = ftxtFechaPosecion.getText();
				boolean acuerdoLegislativo = chkbxAcuerdoLegis.isSelected();
				String cargo = cmbxCargo.getSelectedItem().toString();
				String fechaIngresoString = ftxtFechaIngreso.getText();
				boolean afiliado = chkbxAfiliado.isSelected();
				String funcion = cmbxFuncion.getSelectedItem().toString();
				
				intser.crearIntegrante(nroLegajo, tipo, circunscripcion, nombre, apellido, dni, edad, añoNacimiento, organismo, fechaPosecion, acuerdoLegislativo, cargo, fechaIngresoString, afiliado, funcion);
				
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
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 380, 919, 216);
		contentPane.add(scrollPane);
		
		tblIntegrantes = new JTable();
        model = new DefaultTableModel(){
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	}
        };
        tblIntegrantes.setModel(model);
        tblIntegrantes.setRowHeight(30);
        model.addColumn("NRO LEGAJO");
        model.addColumn("NOMBRE");
        model.addColumn("APELLIDO");
        model.addColumn("TIPO");
        model.addColumn("DNI");
        model.addColumn("AÑO NACIMIENTO");
        model.addColumn("ES AFILIADO");
        model.addColumn("FUNCION");
        model.addColumn("ACUERDO LEGIS.");
        model.addColumn("CARGO");
        
      
        scrollPane.setViewportView(tblIntegrantes);
	}
}
