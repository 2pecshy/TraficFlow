package database;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import sample.SimulatorData;

@SpringBootApplication
@EnableBinding(CustomProcessor.class)
public class Database extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Database.class, args);
    }

    @Autowired
    private SimulatorDataRepository repository;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Database.class);
    }

    @StreamListener(CustomProcessor.INPUT_SIMULATEUR)
    public void getSimu(SimulatorData data){
//        repository.save(data);
        System.out.println("j'ai reçu un SimulatorData");
    }
//
//    @RequestMapping("/simu/{id}")
//    public List<SimulatorData> getRecognition(@PathVariable("id") int id){
//        return repository.findByName(id);
//    }
//
//    @RequestMapping("/simu")
//    public List<SimulatorData> getSimulatorData(){
//        return repository.findAll();
//    }
//
//    @PostMapping("/simu")
//    public ResponseEntity<String> addColleague(@RequestBody SimulatorData data){
//        repository.save(data);
//        return new ResponseEntity<String>(HttpStatus.CREATED);
//    }
//
//    //This is of course a very naive implementation! We are assuming unique names...
//    @DeleteMapping("/simu/{id}")
//    public ResponseEntity<String> deleteSimulatorData(@PathVariable int id){
//        List<SimulatorData> data = repository.findByName(id);
//        if(data.size() == 1) {
//            SimulatorData simu = data.get(0);
//            repository.delete(simu);
//            return new ResponseEntity<String>(HttpStatus.ACCEPTED);
//        }
//        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
//    }
}