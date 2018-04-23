/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.test.throwaway;

import javax.persistence.Embeddable;


/**
 * @author Chris Powell, Discoveri OU
 */
@Embeddable
public class Name
{
    private String firstName;
    private String lastName;

    public Name(){}
    public Name(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}
