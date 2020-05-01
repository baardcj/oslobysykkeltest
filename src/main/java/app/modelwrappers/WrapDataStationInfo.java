package app.modelwrappers;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
public class WrapDataStationInfo {
	
	private int last_updated;
	
	private int ttl;

	@JsonProperty("data")
	private WrapStationInfo data; 
	
}