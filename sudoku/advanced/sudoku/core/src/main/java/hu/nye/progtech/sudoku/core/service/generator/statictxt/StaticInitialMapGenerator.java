package hu.nye.progtech.sudoku.core.service.generator.statictxt;

import hu.nye.progtech.sudoku.core.model.MapVO;
import hu.nye.progtech.sudoku.core.service.generator.InitialMapGenerator;
import hu.nye.progtech.sudoku.core.service.map.MapReaderFacade;

public class StaticInitialMapGenerator implements InitialMapGenerator {

    private final MapVO staticMap;
    
    public StaticInitialMapGenerator(MapReaderFacade mapReaderFacade) {
        this.staticMap = mapReaderFacade.readMap();
    }

    @Override
    public MapVO generateMap() {
        return staticMap;
    }

}
