package com.zifo.ewb.alliance.allianceprocesshelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsk.api.datarestrictionpojo.Datum;
import com.zifo.ewb.alliance.serviceh.EWBService;
import com.zifo.ewb.catalogpojo.Tuple;

/**
 * This class is used to create the new tuple
 * 
 * @author Zifo
 *
 */
public final class CreateNewTupleHelper {
	/**
	 * Field logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CreateNewTupleHelper.class.getName());
	/**
	 * Field comma
	 */
	private static final String COMMA = ",";

	/**
	 * method to create new tuple
	 * 
	 * @param allianceData @param catalogId
	 * @param userDetails
	 * @return
	 */
	public static String createTuple(final Datum allianceData, final String catalogId,
			Map<String, List<String>> userDetails) {
		EWBService ewbService = new EWBService();
		String response = StringUtils.EMPTY;
		
		try {
			final Tuple input = new Tuple();
			input.setId(catalogId);
			input.setDefaultTuple(false);
			input.setDeletable(true);
			input.setEditable(true);
			input.setEnabled(true);

			final List<com.zifo.ewb.catalogpojo.Datum> datalist = new ArrayList<>();
			final com.zifo.ewb.catalogpojo.Datum datum1 = new com.zifo.ewb.catalogpojo.Datum();
			datum1.setName("Alliance Name");
			datum1.setValue(allianceData.getName());
			datum1.setDisplayValue(allianceData.getName());
			datalist.add(datum1);

			input.setData(datalist);
			final ObjectMapper obj = new ObjectMapper();
			final String reqinput = obj.writeValueAsString(input);

//			final CatalogService service = new CatalogService();
			final String tupleCreatedRes = ewbService.createTuple(catalogId, reqinput);
			if (LOGGER.isInfoEnabled()) {
//				LOGGER.info("Tuple Creation Details :" + reqinput);
//				LOGGER.info("Tuple Created Response " + tupleCreatedRes);
				LOGGER.info("Tuple Creation Details: {}", reqinput);
				LOGGER.info("Tuple Created Response {}", tupleCreatedRes);
			}
		}

		catch (final Exception ex) {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info(ex.getMessage());
			}
		}
		return response;

	}

	private CreateNewTupleHelper() {

	}
}
