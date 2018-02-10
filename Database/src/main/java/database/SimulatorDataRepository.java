package database;
import org.springframework.data.mongodb.repository.MongoRepository;
import sample.SimulatorData;

import java.util.List;


public interface SimulatorDataRepository extends MongoRepository<SimulatorData, Integer> {

    public List<SimulatorData> findById(int id);

}