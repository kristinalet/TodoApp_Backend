package com.accenture.letovit.todolist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

		@RequestMapping (value="todos.json", method = RequestMethod.POST)
		public SaveResponse addToDoItem(@RequestBody ToDoItem request) {
			ToDoItemValidator.validate(request);
			
			LocalDateTime now = LocalDateTime.now();
			String prettyDateTime = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(now);
			
			request.setCreatedAt(prettyDateTime);
			
			System.out.println("JeToOno?"+ request);
			String name = UUID.randomUUID().toString();
			//savedToDoItems.put(name , request);
			
			SaveResponse response = new SaveResponse();
			response.setName(name);
			
			 DbToDoItem dbToDoItem = ToDoItemConverter.jsonToDbEntity(request, name);
			   repository.save(dbToDoItem);
			   
		return response;
	
	}
	
	@RequestMapping(value = "todos.json", method = RequestMethod.GET)
	public Map<String, ToDoItem> fetchAllToDoItems() {
		Iterable<DbToDoItem> dbToDoItemList = repository.findAll();
		
		Map<String, ToDoItem> toDoItemsMap = new HashMap<String, ToDoItem>();
		
		for (DbToDoItem dbToDoItem : dbToDoItemList) {
			ToDoItem toDoItem = ToDoItemConverter.dbEntityToJson(dbToDoItem);
		      toDoItemsMap.put(dbToDoItem.getIdentifier(), toDoItem);
		}
		return toDoItemsMap;

		
	}
	
	@RequestMapping(value="todos/{identifier}.json", method = RequestMethod.DELETE)
	public void deteteToDoItem(@PathVariable String identifier) {
		//savedToDoItems.remove(identifier);
		repository.deleteById(identifier);
	}
	
	@RequestMapping(value="todos/{identifier}.json", method = RequestMethod.PATCH)
	public void updateToDoItem(@PathVariable String identifier,
								@RequestBody UpdateRequest requestBody) {
									
		DbToDoItem dbToDoItem = repository.findById(identifier).get();
		   dbToDoItem.setFinished(requestBody.isFinished());
		   repository.save(dbToDoItem);

	}
	

}
