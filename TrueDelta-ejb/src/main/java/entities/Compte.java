package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Compte
 *
 */
@Entity
public class Compte implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ComptePK id;
	
	@Column(unique=true)
	private String numeroCompte;
	
	private float solde;
	
	@ManyToOne
	@JoinColumn(name="idClient", referencedColumnName="utilisateurID", insertable=false , updatable=false, unique=false)
	private Client proprietaire;
	
	@ManyToOne
	@JoinColumn(name="idAgence", referencedColumnName="agenceID", insertable=false , updatable=false,unique=false)
	private Agence agence;
	
	@OneToMany(mappedBy="compte", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<ActifFinancier> actifs= new ArrayList<ActifFinancier>();
	
	@Enumerated(EnumType.STRING)
	private Devise devise;
	@Column(nullable=true)
	private int nbAction;
	@Column(nullable=true)
	private int nbObligation;
	
	private Boolean isAutorise; // Autorisation par la banque
	private Boolean isVerifie; // Verification par l'admin
	@Temporal(TemporalType.DATE)
	@Column(nullable=true)
	private Date lastVerif;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=true,name="date_ouverture", updatable=false)
	private Date dateOuverture;
	
	public Compte() {}

	public ComptePK getId() {
		return id;
	}

	public void setId(ComptePK id) {
		this.id = id;
	}

	public String getNumeroCompte() {
		return numeroCompte;
	}

	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	public float getSolde() {
		return solde;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}

	public Client getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Client proprietaire) {
		this.proprietaire = proprietaire;
	}

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	public Devise getDevise() {
		return devise;
	}

	public void setDevise(Devise devise) {
		this.devise = devise;
	}

	public int getNbAction() {
		return nbAction;
	}

	public void setNbAction(int nbAction) {
		this.nbAction = nbAction;
	}

	public int getNbObligation() {
		return nbObligation;
	}

	public void setNbObligation(int nbObligation) {
		this.nbObligation = nbObligation;
	}

	public Boolean getIsAutorise() {
		return isAutorise;
	}

	public void setIsAutorise(Boolean isAutorise) {
		this.isAutorise = isAutorise;
	}

	public Boolean getIsVerifie() {
		return isVerifie;
	}

	public void setIsVerifie(Boolean isVerifie) {
		this.isVerifie = isVerifie;
	}

	public Date getLastVerif() {
		return lastVerif;
	}

	public void setLastVerif(Date lastVerif) {
		this.lastVerif = lastVerif;
	}

	public Date getDateOuverture() {
		return dateOuverture;
	}

	public void setDateOuverture(Date dateOuverture) {
		this.dateOuverture = dateOuverture;
	}

	
	public List<ActifFinancier> getActifs() {
		return actifs;
	}

	public void setActifs(List<ActifFinancier> actifs) {
		this.actifs = actifs;
	}

	@Override
	public String toString() {
		return "Compte [numeroCompte=" + numeroCompte + ", solde=" + solde + "]";
	}
}
