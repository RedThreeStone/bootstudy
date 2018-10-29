package it.lei.electricsearch.dao;

import it.lei.electricsearch.Man;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SpringDataDao extends PagingAndSortingRepository<Man,String> {
    public Man getById(Integer id);
    public List<Man> getByNameAndHobby(String name,String hobby);
}
