package parduotuves.prekybcentriai;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class PageController {
	
	@Autowired
	private ParduotuvesRepository parduotuves_repository;
	@Autowired
	private RajonasRepository rajonai_repository;
	
	@RequestMapping(path="/")	
	public  String pradzia() {
		
		return "pradzia";
	}
	
	@RequestMapping(path="/parduotuves", method= {RequestMethod.GET,RequestMethod.POST})
	public String parduotuviu_rezultatai( 
			@RequestParam(name="id", required =true, defaultValue="")String id,
			@RequestParam(name="pard_pav", required =true, defaultValue="")String pard_pav,
			@RequestParam(name="id_rajono", required =true, defaultValue="")String id_rajono,
			@RequestParam(name="adresas", required =true, defaultValue="")String adresas,
			@RequestParam(name="prekybinis_plotas", required =true, defaultValue="")String prekybinis_plotas,
			
			@RequestParam(name="siusti", required =true, defaultValue="")String siusti,

	Model model ) throws IOException {
		
				String url_tpl = "pard_ived";
				
				Iterable<Rajonas> mano_rajonai = rajonai_repository.findAll();
				model.addAttribute("rajonas",mano_rajonai);
				
				Iterable<Parduotuves> mano_parduotuves = parduotuves_repository.findAll();
				model.addAttribute("parduotuves",mano_parduotuves);
				
			/*	for( Rajonas rajonas : mano_rajonai) {
					if (rajonas != null) {
						System.out.println(rajonas.getPavadinimas());
					}else {
						System.out.println("Bla bla");
					}
				}
				*/	
				
				
				if (siusti != null && siusti.equals("Patvirtinti")) {
					
					Parduotuves apklausa = new Parduotuves ( 
						null	
						,pard_pav
						, adresas		
						,Integer.parseInt (id_rajono)
						, Double.parseDouble ( prekybinis_plotas )
					);
					parduotuves_repository.save(apklausa);
					
					File failas = new File ("parduotuves_info.csv");
					failas.createNewFile();
					
					FileWriter irasymas_faile = new FileWriter("parduotuves_info.csv", true);
					BufferedWriter rasyk = new BufferedWriter(irasymas_faile);
					
					rasyk.write("\",\""+pard_pav+"\",\"");
					rasyk.write(adresas+"\",\"");
					rasyk.write(id_rajono+"\",\"");
					rasyk.write(prekybinis_plotas+"\",\"");
					rasyk.newLine();
					
					rasyk.close();
					String vardas_rajono = rajonai_repository.findById(apklausa.getId_rajono()).get().getPavadinimas();
					url_tpl="redirect:visos_parduotuves?rajonas_id=" + apklausa.getId_rajono() +"&vardas="+ vardas_rajono ;
	
				}
		return url_tpl;
		
	}
	
	@RequestMapping(path="/visos_parduotuves", method={ RequestMethod.GET, RequestMethod.POST })
	public String visos_parduotuves ( 
			
			@RequestParam(name="rajonas_id", required =true, defaultValue="")String rajonas_id,
			@RequestParam(name="vardas", required =true, defaultValue="")String vardas,
			
			@RequestParam(name="id", required =true, defaultValue="")String id,
			@RequestParam(name="pard_pav", required =true, defaultValue="")String pard_pav,
			@RequestParam(name="adresas", required =true, defaultValue="")String adresas,
			@RequestParam(name="prekybinis_plotas", required =true, defaultValue="")String prekybinis_plotas,
			
			@RequestParam(name="siusti", required =true, defaultValue="")String siusti,
			
			Model model ) throws IOException {

		Iterable<Parduotuves> mano_parduotuves ;
		
		
		if (rajonas_id.equals("") ) {	//	-|+-	jei einam per visu parduotuviu mygtuka								
			mano_parduotuves = parduotuves_repository.findAll();
			
		}else {
			if((!rajonas_id.equals("")) && (!vardas.equals(""))){	//	+|+	jei gauna rajono id ir varda rodo to rajono id, varda, visas priskirtas parduotuves
				
				System.out.println(rajonas_id);
				model.addAttribute("vardas",vardas );				//toks turi ateiti is /rajonai (dar neprijungtas redirectas)
				model.addAttribute("rajonas_id",rajonas_id);
				
				mano_parduotuves = parduotuves_repository.findByIdRajono(Integer.parseInt(rajonas_id));
				
				model.addAttribute("parduotuves",mano_parduotuves);
				
				if (siusti != null && siusti.equals("Patvirtinti")) {
					
					Parduotuves apklausa = new Parduotuves ( 
						null	
						,pard_pav
						, adresas		
						,Integer.parseInt (rajonas_id)
						, Double.parseDouble ( prekybinis_plotas )
					);
					parduotuves_repository.save(apklausa);
					
					File failas = new File ("parduotuves_info.csv");
					failas.createNewFile();
					
					FileWriter irasymas_faile = new FileWriter("parduotuves_info.csv", true);
					BufferedWriter rasyk = new BufferedWriter(irasymas_faile);
					
					rasyk.write("\",\""+pard_pav+"\",\"");
					rasyk.write(adresas+"\",\"");
					rasyk.write(rajonas_id+"\",\"");
					rasyk.write(prekybinis_plotas+"\",\"");
					rasyk.newLine();
					
					rasyk.close();
					
					String url_tpl="redirect:visos_parduotuves?rajonas_id=" + rajonas_id +"&vardas="+ vardas ;
					
					System.out.println("rajono id" + rajonas_id);
									
					return url_tpl;
				}
				
				return "po_rajono_ivedimo";
			
			}else {				//	+|+-	po rajono/parduotuves ivedimo gauna id o vardas nera butinas
				mano_parduotuves = parduotuves_repository.findByIdRajono(Integer.parseInt(rajonas_id));
				
				return "po_rajono_ivedimo";
			}
		}
		
		model.addAttribute("visa_pard_info",mano_parduotuves );
		
		return "visos_pard";
	}
	
	@RequestMapping(path="/rajonai", method= {RequestMethod.GET,RequestMethod.POST})
	public String rajonu_rezultatai( 
			@RequestParam(name="id", required =true, defaultValue="")String id,
			@RequestParam(name="pavadinimas", required =true, defaultValue="")String pavadinimas,
			@RequestParam(name="plotas", required =true, defaultValue="")String plotas,
			@RequestParam(name="gyventoju_skaicius", required =true, defaultValue="")String gyventoju_skaicius,
			
			@RequestParam(name="siusti", required =true, defaultValue="")String siusti,

	Model model ) throws IOException {
		
				String url_tpl = "rajono_ived";
				
				if (siusti != null && siusti.equals("Patvirtinti")) {
					
					Rajonas rajono_kl = new Rajonas ( 
						null	
						, pavadinimas
						, Double.parseDouble (plotas)	
						, Integer.parseInt (gyventoju_skaicius)
					);
					rajonai_repository.save(rajono_kl);
					
					File failas = new File ("rajonai_info.csv");
					failas.createNewFile();
					
					FileWriter irasymas_faile = new FileWriter("rajonai_info.csv", true);
					BufferedWriter rasyk = new BufferedWriter(irasymas_faile);
					
					rasyk.write("\",\""+pavadinimas+"\",\"");
					rasyk.write(plotas+"\",\"");
					rasyk.write(gyventoju_skaicius+"\",\"");
					rasyk.newLine();
					
					rasyk.close();
					url_tpl="redirect:visi_rajonai?rajonas_id=" + rajono_kl.getId() +"&vardas=" + rajono_kl.getPavadinimas();
				}
		return url_tpl;
		
	}
	
	@RequestMapping(path="/visi_rajonai", method={ RequestMethod.GET, RequestMethod.POST })
	public String visi_rajonai ( 
			
			@RequestParam(name="rajonas_id", required =true, defaultValue="")String rajonas_id,
			@RequestParam(name="vardas", required =true, defaultValue="")String vardas,

			Model model ) throws IOException {
		
		Iterable<Rajonas> mano_rajonai = rajonai_repository.findAll();
		model.addAttribute("visu_rajon_info",mano_rajonai );
		model.addAttribute("rajonas_id",rajonas_id );
		model.addAttribute("vardas",vardas );
		
		String url_tpl="visi_rajonai";
		
		if((!rajonas_id.equals(""))&&(!vardas.equals(""))) {
			
			url_tpl="redirect:visos_parduotuves?rajonas_id=" + rajonas_id +"&vardas="+ vardas ;
			return url_tpl;
		}else {
		
			return url_tpl;
		}
	}
	
	
}