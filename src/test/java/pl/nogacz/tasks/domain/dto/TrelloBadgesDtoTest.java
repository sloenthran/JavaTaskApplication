package pl.nogacz.tasks.domain.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TrelloBadgesDtoTest {
    private TrelloBadgesDto trelloBadgesDto = new TrelloBadgesDto(1, 2, 3);

    @Test
    public void getVotes() {
        //When
        int votes = trelloBadgesDto.getVotes();

        //Then
        assertEquals(1, votes);
    }

    @Test
    public void getBoard() {
        //When
        int board = trelloBadgesDto.getBoard();

        //Then
        assertEquals(2, board);
    }

    @Test
    public void getCard() {
        //When
        int card = trelloBadgesDto.getCard();

        //Then
        assertEquals(3, card);
    }
}