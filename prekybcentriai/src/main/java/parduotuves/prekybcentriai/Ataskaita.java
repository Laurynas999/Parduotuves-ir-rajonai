package parduotuves.prekybcentriai;

public class Ataskaita {
	
	private String pard_pav;
    private String adresas;
	private Double prekybinis_plotas;

	private String pavadinimas;
	
	public Ataskaita() {}
	
	public Ataskaita(String pard_pav, String adresas, double prekybinis_plotas) {

		this.setPard_pav(pard_pav);
		this.setAdresas(adresas);
		this.setPrekybinis_plotas(prekybinis_plotas);
	}

	public String getPard_pav() {
		return pard_pav;
	}

	public void setPard_pav(String pard_pav) {
		this.pard_pav = pard_pav;
	}
	
	public String getAdresas() {
		return adresas;
	}

	public void setAdresas(String adresas) {
		this.adresas = adresas;
	}

	public double getPrekybinis_plotas() {
		return prekybinis_plotas;
	}

	public void setPrekybinis_plotas(double prekybinis_plotas) {
		this.prekybinis_plotas = prekybinis_plotas;
	}

	public String getPavadinimas() {
		return pavadinimas;
	}

	public void setPavadinimas(String pavadinimas) {
		this.pavadinimas = pavadinimas;
	}

}
