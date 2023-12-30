import java.io.*;

public class DosyadanVeriOku {

	void dosyadanVeriOku(String dosyaAdi, StringBuilder veri) {
		try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(dosyaAdi))) {
			veri.setLength(0);
			while (true) {
				try {
					Object obj = objIn.readObject();
					if (obj instanceof Hasta) {
						Hasta hasta = (Hasta) obj;
						veri.append("Hasta: TC: ").append(hasta.getTcNo()).append(" Şifre: ").append(hasta.getSifre())
								.append("\n");
					} else if (obj instanceof Doktor) {
						Doktor doktor = (Doktor) obj;
						veri.append("Doktor: TC: ").append(doktor.getTcNo()).append(" Şifre: ")
								.append(doktor.getSifre()).append("\n");
					}
				} catch (EOFException e) {
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
