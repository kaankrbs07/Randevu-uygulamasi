
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DosyaIslemleri {

	public static void hastaBilgisiYaz(Hasta hasta) {
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("hasta_bilgileri.ser")))) {
			out.writeObject(hasta);
			System.out.println("Hasta bilgileri dosyaya yazıldı.");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void doktorBilgisiYaz(Doktor doktor) {
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("doktor_bilgileri.ser")))) {

			out.writeObject(doktor);
			System.out.println("Doktor bilgileri dosyaya yazıldı.");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
