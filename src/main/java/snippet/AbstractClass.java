package snippet;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "serviceItemType")
@JsonSubTypes({ @Type(value = ClassA.class, name = "typeA"), @Type(value = ClassB.class, name = "typeB") })

public abstract class AbstractClass {
	public String serviceItemType;
}