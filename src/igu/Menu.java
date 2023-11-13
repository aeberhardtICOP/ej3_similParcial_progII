package igu;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import service.IntegranteService;
import service.OrganizacionService;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private OrganizacionService orgser;
	private IntegranteService intser;

	
	public Menu(OrganizacionService orgser, IntegranteService intser) {
		this.orgser=orgser;
		this.intser=intser;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 946, 631);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("Inicio");
		menuBar.add(mnInicio);
		
		JMenuItem mnitmABMint = new JMenuItem("ABM Integrante");
		mnInicio.add(mnitmABMint);
		mnitmABMint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ABMIntegrante abm = new ABMIntegrante(intser);
				setEnabled(false);
				abm.addWindowListener(new WindowAdapter() {
					@Override
		            public void windowClosed(WindowEvent e) {
		                setEnabled(true);
						}
					});
				abm.setVisible(true);
			}
		});
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnInicio.add(mntmSalir);
		mntmSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JMenu mnuInfo = new JMenu("Info");
		menuBar.add(mnuInfo);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de..");
		mnuInfo.add(mntmAcercaDe);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	}

}
