
package net.skhu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.skhu.entity.Professor;
import net.skhu.repository.ProfessorRepository;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }
}
