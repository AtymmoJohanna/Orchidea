package isis.projet.backend.service;

import isis.projet.backend.dto.EspeceOrchideeDTO;
import isis.projet.backend.entity.EspeceOrchidee;
//import isis.projet.backend.mapper.EspeceOrchideeMapper;
//import isis.projet.backend.repository.EspeceOrchideeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EspeceOrchideeService {

    private final EspeceOrchideeRepository especeOrchideeRepository;
    private final EspeceOrchideeMapper especeOrchideeMapper;

    public EspeceOrchideeDTO createEspeceOrchidee(EspeceOrchideeDTO especeOrchideeDTO) {
        EspeceOrchidee especeOrchidee = especeOrchideeMapper.toEntity(especeOrchideeDTO);
        EspeceOrchidee savedEspece = especeOrchideeRepository.save(especeOrchidee);
        return especeOrchideeMapper.toDto(savedEspece);
    }

    public EspeceOrchideeDTO getEspeceOrchideeById(Integer id) {
        EspeceOrchidee especeOrchidee = especeOrchideeRepository.findById(id).orElse(null);
        return especeOrchidee != null ? especeOrchideeMapper.toDto(especeOrchidee) : null;
    }

    public List<EspeceOrchideeDTO> getAllEspecesOrchidees() {
        List<EspeceOrchidee> especesOrchidees = especeOrchideeRepository.findAll();
        return especesOrchidees.stream().map(especeOrchideeMapper::toDto).collect(Collectors.toList());
    }

    public EspeceOrchideeDTO updateEspeceOrchidee(Integer id, EspeceOrchideeDTO especeOrchideeDTO) {
        EspeceOrchidee especeOrchidee = especeOrchideeRepository.findById(id).orElse(null);
        if (especeOrchidee != null) {
            especeOrchidee.setNomScientifique(especeOrchideeDTO.getNomScientifique());
            EspeceOrchidee updatedEspeceOrchidee = especeOrchideeRepository.save(especeOrchidee);
            return especeOrchideeMapper.toDto(updatedEspeceOrchidee);
        }
        return null;
    }

    public void deleteEspeceOrchidee(Integer id) {
        especeOrchideeRepository.deleteById(id);
    }
}