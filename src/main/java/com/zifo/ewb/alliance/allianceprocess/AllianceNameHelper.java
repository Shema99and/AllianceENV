package com.zifo.ewb.alliance.allianceprocess;

import java.util.ArrayList;
import java.util.List;

import com.gsk.api.datarestrictionpojo.Datum;
import com.gsk.api.datarestrictionpojo.InputPojo;



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
	public static List<String> getAllianceNames(final InputPojo allianceDetails){
		final List<String> allianceNames = new ArrayList<>();
		
		for(final Datum details:allianceDetails.getData()) {
//			allianceNames.add(details.getAgreement().getName());
			allianceNames.add(details.getName());
		}
		return allianceNames;
	}
	private AllianceNameHelper() {
		
	}
}

