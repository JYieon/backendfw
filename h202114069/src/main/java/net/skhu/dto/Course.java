package net.skhu.dto;

import lombok.Data;

@Data
public class Course {
    int id;
    int year;
    String semester;
    String gubun;
    String code;
    String title;
    int professorId;
    String sigan;
    
    String professorName;
}


