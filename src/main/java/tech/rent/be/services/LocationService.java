package tech.rent.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.rent.be.dto.LocationDTO;
import tech.rent.be.entity.Location;
import tech.rent.be.repository.LocationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {
    @Autowired
    LocationRepository locationRepository;

    public Location CreateLocation(LocationDTO locationDTO){
        Location location = new Location();
        location.setLocation(locationDTO.getLocation());
        return locationRepository.save(location);
    }



    public List<LocationDTO> getAllLocation() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream()
                .map(this::convertToLocationDTO)
                .collect(Collectors.toList());
    }

    private LocationDTO convertToLocationDTO(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(location.getId());
        locationDTO.setLocation(location.getLocation());
        return locationDTO;
    }
}
