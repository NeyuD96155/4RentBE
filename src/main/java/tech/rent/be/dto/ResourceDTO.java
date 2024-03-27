package tech.rent.be.dto;

import tech.rent.be.enums.Type;

public class ResourceDTO{
    Long id;

    Type resourceType;
    String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getResourceType() {
        return resourceType;
    }

    public void setResourceType(Type resourceType) {
        this.resourceType = resourceType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
