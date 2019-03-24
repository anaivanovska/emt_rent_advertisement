package mk.com.ukim.finki.rent_advertisement.persistence.search;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.jboss.logging.annotations.Pos;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class BuildIndex {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    @Transactional
    public void init() {
        entityManager = entityManager.getEntityManagerFactory().createEntityManager();
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        try {
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            System.err.println("An error occured  trying to build the search index " + e.toString());
        }
    }
}
