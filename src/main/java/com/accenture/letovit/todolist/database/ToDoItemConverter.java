package com.accenture.letovit.todolist.database;

import com.accenture.letovit.todolist.ToDoItem;

public class ToDoItemConverter {
	public static DbToDoItem jsonToDbEntity(ToDoItem input, String identifier) {
		DbToDoItem output = new DbToDoItem();

		output.setCreatedAt(input.getCreatedAt());
		output.setFinished(input.isFinished());
		output.setText(input.getText());
		output.setTitle(input.getTitle());

		output.setIdentifier(identifier);

		return output;
	}

	public static ToDoItem dbEntityToJson(DbToDoItem input) {
		ToDoItem output = new ToDoItem();

		output.setCreatedAt(input.getCreatedAt());
		output.setFinished(input.isFinished());
		output.setText(input.getText());
		output.setTitle(input.getTitle());
		
		output.setId(input.getIdentifier());

		return output;
	}
}
