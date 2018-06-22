package com.chen.arxiv.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by chen on 18-2-26.
 */

@Root(name = "author", strict = false)
public class Author {
    @Element(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
