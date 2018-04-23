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
public class Data
{
    @JsonProperty("obj.1")
    private Obj1 obj1;
    @JsonProperty("obj.2")
    private Obj2 obj2;
    private List<RObj3> robj3;

    public Obj1 getObj1() { return obj1; }
    public void setObj1(Obj1 obj1) { this.obj1 = obj1; }
    
    public Obj2 getObj2() { return obj2; }
    public void setObj2(Obj2 obj2) { this.obj2 = obj2; }
    
    public List<RObj3> getRobj3() { return robj3; }
    public void setRobj3(List<RObj3> robj3) { this.robj3 = robj3; }
}
