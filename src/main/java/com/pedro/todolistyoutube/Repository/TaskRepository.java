package com.pedro.todolistyoutube.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedro.todolistyoutube.Model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
}
