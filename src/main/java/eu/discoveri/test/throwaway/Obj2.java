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
public class Obj2
{
    @JsonProperty("attr.2")
    private String attr2;
    @JsonProperty("list.2")
    private List<List2> list2;

    public String getAttr2() { return attr2; }
    public void setAttr2(String attr2) { this.attr2 = attr2; }

    public List<List2> getList2() { return list2; }
    public void setList2(List<List2> list2) { this.list2 = list2; }
}
