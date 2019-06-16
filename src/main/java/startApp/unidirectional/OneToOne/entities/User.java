package startApp.unidirectional.OneToOne.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private long id;

    //Some code

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id", unique = true)
    private Country country;
}
