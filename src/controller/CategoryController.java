package controller;

import model.Category;
import model.CategoryMapper;
import org.apache.ibatis.session.SqlSession;
import view.CategoryView;

import java.util.List;

public class CategoryController {
    private CategoryView view;

    public CategoryController(CategoryView view, CategoryMapper mapper) {
        this.view = view;

        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);
            List<Category> categories = categoryMapper.getAllCategories();

            String[][] categoryArray = categories.stream()
                .map(category -> new String[]{
                    String.valueOf(category.getId()),
                    category.getName(),
                    category.getDescription()
                }).toArray(String[][]::new);

            view.setCategoryList(categoryArray);
        }
    }
}
