package net.skhu.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseEdit {
	int id;
	
	@Min(value=2020, message="2020 이상 2030 이하이어야 합니다")
    int year;
	
	@NotEmpty @NotBlank
    String semester;
	
	@NotEmpty @NotBlank
    String gubun;
	
	@NotEmpty @NotBlank
    String code;
	
	@NotEmpty @NotBlank
    String title;
	
	
    @Min(0)
    int professorId;
	
	@NotEmpty @NotBlank
    String sigan;

}

