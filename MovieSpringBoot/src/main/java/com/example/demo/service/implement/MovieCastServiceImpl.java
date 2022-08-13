package com.example.demo.service.implement;

import com.example.demo.dto.CastDto;
import com.example.demo.dto.CastPage;
import com.example.demo.dto.map.CastMapper;
import com.example.demo.model.Cast;
import com.example.demo.repository.MovieCastRepository;
import com.example.demo.service.CastOfMovieService;
import com.example.demo.service.MovieCastService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieCastServiceImpl implements MovieCastService {
    private final MovieCastRepository movieCastRepository;
    private final CastMapper castMapper;
    private final CastOfMovieService castOfMovieService;

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
    public String deleteMovieCastById(Integer id) {
        Cast cast = movieCastRepository.findById(id).orElse(null);
        if (cast == null) {
            throw new RuntimeException("Cast not found");
        } else {
            castOfMovieService.deleteFkCastByCastId(id);
            movieCastRepository.delete(cast);
            return "Delete cast successfully";
        }
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
    public String editMovieCast(CastDto movieCastDTO) {
        Cast cast = movieCastRepository.findById(movieCastDTO.getId()).orElse(null);
        if (cast == null) {
            throw new RuntimeException("Cast not found");
        } else {
            if (checkNameExit(movieCastDTO.getName())) {
                cast.setAvatar(movieCastDTO.getAvatar());
                cast.setName(movieCastDTO.getName());
                cast.setStory(movieCastDTO.getStory());
                movieCastRepository.save(cast);
                return "Edit cast successfully";
            }
            return "Fail";
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


    public boolean checkNameExit(String name) {
        List<Cast> casts = movieCastRepository.findAll();
        casts.forEach(movieCast -> {
            if (movieCast.getName().equals(name)) {
                throw new RuntimeException("Cast's name already exists");
            }
        });
        return false;
    }
}
