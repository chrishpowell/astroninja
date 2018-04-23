/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 *
 */

package eu.discoveri.astroninja.oodb;

import eu.discoveri.astroninja.exception.ServerAdminException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * This changes depending on underlying oodb.
 * 
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class ServerAdministration
{
    /**
     * Verify a database exists
     * 
     * @param db
     * @return
     * @throws SecurityException
     * @throws IOException 
     */
    public static boolean verifyDatabaseExists( String dbURL )
            throws SecurityException, IOException
    {
        // URL could have properties (eg: objectdb://localhost/astro1.tmp;drop)
        // We want just the dbURL
        int semic  = dbURL.indexOf(';');
        if( semic != -1 )
            dbURL = dbURL.substring(0,semic);
        
        // If Versant
        //return com.versant.admin.ServerAdministration.verifyDatabaseExists(dbURL);
        
        // If ObjectDB (NB: This may not work on NFS etc (cached file handles?)
        Path fullPath = Paths.get(dbURL);
        if( Files.exists(fullPath) )
        { // Check can read
            Files.getOwner(fullPath);
            return true;
        }
        else
            return false;
    }
    
    /**
     * Create a new database
     * 
     * @param dbURL 
     * @throws ServerAdminException
     */
    public static void createDatabase( String dbURL )
            throws ServerAdminException
    {
        // If Versant...
//        try
//        {
//            com.versant.admin.ServerAdministration.createDatabase( dbURL );
//        }
//        catch( com.versant.admin.ServerAdministrationException sex )
//        {
//            throw new ServerAdminException(sex);
//        }
    }
    
    /**
     * Remove/drop a database (Versant)
     * 
     * @param dbURL 
     * @throws ServerAdminException
     */
    public static void removeDatabase( String dbURL )
            throws ServerAdminException
    {
        // If Versant...
//        try
//        {
//            com.versant.admin.ServerAdministration.removeDatabase( dbURL );
//        }
//        catch( com.versant.admin.ServerAdministrationException sex )
//        {
//            throw new ServerAdminException(sex);
//        }
    }
}
