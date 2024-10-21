package dao.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the stock database table.
 * 
 */
@Entity
@Table(name="Habilitation")
@NamedQuery(name="Habilitation.findAll", query="SELECT s FROM Habilitation s")
public class Habilitation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name="ID_MENU")
	private Menu  menu;
	
	@ManyToOne
	@JoinColumn(name="ID_UTILISATEUR")
	private Utilisateur utilisateur;

	@Column(name="AUTORISE")
	private boolean autorise;
	
	

	public Habilitation() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public boolean isAutorise() {
		return autorise;
	}

	public void setAutorise(boolean autorise) {
		this.autorise = autorise;
	}

	
}