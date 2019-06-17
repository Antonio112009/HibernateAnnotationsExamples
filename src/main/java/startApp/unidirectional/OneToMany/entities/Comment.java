/*
 * Developed by Antonio112009 on 17/06/19 00:45
 * Last Modified 17/06/19 00:45
 * Copyright (c) 2019. All rights reserved
 *
 *
 */

package startApp.unidirectional.OneToMany.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "postcom_id")
    private Long id;

    @Column
    private String text;
}
