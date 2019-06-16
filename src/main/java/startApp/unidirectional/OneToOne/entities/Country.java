package startApp.unidirectional.OneToOne.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "country")
public class Country {


    @Id
    @GeneratedValue
    @Column(name = "country_id")
    private long id;

    //Some code

    @Column(name = "country")
    private String country;


}
