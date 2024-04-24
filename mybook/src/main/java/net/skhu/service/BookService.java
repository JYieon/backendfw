package net.skhu.service;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import net.skhu.dto.Book;
import net.skhu.mapper.BookMapper;
import net.skhu.model.Pagination;
import net.skhu.model.BookEdit;

@Service
public class BookService {

    @Autowired BookMapper bookMapper;
    ModelMapper modelMapper = new ModelMapper();

    public BookEdit findOne(int id) {
        Book bookDto = bookMapper.findOne(id);
        return toEditModel(bookDto);
    }
    
    public Book findByTitle(String title) {
        return bookMapper.findByTitle(title);
    }
    
    public List<Book> findAll(Pagination pagination) {
        pagination.setRecordCount(bookMapper.getCount());
        return bookMapper.findAll(pagination);
    }

    public void insert(BookEdit bookEdit, BindingResult bindingResult,
            Pagination pagination) throws Exception {
        if (bindingResult.hasErrors())
            throw new Exception("입력 데이터 오류");
        Book book2 = findByTitle(bookEdit.getTitle());
        if (book2 != null) {
            bindingResult.rejectValue("title", null, "학번이 중복됩니다");
            throw new Exception("입력 데이터 오류");
        }
        Book book = toDto(bookEdit);
        bookMapper.insert(book);
        int lastPage = (int)Math.ceil((double)bookMapper.getCount() / pagination.getSz());
        pagination.setPg(lastPage);
    }

    public void update(BookEdit bookEdit,
    		BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors())
            throw new Exception("입력 데이터 오류");
        
        Book book = toDto(bookEdit);
        bookMapper.update(book);
    }

    public void delete(int id) {
        bookMapper.delete(id);
    }

    public Book toDto(BookEdit bookEdit) {
        return modelMapper.map(bookEdit, Book.class);
    }

    public BookEdit toEditModel(Book bookDto) {
        return modelMapper.map(bookDto, BookEdit.class);
    }
}

