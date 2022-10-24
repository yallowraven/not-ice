package com.yallowrvn.notice.controller;

import com.yallowrvn.notice.controller.exception.NotFoundException;
import com.yallowrvn.notice.dto.incoming.NoteCreationDto;
import com.yallowrvn.notice.dto.outgoing.NoteDto;
import com.yallowrvn.notice.mapper.NoteMapper;
import com.yallowrvn.notice.model.Note;
import com.yallowrvn.notice.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@Validated
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/notice-api/notes")
public class NoteController {
    @Autowired
    NoteService noteService;

    @Autowired
    NoteMapper noteMapper;

    //TODO: pagination
    @GetMapping
    public Collection<NoteDto> getAllNotes() {
        Collection<Note> notes = noteService.findAllNotes();
        return noteMapper.notesToDtos(notes);
    }

    @GetMapping(value = "/{id}")
    public Optional<NoteDto> getNoteById(@PathVariable String id) {
        Optional<Note> note = noteService.findNoteById(id);
        return note.map(noteMapper::noteToDto);
    }

    @GetMapping(params = "search")
    public Collection<NoteDto> getAllNotesBySearchPrompt(@RequestParam String search) {
        Collection<Note> notes = noteService.findAllNotesBySearchPrompt(search);
        return noteMapper.notesToDtos(notes);
    }

    @PostMapping
    public ResponseEntity<NoteDto> createNote(@RequestBody @Valid NoteCreationDto dto) {
        Note note = noteMapper.creationDtoToNote(dto);
        Note createdNote = noteService.createNote(note);

        return new ResponseEntity<>(noteMapper.noteToDto(createdNote), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public NoteDto updateNote(@PathVariable String id, @RequestBody @Valid NoteCreationDto dto) {
        Optional<Note> note = noteService.findNoteById(id);

        if (note.isEmpty()) {
            throw new NotFoundException();
        }

        Note newNote = noteMapper.creationDtoToNote(dto);
        newNote = noteService.updateNote(note.get(), newNote);

        return noteMapper.noteToDto(newNote);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteNoteById(@PathVariable String id) {
        Optional<Note> note = noteService.findNoteById(id);

        if (note.isEmpty()) {
            throw new NotFoundException();
        }

        noteService.deleteNote(note.get());

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
