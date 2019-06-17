/*
 * Developed by Antonio112009 on 16/06/19 03:59
 * Last Modified 16/06/19 03:24
 * Copyright (c) 2019. All rights reserved
 *
 *
 */

package startApp.unidirectional.OneToOne.entities;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String username;

    //Some code

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", unique = true)
    private Customer customer;
}
