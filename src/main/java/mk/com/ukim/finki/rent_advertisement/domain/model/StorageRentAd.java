package mk.com.ukim.finki.rent_advertisement.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.standard.StandardFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Indexed
@AnalyzerDef(name = "fieldAnalyzer",
        filters = { @TokenFilterDef(factory = LowerCaseFilterFactory.class)},
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class))
@Entity
@Table(name="storage_rent_ads")
@Getter
@Setter
public class StorageRentAd {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @DocumentId
    private Long id;
    @Field
    @Analyzer(definition = "fieldAnalyzer")
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private AdvertisementStatus status;

    @IndexedEmbedded
    @ManyToOne
    private User publisher;
    @ManyToOne
    private Location storageLocation;
    @OneToMany(mappedBy = "storageRentAd", cascade = CascadeType.ALL)
    private List<Image> images;

    public StorageRentAd(){

    }

}
