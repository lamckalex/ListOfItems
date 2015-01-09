package io.github.lamckalex.listofitems;

/**
 * Note Object
 * Created by Alex on 1/7/2015.
 */
public class CustomDataObject {
    String noteTitle;
    String noteContent;
    int id;

    //Emptry constructor
    CustomDataObject()
    {

    }

    //Constructor w/ title and content
    CustomDataObject(String noteTitle, String noteContent)
    {
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
    }

    //Constructor w/ id, title and content
    CustomDataObject(int id, String noteTitle, String noteContent)
    {
        this.id = id;
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
    }

    // getting ID
    public int getID(){
        return this.id;
    }

    // setting id
    public void setID(int id){
        this.id = id;
    }

    // getting note title
    public String getNoteTitle(){
        return this.noteTitle;
    }

    // setting note title
    public void setNoteTitle(String noteTitle){
        this.noteTitle = noteTitle;
    }

    // getting note content
    public String getNoteContent(){
        return this.noteContent;
    }

    // setting note content
    public void setNoteContent(String noteContent){
        this.noteContent = noteContent;
    }
}
