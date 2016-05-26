package org.bitbucket.mtriano.Facility;

import org.bitbucket.mtriano.Facility.Facility;

import java.util.ArrayList;

/**
 * Facility Loader Interface
 */
public interface FacilityLoader {

    void loadNetworkFromFile(String filePath);
    ArrayList<Facility> getFacilities();
}
