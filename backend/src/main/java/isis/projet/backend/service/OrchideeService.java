package isis.projet.backend.service;

import isis.projet.backend.dto.OrchideeDTO;
import isis.projet.backend.entity.Orchidee;
import isis.projet.backend.mapper.OrchideeMapper;
import isis.projet.backend.repository.OrchideeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrchideeService {

    private final OrchideeRepository orchideeRepository;
    private final OrchideeMapper orchideeMapper;

    public OrchideeDTO createOrchidee(OrchideeDTO orchideeDTO) {
        Orchidee orchidee = orchideeMapper.toEntity(orchideeDTO);
        Orchidee savedOrchidee = orchideeRepository.save(orchidee);
        return orchideeMapper.toDto(savedOrchidee);
    }

    public OrchideeDTO getOrchideeById(Integer id) {
        Orchidee orchidee = orchideeRepository.findById(id).orElse(null);
        return orchidee != null ? orchideeMapper.toDto(orchidee) : null;
    }

    public List<OrchideeDTO> getAllOrchidees() {
        List<Orchidee> orchidees = orchideeRepository.findAll();
        return orchidees.stream().map(orchideeMapper::toDto).collect(Collectors.toList());
    }

    public OrchideeDTO updateOrchidee(Integer id, OrchideeDTO orchideeDTO) {
        Orchidee orchidee = orchideeRepository.findById(id).orElse(null);
        if (orchidee != null) {
            orchidee.setCommentaire(orchideeDTO.getCommentaire());
            orchidee.setLatitude(orchideeDTO.getLatitude());
            orchidee.setLongitude(orchideeDTO.getLongitude());
            Orchidee updatedOrchidee = orchideeRepository.save(orchidee);
            return orchideeMapper.toDto(updatedOrchidee);
        }
        return null;
    }

    public void deleteOrchidee(Integer id) {
        orchideeRepository.deleteById(id);
    }
}