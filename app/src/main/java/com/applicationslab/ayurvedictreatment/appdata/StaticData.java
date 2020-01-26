package com.applicationslab.ayurvedictreatment.appdata;

import com.applicationslab.ayurvedictreatment.datamodel.HerbalPlantsData;

import java.util.ArrayList;

/**
 * Created by user on 6/7/2016.
 */
public class StaticData {

    public static ArrayList<HerbalPlantsData> herbalPlantsDatas;

    public static ArrayList<HerbalPlantsData> getHerbalPlantsDatas() {
        return herbalPlantsDatas;
    }

    public static void setHerbalPlantsDatas(ArrayList<HerbalPlantsData> herbalPlantsDatas) {
        StaticData.herbalPlantsDatas = herbalPlantsDatas;
    }

}
