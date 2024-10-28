package hu.nye.structural.composite;

import lombok.Value;

import java.util.Collection;

@Value
public class CompositeGraphic implements Graphic {
    String name;
    Collection<Graphic> graphics;

    @Override
    public void draw() {
        System.out.println("Start " + name + " -->");
        graphics.forEach(Graphic::draw);
        System.out.println("<-- End " + name);
    }
}
