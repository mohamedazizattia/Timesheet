package tn.esprit.spring;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEntrepriseService;

import java.text.ParseException;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class EntrepriseServiceImplTest {
    @Autowired
    IEntrepriseService entrepriseService;

    @Test
    public void testAjouterEntreprise (){
        Entreprise e = new Entreprise("esprit","ghazela");
        Entreprise entrepriseAdded = this.entrepriseService.ajouterEntreprise(e);
        Assert.assertEquals(e.getName(), entrepriseAdded.getName());

    }
    @Test
    public void testGetEntreprise() {
        Assert.assertNotNull(this.entrepriseService.getEntrepriseById(1));
    }

    @Test
    public void testDeleteEntreprise() {
        Assert.assertNotNull(this.entrepriseService.getEntrepriseById(1));
    }

}
