package hu.nye.progtech.sudoku.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hu.nye.progtech.sudoku.xml.adapter.LocalDateTimeAdapter;
import hu.nye.progtech.sudoku.xml.model.Block;
import hu.nye.progtech.sudoku.xml.model.GameState;
import hu.nye.progtech.sudoku.xml.model.Player;
import hu.nye.progtech.sudoku.xml.model.Row;
import jakarta.xml.bind.*;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) throws JAXBException, JsonProcessingException {
        Player player = new Player(1, "Player_1", "Password", getGameState());
        JAXBContext context = JAXBContext.newInstance(Player.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.marshal(player, new File("player.xml"));

        Unmarshaller unmarshaller = context.createUnmarshaller();
        unmarshaller.setAdapter(new LocalDateTimeAdapter());
        Player importedPlayer = (Player) unmarshaller.unmarshal(new File("player.xml"));
        System.out.println(importedPlayer);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        //objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String jsonString = objectMapper.writeValueAsString(player);
        System.out.println(jsonString);

        Player player2 = objectMapper.readValue(jsonString, Player.class);
        System.out.println(player2);

    }

    private static GameState getGameState(){
        Block block1 = new Block(1, 2, false);
        Block block2 = new Block(2, 3, true);
        Block block3 = new Block(3, 4, false);
        Block block4 = new Block(4, 5, true);

        Row row1 = new Row(1, List.of(block1, block2));
        Row row2 = new Row(2, List.of(block3, block4));

        return new GameState(1, List.of(row1, row2));
    }

    private static Row getRow(){
        Block block1 = new Block(1, 2, false);
        Block block2 = new Block(2, 3, true);
        Block block3 = new Block(3, 4, false);
        Block block4 = new Block(4, 5, true);

        Row row1 = new Row(1, List.of(block1, block2));

        return row1;
    }
}
