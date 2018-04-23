/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */
package eu.discoveri.astroninja.i18n;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class I18Ntester
{
    public static void main(String[] args)
    {
        // en_US
        System.out.println("Current Locale: " + Locale.getDefault());
        ResourceBundle mybundle = ResourceBundle.getBundle("en_GB");

        // read MyLabels_en_US.properties
        System.out.println("Say how are you in GB English: " + mybundle.getString("how_are_you"));

        Locale.setDefault(new Locale("fr", "FR"));

        // read MyLabels_ms_MY.properties
        System.out.println("Current Locale: " + Locale.getDefault());
        mybundle = ResourceBundle.getBundle("AstroNinja");
        System.out.println("Say Welcome to AstroNinja in the French language: " + mybundle.getString("welcomeToAstroNinja"));
    }
}
