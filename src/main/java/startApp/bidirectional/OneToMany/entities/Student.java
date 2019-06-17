/*
 * Developed by Antonio112009 on 17/06/19 03:03
 * Last Modified 17/06/19 03:03
 * Copyright (c) 2019. All rights reserved
 *
 *
 */

package startApp.bidirectional.OneToMany.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = "professor")
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "student_id")
    private Long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="professor_id")
    private Professor professor;

}
