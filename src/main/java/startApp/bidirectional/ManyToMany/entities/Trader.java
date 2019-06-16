/*
 * Developed by Antonio112009 on 16/06/19 04:12
 * Last Modified 16/06/19 04:12
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
@Table(name = "trader")
public class Trader {

    @Id
    @GeneratedValue
    @Column(name = "trader_id")
    private int id;

    @Column(name = "trader_name")
    private String traderName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "TRADER_STOCKMARKET",
            joinColumns = { @JoinColumn(name = "trader_id") },
            inverseJoinColumns = { @JoinColumn(name = "stockmarket_id") })
    private List<Stockmarket> stockmarket;

}
