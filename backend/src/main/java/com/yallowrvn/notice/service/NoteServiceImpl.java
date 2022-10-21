package com.yallowrvn.notice.service;

import com.mongodb.ErrorCategory;
import com.yallowrvn.notice.model.Note;
import com.yallowrvn.notice.model.Tag;
import com.yallowrvn.notice.repository.NoteRepository;
import com.yallowrvn.notice.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.BulkOperationException;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    TagRepository tagRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Collection<Note> findAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Collection<Note> findAllNotesBySearchPrompt(String prompt) {
        List<String> searchedTagNames = tagRepository.findAll().stream()
                .map(Tag::getName)
                .filter(prompt::contains)
                .collect(Collectors.toList());

        return noteRepository.findAllByTags(searchedTagNames);
    }

    @Override
    public Optional<Note> findNoteById(String id) {
        return noteRepository.findById(id);
    }

    void saveTagsOf(Note note) {
        List<Tag> tags = note.getTags().stream()
                .map(Tag::fromName)
                .collect(Collectors.toList());

        try {
            mongoTemplate.
                    bulkOps(BulkOperations.BulkMode.UNORDERED, Tag.class).
                    insert(tags).
                    execute();
        } catch (BulkOperationException e) {
            //ignore duplicate key errors (duplicate keys are intended to be ignored), rethrow all others
            if (!e.getErrors().stream().
                    allMatch(error -> error.getCategory() == ErrorCategory.DUPLICATE_KEY))
                throw e;
        }
    }

    @Override
    public Note createNote(Note note) {
        note = Note.normalize(note);

        saveTagsOf(note);
        return noteRepository.save(note);
    }

    @Override
    public void deleteNote(Note note) {
        noteRepository.delete(note);
    }

    @Override
    public Note updateNote(Note oldNote, Note newNote) {
        newNote = Note.normalize(newNote);

        newNote.setId(oldNote.getId());
        saveTagsOf(newNote);

        return noteRepository.save(newNote);
    }
}
