/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.test.throwaway;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class Obj1
{
    @JsonProperty("attr.1")
    private String attr1;
    @JsonProperty("list.1")
    private List<String> list1;

    public String getAttr1() { return attr1; }
    public void setAttr1(String attr1) { this.attr1 = attr1; }

    public List<String> getList1() { return list1; }
    public void setList1(List<String> list1) { this.list1 = list1; }
}
