package parduotuves.prekybcentriai;

import java.util.List;

import javax.persistence.*;

@Entity
public class Rajonas {	//turi sutapti su DB lenteles pavadinimu 
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    
    private Integer id;
    private String pavadinimas;
    private Double plotas;
	private Integer gyventoju_skaicius;	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idRajono",referencedColumnName="id",insertable=false, updatable=false)
	private List<Parduotuves> parduotuve;  

	public Rajonas() {
		
	}
	
	public Rajonas(Integer id, String pavadinimas, Double plotas, Integer gyventoju_skaicius) {
		super();
		
		this.id = id;
		this.pavadinimas = pavadinimas;
		this.plotas = plotas;
		this.gyventoju_skaicius = gyventoju_skaicius;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getPavadinimas() {
		return pavadinimas;
	}

	public void setPavadinimas(String pavadinimas) {
		this.pavadinimas = pavadinimas;
	}

	public Double getPlotas() {
		return plotas;
	}

	public void setPlotas(Double plotas) {
		this.plotas = plotas;
	}

	public Integer getGyventoju_skaicius() {
		return gyventoju_skaicius;
	}

	public void setGyventoju_skaicius(Integer gyventoju_skaicius) {
		this.gyventoju_skaicius = gyventoju_skaicius;
	}

}