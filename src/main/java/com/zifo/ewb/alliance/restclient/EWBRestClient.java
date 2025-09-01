package com.zifo.ewb.alliance.restclient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.zifo.ewb.alliance.exceptionhandler.ApiException;

@Service
public class EWBRestClient {

	private final WebClient restClient;
	private static final Logger logger = LogManager.getLogger(EWBRestClient.class);

	public EWBRestClient(@Value("${idbs.hostUrl}") String hostUrl, @Value("${idbs.apiBaseUrl}") String apiBaseUrl,
			@Value("${idbs.authorization}") String authorization) {

		restClient = WebClient.builder().baseUrl(hostUrl + apiBaseUrl)
				.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.AUTHORIZATION, authorization).exchangeStrategies(ExchangeStrategies.builder() // Increasing
																															// size
																															// of
																															// the
																															// response
																															// accepted
																															// to
																															// 16MB
						.codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024)).build())
				.build();
	}

	// {GET} - Retrieve the GUID
	public String getGuId(String path) throws ApiException {
		try {
			ResponseEntity<String> response = restClient.get()
					.uri(uriBuilder -> uriBuilder.path("/catalog/id").queryParam("path", "{Path}").build(path))
					.retrieve().toEntity(String.class).block();
			if (response == null || response.getBody() == null) {
	            throw new ApiException("GUID response is empty or null", HttpStatus.NO_CONTENT);
	        }
			
			return response.getBody();
		} catch (WebClientResponseException e) {
			throw new ApiException("Error while Retrieving the GUID", HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
	}

	// {GET} - Retrieve the Group Name
	public String getGroupName() throws ApiException {
//		ResponseEntity<String> response = null;
		try {
			ResponseEntity<String>response = restClient.get().uri("/security/administration/groups/").retrieve().toEntity(String.class)
					.block();
			if (response == null || response.getBody() == null) {
	            throw new ApiException("Group Name response is empty or null", HttpStatus.NO_CONTENT);
	        }
			return response.getBody();
		} catch (WebClientResponseException e) {
			throw new ApiException("Error while Retrieving the groups", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// {GET} - Retrieve the Catalog Details
	public String getCatalogDetails(String catalogId) throws ApiException {
//		ResponseEntity<String> response = null;
		try {
		ResponseEntity<String> response = restClient.get().uri("/catalog/{catalogId}/tuples", catalogId).retrieve().toEntity(String.class)
					.block();
		if (response == null || response.getBody() == null) {
	            throw new ApiException("Catalog Details response is empty or null", HttpStatus.NO_CONTENT);
	        }
		return response.getBody();
		} catch (WebClientResponseException e) {
			throw new ApiException("Error while Retrieving the catalog Details", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	// {GET} - Retrieve the User Full Name
	public String getUserFullName(String user) throws ApiException {
//		ResponseEntity<String> response = null;
		try {
		ResponseEntity<String> response = restClient.get().uri("/security/administration/users").retrieve().toEntity(String.class).block();
		if (response == null || response.getBody() == null) {
	            throw new ApiException("User Full Name response is empty or null", HttpStatus.NO_CONTENT);
	        }
		return response.getBody();
		} catch (WebClientResponseException e) {
			throw new ApiException("Error while Retrieving the User Name", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// {GET} - Retrieve the Business Area Under Root
	public String getBuisnessAreaUnderRoot() throws ApiException {
//		ResponseEntity<String> response = null;
		try {
		ResponseEntity<String> response = restClient.get().uri(
					uriBuilder -> uriBuilder.path("/entitytree/1").queryParam("includeType", "BUSINESS_AREA").build())
					.retrieve().toEntity(String.class).block();
		if (response == null || response.getBody() == null) {
	            throw new ApiException("Business Area response is empty or null", HttpStatus.NO_CONTENT);
	        }
		return response.getBody();
		} catch (WebClientResponseException e) {
			throw new ApiException("Error while Retrieving the Business Area from Root Entity",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	// {GET} - Retrieve the Child Entities
	public String getChildEntities(String entityId) throws ApiException {
//		ResponseEntity<String> response = null;
		try {
			ResponseEntity<String> response = restClient.get().uri("/entitytree/{entityId}", entityId).retrieve().toEntity(String.class)
					.block();
			if (response == null || response.getBody() == null) {
	            throw new ApiException("Child Entities response is empty or null", HttpStatus.NO_CONTENT);
	        }
			return response.getBody();			
		} catch (WebClientResponseException e) {
			throw new ApiException("Error while Retrieving the enitytree (childEntities) details",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// {GET} - Retrieve the Group Members
	public String getGroupMembers(String groupName) throws ApiException {
//		ResponseEntity<String> response = null;
		try {
			ResponseEntity<String> response = restClient.get().uri("/security/administration/groups/{groupName}", groupName).retrieve()
					.toEntity(String.class).block();
			if (response == null || response.getBody() == null) {
	            throw new ApiException("Group Members response is empty or null", HttpStatus.NO_CONTENT);
	        }
		return response.getBody();
		} catch (WebClientResponseException e) {
			throw new ApiException("Error while Retrieving the group Members", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	// {POST} - Create a user group
	public String createGroup(String requestBody) throws ApiException {

//		ResponseEntity<String> response = null;
		try {
			ResponseEntity<String> response = restClient.post().uri("/security/administration/groups/").contentType(MediaType.APPLICATION_JSON)
					.bodyValue(requestBody).retrieve().toEntity(String.class).block();
			if (response == null || response.getBody() == null) {
	            throw new ApiException("Group creation response is empty or null", HttpStatus.NO_CONTENT);
	        }
			return response.getBody();
		} catch (WebClientResponseException e) {
			throw new ApiException("Error while creating the group", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// {POST} - Create a User folder
	public String createUserFolder(String entityId, String requestBody) throws ApiException {

//		ResponseEntity<String> response = null;
		try {
			ResponseEntity<String> response = restClient.post().uri("/entities/{entityId}/children", entityId)
					.contentType(MediaType.APPLICATION_JSON).bodyValue(requestBody).retrieve().toEntity(String.class)
					.block();
			if (response == null || response.getBody() == null) {
	            throw new ApiException("User folder creation response is empty or null", HttpStatus.NO_CONTENT);
	        }
			return response.getBody();
		} catch (WebClientResponseException e) {
			throw new ApiException("Error while creating the user folder", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// {PUT} - Add the member to the group
	public String addMemberToGroup(String group, String member) throws ApiException {

//		ResponseEntity<String> response = null;
		try {
		ResponseEntity<String> response = restClient.put().uri("/security/administration/groups/{group}/member/{member}", group, member)
					.contentType(MediaType.APPLICATION_JSON).retrieve().toEntity(String.class).block();
		if (response == null || response.getBody() == null) {
	            throw new ApiException("Member addition response is empty or null", HttpStatus.NO_CONTENT);
	        }
		return response.getBody();		
		} catch (WebClientResponseException e) {
			throw new ApiException("Error while adding the member to the group", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// {DELETE} - Delete the member from the group
	public String removeMemberFromGroup(String group, String member) throws ApiException {

//		ResponseEntity<String> response = null;
		try {
			ResponseEntity<String> response = restClient.delete().uri("/security/administration/groups/{group}/member/{member}", group, member)
					.retrieve().toEntity(String.class).block();
			if (response == null || response.getBody() == null) {
	            throw new ApiException("Member removal response is empty or null", HttpStatus.NO_CONTENT);
	        }
			return response.getBody();
		} catch (WebClientResponseException e) {
			throw new ApiException("Error while adding the member to the group", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// {POST} - Create a Tuple in catalog
	public String createTuple(String catalogId, String requestBody) throws ApiException {

//		ResponseEntity<String> response = null;
		try {
			ResponseEntity<String> response = restClient.post().uri("/catalog/{catalogId}/tuples", catalogId)
					.contentType(MediaType.APPLICATION_JSON).bodyValue(requestBody).retrieve().toEntity(String.class)
					.block();
			if (response == null || response.getBody() == null) {
	            throw new ApiException("Tuple creation response is empty or null", HttpStatus.NO_CONTENT);
	        }
			return response.getBody();
		} catch (WebClientResponseException e) {
			throw new ApiException("Error while creating the tuple", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// {DELETE} - Delete a Tuple in catalog
	public String deleteTuple(String catalogId, String tupleGuId) throws ApiException {

//		ResponseEntity<String> response = null;
		try {
			ResponseEntity<String> response = restClient.delete().uri("/catalog/{catalogID}/tuples/{tupleGuId}", catalogId, tupleGuId)
					.retrieve().toEntity(String.class).block();
			if (response == null || response.getBody() == null) {
	            throw new ApiException("Tuple deletion response is empty or null", HttpStatus.NO_CONTENT);
	        }
			return response.getBody();
		} catch (WebClientResponseException e) {
			throw new ApiException("Error while deleting the tuple", HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	// {DELETE} - Delete a user Group
	public String deleteGroup(String groupname) throws ApiException {

//		ResponseEntity<String> response = null;
		try {
		ResponseEntity<String>	response = restClient.delete().uri("/security/administration/groups/{groupname}", groupname).retrieve()
					.toEntity(String.class).block();
		if (response == null || response.getBody() == null) {
	            throw new ApiException("Group deletion response is empty or null", HttpStatus.NO_CONTENT);
	        }
		return response.getBody();
		} catch (WebClientResponseException e) {
			throw new ApiException("Error while deleting the group", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// {GET} - Retrieve the version Details
	public String getversionDetails(String entityId) throws ApiException {
//		ResponseEntity<String> response = null;
		try {
			ResponseEntity<String>	response = restClient.get().uri("/entities/{entityId}/versions", entityId).retrieve().toEntity(String.class)
					.block();
			if (response == null || response.getBody() == null) {
	            throw new ApiException("Version Details response is empty or null", HttpStatus.NO_CONTENT);
	        }
			return response.getBody();
		} catch (WebClientResponseException e) {
			throw new ApiException("Error while Retrieving the version Details", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// {PUT} - Lock the Record
	public String lockRecord(String experimentEntityId) throws ApiException {

//		ResponseEntity<String> response = null;
		try {
			ResponseEntity<String>	response = restClient.put().uri("/locks/entities/{entityId}/lock", experimentEntityId)
					.contentType(MediaType.APPLICATION_JSON).retrieve().toEntity(String.class).block();
			if (response == null || response.getBody() == null) {
	            throw new ApiException("Locking response is empty or null", HttpStatus.NO_CONTENT);
	        }
			return response.getBody();
		} catch (WebClientResponseException e) {
			throw new ApiException("Error while locking the Record", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// {PUT} - Load Spreadsheet
	public String loadSpreadsheet(String versionId, Boolean editMode) throws ApiException {
//		ResponseEntity<String> response = null;
		try {
			ResponseEntity<String>	response = restClient.put()
					.uri(uriBuilder -> uriBuilder.path("/spreadsheet").queryParam("versionId", versionId)
							.queryParam("editMode", Boolean.toString(editMode)).build())
					.contentType(MediaType.APPLICATION_JSON).retrieve().toEntity(String.class).block();
			if (response == null || response.getBody() == null) {
	            throw new ApiException("Spreadsheet loading response is empty or null", HttpStatus.NO_CONTENT);
	        }
			return response.getBody();
		} catch (WebClientResponseException e) {
			throw new ApiException("Error while loading the spreadsheet", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	//{POST} - Retrieve the Spreadsheet Details
	public String loadedSpreadsheetDetails(String modelId, String requestBody) throws ApiException {
		
//		ResponseEntity<String> response = null;
		try {
			ResponseEntity<String> response = restClient.post().uri(uriBuilder -> uriBuilder.path("/spreadsheet/data").queryParam("modelId", modelId).build())
					.contentType(MediaType.APPLICATION_JSON).bodyValue(requestBody).retrieve().toEntity(String.class)
					.block();
			if (response == null || response.getBody() == null) {
	            throw new ApiException("Spreadsheet details response is empty or null", HttpStatus.NO_CONTENT);
	        }
			return response.getBody();
		} catch (WebClientResponseException e) {
			throw new ApiException("Error while retrieving the details from Spreadsheet", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	//{POST} - Save Spreadsheet
	public String saveSpreadsheet(String modelId) throws ApiException {
		
//		ResponseEntity<String> response = null;
		try {
			ResponseEntity<String> response = restClient.post().uri(uriBuilder -> uriBuilder.path("/spreadsheet/save").queryParam("modelId", modelId).build())
					.contentType(MediaType.APPLICATION_JSON).retrieve().toEntity(String.class)
					.block();
			if (response == null || response.getBody() == null) {
	            throw new ApiException("Spreadsheet saving response is empty or null", HttpStatus.NO_CONTENT);
	        }
			return response.getBody();
		} catch (WebClientResponseException e) {
			throw new ApiException("Error while Saving the entity", HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	//{DELETE} - Unload the Spreadsheet
	public String unloadSpreadhseet(String modelId) throws ApiException {
//		ResponseEntity<String> response = null;
		try {
		ResponseEntity<String> response = restClient.delete().uri(uriBuilder -> uriBuilder.path("/spreadsheet").queryParam("modelId", modelId).build())
					.retrieve().toEntity(String.class)
					.block();
		if (response == null || response.getBody() == null) {
	            throw new ApiException("Spreadsheet unloading response is empty or null", HttpStatus.NO_CONTENT);
	        }
		return response.getBody();
		} catch (WebClientResponseException e) {
			throw new ApiException("Error while Unloading the spreadhseet", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//{POST} - Draft saving the experiment
	public String entityDraftSave(String experimentEntityId, String requestBody) throws ApiException {
		
//		ResponseEntity<String> response = null;
		try {
		ResponseEntity<String> response = restClient.post().uri("/cache/entities/{experimentEntityId}",experimentEntityId)
					.contentType(MediaType.APPLICATION_JSON).bodyValue(requestBody).retrieve().toEntity(String.class)
					.block();
		if (response == null || response.getBody() == null) {
	            throw new ApiException("Draft saving response is empty or null", HttpStatus.NO_CONTENT);
	        }
		return response.getBody();
		} catch (WebClientResponseException e) {
			throw new ApiException("Error while Draft saving the experiment", HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	//{DELETE} - Unlock the Record
	public String unlockRecord(String entityId) throws ApiException {
		
//		ResponseEntity<String> response = null;
		try {
		ResponseEntity<String> response = restClient.delete().uri("/locks/entities/{entityId}/lock",entityId).retrieve()
					.toEntity(String.class).block();
		if (response == null || response.getBody() == null) {
	            throw new ApiException("Unlocking response is empty or null", HttpStatus.NO_CONTENT);
	        }
		return response.getBody();
		} catch (WebClientResponseException e) {
			throw new ApiException("Error while unlocking the Record", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
