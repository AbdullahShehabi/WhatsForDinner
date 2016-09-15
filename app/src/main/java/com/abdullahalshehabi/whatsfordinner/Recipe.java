package com.abdullahalshehabi.whatsfordinner;

import java.util.LinkedList;

/**
 * Created by Abdullah Al-Shehabi on 9/14/2016.
 */
public class Recipe {

    private String recipeName;
    private String description;
    private String uri;
    private String[] ingredients;

    Recipe(String name, String description, String uri, String [] ingredients){
        this.recipeName = name;
        this.uri = uri;
        this.ingredients = ingredients;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

}
