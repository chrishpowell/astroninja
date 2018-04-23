/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.aapi;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class Data
{
    @JsonProperty("dates")
    private FTDates     ftDates;
    private Observer    observer;
    private Rows        rows;
    
    public FTDates getFTdates() { return ftDates; }
    public void setFTdates(FTDates ftDates) { this.ftDates = ftDates; }

    public Observer getObserver() { return observer; }
    public void setObserver(Observer observer) { this.observer = observer; }

    public Rows getRows() { return rows; }
    public void setRows(Rows rows) { this.rows = rows; }
}
