//package com.zifo.ewb.alliance.checkpoints;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import com.zifo.ewb.catalogPojo.CatalogDetailPojo;
//import com.zifo.ewb.catalogPojo.Datum;
//import com.zifo.ewb.catalogPojo.Tuple;
//
//
///**
// * this class is used to get 
// * what are the alliances present in catalog
// * @author Zifo
// */
//public final class CatlogAllianceNamesHelper {
//	
//	/**
//	 * method user to get the alliance name in catalog
//	 * @param catalogDetail
//	 * @return
//	 */
//	public static Map<String, String> getAllianceNames(final CatalogDetailPojo catalogDetail){
//		final Map<String,String> catAllianceNames = new HashMap<>();
//		for(final Tuple tuple:catalogDetail.getTuple()) {
//			for(final Datum data:tuple.getData()) {
//				if("Alliance Name".equals(data.getName())) {
//					catAllianceNames.put(data.getValue(), tuple.getId());
//				}
//			}
//		}
//		return catAllianceNames;
//	}
//	private CatlogAllianceNamesHelper() {
//		
//	}
//}
//
