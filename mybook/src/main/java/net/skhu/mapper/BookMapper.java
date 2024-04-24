package net.skhu.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import net.skhu.dto.Book;
import net.skhu.model.Pagination;

@Mapper
public interface BookMapper {

    @Select("""
            SELECT s.*, d.name CategoryName
            FROM book s LEFT JOIN category d ON s.categoryId = d.id
            ORDER BY s.id
            LIMIT #{firstRecordIndex}, #{sz} """)
    List<Book> findAll(Pagination pagination);

    @Select("SELECT COUNT(id) FROM book")
    int getCount();

    @Select("SELECT * FROM book WHERE id = #{id}")
    Book findOne(int id);
    
    @Select("SELECT * FROM book WHERE title = #{title}")
    Book findByTitle(String title);

    @Insert("""
        INSERT book (title, author, categoryId, price, publisher)
        VALUES (#{title}, #{author}, #{categoryId}, #{price}, #{publisher}) """)
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(Book book);

    @Update("""
        UPDATE book SET
          title = #{title},
          author = #{author},
          categoryId = #{categoryId},
          price = #{price},
          publisher = #{publisher}
        WHERE id = #{id} """)
    void update(Book book);

    @Delete("DELETE FROM book WHERE id = #{id}")
    void delete(int id);
}

