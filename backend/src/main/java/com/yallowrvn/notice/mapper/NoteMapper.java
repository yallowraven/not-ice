package com.yallowrvn.notice.mapper;

import com.yallowrvn.notice.dto.incoming.NoteCreationDto;
import com.yallowrvn.notice.dto.outgoing.NoteDto;
import com.yallowrvn.notice.model.Note;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    Note creationDtoToNote(NoteCreationDto dto);

    NoteDto noteToDto(Note note);
    Collection<NoteDto> notesToDtos(Collection<Note> notes);
}
