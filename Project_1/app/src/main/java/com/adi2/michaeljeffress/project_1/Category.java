package com.adi2.michaeljeffress.project_1;

import java.security.PrivateKey;
import java.util.ArrayList;

/**
 * Created by michaeljeffress on 6/22/16.
 */
public class Category {
    private String category;
    private ArrayList<String> todos;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<String> getTodos() {
        return todos;
    }

    public void setTodos(ArrayList<String> todos) {
        this.todos = todos;
    }

    @Override
    public String toString() {
        return category;
    }
}
