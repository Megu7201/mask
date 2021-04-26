package ntou.cs.hw4.mask.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ntou.cs.hw4.mask.entity.Note;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

}
