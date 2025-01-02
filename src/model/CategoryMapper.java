package model;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryMapper {
    @Select("SELECT * FROM categories")
    List<Category> getAllCategories();
}
