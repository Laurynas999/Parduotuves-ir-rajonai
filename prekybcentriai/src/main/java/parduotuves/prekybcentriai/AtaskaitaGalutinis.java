package parduotuves.prekybcentriai;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

public class AtaskaitaGalutinis {
	
protected Session em;
	
	public AtaskaitaGalutinis() {}
	
	public AtaskaitaGalutinis(  Session em  ) {
		    this.em = em;
	}
	
	 public List<Ataskaita> tinkloFiltravimas( String tinklas ) {
		 String filtruoti_tinkla = ")ORDER BY "
		 								+ "`parduotuves`.`prekybinis_plotas`";
		 if(tinklas != null) {
			 if(!tinklas.equals("VISOS")) {
				 filtruoti_tinkla = ") WHERE `parduotuves`.`pard_pav` = '"+ tinklas +"'"
				  					+"ORDER BY "
				  					+	"`parduotuves`.`prekybinis_plotas`";
			 }else {
				 filtruoti_tinkla = ")ORDER BY "
							+ "`parduotuves`.`prekybinis_plotas`";
			 }
		 }
		  			
		 
		  	String uzklausa_pagal_parduotuve =
		  				
		  			"SELECT" 
		  		    	
		  		    	+	" `parduotuves`.`pard_pav`"
			  		    +	", `rajonas`.`pavadinimas`"
			  		    +	", `parduotuves`.`adresas`"
			  		    +	", `parduotuves`.`prekybinis_plotas`"
			  		+"FROM "
			  			+	"`parduotuves`" 
			  		+"JOIN `rajonas` ON (`parduotuves`.`id_rajono`=`rajonas`.`id`" 
			  		+ filtruoti_tinkla;
		  	
		  	System.out.println ( uzklausa_pagal_parduotuve );
		    Query query = em.createNativeQuery ( uzklausa_pagal_parduotuve );
		    return (List<Ataskaita>) query.getResultList();
	  }	
}
