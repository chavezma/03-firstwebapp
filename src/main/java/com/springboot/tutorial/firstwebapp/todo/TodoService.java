package com.springboot.tutorial.firstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
    private static int todosCount = 0;

    static {
        todos.add(new Todo(++todosCount, "matias", "Learn Java"
                            , LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "matias", "Learn Spring"
                            , LocalDate.now().plusYears(2), false));
        todos.add(new Todo(++todosCount, "matias", "Learn Mongo"
                            , LocalDate.now().plusYears(3), false));
    }

    public List<Todo> findByUsername(String username) {
        Predicate<Todo> predicate = todo -> todo.getUsername().equals(username);

        return todos.stream().filter(predicate).collect(Collectors.toList());
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean done){
        Todo newTodo = new Todo(
            ++todosCount,
            username,
            description,
            targetDate,
            done
        );

        this.todos.add(newTodo);
    }

    public void deleteById(int id) {
        Predicate<Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id) {
        Predicate<Todo> predicate = todo -> todo.getId() == id;
        Optional<Todo> todo = todos.stream().filter(predicate).findFirst();

        if (todo.isPresent()) {
            return todo.get();
        } else {
            // Manejar el caso cuando el Todo no se encuentra
            // Puedes lanzar una excepción o devolver un valor predeterminado
            throw new NoSuchElementException("Todo not found for id: " + id);
        }
    }

    public void updateTodo(@Valid Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}
