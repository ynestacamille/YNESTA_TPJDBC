package com.mycompany.app;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Etudiant implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numEt;
	private String nomEt = new String() ;
	private String prenomEt = new String();
	private String cpEt = new String();
	private String villeEt = new String();
	private int annee = 0;
	private int groupe = 0; 
	private Set<Enseignement> eEnseignement = new HashSet<>();
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + annee;
		result = prime * result + ((cpEt == null) ? 0 : cpEt.hashCode());
		result = prime * result
				+ ((eEnseignement == null) ? 0 : eEnseignement.hashCode());
		result = prime * result + groupe;
		result = prime * result + ((nomEt == null) ? 0 : nomEt.hashCode());
		result = prime * result + numEt;
		result = prime * result
				+ ((prenomEt == null) ? 0 : prenomEt.hashCode());
		result = prime * result + ((villeEt == null) ? 0 : villeEt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etudiant other = (Etudiant) obj;
		if (annee != other.annee)
			return false;
		if (cpEt == null) {
			if (other.cpEt != null)
				return false;
		} else if (!cpEt.equals(other.cpEt))
			return false;
		if (eEnseignement == null) {
			if (other.eEnseignement != null)
				return false;
		} else if (!eEnseignement.equals(other.eEnseignement))
			return false;
		if (groupe != other.groupe)
			return false;
		if (nomEt == null) {
			if (other.nomEt != null)
				return false;
		} else if (!nomEt.equals(other.nomEt))
			return false;
		if (numEt != other.numEt)
			return false;
		if (prenomEt == null) {
			if (other.prenomEt != null)
				return false;
		} else if (!prenomEt.equals(other.prenomEt))
			return false;
		if (villeEt == null) {
			if (other.villeEt != null)
				return false;
		} else if (!villeEt.equals(other.villeEt))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Etudiant: numEt=" + numEt + ", nomEt=" + nomEt + ", prenomEt="
				+ prenomEt + ", cpEt=" + cpEt + ", villeEt=" + villeEt
				+ ", annee=" + annee + ", groupe=" + groupe
				+ ", eEnseignement=" + eEnseignement;
	}

	public int getNumEt() {
		return numEt;
	}

	public void setNumEt(int numEt) {
		this.numEt = numEt;
	}

	public String getNomEt() {
		return nomEt;
	}

	public void setNomEt(String nomEt) {
		this.nomEt = nomEt;
	}

	public String getPrenomEt() {
		return prenomEt;
	}

	public void setPrenomEt(String prenomEt) {
		this.prenomEt = prenomEt;
	}

	public String getCpEt() {
		return cpEt;
	}

	public void setCpEt(String cpEt) {
		this.cpEt = cpEt;
	}

	public String getVilleEt() {
		return villeEt;
	}

	public void setVilleEt(String villeEt) {
		this.villeEt = villeEt;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public int getGroupe() {
		return groupe;
	}

	public void setGroupe(int groupe) {
		this.groupe = groupe;
	}

	public Set<Enseignement> geteEnseignement() {
		return eEnseignement;
	}

	public void seteEnseignement(Set<Enseignement> eEnseignement) {
		this.eEnseignement = eEnseignement;
	}

}
