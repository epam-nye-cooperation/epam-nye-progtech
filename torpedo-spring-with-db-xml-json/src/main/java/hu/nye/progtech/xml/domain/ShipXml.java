package hu.nye.progtech.xml.domain;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@NoArgsConstructor
@Data
@AllArgsConstructor
public class ShipXml {
    private int length;
    private int startRow;
    private int startCol;
    private boolean isHorizontal;
    private boolean[] hits;

}
