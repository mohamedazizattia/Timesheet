package tn.esprit.spring;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

import java.util.Date;
import java.util.logging.LogManager;

import org.apache.tomcat.util.http.parser.MediaType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.qos.logback.classic.Logger;
import tn.esprit.spring.controller.RestControlEmploye;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.EmployeServiceImpl;
import tn.esprit.spring.services.IEmployeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTestImpl {
    @Autowired
    EmployeServiceImpl employeservice;
    @Autowired
    IEmployeService iemplser;
    @Autowired
    RestControlEmploye employeController;


    @Test
    public void ajouterEmployeTest() throws Exception {
        Employe employe = new Employe("Achraf","Smirani","achraf@yahoo.com", true, Role.INGENIEUR);
        employeservice.ajouterEmploye(employe);
        assertNotNull("L'instance n'est pas créée", employe);
    }
    @Test
    public void testGetNom() {
        Employe employe = new Employe("Achraf","Smirani","achraf@yahoo.com", true, Role.INGENIEUR);
        assertEquals("Le nom est incorrect", "Achraf", employe.getNom());
    }
    @Test
    public void testSetNom() {
        Employe employe = new Employe("Achraf","Smirani","achraf@yahoo.com", true, Role.INGENIEUR);
        employe.setNom("nom2");
        assertEquals("Le nom est incorrect", "nom2", employe.getNom());
    }
    @Test
    public void testGetPrenom() {
        Employe employe = new Employe("Achraf","Smirani","achraf@yahoo.com", true, Role.INGENIEUR);
        assertEquals("Le prenom est incorrect", "Smirani", employe.getPrenom());
    }
    @Test
    public void testSetPrenom() {
        Employe employe = new Employe("Achraf","Smirani","achraf@yahoo.com", true, Role.INGENIEUR);
        employe.setPrenom("prenom2");
        assertEquals("Le prenom est incorrect", "prenom2", employe.getPrenom());
    }
    @Test
    public void testGetEmail() {
        Employe employe = new Employe("Achraf","Smirani","achraf@yahoo.com", true, Role.INGENIEUR);
        assertEquals("L'email est incorrect", "email1", employe.getEmail());
    }
    @Test
    public void testSetEmail() {
        Employe employe = new Employe("Achraf","Smirani","achraf@yahoo.com", true, Role.INGENIEUR);
        employe.setPrenom("email2");
        assertEquals("L'email est incorrect", "email2", employe.getEmail());
    }
    @Test
    public void getEmployePrenomByIdTest()
    {
        String prenom = employeservice.getEmployePrenomById(1);
        assertEquals("Smirani", prenom);
    }
    @Test
    public void deleteEmployeTest() throws Exception {
        // prepare ID to delete
        Employe achraf =new Employe();
        employeservice.ajouterEmploye(achraf);
        int id = achraf.getId();

        employeController.deleteEmployeById(id);
        assertEquals("redirect:/deleteEmployeById/{idemp}", achraf);
        assertNull(employeservice.getAllEmployes());
    }
    @org.junit.jupiter.api.Test
    public void testAjouterEmployee (){
        Employe e = new Employe("Achraf","Smirani","achraf@yahoo.com", true,Role.INGENIEUR);
        Assert.assertNotNull(this.employeservice.ajouterEmploye(e));
    }
    @org.junit.jupiter.api.Test
    public void testAjouterContrat (){
        Date date1 =new Date(2020,12,24);
        Contrat e = new Contrat(date1,"CDI",1200f);
        Assert.assertNotNull(this.employeservice.ajouterContrat(e));
    }
    @org.junit.jupiter.api.Test
    public void testGetAllEmployee (){
        Assert.assertNotNull(this.employeservice.getAllEmployes());
    }

    @org.junit.jupiter.api.Test
    public void testDeleteContratId() {
        Assert.assertEquals(1,this.employeservice.deleteContratById(1));
    }

}

