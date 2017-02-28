package validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.MessageInterpolator;
import javax.validation.Validator;

import org.hibernate.validator.constraints.NotEmpty;

import io.dropwizard.jersey.validation.Validators;

public class ListBeanValidationTest {

	public static void main(String[] args) {
		List<Bean> asList = Arrays.asList(new Bean("test"), new Bean("test2"));
		
		MsgInterpolator nameResolver = new MsgInterpolator();
		Validator v = Validators.newConfiguration().buildValidatorFactory().getValidator();
		
		// this is what DW does but i am lazy so you get the main version
		if(asList instanceof Iterable) {
			Iterable values = (Iterable) asList;
			Set<ConstraintViolation<Object>> violations = new HashSet<>();
	        for(Object value : values) {
	            violations.addAll(v.validate(value));
	        }
	        
	        violations.forEach(v2 -> System.out.println(v2.getMessage()));
		}
	}
	
	public static class Bean {
		
		Bean(String name) {
			this.name = name;
		}
		
		String name;
		
		@NotEmpty(message="bean not empty ${validatedValue}")
		String wrong;
	}
	
	public static class MsgInterpolator implements MessageInterpolator {

		@Override
		public String interpolate(String messageTemplate, Context context) {
			return interpolate(messageTemplate, context, Locale.getDefault());
		}

		@Override
		public String interpolate(String messageTemplate, Context context, Locale locale) {
			Object validatedValue = context.getValidatedValue();
			
			
			return null;
		}
		
	}
	
}
