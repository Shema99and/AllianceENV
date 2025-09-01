//package com.zifo.ewb.alliance.checkpoints;
//
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.security.KeyManagementException;
//import java.security.KeyStoreException;
//import java.security.NoSuchAlgorithmException;
//import java.security.cert.CertificateException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import javax.xml.bind.JAXBException;
//
//import org.apache.commons.lang3.StringUtils;
//
//import com.gsk.api.dataRestrictionPojo.Datum;
//import com.zifo.ewb.alliance.Services.CatalogService;
//import com.zifo.ewb.alliance.allianceprocesshelper.AddNewMembersHelper;
//import com.zifo.ewb.alliance.allianceprocesshelper.CheckUserAddedHelper;
//import com.zifo.ewb.alliance.allianceprocesshelper.GetAllianceandIDBSUsers;
//import com.zifo.ewb.alliance.allianceprocesshelper.GroupCreatorHelper;
//import com.zifo.ewb.alliance.exceptionhandler.ApiException;
//import com.zifo.ewb.catalogPojo.Tuple;
//
///**
// * In this class we have compared the alliance details with catalog details for
// * any changes, if changes found we delete the existing tuple and create the new
// * with new details
// * 
// * @author Zifo
// *
// */
//public final class CompareAllianceDetails {
//
//	/**
//	 * This method is used to compare the details
//	 * 
//	 * @param userDetails
//	 * @throws ApiException 
//	 */
//	public static String compareDetails(final Datum allianceData, final Tuple tuple, final String catalogId,
//			final List<String> groupName, final Map<String, List<String>> userDetails)
//			throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException,
//			JAXBException, IOException, URISyntaxException, ApiException {
//		String exptRestriction = StringUtils.EMPTY;
//		String phyCmdRestriction = StringUtils.EMPTY;
//		List<String> catAllianceUsers = new ArrayList<>();
//		List<String> catIDBSUsers = new ArrayList<>();
//		final String allianceGroupName = allianceData.getName();
//		final String endUserGroup = "G_GSK_Alliance-" + allianceGroupName + "_EndUser_Edit";
//		final String superUserGroup = "G_GSK_Alliance-" + allianceGroupName + "_SuperUser_Edit";
//		final String readUserGroup = "G_GSK_Alliance-" + allianceGroupName + "_Read";
//		final List<String> allianceMember = userDetails.get("allianceUserList");
//		final List<String> idbsMember = userDetails.get("idbsUserList");
//		for (final com.zifo.ewb.catalogPojo.Datum data : tuple.getData()) {
//			switch (data.getName()) {
//			case "Experiment Restriction":
//				if (data.getValue() != null) {
//					exptRestriction = data.getValue();
//				}
//				break;
//			case "Physical Compound Restriction":
//				if (data.getValue() != null) {
//					phyCmdRestriction = data.getValue();
//				}
//				break;
//			case "Alliance Members":
//				if (data.getValue() != null) {
//					final String[] myArray = data.getValue().split(",");
//					catAllianceUsers = Arrays.asList(myArray);
//				}
//				break;
//			case "IDBS Members":
//				if (data.getValue() != null) {
//					final String[] myArray = data.getValue().split(",");
//					catIDBSUsers = Arrays.asList(myArray);
//				}
//				break;
//			default:
//				break;
//			}
//
//		}
//
//		final List<String> sortedAlliance = allianceMember.stream().sorted().collect(Collectors.toList());
//		final List<String> sortedCatAlliList = catAllianceUsers.stream().sorted().collect(Collectors.toList());
//
//		final List<String> sortedIDBS = idbsMember.stream().sorted().collect(Collectors.toList());
//		final List<String> sortedCatIDBS = catIDBSUsers.stream().sorted().collect(Collectors.toList());
//
//		final Map<String, String> newMemberList = CheckUserAddedHelper.checkUsers(idbsMember, catIDBSUsers);
//		AddNewMembersHelper.addNewUsers(newMemberList, allianceGroupName, groupName); //need to change the Rest client Calls
//
//		final boolean check = GroupCreatorHelper.createGroup(groupName, endUserGroup, readUserGroup,
//				AddNewMembersHelper.removedUserList); //need to change the Rest client Calls
//													  // need to check if we are creating any group? - [Not creating any group only deleting the members in removed List]
//
//		final TupleCreationObject object = new TupleCreationObject();
//		object.setAllianceData(allianceData);
//		object.setTuple(tuple);
//		object.setCatalogId(catalogId);
//		object.setExptRestriction(exptRestriction);
//		object.setPhyCmdRestriction(phyCmdRestriction);
//		object.setSortedIDBS(sortedIDBS);
//		object.setSortedCatIDBS(sortedCatIDBS);
//		object.setSortedAlliance(sortedAlliance);
//		object.setSortedCatAlliList(sortedCatAlliList);
//		object.setCheck(check);
//		return deleteandCreateNewTuple();
//	}
//
//	private static String deleteandCreateNewTuple() throws KeyManagementException, KeyStoreException,
//			NoSuchAlgorithmException, CertificateException, JAXBException, IOException, URISyntaxException, ApiException {   // need to update the web-client
//		System.out.println(TupleCreationObject.tuple.getId());
//		String response = StringUtils.EMPTY;
//		final CatalogService catalogService = new CatalogService();
//		if (TupleCreationObject.check) {
//			final Map<String, List<String>> userDetails = GetAllianceandIDBSUsers
//					.getUsers(TupleCreationObject.allianceData);
////			catalogService.deleteTuples(TupleCreationObject.tuple.getId(), ServiceBase.getEntityAuth(),
////					TupleCreationObject.catalogId);
//			response = CreateNewTupleHelper.createTuple(TupleCreationObject.allianceData, TupleCreationObject.catalogId,
//					userDetails);
//		} else if (!TupleCreationObject.allianceData.getExperimentRestriction().getRestriction()
//				.equals(TupleCreationObject.exptRestriction)) {
//			final Map<String, List<String>> userDetails = GetAllianceandIDBSUsers
//					.getUsers(TupleCreationObject.allianceData);
////			catalogService.deleteTuples(TupleCreationObject.tuple.getId(), ServiceBase.getEntityAuth(),
////					TupleCreationObject.catalogId);
//			response = CreateNewTupleHelper.createTuple(TupleCreationObject.allianceData, TupleCreationObject.catalogId,
//					userDetails);
//		} else if (!TupleCreationObject.allianceData.getPhysicalCompoundRestriction().getRestriction()
//				.equals(TupleCreationObject.phyCmdRestriction)) {
//			final Map<String, List<String>> userDetails = GetAllianceandIDBSUsers
//					.getUsers(TupleCreationObject.allianceData);
////			catalogService.deleteTuples(TupleCreationObject.tuple.getId(), ServiceBase.getEntityAuth(),
////					TupleCreationObject.catalogId);
//			response = CreateNewTupleHelper.createTuple(TupleCreationObject.allianceData, TupleCreationObject.catalogId,
//					userDetails);
//		} else if (TupleCreationObject.sortedIDBS.equals(TupleCreationObject.sortedCatIDBS) == false) {
//			final Map<String, List<String>> userDetails = GetAllianceandIDBSUsers
//					.getUsers(TupleCreationObject.allianceData);
////			catalogService.deleteTuples(TupleCreationObject.tuple.getId(), ServiceBase.getEntityAuth(),
////					TupleCreationObject.catalogId);
//			response = CreateNewTupleHelper.createTuple(TupleCreationObject.allianceData, TupleCreationObject.catalogId,
//					userDetails);
//		} else if (TupleCreationObject.sortedAlliance.equals(TupleCreationObject.sortedCatAlliList) == false) {
//			final Map<String, List<String>> userDetails = GetAllianceandIDBSUsers
//					.getUsers(TupleCreationObject.allianceData);
////			catalogService.deleteTuples(TupleCreationObject.tuple.getId(), ServiceBase.getEntityAuth(),
////					TupleCreationObject.catalogId);
//			response = CreateNewTupleHelper.createTuple(TupleCreationObject.allianceData, TupleCreationObject.catalogId,
//					userDetails);
//		}
//		return response;
//	}
//
////	private CompareAllianceDetails() {
////
////	}
//
//}
