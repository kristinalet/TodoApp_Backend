package com.accenture.letovit.todolist.database;

import org.springframework.data.repository.CrudRepository;

public interface DbToDoItemRepository extends CrudRepository<DbToDoItem, String> {

}

