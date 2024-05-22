package net.skhu.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import net.skhu.entity.Book;
import net.skhu.model.Pagination;
import net.skhu.model.BookEdit;
import net.skhu.repository.BookRepository;

@Service
public class BookService {

    @Autowired BookRepository bookRepository;
    ModelMapper modelMapper = new ModelMapper();

    public BookEdit findOne(int id) {
    	Book bookEntity = bookRepository.findById(id).get();
        return toEditModel(bookEntity);
    }

    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> findAll(Pagination pagination) {
        PageRequest pageRequest = PageRequest.of(pagination.getPg() - 1,
                pagination.getSz(),
                Sort.Direction.ASC, "id");
        Page<Book> page = bookRepository.findAll(pageRequest);
        pagination.setRecordCount((int)page.getTotalElements());
        return page.getContent();
    }

    public void insert(BookEdit bookEdit, BindingResult bindingResult,
            Pagination pagination) throws Exception {
        if (hasErrors(bookEdit, bindingResult))
            throw new Exception("입력 데이터 오류");
        Book book = toEntity(bookEdit);
        bookRepository.save(book);
        int lastPage = (int)Math.ceil((double)bookRepository.count() / pagination.getSz());
        pagination.setPg(lastPage);
    }

    public void update(BookEdit bookEdit,
            BindingResult bindingResult) throws Exception {
        if (hasErrors(bookEdit, bindingResult))
            throw new Exception("입력 데이터 오류");
        Book book = toEntity(bookEdit);
        bookRepository.save(book);
    }

    public void delete(int id) {
    	bookRepository.deleteById(id);
    }

    public Book toEntity(BookEdit bookEdit) {
        return modelMapper.map(bookEdit, Book.class);
    }

    public BookEdit toEditModel(Book bookEntity) {
        return modelMapper.map(bookEntity, BookEdit.class);
    }

    public boolean hasErrors(BookEdit bookEdit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return true;
        Book book2 = findByTitle(bookEdit.getTitle());
        if (book2 != null && book2.getId() != bookEdit.getId()) {
            bindingResult.rejectValue("title", null, "제목이 중복됩니다.");
            return true;
        }
        return false;
    }
}

