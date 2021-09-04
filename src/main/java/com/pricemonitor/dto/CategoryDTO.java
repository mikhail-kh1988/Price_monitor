package com.pricemonitor.dto;

public class CategoryDTO {

    private String categoryName;
    private String description;
    private String newName;
    private String newDescription;

    public CategoryDTO(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }

    public CategoryDTO() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }
}
