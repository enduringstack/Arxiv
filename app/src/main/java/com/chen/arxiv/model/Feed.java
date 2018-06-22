package com.chen.arxiv.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by chen on 18-2-26.
 */

@Root(name = "feed", strict = false)
public class Feed {

    @Element(name = "id")
    private String id;


    @ElementList(name = "entry", inline = true)
    private List<Entry> entry;

    public String getId() {
        return id;
    }

    public List<Entry> getEntry() {
        return entry;
    }
}

