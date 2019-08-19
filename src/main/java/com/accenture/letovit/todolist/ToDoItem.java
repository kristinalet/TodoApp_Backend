package com.accenture.letovit.todolist;

public class ToDoItem {

	private String title;
	private String text;
	private boolean finished;
	private String createdAt;
	
	//Generated
	@Override
	public String toString() {
		return "ToDoItem [title=" + title + ", text=" + text + ", finished=" + finished + ", createdAt=" + createdAt
				+ "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
	

}
