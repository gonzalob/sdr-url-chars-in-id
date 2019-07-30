package sample;

import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import static sample.SampleRepository.PATH;

@RepositoryRestResource(path = PATH)
public interface SampleRepository extends Repository<SampleEntity, String> {

    String PATH = "sample";

    SampleEntity findById(String id);

    SampleEntity save(SampleEntity e);
}
