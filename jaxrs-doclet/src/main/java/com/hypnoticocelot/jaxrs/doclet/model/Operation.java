package com.hypnoticocelot.jaxrs.doclet.model;

import com.google.common.base.Objects;
import com.hypnoticocelot.jaxrs.doclet.parser.AnnotationHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Strings.emptyToNull;

public class Operation {

    private HttpMethod httpMethod;
    private String nickname;
    private String type; // void, primitive, complex or a container
    private String containerType; // type of the container
    private List<ApiParameter> parameters;
    private String summary; // cap at 60 characters for readability in the UI
    private String notes;

    private List<ApiResponseMessage> responseMessages; // swagger 1.2 name

    @SuppressWarnings("unused")
    private Operation() {
    }

    public Operation(Method method) {
        this.httpMethod = method.getMethod();
        this.nickname = emptyToNull(method.getMethodName());
        this.type = emptyToNull(AnnotationHelper.typeOf(method.getReturnType()));
        this.containerType = method.getReturnTypeOf() != null ? emptyToNull(AnnotationHelper.typeOf(method.getReturnTypeOf())) : null;
        this.parameters = method.getParameters().isEmpty() ? null : method.getParameters();
        this.responseMessages = method.getResponseMessages().isEmpty() ? null : method.getResponseMessages();
        this.summary = emptyToNull(method.getFirstSentence());
        this.notes = emptyToNull(method.getComment());
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getNickname() {
        return nickname;
    }

    public String getType() {
        return type;
    }

    public List<ApiParameter> getParameters() {
        return parameters;
    }
    
    public List<ApiResponseMessage> getResponseMessages() {
        return responseMessages;
    }

    public String getSummary() {
        return summary;
    }

    public String getNotes() {
        return notes;
    }


    public Map<String, String> getItems() {
        Map<String, String> result = null;
        if (containerType != null) {
            result = new HashMap<String, String>();
            result.put("$ref", containerType);
        }
        return result;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation that = (Operation) o;
        return Objects.equal(httpMethod, that.httpMethod)
                && Objects.equal(nickname, that.nickname)
                && Objects.equal(type, that.type)
                && Objects.equal(containerType, that.containerType)
                && Objects.equal(parameters, that.parameters)
                && Objects.equal(responseMessages, that.responseMessages)
                && Objects.equal(summary, that.summary)
                && Objects.equal(notes, that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(httpMethod, nickname, type, parameters, responseMessages, summary, notes);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("httpMethod", httpMethod)
                .add("nickname", nickname)
                .add("type", type)
                .add("parameters", parameters)
                .add("responseMessages", responseMessages)
                .add("summary", summary)
                .add("notes", notes)
                .toString();
    }
}
