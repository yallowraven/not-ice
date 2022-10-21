package com.yallowrvn.notice.dto.incoming;

import com.yallowrvn.notice.dto.validation.NotBlankElements;
import com.yallowrvn.notice.dto.validation.NotContainingStringElements;
import com.yallowrvn.notice.dto.validation.SizeElements;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
public class NoteCreationDto implements Serializable {
    @NotBlank
    @Size(min = 1, max = 100)
    private String title;
    @NotNull
    @Size(max = 50000)
    private String content;

    @NotEmpty
    @Size(min = 1, max = 50)
    @NotBlankElements
    @SizeElements(min = 1, max = 25)
    @NotContainingStringElements(string = ",", message = "must not contain elements with commas")
    private List<String> tags;
}
