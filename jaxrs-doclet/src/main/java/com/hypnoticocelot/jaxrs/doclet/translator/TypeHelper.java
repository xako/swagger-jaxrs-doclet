package com.hypnoticocelot.jaxrs.doclet.translator;

import com.sun.javadoc.Type;

public class TypeHelper {
	private TypeHelper() {} // helper methods only
	
	public static Type extractDropwizardType(Type type) {
		if (type.asClassDoc() != null) {
			Type parentType = type.asClassDoc().superclassType();
	    	if (parentType != null 
	    			&& (parentType.qualifiedTypeName().equals("com.yammer.dropwizard.jersey.params.AbstractParam")
	    					|| parentType.qualifiedTypeName().startsWith("io.dropwizard.jersey.params.AbstractParam"))) {
	    		type = parentType.asParameterizedType().typeArguments()[0];
	    	}
		}
		return type;
	}
}
