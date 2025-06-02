package com.example.todo_api.todo;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<Void> createTodo(@RequestBody @Valid TodoCreateRequest request) {
        Long todoId = todoService.createTodo(request.getContent(), request.getMemberId());
        return ResponseEntity.created(URI.create("/todo/" + todoId)).build();
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<Void> updateTodo(@RequestBody TodoUpdateRequest request, @PathVariable(name="todoId") Long todoId) {
        todoService.updateTodo(request.getMemberId(), todoId, request.getNewContent());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Todo>> getTodoList(@RequestBody Long memberId) {
        List<Todo> todos = todoService.findMyTodos(memberId);
        return ResponseEntity.ok(todos);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(@RequestBody TodoDeleteRequest request, @PathVariable(name="todoId") Long todoId) {
        todoService.deleteTodo(request.getMemberId(), todoId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{todoId}/check")
    public ResponseEntity<Void> checkTodo(@RequestBody TodoCheckRequest request, @PathVariable(name="todoId") Long todoId) {
        todoService.checkTodo(request.getMemberId(), todoId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{todoId}/uncheck")
    public ResponseEntity<Void> uncheckTodo(@RequestBody TodoCheckRequest request, @PathVariable(name="todoId") Long todoId) {
        todoService.uncheckTodo(request.getMemberId(), todoId);
        return ResponseEntity.ok().build();
    }
}