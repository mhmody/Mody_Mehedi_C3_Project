import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class RestaurantTest {

    Restaurant restaurant;

    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        LocalTime openingTime = LocalTime.parse("10:00:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        Restaurant restaurant = Mockito.spy(new Restaurant("Mehedi's cafe", "Kolkata", openingTime, closingTime));
        Mockito.doReturn(LocalTime.parse("11:00:25")).when(restaurant).getCurrentTime();
        assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        LocalTime openingTime = LocalTime.parse("10:00:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        Restaurant restaurant = Mockito.spy(new Restaurant("Mehedi's cafe 1", "Kolkata", openingTime, closingTime));
        Mockito.doReturn(LocalTime.parse("09:00:25")).when(restaurant).getCurrentTime();
        assertFalse(restaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        Restaurant restaurant = getRestaurant();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        Restaurant restaurant = getRestaurant();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        Restaurant restaurant = getRestaurant();
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<<<<<<<<<<Price Calculation>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void Calculate_Price_of_the_selectedMenu_with_name() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");

        List<Item> lstItems = new ArrayList<Item>();

        List<String> lstItemsSingle = new ArrayList<String>();
        lstItemsSingle.add("Test Menu Item 1");

        List<String> lstItemsMultiple = new ArrayList<String>();
        lstItemsMultiple.add("Test Menu Item 2");
        lstItemsMultiple.add("Test Menu Item 3");

        List<String> lstItemsMultipleSecond = new ArrayList<String>();
        lstItemsMultipleSecond.add("Test Menu Item 4");
        lstItemsMultipleSecond.add("Test Menu Item 5");
        lstItemsMultipleSecond.add("Test Menu Item 1");

        restaurant = new Restaurant("Testing cafe", "GPU", openingTime, closingTime);
        restaurant.addToMenu("Test Menu Item 1", 119);
        restaurant.addToMenu("Test Menu Item 2", 223);
        restaurant.addToMenu("Test Menu Item 3", 228);
        restaurant.addToMenu("Test Menu Item 4", 143);
        restaurant.addToMenu("Test Menu Item 5", 197);


        assertEquals(119, restaurant.getPriceOfSelectedItems(lstItemsSingle));
        assertEquals(223 + 228, restaurant.getPriceOfSelectedItems(lstItemsMultiple));
        assertNotEquals(223 + 228 + 1, restaurant.getPriceOfSelectedItems(lstItemsMultiple));
        assertNotEquals(143 + 197 + 119, restaurant.getPriceOfSelectedItems(lstItemsMultiple));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<Price Calculation>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<<<<<<<<<<Name Verify>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void search_for_existing_restaurant_should_return_expected_restaurant_object()
    {
        Restaurant restaurant = getRestaurant();
        assertEquals("Amelie's cafe", restaurant.getName());
    }
    //<<<<<<<<<<<<<<<<<<<<<<<Name Verify>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public Restaurant getRestaurant()
    {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        return restaurant;
    }
}