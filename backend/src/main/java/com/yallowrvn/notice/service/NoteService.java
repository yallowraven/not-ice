package com.yallowrvn.notice.service;

import com.yallowrvn.notice.model.Note;

import java.util.Collection;
import java.util.Optional;

public interface NoteService {
    Collection<Note> findAllNotes();
    Collection<Note> findAllNotesBySearchPrompt(String prompt);
    Optional<Note> findNoteById(String id);

    Note createNote(Note note);
    void deleteNote(Note note);
    Note updateNote(Note oldNote, Note newNote);
}
