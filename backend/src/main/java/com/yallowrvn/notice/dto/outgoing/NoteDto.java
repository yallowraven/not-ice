package com.yallowrvn.notice.dto.outgoing;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class NoteDto implements Serializable {
    private String id;
    private String title;
    private String content;
    private List<String> tags;
}
