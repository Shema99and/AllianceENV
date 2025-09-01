package com.zifo.ewb.alliance.allianceprocesshelper;

import java.util.ArrayList;
import java.util.List;




/**
 * This class is used to get 
 * the Alliance names from the alliance Details
 * @author Zifo
 */
public final class AllianceNameHelper {
	/**
	 * Method is used to get the Alliance name
	 * @param allianceDetails
	 * @return
	 */
	public static List<String> getAllianceNames(final com.gsk.api.datarestrictionpojo.InputPojo allianceDetails){
		final List<String> allianceNames = new ArrayList<>();
		
		for(final com.gsk.api.datarestrictionpojo.Datum details:allianceDetails.getData()) {
			allianceNames.add(details.getName());
		}
		return allianceNames;
	}
	private AllianceNameHelper() {
		
	}
}

