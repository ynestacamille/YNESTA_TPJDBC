package com.mycompany.app;


public class Notation {
	private Etudiant etudiant;
	private Module module;
	private float MoyCC;
	private float MoyTest;
	public float getMoyCC() {
		return MoyCC;
	}
	public void setMoyCC(float moyCC) {
		MoyCC = moyCC;
	}
	public float getMoyTest() {
		return MoyTest;
	}
	public void setMoyTest(float moyTest) {
		MoyTest = moyTest;
	}
	@Override
	public String toString() {
		return "Notation [MoyCC=" + MoyCC + ", MoyTest=" + MoyTest + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(MoyCC);
		result = prime * result + Float.floatToIntBits(MoyTest);
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
		Notation other = (Notation) obj;
		if (Float.floatToIntBits(MoyCC) != Float.floatToIntBits(other.MoyCC))
			return false;
		if (Float.floatToIntBits(MoyTest) != Float
				.floatToIntBits(other.MoyTest))
			return false;
		return true;
	}
	public Etudiant getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	

}
