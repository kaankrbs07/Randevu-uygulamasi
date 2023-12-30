import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTabbedPane w_tabpane;
	private JPasswordField fld_hastatcno;
	private JPasswordField fld_hastasifre;
	private JPasswordField fld_doktortc;
	private JPasswordField fld_doktorsifre;

	private String[] hastaliklar = { "Epilepsi", "Diyabet", "Mide Bulantısı", "Romatizma", "Hipertansiyon",
			"Cilt Problemleri", "Kemik ve Eklem Ağrıları","Depresyon","Kanser","Böbrek taşı","Tüberküloz" };
	private String[] poliklinikler = { "Nöroloji", "Endokrinoloji", "Gastroenteroloji", "Romatoloji", "Kardiyoloji",
			"Dermatoloji", "Ortopedi","Psikiyatri","Onkoloji","Üroloji","Göğüs hastalıkları" };
	private String[] gunler = { "Pazartesi", "Salı", "Çarşamba", "Perşembe", "Cuma" };

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginGUI() {
		setTitle("Hastane Otomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 525);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("medical.logo.jpg")));
		lbl_logo.setBounds(142, 0, 194, 189);
		w_pane.add(lbl_logo);

		w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 257, 478, 235);
		w_pane.add(w_tabpane);

		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Hasta Girişi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);

		JLabel lbl_hastatc = new JLabel("T.C NUMARANIZ:");
		lbl_hastatc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lbl_hastatc.setBounds(10, 10, 178, 29);
		w_hastaLogin.add(lbl_hastatc);

		JLabel lbl_hastasifre = new JLabel("ŞİFRE :");
		lbl_hastasifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lbl_hastasifre.setBounds(10, 70, 178, 29);
		w_hastaLogin.add(lbl_hastasifre);

		fld_hastatcno = new JPasswordField();
		fld_hastatcno.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
		fld_hastatcno.setBounds(245, 10, 203, 29);
		w_hastaLogin.add(fld_hastatcno);

		fld_hastasifre = new JPasswordField();
		fld_hastasifre.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
		fld_hastasifre.setBounds(245, 70, 203, 29);
		w_hastaLogin.add(fld_hastasifre);

		JButton btn_hastakayit = new JButton("KAYIT OL");
		btn_hastakayit.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		btn_hastakayit.setBounds(10, 137, 178, 42);
		w_hastaLogin.add(btn_hastakayit);

		btn_hastakayit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				KayitPenceresi kayitPenceresi = new KayitPenceresi();
				kayitPenceresi.setVisible(true);
			}
		});

		JButton btn_hastagiris = new JButton("GİRİŞ YAP");
		btn_hastagiris.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		btn_hastagiris.setBounds(263, 137, 178, 42);
		w_hastaLogin.add(btn_hastagiris);

		btn_hastagiris.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String hastatcno = fld_hastatcno.getText().trim();
				String hastasifre = fld_hastasifre.getText().trim();
				Hasta hasta = Hasta(hastatcno, hastasifre);
				if (hastatcno.length() == 11 && hastatcno.matches("[0-9]+") && hastasifre.length() > 5) {
					DosyaIslemleri.hastaBilgisiYaz(hasta);
				} else {
					JOptionPane.showMessageDialog(null,
							"Geçersiz bilgiler.Lütfen tcnizi doğru ve şifrenizi en az 6 karakter girin.",
							"Tc'nizi doğru giriniz.", JOptionPane.WARNING_MESSAGE);
					return;
				}

			}

		});

		btn_hastagiris.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(0, 1));

				JComboBox<String> comboBoxHastaliklar = new JComboBox<>(hastaliklar);
				panel.add(new JLabel("Hastalık Seçiniz:"));
				panel.add(comboBoxHastaliklar);

				int result = JOptionPane.showConfirmDialog(null, panel, "Hastalık Seçimi", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {
					int selectedHastalikIndex = comboBoxHastaliklar.getSelectedIndex();
					String selectedPoliklinik = poliklinikler[selectedHastalikIndex];

					JPanel panel2 = new JPanel();
					panel2.setLayout(new GridLayout(0, 1));

					JComboBox<String> comboBoxGunler = new JComboBox<>(gunler);
					panel2.add(new JLabel("Randevu Günü Seçiniz:"));
					panel2.add(comboBoxGunler);

					int result2 = JOptionPane.showConfirmDialog(null, panel2, "Gün Seçimi",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

					if (result2 == JOptionPane.OK_OPTION) {
						String selectedGun = (String) comboBoxGunler.getSelectedItem();

						JOptionPane.showMessageDialog(null, "Seçilen Hastalık: " + hastaliklar[selectedHastalikIndex]
								+ "\nPoliklinik: " + selectedPoliklinik + "\nSeçilen Gün: " + selectedGun);
					}

				}
			}

		});

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		w_tabpane.addTab("Doktor Girişi", null, panel, null);
		panel.setLayout(null);

		JLabel lbl_doktortc = new JLabel("T.C NUMARANIZ:");
		lbl_doktortc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lbl_doktortc.setBounds(10, 10, 178, 29);
		panel.add(lbl_doktortc);

		fld_doktortc = new JPasswordField();
		fld_doktortc.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
		fld_doktortc.setBounds(245, 10, 203, 29);
		panel.add(fld_doktortc);

		JLabel lbl_doktorsifre = new JLabel("ŞİFRE :");
		lbl_doktorsifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lbl_doktorsifre.setBounds(10, 70, 178, 29);
		panel.add(lbl_doktorsifre);

		fld_doktorsifre = new JPasswordField();
		fld_doktorsifre.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
		fld_doktorsifre.setBounds(245, 70, 203, 29);
		panel.add(fld_doktorsifre);

		JButton btn_doktorgiris = new JButton("GİRİŞ YAP");
		btn_doktorgiris.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		btn_doktorgiris.setBounds(117, 132, 203, 66);
		panel.add(btn_doktorgiris);

		btn_doktorgiris.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String doktorTc = fld_doktortc.getText().trim();
				String doktorSifre = fld_doktorsifre.getText().trim();
				Doktor doktor = Doktor(doktorTc, doktorSifre);

				if (doktorTc.length() == 11 && doktorTc.matches("[0-9]+") && doktorSifre.length() > 5) {
					JOptionPane.showMessageDialog(null, "doktor girişi yapıldı");
					DosyaIslemleri.doktorBilgisiYaz(doktor);
				} else {
					JOptionPane.showMessageDialog(null,
							"Geçersiz bilgiler.Lütfen tcnizi doğru ve şifrenizi en az 6 karakter girin.",
							"Tc'nizi doğru giriniz.", JOptionPane.WARNING_MESSAGE);
					return;
				}

			}
		});

		JLabel lblNewLabel = new JLabel("Hastane Yönetim Sistemine Hoşgeldiniz");
		lblNewLabel.setBounds(36, 199, 406, 29);
		w_pane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));

	}

	protected Doktor Doktor(String doktortc, String doktorsifre) {
		Doktor doktor = new Doktor(doktortc, doktorsifre);
		return doktor;
	}

	protected Hasta Hasta(String hastatcno, String hastasifre) {
		Hasta hasta = new Hasta(hastatcno, hastasifre);
		return hasta;
	}
}