package se.lexicon.gworkshop_jpa_recipe.exception;

public class DataDuplicateException extends RuntimeException{
    
    public DataDuplicateException( String message ) {
        super(message);
    }
}
