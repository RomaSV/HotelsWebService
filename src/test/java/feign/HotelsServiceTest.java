package feign;

import hotels.Hotel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HotelsServiceTest {
    private HotelsClient hotelsClient;

    @BeforeEach
    public void setHotelsClient() {
        HotelFeignClientBuilder builder = new HotelFeignClientBuilder();
        hotelsClient = builder.createHotelsClient();
    }
    @Test
    public void getHotels() {
        List<Hotel> hotels = hotelsClient.getHotels(new HashMap<>());
        assertEquals(3, hotels.size());
    }
}

