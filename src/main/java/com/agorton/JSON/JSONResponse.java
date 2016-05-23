package com.agorton.JSON;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author andrewgorton
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JSONResponse {
    
    @JsonProperty
    private Search Search;

    public JSONResponse() {
    }
    
    public Search getSearch() {
        return Search;
    }

    public void setSearch(Search Search) {
        this.Search = Search;
    }

    @Override
    public String toString() {
        if (this.Search != null){
            return this.Search.toString();
        } else {
            return "Object is empty";
        }
    }
}
