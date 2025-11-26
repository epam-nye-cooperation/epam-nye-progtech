package hu.nye.progtech.xml.domain;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@XmlRootElement
@NoArgsConstructor
@Data
@AllArgsConstructor
public class GameMapXml {
    private int size;
    private ShipXml ship;
    private Set<RocketDestinationXml> missedTargets;

}
