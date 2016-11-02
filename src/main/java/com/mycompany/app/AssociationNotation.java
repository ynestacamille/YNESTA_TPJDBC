package com.mycompany.app;

import java.util.HashSet;
import java.util.Set;



public class AssociationNotation {
	private Set<Lien> liens = new HashSet<>();
	private static AssociationNotation instance;
	public void creerLien (Module mod, Etudiant et, Notation note){
		Lien lien = new Lien(mod, et);
		lien.setNotation(note);
		liens.add(lien);
	}
	
	public void supprimerLien(Module mod, Etudiant et){
		for(Lien l : liens)
			if(l.getModule() == mod && l.getEtudiant() == et)
				liens.remove(l);
	}
	
	public void SupprimerLien (Lien lien){

		liens.remove(lien);
	}
	
	public Lien getLien (Module mod, Etudiant et){
		Lien l = new Lien(mod, et);
		return l;
		
	}
	
	public Set<Lien> getLiens (Etudiant et){
		Set<Lien> LienEt = new HashSet<Lien>();
		for(Lien l : liens)
			if(l.getEtudiant().equals(et))
				LienEt.add(l);
		return LienEt;
	}
	
	
	public Set<Lien> getLiens (Module mod){
		Set<Lien> LienMod = new HashSet<Lien>();
		for(Lien l : liens)
			if(l.getModule().equals(mod))
				LienMod.add(l);
		return LienMod;
	}
	
	public Set<Module> getModules (Etudiant et){
		Set<Module> module = new HashSet<Module>();
		for(Lien l : liens )
			if(l.getEtudiant() == et) module.add(l.getModule());
		return module;		
	}
	
	public Set<Etudiant> getEtudiants(Module mod){
		Set<Etudiant> etudiants = new HashSet<Etudiant>();
		for(Lien l : liens )
			if(l.getModule() == mod) etudiants.add(l.getEtudiant());
		return etudiants;
	}
	
	public static AssociationNotation getInstance (){
		return instance;
	}
	
	
	
}
