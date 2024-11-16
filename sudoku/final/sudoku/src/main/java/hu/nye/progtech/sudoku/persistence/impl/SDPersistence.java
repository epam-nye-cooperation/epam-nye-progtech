package hu.nye.progtech.sudoku.persistence.impl;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.persistence.GameSaveRepository;
import hu.nye.progtech.sudoku.persistence.Persistence;
import hu.nye.progtech.sudoku.persistence.model.GameSave;
import hu.nye.progtech.sudoku.service.exception.MapReadingException;
import hu.nye.progtech.sudoku.service.exception.MapSavingException;
import hu.nye.progtech.sudoku.service.map.parser.MapParser;
import hu.nye.progtech.sudoku.service.util.MapToStringUtil;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

// Spring-Data based persistence
@Component
public class SDPersistence implements Persistence {

    private static final Logger LOGGER = LoggerFactory.getLogger(SDPersistence.class);

    private final DataSource dataSource;
    private final GameSaveRepository repository;
    private final MapToStringUtil mapToStringUtil;
    private final MapParser mapParser;

    public SDPersistence(DataSource src, GameSaveRepository repo,
                         MapToStringUtil mapToStringUtil, MapParser mapParser) {
        this.dataSource = src;
        this.repository = repo;
        this.mapToStringUtil = mapToStringUtil;
        this.mapParser = mapParser;
        init();
    }

    private void init() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource(JdbcPersistence.DBINIT));
        populator.execute(dataSource);
        LOGGER.info("Ensured that the " + JdbcPersistence.DBINIT + " is loaded");
    }

    @Override
    public void save(MapVO currentMap) {
        GameSave gameSave = new GameSave();
        try {
            repository.delete(gameSave);
            gameSave.setMap(mapToStringUtil.convertMapVoMapToString(currentMap));
            gameSave.setFixed(mapToStringUtil.convertMapVoFixedToString(currentMap));
            repository.saveAndFlush(gameSave);
        } catch(DataAccessException e) {
            throw new MapSavingException("Failed to parse loaded map", e);
        }
    }

    @Override
    public MapVO load() {
        try {
            GameSave save = repository.findById(GameSave.DEFAULT_ID).orElseThrow(EntityNotFoundException::new);
            return mapParser.parseMap(save);
        } catch (DataAccessException e) {
            throw new MapReadingException("Failed to parse loaded map", e);
        }
    }
}
