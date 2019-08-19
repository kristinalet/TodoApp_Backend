package com.accenture.letovit.todolist.validator;

import com.accenture.letovit.todolist.ToDoItem;

public class ToDoItemValidator {
	
	public static void validate(ToDoItem toDoItem) {
		if (toDoItem.getTitle().length() > 15) {
			throw new RuntimeException("Chyba, titulok bol dlhy");
		}
		if (toDoItem.getText().length() > 15) {
			throw new RuntimeException("Chyba, text bol dlhy");
		}
		for(int i=0; i<toDoItem.getTitle().length(); i++) {
			Character znak = toDoItem.getTitle().charAt(i);
			if (!Character.isLetterOrDigit(znak) && !Character.isSpaceChar(znak)) {
				throw new RuntimeException("Znak nie je, ani pismeno, ani cislo");
			}
		}
	}

}
