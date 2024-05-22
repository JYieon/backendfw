package net.skhu.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductEdit {
    int id;

    @Size(min=2, max=20)
    @NotBlank @NotEmpty 
    String name;
    
    @Min(10)
    int price;
    
    @Min(0)
    int quantity;
    
    int categoryId;
    
}
