package com.example.tyler.firebasepocwriting;

/**
 * Created by Tyler on 5/27/2016.
 */
public class ChatMessage {

    private String author;
    private String message;

    public ChatMessage(){
    }

    public ChatMessage(String author, String message){
        this.message = message;
        this.author = author;
    }

    public String getAuthor(){
        return author;
    }

    public String getMessage(){
        return message;
    }
}
