package app.modelwrappers;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;
import lombok.Data;

/*
 * Wrapper class that contains the other wrapper class WrapStationStatus. 
 *  Used by restTemplate to query API. 
 */
@Data
@NoArgsConstructor 
public class WrapDataStationStatus{
	
	private int last_updated;
	
	private int ttl;

	@JsonProperty("data")
	private WrapStationStatus data; 
	
}