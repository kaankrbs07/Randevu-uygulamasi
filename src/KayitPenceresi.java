import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class KayitPenceresi extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTC;
	private JPasswordField txtSifre;

	public KayitPenceresi() {
		setTitle("Kayıt Ol");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTc = new JLabel("T.C. Kimlik Numarası:");
		lblTc.setBounds(10, 20, 150, 20);
		contentPane.add(lblTc);

		txtTC = new JTextField();
		txtTC.setBounds(160, 20, 120, 20);
		contentPane.add(txtTC);
		txtTC.setColumns(10);

		JLabel lblSifre = new JLabel("Şifre:");
		lblSifre.setBounds(10, 50, 150, 20);
		contentPane.add(lblSifre);

		txtSifre = new JPasswordField();
		txtSifre.setBounds(160, 50, 120, 20);
		contentPane.add(txtSifre);

		JButton btnKayit = new JButton("KAYIT OL");
		btnKayit.setBounds(80, 100, 120, 30);
		contentPane.add(btnKayit);

		btnKayit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tcNo = txtTC.getText().trim();
				String sifre = new String(txtSifre.getPassword()).trim();

				if (tcNo.length() == 11 && tcNo.matches("[0-9]+") && sifre.length() > 5) {

					Hasta yeniHasta = new Hasta(tcNo, sifre);

					JOptionPane.showMessageDialog(null, "Kayıt işlemi başarıyla tamamlandı.");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"Geçersiz bilgiler. T.C. kimlik numarası 11 haneli olmalı, şifre en az 6 karakterden oluşmalı.",
							"Geçersiz giriş", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}
}