/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */
package eu.discoveri.astroninja.astronomy.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class AAToken
{
    private static String  data,
                           tokenStr;
    
    /*
     * Unpack the incoming json.
     * @param data 
     */
    @JsonProperty("data")
    private void unpackNested(Map<String, Object> data)
    {
        tokenStr = (String) data.get("token");
    }

    /**
     * Get the token string.
     * 
     * @return 
     */
    public static String getTokenString() { return tokenStr; }
}
