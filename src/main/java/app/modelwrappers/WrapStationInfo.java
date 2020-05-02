package app.modelwrappers;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

import app.model.Station;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Wrapper class that contains list of Stations. 
 * Used by restTemplate to query API. 
 */
@NoArgsConstructor
@Data
public class WrapStationInfo {
	
	@JsonProperty("stations")
	private List<Station> stations; 
	
}
