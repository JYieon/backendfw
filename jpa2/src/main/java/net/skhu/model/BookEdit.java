package net.skhu.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookEdit {
    int id;

    @NotEmpty @NotBlank
    String title;

    @NotEmpty @NotBlank
    String author;

    @Min(value=1, message="학과를 선택하세요.")
    int price;


    @NotEmpty @NotBlank
    String publisher;

    @Min(value=1, message="카테고리를 선택하세요.")
    int CategoryId;
}

