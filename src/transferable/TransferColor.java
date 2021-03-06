package transferable;

import java.awt.Color;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
/**
 * Classe pour transférer une couleur vers un code hexadécimale
 */
public class TransferColor extends TransferHandler {
	public int getSourceActions(JComponent c) {
		return COPY;
	}

	/**
	 * Fonction qui traduit une couleur vers un code de type "#FFFF"
	 */
	protected Transferable createTransferable(JComponent c) {
		Color color = ((JPanel) c).getBackground();
		String rgb = Integer.toHexString(color.getRGB());
		rgb = rgb.substring(2, rgb.length());
		return new StringSelection("#"+rgb);
	}

	public boolean canImport(TransferHandler.TransferSupport support) {
		return false;
	}
	
	public boolean importData(TransferHandler.TransferSupport support) {
		return false;
	}
}
