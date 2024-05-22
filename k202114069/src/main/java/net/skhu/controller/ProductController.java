package net.skhu.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import net.skhu.entity.Category;
import net.skhu.entity.Product;
import net.skhu.model.Pagination;
import net.skhu.model.ProductEdit;
import net.skhu.service.CategoryService;
import net.skhu.service.ProductService;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired ProductService productService;
    @Autowired CategoryService categoryService;

    @GetMapping("list")
    public String list(Model model, Pagination pagination) {
        List<Product> products = productService.findAll(pagination);
        model.addAttribute("products", products);
        return "product/list";
    }

    @GetMapping("create")
    public String create(Model model, Pagination pagination) {
        ProductEdit productEdit = new ProductEdit();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("productEdit", productEdit);
        model.addAttribute("categories", categories);
        return "product/edit";
    }

    @PostMapping("create")
    public String create(Model model, Pagination pagination,
            @Valid ProductEdit productEdit, BindingResult bindingResult) {
        try {
            productService.insert(productEdit, bindingResult, pagination);
            return "redirect:list?" + pagination.getQueryString();
        }
        catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("categories", categoryService.findAll());
            bindingResult.rejectValue("", null, "등록할 수 없습니다.");
            return "product/edit";
        }
    }

    @GetMapping("edit")
    public String edit(Model model, @RequestParam("id") int id,
            Pagination pagination) {
        ProductEdit productEdit = productService.findOne(id);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("productEdit", productEdit);
        model.addAttribute("categories", categories);
        return "product/edit";
    }

    @PostMapping(value="edit", params="cmd=save")
    public String edit(Model model, Pagination pagination,
            @Valid ProductEdit productEdit, BindingResult bindingResult) {
        try {
            productService.update(productEdit, bindingResult);
            return "redirect:list?" + pagination.getQueryString();
        }
        catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("categories", categoryService.findAll());
            bindingResult.rejectValue("", null, "수정할 수 없습니다.");
            return "product/edit";
        }
    }

    @PostMapping(value="edit", params="cmd=delete")
    public String delete(Model model, Pagination pagination,
            ProductEdit productEdit, BindingResult bindingResult) {
        try {
            productService.delete(productEdit.getId());
            return "redirect:list?" + pagination.getQueryString();
        }
        catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("categories", categoryService.findAll());
            bindingResult.rejectValue("", null, "삭제할 수 없습니다.");
            return "product/edit";
        }
    }
}

