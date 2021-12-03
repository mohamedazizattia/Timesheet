package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	private static final Logger l = LogManager.getLogger(EmployeServiceImpl.class);


	public int ajouterEmploye(Employe employe) {
		try{
			l.info("In ajouterEmploye() : ");
			l.debug("je vais lancer l'ajout: ");
			employeRepository.save(employe);
			l.info("Ajout avec succés ");

			return employe.getId();}
		catch (Exception e)
		{
			l.error(e.getMessage());
			return -1;
		}
	}

	
	
	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		try{
		l.info("In mettreAjourEmailByEmployeId() : ");
		Employe employe = employeRepository.findById(employeId).get();
		l.debug("je vais lancer le mettre à jour de l'email: ");

		employe.setEmail(email);
		employeRepository.save(employe);
		l.info("Ajout avec succés "); }
		catch (Exception e)
		{	l.info(" le mettre à jour a echoué");
			l.error(e.getMessage());
			}
	}
	
	public void affecterEmployeADepartement(int employeId, int depId) {
		Departement depManagedEntity = deptRepoistory.findById(depId).get();
		Employe employeManagedEntity = employeRepository.findById(employeId).get();

		if(depManagedEntity.getEmployes() == null){
			List<Employe> employes = new ArrayList<>();
			employes.add(employeManagedEntity);
			depManagedEntity.setEmployes(employes);
		}else{

			depManagedEntity.getEmployes().add(employeManagedEntity);

		}

	}
	@Transactional
	//supprimer l'employe du departement
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{	try {
		l.info("in desaffecterEmployeDuDepartement");
		Departement dep = deptRepoistory.findById(depId).get();

		int employeNb = dep.getEmployes().size();
		l.info("on commence le parcours du liste des employé");
		for(int index = 0; index < employeNb; index++){
			l.debug("l'employé avec l'id"+dep.getEmployes().get(index).getId());
			if(dep.getEmployes().get(index).getId() == employeId){
				dep.getEmployes().remove(index);
			}

		}

	}catch (Exception e){
		l.error(e.getMessage());
	}
	}

	public int ajouterContrat(Contrat contrat) {
		l.info("in Ajout Contrat");

		contratRepoistory.save(contrat);
		l.debug("le contrat ajouté est"+contrat.getTypeContrat());
		l.info("ajout avec succés");
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
		Employe employeManagedEntity = employeRepository.findById(employeId).get();

		contratManagedEntity.setEmploye(employeManagedEntity);
		contratRepoistory.save(contratManagedEntity);
		
	}

	public String getEmployePrenomById(int employeId) {
		Employe employeManagedEntity = employeRepository.findById(employeId).get();
		return employeManagedEntity.getPrenom();
	}
	public void deleteEmployeById(int employeId)
	{
		Employe employe = employeRepository.findById(employeId).get();

		//Desaffecter l'employe de tous les departements
		//c'est le bout master qui permet de mettre a jour
		//la table d'association
		for(Departement dep : employe.getDepartements()){
			dep.getEmployes().remove(employe);
		}

		employeRepository.delete(employe);
	}

	public int deleteContratById(int contratId) {
		int size = contratRepoistory.getAllI().size();
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
		contratRepoistory.delete(contratManagedEntity);
		return  size-contratRepoistory.getAllI().size();
	}

	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}
	
	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();

	}
	
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}
	public void deleteAllContratJPQL() {
         employeRepository.deleteAllContratJPQL();
	}
	
	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}
	
	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getAllEmployes() {
				return (List<Employe>) employeRepository.findAll();
	}

}
