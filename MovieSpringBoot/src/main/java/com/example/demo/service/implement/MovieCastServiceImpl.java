package com.example.demo.service.implement;

import com.example.demo.dto.CastDto;
import com.example.demo.dto.CastPage;
import com.example.demo.dto.map.CastMapper;
import com.example.demo.model.Cast;
import com.example.demo.model.CastOfMovie;
import com.example.demo.repository.FKCastRepository;
import com.example.demo.repository.MovieCastRepository;
import com.example.demo.service.MovieCastService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieCastServiceImpl implements MovieCastService {
    private final MovieCastRepository movieCastRepository;
    private final CastMapper castMapper;
    private final FKCastRepository fkCastRepository;

    @Override
    public List<CastDto> getAllCast() {
        return movieCastRepository.findAll().stream().map(cast -> {
            return castMapper.castToCastDto(cast);
        }).collect(Collectors.toList());
    }

    @Override
    public CastDto getMovieCastById(Integer id) {
        return castMapper.castToCastDto(movieCastRepository.getById(id));
    }


    @Override
    public Boolean deleteMovieCastById(Integer id) {
        Cast cast = movieCastRepository.findById(id).orElse(null);
        if (Objects.isNull(cast)) {
            throw new RuntimeException("Cast not found");
        } else {
            if (!checkCastOnFKCast(cast.getId())) {
                movieCastRepository.delete(cast);
                return true;
            } else {
                throw new RuntimeException("Already exist in movie! Couldn't delete cast!");
            }
        }
    }

    private Boolean checkCastOnFKCast(Integer castId) {
        List<CastOfMovie> castOfMovies = fkCastRepository.findAll();
        for (CastOfMovie castOfMovie : castOfMovies) {
            if (castOfMovie.getId().getCastId() == castId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public CastDto createMovieCast(CastDto castDto) {
        if (checkNameExit(castDto.getName()) == false) {
            Cast cast = new Cast();
            cast.setAvatar(castDto.getAvatar());
            cast.setName(castDto.getName());
            cast.setStory(castDto.getStory());
            cast.setBirthday(castDto.getBirthday());
            movieCastRepository.save(cast);
            return castDto;
        }
        return null;

    }

    @Override
    public Boolean editMovieCast(CastDto movieCastDTO) {
        Cast cast = movieCastRepository.findById(movieCastDTO.getId()).orElse(null);
        if (Objects.isNull(cast)) {
            throw new RuntimeException("Cast not found");
        } else {
            if (checkNameExitEdit(movieCastDTO.getName(), cast.getName()) == false) {
                cast.setAvatar(movieCastDTO.getAvatar());
                cast.setName(movieCastDTO.getName());
                cast.setStory(movieCastDTO.getStory());
                cast.setBirthday(movieCastDTO.getBirthday());
                movieCastRepository.save(cast);
                return true;
            }
            return false;
        }
    }

    @Override
    public CastPage getAllCastPage(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // create Pageable instance
//        Pageable pageable = ;
        Page<Cast> casts = movieCastRepository.findAll(PageRequest.of(pageNo, pageSize, sort));

        List<CastDto> content = casts.getContent().stream().map(cast -> {
            return castMapper.castToCastDto(cast);
        }).collect(Collectors.toList());
        CastPage castPage = new CastPage();
        castPage.setMovieCastDTOS(content);
        castPage.setPageNo(casts.getNumber());
        castPage.setPageSize(casts.getSize());
        castPage.setTotalElements(casts.getTotalElements());
        castPage.setTotalPages(casts.getTotalPages());
        castPage.setFirst(casts.isFirst());
        castPage.setLast(casts.isLast());

        return castPage;
    }

    @Override
    public Integer getNumCast() {
        return movieCastRepository.getNumCast();
    }


    public boolean checkNameExit(String name) {
        List<Cast> casts = movieCastRepository.findAll();
        casts.forEach(movieCast -> {
            if (movieCast.getName().equals(name)) {
                throw new RuntimeException("Cast's name already exists");
            }
        });
        return false;
    }

    public boolean checkNameExitEdit(String nameNew, String nameOld) {
        if (!nameNew.equals(nameOld)) {
            List<Cast> casts = movieCastRepository.findAll();
            casts.forEach(movieCast -> {
                if (movieCast.getName().equals(nameNew)) {
                    throw new RuntimeException("Cast's name already exists");
                }
            });
        }
        return false;
    }
}
