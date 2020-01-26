package com.applicationslab.ayurvedictreatment.datamodel;

import java.util.ArrayList;

/**
 * Created by user on 6/19/2016.
 */
public class DiagnosisData {

    private String disease;
    private String primarySymptom;
    private ArrayList<String> secondarySymptoms;

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getPrimarySymptom() {
        return primarySymptom;
    }

    public void setPrimarySymptom(String primarySymptom) {
        this.primarySymptom = primarySymptom;
    }

    public ArrayList<String> getSecondarySymptoms() {
        return secondarySymptoms;
    }

    public void setSecondarySymptoms(ArrayList<String> secondarySymptoms) {
        this.secondarySymptoms = secondarySymptoms;
    }

}
