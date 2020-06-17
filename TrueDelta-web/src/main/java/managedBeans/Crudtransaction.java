package managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entities.*;
import implementations.TransactionServiceImp;

@ManagedBean(name= "crudtransaction")
@SessionScoped
public class Crudtransaction implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Date date;
	private String type; // Achat ou vente
    private float rendementTransaction ;
    private Boolean isValide;
    private long numcompte ;
    private int scoreTransaction;
    private ActifFinancier actif;
    private Transaction transaction ;
    private Compte compte ;
    
	private List<Transaction> transactions;
	private List<Compte> comptes ;
	private List<ActifFinancier> actifs ;
	private List<String> typesT = Arrays.asList("achat", "vente");
	private List<String> valides = Arrays.asList("true", "false");
	@EJB
	TransactionServiceImp ts;
	public void ajouterTransaction() {
		Transaction t = new Transaction();
		
		SimpleDateFormat dateformat=new SimpleDateFormat("dd/mm/yyyy");
		Date date = new Date();
		DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(
		DateFormat.MEDIUM,
		DateFormat.MEDIUM);
		t.setDate(date);
		t.setActif(actif);
		t.setNumcompte(numcompte);
		t.setIsValide(isValide);
		t.setRendementTransaction(rendementTransaction);
		t.setScoreTransaction(scoreTransaction);
		t.setType(type);
	ts.ajouterTransaction(t); }
	
	private Integer  transactionupdated;
	public String doRemove() {
		try {ts.removeTransaction(id);
		}  catch (Exception e) {
			
		
		FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("Transaction est supprimée aves succés"));}
		
	return "accueil?faces-redirect=true";}
	public void doUpdate() {
		Transaction t = ts.getTransactionById(id);
		t.setType(type);
		t.setNumcompte(numcompte);
	
		
		
		ts.updateTransaction(t);
	}
	public void displayTransaction(Transaction trans)
	{
	this.setId(trans.getId());
	this.setDate(trans.getDate());
	this.setIsValide(trans.getIsValide());
	this.setType(trans.getType());
	this.setNumcompte(trans.getNumcompte());
	this.setRendementTransaction(trans.getRendementTransaction());
	this.setScoreTransaction(trans.getScoreTransaction());
	this.setActif(trans.getActif());
	}
	public void doAffecterTA(int actifId, int transactionId) throws IOException {
		 ts.affecterActifTransaction(actifId, transactionId);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getRendementTransaction() {
		return rendementTransaction;
	}
	public void setRendementTransaction(float rendementTransaction) {
		this.rendementTransaction = rendementTransaction;
	}
	public Boolean getIsValide() {
		return isValide;
	}
	public void setIsValide(Boolean isValide) {
		this.isValide = isValide;
	}
	public long getNumcompte() {
		return numcompte;
	}
	public void setNumcompte(long numcompte) {
		this.numcompte = numcompte;
	}
	public int getScoreTransaction() {
		return scoreTransaction;
	}
	public void setScoreTransaction(int scoreTransaction) {
		this.scoreTransaction = scoreTransaction;
	}
	
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	public List<Transaction> getTransactions() {
		transactions = ts.consulterTransactions();
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public List<Compte> getComptes() {
		comptes = ts.consulterCompte();
		return comptes;
	}
	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}
	public List<ActifFinancier> getActifs() {
		actifs = ts.consulterActifs();
		return actifs;
	}
	public void setActifs(List<ActifFinancier> actifs) {
		this.actifs = actifs;
	}
	public TransactionServiceImp getTs() {
		return ts;
	}
	public void setTs(TransactionServiceImp ts) {
		this.ts = ts;
	}
	public Integer getTransactionupdated() {
		return transactionupdated;
	}
	public void setTransactionupdated(Integer transactionupdated) {
		this.transactionupdated = transactionupdated;
	}
	public ActifFinancier getActif() {
		return actif;
	}
	public void setActif(ActifFinancier actif) {
		this.actif = actif;
	}
	public List<String> getTypesT() {
		return typesT;
	}
	public void setTypesT(List<String> typesT) {
		this.typesT = typesT;
	}
	public List<String> getValides() {
		return valides;
	}
	public void setValides(List<String> valides) {
		this.valides = valides;
	}
	
	public void chercherTbyId() {
		ts.getTransactionById(id);
	}
}