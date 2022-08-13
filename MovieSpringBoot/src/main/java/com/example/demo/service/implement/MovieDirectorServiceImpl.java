package com.example.demo.service.implement;

import com.example.demo.dto.DirectorDto;
import com.example.demo.dto.DirectorPage;
import com.example.demo.dto.map.DirectorMapper;
import com.example.demo.model.Director;
import com.example.demo.repository.MovieDirectorRepository;
import com.example.demo.service.FKDirectorService;
import com.example.demo.service.MovieDirectorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieDirectorServiceImpl implements MovieDirectorService {
    private final MovieDirectorRepository movieDirectorRepository;
    private final DirectorMapper directorMapper;
    private final FKDirectorService fkDirectorService;

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
    public String deleteMovieDirectorById(Integer id) {
        Director director = movieDirectorRepository.findById(id).orElse(null);
        if (director == null) {
            throw new RuntimeException("Director not found");
        } else {
            fkDirectorService.deleteFkDirectorByDirectorId(id);
            movieDirectorRepository.delete(director);
            return "Delete a director successfully";
        }
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
    public String editMovieDirector(DirectorDto movieDirectorDTO) {
        Director director = movieDirectorRepository.findById(movieDirectorDTO.getId()).orElse(null);
        if (director == null) {
            throw new RuntimeException("Director not found");
        } else {
            if (checkNameInDirector(movieDirectorDTO.getName()) == false) {
                director.setAvatar(movieDirectorDTO.getAvatar());
                director.setName(movieDirectorDTO.getName());
                director.setStory(director.getStory());
                movieDirectorRepository.save(director);
                return "Edit director successfully";
            }
            return "Fail";
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
}
