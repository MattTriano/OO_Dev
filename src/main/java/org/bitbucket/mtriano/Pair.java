package org.bitbucket.mtriano;

import java.util.ArrayList;

/**
 * Created by Matt on 5/7/2016.
 */
public interface Pair {

    int getDistance() throws InvalidDataException;
    Facility getFacilityA();
    Facility getFacilityB();
    ArrayList<Facility> getPair();

}
