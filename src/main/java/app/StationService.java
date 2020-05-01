package app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import app.model.Station;
import app.model.StationStatus;
import app.modelwrappers.WrapDataStationInfo;
import app.modelwrappers.WrapDataStationStatus;

@EnableScheduling
@Service
public class StationService {

	private final String stationInfoURL = "https://gbfs.urbansharing.com/oslobysykkel.no/station_information.json";
	private final String stationStatusURL = "https://gbfs.urbansharing.com/oslobysykkel.no/station_status.json";
    
	private Map<Integer, Station> stationMap = new HashMap<Integer, Station>();
	private RestTemplate restTemplate = new RestTemplate();

	
    @Bean
    public Map<Integer, Station> stationMap(){
    	return stationMap;
    }
    
    
    public void addStation(int id, Station station) {
    	stationMap.put(id, station);
    }
    
    
    public Station getStation(int id) {
    	return stationMap.get(id);
    }
        
    
    @Scheduled(fixedRate=30000)
    private void updateStationStatus() {
    	System.out.println("In service.class updateStationStatus()"); 
    	ResponseEntity<WrapDataStationStatus> statStatus = restTemplate.getForEntity(stationStatusURL, WrapDataStationStatus.class);
		List<StationStatus> statStatusList = statStatus.getBody().getData().getStations();
		
		for(StationStatus stStat : statStatusList) {
			Station st = getStation(stStat.getStation_id());
			st.setNum_bikes_available(stStat.getNum_bikes_available());
			st.setNum_docks_available(stStat.getNum_docks_available());
		}
    }
	
    
    @PostConstruct
    public void init() {
        System.out.println("In service.class init()"); 

		ResponseEntity<WrapDataStationInfo> statInfo = restTemplate.getForEntity(stationInfoURL, WrapDataStationInfo.class);
		List<Station> statInfoList = statInfo.getBody().getData().getStations();
		
		for(Station st : statInfoList) {
			addStation(st.getStation_id(), st);
		}
		
		updateStationStatus();
    }
	
}
