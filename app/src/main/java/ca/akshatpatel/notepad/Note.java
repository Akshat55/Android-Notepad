package ca.akshatpatel.notepad;

/**
 * Created by Akshat Patel on 9/4/2017
 */
public class Note {

    private long id;
    private String name;
    private String message;

    //Default constructor - Sets the app to safe empty state
    Note() {
        this.id = -1;
        this.name = null;
        this.message = null;
    }

    //Constructor override
    Note(long id, String name, String message) {
        this.id = id;
        this.name = name;
        this.message = message;
    }

    Note(String name, String message) {
        this.id = -1;
        this.name = name;
        this.message = message;
    }

    //Setters & Getters

    /**
     * @param name The new name to be assigned to the Note object
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @param message The new message to be assigned to the Note object
     */
    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * @return Note name
     */
    public String getName() {
        return this.name;
    }


    /**
     * @return Note message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @return id
     */
    public long getId() {
        return this.id;
    }
}
