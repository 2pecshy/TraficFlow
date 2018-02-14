package database;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.SimulatorData;

import java.util.List;

@SpringBootApplication
@RestController
@EnableBinding(CustomProcessor.class)
public class Database extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Database.class, args);
    }

    @Autowired
    org.springframework.data.mongodb.core.MongoTemplate mongoTemplate;

    @Autowired
    private SimulatorDataRepository repository;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Database.class);
    }

    @StreamListener(CustomProcessor.INPUT_SIMULATEUR)
    public void getSimu(SimulatorData data){
        repository.save(data);
        System.out.println(data);
    }

    @RequestMapping("/database")
    public List<SimulatorData> getSimulatorData(){
        return repository.findAll();
    }

    @RequestMapping("/database/{id}")
    public List<SimulatorData> getSimulatorDataId(@PathVariable("id") String id){
        return repository.findById(id);
    }
//
//    @PostMapping("/simu")
//    public ResponseEntity<String> addColleague(@RequestBody SimulatorData data){
//        repository.save(data);
//        return new ResponseEntity<String>(HttpStatus.CREATED);
//    }
//

    // PURGE DATABASE !
    @DeleteMapping("/database")
    public void deleteSimulatorData(){
        mongoTemplate.getDb().dropDatabase();
    }

    @DeleteMapping("/database/{id}")
    public ResponseEntity<String> deleteSimulatorData(@PathVariable("id") String id){
        List<SimulatorData> data = repository.findById(id);
        if(data.size() == 1) {
            SimulatorData simu = data.get(0);
            repository.delete(simu);
            return new ResponseEntity<String>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
//        mongoTemplate.getDb().dropDatabase();
    }
}