/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.aapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.discoveri.astroninja.astronomy.utils.AAToken;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.ProtocolException;
import java.net.URL;
import java.net.HttpURLConnection;


/**
 * Get planet positions etc. from astronomyapi.org.
 * 
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class BodyPositions
{
    
    /**
     * Get a JWT token.
     * 
     * @return
     * @throws ProtocolException
     * @throws IOException 
     */
    public static AAToken getTokenAA()
            throws ProtocolException, IOException
    {
        // JWT setup
        String AASERVER = "http://api.astronomyapi.org/auth?app_id=318beb95-f784-4b5a-8132-b1287a35fca4&app_secret=8e67be7f4acad5681ab3558610d8b50f9358e0c3";
        AAToken aaToken;
        
        // Get response from remote server
        String resp = getResponse(AASERVER);
        
        // Instantiate AAtoken via ObjectMapper
        aaToken = new ObjectMapper().readValue(resp,AAToken.class);
        return aaToken;
    }
    
    /**
     * Get solar system body positions.
     * 
     * @return
     * @throws ProtocolException
     * @throws IOException 
     */
    public static PositionsBodies getPositionsBodies()
            throws ProtocolException, IOException
    {
        // Ste up filter
        String filter = String.format("?lon=%f&lat=%f&from_year=%d&from_month=%02d&from_day=%02d&to_year=%d&to_month=%02d&to_day=%02d&hour=%02d&minute=%02d",
                                       42.4304f,19.2594f,2018,4,20,2018,4,21,0,0);
        
        // Positions
        String AAPOSNS = "http://api.astronomyapi.org/positions"+filter;

        // Get response from remote server
        String resp = getResponse(AAPOSNS, AAToken.getTokenString());
        
        // Ok, get ready to map the returned Json
        JsonNode positions = new ObjectMapper().readTree(resp);
        
        // Instantiate PositionsBodies
        PositionsBodies pb = new PositionsBodies();

        return pb;
    }
    
    /*
     * Get a response from astronomyapi.org
     */
    private static String getResponse( String get )
            throws ProtocolException, IOException
    {
        return getResponse( get, "" );
    }
    
    /*
     * Get a response with token
     */
    private static String getResponse( String get, String token )
            throws ProtocolException, IOException
    {
        // Connection
        HttpURLConnection con;
        // Response
        StringBuilder resp = new StringBuilder();
        
        // Open the connection
        con = (HttpURLConnection)(new URL(get).openConnection());
        
        // Set for GET requests
        con.setRequestMethod("GET");
        
        // Add Authorization
        if( !token.isEmpty() )
        {
            con.setRequestProperty("Authorization", "Bearer " +token);
            con.setRequestProperty("Accept", "application/json");
        }

        // This does a GET
        int respCode = con.getResponseCode();
        if( respCode >= 400 )
        {
            // Read the error
            try( BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream())) )
            {
                String inLine;
                while( (inLine = in.readLine()) != null )
                {
                    resp.append(inLine);
                }
            } catch( EOFException errx )
            {
                errx.printStackTrace();
                System.exit(1);
            }
            
            System.out.println("Error..> " +resp.toString());
            System.exit(2);
        }
        
        // Read the response
        try( BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())) )
        {
            String inLine;
            while( (inLine = in.readLine()) != null )
            {
                resp.append(inLine);
            }
        } catch( EOFException eofx )
        {
            eofx.printStackTrace();
            System.out.println("Resp..> " +resp.toString());
            System.exit(3);
        }
        
        return resp.toString();
    }
    
    
    /**
     * M A I N
     * -------
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args)
            throws Exception
    {
        BodyPositions.getTokenAA();
        System.out.println( "Token: [" +AAToken.getTokenString()+ "]" );
        
        PositionsBodies pb = getPositionsBodies();
        System.out.println("Bodies: Dates: [" +pb.getData().getFTdates().getFromDT() +"] to [" +pb.getData().getFTdates().getToDT() +"]");
    }
}
