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
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "country_id")
    private long id;

    //Some code

    @Column(name = "customer_name")
    private String customerName;


}
