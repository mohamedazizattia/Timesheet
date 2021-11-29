package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	private static final Logger l = LogManager.getLogger(EntrepriseServiceImpl.class);

	public Entreprise ajouterEntreprise(Entreprise entreprise) {
		l.info("In ajouterEntreprise() : ");
			entrepriseRepoistory.save(entreprise);
		l.info("Ajout avec succés ");

		return entreprise;
	}

	public int ajouterDepartement(Departement dep) {
		deptRepoistory.save(dep);
		return dep.getId();
	}

	@javax.annotation.Nullable
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		        //Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
				Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).orElse(null);
				Departement depManagedEntity = deptRepoistory.findById(depId).orElse(null);

				if(entrepriseManagedEntity!=null&&depManagedEntity!=null){
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);}
		
	}
	@javax.annotation.Nullable
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).orElse(null);
		List<String> depNames = new ArrayList<>();
		if (entrepriseManagedEntity!=null) {
			for (Departement dep : entrepriseManagedEntity.getDepartements()) {
				depNames.add(dep.getName());
			}
		}
		return depNames;

	}

	@javax.annotation.Nullable
	public Entreprise deleteEntrepriseById(int entrepriseId) {
		l.info("In deleteEntrepriseById : ",entrepriseId);
		entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).orElse(null));
		return entrepriseRepoistory.findById(entrepriseId).orElse(null);
	}
	@javax.annotation.Nullable

	public void deleteDepartementById(int depId) {
		deptRepoistory.delete(deptRepoistory.findById(depId).orElse(null));
	}

	@javax.annotation.Nullable
	public Entreprise getEntrepriseById(int entrepriseId) {
		l.info("In getEntrepriseById() : ");
		l.info("In getEntrepriseById() with ID  : ",entrepriseRepoistory.findById(entrepriseId).orElse(null));

		return entrepriseRepoistory.findById(entrepriseId).orElse(null);
	}

	

}
