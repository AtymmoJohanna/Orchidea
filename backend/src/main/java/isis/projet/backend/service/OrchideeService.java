package isis.projet.backend.service;

import isis.projet.backend.dto.OrchideeDTO;
import isis.projet.backend.entity.Orchidee;
import isis.projet.backend.dao.OrchideeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrchideeService {

    private final OrchideeRepository orchideeRepository;
    private final ModelMapper modelMapper;  // Injection du ModelMapper

    // Convertir une entité Orchidee en DTO
    public OrchideeDTO createOrchidee(OrchideeDTO orchideeDTO) {
        Orchidee orchidee = modelMapper.map(orchideeDTO, Orchidee.class);  // Convertir le DTO en entité
        Orchidee savedOrchidee = orchideeRepository.save(orchidee);  // Sauvegarder l'entité
        return modelMapper.map(savedOrchidee, OrchideeDTO.class);  // Convertir l'entité sauvegardée en DTO
    }

    // Convertir une entité Orchidee en DTO par ID
    public OrchideeDTO getOrchideeById(Integer id) {
        Orchidee orchidee = orchideeRepository.findById(id).orElse(null);
        return orchidee != null ? modelMapper.map(orchidee, OrchideeDTO.class) : null;
    }

    // Récupérer toutes les Orchidees et les convertir en DTO
    public List<OrchideeDTO> getAllOrchidees() {
        List<Orchidee> orchidees = orchideeRepository.findAll();
        return orchidees.stream()
                .map(orchidee -> modelMapper.map(orchidee, OrchideeDTO.class))  // Conversion dans le stream
                .collect(Collectors.toList());
    }

    // Mettre à jour une Orchidee à partir d'un DTO
    public OrchideeDTO updateOrchidee(Integer id, OrchideeDTO orchideeDTO) {
        Orchidee orchidee = orchideeRepository.findById(id).orElse(null);
        if (orchidee != null) {
            // Map le DTO dans l'entité existante
            modelMapper.map(orchideeDTO, orchidee);
            Orchidee updatedOrchidee = orchideeRepository.save(orchidee);  // Sauvegarder l'entité mise à jour
            return modelMapper.map(updatedOrchidee, OrchideeDTO.class);  // Retourner le DTO mis à jour
        }
        return null;
    }

    // Supprimer une Orchidee
    public void deleteOrchidee(Integer id) {
        orchideeRepository.deleteById(id);
    }
}