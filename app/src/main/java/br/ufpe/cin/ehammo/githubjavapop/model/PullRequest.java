
package br.ufpe.cin.ehammo.githubjavapop.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PullRequest {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("html_url")
    @Expose
    private String htmlUrl;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("user")
    @Expose
    private Owner owner;
    @SerializedName("body")
    @Expose
    private String body;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
