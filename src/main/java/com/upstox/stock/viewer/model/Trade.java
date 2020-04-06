package com.upstox.stock.viewer.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "sym", "T", "P", "Q", "TS", "side", "TS2" })
public class Trade {

	@JsonProperty("sym")
	private String sym;
	@JsonProperty("T")
	private String t;
	@JsonProperty("P")
	private Double p;
	@JsonProperty("Q")
	private Double q;
	@JsonProperty("TS")
	private Double tS;
	@JsonProperty("side")
	private String side;
	@JsonProperty("TS2")
	private Double tS2;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("sym")
	public String getSym() {
		return sym;
	}

	@JsonProperty("sym")
	public void setSym(String sym) {
		this.sym = sym;
	}

	@JsonProperty("T")
	public String getT() {
		return t;
	}

	@JsonProperty("T")
	public void setT(String t) {
		this.t = t;
	}

	@JsonProperty("P")
	public Double getP() {
		return p;
	}

	@JsonProperty("P")
	public void setP(Double p) {
		this.p = p;
	}

	@JsonProperty("Q")
	public Double getQ() {
		return q;
	}

	@JsonProperty("Q")
	public void setQ(Double q) {
		this.q = q;
	}

	@JsonProperty("TS")
	public Double getTS() {
		return tS;
	}

	@JsonProperty("TS")
	public void setTS(Double tS) {
		this.tS = tS;
	}

	@JsonProperty("side")
	public String getSide() {
		return side;
	}

	@JsonProperty("side")
	public void setSide(String side) {
		this.side = side;
	}

	@JsonProperty("TS2")
	public Double getTS2() {
		return tS2;
	}

	@JsonProperty("TS2")
	public void setTS2(Double tS2) {
		this.tS2 = tS2;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}