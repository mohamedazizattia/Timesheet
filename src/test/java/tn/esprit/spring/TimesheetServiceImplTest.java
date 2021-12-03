package tn.esprit.spring;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.services.ITimesheetService;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.not;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class TimesheetServiceImplTest {
    @Autowired
    ITimesheetService iTimesheetService;


    @org.junit.jupiter.api.Test
    public void ajouterMission() {
        Mission m1= new Mission(2,"paris","paris") ;
        Assert.assertNotNull(this.iTimesheetService.ajouterMission(m1));
    }
  //  @org.junit.jupiter.api.Test
//    public void affecterMissionADepartement() {
//        Assert.assertNotNull(this.iTimesheetService.affecterMissionADepartement(1,1));
//    }
//    @org.junit.jupiter.api.Test
//
//    public void ajouterTimesheet(){
//        Date date1 =new Date(2020,12,24);
//        Date date2 =new Date(2020,12,24);
//        this.iTimesheetService.ajouterTimesheet(1,1,date1,date2);
//        Assert.assertNotEquals(0,this.iTimesheetService.ajouterTimesheet(1,1,date1,date2));
//    }
//    @org.junit.jupiter.api.Test
//    public void  findAllMissionByEmployeJPQL() {
//        Assert.assertNotNull(this.iTimesheetService.findAllMissionByEmployeJPQL(1));
//    }
    @org.junit.jupiter.api.Test
    public void getAllEmployeByMission(){
        Assert.assertNotNull(this.iTimesheetService.getAllEmployeByMission(1));
    }


}
