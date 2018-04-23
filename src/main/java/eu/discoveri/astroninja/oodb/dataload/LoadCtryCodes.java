/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.oodb.dataload;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * https://github.com/lukes/ISO-3166-Countries-with-Regional-Codes or
 * https://www.iso.org/iso-3166-country-codes.html
 * 
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class LoadCtryCodes
{
    private static final String CTRY_CSV_FILE = "/home/chrispowell/Documents/AstroTurf/geohash/ISO-3166-Countries-with-Regional-Codes-master/all/all.csv";
    
    public static void main(String[] args)
            throws IOException
    {
        try (
            Reader reader = Files.newBufferedReader(Paths.get(CTRY_CSV_FILE));
            CSVReader csvReader = new CSVReader(reader);
        ) {
            // Reading Records One by One in a String array
            // name,alpha-2,alpha-3,country-code,iso_3166-2,region,sub-region,region-code,sub-region-code
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                System.out.println("Name : " + nextRecord[0]);          // Persist
                System.out.println("Alpha-2 : " + nextRecord[1]);       // Persist
                System.out.println("Alpha-3 : " + nextRecord[2]);
                System.out.println("Ctry code : " + nextRecord[3]);     // Persist
                System.out.println("ISO 3166-2 : " + nextRecord[4]);    // -> subregion list?
                System.out.println("Region : " + nextRecord[5]);
                System.out.println("Sub-region : " + nextRecord[6]);
                System.out.println("Region code : " + nextRecord[7]);
                System.out.println("Sub-region code : " + nextRecord[8]);
                System.out.println("==========================");
            }
        }
    }
}
