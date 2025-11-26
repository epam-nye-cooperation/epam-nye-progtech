package hu.nye.progtech.xml.domain;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@NoArgsConstructor
@Data
@AllArgsConstructor
public class PlayerXml {

    private String name;
}
