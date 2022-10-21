package com.yallowrvn.notice.dto.outgoing;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class NotePreviewDto implements Serializable {
    private String title;
    private List<String> tags;
}
