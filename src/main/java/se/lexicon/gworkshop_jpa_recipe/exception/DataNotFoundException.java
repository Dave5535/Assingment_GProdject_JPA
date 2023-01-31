package se.lexicon.gworkshop_jpa_recipe.exception;

public class DataNotFoundException extends RuntimeException{
    
    public DataNotFoundException(String message){
        super(message);
    }
    
}
