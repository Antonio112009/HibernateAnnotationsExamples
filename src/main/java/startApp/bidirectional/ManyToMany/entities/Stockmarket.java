/*
 * Developed by Antonio112009 on 16/06/19 04:13
 * Last Modified 16/06/19 04:13
 * Copyright (c) 2019. All rights reserved
 *
 *
 */

package startApp.bidirectional.ManyToMany.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stockmarket")
public class Stockmarket {

    @Id
    @GeneratedValue
    @Column(name = "stockmarket_id")
    private long id;

    @Column(name = "stockmarket_name")
    private String stockmarketName;

    @ManyToMany(mappedBy="stockmarket")
    private List<Trader> traders;

}
