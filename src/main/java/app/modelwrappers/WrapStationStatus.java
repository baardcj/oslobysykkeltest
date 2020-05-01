package app.modelwrappers;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

import app.model.StationStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WrapStationStatus {
	
	@JsonProperty("stations")
	private List<StationStatus> stations; 
	
}