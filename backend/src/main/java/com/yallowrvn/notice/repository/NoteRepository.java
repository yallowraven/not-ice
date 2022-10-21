package com.yallowrvn.notice.repository;

import com.yallowrvn.notice.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
    @Query("{ tags: { $all: ?0 } }")
    Collection<Note> findAllByTags(List<String> tags);
}
