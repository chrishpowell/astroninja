/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.aapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class Columns
{
    private String          id;
    private String          name;
    private LocalDateTime   date;
    private Dist            dist;
    private Meta            meta;
    @JsonProperty("pos")
    private Positions       posn;

    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public Dist getDist() { return dist; }
    public void setDist(Dist dist) { this.dist = dist; }

    public Meta getMeta() { return meta; }
    public void setMeta(Meta meta) { this.meta = meta; }

    public Positions getPosn() { return posn; }
    public void setPosn(Positions posn) { this.posn = posn; }
}
