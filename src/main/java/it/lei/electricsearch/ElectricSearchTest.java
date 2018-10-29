package it.lei.electricsearch;

import it.lei.electricsearch.dao.RestDao;
import it.lei.electricsearch.dao.SpringDataDao;
import it.lei.redis.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public class ElectricSearchTest extends BaseTest {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    private RestDao restDao;
    @Autowired
    private SpringDataDao springDataDao;
    @Test
    public  void saveTest(){
        //restDao.getUserById("102");
        Optional<Man> byId = springDataDao.findById("101");
        System.out.println(byId.get());
        List<Man> mans = springDataDao.getByNameAndHobby("黄磊", "健身");
        System.out.println(mans);
        PageRequest pageRequest = PageRequest.of(0, 1);
        Page<Man> manPage = springDataDao.findAll(pageRequest);
        List<Man> content = manPage.getContent();
        System.out.println(content);
        System.out.println(manPage.getTotalElements());
        System.out.println(manPage.getTotalPages());
    }
}
