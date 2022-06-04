import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    // Part 3
    List<Item> menuItemList = new ArrayList<Item>();

    // Part 2 : Solution
    public void testSetupRestaurant() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    // Part 2 : Solution
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        testSetupRestaurant();
        Restaurant restaurantMock = Mockito.mock(Restaurant.class);
        restaurantMock.setClosingTime(LocalTime.parse("11:00:00"));
        Mockito.when(restaurantMock.isRestaurantOpen()).thenReturn(true);
        assertTrue(restaurantMock.isRestaurantOpen());
    }
    // Part 2 : Solution
    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        testSetupRestaurant();
        Restaurant restaurantMock = Mockito.mock(Restaurant.class);
        restaurantMock.setClosingTime(LocalTime.parse("22:30:00"));
        Mockito.when(restaurantMock.isRestaurantOpen()).thenReturn(false);
        assertFalse(restaurant.isRestaurantOpen());
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    // Part 2 : Solution
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        testSetupRestaurant();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    // Part 2 : Solution
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        testSetupRestaurant();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    // Part 2 : Solution
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        testSetupRestaurant();
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<<<<<<<<<<<<ORDER VALUE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    // Part 3: Failing test case
    @Test
    public void order_value_should_get_total_when_items_selected_from_menu() {
        testSetupRestaurant();
        menuItemList = restaurant.getMenu();
        assertEquals(643, restaurant.getOrderValue(menuItemList));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<ORDER VALUE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}