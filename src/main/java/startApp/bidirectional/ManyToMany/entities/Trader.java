/*
 * Developed by Antonio112009 on 16/06/19 04:12
 * Last Modified 16/06/19 04:12
 * Copyright (c) 2019. All rights reserved
 *
 *
 */

package startApp.bidirectional.ManyToMany.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = "stockmarkets")
@Table(name = "trader")
public class Trader {

    @Id
    @GeneratedValue
    @Column(name = "trader_id")
    private Long id;

    @Column(name = "trader_name")
    private String traderName;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH
    })
    @JoinTable(name = "TRADER_STOCKMARKET",
            joinColumns = { @JoinColumn(name = "trader_id") },
            inverseJoinColumns = { @JoinColumn(name = "stockmarket_id") })
    private List<Stockmarket> stockmarkets = new ArrayList<>();


    /*
    We need to add methods below to make everything work correctly
     */

    public void addStockmarket(Stockmarket stockmarket) {
        stockmarkets.add(stockmarket);
        stockmarket.getTraders().add(this);
    }

    public void removeStockmarket(Stockmarket stockmarket) {
        stockmarkets.remove(stockmarket);
        stockmarket.getTraders().remove(this);
    }

}
