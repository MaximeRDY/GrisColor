package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Classe qui gere le haut de la frame
 * @author Maxime Raverdy et Damien Level
 *
 */
public class PanelHaut extends JPanel{
	/**
	 * compteur : Compte le nombre de couleur affiché
	 * ligneColors: Enregistre toutes les lignes affichées
	 * parent : la frame parent, retourne la frame principal
	 * lineTitle : retourne le ligne titre
	 */
	private int compteur;
	private ArrayList<LignePanel> ligneColors;
	private ChoiceColorVue parent;
	private JPanel lineTitle;
	public JLabel newColor;
	public JLabel newGris;
	
	
	/**
	 * Construit la haut de la frame, appelle les differentes fonctions 
	 * @param parent la frame parent 
	 */
	public PanelHaut(ChoiceColorVue parent){
		this.parent = parent;
		compteur = 0;
		ligneColors = new ArrayList<LignePanel>();
		JPanel lineTitle = new JPanel();
		
		this.setPreferredSize(new Dimension(800,500));
		this.setMinimumSize(new Dimension(800,500));
		this.setMaximumSize(new Dimension(800,500));
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.setBorder(BorderFactory.createTitledBorder("Choose Color"));
	}
	
	/**
	 * Ajoute la ligne de titre pour les differents panel
	 */
	private void AddTitleLine(){
		JLabel name = new JLabel("Name");
		name.setPreferredSize(new Dimension(60, 10));
		name.setMinimumSize(new Dimension(60,10));
		name.setMaximumSize(new Dimension(60,10));
		
		JLabel color = new JLabel("Color");
		color.setPreferredSize(new Dimension(60, 10));
		color.setMinimumSize(new Dimension(60,10));
		color.setMaximumSize(new Dimension(60,10));
		
		JLabel gris = new JLabel("Grayscale");
		gris.setPreferredSize(new Dimension(70, 15));
		gris.setMinimumSize(new Dimension(70,15));
		gris.setMaximumSize(new Dimension(70,15));
		
		newColor = new JLabel("New color");
		newColor.setPreferredSize(new Dimension(70, 10));
		newColor.setMinimumSize(new Dimension(70,10));
		newColor.setMaximumSize(new Dimension(70,10));
		newColor.setVisible(false);
		
		newGris = new JLabel("Grayscale");
		newGris.setPreferredSize(new Dimension(80, 15));
		newGris.setMinimumSize(new Dimension(80,15));
		newGris.setMaximumSize(new Dimension(80,15));
		newGris.setVisible(false);
		
		lineTitle = new JPanel();
		lineTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
		lineTitle.setLayout(new BoxLayout(lineTitle, BoxLayout.X_AXIS));
		
		
		lineTitle.add(Box.createRigidArea(new Dimension(130,0)));
		lineTitle.add(name);
		lineTitle.add(Box.createRigidArea(new Dimension(50,0)));
		lineTitle.add(color);
		lineTitle.add(Box.createRigidArea(new Dimension(3,0)));
		lineTitle.add(gris);
		lineTitle.add(Box.createRigidArea(new Dimension(20,0)));
		lineTitle.add(newColor);
		lineTitle.add(Box.createRigidArea(new Dimension(10,0)));
		lineTitle.add(newGris);
		
		lineTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		this.add(lineTitle);
	
	}

	/**
	 * Ajoute une ligne de couleur
	 */
	public void addLine() {
		if(compteur == 0){
			this.AddTitleLine();
		}
		Color color = JColorChooser.showDialog(new JFrame(), "Choose your Colors", null);
		if(color != null){
			LignePanel panel = new LignePanel(parent, color);
			this.add(panel);
			this.ligneColors.add(panel); 
			this.revalidate();
			compteur ++;
			if(compteur == 2){
				parent.getPanelBas().buttonModifAuto.setEnabled(true);
				parent.getMenu().menuItemMod.setEnabled(true);
			}else if(compteur == 10){
				parent.getPanelBas().buttonAdd.setEnabled(true);
				parent.getMenu().menuItemAdd.setEnabled(false);
			}
			parent.getPanelBas().slider.setEnabled(false);

		}
	}
	
	/**
	 * Supprime une ligne de couleur
	 * @param ligne la ligne qu'il faut supprimer
	 */
	public void supLigne(JPanel ligne){
		this.remove(ligne);
		ligneColors.remove(ligne);
		this.revalidate();
		this.repaint();
		compteur--;
		if(compteur == 1){
			parent.getPanelBas().buttonModifAuto.setEnabled(false);
			parent.getMenu().menuItemMod.setEnabled(false);
		}else if(compteur == 9){
			parent.getPanelBas().buttonAdd.setEnabled(true);
			parent.getMenu().menuItemAdd.setEnabled(true);
		}else if (compteur == 0){
			this.remove(lineTitle);
		}
		parent.getPanelBas().slider.setEnabled(false);
	}
	
	/**
	 * Retourne toutes les lignes de couleur
	 * @return
	 */
	public ArrayList<LignePanel> getLines(){
		return ligneColors;
	}
}
