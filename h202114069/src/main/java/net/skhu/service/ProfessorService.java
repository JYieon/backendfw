package net.skhu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.skhu.dto.Professor;
import net.skhu.mapper.ProfessorMapper;

@Service
public class ProfessorService {

    @Autowired
    public ProfessorMapper professorMapper;

    public List<Professor> findAll() {
        return professorMapper.findAll();
    }

}

