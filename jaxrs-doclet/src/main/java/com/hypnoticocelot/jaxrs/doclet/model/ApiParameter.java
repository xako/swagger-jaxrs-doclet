package com.hypnoticocelot.jaxrs.doclet.model;

import com.google.common.base.Objects;
import com.hypnoticocelot.jaxrs.doclet.parser.AnnotationHelper;

public class ApiParameter extends Property {
    private String paramType;
    private String name;

    @SuppressWarnings("unused")
    private ApiParameter() {}
    
    public ApiParameter(String paramType, String name, String description, String dataType, String containerOf) {
    	super(dataType, description, containerOf);
        this.paramType = paramType;
        this.name = name;
    }
    
    @Override
    public String getType() {
    	return AnnotationHelper.isContainerType(type) && paramType.equals("query") ? containerOf : type;
    }

    public String getParamType() {
        return paramType;
    }

    public String getName() {
        return name;
    }

    public boolean getRequired() {
        return !paramType.equals("query");
    }

    public boolean getAllowMultiple() {
    	return AnnotationHelper.isContainerType(type) && paramType.equals("query");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ApiParameter that = (ApiParameter) o;
    	return super.equals(o)
                && Objects.equal(name, that.name)
                && Objects.equal(paramType, that.paramType);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(paramType, name, getType(), getDescription(), getItems());
    }

    @Override
    public String toString() {
        return super.toString()
        	+ Objects.toStringHelper(this)
                .add("name", name)
                .add("paramType", paramType)
                .add("allowMultiple", getAllowMultiple())
                .toString();
    }
}
