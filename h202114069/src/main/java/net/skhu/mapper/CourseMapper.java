package net.skhu.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import net.skhu.dto.Course;
import net.skhu.model.Pagination;

@Mapper
public interface CourseMapper {

    @Select("""
            SELECT s.*, d.name professorName
            FROM course s LEFT JOIN professor d ON s.professorId = d.id
            ORDER BY s.id
            LIMIT #{firstRecordIndex}, #{sz} """)
    List<Course> findAll(Pagination pagination);

    @Select("SELECT COUNT(id) FROM course")
    int getCount();

    @Select("SELECT * FROM course WHERE id = #{id}")
    Course findOne(int id);

    @Select("SELECT * FROM course WHERE code = #{code}")
    Course findByCode(String code);

    @Insert("""
        INSERT course (year, semester, gubun, code, title, professorId, sigan)
        VALUES (#{year}, #{semester}, #{gubun}, #{code}, #{title}, #{professorId}, #{sigan}) """)
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(Course course);

    @Update("""
        UPDATE course SET
          year= #{year},
          semester = #{semester},
          gubun = #{gubun},
          code = #{code},
          title = #{title},
          professorId = #{professorId},
          sigan = #{sigan}
        WHERE id = #{id} """)
    void update(Course course);

    @Delete("DELETE FROM course WHERE id = #{id}")
    void delete(int id);
}

