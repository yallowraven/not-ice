package com.yallowrvn.notice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Data
@Document(collection = "tags")
public class Tag {
    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    public static Tag fromName(String name) {
        Tag tag = new Tag();
        tag.setName(name);
        return tag;
    }
}
