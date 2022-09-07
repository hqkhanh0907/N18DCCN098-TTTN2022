package com.example.demo.service.implement;

import com.example.demo.dto.DirectorDto;
import com.example.demo.dto.DirectorPage;
import com.example.demo.dto.map.DirectorMapper;
import com.example.demo.model.Director;
import com.example.demo.model.DirectorOfMovie;
import com.example.demo.repository.FKDirectorRepository;
import com.example.demo.repository.MovieDirectorRepository;
import com.example.demo.service.MovieDirectorService;
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
public class MovieDirectorServiceImpl implements MovieDirectorService {
    private final MovieDirectorRepository movieDirectorRepository;
    private final DirectorMapper directorMapper;
    private final FKDirectorRepository fkDirectorRepository;

    @Override
    public List<DirectorDto> getAllMovieDirector() {
        return movieDirectorRepository.findAll().stream().map(director -> {
            return directorMapper.directorToDirectorDto(director);
        }).collect(Collectors.toList());
    }

    @Override
    public DirectorDto getMovieDirectorById(Integer id) {
        return directorMapper.directorToDirectorDto(movieDirectorRepository.getById(id));
    }

    @Override
    public Boolean deleteMovieDirectorById(Integer id) {
        Director director = movieDirectorRepository.findById(id).orElse(null);
        if (Objects.isNull(director)) {
            throw new RuntimeException("Director not found");
        } else {
            if (!checkDirectorOnMovie(director.getId())) {
                movieDirectorRepository.delete(director);
                return true;
            } else {
                throw new RuntimeException("Already exist in movie! Couldn't delete director!");
            }
        }
    }

    private Boolean checkDirectorOnMovie(Integer id) {
        List<DirectorOfMovie> directorOfMovies = fkDirectorRepository.findAll();
        for (DirectorOfMovie directorOfMovie : directorOfMovies) {
            if (directorOfMovie.getId().getDirectorId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public DirectorDto createMovieDirector(DirectorDto movieDirectorDTO) {
        if (checkNameInDirector(movieDirectorDTO.getName()) == false) {
            Director director = new Director();
            director.setAvatar(movieDirectorDTO.getAvatar());
            director.setName(movieDirectorDTO.getName());
            director.setStory(movieDirectorDTO.getStory());
            director.setBirthday(movieDirectorDTO.getBirthday());
            movieDirectorRepository.save(director);
            return movieDirectorDTO;
        }
        return null;
    }

    @Override
    public Boolean editMovieDirector(DirectorDto movieDirectorDTO) {
        Director director = movieDirectorRepository.findById(movieDirectorDTO.getId()).orElse(null);
        if (Objects.isNull(director)) {
            throw new RuntimeException("Director not found");
        } else {
            if (checkNameEditInDirector(movieDirectorDTO.getName(), director.getName()) == false) {
                director.setAvatar(movieDirectorDTO.getAvatar());
                director.setName(movieDirectorDTO.getName());
                director.setBirthday(movieDirectorDTO.getBirthday());
                director.setStory(director.getStory());
                movieDirectorRepository.save(director);
                return true;
            }
            return false;
        }
    }

    @Override
    public DirectorPage getAllDirectorPage(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Page<Director> directors = movieDirectorRepository.findAll(PageRequest.of(pageNo, pageSize, sort));
        // get content for page object
        List<Director> listOfPosts = directors.getContent();

        List<DirectorDto> content = directors.getContent().stream().map(director -> {
            return directorMapper.directorToDirectorDto(director);
        }).collect(Collectors.toList());
        DirectorPage directorPage = new DirectorPage();
        directorPage.setMovieDirectorDTOS(content);
        directorPage.setPageNo(directors.getNumber());
        directorPage.setPageSize(directors.getSize());
        directorPage.setTotalElements(directors.getTotalElements());
        directorPage.setTotalPages(directors.getTotalPages());
        directorPage.setFirst(directors.isFirst());
        directorPage.setLast(directors.isLast());

        return directorPage;
    }

    public boolean checkNameInDirector(String name) {
        List<Director> directors = movieDirectorRepository.findAll();
        directors.forEach(movieDirector -> {
            if (movieDirector.getName().equals(name)) {
                throw new RuntimeException("Director's name already exits");
            }
        });
        return false;
    }

    public boolean checkNameEditInDirector(String nameNew, String nameOld) {
        if (!nameNew.equals(nameOld)) {
            List<Director> directors = movieDirectorRepository.findAll();
            directors.forEach(movieDirector -> {
                if (movieDirector.getName().equals(nameNew)) {
                    throw new RuntimeException("Director's name already exits");
                }
            });
        }
        return false;
    }
}
