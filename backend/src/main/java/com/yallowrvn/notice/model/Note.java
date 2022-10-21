package com.yallowrvn.notice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
@Document(collection = "notes")
public class Note {
    @Id
    private String id;

    private String title;
    private String content;
    @Indexed
    private List<String> tags;

    public static Note normalize(Note note) {
        Note normalizedNote = new Note();

        normalizedNote.setId(note.getId());
        normalizedNote.setTitle(note.getTitle().trim());
        normalizedNote.setContent(note.getContent());
        normalizedNote.setTags(note.getTags().stream().map(String::trim).collect(Collectors.toList()));

        return normalizedNote;
    }
}
