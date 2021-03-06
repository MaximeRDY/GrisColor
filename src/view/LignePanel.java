package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

import transferable.ImportColor;
import transferable.TransferColor;
/**
 * Classe qui gere les differentes ligne de couleur
 * @author Maxime Raverdy et Damien Level
 *
 */
public class LignePanel extends JPanel {
	private JTextField textField;
	public JPanel labelColor;
	public JPanel labelGris;
	public JPanel labelColorMod;
	public JPanel labelGrisMod;
	public JTextField codeRGB;
	private JButton moins;
	private ChoiceColorVue parent;
	private JPanel ligne;
	
	/**
	 * Constructeur qui instancie une ligne panel
	 * @param parent
	 * @param color
	 */
	public LignePanel(ChoiceColorVue parent, Color color) {
		this.parent = parent;

		moins = new JButton("-");
		moins.addActionListener(new SupLignePanel());

		textField = new JTextField();
		textField.setMinimumSize(new Dimension(100, 20));
		textField.setMinimumSize(new Dimension(100, 20));
		textField.setMaximumSize(new Dimension(100, 20));

		labelColor = new JPanel();
		labelColor.setPreferredSize(new Dimension(35, 35));
		labelColor.setMinimumSize(new Dimension(35, 35));
		labelColor.setMaximumSize(new Dimension(35, 35));
		labelColor.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		labelColor.setTransferHandler(new ImportColor());

		labelGris = new JPanel();
		labelGris.setPreferredSize(new Dimension(35, 35));
		labelGris.setMinimumSize(new Dimension(35, 35));
		labelGris.setMaximumSize(new Dimension(35, 35));
		labelGris.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		labelColorMod = new JPanel();
		labelColorMod.setPreferredSize(new Dimension(35, 35));
		labelColorMod.setMinimumSize(new Dimension(35, 35));
		labelColorMod.setMaximumSize(new Dimension(35, 35));
		
		
		labelGrisMod = new JPanel();
		labelGrisMod.setPreferredSize(new Dimension(35, 35));
		labelGrisMod.setMinimumSize(new Dimension(35,35));
		labelGrisMod.setMaximumSize(new Dimension(35,35));
		
		
		codeRGB = new JTextField();
		codeRGB.setMinimumSize(new Dimension(50,20));
		codeRGB.setMinimumSize(new Dimension(50,20));
		codeRGB.setMaximumSize(new Dimension(50,20));
		codeRGB.setEditable(false);
		codeRGB.setVisible(false);
		
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		labelColor.addMouseListener(new ColorListener());

		addColor(color);

		labelColorMod.setTransferHandler(new TransferColor());
		labelColorMod.addMouseMotionListener(new ColorMouseListener());

		this.add(Box.createRigidArea(new Dimension(20, 0)));
		this.add(moins);
		this.add(Box.createRigidArea(new Dimension(40, 0)));
		this.add(textField);
		this.add(Box.createRigidArea(new Dimension(40, 0)));
		this.add(labelColor);
		this.add(Box.createRigidArea(new Dimension(40, 0)));
		this.add(labelGris);
		this.add(Box.createRigidArea(new Dimension(60, 0)));
		this.add(labelColorMod);
		this.add(Box.createRigidArea(new Dimension(40, 0)));
		this.add(labelGrisMod);
		this.add(Box.createRigidArea(new Dimension(40,0)));
		this.add(codeRGB);
		

		this.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		this.ligne = this;
	}

	/**
	 * Action relative au bouton "-" qui supprime la ligne
	 */
	class SupLignePanel extends AbstractAction {
		public void actionPerformed(ActionEvent arg0) {
			parent.getPanelHaut().supLigne(ligne);
		}
	}
	
	/**
	 * Methode qui permet l'ajout de la couleur "color" dans "labelColor" et de son niveau de gris dans "labelGris"
	 * @param color
	 */
	public void addColor(Color color){
			labelColor.setBackground(color);
			int niveaugris = getGris(labelColor.getBackground());
			labelGris.setBackground(new Color(niveaugris,niveaugris,niveaugris));
	}

	/**
	 * Methode qui retourne la valeur du niveau de gris d'une couleur
	 * @param c
	 * @return
	 */
	public int getGris(Color c) {
		return (int) (0.3 * c.getRed() + 0.59 * c.getGreen() + 0.11 * c
				.getBlue());
	}

	/**
	 * Methode qui permet la modification d'une couleur
	 */
	public void modColor() {
		Color color = JColorChooser.showDialog(new JFrame(),
				"Choose your color", labelColor.getBackground());
		if (color != null)
			addColor(color);
	}

	/**
	 * Methode qui permet d'inserer les couleurs modifiees proposees par l'algo
	 * @param cc
	 * @param cg
	 */
	public void insertColorMod(Color cc, Color cg) {
		labelColorMod.setBackground(cc);
		labelGrisMod.setBackground(cg);
		labelColorMod.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		labelGrisMod.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	/**
	 * Classe pour appeller le drag and drop de la couleur vers un code hexa
	 * @author Maxime Raverdy et Damien Level
	 *
	 */
	class ColorMouseListener extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent e) {
			Object o = (Object) e.getSource();
			
			JPanel object = (JPanel) o;
			object.getTransferHandler().exportAsDrag(object, e,
					TransferHandler.COPY);
		}
	}

	/**
	 * Classe qui implements le listenet lors d'un clic sur la couleur ajoutee par l'utilisateur
	 */
	class ColorListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			modColor();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
