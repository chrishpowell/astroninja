/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.aapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class FTDates
{
    @JsonProperty("from")
    private LocalDateTime       fromDT;
    @JsonProperty("list")
    private List<LocalDateTime> listDT;
    @JsonProperty("to")
    private LocalDateTime       toDT;

    public LocalDateTime getFromDT() { return fromDT; }
    public void setFromDT(LocalDateTime fromDT) { this.fromDT = fromDT; }

    public List<LocalDateTime> getListDT() { return listDT; }
    public void setListDT(List<LocalDateTime> listDT) { this.listDT = listDT; }

    public LocalDateTime getToDT() { return toDT; }
    public void setToDT(LocalDateTime toDT) { this.toDT = toDT; }
}
