/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.aapi;

import java.util.List;

/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class Rows
{
    private String          id;
    private String          name;
    private List<Columns>   columns;

    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public List<Columns> getColumns() { return columns; }
    public void setColumns(List<Columns> columns) { this.columns = columns; }
}
