package dw.introspec;

import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

import dw.introspec.VersioningProperties.Property;

public class VersioningPropertiesIntrospector extends JacksonAnnotationIntrospector {
    
	private static final long serialVersionUID = 1L;
	private String version;

    public VersioningPropertiesIntrospector(String version) {
        this.version = version;
    }
    
    @Override
    public PropertyName findNameForSerialization(Annotated a) {
    	 PropertyName propertyName = findNameFromVersioningProperties(a);
         if (propertyName != null) {
             return propertyName;
         }
    	return super.findNameForSerialization(a);
    }
    
    @Override
    public PropertyName findNameForDeserialization(Annotated a) {
    	 PropertyName propertyName = findNameFromVersioningProperties(a);
         if (propertyName != null) {
             return propertyName;
         }
    	return super.findNameForDeserialization(a);
    }
    
    private PropertyName findNameFromVersioningProperties(Annotated a) {
        VersioningProperties annotation = a.getAnnotation(VersioningProperties.class);
        if (annotation == null) {
            return null;
        }
        for (Property property : annotation.value()) {
            if (version.equals(property.version())) {
                return new PropertyName(property.value());
            }
        }
        return null;
    }

}