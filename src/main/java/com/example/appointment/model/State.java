package com.example.appointment.model;
import java.util.List;
import lombok.*;
import jakarta.persistence.*;
@Entity
@Table(name = "states")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stateId;

    private String stateName;

    @OneToMany(mappedBy = "state")
    private List<City> cities;

    @Override
    public String toString() {
        return stateName;
    }
}
