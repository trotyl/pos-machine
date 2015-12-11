package me.trotyl.pos.promotion;

import me.trotyl.pos.model.Discount;
import me.trotyl.pos.unknown.CartItem;
import me.trotyl.pos.unknown.Rebate;
import org.javatuples.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class ComsitePromotionTest {

    private ComsitePromotion promotion;

    @Before
    public void setUp() throws Exception {

        List<Discount> discounts = asList(new Discount("ITEM000001", 75), new Discount("ITEM000005", 90));
        Promotion discountPromotion = new DiscountPromotion(discounts);

        List<String> barcodes = asList("ITEM000001", "ITEM000003");
        Promotion secondHalfPricePromotion = new SecondHalfPricePromotion(barcodes);

        promotion = new ComsitePromotion(discountPromotion, secondHalfPricePromotion);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void apply_should_have_proper_result() {

        List<CartItem> items = asList(
                new CartItem("ITEM000001", 40.0, 3),
                new CartItem("ITEM000003", 50.0, 2),
                new CartItem("ITEM000005", 60.0, 2));

        Pair<List<CartItem>, List<Rebate>> pair = promotion.apply(items);
        List<CartItem> appliedItems = pair.getValue0();

        assertThat(appliedItems.size(), is(3));
        assertThat(appliedItems.get(0).getBarcode(), is("ITEM000001"));
        assertThat(appliedItems.get(0).getPrice(), is(30.0));
        assertThat(appliedItems.get(1).getBarcode(), is("ITEM000003"));
        assertThat(appliedItems.get(1).getPrice(), is(50.0));
        assertThat(appliedItems.get(2).getBarcode(), is("ITEM000005"));
        assertThat(appliedItems.get(2).getPrice(), is(54.0));

        List<Rebate> rebates = pair.getValue1();

        assertThat(rebates.size(), is(2));
        assertThat(rebates.get(0).getBarcode(), is("ITEM000001"));
        assertThat(rebates.get(0).getRebate(), is(15.0));
        assertThat(rebates.get(1).getBarcode(), is("ITEM000003"));
        assertThat(rebates.get(1).getRebate(), is(25.0));
    }

    @Test
    public void apply_should_have_proper_result_when_empty() {

        Pair<List<CartItem>, List<Rebate>> pair = promotion.apply(Collections.emptyList());

        assertThat(pair.getValue0().size(), is(0));
        assertThat(pair.getValue1().size(), is(0));
    }
}
