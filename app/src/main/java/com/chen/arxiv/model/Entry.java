package com.chen.arxiv.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by chen on 18-2-26.
 */

@Root(name = "entry", strict = false)
public class Entry {
    @Element(name = "updated")
    private String updated;
    @Element(name = "published")
    private String published;
    @Element(name = "title")
    private String title;
    @Element(name = "summary")
    private String summary;
    @Element(name = "id")
    private String id;
    @ElementList(name = "author", inline = true)
    private List<Author> author;

    public String getUpdated() {
        return updated;
    }

    public String getPublished() {
        return published;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getId() {
        return id;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }
}
