package mk.com.ukim.finki.rent_advertisement.persistence.search;



import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.PhraseMatchingContext;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class SearchRepositoryImpl implements SearchRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public <T>  List<T> searchKeyword(Class<T> entityClass, String keyword, String... fields) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(entityClass).get();

        Query query = queryBuilder.keyword()
                .wildcard()
                .onFields(fields)
                .matching(keyword + "*")
                .createQuery();

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query);

        return jpaQuery.getResultList();

    }


    @Override
    public <T> List<T> searchPhrase(Class<T> entityClass ,String text, String... fields){
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(entityClass).get();

        BooleanJunction<BooleanJunction> bool = queryBuilder.bool();
        Query query = null;

        if(text.contains(" ")){
            String[] tokens = text.split(" ");
            for(int i=0; i<tokens.length; i++){
                bool.should(
                        queryBuilder.keyword()
                                .onFields(fields)
                                .matching(tokens[i])
                                .createQuery()
                ).boostedTo(.7f);

                searchKeywordByWildcardOrFuzzy(tokens[i], bool, queryBuilder, fields);

            }

            PhraseMatchingContext phraseQuery = getMultipleFiledQuert(queryBuilder, fields);

            bool.should(
                    phraseQuery.sentence(text).createQuery()).boostedTo(3f);
            query = bool.createQuery();

        }else{
            searchKeywordByWildcardOrFuzzy(text, bool, queryBuilder, fields);
            query = bool.createQuery();
        }

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, entityClass);
        List<T> results = jpaQuery.getResultList();

        return results;
    }

    private PhraseMatchingContext getMultipleFiledQuert(QueryBuilder queryBuilder, String[] fields) {
        PhraseMatchingContext phraseQuery =  queryBuilder.phrase().onField(fields[0]);

        for(int i=1; i<fields.length; i++){
            phraseQuery = phraseQuery.andField(fields[i]);
        }

        return phraseQuery;
    }

    private void searchKeywordByWildcardOrFuzzy(String word, BooleanJunction<BooleanJunction> bool, QueryBuilder queryBuilder, String[] fields) {
        bool.should(
                queryBuilder.keyword()
                        .wildcard()
                        .onFields(fields)
                        .matching("*" + word.toLowerCase() + "*")
                        .createQuery()
        ).boostedTo(0.5f);


        bool.should(
                queryBuilder.keyword()
                        .fuzzy()
                        .withEditDistanceUpTo(1)
                        .withPrefixLength(0)
                        .onFields(fields)
                        .matching(word)
                        .createQuery()

        ).boostedTo(0.5f);

    }

}
