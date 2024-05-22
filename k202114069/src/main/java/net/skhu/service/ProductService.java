package net.skhu.service;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import net.skhu.entity.Product;
import net.skhu.model.ProductEdit;
import net.skhu.model.Pagination;
import net.skhu.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired ProductRepository productRepository;
    ModelMapper modelMapper = new ModelMapper();

    public ProductEdit findOne(int id) {
    	Product productEntity = productRepository.findById(id).get();
        return toEditModel(productEntity);
    }

    public Product findByCode(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> findAll(Pagination pagination) {
        PageRequest pageRequest = PageRequest.of(pagination.getPg() - 1,
                pagination.getSz(),
                Sort.Direction.ASC, "id");
        Page<Product> page = productRepository.findAll(pageRequest);
        pagination.setRecordCount((int)page.getTotalElements());
        return page.getContent();
    }

    public void insert(ProductEdit productEdit, BindingResult bindingResult,
            Pagination pagination) throws Exception {
        if (hasErrors(productEdit, bindingResult))
            throw new Exception("입력 데이터 오류");
        Product course = toEntity(productEdit);
        productRepository.save(course);
        int lastPage = (int)Math.ceil((double)productRepository.count() / pagination.getSz());
        pagination.setPg(lastPage);
    }

    public void update(ProductEdit productEdit,
            BindingResult bindingResult) throws Exception {
        if (hasErrors(productEdit, bindingResult))
            throw new Exception("입력 데이터 오류");
        Product product = toEntity(productEdit);
        productRepository.save(product);
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }

    public Product toEntity(ProductEdit productEdit) {
        return modelMapper.map(productEdit, Product.class);
    }

    public ProductEdit toEditModel(Product productEntity) {
        return modelMapper.map(productEntity, ProductEdit.class);
    }

    public boolean hasErrors(ProductEdit productEdit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return true;
        Product product2 = findByCode(productEdit.getName());
        if (product2 != null && product2.getId() != productEdit.getId()) {
            bindingResult.rejectValue("code", null, "과목코드가 중복됩니다.");
            return true;
        }
        return false;
    }
}

