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
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @Column
    private String postHeader;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Comment> comments;
}
