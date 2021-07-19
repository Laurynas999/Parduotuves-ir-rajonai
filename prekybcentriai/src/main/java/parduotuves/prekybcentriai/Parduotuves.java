package parduotuves.prekybcentriai;

import javax.persistence.*;

@Entity
public class Parduotuves {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    
    private Integer id;
    private String pard_pav;
    private String adresas;
	private Integer idRajono;
	private Double prekybinis_plotas;	
	
	@ManyToOne
	@JoinColumn(name="idRajono", referencedColumnName="id", insertable=false, updatable=false)
	private Rajonas rajonas;

	public Parduotuves() {
		
	}
	
	public Parduotuves(Integer id, String pard_pav, String adresas, Integer id_rajono,
			Double prekybinis_plotas) {
		super();
		
		this.id = id;
		this.pard_pav = pard_pav;
		this.adresas = adresas;
		this.idRajono = id_rajono;
		this.prekybinis_plotas = prekybinis_plotas;
	}
	public Parduotuves(String id, String pard_pav, String adresas, String id_rajono,
			String prekybinis_plotas) {
		
		super();

		this.pard_pav = pard_pav;
		this.adresas = adresas;
		
		try {
		
				this.id = Integer.parseInt (id );	
				this.idRajono = Integer.parseInt (id_rajono );	
				this.prekybinis_plotas = Double.parseDouble( prekybinis_plotas );
		
		} catch ( Exception e ) {
		}		
	}	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getId_rajono() {
		return idRajono;
	}

	public void setId_rajono(Integer id_rajono) {
		this.idRajono = id_rajono;
	}

	public Double getPrekybinis_plotas() {
		return prekybinis_plotas;
	}

	public void setPrekybinis_plotas(Double prekybinis_plotas) {
		this.prekybinis_plotas = prekybinis_plotas;
	}

}