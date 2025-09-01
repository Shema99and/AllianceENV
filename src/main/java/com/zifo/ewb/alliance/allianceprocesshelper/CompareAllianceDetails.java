package com.zifo.ewb.alliance.allianceprocesshelper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.gsk.api.datarestrictionpojo.Datum;
import com.zifo.ewb.alliance.exceptionhandler.ApiException;
import com.zifo.ewb.catalogpojo.Tuple;

/**
 * In this class we have compared the alliance details with catalog details for
 * any changes, if changes found we delete the existing tuple and create the new
 * with new details
 * 
 * @author Zifo
 *
 */
public final class CompareAllianceDetails {
	private CompareAllianceDetails() {
	}

	/**
	 * This method is used to compare the details
	 * 
	 * @param userDetails
	 * @throws ApiException 
	 */
	public static String compareDetails(final Datum allianceData, final Tuple tuple, final String catalogId,
			final List<String> groupName, final Map<String, List<String>> userDetails)
			throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException,
			IOException, URISyntaxException, ApiException {
		String exptRestriction = StringUtils.EMPTY;
		String phyCmdRestriction = StringUtils.EMPTY;
		List<String> catAllianceUsers = new ArrayList<>();
		List<String> catIDBSUsers = new ArrayList<>();
		final String allianceGroupName = allianceData.getName();
		final String endUserGroup = "G_GSK_Alliance-" + allianceGroupName + "_EndUser_Edit";
//		final String superUserGroup = "G_GSK_Alliance-" + allianceGroupName + "_SuperUser_Edit";
		final String readUserGroup = "G_GSK_Alliance-" + allianceGroupName + "_Read";
		final List<String> allianceMember = userDetails.get("allianceUserList");
		final List<String> idbsMember = userDetails.get("idbsUserList");
		for (final com.zifo.ewb.catalogpojo.Datum data : tuple.getData()) {
			switch (data.getName()) {
			case "Experiment Restriction":
				if (data.getValue() != null) {
					exptRestriction = data.getValue();
				}
				break;
			case "Physical Compound Restriction":
				if (data.getValue() != null) {
					phyCmdRestriction = data.getValue();
				}
				break;
			case "Alliance Members":
				if (data.getValue() != null) {
					final String[] myArray = data.getValue().split(",");
					catAllianceUsers = Arrays.asList(myArray);
				}
				break;
			case "IDBS Members":
				if (data.getValue() != null) {
					final String[] myArray = data.getValue().split(",");
					catIDBSUsers = Arrays.asList(myArray);
				}
				break;
			default:
				break;
			}

		}

		final List<String> sortedAlliance = allianceMember.stream().sorted().collect(Collectors.toList());
		final List<String> sortedCatAlliList = catAllianceUsers.stream().sorted().collect(Collectors.toList());

		final List<String> sortedIDBS = idbsMember.stream().sorted().collect(Collectors.toList());
		final List<String> sortedCatIDBS = catIDBSUsers.stream().sorted().collect(Collectors.toList());

		final Map<String, String> newMemberList = CheckUserAddedHelper.checkUsers(idbsMember, catIDBSUsers);
		AddNewMembersHelper.addNewUsers(newMemberList, allianceGroupName, groupName); //need to change the Rest client Calls

		final boolean check = GroupCreatorHelper.UpdateGroup(groupName, endUserGroup, readUserGroup,
				AddNewMembersHelper.removedUserList); //need to change the Rest client Calls
													  // need to check if we are creating any group? - [Not creating any group only deleting the members in removed List]

		final TupleCreationObject object = new TupleCreationObject();
		object.setAllianceData(allianceData);
		object.setTuple(tuple);
		object.setCatalogId(catalogId);
		object.setExptRestriction(exptRestriction);
		object.setPhyCmdRestriction(phyCmdRestriction);
		object.setSortedIDBS(sortedIDBS);
		object.setSortedCatIDBS(sortedCatIDBS);
		object.setSortedAlliance(sortedAlliance);
		object.setSortedCatAlliList(sortedCatAlliList);
		object.setCheck(check);
		return deleteandCreateNewTuple();
	}

	private static String deleteandCreateNewTuple() throws 
//	KeyManagementException, KeyStoreException, NoSuchAlgorithmException, IOException, URISyntaxException, CertificateException{   // need to update the web-client
		ApiException {
		TupleCreationObject tupleCreationObject = new TupleCreationObject();
		System.out.println(tupleCreationObject.getTuple().getId());
		String response = StringUtils.EMPTY;
		if (tupleCreationObject.isCheck()) {
			final Map<String, List<String>> userDetails = GetAllianceandIDBSUsers
					.getUsers(tupleCreationObject.getAllianceData());
//			catalogService.deleteTuples(TupleCreationObject.tuple.getId(), ServiceBase.getEntityAuth(),
//					TupleCreationObject.catalogId);
			response = CreateNewTupleHelper.createTuple(tupleCreationObject.getAllianceData(), tupleCreationObject.getCatalogId(),
					userDetails);
		} else if (!tupleCreationObject.getAllianceData().getExperimentRestriction().getRestriction()
				.equals(tupleCreationObject.getExptRestriction())) {
			final Map<String, List<String>> userDetails = GetAllianceandIDBSUsers
					.getUsers(tupleCreationObject.getAllianceData());
//			catalogService.deleteTuples(TupleCreationObject.tuple.getId(), ServiceBase.getEntityAuth(),
//					TupleCreationObject.catalogId);
			response = CreateNewTupleHelper.createTuple(tupleCreationObject.getAllianceData(), tupleCreationObject.getCatalogId(),
					userDetails);
		} else if (!tupleCreationObject.getAllianceData().getPhysicalCompoundRestriction().getRestriction()
				.equals(tupleCreationObject.getPhyCmdRestriction())) {
			final Map<String, List<String>> userDetails = GetAllianceandIDBSUsers
					.getUsers(tupleCreationObject.getAllianceData());
//			catalogService.deleteTuples(TupleCreationObject.tuple.getId(), ServiceBase.getEntityAuth(),
//					TupleCreationObject.catalogId);
			response = CreateNewTupleHelper.createTuple(tupleCreationObject.getAllianceData(), tupleCreationObject.getCatalogId(),
					userDetails);
		} else if (tupleCreationObject.getSortedIDBS().equals(tupleCreationObject.getSortedCatAlliList()) == false) {
			final Map<String, List<String>> userDetails = GetAllianceandIDBSUsers
					.getUsers(tupleCreationObject.getAllianceData());
//			catalogService.deleteTuples(TupleCreationObject.tuple.getId(), ServiceBase.getEntityAuth(),
//					TupleCreationObject.catalogId);
			response = CreateNewTupleHelper.createTuple(tupleCreationObject.getAllianceData(), tupleCreationObject.getCatalogId(),
					userDetails);
		} else if (tupleCreationObject.getSortedIDBS().equals(tupleCreationObject.getSortedCatAlliList()) == false) {
			final Map<String, List<String>> userDetails = GetAllianceandIDBSUsers
					.getUsers(tupleCreationObject.getAllianceData());
//			catalogService.deleteTuples(TupleCreationObject.tuple.getId(), ServiceBase.getEntityAuth(),
//					TupleCreationObject.catalogId);
			response = CreateNewTupleHelper.createTuple(tupleCreationObject.getAllianceData(), tupleCreationObject.getCatalogId(),
					userDetails);
		}
		return response;
	}


}
