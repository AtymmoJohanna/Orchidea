package isis.projet.backend.service;

import isis.projet.backend.dto.EspeceOrchideeDTO;
import isis.projet.backend.entity.EspeceOrchidee;
import isis.projet.backend.dao.EspeceOrchideeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EspeceOrchideeService {

    private final EspeceOrchideeRepository especeOrchideeRepository;
    private final ModelMapper modelMapper;  // Injection de ModelMapper

    @Transactional
    public EspeceOrchideeDTO createEspeceOrchidee(EspeceOrchideeDTO especeOrchideeDTO) {
        EspeceOrchidee especeOrchidee = modelMapper.map(especeOrchideeDTO, EspeceOrchidee.class);  // Conversion DTO -> Entité
        EspeceOrchidee savedEspece = especeOrchideeRepository.save(especeOrchidee);
        return modelMapper.map(savedEspece, EspeceOrchideeDTO.class);  // Conversion Entité -> DTO
    }

    public EspeceOrchideeDTO getEspeceOrchideeById(Integer id) {
        EspeceOrchidee especeOrchidee = especeOrchideeRepository.findById(id).orElse(null);
        return especeOrchidee != null ? modelMapper.map(especeOrchidee, EspeceOrchideeDTO.class) : null;
    }

    public List<EspeceOrchideeDTO> getAllEspecesOrchidees() {
        List<EspeceOrchidee> especesOrchidees = especeOrchideeRepository.findAll();
        return especesOrchidees.stream()
                .map(especeOrchidee -> modelMapper.map(especeOrchidee, EspeceOrchideeDTO.class))  // Conversion dans le stream
                .collect(Collectors.toList());
    }
}