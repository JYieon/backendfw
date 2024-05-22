package net.skhu.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CourseEdit {
    int id;

    @Min(2010)
    int year;

    @NotEmpty @NotBlank
    @Pattern(regexp="1학기|2학기", message="1힉기, 2학기 중 하나를 입력하세요.")
    String semester;

    @NotEmpty @NotBlank
    String gubun;

    @NotEmpty @NotBlank
    String code;

    @NotEmpty @NotBlank
    String title;

    @NotEmpty @NotBlank
    String sigan;

    int professorId;
}
