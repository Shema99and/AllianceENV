package com.zifo.ewb.catalogpojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "path", "tuple" })
public class CatalogDetailPojo {

	@JsonProperty("path")
	private Object path;
	@JsonProperty("tuple")
	private List<Tuple> tuple;

	@JsonProperty("path")
	public Object getPath() {
		return path;
	}

	@JsonProperty("path")
	public void setPath(Object path) {
		this.path = path;
	}

	@JsonProperty("tuple")
	public List<Tuple> getTuple() {
		return tuple;
	}

	@JsonProperty("tuple")
	public void setTuple(List<Tuple> tuple) {
		this.tuple = tuple;
	}

}
