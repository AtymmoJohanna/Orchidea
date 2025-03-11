package isis.projet.backend.service;

import isis.projet.backend.dto.AvisDTO;
import isis.projet.backend.entity.Avis;
import isis.projet.backend.mapper.AvisMapper;
import isis.projet.backend.repository.AvisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvisService {

    private final AvisRepository avisRepository;
    private final AvisMapper avisMapper;

    public AvisDTO createAvis(AvisDTO avisDTO) {
        Avis avis = avisMapper.toEntity(avisDTO);
        Avis savedAvis = avisRepository.save(avis);
        return avisMapper.toDto(savedAvis);
    }

    public AvisDTO getAvisById(Integer id) {
        Avis avis = avisRepository.findById(id).orElse(null);
        return avis != null ? avisMapper.toDto(avis) : null;
    }

    public List<AvisDTO> getAvisByUser(String nomUser) {
        List<Avis> avisList = avisRepository.findByNomUser(nomUser);
        return avisList.stream().map(avisMapper::toDto).collect(Collectors.toList());
    }

    public AvisDTO updateAvis(Integer id, AvisDTO avisDTO) {
        Avis avis = avisRepository.findById(id).orElse(null);
        if (avis != null) {
            avis.setCommentaire(avisDTO.getCommentaire());
            avis.setDate(avisDTO.getDate()); // Assurez-vous que vous mettez à jour d'autres propriétés pertinentes
            Avis updatedAvis = avisRepository.save(avis);
            return avisMapper.toDto(updatedAvis);
        }
        return null;
    }

    public void deleteAvis(Integer id) {
        avisRepository.deleteById(id);
    }

    public List<AvisDTO> getAllAvis() {
        List<Avis> avisList = avisRepository.findAll();
        return avisList.stream().map(avisMapper::toDto).collect(Collectors.toList());
    }
}