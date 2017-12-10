package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.JobAttributes;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.*;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.MouseEvent;
import java.util.Calendar;

public class jFrameTP3 extends JFrame {

	//attibuts de la classe
	private static final long serialVersionUID = -5406745872685262568L;// pour empêcher un warning
	private Connection conn;
	private String login = "C##PHTRE57";
	private String password = "bd111132090";	
	private String username;
	private String userPassword;
	private int noLogins = 2;
	private int isAdmin = 0;//bool pour savoir si admin
	private int isSup = 0;//bool pour savoir si superviseur
	private List<String> listeProjet;
	
	private boolean boolAjout = false;
	private boolean boolMaj = false;
	
	//composantes du frame
	private JPanel contentPane;
	private JTextField textFieldNom;
	private JTextField textFieldMnt;
	private JTextField textFieldDateD;
	private JTextField textFieldDateF;
	private JList<?> list;
	private JComboBox<String> comboBox;
	private JButton btnRechercher;
	private JButton btnAnnuler;
	private JButton btnNewButton;
	private JButton btnQuitter;
	private JButton btnAjouter;
	private JButton btnMettreAJour;
	private JButton btnArchiver;
	private JTextArea textArea;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jFrameTP3 frame = new jFrameTP3();
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
	public jFrameTP3() {
		
		//les attributs de toutes les composantes dans le frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("CRIPE");
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProjets = new JLabel("Projets:");
		lblProjets.setBounds(6, 6, 61, 16);
		contentPane.add(lblProjets);
		
		btnRechercher = new JButton("Rechercher...");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rechercherAction();
			}
		});
		btnRechercher.setBounds(120, 1, 117, 29);
		contentPane.add(btnRechercher);
		
		JLabel lblNo = new JLabel("No");
		lblNo.setBounds(6, 46, 61, 16);
		contentPane.add(lblNo);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(97, 46, 61, 16);
		contentPane.add(lblNom);
		
		JLabel lblMontant = new JLabel("Montant");
		lblMontant.setBounds(446, 46, 61, 16);
		contentPane.add(lblMontant);
		
		JLabel lblStatut = new JLabel("Statut");
		lblStatut.setBounds(549, 46, 61, 16);
		contentPane.add(lblStatut);
		
		JLabel lblDateDebut = new JLabel("Date debut");
		lblDateDebut.setBounds(642, 46, 75, 16);
		contentPane.add(lblDateDebut);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 65, 738, 104);
		contentPane.add(scrollPane);
		
		JLabel lblNom_1 = new JLabel("Nom: **");
		lblNom_1.setBounds(6, 197, 61, 16);
		contentPane.add(lblNom_1);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(97, 192, 647, 36);
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);
		
		JLabel lblMembres = new JLabel("Membres:");
		lblMembres.setBounds(6, 253, 61, 16);
		contentPane.add(lblMembres);
		
		textArea = new JTextArea();
		textArea.setBounds(98, 253, 348, 153);
		contentPane.add(textArea);
		textArea.setEditable(false);
		
		JLabel lblStatut_1 = new JLabel("Statut **:");
		lblStatut_1.setBounds(458, 253, 132, 16);
		contentPane.add(lblStatut_1);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(602, 249, 142, 36);
		contentPane.add(comboBox);
		comboBox.addItem("en attente");
		comboBox.addItem("approuve");
		comboBox.addItem("terminee");
		
		JLabel lblMontantAllouee = new JLabel("Montant allouee **:");
		lblMontantAllouee.setBounds(458, 302, 132, 16);
		contentPane.add(lblMontantAllouee);
		
		textFieldMnt = new JTextField();
		textFieldMnt.setBounds(602, 297, 142, 33);
		contentPane.add(textFieldMnt);
		textFieldMnt.setColumns(10);
		
		JLabel lblDateDebut_1 = new JLabel("Date debut **:");
		lblDateDebut_1.setBounds(458, 340, 132, 16);
		contentPane.add(lblDateDebut_1);
		
		textFieldDateD = new JTextField();
		textFieldDateD.setBounds(602, 335, 142, 36);
		contentPane.add(textFieldDateD);
		textFieldDateD.setColumns(10);
		
		JLabel lblDateFin = new JLabel("Date fin:");
		lblDateFin.setBounds(458, 390, 132, 16);
		contentPane.add(lblDateFin);
		
		textFieldDateF = new JTextField();
		textFieldDateF.setBounds(602, 380, 142, 36);
		contentPane.add(textFieldDateF);
		textFieldDateF.setColumns(10);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				annulerAction();
			}
		});
		btnAnnuler.setBounds(578, 426, 83, 29);
		contentPane.add(btnAnnuler);
		
		btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionOk();
			}
		});
		btnNewButton.setBounds(661, 426, 83, 29);
		contentPane.add(btnNewButton);
		
		btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitterApp();
			}
		});
		btnQuitter.setBounds(120, 426, 90, 29);
		contentPane.add(btnQuitter);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prepAjouterProjet();
			}
		});
		btnAjouter.setBounds(210, 426, 90, 29);
		contentPane.add(btnAjouter);
		
		btnMettreAJour = new JButton("Mettre a jour");
		btnMettreAJour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prepMajProjet();
			}
		});
		btnMettreAJour.setBounds(304, 426, 106, 29);
		contentPane.add(btnMettreAJour);
		
		btnArchiver = new JButton("Archiver...");
		btnArchiver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				archiverAction();
			}
		});
		btnArchiver.setBounds(408, 426, 90, 29);
		contentPane.add(btnArchiver);
		
		disableMajBtns();
		
		list = new JList<>();
		scrollPane.setViewportView(list);
		list.setFont(new Font("Courier New", Font.PLAIN, 14));
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
		renderer.setVerticalAlignment(SwingConstants.CENTER);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String projetChoisi = (String) list.getSelectedValue();
				projetChoisi = projetChoisi.substring(0, 4);
				affichageProjet(projetChoisi);
				enbaleBtnExceptOkCancel();
				disableMaj();
								
			}
		});
		
		//on tente de se connecter a la bd
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@ift-p-ora12c.fsg.ulaval.ca:1521:ora12c", login, password);
			seConnecter();
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
		
	}
	
	private void seConnecter() {
		username = JOptionPane.showInputDialog("Entrez votre nom d'utilisateur");
		userPassword = JOptionPane.showInputDialog("Entrez votre mot de passe");
		try {
			CallableStatement stmt = conn.prepareCall("{? = call FCT_MEM_EXISTS(?, ?)}");
			stmt.setString(2, username);
			stmt.setString(3, userPassword);
			stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
			stmt.executeQuery();
		
			if (!stmt.getString(1).isEmpty()) {
				System.out.println("Connexion ok");	
			}
			else {
				System.out.println("Connexion failed");
			}
		}
		catch (Exception ex) {
			if (noLogins <= 0) {
				JOptionPane.showMessageDialog(this, "Vous avez fait 3 tentatives de connection infructueuses l'application se fermera.");
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			}
			else {
				JOptionPane.showMessageDialog(this, "La combinaison nom utilisateur/mot de passe est invalide");
				noLogins--;
				seConnecter();
			}
		}
		
		try {
			CallableStatement stmt1 = conn.prepareCall("{? = call FCT_MEM_IS_ADMIN(?, ?)}");
			stmt1.setString(2, username);
			stmt1.setString(3, userPassword);
			stmt1.registerOutParameter(1, java.sql.Types.VARCHAR);
			stmt1.executeQuery();
			isAdmin = Integer.parseInt(stmt1.getString(1));
			
			CallableStatement stmt2 = conn.prepareCall("{? = call FCT_MEM_IS_SUP(?, ?)}");
			stmt2.setString(2, username);
			stmt2.setString(3, userPassword);
			stmt2.registerOutParameter(1, java.sql.Types.VARCHAR);
			stmt2.executeQuery();
			isSup = Integer.parseInt(stmt2.getString(1));
			
			if (isAdmin == 1 || isSup == 1){
				System.out.println("Droits user ok");
				populateJList();
			}
			else {
				if (noLogins <= 0) {
					JOptionPane.showMessageDialog(this, "Vous avez fait 3 tentatives de connection infructueuses l'application se fermera.");
					this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
				}
				else {
					JOptionPane.showMessageDialog(this, "Vous n'avez pas les dtoits pour utiliser l'application");
					noLogins--;
					seConnecter();
				}
			}
			
		} catch(Exception ex1) {
			System.out.println(ex1.getMessage());
			JOptionPane.showMessageDialog(this, "Erreur fatale");
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
			
	}
	
	private void populateJList() {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from TP2_PROJET order by DATE_DEBUT_PRO");
			listeProjet = new ArrayList<String>();
			listeProjet.clear();
			while (rs.next()) {
				String noPro = rs.getString("NO_PROJET");
				String nomPro = rs.getString("NOM_PRO");
				String mntPro = rs.getString("MNT_ALLOUE_PRO");
				String statutPro = rs.getString("STATUT_PRO");
				String dateD = rs.getString("DATE_DEBUT_PRO").substring(0, 10);
				char[] tempNo = new char[7];
				char[] tempNom = new char[43 - nomPro.length()];
				char[] tempMnt = new char[12 - mntPro.length()];
				char[] tempStatut = new char[14 - statutPro.length()];
				Arrays.fill(tempNo, ' ');
				Arrays.fill(tempNom, ' ');
				Arrays.fill(tempMnt, ' ');
				Arrays.fill(tempStatut, ' ');
				noPro = noPro + new String(tempNo);
				nomPro = nomPro + new String(tempNom);
				mntPro = mntPro + new String(tempMnt);
				statutPro = statutPro + new String(tempStatut);
				
				listeProjet.add(noPro + nomPro + mntPro + statutPro + dateD);
			}
			
			String[] tempTab = new String[listeProjet.size()];
			listeProjet.toArray(tempTab);
			list.setModel(new AbstractListModel() {
				String[] valeurs = tempTab;
				public int getSize() {
					return valeurs.length;
				}
				public Object getElementAt(int index) {
					return valeurs[index];
				}
			});
			
			
			
		}
		catch (SQLException sqlEx) {
			System.out.println(sqlEx.getMessage() + " " + "during Jlist populating");
		}
	}
	
	private void quitterApp() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
	private void rechercherAction() {
		String nomPro = JOptionPane.showInputDialog("Entrez le nom complet ou partiel du projet que vous recherchez:");
		String preMem = JOptionPane.showInputDialog("Entrez le prenom complet ou partiel du membre:");
		String nomMem = JOptionPane.showInputDialog("Entrez le nom complet ou partiel du membre:");
		try {
			CallableStatement stmt = conn.prepareCall("{? = call FCT_RECHERCHE_PROJET(?, ?, ?)}");
			stmt.setString(2, nomPro);
			stmt.setString(3, preMem);
			stmt.setString(4, nomMem);
			stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
			stmt.executeQuery();
			String noPro = stmt.getString(1);
			affichageProjet(noPro);
			
			int index = -1;
			for (int i = 0; i < listeProjet.size(); i++) {
				if (listeProjet.get(i).substring(0, 5).contains(noPro)) {
					index = i;
				}
			}
			
			try {
				list.setSelectedIndex(index);
			}
			catch(Exception ex1) {
				System.out.println(ex1.getMessage());
			}
						
		}
		catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage());
		}
	}
	
	private void affichageProjet(String projetChoisi) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from TP2_PROJET where NO_PROJET = " +  projetChoisi);
			if (rs.next()) {
				textFieldNom.setText(rs.getString("NOM_PRO"));
				textFieldMnt.setText(rs.getString("MNT_ALLOUE_PRO"));
				textFieldDateD.setText(rs.getString("DATE_DEBUT_PRO").substring(0, 10));
				try
				{
					String tempDateF = rs.getString("DATE_FIN_PRO").substring(0, 10);
					textFieldDateF.setText(tempDateF);
				}
				catch (Exception ex) {
					textFieldDateF.setText("");
				}
				comboBox.setSelectedItem(rs.getString("STATUT_PRO"));
			}
		}
		catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		//affichage des membres
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from TP2_EQUIPE_PROJET where NO_PROJET = " +  projetChoisi);
			String temp = "";
			ArrayList<String> tempList = new ArrayList<>();
			while (rs.next()) {
				int estDir = Integer.parseInt(rs.getString("EST_DIRECTEUR_PRO"));
				if (estDir == 1) {
					Statement stmt1 = conn.createStatement();
					ResultSet rs1 = stmt1.executeQuery("select NOM_MEM, PRENOM_MEM from TP2_MEMBRE where NO_MEMBRE = " +  rs.getString("NO_MEMBRE"));
					if (rs1.next()) {
						 tempList.add(rs1.getString("PRENOM_MEM") + " " + rs1.getString("NOM_MEM") + " *" + "\n");
					}
				}
				else {
					Statement stmt1 = conn.createStatement();
					ResultSet rs1 = stmt1.executeQuery("select NOM_MEM, PRENOM_MEM from TP2_MEMBRE where NO_MEMBRE = " +  rs.getString("NO_MEMBRE"));
					if (rs1.next()) {
						tempList.add(rs1.getString("PRENOM_MEM") + " " + rs1.getString("NOM_MEM") + "\n");
					}
				}
			}
			
			ArrayList<String> tempList1 = new ArrayList<>();
			for (int i = 0; i < tempList.size(); i++) {
				String tempStr = tempList.get(i);
				if (tempStr.contains("*")){
					tempList1.add(tempStr);
					tempList.remove(i);
				}
			}
			
			tempList1.addAll(tempList);
			
			for (int i = 0; i < tempList1.size(); i++) {
				temp += tempList1.get(i);
			}
			textArea.setText(temp);
		}
		catch (SQLException ex1) {
			System.out.print(ex1.getMessage() + "during populating members");
		}
		
	}
	
	private void prepAjouterProjet() {
		setTextFieldsEmpty();
		enableMaj();
		disableBtnExceptOkCancel();
		boolAjout = true;
	}
	
	private void prepMajProjet() {
		enableMaj();
		disableBtnExceptOkCancel();
		boolMaj = true;
	}
	
	private void actionOk() {
		if (boolAjout) {
			if (!majFieldsEmpty()) {
				String nomProjet = textFieldNom.getText();
				String statutProjet = (String)comboBox.getSelectedItem();
				String mntAlloueProjet = textFieldMnt.getText();
				String dateD = textFieldDateD.getText();
				String dateF = textFieldDateF.getText();
				try {
					Statement stmt = conn.createStatement();
					if (dateF.isEmpty()) {
						String tempStr = "INSERT INTO TP2_PROJET"
								+ " VALUES (SQ_PROJET.nextval, '" + nomProjet + "', " + 
								mntAlloueProjet + ", '" + statutProjet + "', to_date('" + dateD + "', 'yy-mm-dd'), null)";
						stmt.executeUpdate(tempStr);
					}
					else {
						String tempStr = "INSERT INTO TP2_PROJET"
								+ " VALUES (SQ_PROJET.nextval, '" + nomProjet + "', " + 
								mntAlloueProjet + ", '" + statutProjet + "', to_date('" + dateD + "', 'yy-mm-dd'), to_date('" +
								dateF + "', 'yy-mm-dd'))";
						System.out.println(tempStr);
						stmt.executeUpdate(tempStr);
					}
					JOptionPane.showMessageDialog(this, "Projet " + nomProjet + " ajoute!");
					disableMaj();
					enbaleBtnExceptOkCancel();
				}
				catch (SQLException sqlEx) {
					JOptionPane.showMessageDialog(this, sqlEx.getMessage());
					System.out.println(sqlEx.getMessage());
					enbaleBtnExceptOkCancel();
					disableMaj();
					try {
						String projetChoisi = (String) list.getSelectedValue();
						projetChoisi = projetChoisi.substring(0, 4);
						affichageProjet(projetChoisi);
					}
					catch(Exception ex) {
						
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Veuillez remplir les champs requis ** et reessayer");
				try {
					String projetChoisi = (String) list.getSelectedValue();
					projetChoisi = projetChoisi.substring(0, 4);
					affichageProjet(projetChoisi);
				}
				catch(Exception ex) {
					
				}
				enbaleBtnExceptOkCancel();
			}
			
			boolAjout = false;
			
		}
		else if (boolMaj){
			if (!majFieldsEmpty()) {
				String nomProjet = textFieldNom.getText();
				String statutProjet = (String)comboBox.getSelectedItem();
				String mntAlloueProjet = textFieldMnt.getText();
				String dateD = textFieldDateD.getText();
				String dateF = textFieldDateF.getText();
				String projetChoisi = (String) list.getSelectedValue();
				projetChoisi = projetChoisi.substring(0, 4);
				try {
					Statement stmt = conn.createStatement();
					if (dateF.isEmpty()) {
						String tempStr = "UPDATE TP2_PROJET SET NOM_PRO = '" + nomProjet + "', " + "MNT_ALLOUE_PRO = " +
								mntAlloueProjet + ", STATUT_PRO = '" + statutProjet + "', "
										+ "DATE_DEBUT_PRO = to_date('" + dateD + "', 'yy-mm-dd') where NO_PROJET = " + projetChoisi;
						stmt.executeUpdate(tempStr);
					}
					else {
						String tempStr = "UPDATE TP2_PROJET SET NOM_PRO = '" + nomProjet + "', " + "MNT_ALLOUE_PRO = " +
								mntAlloueProjet + ", STATUT_PRO = '" + statutProjet + "', DATE_DEBUT_PRO = to_date('" + dateD + "', 'yy-mm-dd'), "
										+ "DATE_FIN_PRO = to_date('" + dateF + "', 'yy-mm-dd') where NO_PROJET = " + projetChoisi;
						stmt.executeUpdate(tempStr);
					}
					JOptionPane.showMessageDialog(this, "Projet " + nomProjet + " modifie!");
					disableMaj();
					enbaleBtnExceptOkCancel();
				}
				catch (SQLException sqlEx) {
					JOptionPane.showMessageDialog(this, sqlEx.getMessage());
					System.out.println(sqlEx.getMessage());
					enbaleBtnExceptOkCancel();
					disableMaj();
					try {
						String projetChoisi1 = (String) list.getSelectedValue();
						projetChoisi1 = projetChoisi1.substring(0, 4);
						affichageProjet(projetChoisi1);
					}
					catch(Exception ex) {
						
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Veuillez remplir les champs requis ** et reessayer");
				String projetChoisi = (String) list.getSelectedValue();
				projetChoisi = projetChoisi.substring(0, 4);
				affichageProjet(projetChoisi);
				disableMaj();
				enbaleBtnExceptOkCancel();
			}
			
			boolMaj = false;
		}
		
		populateJList();
		
	}
	
	private void annulerAction() {
		boolMaj = false;
		boolAjout = false;
		disableMaj();
		enbaleBtnExceptOkCancel();
		try {
			String projetChoisi = (String) list.getSelectedValue();
			projetChoisi = projetChoisi.substring(0, 4);
			affichageProjet(projetChoisi);
		}
		catch(Exception ex) {
			
		}
		
		
	}
	
	private void archiverAction() {
		String dateStr = JOptionPane.showInputDialog("Entrez une date vielle de plus de deux ans au format aaaa-mm-jj: ");
		Calendar today = Calendar.getInstance();
		Calendar date = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		try {
			int annee = Integer.parseInt(dateStr.substring(0, 4));
			int mois = Integer.parseInt(dateStr.substring(5, 7));
			int jour = Integer.parseInt(dateStr.substring(8, 10));
			
			date.set(annee, mois, jour);
			today.add(Calendar.YEAR, -2);
			
			if (date.before(today)) {
				try {
					CallableStatement stmt1 = conn.prepareCall("{call SP_ARCHIVER_PROJET(?)}");
					stmt1.setDate(1, java.sql.Date.valueOf(dateStr));
					stmt1.executeQuery();
					JOptionPane.showMessageDialog(this, "Les projets dont la date de fin est anterieur a " + dateStr + " ont ete "
							+ "archives");
				}
				catch (SQLException sqlEx) {
					JOptionPane.showMessageDialog(this, sqlEx.getMessage());
				}
				
			}
			else {
				JOptionPane.showMessageDialog(this, "La date n'est pas plus vieille que deux ans en date d'aujourd'hui");
			}
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(this, "Le format de date entree n'est pas le bon");
		}
		
		populateJList();
		disableMaj();
		enbaleBtnExceptOkCancel();
		
	}
	
	private boolean majFieldsEmpty() {
		return (textFieldDateD.getText().isEmpty() || textFieldMnt.getText().isEmpty() || textFieldMnt.getText().isEmpty()
				|| textFieldNom.getText().isEmpty());
	}
	
	private void setTextFieldsEmpty() {
		textFieldDateD.setText("");
		textFieldDateF.setText("");
		textFieldMnt.setText("");
		textFieldNom.setText("");
		textArea.setText("");
	}
	
	private void setTextFieldsEmptyExceptMembers() {
		textFieldDateD.setText("");
		textFieldDateF.setText("");
		textFieldMnt.setText("");
		textFieldNom.setText("");
	}
	
	private void disableMajBtns() {
		btnMettreAJour.setEnabled(false);
		btnNewButton.setEnabled(false);
		btnAnnuler.setEnabled(false);
		textFieldDateD.setEditable(false);
		textFieldMnt.setEditable(false);
		comboBox.setEnabled(false);
		textFieldNom.setEditable(false);
		textFieldDateF.setEditable(false);
	}
	
	private void enableMajBtns() {
		btnMettreAJour.setEnabled(true);
		btnNewButton.setEnabled(true);
		btnAnnuler.setEnabled(true);
		textFieldDateD.setEditable(true);
		textFieldMnt.setEditable(true);
		comboBox.setEnabled(true);
		textFieldNom.setEditable(true);
		textFieldDateF.setEditable(true);	
	}
	
	private void enableMaj() {
		textFieldDateD.setEditable(true);
		textFieldMnt.setEditable(true);
		comboBox.setEnabled(true);
		textFieldNom.setEditable(true);
		textFieldDateF.setEditable(true);
	}
	
	private void disableMaj() {
		textFieldDateD.setEditable(false);
		textFieldMnt.setEditable(false);
		comboBox.setEnabled(false);
		textFieldNom.setEditable(false);
		textFieldDateF.setEditable(false);
	}
	
	private void disableBtnExceptOkCancel() {
		btnAjouter.setEnabled(false);
		btnArchiver.setEnabled(false);
		btnMettreAJour.setEnabled(false);
		btnQuitter.setEnabled(false);
		btnRechercher.setEnabled(false);
		
		btnAnnuler.setEnabled(true);
		btnNewButton.setEnabled(true);
	}
	
	private void enbaleBtnExceptOkCancel() {
		btnAjouter.setEnabled(true);
		btnArchiver.setEnabled(true);
		btnMettreAJour.setEnabled(true);
		btnQuitter.setEnabled(true);
		btnRechercher.setEnabled(true);
		
		btnAnnuler.setEnabled(false);
		btnNewButton.setEnabled(false);
	}
	
	
	
}
