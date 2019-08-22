package com.accenture.letovit.todolist;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.letovit.todolist.database.DbToDoItem;
import com.accenture.letovit.todolist.database.DbToDoItemRepository;
import com.accenture.letovit.todolist.database.ToDoItemConverter;
import com.accenture.letovit.todolist.validator.ToDoItemValidator;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ToDoListControler {
	private DbToDoItemRepository repository;
	
	public ToDoListControler(DbToDoItemRepository repository) {
	      this.repository = repository;
	}

		@RequestMapping (value="todos", method = RequestMethod.POST)
		public String addToDoItem(@RequestBody ToDoItem request) {
			//validate incoming request with our validator
			ToDoItemValidator.validate(request);
			
			//generate response object with the todo item name (which is generated id)
			LocalDateTime now = LocalDateTime.now();
			String prettyDateTime = DateTimeFormatter.ISO_DATE_TIME.format(now);
			
			request.setCreatedAt(prettyDateTime);
			
			System.out.println("JeToOno?"+ request);
			String name = UUID.randomUUID().toString();
			//savedToDoItems.put(name , request);
			
			 DbToDoItem dbToDoItem = ToDoItemConverter.jsonToDbEntity(request, name);
			   repository.save(dbToDoItem);
			   
		return name;
	
	}
	
	@RequestMapping(value = "todos", method = RequestMethod.GET)
	public List<ToDoItem> fetchAllToDoItems() {
		//fetch all data from database table
		Iterable<DbToDoItem> dbToDoItemList = repository.findAll();
		//create empty list that will be converted to json
		List<ToDoItem> toDoItems = new ArrayList<ToDoItem>();
		//fill our list with data from database
		for (DbToDoItem dbToDoItem : dbToDoItemList) {
			toDoItems.add(ToDoItemConverter.dbEntityToJson(dbToDoItem));
		}
		return toDoItems;

		
	}
	
	@RequestMapping(value="todos/{identifier}", method = RequestMethod.DELETE)
	public void deteteToDoItem(@PathVariable String identifier) {
		//savedToDoItems.remove(identifier);
		repository.deleteById(identifier);
	}
	
	@RequestMapping(value="todos/{identifier}", method = RequestMethod.PATCH)
	public void updateToDoItem(@PathVariable String identifier,
								@RequestBody UpdateRequest requestBody) {
									
		DbToDoItem dbToDoItem = repository.findById(identifier).get();
		   dbToDoItem.setFinished(requestBody.isFinished());
		   repository.save(dbToDoItem);

	}
	

}
