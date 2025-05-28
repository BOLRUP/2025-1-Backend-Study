package com.example.todo_api.todo;

import com.example.todo_api.member.Member;
import com.example.todo_api.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long createTodo(String content, Long memberId) {
        Member member = memberRepository.findById(memberId);

        if (member == null) {
            throw new RuntimeException("멤버가 존재하지 않습니다.");
        }

        Todo todo = new Todo(content, member);
        todoRepository.save(todo);
        return todo.getId();
    }


    @Transactional(readOnly = true)
    public List<Todo> findMyTodos(Long memberId) {
        Member member = memberRepository.findById(memberId);

        if (member == null) {
            throw new RuntimeException("멤버가 존재하지 않습니다.");
        }

        return todoRepository.findAllByMember(member);
    }


    @Transactional
    public void updateTodo(Long memberId, Long todoId, String newContent) {
        Member member = memberRepository.findById(memberId);

        if (member == null) {
            throw new RuntimeException("멤버가 존재하지 않습니다.");
        }

        Todo todo = todoRepository.findById(todoId);

        if (todo == null) {
            throw new RuntimeException("할 일이 존재하지 않습니다.");
        }

        if (todo.getMember() != member) {
            throw new RuntimeException("할 일은 내가 만든 할 일만 수정할 수 있습니다.");
        }

        todo.updateContent(newContent);
    }


    @Transactional
    public void deleteTodo(Long memberId, Long todoId) {
        Member member = memberRepository.findById(memberId);

        if (member == null) {
            throw new RuntimeException("멤버가 존재하지 않습니다.");
        }

        Todo todo = todoRepository.findById(todoId);

        if (todo == null) {
            throw new RuntimeException("할 일이 존재하지 않습니다.");
        }

        if (todo.getMember() != member) {
            throw new RuntimeException("할 일은 내가 만든 할 일만 제거할 수 있습니다.");
        }

        todoRepository.deleteById(todo.getId());
    }
}