package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Contrat;

import java.util.List;

@Repository
public interface ContratRepository extends CrudRepository<Contrat, Integer>{
    @Query("Select c from Contrat c ")
    public List<Contrat> getAllI();
} 
