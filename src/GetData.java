import javax.swing.*;

public class GetData extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;

	public GetData() {
		textArea = new JTextArea(20, 40);
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Okunan Veriler");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		DosyadanVeriOku dosyaOkuyucu = new DosyadanVeriOku();

		StringBuilder veriHasta = new StringBuilder();
		StringBuilder veriDoktor = new StringBuilder();

		String dosyaHasta = "hasta_bilgileri.ser";
		String dosyaDoktor = "doktor_bilgileri.ser";

		dosyaOkuyucu.dosyadanVeriOku(dosyaHasta, veriHasta);
		dosyaOkuyucu.dosyadanVeriOku(dosyaDoktor, veriDoktor);
		textArea.setText(veriHasta.toString() + "\n" + veriDoktor.toString());
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(GetData::new);
	}
}
