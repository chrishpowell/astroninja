/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.astronomy;

import com.opencsv.bean.CsvBindByName;


/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class ConstlStarSAC
{
    @CsvBindByName(column="id", required=false)
    private int id;
    
    @CsvBindByName(column="bf", required=true)
    private String bayerFlamsteed;
    
    @CsvBindByName(column="proper", required=true)
    private String properName;
    
    @CsvBindByName(column="ra", required=false)
    private float ra;
    
    @CsvBindByName(column="dec", required=true)
    private float decl;
    
    @CsvBindByName(column="dist", required=true)
    private float distParsecs;
    
    @CsvBindByName(column="mag", required=true)
    private float magnitude;
    
    @CsvBindByName(column="absmag", required=false)
    private float absmag;
    
    @CsvBindByName(column="x", required=true)
    private float x;
    
    @CsvBindByName(column="y", required=true)
    private float y;
    
    @CsvBindByName(column="z", required=true)
    private float z;
    
    @CsvBindByName(column="rarad", required=true)
    private float rarad;
    
    @CsvBindByName(column="decrad", required=true)
    private float decrad;
    
    @CsvBindByName(column="con", required=true)
    private String constellation;
    
}
