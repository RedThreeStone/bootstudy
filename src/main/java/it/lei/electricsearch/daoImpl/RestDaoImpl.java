package it.lei.electricsearch.daoImpl;

import it.lei.day1.entity.User;
import it.lei.electricsearch.dao.RestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Repository(value = "restDao")
public class RestDaoImpl implements RestDao {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public User getUserById(String id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        HashMap<String, Object> params = new HashMap<>();
        String uri="http://localhost:9200/person/man/{id}";
        params.put("id",id);
        String result = restTemplate.getForObject(uri, String.class, params);
        System.out.println(result);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");
        httpHeaders.add("Accept","*/*");
        httpHeaders.add("Cache-Control","no-cache");
        String json="{   \"query\":{     \"bool\":{       \"must\":{        " +
                " \"match\":{           \"age\":\"18\"         }       }," +
                "       \"should\":{         \"match\":{           \"name\":\"黄磊\" " +
                "        }       }     }   } }";

        HttpEntity httpEntity = new HttpEntity(json,httpHeaders);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("http://localhost:9200/_all/man/_search", httpEntity, String.class);
        System.out.println(stringResponseEntity.getBody());
        return null;
    }
}
