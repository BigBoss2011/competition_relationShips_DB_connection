package tn.isg.soa.competitionServer.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@AllArgsConstructor
//or @Data
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @NonNull
    private String name;
    private int nbPlayers;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate creationDate;
    @OneToMany(mappedBy="playerTeam",cascade=CascadeType.ALL)
    private Set<Player> teamPlayers=new HashSet<>();
    @OneToOne (mappedBy="teamOwner")
    private Stadium teamStadium;
    @ManyToMany
    @JoinTable(name="team_competition",
            joinColumns=@JoinColumn(name="team_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="competition_id",referencedColumnName="id")
    )
    private Set<Competition> teamCompetitions =new HashSet<>();
}
