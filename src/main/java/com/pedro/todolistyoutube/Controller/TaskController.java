package com.pedro.todolistyoutube.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pedro.todolistyoutube.Model.Task;
import com.pedro.todolistyoutube.Service.TaskService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class TaskController {

    TaskService taskService;

    @ApiOperation(value = "Criando uma nova tarefa")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Tarefa criada com sucesso"),
        @ApiResponse(code = 500, message = "Houve um erro ao criar a tarefa, verifique as informações")
    })

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        log.info("Criando uma nova tarefa com as informações [{}]", task);
        return taskService.createTask(task);
    }

    @ApiOperation(value = "Listando todas as tarefas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Tarefas listadas com sucesso"),
        @ApiResponse(code = 500, message = "Houve um erro ao listar as tarefas, verifique as informações")
    })

    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasks() {
        log.info("Listando todas as tarefas cadastradas");
        return taskService.listAllTasks();
    }

    @ApiOperation(value = "Buscando uma tarefa pelo id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Tarefa encontrada com sucesso"),
        @ApiResponse(code = 404, message = "Não foi encontrada nenhuma tarefa com esse id, verifique as informações")
    })

    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") Long id) {
        log.info("Buscando tarefa com o id [{}]", id);
        return taskService.findTaskById(id);
    }

    @ApiOperation(value = "Atualizando uma tarefa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Tarefa atualizada com sucesso"),
        @ApiResponse(code = 404, message = "Houve um erro ao atualizar a tarefa, verifique as informações")
    })

    @PutMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updateTaskById(@PathVariable(value = "id") Long id, @RequestBody Task task) {
        log.info("Atualizando a tarefa com id [{}], as novas informações são: [{}]", id, task);
        return taskService.updateTaskById(task, id);
    }

    @ApiOperation(value = "Deletando uma nova tarefa")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Tarefa deletada com sucesso"),
        @ApiResponse(code = 404, message = "Houve um erro ao deletar a tarefa, verifique as informações")
    })

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteTaskById(@PathVariable(value = "id") Long id) {
        log.info("Excluindo tarefa com o id [{}]", id);
        return taskService.deleteById(id);
    }
}
