package validation;

import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.constraints.NotEmpty;

public class SizeEmptyValidator {

    
    public static void main(String[] args) {
        
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        
        A a = new A();
        
        System.out.println(validator.validate(a));
        
    }
    
    
    public static class A {
        
        
        @NotEmpty
        String bla;
    }
}
