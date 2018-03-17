package org.neu.cabs.orm;

import lombok.*;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * 机场实体类
 * @author 李浩然
 */
@Entity
@Table(name = "AIRPORT")
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Airport {
    /**
     * 机场ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 中文名
     */
    @Column(length = 50)
    @NotNull
    private String chineseName;

    /**
     * 英文名
     */
    @Column(length = 100)
    @NotNull
    private String englishName;

    /**
     * 机场IATA代码
     */
    @Column(length = 5, unique = true)
    @NotNull
    private String iataCode;

    /**
     * 机场ICAO代码
     */
    @Column(length = 5, unique = true)
    @NotNull
    private String icaoCode;

    /**
     * 飞行区等级
     */
    @Column(length = 3)
    @NotNull
    private String flightLevel;

    /**
     * 机位数
     */
    @Column
    @NotNull
    private Integer seatOfPlane;

    /**
     * 机场地址
     */
    @NotNull
    private Address address;

    /**
     * 拥有的航站楼
     */
    @OneToMany(cascade = { CascadeType.ALL })
    @org.hibernate.annotations.Fetch(value = FetchMode.JOIN)
    private Set<Terminal> terminals;

    public String getTerminalString() {
        List<Terminal> terminalList = new ArrayList<>(terminals);
        Collections.sort(terminalList);
        StringBuilder builder = new StringBuilder();
        for (Iterator<Terminal> i = terminalList.iterator(); i.hasNext(); ) {
            builder.append(i.next().getTerminalName());
            if (i.hasNext()) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }
}
