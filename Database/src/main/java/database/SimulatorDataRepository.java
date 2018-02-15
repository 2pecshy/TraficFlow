package database;
import org.springframework.data.mongodb.repository.MongoRepository;
import sample.SimulatorData;

import java.util.List;


public interface SimulatorDataRepository extends MongoRepository<SimulatorData, String> {

    public List<SimulatorData> findById(String id);
    public List<SimulatorData> findByidSimulation(String id);
    public List <SimulatorData> deleteById(String id);

}